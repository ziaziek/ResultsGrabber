package start;

import logging.LogPc;
import gubas.javaapplication1.FormsCaller;
import gui.MainWindow;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logging.LoggerCustom;

public class ResultsGrabber {

	//private LoggerCustom log = null;
        // data from the web

	/**
	 * @param args
	 */
	public static void main(String[] args) {           
            try {
                LogPc.logConfigure();
            } catch (IOException ex) {
                Logger.getLogger(ResultsGrabber.class.getName()).log(Level.SEVERE, null, ex);
            }
            LogPc.Pclog.info("Starting");
		FormsCaller.callNewMainWindow("Tennis Analyzer", new MainWindow());
	}


}
