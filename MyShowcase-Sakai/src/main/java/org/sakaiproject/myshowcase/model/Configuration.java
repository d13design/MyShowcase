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


public class Configuration extends Audited {
	
	private static final long serialVersionUID = 1L;	

	private String fileStoreAddress;
	
	private String uploadDir;
	
	private String parent;
	
	private String loginGreeting;

	
	/**
	  * Default constructor
	  */
    public Configuration(){
    	
	}
 
    
	/**
	  * Getters and Setters
	 */
	
	public String getFileStoreAddress(){
		
		return this.fileStoreAddress;
	}

	
	public void setFileStoreAddress(String fileStoreAddress){
		
		this.fileStoreAddress = fileStoreAddress;
	}

	
	public String getUploadDir(){
		
		return this.uploadDir;
	}
	
	public void setUploadDir(String uploadDir){
		
		this.uploadDir = uploadDir;
	}

	
	public String getParent(){
		
		return this.parent;
	}

	
	public void setParent(String parent){
		
		this.parent = parent;
	}

	
	public String getLoginGreeting(){
		
		return this.loginGreeting;
	}

	
	public void setLoginGreeting(String loginGreeting){
		
		this.loginGreeting = loginGreeting;
	}	
}