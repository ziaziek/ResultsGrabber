package data.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


import data.interfaces.IData;
import data.interfaces.IFilter;

public class RawData implements IData {

	String name;
	Object myData;
	
	public RawData(){
		super();
		name = "text" ;
	}
	
	@Override
	public Iterator<?> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAttributes() {		
		return Arrays.asList(this.name);
	}

	@Override
	public Object getValues(String attr) {
		return myData;
	}

	@Override
	public void Load(Object v) {
		myData=v;	
	}

	@Override
	public Iterable process(IFilter filter, Iterable it) {
		// TODO Auto-generated method stub
		return null;
	}

}
