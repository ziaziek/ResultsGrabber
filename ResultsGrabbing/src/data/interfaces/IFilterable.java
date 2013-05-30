package data.interfaces;

public interface IFilterable<T> extends Iterable<T> {

	Iterable<T> process(IFilter<T> filter, Iterable<T> it);
	
}
