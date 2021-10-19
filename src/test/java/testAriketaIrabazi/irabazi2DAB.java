package testAriketaIrabazi;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import dataAccess.DataAccess;
import dataAccess.DataAccessIrabazi;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import test.dataAccess.TestDataAccessTest;

public class irabazi2DAB {

	//sut:system under test
		 static DataAccessIrabazi sut=new DataAccessIrabazi(true);
		 
		 //additional operations needed to execute the test 
		 static TestDataAccessTest testDA=new TestDataAccessTest();
		
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
