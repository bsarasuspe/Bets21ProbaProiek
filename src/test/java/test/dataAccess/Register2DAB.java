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
import configuration.UtilDate;
import dataAccess.DataAccess;
import dataAccess.DataAccessRegister;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import domain.RegisteredUser;
import domain.User;
import domain.Worker;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import exceptions.UserAlreadyExist;
import test.businessLogic.TestFacadeImplementation;
 
public class RegisterDAB2 {

	 //sut:system under test
	 static DataAccessRegister sut=new DataAccessRegister(true);
	 
	 //additional operations needed to execute the test 
	 static TestDataAccessTest testDA=new TestDataAccessTest();
	
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
			RegisteredUser user = new RegisteredUser(izena, pasahitza, email, id, bankuZenbakia, UtilDate.newDate(urtea, hilabetea, eguna));
			
			String[] myStringList = {"Nikolas","12345678L","ni@ni.com","123"};
			int[] myIntList = {1994,8,11};
			
			//invoke System Under Test (sut) 
				try {
					sut.open(true);
					sut.register(myStringList,myIntList, bankuZenbakia, tipo);
					sut.close();
					assertTrue(true);
					
				}
				catch(Exception e) {
					fail();
				}
				finally {
					testDA.removeUser(user);
				}

		   }
	/*
	@Test
	//sut.irabazi:  The user is a worker. 
	public void test2() {
			
			//define parameters
			String izena = "Nikolas";
			String id = "12345678L";
			String email = "ni@ni.com";
			String pasahitza = "123";
			int urtea = 1994;
			int hilabetea = 8;
			int eguna = 11;
			long bankuZenbakia = 1234567899;
			int tipo = 1;
			Worker user = new Worker(izena, pasahitza, email, id);
			
			String[] myStringList = {"Nikolas","12345678L","ni@ni.com","123"};
			int[] myIntList = {1994,8,11};
			//invoke System Under Test (sut) 
				try {
					sut.open(true);
					sut.register(myStringList,myIntList, bankuZenbakia, tipo);
					sut.close();
					assertTrue(true);
					
				}
				catch(Exception e) {
					fail();
				}
				finally {
					testDA.removeUser(user);
				}

		   }
	/*
	@Test
	//sut.irabazi:  The user is an admin. 
	public void test3() {
			
			//define parameters
			String[] myStringList = {"Nikolas","12345678L","ni@ni.com","123"};
			int[] myIntList = {1994,8,11};
			long bankuZenbakia = 1234567899;
			int tipo = 0;
			//invoke System Under Test (sut) 
				try {
					sut.open(true);
					sut.register(myStringList,myIntList, bankuZenbakia, tipo);
					sut.close();
					assertTrue(true);
					
				}
				catch(Exception e) {
					fail();
				}

		   }

	@Test
	//sut.irabazi:  The user already exists
	public void test4() {
			
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
			RegisteredUser user;
			
			String[] myStringList = {"Nikolas","12345678L","ni@ni.com","123"};
			int[] myIntList = {1994,8,11};
			
			//set up test data base
			try {
				user = testDA.addUser(izena, id, email, pasahitza, urtea, hilabetea, eguna, bankuZenbakia, tipo);
			
			//invoke System Under Test (sut) 
				try {
					sut.open(true);
					sut.register(myStringList,myIntList, bankuZenbakia, tipo);
					sut.close();
					fail();
					
				}
				catch(UserAlreadyExist e) {
					assertTrue(true);
				}
				finally {
					testDA.removeUser(user);
				}
			} catch (UserAlreadyExist e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				fail();
			}

		   }
	
	@Test
	//sut.irabazi:  The name is null
	public void test5() {
			
			//define parameters
			String izena = null;
			String id = "12345678L";
			String email = "ni@ni.com";
			String pasahitza = "123";
			int urtea = 1994;
			int hilabetea = 8;
			int eguna = 11;
			long bankuZenbakia = 1234567899;
			int tipo = 2;
			RegisteredUser user = new RegisteredUser(izena, pasahitza, email, id, bankuZenbakia, UtilDate.newDate(urtea, hilabetea, eguna));
			
			String[] myStringList = {"Nikolas","12345678L","ni@ni.com","123"};
			int[] myIntList = {1994,8,11};
			
			//invoke System Under Test (sut) 
				try {
					sut.open(true);
					sut.register(myStringList,myIntList, bankuZenbakia, tipo);
					sut.close();
					fail();
					
				}
				catch(UserAlreadyExist e) {
					assertTrue(true);
				}
				catch(IllegalArgumentException e) {
					e.printStackTrace();
					assertTrue(true);
				}
	}
	
	@Test
	//sut.irabazi:  The id is null
	public void test6() {
			
			//define parameters
			String izena = "Nikolas";
			String id = null;
			String email = "ni@ni.com";
			String pasahitza = "123";
			int urtea = 1994;
			int hilabetea = 8;
			int eguna = 11;
			long bankuZenbakia = 1234567899;
			int tipo = 2;
			RegisteredUser user = new RegisteredUser(izena, pasahitza, email, id, bankuZenbakia, UtilDate.newDate(urtea, hilabetea, eguna));
			
			String[] myStringList = {"Nikolas","12345678L","ni@ni.com","123"};
			int[] myIntList = {1994,8,11};
			
			//invoke System Under Test (sut) 
				try {
					sut.open(true);
					sut.register(myStringList,myIntList, bankuZenbakia, tipo);
					sut.close();
					fail();
					
				}
				catch(UserAlreadyExist e) {
					assertTrue(true);
				}
				catch(IllegalArgumentException e) {
					e.printStackTrace();
					assertTrue(true);
				}
	}
	
	@Test
	//sut.irabazi:  The email is null
	public void test7() {
			
			//define parameters
			String izena = "Nikolas";
			String id = "12345678L";
			String email = null;
			String pasahitza = "123";
			int urtea = 1994;
			int hilabetea = 8;
			int eguna = 11;
			long bankuZenbakia = 1234567899;
			int tipo = 2;
			RegisteredUser user = new RegisteredUser(izena, pasahitza, email, id, bankuZenbakia, UtilDate.newDate(urtea, hilabetea, eguna));
			
			String[] myStringList = {"Nikolas","12345678L","ni@ni.com","123"};
			int[] myIntList = {1994,8,11};
			
			//invoke System Under Test (sut) 
				try {
					sut.open(true);
					sut.register(myStringList,myIntList, bankuZenbakia, tipo);
					sut.close();
					fail();
					
				}
				catch(UserAlreadyExist e) {
					assertTrue(true);
				}
				catch(IllegalArgumentException e) {
					e.printStackTrace();
					assertTrue(true);
				}
	}
	
	@Test
	//sut.irabazi:  The password is null
	public void test8() {
			
			//define parameters
			String izena = "Nikolas";
			String id = "12345678L";
			String email = "ni@ni.com";
			String pasahitza = null;
			int urtea = 1994;
			int hilabetea = 8;
			int eguna = 11;
			long bankuZenbakia = 1234567899;
			int tipo = 2;
			RegisteredUser user = new RegisteredUser(izena, pasahitza, email, id, bankuZenbakia, UtilDate.newDate(urtea, hilabetea, eguna));
			
			String[] myStringList = {"Nikolas","12345678L","ni@ni.com","123"};
			int[] myIntList = {1994,8,11};
			
			//invoke System Under Test (sut) 
				try {
					sut.open(true);
					sut.register(myStringList,myIntList, bankuZenbakia, tipo);
					sut.close();
					fail();
					
				}
				catch(UserAlreadyExist e) {
					assertTrue(true);
				}
				catch(IllegalArgumentException e) {
					e.printStackTrace();
					assertTrue(true);
				}
	}
	
	@Test
	//sut.irabazi:  The year is null
	public void test9() {
			try {
				
			//define parameters
			String izena = "Nikolas";
			String id = "12345678L";
			String email = "ni@ni.com";
			String pasahitza = "123";
			int urtea = (Integer) null;
			int hilabetea = 8;
			int eguna = 11;
			long bankuZenbakia = 1234567899;
			int tipo = 2;
			RegisteredUser user = new RegisteredUser(izena, pasahitza, email, id, bankuZenbakia, UtilDate.newDate(urtea, hilabetea, eguna));
			
			String[] myStringList = {"Nikolas","12345678L","ni@ni.com","123"};
			int[] myIntList = {1994,8,11};
			
			//invoke System Under Test (sut) 
				try {
					sut.open(true);
					sut.register(myStringList,myIntList, bankuZenbakia, tipo);
					sut.close();
					fail();
					
				}
				catch(UserAlreadyExist e) {
					assertTrue(true);
				}
				catch(IllegalArgumentException e) {
					e.printStackTrace();
					assertTrue(true);
				}
			}
			catch(NullPointerException e) {
				fail();
			}
	}
	
	@Test
	//sut.irabazi:  The month is null
	public void test10() {
		try {
			
		//define parameters
		String izena = "Nikolas";
		String id = "12345678L";
		String email = "ni@ni.com";
		String pasahitza = "123";
		int urtea = 1994;
		int hilabetea = (Integer) null;
		int eguna = 11;
		long bankuZenbakia = 1234567899;
		int tipo = 2;
		RegisteredUser user = new RegisteredUser(izena, pasahitza, email, id, bankuZenbakia, UtilDate.newDate(urtea, hilabetea, eguna));
		
		String[] myStringList = {"Nikolas","12345678L","ni@ni.com","123"};
		int[] myIntList = {1994,8,11};
		
		//invoke System Under Test (sut) 
			try {
				sut.open(true);
				sut.register(myStringList,myIntList, bankuZenbakia, tipo);
				sut.close();
				fail();
				
			}
			catch(UserAlreadyExist e) {
				assertTrue(true);
			}
			catch(IllegalArgumentException e) {
				e.printStackTrace();
				assertTrue(true);
			}
		}
		catch(NullPointerException e) {
			fail();
		}
}
	
	@Test
	//sut.irabazi:  The day is null
	public void test11() {
		try {
			
		//define parameters
		String izena = "Nikolas";
		String id = "12345678L";
		String email = "ni@ni.com";
		String pasahitza = "123";
		int urtea = 1994;
		int hilabetea = 8;
		int eguna = (Integer) null;
		long bankuZenbakia = 1234567899;
		int tipo = 2;
		RegisteredUser user = new RegisteredUser(izena, pasahitza, email, id, bankuZenbakia, UtilDate.newDate(urtea, hilabetea, eguna));
		
		String[] myStringList = {"Nikolas","12345678L","ni@ni.com","123"};
		int[] myIntList = {1994,8,11};
		
		//invoke System Under Test (sut) 
			try {
				sut.open(true);
				sut.register(myStringList,myIntList, bankuZenbakia, tipo);
				sut.close();
				fail();
				
			}
			catch(UserAlreadyExist e) {
				assertTrue(true);
			}
			catch(IllegalArgumentException e) {
				e.printStackTrace();
				assertTrue(true);
			}
		}
		catch(NullPointerException e) {
			fail();
		}
}
	/*
	@Test
	//sut.irabazi:  The bankNumber is null
	public void test12() {
		try {
			
		//define parameters
		String izena = "Nikolas";
		String id = "12345678L";
		String email = "ni@ni.com";
		String pasahitza = "123";
		int urtea = 1994;
		int hilabetea = 8;
		int eguna = 11;
		long bankuZenbakia = (Long) null;
		int tipo = 2;
		RegisteredUser user = new RegisteredUser(izena, pasahitza, email, id, bankuZenbakia, UtilDate.newDate(urtea, hilabetea, eguna));
		
		String[] myStringList = {"Nikolas","12345678L","ni@ni.com","123"};
		int[] myIntList = {1994,8,11};
		
		//invoke System Under Test (sut) 
			try {
				sut.open(true);
				sut.register(myStringList,myIntList, bankuZenbakia, tipo);
				sut.close();
				fail();
				
			}
			catch(UserAlreadyExist e) {
				assertTrue(true);
			}
			catch(IllegalArgumentException e) {
				e.printStackTrace();
				assertTrue(true);
			}
		}
		catch(NullPointerException e) {
			fail();
		}
}*/
	/*
	@Test
	//sut.irabazi:  The type is null
	public void test13() {
		try {
			
		//define parameters
		String izena = "Nikolas";
		String id = "12345678L";
		String email = "ni@ni.com";
		String pasahitza = "123";
		int urtea = 1994;
		int hilabetea = 8;
		int eguna = 11;
		long bankuZenbakia = 1234567899;
		int tipo = (Integer) null;
		RegisteredUser user = new RegisteredUser(izena, pasahitza, email, id, bankuZenbakia, UtilDate.newDate(urtea, hilabetea, eguna));
		
		String[] myStringList = {"Nikolas","12345678L","ni@ni.com","123"};
		int[] myIntList = {1994,8,11};
		
		//invoke System Under Test (sut) 
			try {
				sut.open(true);
				sut.register(myStringList,myIntList, bankuZenbakia, tipo);
				sut.close();
				fail();
				
			}
			catch(UserAlreadyExist e) {
				assertTrue(true);
			}
			catch(IllegalArgumentException e) {
				e.printStackTrace();
				assertTrue(true);
			}
		}
		catch(NullPointerException e) {
			fail();
		}
}*/
	
	
	
	
		
}
