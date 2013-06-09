package database;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.CriteriaImpl;

import data.Matches;
import data.Players;

import errors.DataDealerReadException;
import errors.DataDealerWriteException;

public class DataDealer {

	Session session;

	List<IDataDealerListener> listeners = new ArrayList<IDataDealerListener>();
	
	public List<IDataDealerListener> getListeners() {
		return listeners;
	}

	public void setListeners(List<IDataDealerListener> listeners) {
		this.listeners = listeners;
	}

	public Session getSession() {
		return session;
	}

	protected String configLocation = "E:\\Przemek\\GitRepo\\ResultsGrabbing\\hib\\hibernate.cfg.xml";
	protected String configPersistentPackage = "data";
	/**
	 * Determines how many objects are kept in session memory until they are flushed to the database.
	 */
	protected int flushCounter = 1;
	
	public int getFlushCounter() {
		return flushCounter;
	}

	public void setFlushCounter(int flushCounter) {
		this.flushCounter = flushCounter;
	}

	public DataDealer() {

		createAndOpenSession();
	}

	protected void createAndOpenSession() {
		SessionFactory factory = new Configuration()
				.configure(new File(configLocation)).addClass(Players.class).addClass(Matches.class)
				.buildSessionFactory();
		session = factory.openSession();
	}

	public boolean alreadyExists(Class<?> cname, int id) {
		if (session == null || !session.isOpen()) {
			createAndOpenSession();
		}
		if (session != null && session.isOpen()) {
			boolean res = session.get(cname, new Integer(id)) != null;
			return res;
		}
		return false;
	}

	public int Write(Object obj) throws DataDealerWriteException {
		if (session != null && session.isOpen()) {
			Transaction tx = session.beginTransaction();
			if (!(obj instanceof List)) {
				try {
					session.saveOrUpdate(obj);
					notifyListeners(1);
				} catch (org.hibernate.JDBCException ex) {
					throw new DataDealerWriteException();
				}
			} else {
				int counter = 0;
				for (Object o : (List) obj) {
					try {
						session.saveOrUpdate(o);
						counter++;
						notifyListeners(counter);
						System.out.println(counter);
//						if (counter % flushCounter == 0) {
//							session.flush();
//						}
					} catch (org.hibernate.JDBCException ex ) {
						System.out.println("ERROR!");
					}

				}
			}
			if(tx!=null && tx.isActive()){
				tx.commit();
			}
			
			return 0;
		}
		throw new DataDealerWriteException();
	}

	
	protected void commitWrite(Session session){
		Transaction tx = session.beginTransaction();
		try {
			tx.commit();
		} catch (org.hibernate.JDBCException ex){
			tx = null;
		}
	}
	
	public Object readData(Class<?> cname, int id)
			throws DataDealerReadException {
		if (session != null && session.isOpen()) {
			return session.get(cname, new Integer(id));
		}
		throw new DataDealerReadException();

	}

	public void close() {
		if (session != null) {
			session.flush();
			session.close();
			session = null;
		}
	}
	
	protected void notifyListeners(int x){
		for(IDataDealerListener l: listeners){
			l.changeNumberOfProcessed(x);
		}
		
	}
}
