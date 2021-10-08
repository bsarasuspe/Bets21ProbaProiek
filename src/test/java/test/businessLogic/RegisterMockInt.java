package test.businessLogic;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Pronostico;
import domain.Question;
import domain.RegisteredUser;
import domain.Worker;
import exceptions.UserAlreadyExist;

public class RegisterMockInt {
	
	 DataAccess dataAccess=Mockito.mock(DataAccess.class);
     Question mockedQuestion=Mockito.mock(Question.class);
     Pronostico mockedForecast=Mockito.mock(Pronostico.class);
     
	
	@InjectMocks
	 BLFacade sut=new BLFacadeImplementation(dataAccess);
	
	@Test
	//sut.irabazi:  The user is a registeredUser. 
	public void test1() {
			
			//define parameters
			String[] myStringList = {"Nikolas","12345678L","ni@ni.com","123"};
			int[] myIntList = {1994,8,11};
			long bankuZenbakia = 1234567899;
			int tipo = 2;
			
			//invoke System Under Test (sut) 
				try {
					sut.register(myStringList,myIntList, bankuZenbakia, tipo);
					Mockito.verify(dataAccess, Mockito.times(1)).register(myStringList,myIntList, bankuZenbakia, tipo);
					assertTrue(true);
					
				}
				catch(Exception e) {
					fail();
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
			
			String[] myStringList = {"Nikolas","12345678L","ni@ni.com","123"};
			int[] myIntList = {1994,8,11};
			
			//invoke System Under Test (sut) 
				try {
					sut.register(myStringList,myIntList, bankuZenbakia, tipo);
					Mockito.verify(dataAccess, Mockito.times(1)).register(myStringList,myIntList, bankuZenbakia, tipo);
					assertTrue(true);
					
				}
				catch(Exception e) {
					fail();
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
			String[] myStringList = {"Nikolas","12345678L","ni@ni.com","123"};
			int[] myIntList = {1994,8,11};
			
			//invoke System Under Test (sut) 
				try {
					sut.register(myStringList,myIntList, bankuZenbakia, tipo);
					Mockito.verify(dataAccess, Mockito.times(1)).register(myStringList,myIntList, bankuZenbakia, tipo);
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
			
			//invoke System Under Test (sut) 
				try {
					sut.register(myStringList,myIntList, bankuZenbakia, tipo);
					Mockito.doThrow(new UserAlreadyExist()).when(dataAccess).register(myStringList,myIntList, bankuZenbakia, tipo);
					fail();
					
				}
				catch(UserAlreadyExist e) {
					assertTrue(true);
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
					sut.register(myStringList,myIntList, bankuZenbakia, tipo);
					Mockito.doThrow(new RuntimeException()).when(dataAccess).register(myStringList,myIntList, bankuZenbakia, tipo);
					fail();
					
				}
				catch(UserAlreadyExist e) {
					assertTrue(true);
				}
				catch(RuntimeException e) {
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
					sut.register(myStringList,myIntList, bankuZenbakia, tipo);
					Mockito.doThrow(new RuntimeException()).when(dataAccess).register(myStringList,myIntList, bankuZenbakia, tipo);
					fail();
					
				}
				catch(UserAlreadyExist e) {
					assertTrue(true);
				}
				catch(RuntimeException e) {
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
				sut.register(myStringList,myIntList, bankuZenbakia, tipo);
				Mockito.doThrow(new RuntimeException()).when(dataAccess).register(myStringList,myIntList, bankuZenbakia, tipo);
				fail();
				
			}
			catch(UserAlreadyExist e) {
				assertTrue(true);
			}
			catch(RuntimeException e) {
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
				sut.register(myStringList,myIntList, bankuZenbakia, tipo);
				Mockito.doThrow(new RuntimeException()).when(dataAccess).register(myStringList,myIntList, bankuZenbakia, tipo);
				fail();
				
			}
			catch(UserAlreadyExist e) {
				assertTrue(true);
			}
			catch(RuntimeException e) {
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
				sut.register(myStringList,myIntList, bankuZenbakia, tipo);
				Mockito.doThrow(new RuntimeException()).when(dataAccess).register(myStringList,myIntList, bankuZenbakia, tipo);
				fail();
				
			}
			catch(UserAlreadyExist e) {
				assertTrue(true);
			}
			catch(RuntimeException e) {
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
			sut.register(myStringList,myIntList, bankuZenbakia, tipo);
			Mockito.doThrow(new RuntimeException()).when(dataAccess).register(myStringList,myIntList, bankuZenbakia, tipo);
			fail();
			
		}
		catch(UserAlreadyExist e) {
			assertTrue(true);
		}
		catch(RuntimeException e) {
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
			sut.register(myStringList,myIntList, bankuZenbakia, tipo);
			Mockito.doThrow(new RuntimeException()).when(dataAccess).register(myStringList,myIntList, bankuZenbakia, tipo);
			fail();
			
		}
		catch(UserAlreadyExist e) {
			assertTrue(true);
		}
		catch(RuntimeException e) {
			e.printStackTrace();
			assertTrue(true);
		}
		}
		catch(NullPointerException e) {
			fail();
		}
}
	
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
			sut.register(myStringList,myIntList, bankuZenbakia, tipo);
			Mockito.doThrow(new RuntimeException()).when(dataAccess).register(myStringList,myIntList, bankuZenbakia, tipo);
			fail();
			
		}
		catch(UserAlreadyExist e) {
			assertTrue(true);
		}
		catch(RuntimeException e) {
			e.printStackTrace();
			assertTrue(true);
		}
		}
		catch(NullPointerException e) {
			fail();
		}
}
	
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
			sut.register(myStringList,myIntList, bankuZenbakia, tipo);
			Mockito.doThrow(new RuntimeException()).when(dataAccess).register(myStringList,myIntList, bankuZenbakia, tipo);
			fail();
			
		}
		catch(UserAlreadyExist e) {
			assertTrue(true);
		}
		catch(RuntimeException e) {
			e.printStackTrace();
			assertTrue(true);
		}
		}
		catch(NullPointerException e) {
			fail();
		}
}
	
	
	
	
		


}
