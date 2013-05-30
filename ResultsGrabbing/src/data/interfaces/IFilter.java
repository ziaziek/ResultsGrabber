package data.interfaces;

import java.util.Iterator;


public interface IFilter<T> {

	boolean filtered(Iterator<T> d);

	
}
