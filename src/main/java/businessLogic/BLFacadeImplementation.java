package businessLogic;
//hola
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Question;
import domain.RegisteredUser;
import domain.User;
import domain.Apostua;
import domain.Event;
import domain.Pronostico;
import exceptions.EvenAlreadyExists;
import exceptions.EventFinished;
import exceptions.ForecastAlreadyExist;
import exceptions.IncorrectBetException;
import exceptions.JarraitzenZenuenException;
import exceptions.MismachingUseraAndPasword;
import exceptions.MutualFollowingException;
import exceptions.QuestionAlreadyExist;
import exceptions.UserAlreadyExist;
import exceptions.UserDoesNotExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
		    dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
			}
		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();

		}
		dbManager=da;		
	}
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
		dbManager.open(false);
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dbManager.createQuestion(event,question,betMinimum);		

		dbManager.close();
		
		return qry;
   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public Vector<Event> getEvents(Date date)  {
		dbManager.open(false);
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}
	
	
	public void close() {
		DataAccess dB4oManager=new DataAccess(false);

		dB4oManager.close();

	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}
    
    @WebMethod
    public void register(String Izena, String Id, String Email, String Pasahitza,int urtea, int hilabetea, int eguna, long BankuZenbakia,int tipo) throws UserAlreadyExist {
    	dbManager.open(false);
		dbManager.register(Izena, Id, Email, Pasahitza,  urtea,hilabetea,eguna,  BankuZenbakia, tipo);
    	dbManager.close();
    }
    
    
    @WebMethod
    public User login(String username, String password) throws UserDoesNotExist,MismachingUseraAndPasword {
    	dbManager.open(false);
		User user = (dbManager.login(username, password));
    	dbManager.close();
    	return user;
    }
    
    @WebMethod
    public void createEvent (String description,Date eventDate) throws EvenAlreadyExists {
    	dbManager.open(false);
		dbManager.createEvent(description, eventDate);
    	dbManager.close();

    }
    
    @WebMethod
    public RegisteredUser diruaGeitu (RegisteredUser usuarioa, double dirua) {
    	dbManager.open(false);
    	RegisteredUser tmp = dbManager.DiruaEzarri(usuarioa, usuarioa.getBalance()+dirua);
    	dbManager.close();
    	return tmp;
    }
    
    @WebMethod
    public Question createForecast(Question galdera,String izena,double multiplikadorea) throws ForecastAlreadyExist {
    	dbManager.open(false);
    	Question tmp=dbManager.createForecast(galdera,izena,multiplikadorea);
    	dbManager.close();
    	return tmp;
    }

    	
    @WebMethod
    public void ordaindu(Pronostico pronostikoa) {
    	dbManager.open(false);
    	for (Apostua i:  pronostikoa.getApostuak()) {
    		dbManager.DiruaEzarri(i.getUsuarioa(), i.getUsuarioa().getBalance()+i.getKantitatea()*pronostikoa.getCuota());
    	}
    	dbManager.close();
    }
    
    @WebMethod
    public List<Pronostico> PronostikoakLortu(int ID) {
    	dbManager.open(false);
    	List<Pronostico> tmp=dbManager.PronostikoakLortu(ID);
    	dbManager.close();
    	return tmp;
    }
    
    @WebMethod
    public RegisteredUser ApostuaEgin(double DiruKantitaea,RegisteredUser usuario,Pronostico  pronostico)  throws IncorrectBetException {
    	dbManager.open(false);
    	Vector<Pronostico> aljebra=new Vector<Pronostico>();
    	aljebra.add(pronostico);
    	RegisteredUser tmp= dbManager.ApostuaEgin(DiruKantitaea, usuario, aljebra);
    	dbManager.close();
    	return tmp;
    }
    
    @WebMethod
    public RegisteredUser ApostuaEgin(double DiruKantitaea,RegisteredUser usuario,Vector<Pronostico> pronostico)  throws IncorrectBetException {
    	dbManager.open(false);
    	RegisteredUser tmp= dbManager.ApostuaEgin(DiruKantitaea, usuario, pronostico);
    	dbManager.close();
    	return tmp;
    }
    
	@WebMethod
	public void irabazi(Pronostico irabPronostico) {
		dbManager.open(false);
    	dbManager.irabazi(irabPronostico);
    	dbManager.close();
	}

	@WebMethod
	public Question GalderaLortu(Pronostico Pronostico) {
		dbManager.open(false);
		Question tmp= dbManager.GalderaLortu(Pronostico);
    	dbManager.close();
    	return tmp;
	}

	@WebMethod
	public Event EbentuaLortu(Question galdera) {
		dbManager.open(false);
		Event tmp= dbManager.EbentuaLortu(galdera);
    	dbManager.close();
    	return tmp;
	}

	@Override
	public RegisteredUser jarraitu(RegisteredUser Jarraitzailea, String Jarraitua, double porcentage)throws MutualFollowingException, JarraitzenZenuenException {
		dbManager.open(false);
		RegisteredUser tmp= dbManager.jarraitu(Jarraitzailea, Jarraitua, porcentage);
    	dbManager.close();
    	return tmp;
	}

	@Override
	public RegisteredUser unfollow(RegisteredUser Jarraitzailea, RegisteredUser Jarraitua) {
		dbManager.open(false);
		RegisteredUser tmp= dbManager.unfollow(Jarraitzailea, Jarraitua);
    	dbManager.close();
    	return tmp;
	}

	@Override
	public RegisteredUser apgarseguir(RegisteredUser usuario) {
		dbManager.open(false);
		RegisteredUser tmp= dbManager.apgarseguir(usuario);
    	dbManager.close();
    	return tmp;
	}

	@Override
	public RegisteredUser encenderseguir(RegisteredUser usuario, int comision) {
		dbManager.open(false);
		RegisteredUser tmp= dbManager.encenderseguir(usuario, comision);
    	dbManager.close();
    	return tmp;
	}

	@Override
	public RegisteredUser conseguirusuario(String usuario) {
		dbManager.open(false);
		RegisteredUser tmp= dbManager.conseguirusuario(usuario);
    	dbManager.close();
    	return tmp;
	}
	
}

