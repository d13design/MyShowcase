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


public class Showcase extends Audited {
	private static final long serialVersionUID = 1L;	
	private Long showcaseId;
	private String name;
	private String keyword; 
	private String shortDesc;
	private String fullDesc; 	
	private Owner owner;
	private Long ownerId;
	private ShowcaseTheme theme;
	private Date publishedDate;
	private Long publishedDateTime ;
	private Date reviewStartDate;
	private Long reviewStartDateTime ;
	private Date reviewEndDate;
	private Long reviewEndDateTime ;
	private Set<ShowcaseReviewer> showcaseReviewers = new HashSet<ShowcaseReviewer>();
	private Set<Artefact> artefacts = new HashSet<Artefact>();
	private Boolean published;
	
	/**
	 * Default constructor
	 */
	public Showcase() {
	}

	/**
	 * Minimal constructor
	 */
	public Showcase(String name,
			        String shortDesc,
			        String fullDesc,
			        Owner owner,
			        ShowcaseTheme theme
			        ) {
		this.name = name;
		this.shortDesc = shortDesc;
		this.fullDesc = fullDesc;
		this.owner = owner;
		this.theme = theme;
		}

	/**
	 * Full constructor
	 */
	public Showcase(String name,
			        String shortDesc,
			        String fullDesc,
			        String keyword,
			        Owner owner,
			        ShowcaseTheme theme,
			        Date createdDate,
			        String createdBy,
			        Date reviewStartDate,
			        Date reviewEndDate) {
		this.name = name;
		this.shortDesc = shortDesc;
		this.fullDesc = fullDesc;
		this.keyword = keyword;		
		this.owner = owner;
		this.theme = theme;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.reviewStartDate = reviewStartDate;
		this.reviewEndDate = reviewEndDate;
		this.publishedDateTime = createdDate.getTime() ;
		this.reviewStartDateTime = reviewStartDate.getTime() ;
		this.reviewEndDateTime = reviewEndDate.getTime() ;
	}

	/**
	 * Getters and Setters
	 */

	
	public Long getShowcaseId() {
		return showcaseId;
	}

	
	public void setShowcaseId(Long showcaseId) {
		this.showcaseId = showcaseId;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getFullDesc() {
		return fullDesc;
	}

	
	public void setFullDesc(String fullDesc) {
		this.fullDesc = fullDesc;
    }

	
	public String getShortDesc() {
		return shortDesc;
	}

	
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
    }

	
	public String getKeyword() {
		return keyword;
	}

	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
    }

	
	public Owner getOwner() {
		return owner;
	}

	
	public void setOwner(Owner owner) {
		this.owner = owner;
    }

	public Long getOwnerId() {
		return ownerId;
	}

	
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
    }

	public ShowcaseTheme getTheme() {
		return theme;
	}

	
	public void setTheme(ShowcaseTheme theme) {
		this.theme = theme;
    }

	
	public Set<ShowcaseReviewer> getShowcaseReviewers() {
		return this.showcaseReviewers;
	}

	
	public void setShowcaseReviewers(Set<ShowcaseReviewer> showcaseReviewers) {
		this.showcaseReviewers = showcaseReviewers;
	}	

	
	public Set<Artefact> getArtefacts() {
		return this.artefacts;
	}

	
	public void setArtefacts(Set<Artefact> artefacts) {
		this.artefacts = artefacts;
	}	

	
	public Boolean getPublished() {
		return published;
	}

	
	public void setPublished(Boolean published) {
		this.published = published;
	}
	

	
	public Date getPublishedDate() {
		return publishedDate;
	}

	
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}	

	
	
	public Long getPublishedDateTime() {
		
		publishedDateTime = publishedDate.getTime();
		
		return publishedDateTime;
	}

	public void setPublishedDateTime(Date publishedDate) {
		this.publishedDateTime = publishedDate.getTime();
	}

	
	public Date getReviewStartDate() {
		return reviewStartDate;
	}

	public String getFormattedReviewStartDate() {
		String oStr = "" ;
		if (reviewStartDate != null) {
			int dd = reviewStartDate.getDate() ;
			String sdd ;
			if (dd < 10)
				sdd = "0" ;
			else
				sdd = "" ;
	
			int mm = reviewStartDate.getMonth() + 1 ;
			String smm ;
			if (mm < 10)
				smm = "0" ;
			else
				smm = "" ;
	
			int yy = reviewStartDate.getYear() + 1900 ;
			oStr = sdd + dd + "/" + smm + mm + "/" + yy;
		}
		return oStr ;
	}
	
	public void setReviewStartDate(Date reviewStartDate) {
		this.reviewStartDate = reviewStartDate;
	}		

	public Long getReviewStartDateTime() {
	
		if (reviewStartDate == null)
			reviewStartDateTime = null ;
		else
			reviewStartDateTime = reviewStartDate.getTime();
		
		return reviewStartDateTime;
	}

	public void setReviewStartDateTime(Date reviewStartDate) {
		if (reviewStartDate == null)
			this.reviewEndDateTime = new Long(0) ;
		else
			this.reviewStartDateTime = reviewStartDate.getTime();
	}
	
	
	public Date getReviewEndDate() {
		return reviewEndDate;
	}

	public String getFormattedReviewEndDate() {
		String oStr = "" ;
		if (reviewEndDate != null) {
			int dd = reviewEndDate.getDate() ;
			String sdd ;
			if (dd < 10)
				sdd = "0" ;
			else
				sdd = "" ;
	
			int mm = reviewEndDate.getMonth() + 1 ;
			String smm ;
			if (mm < 10)
				smm = "0" ;
			else
				smm = "" ;
	
			int yy = reviewEndDate.getYear() + 1900 ;
			oStr = sdd + dd + "/" + smm + mm + "/" + yy;
		}
		return oStr ;
	}
	
	public void setReviewEndDate(Date reviewEndDate) {
		this.reviewEndDate = reviewEndDate;
	}		

	public Long getReviewEndDateTime() {
		
		if (reviewEndDate == null)
			reviewEndDateTime = null ;
		else
			reviewEndDateTime = reviewEndDate.getTime();
		
		return reviewEndDateTime;
	}

	public void setReviewEndDateTime(Date reviewEndDate) {
		if (reviewEndDate == null)
			this.reviewEndDateTime = new Long(0) ;
		else
			this.reviewEndDateTime = reviewEndDate.getTime();
	}
	
}
