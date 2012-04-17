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

public class Reviewer extends Audited {
	private static final long serialVersionUID = 1L;	
	private Long reviewerId;
	private String name;
	private String email; 
	private Owner owner;
	private Set<ShowcaseReviewer> showcaseReviewers = new HashSet<ShowcaseReviewer>();

	/**
	 * Default constructor
	 */
	public Reviewer() {
	}

	/**
	 * Minimal constructor
	 */
	public Reviewer(String name,
			        String email,
			        Owner owner) {
		this.name = name;
		this.email = email;
		this.owner = owner;
		}

	/**
	 * Full constructor
	 */
	public Reviewer(String name,
			        String email,
			        Owner owner,
			        Date createdDate,
			        String createdBy) {
		this.name = name;
		this.email = email;
		this.owner = owner;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
	}

	
	/**
	 * Getters and Setters
	 */

	public Long getReviewerId() {
		return reviewerId;
	}

	
	public void setReviewerId(Long reviewerId) {
		this.reviewerId = reviewerId;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getEmail() {
		return email;
	}

	
	public void setEmail(String email) {
		this.email = email;
    }

	
	public Owner getOwner() {
		return owner;
	}

	
	public void setOwner(Owner owner) {
		this.owner = owner;
    }

	
	public Set<ShowcaseReviewer> getShowcaseReviewers() {
		return this.showcaseReviewers;
	}

	
	public void setShowcaseReviewers(Set<ShowcaseReviewer> showcaseReviewers) {
		this.showcaseReviewers = showcaseReviewers;
	}	

}
