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
import static org.junit.Assert.assertSame;


public class AccountTest {
	
	final static String valueUserId = "userId";
	
	final static Long valueAccountId = new Long("1");
	
	final static String newValueUserId = "userIdNew";
	
	final static Long newValueAccountId = new Long("2");
	
	private Account account = new Account();
	
	Owner owner;
	
	AccountSource accountSource;

	
	@Before
	public void instantiate() {
		
		owner = new Owner();
		
		accountSource = new AccountSource();
		
		account.setOwner(owner);
		
		account.setAccountSource(accountSource);
		
		account.setUserId(valueUserId);
		
		account.setAccountId(valueAccountId);
		
	}

	
	@Test
	public void getUserIdTest() {
		assertEquals("Model: Test that account.userid is correctly read.", valueUserId, account.getUserId());
	}	

	
	@Test
	public void getAccountIdByTest() {
		assertEquals("Model: Test that account.accountId is correctly read.", valueAccountId, account.getAccountId());
	}	

	
	@Test
	public void getOwnerTest() {
		assertSame("Model:Test that account.owner is correctly read.", owner, account.getOwner());
	}	

	
	@Test
	public void getAccountSourceTest() {
		assertSame("Model:Test that account.accountSource is correctly read.", accountSource, account.getAccountSource());
	}	

	
	@Test
	public void setAccountIdTest() {
		
		account.setAccountId(newValueAccountId);
	
		assertEquals("Model:Test that account.accountId is correctly set.", newValueAccountId, account.getAccountId());
	}		

	
	@Test
	public void setUserIdTest() {
		
		account.setUserId(newValueUserId);
	
		assertEquals("Model:Test that account.userId is correctly set.", newValueUserId, account.getUserId());
	}		

}
