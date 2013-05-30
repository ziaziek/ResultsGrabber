package data.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import data.interfaces.IData;
import data.interfaces.IDataCondition;
import data.interfaces.IDataStorage;

public class FileStorage implements IDataStorage {

	protected String fileName="";
	protected BufferedReader reader;
	protected BufferedWriter writer;
	protected File f;
	protected IData Data;
	@Override
	public IData readData(IDataCondition cond) throws IOException {
		//reader = Files.newBufferedReader(Paths.get(fileName), Charset.forName("utf-8"));
		if(Data==null){
			Data = new RawData();
		}
		Data.Load(Files.readAllLines(Paths.get(fileName), Charset.defaultCharset()));
//		String line, loadData="";
//		while((line=reader.readLine())!=null){
//			loadData+=line;
//		}
		
//		Data.Load(loadData);
//		reader.close();
//		reader=null;
		return Data;
	}

	@Override
	public int writeData(IData data) throws IOException {
		if(fileName !=null){
		writer = Files.newBufferedWriter(Paths.get(fileName), Charset.forName("utf-8"));
		writer.write((String) data.getValues(null));
		writer.close();
		writer=null;
		return 0;	
		}
		return -1;
	}

	@Override
	public int deleteData(IDataCondition cond) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setStringConnection(String cstr) {
		fileName=cstr;
	}

	@Override
	public String getStringConnection() {
		return fileName;
	}

	@Override
	public int Connect() throws IOException {
		if(!Files.exists(Paths.get(fileName))){
			f = new File(fileName);
			f.createNewFile();
		}
		return 0;
	}

	@Override
	public void Disconnect() {
		// TODO Auto-generated method stub
		
	}

}
