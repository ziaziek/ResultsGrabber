package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import data.Players;

public class databaseTests {

	private Session sess;
	private SessionFactory factory;
	private Query truncateAll;
	
	@Before
	public void setUp() throws Exception {
		factory = new Configuration().configure(new File("E:\\Users\\Przemek\\Documents\\workspace-sts-3.2.0.RELEASE\\ResultsGrabbing\\hib\\hibernate.cfg.xml")).addClass(Players.class).buildSessionFactory();
		sess = factory.openSession();
		if(sess!=null){
			truncateAll = sess.createSQLQuery("select truncate_query()");
		}
	}

	@After
	public void tearDown() throws Exception {
		if(sess!=null && sess.isOpen()){
			try {
			List p = truncateAll.list();	
			} catch(Exception ex){
				ex.getMessage();
			}
			
			sess.close();
		}
		if(factory !=null){
			factory.close();
		}
	}

	@Test
	public void connectionTest() {
		assertNotNull(sess);
		List ret = sess.createSQLQuery("select * from players").list();
		assertTrue(!ret.isEmpty());
		
	}
	
	
	@Test
	public void insertSelectTest(){
		Players p = new Players();
		p.setCountry("Spain");
		p.setFirstName("Rafael");
		p.setLastName("Nadal");
		Calendar c = Calendar.getInstance();
		c.set(1991, 10, 25);
		p.setBirthday(c);
		sess.save(p);
		sess.flush();
		List retp = sess.createQuery("from Players as players").list();
		assertNotNull(retp);
		assertTrue(retp.size()>0);
		Players p1 = (Players)retp.get(0);
		assertEquals(p.getFirstName(), p1.getFirstName());
		assertEquals(p.getLastName(), p1.getLastName());
		assertEquals(p.getBirthday(), p1.getBirthday());
		}
	
	

}
