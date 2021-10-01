package test.dataAccess;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Admin;
import domain.Apostua;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import domain.RegisteredUser;
import domain.Worker;
import exceptions.UserAlreadyExist;

public class TestDataAccess {
	protected  EntityManager  db;
	protected  EntityManagerFactory emf;

	ConfigXML  c=ConfigXML.getInstance();


	public TestDataAccess()  {
		
		System.out.println("Creating TestDataAccess instance");

		open();
		
	}

	
	public void open(){
		
		System.out.println("Opening TestDataAccess instance ");

		String fileName=c.getDbFilename();
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean removeEvent(Event ev) {
		System.out.println(">> DataAccessTest: removeEvent");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e!=null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			return true;
		} else 
		return false;
    }
		
		public Event addEventWithQuestion(String desc, Date d, String question, float qty) {
			System.out.println(">> DataAccessTest: addEvent");
			Event ev=null;
				db.getTransaction().begin();
				try {
				    ev=new Event(desc,d);
				    ev.addQuestion(question, qty);
					db.persist(ev);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return ev;
	    }
		public boolean existQuestion(Event ev,Question q) {
			System.out.println(">> DataAccessTest: existQuestion");
			Event e = db.find(Event.class, ev.getEventNumber());
			if (e!=null) {
				return e.DoesQuestionExists(q.getQuestion());
			} else 
			return false;
			
		}
		
		public void addUser(String Izena, String Id, String Email, String Pasahitza, int urtea, int hilabetea, int eguna, long BankuZenbakia,int tipo) throws UserAlreadyExist{//0=Admin,1=worker,2=register user
			db.getTransaction().begin();
			db.persist(new RegisteredUser(Izena, Pasahitza, Email, Id, BankuZenbakia, UtilDate.newDate(urtea, hilabetea, eguna)));
		}
		
		public void addPronostico(String Erantzuna, double Cuota,Question galdera) {
			db.getTransaction().begin();
			db.persist(new Pronostico(Erantzuna,Cuota,galdera));
		}
		
		public boolean existApostua(Apostua ap) {
			System.out.println(">> DataAccessTest: existApostua");
			Apostua a = db.find(Apostua.class, ap.getId());
			if (a!=null) {
				return true;
			} else 
			return false;
		}
}

