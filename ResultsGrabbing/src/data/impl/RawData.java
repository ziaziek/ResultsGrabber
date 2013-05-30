package data.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.hibernate.metamodel.domain.Attribute;
import org.hibernate.metamodel.domain.AttributeContainer;

import data.interfaces.IData;
import data.interfaces.IFilter;

public class RawData implements IData {

	Attribute name;
	Object myData;
	
	public RawData(){
		super();
		name = new Attribute() {
			
			@Override
			public boolean isSingular() {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public String getName() {
				return "text";
			}
			
			@Override
			public AttributeContainer getAttributeContainer() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	
	@Override
	public Iterator<?> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Attribute> getAttributes() {		
		return Arrays.asList(this.name);
	}

	@Override
	public Object getValues(Attribute attr) {
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
