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

package org.sakaiproject.myshowcase.logic;

import java.util.ArrayList;
import java.util.List;

public class Validation {
	
	private static final long serialVersionUID = 1L;	
	boolean valid = true;
	List<String> messages = new ArrayList<String>();
	int numberOfMessages  = 0;

	/**
	 * Default constructor
	 */
    public Validation(){
	}

	/**
	 * Getters and Setters
	 */

	public List<String> getMessages(){
		
		return messages;
	}

	
	public int getNumberOfMessages(){
		
		numberOfMessages = getMessages().size();
		
		return numberOfMessages;
	}

	
	public void addMessage(String message){
		
		getMessages().add(message);
	}

	
	public boolean isValid(){
		
		return valid;
	}

	
	public void setValid(boolean valid){
		
		this.valid = valid;
	}
	
}

