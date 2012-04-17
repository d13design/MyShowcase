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


import org.junit.*;
import static org.junit.Assert.assertEquals;


public class ArtefactTypeListTest{
	
	final static String valueName = "name";	
	
	final static String newValueName = "nameNew";	
	
	final static int valueCount = 1;	
	
	final static int newValueCount = 2;	
	
	ArtefactTypeList artefactTypeList;

	
	@Before
	public void instantiate(){
		
		artefactTypeList = new ArtefactTypeList();
		
		artefactTypeList.setName(valueName);
		
		artefactTypeList.setCount(valueCount);
	}

	
	@Test
	public void getCountTest(){
		
		assertEquals("Model:Test that artefactTypeList.count is correctly read.", valueCount, artefactTypeList.getCount());
	}	

	
	@Test
	public void setCountTest(){
		
		artefactTypeList.setCount(newValueCount);
	
		assertEquals("Model:Test that artefactTypeList.count is correctly set.", newValueCount, artefactTypeList.getCount());
	}		

	
	@Test
	public void getNameTest(){
		
		assertEquals("Model:Test that artefactTypeList.name is correctly read.", valueName, artefactTypeList.getName());
	}	

	
	@Test
	public void setNameTest(){
		
		artefactTypeList.setName(newValueName);
	
		assertEquals("Model:Test that artefactTypeList.name is correctly set.", newValueName, artefactTypeList.getName());
	}		
	

}
