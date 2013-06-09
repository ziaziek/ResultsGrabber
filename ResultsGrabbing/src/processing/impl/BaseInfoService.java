package processing.impl;

import java.io.File;
import java.util.Calendar;

import logging.LogPc;

import org.apache.log4j.Logger;

public class BaseInfoService {

	protected File dir = null;
	
	protected int  numberOfProcessedItems, numberOfItemsToProcess =0;
	
	public static final String  DATE_SEPARATOR = "-";
	
	public static final String INFO_SEPARATOR = ",";
	
	protected static Logger log = LogPc.Pclog;
	protected static final String playersMarker = "Player:";
	protected static final String matchesMarker = "Match:";
	protected static final String gamesMarker = "Game:";
	
	public static Calendar convertDateStringToCalendar(String dateStr, String separator){
		String[] dateArray = dateStr.split("-");
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(dateArray[2]),
				Integer.parseInt(dateArray[1]),
				Integer.parseInt(dateArray[0]));
		return c;
	}
}
