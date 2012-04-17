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


public class ArtefactTest{
	
	final static String valueName = "name";	
	
	final static String newValueName = "nameNew";	
	
	final static String valueDescription = "description";	
	
	final static String newValueDescription = "descriptionNew";	
	
	final static String valueShortDesc = "shortDesc";	
	
	final static String newValueShortDesc = "shortDescNew";
	
	final static boolean valueLocked = true;
	
	final static boolean newValueLocked = false;

	final static Long valueArtefactId = new Long("1");
	
	final static Long newValueArtefactId = new Long("2");	
	Owner owner;
	
	Showcase showcase;
	
	Artefact artefact;
	
	Set<ArtefactTag> artefactTags = new HashSet<ArtefactTag>();
	
	Set<ArtefactMapping> artefactMappings = new HashSet<ArtefactMapping>();
	
	
	@Before
	public void instantiate(){
		
		artefact = new Artefact();
		
		owner = new Owner();
		
		showcase = new Showcase();
		
		ArtefactMapping artefactMapping = new ArtefactMapping();
		
		ArtefactTag artefactTag = new ArtefactTag();
		
		artefact.setArtefactId(valueArtefactId);		
		
		artefactTags.add(artefactTag);
		
		artefactMappings.add(artefactMapping);
		
		artefact.setArtefactMappings(artefactMappings);
		
		artefact.setArtefactTags(artefactTags);
		
		artefact.setName(valueName);
		
		artefact.setDescription(valueDescription);
		
		artefact.setShortDesc(valueShortDesc);
		
		artefact.setOwner(owner);
		
		artefact.setShowcase(showcase);
		
		artefact.setLocked(valueLocked);
	}

	
	@Test
	public void getDescriptionTest(){
		
		assertEquals("Model:Test that artefact.description is correctly read.", valueDescription, artefact.getDescription());
	}	

	
	@Test
	public void setDescriptionTest(){
		
		artefact.setDescription(newValueDescription);
	
		assertEquals("Model:Test that artefact.description is correctly set.", newValueDescription, artefact.getDescription());
	}		

	
	@Test
	public void getShortDescTest(){
		
		assertEquals("Model:Test that artefact.shortDesc is correctly read.", valueShortDesc, artefact.getShortDesc());
	}	

	
	@Test
	public void setShortDescTest(){
		
		artefact.setShortDesc(newValueShortDesc);
	
		assertEquals("Model:Test that artefact.shortDesc is correctly set.", newValueShortDesc, artefact.getShortDesc());
	}	

	
	@Test
	public void getNameTest(){
		
		assertEquals("Model:Test that artefact.name is correctly read.", valueName, artefact.getName());
	}	

	
	@Test
	public void setLockedTest(){
		
		artefact.setLocked(newValueLocked);
	
		assertEquals("Model:Test that artefact.locked is correctly set.", newValueLocked, artefact.getLocked());
	}		

	
	@Test
	public void getLockedTest(){
		
		assertEquals("Model:Test that artefact.locked is correctly read.", valueLocked, artefact.getLocked());
	}	

	
	@Test
	public void setNameTest(){
		
		artefact.setName(newValueName);
	
		assertEquals("Model:Test that artefact.name is correctly set.", newValueName, artefact.getName());
	}		
	
	@Test
	public void getOwnerTest(){
		
		assertSame("Model:Test that artefact.owner is correctly read.", owner, artefact.getOwner());
	}		

	
	@Test
	public void getShowcaseTest(){
		
		assertSame("Model:Test that artefact.showcase is correctly read.", showcase, artefact.getShowcase());
	}

	
	@Test
	public void getArtefactTagsTest() {
	
		assertSame("Model:Test that artefact.artefactTags are correctly read.", artefactTags , artefact.getArtefactTags());
	}	

	
	@Test
	public void getArtefactMappingsTest() {
	
		assertSame("Model:Test that artefact.artefactMappings are correctly read.", artefactMappings , artefact.getArtefactMappings());
	}

	
	@Test
	public void getArtefactIdTest(){
		
		assertEquals("Model: Test that artefact.artefactId is correctly read.", valueArtefactId, artefact.getArtefactId());
	}

	
	@Test
	public void setArtefactIdTest(){
		
		artefact.setArtefactId(newValueArtefactId);
	
		assertEquals("Model:Test that artefact.artefactId is correctly set.", newValueArtefactId, artefact.getArtefactId());
	}		
	
}
