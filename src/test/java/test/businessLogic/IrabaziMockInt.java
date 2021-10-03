package test.businessLogic;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import domain.RegisteredUser;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import exceptions.UserAlreadyExist;
import test.businessLogic.TestFacadeImplementation;

public class IrabaziMockInt {

	 DataAccess dataAccess=Mockito.mock(DataAccess.class);
     Question mockedQuestion=Mockito.mock(Question.class);
     Pronostico mockedForecast=Mockito.mock(Pronostico.class);
     
	
	@InjectMocks
	 BLFacade sut=new BLFacadeImplementation(dataAccess);
	
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
			
			//configure mock
			Mockito.doThrow(new RuntimeException()).when(dataAccess).irabazi(Mockito.any(Pronostico.class));

			
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
		
		//configure mock
		Mockito.doThrow(new RuntimeException()).when(dataAccess).irabazi(Mockito.any(Pronostico.class));

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
		Pronostico iraPro = new Pronostico(forename,percentage,question);
		//configure mock
		Mockito.doThrow(new RuntimeException()).when(dataAccess).irabazi(Mockito.any(Pronostico.class));
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
		Pronostico iraPro = new Pronostico(forename,percentage,question);
		//configure mock
		

		//invoke System Under Test (sut) 
		try {
			sut.irabazi(iraPro);
			assertTrue(true);
			}
		catch(Exception e) {
			e.printStackTrace();
			fail();
			}

		
	}
}
