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


public class ArtefactType extends Audited implements Serializable  {
	private static final long serialVersionUID = 1L;
	private Long artefactTypeId;
	private String name;
	private String description;
	private Set<Artefact> artefacts = new HashSet<Artefact>();

	
	/**
	 * Default constructor
	 */
	public ArtefactType() {
	}

	
	/**
	 * Minimal constructor
	 */
	public ArtefactType(String name) {
		this.name = name;
		}

	
	/**
	 * Full constructor
	 */
	public ArtefactType(String name,
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

	public Long getArtefactTypeId() {
		return artefactTypeId;
	}

	
	public void setArtefactTypeId(Long artefactTypeId) {
		this.artefactTypeId = artefactTypeId;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getDescription() {
		return description;
	}

	
	public void setDescription(String description) {
		this.description = description;
	}

	
	public Set<Artefact> getArtefacts() {
		return this.artefacts;
	}

	
	public void setArtefacts(Set<Artefact> artefacts) {
		this.artefacts = artefacts;
	}		

	public ArtefactType getDeepCopy() {
		
		ArtefactType artefactType = new ArtefactType();
		artefactType.setName(getName());
		artefactType.setDescription(getDescription());
		artefactType.setArtefacts(getArtefacts());
		
		return artefactType;
	}	
}

