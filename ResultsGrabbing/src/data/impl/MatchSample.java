package data.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.interfaces.ISample;

public class MatchSample implements ISample {

	public static final String DATE_ATTRIB = "Date";
	public static final String PLAYER_NAME_ATTRIB = "PlayerName";
	public static final String OPONENT_NAME_ATTRIB = "OponentName";

	protected List<String> attribs = null;
	protected Map<String, Object> vals = null;

	public MatchSample() {
		attribs = Arrays.asList(DATE_ATTRIB, PLAYER_NAME_ATTRIB,
				OPONENT_NAME_ATTRIB);
		vals = new HashMap<String, Object>();

	}

	@Override
	public List<String> getAttributeNames() {
		// TODO Auto-generated method stub
		return attribs;
	}

	@Override
	public Object getValue(String attrName) {
		// TODO Auto-generated method stub
		return vals.get(attrName);
	}

	@Override
	public Class<?> getValueType(String attrName) {
		// TODO Auto-generated method stub
		return vals.get(attrName).getClass();
	}

	@Override
	public List<Object> getValues() {

		return Arrays.asList(vals.values().toArray());
	}

	@Override
	public boolean setValue(String attrName, Object v) {
		if (attribs.contains(attrName)) {
			vals.put(attrName, v);
			return true;
		}
		return false;
	}
}
