package logging;

import org.apache.log4j.Logger;

public class LogPc  {

	public static Logger Pclog=  Logger
			.getLogger("ResultsGrabberLog");
	
	public static final String pattern = "[%-4r] [%d{ISO8601}] [%t] [%c] [%l] [%-5p] [- %m]%n";

}
