package processing.impl;

import java.io.File;
import java.util.List;

import processing.interfaces.IInfoService;

public class MatchesInfoService extends BaseInfoService implements IInfoService {

	/**
	 * Function will get info about matches scanning through the files in the directory. 
	 * First, it will need to discover the player's name and associate it with an id.\
	 * It will aldo need to associate id's of the opponents.
	 */
	@Override
	public List getInfoList() throws InfoServiceDirectoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDirectory(File dir) {
		this.dir = dir;
	}

}
