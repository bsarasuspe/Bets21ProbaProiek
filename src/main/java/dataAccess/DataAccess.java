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

import javax.jws.WebMethod;
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
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

     public DataAccess(boolean initializeMode)  {
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);
		
	}

	public DataAccess()  {	
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
				q1=ev1.addQuestion("¿Quién ganará el partido?",1);
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion("¿Quién ganará el partido?",1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion("¿Quién ganará el partido?",1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
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
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		
			Event ev = db.find(Event.class, event.getEventNumber());
			
			if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
			
			db.getTransaction().begin();
			Question q = ev.addQuestion(question, betMinimum);
			//db.persist(q);
			db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			return q;
		
	}
	public void createEvent(String description,Date eventDate) throws EvenAlreadyExists {
		db.getTransaction().begin();
		try{
		db.persist(new Event(description,eventDate));
		db.getTransaction().commit();
		}catch (javax.persistence.RollbackException e) {
			throw new EvenAlreadyExists();
		}
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	return res;
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
	
	public void register(String Izena, String Id, String Email, String Pasahitza, int urtea, int hilabetea, int eguna, long BankuZenbakia,int tipo) throws UserAlreadyExist{//0=Admin,1=worker,2=register user
		db.getTransaction().begin();
		try {
			if (tipo==2) {
				db.persist(new RegisteredUser(Izena, Pasahitza, Email, Id, BankuZenbakia, UtilDate.newDate(urtea, hilabetea, eguna)));
			}
			if (tipo==1) {
				db.persist(new Worker(Izena, Pasahitza, Email, Id));
			}
			if (tipo==0) {
				db.persist(new Admin(Izena, Pasahitza, Email, Id));
			}
			db.getTransaction().commit();
			System.out.println("Saved");
		}
		catch(javax.persistence.RollbackException e){
			throw new UserAlreadyExist();
		}
	}
	
	public User login(String username, String password) throws UserDoesNotExist,MismachingUseraAndPasword{
		User user = db.find(User.class, username);
		if (user==null) {
			throw new UserDoesNotExist();
		}
		if(!(user.getPassword().equals(password))) {
			throw new MismachingUseraAndPasword();
		}
			return user;		
	}
	
	public RegisteredUser DiruaEzarri(RegisteredUser user, double dirua) {
		db.getTransaction().begin();
		RegisteredUser userlocal = db.find(RegisteredUser.class, user.getUsername());
		userlocal.setBalance(dirua);
		db.persist(userlocal);
		db.getTransaction().commit();
		return userlocal;
	}
	
	public Question createForecast(Question galdera,String izena,double multiplikadorea) throws ForecastAlreadyExist{
		
		db.getTransaction().begin();
		Question local=db.createQuery("SELECT q FROM Question q where q.questionNumber="+galdera.getQuestionNumber()+"", Question.class).getResultList().get(0);
		Pronostico tmp=local.addPronostico(izena, multiplikadorea);
		db.persist(tmp);
		db.persist(local);
		db.getTransaction().commit();
		return local;
	}
	
	public List<Pronostico> PronostikoakLortu(int ID){
		db.getTransaction().begin();
		List<Pronostico> buelta=db.createQuery("SELECT p FROM Pronostico p where p.galdera.questionNumber="+ID+"", Pronostico.class).getResultList();
		db.getTransaction().commit();
		return buelta;
	}

	public RegisteredUser ApostuaEgin(double DiruKantitaea,RegisteredUser usuario, Vector<Pronostico>  pronostico) throws IncorrectBetException{
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
		Apostua apusta=new Apostua(DiruKantitaea, user, pronosticolocal);
		user.setBalance(usuario.getBalance()-DiruKantitaea);
		db.persist(user);
		db.persist(apusta);
		for (Pronostico i:pronosticolocal) {
			db.persist(i);
		}
		db.getTransaction().commit();
		for (RegisteredUser i:user.getJarraitzendidate()) {
			try {
				ApostuaEginDos(DiruKantitaea*i.getPortzentaia(user), i, pronosticolocal,user);
			}catch (IncorrectBetException e) {
				// TODO: mandar correo electronico
			}
		}
		return user;
	}
	
	private RegisteredUser ApostuaEginDos (double DiruKantitaea,RegisteredUser usuario, Vector<Pronostico>  pronostico,RegisteredUser mandon) throws IncorrectBetException{
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
		Apostua apusta=new Apostua(DiruKantitaea, user, pronosticolocal,mandon);
		user.setBalance(usuario.getBalance()-DiruKantitaea);
		db.persist(user);
		db.persist(apusta);
		for (Pronostico i:pronosticolocal) {
			db.persist(i);
		}
		db.getTransaction().commit();
		return user;
	}
	
	public void irabazi(Pronostico irabPronostico) {
		db.getTransaction().begin();
		Pronostico pronosticolocala= db.find(Pronostico.class, irabPronostico);
		RegisteredUser usuario;
		pronosticolocala.setEstado(true);//marco como resuelto el pronostico
		db.persist(pronosticolocala);
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
		}//pago las apuestas que coresponde pagar
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
	

	public Question GalderaLortu(Pronostico Pronostico) {
		db.getTransaction().begin();
		Question tmp=db.find(Pronostico.class, Pronostico.getId()).getGaldera();
		db.getTransaction().commit();
		return tmp;
	}


	public Event EbentuaLortu(Question galdera) {
		db.getTransaction().begin();
		Event tmp=db.find(Question.class, galdera.getQuestionNumber()).getEvent();
		db.getTransaction().commit();
		return tmp;
	}
	
	public RegisteredUser jarraitu (RegisteredUser Jarraitzailea,String Jarraitua,double porcentage) throws MutualFollowingException,JarraitzenZenuenException {
		db.getTransaction().begin();
		RegisteredUser JarraitzaileaLocal=db.find(RegisteredUser.class, Jarraitzailea);
		try {
			RegisteredUser JarrairuaLocal=db.find(RegisteredUser.class, new RegisteredUser(Jarraitua, "", "", "699999", 0, null));//no se si hay que mirar si es null
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
	
	public RegisteredUser unfollow (RegisteredUser Jarraitzailea,RegisteredUser Jarraitua){
		db.getTransaction().begin();
		RegisteredUser JarraitzaileaLocal=db.find(RegisteredUser.class, Jarraitzailea);
			//RegisteredUser JarrairuaLocal=db.find(RegisteredUser.class, new RegisteredUser(Jarraitua, "", "", "699999", 0, null));//no se si esto chuta
			RegisteredUser JarrairuaLocal=db.find(RegisteredUser.class, Jarraitua);
			JarraitzaileaLocal.unfollow(JarrairuaLocal);
			db.persist(JarrairuaLocal);
			db.persist(JarraitzaileaLocal);
			db.getTransaction().commit();
			return JarraitzaileaLocal;
	}
	
	public RegisteredUser apgarseguir (RegisteredUser pertzona) {
		db.getTransaction().begin();
		RegisteredUser Local=db.find(RegisteredUser.class, pertzona);
		for(RegisteredUser i:Local.apgarseguir()) {
			db.persist(i);
		}
		db.persist(Local);
		db.getTransaction().commit();
		return Local;
	}
	
	public RegisteredUser encenderseguir(RegisteredUser usuario, int comision) {
		db.getTransaction().begin();
		RegisteredUser Local=db.find(RegisteredUser.class, usuario);
		Local.encenderseguir(comision);
		db.persist(Local);
		db.getTransaction().commit();
		return Local;
	}
	
	public RegisteredUser conseguirusuario(String usuario) {
		return db.find(RegisteredUser.class, new RegisteredUser(usuario, "", "", "699999", 0, null));
	}
}
