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

public class ArtefactDetail extends Audited{
	
	private static final long serialVersionUID = 1L;
	
	private Long artefactId;
	
	private String detail;
	
	private String url;
	
	private String filePath;
	
	private Long fileSize;
	
	private String fileType;
	
	private String fileName;
	
	private String flickrUserName;
	
	private String twitterUserName;
	
	private Artefact artefact;

	
	/**
	  * Default constructor
	  */
	public ArtefactDetail(){
		
	}

	
	/**
	  * Minimal constructor
	  */
	public ArtefactDetail(String detail){
		
		this.detail = detail;
	}

	
	/**
	  * Full constructor
	  */
	public ArtefactDetail(String detail,
			              Date createdDate,
			              String createdBy){
		
		this.detail = detail;
		
		this.createdDate = createdDate;
		
		this.createdBy = createdBy;
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

	
	public String getDetail(){
		
		return detail;
	}

	
	public void setDetail(String detail){
		
		this.detail = detail;
	}

	
	public String getUrl(){
		
		return url;
	}

	
	public void setUrl(String url){
		
		this.url = url;
	}

	
	public String getFilePath(){
		
		return filePath;
	}

	
	public void setFilePath(String filePath){
		
		this.filePath = filePath;
	}

	
	public Long getFileSize(){
		
		return fileSize;
	}

	
	public void setFileSize(Long fileSize){
		
		this.fileSize = fileSize;
	}

	
	public String getFileName(){
		
		return fileName;
	}

	
	public void setFileName(String fileName){
		
		this.fileName = fileName;
	}

	
	public String getFileType(){
		
		return fileType;
	}

	
	public void setFileType(String fileType){
		
		this.fileType = fileType;
	}

	
	public String getTwitterUserName(){
		
		return twitterUserName;
	}

	
	public void setTwitterUserName(String twitterUserName){
		
		this.twitterUserName = twitterUserName;
	}	

	
	public String getFlickrUserName(){
		
		return flickrUserName;
	}

	
	public void setFlickrUserName(String flickrUserName){
		
		this.flickrUserName = flickrUserName;
	}	

	
	public Artefact getArtefact(){
		
		return artefact;
	}

	
	public void setArtefact(Artefact artefact){
		
		this.artefact = artefact;
	}

	
	public ArtefactDetail getDeepCopy(){
		
		ArtefactDetail artefactDetail = new ArtefactDetail();
		
		artefactDetail.setDetail(getDetail());
		
		artefactDetail.setUrl(getUrl());
		
		artefactDetail.setFilePath(getFilePath());
		
		artefactDetail.setFileType(getFileType());
		
		artefactDetail.setFileName(getFileName());
		
		return artefactDetail;
	}		
}

