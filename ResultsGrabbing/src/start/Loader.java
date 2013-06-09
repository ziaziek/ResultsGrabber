package start;

import java.io.File;

import logging.LogPc;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import processing.impl.InfoServiceDirectoryException;
import processing.interfaces.IServiceFactory;
import data.Games;
import data.Matches;
import data.Players;
import database.DataDealer;
import database.IDataDealerListener;
import errors.DataDealerWriteException;

public class Loader implements IDataDealerListener {

	protected Logger log = LogPc.Pclog;
	private String dirName;
	protected int  numberOfProcessedItems, numberOfItemsToProcess =0;
	protected Class<?>[] classes = {Players.class, Matches.class, Games.class};
	private IServiceFactory retrievalServiceFactory = null;
	
	public IServiceFactory getRetrievalServiceFactory() {
		return retrievalServiceFactory;
	}

	public void setRetrievalServiceFactory(IServiceFactory retrievalServiceFactory) {
		this.retrievalServiceFactory = retrievalServiceFactory;
	}

	public Loader(String fn){
		dirName = fn;
	}
	
	public int load(Class<?>[] classesToRun){
		DataDealer d = new DataDealer();
			d.getListeners().add(this);
			for(int i=0; i<classesToRun.length; i++){
				int nt = loadInfo(d, classesToRun[i]);
				if(log.getLevel()== Level.INFO){
					log.info("Loaded elements: " + nt);
				}
			}
			return numberOfProcessedItems;
	}
	
	public int load(){	
			return load(classes);
	}
	
	protected int loadInfo(DataDealer d, Class<?> c){
		numberOfProcessedItems = 0;
		try {
			if (retrievalServiceFactory != null) {
				d.Write(retrievalServiceFactory.getInfoService(c,
						new File(dirName)).getInfoList());
			}
		} catch (DataDealerWriteException | InfoServiceDirectoryException e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return numberOfProcessedItems;
	}
	
	public void changeNumberOfProcessed(int inserted) {
		numberOfProcessedItems=inserted;
	}
}
