package processing.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


import data.Games;
import data.GamesResults;
import data.Matches;
import data.MatchesExt;
import data.Players;
import data.PlayersHelper;
import database.DataDealer;

import processing.interfaces.IInfoService;
import processing.interfaces.IMatchFoundEventListener;

public class GamesInfoService extends BaseInfoService implements IInfoService, IMatchFoundEventListener {

	protected int currentPlayerId = 0;
	protected List<Games> gamesList = null;
	protected List<String> fstr= null;
	@Override
	public List getInfoList() throws InfoServiceDirectoryException {
		List<Games> retList = new ArrayList<Games>();
		DataDealer d = new DataDealer();
		if(dir==null){
			throw new InfoServiceDirectoryException();
		}
		if(dir.isDirectory()){
			if(gamesList==null){
				gamesList = new ArrayList<Games>();
			}
			for(File f : dir.listFiles()){
				try {
					fstr = Files.readAllLines(f.toPath(), Charset.forName("utf-8"));
					Players curP = PlayersInfoService.extractPlayers(fstr);
					currentPlayerId = PlayersHelper.findByName(d, curP.getFirstName(), curP.getLastName()).get(0).getId();
					List<Matches> m = MatchesInfoService.extractMatchesInfo(fstr, this); //this method will call another method which will fill the list with data.
					//that is done so to avoid code repetition
				} catch (IOException e) {
					log.error(e.getLocalizedMessage(), e);
				}
			}
		}
		return gamesList;
	}

	@Override
	public void setDirectory(File dir) {
		this.dir=dir;
		
	}
	
	protected Games extractGamesInfo(String t, DataDealer d, int idp, int idm) {
		Games g = new Games();
		String[] info = t.split(INFO_SEPARATOR);
		if (info.length == 4) {
			g.setAge(Integer.parseInt(info[0]));
			g.setResult(GamesResults.translate(info[2]));
			g.setAvgPointDiff(Double.parseDouble(info[3]));
			g.setIdMatches(idm);
			g.setIdPlayers(idp);
			g.setIdOponents(PlayersHelper.findByName(d, info[1], info[2]).get(0).getId());
		}
		return g;
	}

	@Override
	public void setProcessedMatchAndItemNumber(Matches m, int itemNumber) {
		DataDealer d = new DataDealer();
		if(fstr==null){
			return;
		}
		if(gamesList==null){
			gamesList = new ArrayList<Games>();
		}
		if(fstr.size()>itemNumber+1){
			int i = itemNumber+2;
			while(i<fstr.size() && fstr.get(i).equals(gamesMarker)){
				gamesList.add(extractGamesInfo(fstr.get(i+1), d, currentPlayerId, MatchesExt.findByUniqueConstraints(d, m.getDate(), m.getCity()).get(0).getId()));
				i+=2;
			}
		}
		
	}
}
