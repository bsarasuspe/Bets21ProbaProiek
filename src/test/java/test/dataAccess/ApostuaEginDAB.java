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
import exceptions.QuestionAlreadyExist;

public class ApostuaEginDAB {

	 //sut:system under test
	 static DataAccess sut=new DataAccess(true);
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();

	private Apostua ap;
	private RegisteredUser u, user;
	
	@Test
	//sut.createQuestion:  The event has NOT one question with a queryText. 
	public void test1() {
		try {
			
			//define paramaters
			Event ev = new Event(72, "Futbol partidua", new Date());
			Question q = new Question(210, "Nork irabaziko du?", 10, ev);
			
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			testDA.addUser("Beñat", "bsarasua", "bsarasua@gmail.com", "pass", 2001, 6, 13, 34534, 2);
			testDA.addPronostico("Atletic", 2.1,q);
			testDA.close();			
			
			//invoke System Under Test (sut) 
			user = sut.ApostuaEgin(10,u,p);
			
			//verify the results
			assertTrue(user!=null);
			
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			fail();
			} finally {//
				  //Remove the created objects in the database (cascade removing)   
				testDA.open();
		          boolean b=testDA.removeUser(user);
		          testDA.close();
		      //     System.out.println("Finally "+b);          
		        }
		   }
	
}
