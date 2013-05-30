package data.interfaces;

import java.io.IOException;

public interface IDataStorage {
	
	public IData readData(IDataCondition cond) throws IOException;
	
	public int writeData(IData data) throws IOException;
	
	public int deleteData(IDataCondition cond);
	
	public void setStringConnection(String cstr);
	
	public String getStringConnection();
	
	public int Connect() throws IOException;
	
	public void Disconnect();
}
