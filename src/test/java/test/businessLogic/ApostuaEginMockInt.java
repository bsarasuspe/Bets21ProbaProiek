package test.businessLogic;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Apostua;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import domain.RegisteredUser;
import exceptions.IncorrectBetException;
import exceptions.UserAlreadyExist;
import test.dataAccess.TestDataAccess;

public class ApostuaEginMockInt {
	
	DataAccess dataAccess=Mockito.mock(DataAccess.class);
	RegisteredUser mockedUser=Mockito.mock(RegisteredUser.class);;
	RegisteredUser mockedUser2=Mockito.mock(RegisteredUser.class);;
	Apostua mockedAp=Mockito.mock(Apostua.class);;
	Event mockedEv=Mockito.mock(Event.class);;
	Question mockedQ=Mockito.mock(Question.class);;
	Pronostico mockedP=Mockito.mock(Pronostico.class);;
	
	@InjectMocks
	BLFacade sut=new BLFacadeImplementation(dataAccess);
	
	@Test 
	public void test1() throws UserAlreadyExist, IncorrectBetException {
		try {
			
			//define paramaters
			String eventText="event1";
			String queryText="query1";
			Float betMinimum=new Float(5);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			RegisteredUser user = new RegisteredUser("Beñat", "bsarasua", "bsarasua@ehu.eus", "pass", 0, new Date());
			Event ev = new Event(200, eventText, oneDate);
			Question q = new Question(200,queryText,10,ev);
			Pronostico p = new Pronostico("Atletic",2.0,q);
			Vector<Pronostico> pro = new Vector<Pronostico>();
			pro.add(p);	
			
			//configure mock
			Mockito.doReturn(user).when(dataAccess).ApostuaEgin(20,Mockito.any(RegisteredUser.class),pro);
			
			//verify the results
			assertTrue(true);
			
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail 
			fail();
			} finally {
      
		        }
		   }
	
	@Test 
	public void test2() throws UserAlreadyExist, IncorrectBetException {
		try {
			
			//define paramaters
			String eventText="event1";
			String queryText="query1";
			Float betMinimum=new Float(5);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			RegisteredUser user = new RegisteredUser("Beñat", "bsarasua", "bsarasua@ehu.eus", "pass", 0, new Date());
			Event ev = new Event(200, eventText, oneDate);
			Question q = new Question(200,queryText,10,ev);
			Pronostico p = new Pronostico("Atletic",2.0,q);
			Vector<Pronostico> pro = new Vector<Pronostico>();
			pro.add(p);	
			
			//configure mock
			Mockito.doThrow(new IncorrectBetException()).when(dataAccess).ApostuaEgin(5,Mockito.any(RegisteredUser.class),pro);
			
			//verify the results
			fail();
			
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail 
			 assertTrue(true);
			} finally {
      
		        }
		   }
	
	@Test
	public void test3() throws UserAlreadyExist, IncorrectBetException {
		try {
			
			//define paramaters
			String eventText="event1";
			String queryText="query1";
			Float betMinimum=new Float(5);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			Event ev = new Event(200, eventText, oneDate);
			Question q = new Question(200,queryText,10,ev);
			Pronostico p = new Pronostico("Atletic",2.0,q);
			Vector<Pronostico> pro = new Vector<Pronostico>();
			pro.add(p);	
			
			//configure mock
			Mockito.doThrow(new NullPointerException()).when(dataAccess).ApostuaEgin(5,Mockito.any(RegisteredUser.class),pro);
			
			//verify the results
			fail();
			
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail 
			 assertTrue(true);
			} finally {
      
		        }
		   }
	
	@Test
	public void test4() throws UserAlreadyExist, IncorrectBetException {
		try {
			
			//define paramaters
			String eventText="event1";
			String queryText="query1";
			Float betMinimum=new Float(5);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			RegisteredUser user = new RegisteredUser("Beñat", "bsarasua", "bsarasua@ehu.eus", "pass", 0, new Date());
			Event ev = new Event(200, eventText, oneDate);
			Question q = new Question(200,queryText,10,ev);
			Pronostico p = new Pronostico("Atletic",2.0,q);
			Vector<Pronostico> pro = new Vector<Pronostico>();
			pro.add(p);	
			
			//configure mock
			Mockito.doThrow(new IncorrectBetException()).when(dataAccess).ApostuaEgin(5,Mockito.any(RegisteredUser.class),null);
			
			//verify the results
			fail();
			
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail 
			 assertTrue(true);
			} finally {
      
		        }
		   }
	
	@Test
	public void test5() throws UserAlreadyExist, IncorrectBetException {
		try {
			
			//define paramaters
			String eventText="event1";
			String queryText="query1";
			Float betMinimum=new Float(5);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			Event ev = new Event(200, eventText, oneDate);
			Question q = new Question(200,queryText,10,ev);
			Pronostico p = new Pronostico("Atletic",2.0,q);
			Vector<Pronostico> pro = new Vector<Pronostico>();
			pro.add(p);	
			
			//configure mock
			Mockito.doThrow(new IncorrectBetException()).when(dataAccess).ApostuaEgin(5,Mockito.any(RegisteredUser.class),pro);
			
			//verify the results
			fail();
			
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail 
			 assertTrue(true);
			} finally {
      
		        }
		   }
	
	@Test
	//sut.createQuestion:  The event has NOT one question with a queryText. 
	public void test6() throws UserAlreadyExist, IncorrectBetException {
		try {
			
			//define paramaters
			String eventText="event1";
			String queryText="query1";
			Float betMinimum=new Float(5);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			RegisteredUser user = new RegisteredUser("Beñat", "bsarasua", "bsarasua@ehu.eus", "pass", 0, new Date());
			Event ev = new Event(200, eventText, oneDate);
			Question q = new Question(200,queryText,10,ev);
			Vector<Pronostico> pro = null;
			
			//configure mock
			Mockito.doThrow(new IncorrectBetException()).when(dataAccess).ApostuaEgin(5,Mockito.any(RegisteredUser.class),pro);
			
			//verify the results
			fail();
			
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail 
			 assertTrue(true);
			} finally {
      
		        }
		   }
	
}
