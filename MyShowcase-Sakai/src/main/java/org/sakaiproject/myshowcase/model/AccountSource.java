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

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;



public class AccountSource extends Audited implements Serializable  {
	private static final long serialVersionUID = 1L;
	private Long accountSourceId;
	private String name;
	private String location;	
	private String description;
	private Set<Account> accounts = new HashSet<Account>();

	
	/**
	 * Default constructor
	 */
	public AccountSource() {
	}

	
	/**
	 * Minimal constructor
	 */
	public AccountSource(String name) {
		this.name = name;
		}

	
	/**
	 * Full constructor
	 */
	public AccountSource(String name,
						 String location,	
						String description,
			            Date createdDate,
			            String createdBy) {
		this.name = name;
		this.description = description;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
	}

	/**
	 * Getters and Setters
	 */

	public Long getAccountSourceId() {
		return accountSourceId;
	}

	
	public void setAccountSourceId(Long accountSourceId) {
		this.accountSourceId = accountSourceId;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getLocation() {
		return location;
	}

	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getDescription() {
		return description;
	}

	
	public void setDescription(String description) {
		this.description = description;
	}

	
	public Set<Account> getAccounts() {
		return this.accounts;
	}

	
	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
	
	public AccountSource getDeepCopy() {
		
		AccountSource accountSource = new AccountSource();
		accountSource.setName(getName());
		accountSource.setDescription(getDescription());

		
		return accountSource;
	}	
}

