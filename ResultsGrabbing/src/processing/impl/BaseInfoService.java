package processing.impl;

import java.io.File;

import logging.LogPc;

import org.apache.log4j.Logger;

public class BaseInfoService {

	protected File dir = null;
	
	protected int  numberOfProcessedItems, numberOfItemsToProcess =0;
	
	protected Logger log = LogPc.Pclog;
	protected final String playersMarker = "Player:";
	protected final String matchesMarker = "Match:";
	protected final String gamesMarker = "Game:";
}
