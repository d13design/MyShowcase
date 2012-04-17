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


public class Registration extends Audited {
	private static final long serialVersionUID = 1L;	
	private String password;
	private String passwordConfirm;
	private String userName;
	private String email;
	private String emailConfirm;
	private String forename;
	private String surname;
	private String birthDate;
	
	/**
	 * Default constructor
	 */
    public Registration() {
	}

    
	/**
	 * Getters and Setters
	 */
    
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	   
	public String getForename() {
		return forename;
	}
	
	public void setForename(String forename) {
		this.forename = forename;
	}	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
    }
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
    }	
	
	public String getEmailConfirm() {
		return emailConfirm;
	}
	
	public void setEmailConfirm(String emailConfirm) {
		this.emailConfirm = emailConfirm;
    }	
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
    }	
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
    }
	
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
    }	
	
	public String getBirthDate() {
		return this.birthDate;
    }		
	
}