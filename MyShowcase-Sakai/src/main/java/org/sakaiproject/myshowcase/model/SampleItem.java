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

/**
 * This is a sample POJO (data storage object) 
 * @author Sakai App Builder -AZ
 */
public class SampleItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;
	private String ownerId; // Sakai userId
	private String locationId; // Sakai locationId
	private Boolean hidden; // only visible to owner if true
	private Date dateCreated;

	/**
	 * Default constructor
	 */
	public SampleItem() {
	}

	/**
	 * Minimal constructor
	 */
	public SampleItem(String title,
			String ownerId, String locationId) {
		this.title = title;
		this.ownerId = ownerId;
		this.locationId = locationId;
	}

	/**
	 * Full constructor
	 */
	public SampleItem(String title,
			String ownerId, String locationId,
			Boolean hidden, Date dateCreated) {
		this.title = title;
		this.ownerId = ownerId;
		this.locationId = locationId;
		this.hidden = hidden;
		this.dateCreated = dateCreated;
	}

	/**
	 * Getters and Setters
	 */
	public Boolean getHidden() {
		return hidden;
	}
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

}
