package logging;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LoggerClass extends Logger {

	
	protected LoggerClass(String name) {
		super(name);
	}

	@Override
	public void info(Object message){
		if(this.level == Level.INFO){
			super.info(message);
		}
	}
	
	@Override 
	public void info(Object message, Throwable t){
		if(this.level == Level.INFO){
			super.info(message, t);
		}
	}
	

}
