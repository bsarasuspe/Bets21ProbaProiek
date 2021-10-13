package test.dataAccess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Apostua;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import domain.RegisteredUser;
import domain.User;
import exceptions.IncorrectBetException;
import exceptions.QuestionAlreadyExist;
import exceptions.UserAlreadyExist;

public class ApostuaEginDAW {

	 //sut:system under test
	 static DataAccess sut=new DataAccess(true);
	 
	 //additional operations needed to execute the test 
	 static TestDataAccessTest testDA=new TestDataAccessTest();

	private Apostua ap;
	private RegisteredUser user, user2, user3, u;
	private Event ev;
	private Question q;
	private Pronostico p;
	
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
			
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			user = testDA.addUser("Beñat", "bsarasua", "bsarasua@ehu.eus", "pass", 2001, 10, 21, 123, 2);
			ev = testDA.addEventWithQuestion(eventText, oneDate, queryText, betMinimum);
			q = ev.getQuestions().get(0);
			p = testDA.addPronostico("Atletic", 2, q);
			Vector<Pronostico> pro = new Vector<Pronostico>();
			pro.add(p);
			testDA.close();			
			
			//invoke System Under Test (sut) 
			sut.open(true);
			u = sut.ApostuaEgin(10.0, user, pro);
			sut.close();
			
			//verify the results
			assertTrue(true);
			
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail 
			fail();
			} finally {
				  //Remove the created objects in the database (cascade removing)  
				testDA.open();
					boolean c=testDA.removeEvent(ev);
					boolean b=testDA.removeUser(user);
		          testDA.close();
		      //     System.out.println("Finally "+b);          
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
			
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			user = testDA.addUser("Beñat", "bsarasua", "bsarasua@ehu.eus", "pass", 2001, 10, 21, 123, 2);
			ev = testDA.addEventWithQuestion(eventText, oneDate, queryText, betMinimum);
			q = ev.getQuestions().get(0);
			p = testDA.addPronostico("Atletic", 2, q);
			Vector<Pronostico> pro = new Vector<Pronostico>();
			pro.add(p);
			testDA.close();		
			
			System.out.println(user.getUsername()+" "+ev.getEventNumber()+" "+q.getQuestion()+" "+p.getErantzuna());
			//invoke System Under Test (sut)  
			sut.open(true);
			u = sut.ApostuaEgin(3.0, user, pro);
			sut.close();
			fail();
			
			//verify the results
			
			
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail 
			   assertTrue(true);
			} finally {
				  //Remove the created objects in the database (cascade removing)  
				System.out.println("froga"+user.getUsername()+" "+ev.getEventNumber());
				testDA.open();
				boolean c=testDA.removeEvent(ev);
				boolean b=testDA.removeUser(user);
		          testDA.close();
		      //     System.out.println("Finally "+b);          
		        }
		   }
	
	@Test
	//sut.createQuestion:  The event has NOT one question with a queryText. 
	public void test3() throws UserAlreadyExist, IncorrectBetException {
		try {
			
			//define paramaters
			String eventText="event1";
			String queryText="query1";
			Float betMinimum=new Float(2);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			user = testDA.addUser("Beñat", "bsarasua", "bsarasua@ehu.eus", "pass", 2001, 10, 21, 123, 2);
			user2 = testDA.addUser("Eki", "ekimendibil", "eki@ehu.eus", "pass", 2001, 10, 21, 123, 2);
			user.encenderseguir(2);
			user2.seguir(user, 0.4);
			ev = testDA.addEventWithQuestion(eventText, oneDate, queryText, betMinimum);
			q = ev.getQuestions().get(0);
			Vector<Pronostico> pro = new Vector<Pronostico>();
			pro.add(p);
			testDA.close();			
			
			//invoke System Under Test (sut)  
			sut.open(true);
			u = sut.ApostuaEgin(5.3, user, pro);
			sut.close();
			fail();
			
			//verify the results
			
			
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail 
			   assertTrue(true);
			} finally {
				  //Remove the created objects in the database (cascade removing)  
				testDA.open();
				boolean c=testDA.removeEvent(ev);
				boolean b=testDA.removeUser(user);
		          testDA.close();
		      //     System.out.println("Finally "+b);          
		        }
		   }
	
}
