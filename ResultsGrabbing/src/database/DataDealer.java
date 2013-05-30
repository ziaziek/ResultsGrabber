package database;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import errors.DataDealerReadException;
import errors.DataDealerWriteException;

public class DataDealer {

	Session session;
	protected String configLocation = "E:\\Users\\Przemek\\Documents\\workspace-sts-3.2.0.RELEASE\\ResultsGrabbing\\hib\\hibernate.cfg.xml";
	
	public DataDealer(){
		SessionFactory factory = new Configuration().configure(new File(configLocation)).buildSessionFactory();
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
}
