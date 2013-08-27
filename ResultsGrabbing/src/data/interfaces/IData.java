package data.interfaces;

import java.util.List;

public interface IData extends IFilterable {

	public List<String> getAttributes();
	
	public Object getValues(String attr);
	
	public void Load(Object v);
}
