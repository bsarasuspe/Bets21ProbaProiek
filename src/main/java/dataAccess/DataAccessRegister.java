package dataAccess;

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
import domain.User;
import domain.Worker;
import domain.RegisteredUser;
import exceptions.EvenAlreadyExists;
import exceptions.ForecastAlreadyExist;
import exceptions.IncorrectBetException;
import exceptions.MismachingUseraAndPasword;
import exceptions.MutualFollowingException;
import exceptions.QuestionAlreadyExist;
import exceptions.UserAlreadyExist;
import exceptions.UserDoesNotExist;
import exceptions.JarraitzenZenuenException;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccessRegister  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

     public DataAccessRegister(boolean initializeMode)  {
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);
		
	}

	public DataAccessRegister()  {	
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
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */

	


	

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

	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	
	public void register(String[] myStringList, int[] myIntList, long BankuZenbakia,int tipo) throws UserAlreadyExist{//0=Admin,1=worker,2=register user
		db.getTransaction().begin();
		try {
			if (tipo==2) {
				db.persist(new RegisteredUser(myStringList[0], myStringList[1], myStringList[2], myStringList[3], BankuZenbakia, UtilDate.newDate(myIntList[0], myIntList[1], myIntList[2])));
			}
			if (tipo==1) {
				db.persist(new Worker(myStringList[0], myStringList[1], myStringList[2], myStringList[3]));
			}
			if (tipo==0) {
				db.persist(new Admin(myStringList[0], myStringList[1], myStringList[2], myStringList[3]));
			}
			db.getTransaction().commit();
			System.out.println("Saved");
		}
		catch(javax.persistence.RollbackException e){
			throw new UserAlreadyExist();
		}
	}

}
