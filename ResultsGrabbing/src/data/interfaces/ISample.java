package data.interfaces;

import java.util.List;

public interface ISample {

	public List<String> getAttributeNames();

	public Object getValue(String attrName);

	public Class<?> getValueType(String attrName);

	public List<Object> getValues();

	public boolean setValue(String attrName, Object v);
}
