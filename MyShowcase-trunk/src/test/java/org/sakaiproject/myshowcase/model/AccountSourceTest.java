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

import java.util.HashSet;
import java.util.Set;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;


public class AccountSourceTest {
	
	final static String valueLocation = "location";
	
	final static String valueName = "name";	
	
	final static String valueDescription = "description";
	
	final static String newValueLocation = "locationNew";
	
	final static String newValueName = "nameNew";	
	
	final static String newValueDescription = "descriptionNew";	
	
	final static Long valueAccountSourceId = new Long("1");
	
	final static Long newValueAccountSourceId = new Long("2");
	
	Set<Account> accounts = new HashSet<Account>();	
	
	AccountSource accountSource;

	
	@Before
	public void instantiate() {
		
		accountSource = new AccountSource();
		
		accountSource.setLocation(valueLocation);
		
		accountSource.setName(valueName);
		
		accountSource.setAccountSourceId(valueAccountSourceId);
		
		accountSource.setDescription(valueDescription);
		
		Account account = new Account();
		
		accounts.add(account);
		
		accountSource.setAccounts(accounts);		
	}

	
	@Test
	public void getLocationTest() {
		assertEquals("Model: Test that accountSource.location is correctly read.", valueLocation, accountSource.getLocation());
	}	

	
	@Test
	public void getAccountSourceIdByTest() {
		assertEquals("Model: Test that accountSource.accountSourceId is correctly read.", valueAccountSourceId, accountSource.getAccountSourceId());
	}	

	
	@Test
	public void getNameTest() {
		assertSame("Model:Test that accountSource.name is correctly read.", valueName, accountSource.getName());
	}	

	
	@Test
	public void setNameTest() {
		
		accountSource.setName(newValueName);
	
		assertEquals("Model:Test that accountSource.name is correctly set.", newValueName, accountSource.getName());
	}		

	
	@Test
	public void setDescriptionTest() {
		
		accountSource.setDescription(newValueDescription);
	
		assertEquals("Model:Test that accountSource.desciption is correctly set.", newValueDescription, accountSource.getDescription());
	}		


	@Test
	public void setLocationTest() {
		
		accountSource.setLocation(newValueLocation);
	
		assertEquals("Model:Test that accountSource.Location is correctly set.", newValueLocation, accountSource.getLocation());
	}		

	
	@Test
	public void getAccountsTest() {
	
		assertSame("Model:Test that accountSource.accounts are correctly read.", accounts ,accountSource.getAccounts());
	}	
	
	
}