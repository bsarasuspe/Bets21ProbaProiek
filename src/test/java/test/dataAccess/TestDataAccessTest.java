package test.dataAccess;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

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
import domain.User;
import domain.Worker;
import exceptions.IncorrectBetException;
import exceptions.JarraitzenZenuenException;
import exceptions.MutualFollowingException;
import exceptions.UserAlreadyExist;

public class TestDataAccessTest {
	protected  EntityManager  db;
	protected  EntityManagerFactory emf;

	ConfigXML  c=ConfigXML.getInstance();


	public TestDataAccessTest()  {
		
		System.out.println("Creating TestDataAccessTest instance");

		open();
		
	}

	
	public void open(){
		
		System.out.println("Opening TestDataAccessTest instance ");

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
		
		public RegisteredUser addUser(String Izena, String Id, String Email, String Pasahitza, int urtea, int hilabetea, int eguna, long BankuZenbakia,int tipo) throws UserAlreadyExist{//0=Admin,1=worker,2=register user
			System.out.println(">> DataAccessTest: addUser");
			db.getTransaction().begin();
			RegisteredUser u = new RegisteredUser(Izena, Pasahitza, Email, Id, BankuZenbakia, UtilDate.newDate(urtea, hilabetea, eguna));
			db.persist(u);
			db.getTransaction().commit();
			return u;
		}
		
		public Pronostico addPronostico(String Erantzuna, double Cuota,Question galdera) {
			System.out.println(">> DataAccessTest: addPronostico");
			Question q = db.find(Question.class, galdera.getQuestionNumber());
			db.getTransaction().begin();
			Pronostico p = null;
			try {
				p = new Pronostico(Erantzuna,Cuota,q);
				db.persist(p);
				db.getTransaction().commit();
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return p;
		}
		
		public boolean removeUser(User user) {
			System.out.println(">> DataAccessTest: removeUser");
			User u = db.find(User.class, user.getUsername());
			if (u!=null) {
				db.getTransaction().begin();
				db.remove(u);
				db.getTransaction().commit();
				return true;
			} else 
			return false;
	    }
		
		public boolean removeApostua(Integer id) {
			System.out.println(">> DataAccessTest: removeApostua");
			Apostua a = db.find(Apostua.class, id);
			if (a!=null) {
				db.getTransaction().begin();
				db.remove(a);
				db.getTransaction().commit();
				return true;
			} else 
			return false;
	    }
		
		public RegisteredUser jarraitu(RegisteredUser Jarraitzailea,RegisteredUser Jarraitua,double porcentage) throws MutualFollowingException,JarraitzenZenuenException {
			System.out.println(">> DataAccessTest: jarraitu");
			db.getTransaction().begin();
			RegisteredUser JarraitzaileaLocal=db.find(RegisteredUser.class, Jarraitzailea);
			try {
				RegisteredUser JarrairuaLocal=db.find(RegisteredUser.class, Jarraitua.getUsername());//no se si hay que mirar si es null
				System.out.println(JarraitzaileaLocal.getUsername()+">> jarraitu "+JarrairuaLocal.getUsername());
				JarraitzaileaLocal.seguir(JarrairuaLocal/*persona a seguir*/, porcentage);
				db.persist(JarrairuaLocal);
				db.persist(JarraitzaileaLocal);
				db.getTransaction().commit();
				return JarraitzaileaLocal;
			}catch (MutualFollowingException e){
				db.getTransaction().rollback();
				throw e;
			}catch (JarraitzenZenuenException e) {
				db.getTransaction().rollback();
				throw e;
			}
		}
		public boolean removeForecast(Pronostico fr){
			
			System.out.println(">> DataAccessTest: removeEvent");
			Pronostico f = db.find(Pronostico.class, fr.getId());
			if (f!=null) {
				db.getTransaction().begin();
				db.remove(f);
				db.getTransaction().commit();
				return true;
			} else 
			return false;
		}
		
public boolean removeQuestion(Question qu){
			
			System.out.println(">> DataAccessTest: removeQuestion");
			Question q = db.find(Question.class, qu.getQuestionNumber());
			if (q!=null) {
				db.getTransaction().begin();
				db.remove(q);
				db.getTransaction().commit();
				return true;
			} else 
			return false;
		}


public void addQuestion(Question qu) {
	System.out.println(">> DataAccessTest: addQuestion");
	db.getTransaction().begin();
	try {
		db.persist(qu);
		db.getTransaction().commit();
	}
	catch (Exception e){
		e.printStackTrace();
	}
}

public Apostua addBet(double DiruKantitaea,RegisteredUser usuario, Vector<Pronostico>  pronostico) throws IncorrectBetException{
	db.getTransaction().begin();
	Vector<Pronostico> pronosticolocal=new Vector<Pronostico>();
	Pronostico tmp;
	for (Pronostico i:pronostico) {
		tmp=db.find(Pronostico.class, i);
		pronosticolocal.add(tmp);
	}
	RegisteredUser user = db.find(RegisteredUser.class, usuario.getUsername());
	Apostua apostua=new Apostua(DiruKantitaea, user, pronosticolocal);
	user.setBalance(usuario.getBalance()-DiruKantitaea);
	db.persist(user);
	db.persist(apostua);
	for (Pronostico i:pronosticolocal) {
		db.persist(i);
	}
	db.getTransaction().commit();
	return apostua;
}

public Apostua addBetJarraitzaile (double DiruKantitaea,RegisteredUser usuario, 
		Vector<Pronostico> pronostico,RegisteredUser mandon) throws IncorrectBetException{
	db.getTransaction().begin();
	Vector<Pronostico> pronosticolocal=new Vector<Pronostico>();
	Pronostico tmp;
	for (Pronostico i:pronostico) {
		tmp=db.find(Pronostico.class, i);
		if (tmp.getGaldera().getBetMinimum()>DiruKantitaea) {
			throw new IncorrectBetException();
		}
		pronosticolocal.add(tmp);
	}

	RegisteredUser user = db.find(RegisteredUser.class, usuario.getUsername());
	Apostua apostua=new Apostua(DiruKantitaea, user, pronosticolocal,mandon);
	user.setBalance(usuario.getBalance()-DiruKantitaea);
	db.persist(user);
	db.persist(apostua);
	for (Pronostico i:pronosticolocal) {
		db.persist(i);
	}
	db.getTransaction().commit();
	return apostua;
}

}

