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
//*FilePath: <myshowcase@myknowledgemap.com>                                   *
//*                                                                         *
//***************************************************************************

package org.sakaiproject.myshowcase.model;


import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;


public class ArtefactDetailTest{
	
	final static String valueUrl = "userName";	
	
	final static String newValueUrl = "userNameNew";	
	
	final static String valueDetail = "detail";	
	
	final static String newValueDetail = "detailNew";
	
	final static String valueFilePath = "filePath";	
	
	final static String newValueFilePath = "filePathNew";
	
	final static String valueFileType = "fileType";	
	
	final static String newValueFileType = "fileTypeNew";	
	
	final static String valueFileName = "fileName";	
	
	final static String newValueFileName = ("fileNameNew");
	
	final static String valueFlickrUserName = "flickrUserName";	
	
	final static String newValueFlickrUserName = ("flickrUserNameNew");
	
	final static String valueTwitterUserName = "twitterUserName";	
	
	final static String newValueTwitterUserName = ("twitterUserNameNew");	

	final static Long valueArtefactId = new Long("1");
	
	final static Long newValueArtefactId = new Long("2");
	
	Artefact artefact;
	
	ArtefactDetail artefactDetail;

	
	@Before
	public void instantiate(){
		
		artefactDetail = new ArtefactDetail();
		
		artefactDetail.setArtefactId(valueArtefactId);
		
		artefactDetail.setUrl(valueUrl);
		
		artefactDetail.setDetail(valueDetail);
		
		artefactDetail.setFileType(valueFileType);
		
		artefactDetail.setFilePath(valueFilePath);
		
		artefactDetail.setFileName(valueFileName);
		
		artefactDetail.setTwitterUserName(valueTwitterUserName);
		
		artefactDetail.setFlickrUserName(valueFlickrUserName);
		
		artefact = new Artefact();
		
		artefactDetail.setArtefact(artefact);
		
	}

	
	@Test
	public void getDetailTest(){
		
		assertEquals("Model:Test that artefactDetail.detail is correctly read.", valueDetail, artefactDetail.getDetail());
	}	

	
	@Test
	public void setDetailTest(){
		
		artefactDetail.setDetail(newValueDetail);
	
		assertEquals("Model:Test that artefactDetail.detail is correctly set.", newValueDetail, artefactDetail.getDetail());
	}		

	
	@Test
	public void getFileNameTest(){
		
		assertEquals("Model:Test that artefactDetail.fileName is correctly read.", valueFileName, artefactDetail.getFileName());
	}	

	
	@Test
	public void setFileNameTest(){
		
		artefactDetail.setFileName(newValueFileName);
	
		assertEquals("Model:Test that artefactDetail.fileName is correctly set.", newValueFileName, artefactDetail.getFileName());
	}	

	
	@Test
	public void getFileTypeTest(){
		
		assertEquals("Model:Test that artefactDetail.fileType is correctly read.", valueFileType, artefactDetail.getFileType());
	}	

	
	@Test
	public void setFileTypeTest(){
		
		artefactDetail.setFileType(newValueFileType);
	
		assertEquals("Model:Test that artefactDetail.fileName is correctly set.", newValueFileType, artefactDetail.getFileType());
	}		

	
	@Test
	public void getUrlTest(){
		
		assertEquals("Model:Test that artefactDetail.userName is correctly read.", valueUrl, artefactDetail.getUrl());
	}	

	
	@Test
	public void setUrlTest(){
		
		artefactDetail.setUrl(newValueUrl);
	
		assertEquals("Model:Test that artefactDetail.userName is correctly set.", newValueUrl, artefactDetail.getUrl());
	}		

	
	@Test
	public void getFilePathTest(){
		
		assertEquals("Model:Test that artefactDetail.filePath is correctly read.", valueFilePath, artefactDetail.getFilePath());
	}	

	
	@Test
	public void setFilePathTest(){
		
		artefactDetail.setFilePath(newValueFilePath);
	
		assertEquals("Model:Test that artefactDetail.filePath is correctly set.", newValueFilePath, artefactDetail.getFilePath());
	}		

	
	@Test
	public void getFlickrUserNameTest(){
		
		assertEquals("Model:Test that artefactDetail.flickrUserName is correctly read.", valueFlickrUserName, artefactDetail.getFlickrUserName());
	}	

	
	@Test
	public void setFlickrUserNameTest(){
		
		artefactDetail.setFlickrUserName(newValueFlickrUserName);
	
		assertEquals("Model:Test that artefactDetail.flickrUserName is correctly set.", newValueFlickrUserName, artefactDetail.getFlickrUserName());
	}		

	
	@Test
	public void getTwitterUserNameTest(){
		
		assertEquals("Model:Test that artefactDetail.twitterUserName is correctly read.", valueTwitterUserName, artefactDetail.getTwitterUserName());
	}	

	
	@Test
	public void setTwitterUserNameTest(){
		
		artefactDetail.setTwitterUserName(newValueTwitterUserName);
	
		assertEquals("Model:Test that artefactDetail.twitterUserName is correctly set.", newValueTwitterUserName, artefactDetail.getTwitterUserName());
	}

	
	@Test
	public void getArtefactIdTest(){
		
		assertEquals("Model: Test that artefactDetail.artefactId is correctly read.", valueArtefactId, artefactDetail.getArtefactId());
	}

	
	@Test
	public void setArtefactIdTest(){
		
		artefactDetail.setArtefactId(newValueArtefactId);
	
		assertEquals("Model:Test that artefactDetail.artefactId is correctly set.", newValueArtefactId, artefactDetail.getArtefactId());
	}	

	
	@Test
	public void getArtefactTest(){
		
		assertSame("Model:Test that artefactDetail.artefact is correctly read.", artefact, artefactDetail.getArtefact());
	}		
}
