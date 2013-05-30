package database;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DataWriter {

	Session session;
	protected String configLocation = "E:\\Users\\Przemek\\Documents\\workspace-sts-3.2.0.RELEASE\\ResultsGrabbing\\hib\\hibernate.cfg.xml";
	
	public DataWriter(){
		SessionFactory factory = new Configuration().configure(new File(configLocation)).buildSessionFactory();
		session = factory.openSession();

	}
	
	public boolean alreadyExists(Class<?> cname, int id){
		if(session!=null && session.isOpen()){
			return session.get(cname, new Integer(id))!=null;
		}
		return false;
	}
	
}
