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


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Owner extends Audited {
	private static final long serialVersionUID = 1L;
	
	private Long ownerId;
	private String userId;
	private String fullname;
	private String forename;
	private String surname;
	private String userName;
	private String password;
	private String email;
	
	private Set<Showcase> showcases = new HashSet<Showcase>();
	private Set<Reviewer> reviewers = new HashSet<Reviewer>();
	private Set<Artefact> artefacts = new HashSet<Artefact>();
	private Set<Account> accounts = new HashSet<Account>();
	private Set<Tag> tags = new HashSet<Tag>();
	private Set<ArtefactMapping> mappings = new HashSet<ArtefactMapping>();
	
	/**
	 * Default constructor
	 */
	public Owner() {
	}

	
	/**
	 * Minimal constructor
	 */
	public Owner(String userId) {
		this.userId = userId;
	}

	
	/**
	 * Full constructor
	 */
	public Owner(String userId,
			     Date createdDate,
			     String createdBy) {
		this.userId = userId;
		this.createdBy = createdBy;
	}

	/**
	 * Complete constructor
	 */
	public Owner(String userId,
				 String fullname,
				 String forename,
				 String surname,
			     Date createdDate,
			     String createdBy) {
		this.userId = userId;
		this.fullname = fullname;
		this.forename = forename;
		this.surname = surname;
		this.createdBy = createdBy;
	}

	/**
	 * Getters and Setters
	 */

	public Long getOwnerId() {
		return ownerId;
	}

	
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	public String getFullname() {
		return fullname;
	}
	
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	
	public String getForename() {
		return forename;
	}
	
	public void setForename(String forename) {
		this.forename = forename;
	}

	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
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
		
	
	public Set<Reviewer> getReviewers() {
		return this.reviewers;
	}

	
	public void setReviewers(Set<Reviewer> reviewers) {
		this.reviewers = reviewers;
	}

	
	public Set<Showcase> getShowcases() {
		return this.showcases;
	}

	
	public void setShowcases(Set<Showcase> showcases) {
		this.showcases = showcases;
	}	

	
	public Set<Artefact> getArtefacts() {
		return this.artefacts;
	}

	
	public void setArtefacts(Set<Artefact> artefacts) {
		this.artefacts = artefacts;
	}

	
	public Set<Account> getAccounts() {
		return this.accounts;
	}

	
	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
	
	public Set<Tag> getTags() {
		return this.tags;
	}

	
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}		
	
	public Set<ArtefactMapping> getMappings() {
		return this.mappings;
	}

	
	public void setMappings(Set<ArtefactMapping> mappings) {
		this.mappings = mappings;
	}		
	
}
