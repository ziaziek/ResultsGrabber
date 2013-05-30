package tests;

import static org.junit.Assert.*;

import java.util.List;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import start.Grabber;
import data.impl.FileStorage;
import data.interfaces.IData;
import data.interfaces.IDataStorage;

public class Grabber2Test {

	private IDataStorage store;
	private IData data;
	static String fn = "e:\\GrabberFiles\\PlayersList.txt";
	static String fnIn = "file:///e:\\dataIn.txt";
	private Grabber g;
	
	@Before
	public void setUp() throws Exception {
		store = new FileStorage();
		store.setStringConnection(fn);
		//store.Connect();
		g = new Grabber();
	}

	@AfterClass
	public static void tearDown() throws Exception {
	}

	@Test
	public void listCreationGrabberTest(){
		try {
			List<URL> ret = g.createURLList(fn);
			assertNotNull(ret);
			assertTrue(ret.size()==100);
			System.out.println(ret.get(6).toString());
			assertTrue(ret.get(6).getPath().contains("Juan-Martin-Del-Potro"));
			assertTrue(ret.get(7).getPath().contains("Jo-Wilfried-Tsonga"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


}
