package database;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import data.Players;

import errors.DataDealerReadException;
import errors.DataDealerWriteException;

public class DataDealer {

	Session session;
	public Session getSession() {
		return session;
	}

	protected String configLocation = "E:\\Przemek\\GitRepo\\ResultsGrabbing\\hib\\hibernate.cfg.xml";
	protected String configPersistentPackage = "data";
	
	public DataDealer(){
		SessionFactory factory = new Configuration().configure(new File(configLocation)).addClass(Players.class).buildSessionFactory();
		session = factory.openSession();

	}
	
	public boolean alreadyExists(Class<?> cname, int id){
		if(session!=null && session.isOpen()){
			return session.get(cname, new Integer(id))!=null;
		}
		return false;
	}
	
	public int Write(Object obj) throws DataDealerWriteException{
		if(session!=null && session.isOpen()){
			session.saveOrUpdate(obj);
			return 0;
		}
		throw new DataDealerWriteException();
	}
	
	public Object readData(Class<?> cname, int id) throws DataDealerReadException{
		if(session!=null && session.isOpen()){
			return session.get(cname, new Integer(id));
		}
		throw new DataDealerReadException();
		
	}
	
	public void close(){
		if(session!=null){
			session.flush();
			session.close();
			session=null;
		}
	}
}
