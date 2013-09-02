package start;

import data.Games;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import logging.LogPc;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.helpers.DefaultHandler;

import structuresdefinition.WebSiteHandler;

import data.Matches;
import data.Players;
import data.impl.FileStorage;
import data.impl.GrabbedTextData;
import data.impl.RawData;
import data.interfaces.IData;
import data.interfaces.IDataStorage;
import data.interfaces.ISample;
import database.DataDealer;
import errors.DataDealerReadException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.hibernate.Query;

public class Grabber {

	private Logger log = LogPc.Pclog;
	protected WebSiteHandler handler;
	protected final String addressPattern = "http://www.atpworldtour.com/Tennis/Players/Top-Players/???.aspx?t=pa&y=0&m=s&e=0#";
	public IData Data;
    public List<ISample> samplesList = new ArrayList<>();
	protected List<Byte> ByteBuffer = new ArrayList<>();
	public Grabber(){

	}
	
 // wyodrebnij dane z pomiedzy znacznikow
	public void processDownload() throws  IOException{
		String hloc = "dataIn.txt";
		if(handler==null){
		handler = new WebSiteHandler(hloc);	
		}
		log.info("WebSitehandler initiated at "+hloc);
		handler.setClassContaining("commonProfileContainer");
		handler.setClassActivityInfo("bioPlayActivityInfo");
		handler.setClassActivityTableAlt("bioTableAlt");
		handler.setIdPlayerBioInfoList("playerBioInfoList");
                handler.setPlayerBioInfoRank("playerBioInfoRank");
		samplesList = handler.run();
		Data = new GrabbedTextData();
		if(samplesList!=null && !samplesList.isEmpty()){
			Data.Load(samplesList);
		}
		
	}
	
	public void processDownload(URL url) throws IOException{
		handler = new WebSiteHandler(url);
		processDownload();
	}
	
	public void download(URL url) throws IOException {
		// TODO Auto-generated method stub
		byte buf[] = new byte[1];
		url.openConnection();
		InputStream istr = url.openStream();
		while(istr.read(buf)!=-1){
			for(byte b: buf){
				ByteBuffer.add(b);	
			}
		}
		
		istr.close();
		
		Data = new RawData();
		Data.Load(convertByteBuffer().toString());
	}

	
	public void writeData(IDataStorage storage){
		try {
			storage.Connect();
			storage.writeData(Data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LogPc.Pclog.error(e.getMessage(), e);
		}
		
	}
	
	protected StringBuilder convertByteBuffer(){
		StringBuilder strb = new StringBuilder();
		for(byte b: ByteBuffer){
			strb.append((char)b);
		}
		return strb;
		
	}
	public void PrintByteBuffer() {
		// TODO Auto-generated method stub
		
		System.out.println(convertByteBuffer().toString());
	}
	
	public  List<URL> createURLList(String playersFileName) throws IOException{
		LogPc.Pclog.info("Creating list of URLs");
		List<URL> retList = new ArrayList<>();
		FileStorage stor = new FileStorage();
		stor.setStringConnection(playersFileName);
		LogPc.Pclog.info("FileName used: "+ playersFileName);
		//stor.Connect();
		RawData pl = (RawData) stor.readData(null);
		if(pl.getValues(null) instanceof List){
			for(String Name: (List<String>)pl.getValues(null)){
			Name = Name.replace(" ", "-");
			retList.add(new URL(addressPattern.replace("???", Name)));
		}
		}
		LogPc.Pclog.info("URL list size = "+ retList.size());
		return retList;
	}
        
         /**
         * Updates information about the player's rank, basing on the information 
         * from the gmes as an apponent at the given date
         * @param IdPlayer - id of the player that rank at the given date should be updated
         * @return the rank of the player at the given date
         * @author Przemek
         */
    protected Integer updatePlayersRankInfo(Integer idPlayer, Calendar date) throws DataDealerReadException {
        Integer rank = null;
        DataDealer d = new DataDealer();
        int dateTolerance = 7;
        Players p = (Players)d.readData(Players.class , idPlayer);
        List<Games> games = null;
        Query qry = d.getSession().createQuery("from Games as g  join g.idmatches as m where g.idPlayers=:idp and m.Date between(1, :d2):d");
        qry.setParameter(0, idPlayer);
        qry.setParameter(1, date);
        qry.setParameter(2, date);
        int i = 0;
        while(games==null || games.isEmpty()){
            games = d.readQueryBasedData(qry);
            i++;
            Calendar dateFrom = Calendar.getInstance();
            dateFrom.setTime(date.getTime());
            Calendar dateTo = Calendar.getInstance();
            dateTo.setTime(date.getTime());
            dateFrom.add(Calendar.DAY_OF_MONTH, -i*dateTolerance);
            dateTo.add(Calendar.DAY_OF_MONTH, i* dateTolerance);
            qry.setParameter(1,dateFrom );
            qry.setParameter(2, dateTo);
        }
                  
        if(games.size()>0){
            for(Games g : games){
                //Calculate average rank
                rank+=g.getOponentRank();
            }
            rank = rank/games.size();
        }
        return rank;
    }
}
