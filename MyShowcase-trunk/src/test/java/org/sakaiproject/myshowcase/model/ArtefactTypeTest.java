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

import java.util.HashSet;
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;


public class ArtefactTypeTest{

	
	final static String valueName = "name";	
	
	final static String valueDescription = "description";
	
	final static String newValueName = "nameNew";	
	
	final static String newValueDescription = "descriptionNew";	
	
	final static Long valueArtefactTypeId = new Long("1");
	
	final static Long newValueArtefactTypeId = new Long("2");
	
	Set<Artefact> artefacts = new HashSet<Artefact>();	
	
	ArtefactType artefactType;

	
	@Before
	public void instantiate(){
		
		artefactType = new ArtefactType();
		
		artefactType.setName(valueName);
		
		artefactType.setArtefactTypeId(valueArtefactTypeId);
		
		artefactType.setDescription(valueDescription);
		
		Artefact artefact = new Artefact();
		
		artefacts.add(artefact);
		
		artefactType.setArtefacts(artefacts);		
	}

	
	@Test
	public void getArtefactTypeIdByTest(){
		
		assertEquals("Model: Test that artefactType.artefactTypeId is correctly read.", valueArtefactTypeId, artefactType.getArtefactTypeId());
	}	

	
	@Test
	public void getNameTest(){
		
		assertSame("Model:Test that artefactType.name is correctly read.", valueName, artefactType.getName());
	}	

	
	@Test
	public void setNameTest(){
		
		
		artefactType.setName(newValueName);
	
		assertEquals("Model:Test that artefactType.name is correctly set.", newValueName, artefactType.getName());
	}		

	
	@Test
	public void getDescriptionTest(){
		
		assertSame("Model:Test that artefactType.description is correctly read.", valueDescription, artefactType.getDescription());
	}	

	
	@Test
	public void setDescriptionTest(){
		
		artefactType.setDescription(newValueDescription);
	
		assertEquals("Model:Test that artefactType.desciption is correctly set.", newValueDescription, artefactType.getDescription());
	}		

	
	@Test
	public void getArtefactsTest(){
	
		assertSame("Model:Test that artefactType.artefacts are correctly read.", artefacts ,artefactType.getArtefacts());
	}	
	
	
}