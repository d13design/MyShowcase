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


public class Tag extends Audited{
	private static final long serialVersionUID = 1L;	
	private Long tagId;
	private String name;
	private int searchCount;
	private Owner owner;
	private Set<ArtefactTag> artefactTags = new HashSet<ArtefactTag>();
	
	
	/**
	  * Default constructor
	  */
	public Tag() {
	}

	
	/**
	  * Minimal constructor
	  */
	public Tag(String name,
			   Owner owner,
			   int searchCount ){
		
		this.name = name;
		
		this.owner = owner;
		
		this.searchCount = searchCount;
	}

	
	/**
	  * Full constructor
	  */
	public Tag(String name,
			   int searchCount,
			   Owner owner,
               Date createdDate,
	           String createdBy){
		
		this.name = name;
		
		this.searchCount = searchCount;
		
		this.owner = owner;
		
		this.createdDate = createdDate;
		
		this.createdBy = createdBy;
	}

	
	/**
	  * Getters and Setters
	  */

	
	public Long getTagId(){
		
		return tagId;
	}

	
	public void setTagId(Long tagId){
		
		this.tagId = tagId;
	}

	
	public String getName(){
		
		return name;
	}

	
	public void setName(String name){
		
		this.name = name;
	}

	
	public int getSearchCount(){
		
		return searchCount;
	}

	
	public void setSearchCount(int searchCount){
		
		this.searchCount = searchCount;
	}	

	
	public Set<ArtefactTag> getArtefactTags(){
		
		return this.artefactTags;
	}

	
	public void setArtefactTags(Set<ArtefactTag> artefactTags){
		
		this.artefactTags = artefactTags;
	}

	
	public Owner getOwner(){
		
		return owner;
	}

	
	public void setOwner(Owner owner){
		
		this.owner = owner;
    }	

	
	public Tag getDeepCopy(){
		
		Tag tag= new Tag();
		
		tag.setName(getName());
		
		tag.setSearchCount(getSearchCount());
	
		return tag;
	}		
}

