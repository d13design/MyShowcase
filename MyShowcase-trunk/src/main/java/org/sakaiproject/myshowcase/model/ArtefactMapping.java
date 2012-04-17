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

public class ArtefactMapping extends Audited{
	
	private static final long serialVersionUID = 1L;
	
	private Long artefactMappingId;
	
	private Long competencyId;
	
	private Long mappingId;
	
	private Artefact artefact;
	
	/**
	  * Default constructor
	  */
	public ArtefactMapping(){
	}

	
	/**
	  * Minimal constructor
	  */
	public ArtefactMapping(Artefact artefact,
							Long competencyId,
							Long mappingId){
		
		this.artefact = artefact;
		
		this.competencyId = competencyId;
		
		this.mappingId = mappingId;
	}

	
	/**
	  * Full constructor
	  */
	public ArtefactMapping(Artefact artefact,
							Long competencyId,
							Long mappingId,
			                Date createdDate,
			                String createdBy){
		
		this.artefact = artefact;
		
		this.competencyId = competencyId;
		
		this.mappingId = mappingId;
		
		this.createdDate = createdDate;
		
		this.createdBy = createdBy;
	}

	
	/**
	 * Getters and Setters
	 */

	public Long getArtefactMappingId(){
		
		return artefactMappingId;
	}

	
	public void setArtefactMappingId(Long artefactMappingId){
		
		this.artefactMappingId = artefactMappingId;
	}

	
	public Artefact getArtefact(){
		
		return artefact;
	}

	
	public void setArtefact(Artefact artefact){
		
		this.artefact = artefact;
	}

	
	public Long getCompetencyId(){
		
		return competencyId;
	}

	
	public void setCompetencyId(Long competencyId){
		
		this.competencyId = competencyId;
	}
	
	
	public Long getMappingId(){
		
		return mappingId;
	}

	
	public void setMappingId(Long mappingId){
		
		this.mappingId = mappingId;
	}
}

