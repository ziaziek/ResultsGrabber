package start;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import data.Players;
import data.impl.FileStorage;

import logging.LogPc;

public class Loader {

	private Logger log = LogPc.Pclog;
	private final String playersMarker = "Players:";
	private final String matchesMarker = "Match:";
	private String dirName;
	
	public Loader(String fn){
		dirName = fn;
	}
	
	protected void loadPlayers(){
		File f0 = new File(dirName);
		List<Players> playersList = new ArrayList<Players>();
		if(f0.isDirectory()){
			for(File f: f0.listFiles()){
				List<String> fContents = new ArrayList<String>();
					try {
						playersList.add(extractPlayers(Files.readAllLines(f.toPath(), Charset.forName("utf-8") )));
					} catch (IOException e) {
						log.error(e.getLocalizedMessage(), e);
					}
			}
		}
		
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
