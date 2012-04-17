//***************************************************************************
//*MyShowcase                                                               * 
//*Copyright (C) 2010 MyKnowledgeMap Ltd.                                   *
//*                                                                         *
//*This program is free software: you can redistribute it and/or modify     *
//*it under the terms of the GNU Affero General Public License as           *
//*published by the Free Software Foundation, either version 3 of the       *
//*License, or (at your option) any later version.                          *
//*                                                                         *
//*This program is distributed in the hope that it will be useful,          *
//*but WITHOUT ANY WARRANTY; without even the implied warranty of           *
//*MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the            * 
//*GNU Affero General Public License for more details.                      *
//*                                                                         *
//*You should have received a copy of the GNU Affero General Public License *
//*along with this program.  If not, see <http://www.gnu.org/licenses/>.    *
//*                                                                         *
//*Web: <http://www.my-showcase.org/>.                                      *
//*Email: <myshowcase@myknowledgemap.com>                                   *
//*                                                                         *
//***************************************************************************

package org.sakaiproject.myshowcase.model;


import org.junit.*;
import static org.junit.Assert.assertEquals;


public class RegistrationTest{
	
	final static String valueUserName = "userName";	
	
	final static String newValueUserName = "userNameNew";	
	
	final static String valuePassword = "password";	
	
	final static String newValuePassword = "passwordNew";
	
	final static String valuePasswordConfirm = "passwordConfirm";	
	
	final static String newValuePasswordConfirm = "passwordConfirmNew";	
	
	final static String valueEmail = "email";	
	
	final static String newValueEmail = "emailNew";
	
	final static String valueEmailConfirm = "emailConfirm";	
	
	final static String newValueEmailConfirm = "emailConfirmNew";	
	
	final static String valueForename = "forename";	
	
	final static String newValueForename = ("forenameNew");
	
	final static String valueSurname = "surname";	
	
	final static String newValueSurname = ("surnameNew");
	
	final static String valueBirthDate = "10/12/1984";	
	
	final static String newValueBirthDate = ("11/11/1986");
	
	
	Registration registration;

	
	@Before
	public void instantiate(){
		
		registration = new Registration();
		
		registration.setUserName(valueUserName);
		
		registration.setPassword(valuePassword);
		
		registration.setPasswordConfirm(valuePasswordConfirm);
		
		registration.setEmail(valueEmail);
		
		registration.setEmailConfirm(valueEmailConfirm);
		
		registration.setForename(valueForename);
		
		registration.setSurname(valueSurname);
		
		registration.setBirthDate(valueBirthDate);		
	}

	
	@Test
	public void getPasswordTest(){
		
		assertEquals("Model:Test that registration.password is correctly read.", valuePassword, registration.getPassword());
	}	

	
	@Test
	public void setPasswordTest(){
		
		registration.setPassword(newValuePassword);
	
		assertEquals("Model:Test that registration.password is correctly set.", newValuePassword, registration.getPassword());
	}		

	
	@Test
	public void getPasswordConfirmTest(){
		
		assertEquals("Model:Test that registration.passwordConfirm is correctly read.", valuePasswordConfirm, registration.getPasswordConfirm());
	}	

	
	@Test
	public void setPasswordConfirmTest(){
		
		registration.setPasswordConfirm(newValuePasswordConfirm);
	
		assertEquals("Model:Test that registration.passwordConfirm is correctly set.", newValuePasswordConfirm, registration.getPasswordConfirm());
	}		

	
	@Test
	public void getUserNameTest(){
		
		assertEquals("Model:Test that registration.userName is correctly read.", valueUserName, registration.getUserName());
	}	

	
	@Test
	public void setUserNameTest(){
		
		registration.setUserName(newValueUserName);
	
		assertEquals("Model:Test that registration.userName is correctly set.", newValueUserName, registration.getUserName());
	}		

	
	@Test
	public void getEmailTest(){
		
		assertEquals("Model:Test that registration.email is correctly read.", valueEmail, registration.getEmail());
	}	

	
	@Test
	public void setEmailTest(){
		
		registration.setEmail(newValueEmail);
	
		assertEquals("Model:Test that registration.email is correctly set.", newValueEmail, registration.getEmail());
	}		

	
	@Test
	public void getEmailConfirmTest(){
		
		assertEquals("Model:Test that registration.emailConfirm is correctly read.", valueEmailConfirm, registration.getEmailConfirm());
	}	

	
	@Test
	public void setEmailConfirmTest(){
		
		registration.setEmailConfirm(newValueEmailConfirm);
	
		assertEquals("Model:Test that registration.emailConfirm is correctly set.", newValueEmailConfirm, registration.getEmailConfirm());
	}			

	
	@Test
	public void getForenameTest(){
		
		assertEquals("Model:Test that registration.forename is correctly read.", valueForename, registration.getForename());
	}	

	
	@Test
	public void setForenameTest(){
		
		registration.setForename(newValueForename);
	
		assertEquals("Model:Test that registration.forename is correctly set.", newValueForename, registration.getForename());
	}		

	
	@Test
	public void getSurnameTest(){
		
		assertEquals("Model:Test that registration.surname is correctly read.", valueSurname, registration.getSurname());
	}	

	
	@Test
	public void setSurnameTest(){
		
		registration.setSurname(newValueSurname);
	
		assertEquals("Model:Test that registration.surname is correctly set.", newValueSurname, registration.getSurname());
	}

	
	@Test
	public void getBirthDateTest(){
		
		assertEquals("Model:Test that registration.birthDate is correctly read.", valueBirthDate, registration.getBirthDate());
	}	

	
	@Test
	public void setBirthDateTest(){
		
		registration.setBirthDate(newValueBirthDate);
	
		assertEquals("Model:Test that registration.birthDate is correctly set.", newValueBirthDate, registration.getBirthDate());
	}	
	
}
