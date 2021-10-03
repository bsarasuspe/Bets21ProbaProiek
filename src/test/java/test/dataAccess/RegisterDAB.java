package test.dataAccess;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import domain.RegisteredUser;
import domain.User;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import exceptions.UserAlreadyExist;
import test.businessLogic.TestFacadeImplementation;

public class RegisterDAB {

	 //sut:system under test
	 static DataAccess sut=new DataAccess(true);
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();
	
	@Test
	//sut.irabazi:  The user is a registeredUser. 
	public void test1() {
			
			//define parameters
			String izena = "Nikolas";
			String id = "12345678L";
			String email = "ni@ni.com";
			String pasahitza = "123";
			int urtea = 1994;
			int hilabetea = 8;
			int eguna = 11;
			long bankuZenbakia = 1234567899;
			int tipo = 2;
			
			//set up test data base
			
			//invoke System Under Test (sut) 
				try {
					sut.open(true);
					sut.register(izena, id, email, pasahitza, urtea, hilabetea, eguna, bankuZenbakia, tipo);;
					sut.close();
					assertTrue(true);
					
				}
				catch(Exception e) {
					fail();
				}

		   }
	@Test
	//sut.createQuestion:  The forecast is null. 
		public void test2() {
		
		//define parameters	
		String forename="forecast1";
		double percentage=0.5;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date=null;;
		try {
			date = sdf.parse("05/10/2022");
		} 
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
					
		Question question = new Question(1, "galderaTextu", 500, new Event(1, "Deskribapena",date));
		Pronostico iraPro = null;
		
		//set up test data base
			testDA.open();
			testDA.addQuestion(question);
			testDA.close();
		//invoke System Under Test (sut) 
			try {
				sut.open(true);
				sut.irabazi(iraPro);
				sut.close();
				fail();
			}
			catch(Exception e) {
				assertTrue(true);
			}
			finally {
				testDA.open();
				testDA.removeQuestion(question);
				testDA.close();
			}
		
	   }
	
	@Test
	//sut.createQuestion:  The question is not in the data base. 
	public void test3() {
		
		//define parameters
		String forename="forecast1";
		double percentage=0.5;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date=null;
		try {
			date = sdf.parse("05/10/2022");
		} 
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		Question question = new Question(1, "galderaTextu", 500, new Event(1, "Deskribapena",date));
		//set up test data base
		testDA.open();
		Pronostico iraPro = testDA.addPronostico(forename, percentage, question);
		testDA.close();
		//invoke System Under Test (sut) 
		try {
			sut.open(true);
			sut.irabazi(iraPro);
			sut.close();
			fail();
		}
		catch(Exception e) {
			assertTrue(true);
		}
		finally {
			testDA.open();
			testDA.removeForecast(iraPro);
			testDA.close();
		}
		
	}
	
	@Test
	//sut.createQuestion:  Everything is correct. 
	public void test4() {
		
		//define parameters
		String forename="forecast1";
		double percentage=0.5;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date=null;
		try {
			date = sdf.parse("05/10/2022");
		} 
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		Question question = new Question(1, "galderaTextu", 500, new Event(1, "Deskribapena",date));
		//set up test data base
			testDA.open();
			testDA.addQuestion(question);
			Pronostico iraPro = testDA.addPronostico(forename, percentage, question);			
			testDA.close();
			//invoke System Under Test (sut) 
			try {
				sut.open(true);
				sut.irabazi(iraPro);
				sut.close();
				assertTrue(true);
				}
			catch(Exception e) {
				e.printStackTrace();
				fail();
				}
				finally {
					testDA.open();
					testDA.removeForecast(iraPro);
					testDA.removeQuestion(question);
					testDA.close();
				}

		
	}
}