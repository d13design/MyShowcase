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


public class LoginTest{
	
	final static String valueUserName = "userName";	
	
	final static String newValueUserName = "userNameNew";	
	
	final static String valuePassword = "password";	
	
	final static String newValuePassword = "passwordNew";	
	
	Login login;

	
	@Before
	public void instantiate(){
		
		login = new Login();
		
		login.setUserName(valueUserName);
		
		login.setPassword(valuePassword);
	}

	
	@Test
	public void getPasswordTest(){
		
		assertEquals("Model:Test that login.password is correctly read.", valuePassword, login.getPassword());
	}	

	
	@Test
	public void setPasswordTest(){
		
		login.setPassword(newValuePassword);
	
		assertEquals("Model:Test that login.password is correctly set.", newValuePassword, login.getPassword());
	}		

	
	@Test
	public void getUserNameTest(){
		
		assertEquals("Model:Test that login.userName is correctly read.", valueUserName, login.getUserName());
	}	

	
	@Test
	public void setUserNameTest(){
		
		login.setUserName(newValueUserName);
	
		assertEquals("Model:Test that login.userName is correctly set.", newValueUserName, login.getUserName());
	}		
	

}