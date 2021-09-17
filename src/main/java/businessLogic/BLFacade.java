package businessLogic;

import java.util.Vector;
import java.util.Date;
import java.util.List;

//import domain.Booking;
import domain.Question;
import domain.RegisteredUser;
import domain.User;
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

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  

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
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	
	
	@WebMethod public User login(String username, String password) throws UserDoesNotExist, MismachingUseraAndPasword;
	
	
	@WebMethod public void register(String Izena, String Id, String Email, String Pasahitza,int urtea, int hilabetea, int eguna, long BankuZenbakia,int tipo) throws UserAlreadyExist;

	@WebMethod public void createEvent (String description,Date eventDate) throws EvenAlreadyExists;
	
	@WebMethod public RegisteredUser diruaGeitu (RegisteredUser usuarioa, double dirua);
	
	@WebMethod public Question createForecast(Question galdera,String izena,double multiplikadorea) throws ForecastAlreadyExist;
	
	@WebMethod public List<Pronostico> PronostikoakLortu(int ID);
	
	@WebMethod public RegisteredUser ApostuaEgin(double DiruKantitaea,RegisteredUser usuario,Pronostico  pronostico) throws IncorrectBetException;
	
	@WebMethod public RegisteredUser ApostuaEgin(double DiruKantitaea,RegisteredUser usuario,Vector<Pronostico> pronostico)  throws IncorrectBetException;
	
	@WebMethod public void irabazi(Pronostico irabPronostico);
	
	@WebMethod public Question GalderaLortu (Pronostico Pronostico);
	
	@WebMethod public Event EbentuaLortu (Question irabPronostico);

	@WebMethod public RegisteredUser jarraitu (RegisteredUser Jarraitzailea,String Jarraitua,double porcentage) throws MutualFollowingException,JarraitzenZenuenException;
	
	@WebMethod public RegisteredUser unfollow (RegisteredUser Jarraitzailea,RegisteredUser Jarraitua);
	
	@WebMethod public RegisteredUser apgarseguir(RegisteredUser usuario);
	
	@WebMethod public RegisteredUser encenderseguir (RegisteredUser usuario,int comision) ;
	
	@WebMethod public RegisteredUser conseguirusuario (String usuario) ;
	
}
