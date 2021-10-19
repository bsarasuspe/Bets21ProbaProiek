package testAriketaIrabazi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
import exceptions.EvenAlreadyExists;
import exceptions.ForecastAlreadyExist;
import exceptions.IncorrectBetException;
import exceptions.JarraitzenZenuenException;
import exceptions.MismachingUseraAndPasword;
import exceptions.MutualFollowingException;
import exceptions.QuestionAlreadyExist;
import exceptions.UserAlreadyExist;
import exceptions.UserDoesNotExist;

public class DataAccessIrabazi {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

     public DataAccessIrabazi(boolean initializeMode)  {
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);
		
	}

	public DataAccessIrabazi()  {	
		 this(false);
	}
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();
		try {

			
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
	    
			Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
			

			Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month+1,28));
			Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month+1,28));
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month+1,28));
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month+1,28));
			
			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
					
			if (Locale.getDefault().equals(new Locale("es"))) {
				String pregunta = "¿Quién ganará el partido?";
				q1=ev1.addQuestion(pregunta,1);
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion(pregunta,1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion(pregunta,1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				String question = "Who will win the match";
				q1=ev1.addQuestion(question,1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion(question,1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion(question,1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				String galdera = "Zeinek irabaziko du partidua?";
				q1=ev1.addQuestion(galdera,1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion(galdera,1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion(galdera,1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
				
			}
			
			Pronostico p1=new Pronostico("uno", 2, q1),p2=new Pronostico("dos", 2, q1),p3=new Pronostico("tres", 2, q1);
			Pronostico p4=new Pronostico("cuatro", 2, q2),p5=new Pronostico("cinco", 2, q2),p6=new Pronostico("seis", 2, q2);
			q1.addPronostiko(p1);
			q1.addPronostiko(p2);
			q1.addPronostiko(p3);
			q2.addPronostiko(p4);
			q2.addPronostiko(p5);
			q2.addPronostiko(p6);
			
			db.persist(p1);
			db.persist(p2);
			db.persist(p3);
			db.persist(p4);
			db.persist(p5);
			db.persist(p6);
			
			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6); 
	
	        
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);			
			//////////////////////////////////////////////////////////////////////////////////////////////////////////
			db.persist(new Admin("a", "a", "a","a"));
			RegisteredUser tmp=new RegisteredUser("b", "b", "", "", 0,  UtilDate.newDate(2001,1,1));
			tmp.encenderseguir(20);
			db.persist(tmp);
			db.persist(new RegisteredUser("c", "c", "", "", 0,  UtilDate.newDate(2001,1,1)));
			tmp=new RegisteredUser("d", "d", "", "", 0,  UtilDate.newDate(2001,1,1));
			tmp.encenderseguir(30);
			db.persist(tmp);
			///////////////////////////////////////////////////////////////////////////////////////////////////////////
			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}


public void open(boolean initializeMode){
		
		System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode) {
			fileName=fileName+";drop";
			System.out.println("Deleting the DataBase");
		}
		
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
public boolean existQuestion(Event event, String question) {
	System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
	Event ev = db.find(Event.class, event.getEventNumber());
	return ev.DoesQuestionExists(question);
	
}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	

	
	public void irabazi(Pronostico irabPronostico) {
		db.getTransaction().begin();
		Pronostico pronosticolocala= db.find(Pronostico.class, irabPronostico);
		RegisteredUser usuario;
		pronosticolocala.setEstado(true);//marco como resuelto el pronostico
		db.persist(pronosticolocala);
		ordaindu(pronosticolocala);//pago las apuestas que coresponde pagar
		for (Pronostico i: pronosticolocala.getGaldera().getPronostikoak()) {
			if (!i.getEstado()) {
				i.perder();
				db.persist(i);
				for (Apostua j:i.getApostuak()) {
					db.persist(j);
				}
			}
		}//el resto de pronosticos de la galdera los marco como perdidos
		db.getTransaction().commit();
	}

	/**
	 * @param pronosticolocala
	 */
	private void ordaindu(Pronostico pronosticolocala) {
		RegisteredUser usuario;
		for (Apostua i:pronosticolocala.getApostuak()) {
			if (i.pagar()){
				usuario=i.getUsuarioa();
				double cuota=1;
				for (Pronostico j:i.getPronostikoa()) {
					cuota*=j.getCuota();
				}
				usuario.setBalance(usuario.getBalance()+i.getKantitatea()*cuota);//Pago la cuota corespondiente
				if (i.getCopiado()!=null) {
					usuario.setBalance(usuario.getBalance()-i.getKantitatea()*cuota*i.getComision());
					i.getCopiado().setBalance(i.getCopiado().getBalance()+i.getKantitatea()*cuota*i.getComision());
					db.persist(i.getCopiado());
				}
				db.persist(usuario);
			}
		}
	}
	


}
