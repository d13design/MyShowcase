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

public class ShowcaseReviewer extends Audited{
	
	private static final long serialVersionUID = 1L;
	
	private Long showcaseReviewerId;
	
	private Showcase showcase;
	
	private Reviewer reviewer;
	
	private Date reviewedDate;

	
	/**
	  * Default constructor
	  */
	public ShowcaseReviewer() {
	}

	
	/**
	  * Minimal constructor
	  */
	public ShowcaseReviewer(Showcase showcase,
			                Reviewer reviewer){
		
		this.showcase = showcase;
		
		this.reviewer = reviewer;
	}

	/**
	 * Full constructor
	 */
	public ShowcaseReviewer(Showcase showcase,
			                Reviewer reviewer,
			                Date createdDate,
			                String createdBy){
		
		this.showcase = showcase;
		
		this.reviewer = reviewer;
		
		this.createdDate = createdDate;
		
		this.createdBy = createdBy;
	}

	/**
	 * Getters and Setters
	 */

	
	public Long getShowcaseReviewerId(){
		
		return showcaseReviewerId;
	}

	
	public void setShowcaseReviewerId(Long showcaseReviewerId){
		
		this.showcaseReviewerId = showcaseReviewerId;
	}

	
	public Showcase getShowcase(){
		
		return showcase;
	}

	
	public void setShowcase(Showcase showcase){
		
		this.showcase = showcase;
	}

	
	public Reviewer getReviewer(){
		
		return reviewer;
	}

	
	public void setReviewer(Reviewer reviewer){
		
		this.reviewer = reviewer;
	}

	
	public Date getReviewedDate(){
		
		return reviewedDate;
	}

	
	public void setReviewedDate(Date reviewedDate){
		
		this.reviewedDate = reviewedDate;
	}
	
}
