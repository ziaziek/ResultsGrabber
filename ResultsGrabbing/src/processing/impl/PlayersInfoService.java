package processing.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import data.Players;

import processing.interfaces.IInfoService;

public class PlayersInfoService extends BaseInfoService implements IInfoService {

	
	@Override
	public List getInfoList() throws InfoServiceDirectoryException {
		if(dir==null){
			throw new InfoServiceDirectoryException();
		}
		List<Players> playersList = new ArrayList<Players>();
		if(dir.isDirectory()){
			for(File f: dir.listFiles()){
					try {
						if(f.length()>0){
							playersList.add(extractPlayers(Files.readAllLines(f.toPath(), Charset.forName("utf-8") )));
						}
					} catch (IOException e) {
						log.error(e.getLocalizedMessage(), e);
					}
			}
		}
		numberOfItemsToProcess = playersList.size();
		return playersList;
	}

	@Override
	public void setDirectory(File dir) {
		this.dir = dir; 
	}

	protected Players extractPlayers(List<String> t) {
		int i = 0;
		Players p = new Players();
		for (String s : t) {
			if (s.equals(playersMarker)) {
				if (t.size() > i) {
					String[] info = t.get(i + 1).split(",");		
					try {
						p.setFirstName(info[0]);
						p.setLastName(info[1]);
						String[] dateArray = info[2].split("-");
						Calendar c = Calendar.getInstance();
						c.set(Integer.parseInt(dateArray[2]),
								Integer.parseInt(dateArray[1]),
								Integer.parseInt(dateArray[0]));
						p.setBirthday(c);
						p.setCountry(info[3]);
					} catch (Exception e) {
						log.error(e.getLocalizedMessage(), e);
					}
				}
			}
			i++;
		}
		

		return p;

	}
}
