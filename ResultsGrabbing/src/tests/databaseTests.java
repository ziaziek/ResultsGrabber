package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import data.Players;
import database.DataDealer;
import errors.DataDealerReadException;
import errors.DataDealerWriteException;

public class databaseTests {

	private Session sess;
	private SessionFactory factory;
	private Query truncateAll;
	
	@Before
	public void setUp() throws Exception {
		factory = new Configuration().configure(new File("E:\\Przemek\\GitRepo\\ResultsGrabbing\\hib\\hibernate.cfg.xml")).addClass(Players.class).buildSessionFactory();
		sess = factory.openSession();
		if(sess!=null){
			truncateAll = sess.createSQLQuery("select truncate_query()");
		}
	}

	@After
	public void tearDown() throws Exception {
		if(sess!=null && sess.isOpen()){
			try {
			//List p = truncateAll.list();	
			} catch(Exception ex){
				ex.getMessage();
			}
			
			sess.close();
		}
		if(factory !=null){
			factory.close();
		}
	}
@Ignore
	@Test
	public void connectionTest() {
		assertNotNull(sess);
		List ret = sess.createSQLQuery("select * from players").list();
		assertTrue(!ret.isEmpty());
		
	}
	
	@Ignore
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
	
	@Test
	public void DataDealerTest(){
		DataDealer dealer = new DataDealer();
		assertNotNull(dealer.getSession());
		assertTrue(dealer.getSession().isOpen());
		dealer.close();
		assertTrue(!dealer.getSession().isOpen());
	}
	
	@Test
	public void DataDealerWorkingTest(){
		int id = 25;
		DataDealer dealer = new DataDealer();
		Players p = new Players();
		//assertTrue(!dealer.alreadyExists(Players.class, id));
		try {
			p.setCountry("Spain");
			p.setFirstName("AAA");
			p.setLastName("BBBBB");
			Calendar c = Calendar.getInstance();
			c.set(2000, 0, 1);
			p.setBirthday(c);
			assertEquals(0, dealer.Write(p));
			id++;
			assertTrue(dealer.alreadyExists(Players.class, id));
			
		} catch (DataDealerWriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
				p = (Players) dealer.readData(Players.class, id);
				assertEquals(id, p.getId());
				assertEquals("Spain", p.getCountry());
			} catch (DataDealerReadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	@Test
	public void existstest(){
		DataDealer dealer = new DataDealer();
		int id=25;
		assertTrue(dealer.alreadyExists(Players.class, id));
	}

}
