package start;

import data.Games;
import java.io.IOException;
import java.net.URL;
import logging.LogPc;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import data.impl.FileStorage;
import data.interfaces.IDataStorage;
import database.DataDealer;
import gubas.javaapplication1.FormsCaller;
import gui.MainWindow;
import processing.impl.DefaultInfoServiceFactory;
import tests.LoaderTests;

public class ResultsGrabber {

	private static Logger log = LogPc.Pclog;
	public static int playersTimeOut = 1000; // time out between reading players
	protected static String playersListFile = "C:/Users/ResultsGrabber/PlayersList.txt"; //to be set by the users
        protected static  String dataOut = "C:/Users/ResultsGrabber/dataOut.txt"; //to be set by the users
        protected static String playersResultsPrefix = "C:/Users/ResultsGrabber/Results/Player_";
        // data from the web

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		logConfigure();
		log.info("Starting...");
                //Remove and uncomment calling the window
                //loadGames();
		FormsCaller.callNewMainWindow("Tennis Analyzer", new MainWindow());
	}
        
        private static void loadData(){
            IDataStorage storage = new FileStorage();
		IDataStorage storedNames = new FileStorage();
		storage.setStringConnection(dataOut);
		storedNames.setStringConnection(playersListFile);
		Grabber g = new Grabber();

		try {
			int i = 0;
			for (URL u : g.createURLList(playersListFile)) {
				System.out.println("Processing " + u.toString());
				log.info("Processing " + u.toString());
				g.processDownload(u);
				storage.setStringConnection(playersResultsPrefix
						+ i + ".txt");
				storage.Connect();
				g.writeData(storage);
				i++;
				if (Thread.currentThread() != null) {
					try {
						Thread.sleep(playersTimeOut);
						log.info("Thread stopped.");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		log.info("Finished.");
		// g.processDownload();
		// g.writeData(storage);
        }
        
    private static void logConfigure() {
        URL ul = ResultsGrabber.class.getResource("../log4j.properties");
        PropertyConfigurator.configure(ul);
    }
    
    
    private static void loadGames(){
        DataDealer d = new DataDealer();
		Loader l = new Loader("C:/Users/ResultsGrabber/Results");
		l.setRetrievalServiceFactory(new DefaultInfoServiceFactory());
                l.load(new Class<?>[]{Games.class});
                d.close();
    }
}
