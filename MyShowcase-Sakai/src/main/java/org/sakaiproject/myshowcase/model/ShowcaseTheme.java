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

public class ShowcaseTheme extends Audited {
	private static final long serialVersionUID = 1L;	
	private Long showcaseThemeId;
	private String name;
	private String usageDesc;
	private String illustration;	
	private String view;
	
	private Set<Showcase> showcases = new HashSet<Showcase>();
	
	/**
	 * Default constructor
	 */
	public ShowcaseTheme() {
	}

	
	/**
	 * Minimal constructor
	 */
	public ShowcaseTheme(String name) {
		
		this.name = name;
	}

	
	/**
	 * Full constructor
	 */
	public ShowcaseTheme(String name,
			        Date createdDate,
			        String createdBy) {
		
		this.name = name;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
	}

	
	/**
	 * Getters and Setters
	 */

	public Long getShowcaseThemeId() {
		return showcaseThemeId;
	}

	
	public void setShowcaseThemeId(Long showcaseThemeId) {
		this.showcaseThemeId = showcaseThemeId;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getUsageDesc() {
		return usageDesc;
	}

	
	public void setUsageDesc(String usageDesc) {
		this.usageDesc = usageDesc;
	}


	
	public String getIllustration() {
		return illustration;
	}

	
	public void setIllustration(String illustration) {
		this.illustration = illustration;
	}	


	public String getView() {
		return view;
	}

	
	public void setView(String view) {
		this.view = view;
	}	
	
	
	public Set<Showcase> getShowcases() {
		return this.showcases;
	}

	
	public void setShowcases(Set<Showcase> showcases) {
		this.showcases = showcases;
	}		

}

