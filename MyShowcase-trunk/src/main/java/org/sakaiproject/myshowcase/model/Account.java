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


public class Account extends Audited {
	
	private static final long serialVersionUID = 1L;
	
	private Long accountId;
	
	private String userId;
	
	private AccountSource accountSource;
	
	private Owner owner;

	
	/**
	  * Default constructor
	  */
    public Account() {
	}

    
	/**
	  * Minimal constructor
	  */
	public Account( String userId,
			        Owner owner) {
		this.userId = userId;
		this.owner = owner;

		}


	/**
	  * Getters and Setters
	  */

	public Long getAccountId() {
		return accountId;
	}

	
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	
	public String getUserId() {
		return userId;
	}

	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	public AccountSource getAccountSource() {
		return accountSource;
	}

	
	public void setAccountSource(AccountSource accountSource) {
		this.accountSource = accountSource;
    }
	
	
	public Owner getOwner() {
		return owner;
	}

	
	public void setOwner(Owner owner) {
		this.owner = owner;
    }
	

	public Account getDeepCopy() {
		
		Account account = new Account();
		account.setUserId(getUserId());
		
		return account;
	}	
	
}
