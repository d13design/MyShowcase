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

public class Audited {
	private static final long serialVersionUID = 1L;
	protected Date createdDate;
	protected Long createdDateTime;
	protected String createdBy;
	protected Date updatedDate;
	protected Long updatedDateTime;
	protected String updatedBy;
	
	/**
	 * Default constructor
	 */
	public Audited() {
	}

	
	public Date getCreatedDate() {
		return createdDate;
	}

	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public Long getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDate) {
		this.createdDateTime = createdDate.getTime();
	}

	
	public Date getUpdatedDate() {
		return updatedDate;
	}

	
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}	

	public Long getUpdatedDateTime() {
		return createdDateTime;
	}

	public void setUpdatedDateTime(Date updatedDate) {
		this.updatedDateTime = updatedDate.getTime();
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	
	public String getCreatedBy() {
		return createdBy;
	}

	
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}	
}
