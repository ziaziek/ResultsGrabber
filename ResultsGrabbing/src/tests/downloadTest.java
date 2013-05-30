package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import start.Grabber;

public class downloadTest {

	private Grabber g; 
	
	@Before
	public void setUp() throws Exception {
		g = new Grabber();
		g.download(new URL("http://www.wp.pl"));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		if(g!=null){
			assertNotNull(g.Data);
		}
	}
	


}
