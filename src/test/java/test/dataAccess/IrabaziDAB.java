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
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import test.businessLogic.TestFacadeImplementation;

public class IrabaziDAB {

	 //sut:system under test
	 static DataAccess sut=new DataAccess(true);
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();
	
	@Test
	//sut.irabazi:  The winning forecast is not in the data base. 
	public void test1() {
			
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
			Pronostico iraPro = new Pronostico(forename,percentage,question);
			
			//invoke System Under Test (sut) 
			try {
				sut.irabazi(iraPro);
				fail();
			}
			catch(Exception e) {
				assertTrue(true);
			}
			
		   }
	@Test
	//sut.createQuestion:  The forecast is null. 
		public void test2() {
		
		//define parameters		
		Pronostico iraPro = null;
		
		//set up test data base
		
		
		//invoke System Under Test (sut) 
		try {
			sut.irabazi(iraPro);
			fail();
		}
		catch(Exception e) {
			assertTrue(true);
		}
		}
	@Test
	//sut.createQuestion:  The user is not in the data base. 
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
		Pronostico iraPro = new Pronostico(forename,percentage,question);
		
		//invoke System Under Test (sut) 
		try {
			sut.irabazi(iraPro);
			fail();
		}
		catch(Exception e) {
			assertTrue(true);
		}
		
	}
}