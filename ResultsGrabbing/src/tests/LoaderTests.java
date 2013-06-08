package tests;

import static org.junit.Assert.*;

import org.apache.log4j.Level;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import processing.impl.DefaultInfoServiceFactory;

import start.Loader;

import data.Players;
import database.DataDealer;

public class LoaderTests {

	class LoaderStub extends Loader {

		public LoaderStub(String fn) {
			super(fn);
			this.log.setLevel(Level.INFO);
			// TODO Auto-generated constructor stub
		}
		
		
		public int getNumberOfItemsToProcess(){
			return super.numberOfItemsToProcess;
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
		l.setRetrievalServiceFactory(new DefaultInfoServiceFactory());
		assertTrue(l.load(new Class<?>[] {Players.class})>0);
	}

}
