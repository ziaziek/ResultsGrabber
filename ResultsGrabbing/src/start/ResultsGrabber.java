package start;

import logging.LogPc;
import gubas.javaapplication1.FormsCaller;
import gui.MainWindow;

public class ResultsGrabber {

	//private LoggerCustom log = null;
        // data from the web

	/**
	 * @param args
	 */
	public static void main(String[] args) {           
            LogPc.logConfigure(ResultsGrabber.class);
		FormsCaller.callNewMainWindow("Tennis Analyzer", new MainWindow());
	}


}
