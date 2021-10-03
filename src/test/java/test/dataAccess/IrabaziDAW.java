package test.dataAccess;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.junit.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Apostua;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import domain.RegisteredUser;
import exceptions.IncorrectBetException;
import exceptions.JarraitzenZenuenException;
import exceptions.MutualFollowingException;
import exceptions.UserAlreadyExist;

public class IrabaziDAW {

	 //sut:system under test
	 static DataAccess sut=new DataAccess(true);
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();
	

	@Test 
	public void test1() {
		
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
		try {
			testDA.open();
			testDA.addQuestion(question);
			RegisteredUser user = testDA.addUser("u1", "49123453", "a@ehu.eus", "e5j632kl", 1990, 5, 4, 12345623, 1);
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
					testDA.removeUser(user);
					testDA.removeForecast(iraPro);
					testDA.removeQuestion(question);
					testDA.close();
				}
			}
			catch(UserAlreadyExist e){
				e.printStackTrace();
			}
		
	}
	
	@Test
	public void test2() {
		
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
		try {
			testDA.open();
			testDA.addQuestion(question);
			RegisteredUser user = testDA.addUser("u1", "49123453", "a@ehu.eus", "e5j632kl", 1990, 5, 4, 12345623, 1);
			Vector<Pronostico> proVec=new Vector<Pronostico>();
			Pronostico iraPro = testDA.addPronostico(forename, percentage, question);
			proVec.add(iraPro);
			Apostua bet = new Apostua();
			try {
				bet = testDA.addBet(250.50, user, proVec);
			} catch (IncorrectBetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				fail();
			}
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
					testDA.removeUser(user);
					testDA.removeForecast(iraPro);
					testDA.removeQuestion(question);
					testDA.removeApostua(bet.getId());
					testDA.close();
				}
			}
			catch(UserAlreadyExist e){
				e.printStackTrace();
			}
		
	}
	
	@Test
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
		try {
			testDA.open();
			testDA.addQuestion(question);
			RegisteredUser user = testDA.addUser("u1", "49123453", "a@ehu.eus", "e5j632kl", 1990, 5, 4, 12345623, 1);
			Vector<Pronostico> proVec=new Vector<Pronostico>();
			Pronostico iraPro = testDA.addPronostico(forename, percentage, question);
			proVec.add(iraPro);
			Apostua bet = new Apostua();
			try {
				bet = testDA.addBet(250.50, user, proVec);
				bet.SetBalida(true);
			} catch (IncorrectBetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				fail();
			}
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
					testDA.removeUser(user);
					testDA.removeForecast(iraPro);
					testDA.removeQuestion(question);
					testDA.removeApostua(bet.getId());
					testDA.close();
				}
			}
			catch(UserAlreadyExist e){
				e.printStackTrace();
			}
		
	}
	
	@Test
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
		Question question = new Question(1, "galderaTextu", 12, new Event(1, "Deskribapena",date));
		//set up test data base
		try {
			testDA.open();
			testDA.addQuestion(question);
			RegisteredUser user = testDA.addUser("u1", "49123453", "a@ehu.eus", "e5j632kl", 1990, 5, 4, 12345623, 1);
			RegisteredUser userFollow = testDA.addUser("u2", "49123473", "b@ehu.eus", "e7j632kl", 1990, 5, 4, 12745623, 1);
			try {
				user.encenderseguir(2);
				userFollow.seguir(user, 0.4);
			} catch (MutualFollowingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				fail();
			} catch (JarraitzenZenuenException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				fail();
			}
			Vector<Pronostico> proVec=new Vector<Pronostico>();
			Pronostico iraPro = testDA.addPronostico(forename, percentage, question);
			proVec.add(iraPro);
			Apostua bet = new Apostua();
			Apostua betFollow = new Apostua();
			try {
				bet.SetBalida(true);
				bet = testDA.addBet(250.50, user, proVec);
				betFollow = testDA.addBetJarraitzaile(100, userFollow, proVec, user);				
			} catch (IncorrectBetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				fail();
			}
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
					testDA.removeUser(user);
					testDA.removeUser(userFollow);
					testDA.removeForecast(iraPro);
					testDA.removeQuestion(question);
					testDA.removeApostua(bet.getId());
					testDA.removeApostua(betFollow.getId());
					testDA.close();
				}
			}
			catch(UserAlreadyExist e){
				e.printStackTrace();
			}
		
	}
	
	@Test
	public void test5() {
		
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
		Question question = new Question(1, "galderaTextu", 12, new Event(1, "Deskribapena",date));
		//set up test data base
		try {
			testDA.open();
			testDA.addQuestion(question);
			RegisteredUser user = testDA.addUser("u1", "49123453", "a@ehu.eus", "e5j632kl", 1990, 5, 4, 12345623, 1);
			RegisteredUser userFollow = testDA.addUser("u2", "49123473", "b@ehu.eus", "e7j632kl", 1990, 5, 4, 12745623, 1);
			try {
				user.encenderseguir(2);
				userFollow.seguir(user, 0.4);
			} catch (MutualFollowingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				fail();
			} catch (JarraitzenZenuenException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				fail();
			}
			Vector<Pronostico> proVec=new Vector<Pronostico>();
			Pronostico iraPro = testDA.addPronostico(forename, percentage, question);
			Pronostico galPro = testDA.addPronostico("forecast2", percentage, question);
			proVec.add(iraPro);
			proVec.add(galPro);
			Apostua bet = new Apostua();
			Apostua betFollow = new Apostua();
			question.addPronostiko(iraPro);
			try {
				
				bet = testDA.addBet(250.50, user, proVec);
				betFollow = testDA.addBetJarraitzaile(100, userFollow, proVec, user);
				bet.SetBalida(true);
				betFollow.SetBalida(true);
			} catch (IncorrectBetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				fail();
			}
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
					testDA.removeUser(user);
					testDA.removeUser(userFollow);
					testDA.removeForecast(iraPro);
					testDA.removeForecast(galPro);
					testDA.removeQuestion(question);
					testDA.removeApostua(bet.getId());
					testDA.removeApostua(betFollow.getId());
					testDA.close();
				}
			}
			catch(UserAlreadyExist e){
				e.printStackTrace();
			}
		
	}
	
	@Test
	public void test6() {
		
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
		Question question = new Question(1, "galderaTextu", 12, new Event(1, "Deskribapena",date));
		//set up test data base
		try {
			testDA.open();
			testDA.addQuestion(question);
			RegisteredUser user = testDA.addUser("u1", "49123453", "a@ehu.eus", "e5j632kl", 1990, 5, 4, 12345623, 1);
			Vector<Pronostico> proVec=new Vector<Pronostico>();
			Pronostico iraPro = testDA.addPronostico(forename, percentage, question);
			Pronostico galPro = testDA.addPronostico("forecast2", percentage, question);
			proVec.add(iraPro);
			proVec.add(galPro);
			Apostua bet = new Apostua();
			Apostua betFalse = new Apostua();
			question.addPronostiko(iraPro);
			question.addPronostiko(galPro);
			try {
				
				bet = testDA.addBet(250.50, user, proVec);
				betFalse = testDA.addBet(150.50, user, proVec);
				bet.SetBalida(true);
				betFalse.SetBalida(false);
			} catch (IncorrectBetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				fail();
			}
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
					testDA.removeUser(user);
					testDA.removeForecast(iraPro);
					testDA.removeForecast(galPro);
					testDA.removeQuestion(question);
					testDA.removeApostua(bet.getId());
					testDA.removeApostua(betFalse.getId());
					testDA.close();
				}
			}
			catch(UserAlreadyExist e){
				e.printStackTrace();
			}
	}
	
	@Test
	public void test7() {
		
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
		Question question = new Question(1, "galderaTextu", 12, new Event(1, "Deskribapena",date));
		//set up test data base
		try {
			testDA.open();
			testDA.addQuestion(question);
			RegisteredUser user = testDA.addUser("u1", "49123453", "a@ehu.eus", "e5j632kl", 1990, 5, 4, 12345623, 1);
			RegisteredUser userFollow = testDA.addUser("u2", "49123473", "b@ehu.eus", "e7j632kl", 1990, 5, 4, 12745623, 1);
			try {
				user.encenderseguir(2);
				userFollow.seguir(user, 0.4);
			} catch (MutualFollowingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				fail();
			} catch (JarraitzenZenuenException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				fail();
			}
			Vector<Pronostico> proVec=new Vector<Pronostico>();
			Pronostico iraPro = testDA.addPronostico(forename, percentage, question);
			Pronostico galPro = testDA.addPronostico("forecast2", percentage, question);
			proVec.add(iraPro);
			proVec.add(galPro);
			Apostua bet = new Apostua();
			Apostua betFollow = new Apostua();
			Apostua betFalse = new Apostua();
			question.addPronostiko(iraPro);
			question.addPronostiko(galPro);
			try {
				
				bet = testDA.addBet(250.50, user, proVec);
				betFalse = testDA.addBet(150.50, user, proVec);
				betFollow = testDA.addBetJarraitzaile(100, userFollow, proVec, user);
				bet.SetBalida(true);
				betFalse.SetBalida(false);
				betFollow.SetBalida(true);
			} catch (IncorrectBetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				fail();
			}
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
					testDA.removeUser(user);
					testDA.removeUser(userFollow);
					testDA.removeForecast(iraPro);
					testDA.removeForecast(galPro);
					testDA.removeQuestion(question);
					testDA.removeApostua(bet.getId());
					testDA.removeApostua(betFollow.getId());
					testDA.removeApostua(betFalse.getId());
					testDA.close();
				}
			}
			catch(UserAlreadyExist e){
				e.printStackTrace();
			}
		
	}
}
