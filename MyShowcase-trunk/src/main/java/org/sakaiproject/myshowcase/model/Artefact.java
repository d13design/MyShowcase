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

public class Artefact extends Audited {
	
	private static final long serialVersionUID = 1L;
	
	private Long artefactId;
	
	private String name;
	
	private boolean locked; 
	
	private String shortDesc;
	
	private String description; 
	
	private Owner owner;
	
	private ArtefactType type;
	
	private Showcase showcase;
	
	private ArtefactDetail artefactDetail;
	
	private Set<ArtefactTag> artefactTags = new HashSet<ArtefactTag>();
	
	private Set<ArtefactMapping> artefactMappings = new HashSet<ArtefactMapping>();

	private Long savedArtefactId;

	
	/**
	  * Default constructor
	  */
    public Artefact() {
	}

    
	/**
	  * Minimal constructor
	  */
	public Artefact(String name,
			        String shortDesc,
			        String description,
			        Owner owner,
			        ArtefactType type){
		
		this.name = name;
		
		this.shortDesc = shortDesc;
		
		this.description = description;
		
		this.owner = owner;
		
		this.type = type;
		
	}

	
	/**
	 * Full constructor
	 */
	public Artefact(String name,
			        String shortDesc,
			        String description,
			        Owner owner,
			        ArtefactType type,
			        boolean locked,
			        Showcase showcase,
			        Date createdDate){
		
		this.name = name;
		
		this.shortDesc = shortDesc;
		
		this.description = description;
		
		this.locked = locked;	
		
		this.owner = owner;
		
		this.type = type;
		
		this.showcase = showcase;
		
		this.createdDate = createdDate;
		
		this.createdDateTime = createdDate.getTime() ;
	}

	/**
	 * Getters and Setters
	 */

	public Long getArtefactId(){
		
		return artefactId;
	}

	
	public void setArtefactId(Long artefactId){
		
		this.artefactId = artefactId;
	}

	
	public Long getSavedArtefactId(){
		
		return savedArtefactId;
	}

	
	public void setSavedArtefactId(Long savedArtefactId){
		
		this.savedArtefactId = savedArtefactId;
	}

	
	public String getName(){
		
		return name;
	}

	
	public void setName(String name){
		
		this.name = name;
	}

	
	public String getDescription(){
		
		return description;
	}

	
	public void setDescription(String description){
		
		this.description = description;
    }

	
	public String getShortDesc(){
		
		return shortDesc;
	}

	
	public void setShortDesc(String shortDesc){
		
		this.shortDesc = shortDesc;
    }

	
	public boolean getLocked(){
		
		return locked;
	}

	
	public void setLocked(boolean locked){
		
		this.locked = locked;
    }

	
	public Owner getOwner(){
		
		return owner;
	}

	
	public void setOwner(Owner owner){
		
		this.owner = owner;
    }

	
	public Showcase getShowcase(){
		
		return showcase;
	}

	
	public void setShowcase(Showcase showcase){
		
		this.showcase = showcase;
    }

	
	public ArtefactType getType(){
		
		return type;
	}

	
	public void setType(ArtefactType type){
		
		this.type = type;
    }

	
	public ArtefactDetail getArtefactDetail(){
		
		return artefactDetail;
	}

	
	public void setArtefactDetail(ArtefactDetail artefactDetail){
		
		this.artefactDetail = artefactDetail;
    }	

	
	public Set<ArtefactTag> getArtefactTags(){
		
		return this.artefactTags;
	}

	
	public void setArtefactTags(Set<ArtefactTag> artefactTags){
		
		this.artefactTags = artefactTags;
	}	

	
	public Set<ArtefactMapping> getArtefactMappings(){
		
		return this.artefactMappings;
	}

	
	public void setArtefactMappings(Set<ArtefactMapping> artefactMappings){
		
		this.artefactMappings = artefactMappings;
	}	

	
	public Artefact getDeepCopy() {
		
		Artefact artefact = new Artefact();
		
		artefact.setSavedArtefactId(getArtefactId());
		
		artefact.setName(getName());

		artefact.setType(getType());
		
		artefact.setDescription(getDescription());
		
		artefact.setLocked(getLocked());
		
		artefact.setShortDesc(getShortDesc());
		
		artefact.setArtefactDetail(getArtefactDetail().getDeepCopy());
		
		return artefact;
	}	
	
}

