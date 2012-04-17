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

package org.sakaiproject.myshowcase.tool;

public enum MyShowcaseCompetencyFrameworkCV {

	COMPETENCY_ID(1337),
	INTRODUCTION(1338),
	CONTACT_DETAILS(1339),
	EDUCATION(1340),
	EXPERIENCE(1341),
	ADDITIONAL_SKILLS(1342),
	HOBBIES_AND_INTERESTS(1343),
	FURTHER_INFO(1344);
	
	
    private final long identifier; 
 
    
    MyShowcaseCompetencyFrameworkCV(long identifier) {
        this.identifier = identifier;
    }
 
    
    public long identifier(){
    	return identifier;
   	}	

}
