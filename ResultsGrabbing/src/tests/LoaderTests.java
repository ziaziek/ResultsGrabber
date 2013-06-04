package tests;

import static org.junit.Assert.*;

import org.apache.log4j.Level;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import start.Loader;

import database.DataDealer;

public class LoaderTests {

	class LoaderStub extends Loader {

		public LoaderStub(String fn) {
			super(fn);
			this.log.setLevel(Level.INFO);
			// TODO Auto-generated constructor stub
		}
		
		public int loadPlayers(){
			return super.loadPlayers();
		}
	}
	
	DataDealer d = null;
	
	@Before
	public void setUp() throws Exception {
		d = new DataDealer();
	}

	@After
	public void tearDown() throws Exception {
		d.close();
	}

	
	@Test
	public void playersLoadingTest(){
		LoaderStub l = new LoaderStub("E:\\GrabberFiles\\PlayersFiles");
		assertEquals(100,l.loadPlayers());
	}

}
