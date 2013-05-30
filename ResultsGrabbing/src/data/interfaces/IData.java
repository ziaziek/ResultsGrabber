package data.interfaces;

import java.util.List;

import org.hibernate.metamodel.domain.Attribute;

public interface IData extends IFilterable {

	public List<Attribute> getAttributes();
	
	public Object getValues(Attribute attr);
	
	public void Load(Object v);
}
