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

public class RegisterDAW {

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
			RegisteredUser user = new RegisteredUser(izena, pasahitza, email, id, bankuZenbakia, UtilDate.newDate(urtea, hilabetea, eguna));
			//invoke System Under Test (sut) 
				try {
					sut.open(true);
					sut.register(izena, id, email, pasahitza, urtea, hilabetea, eguna, bankuZenbakia, tipo);
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
				finally {
					testDA.removeUser(user);
				}

		   }
	@Test
	//sut.irabazi:  The user is an admin. 
	public void test3() {
			
			//define parameters
			String izena = "Nikolas";
			String id = "12345678L";
			String email = "ni@ni.com";
			String pasahitza = "123";
			int urtea = 1994;
			int hilabetea = 8;
			int eguna = 11;
			long bankuZenbakia = 1234567899;
			int tipo = 0;
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
			//set up test data base
			try {
				user = testDA.addUser(izena, id, email, pasahitza, urtea, hilabetea, eguna, bankuZenbakia, tipo);
			
			//invoke System Under Test (sut) 
				try {
					sut.open(true);
					sut.register(izena, id, email, pasahitza, urtea, hilabetea, eguna, bankuZenbakia, tipo);;
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
}
