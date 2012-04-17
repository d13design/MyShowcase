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
import static org.junit.Assert.assertSame;


public class ArtefactMappingTest{

	final static Long valueArtefactMappingId = new Long("1");
	
	final static Long newValueArtefactMappingId = new Long("2");
	
	final static Long valueCompetencyId = new Long("3");
	
	final static Long newValueCompetencyId = new Long("4");
	
	final static Long valueMappingId = new Long("5");
	
	final static Long newValueMappingId = new Long("6");
	
	Artefact artefact;
	
	ArtefactMapping artefactMapping;
	
	
	@Before
	public void instantiate(){
		
		artefactMapping = new ArtefactMapping();
		
		artefact = new Artefact();
		
		artefactMapping.setArtefact(artefact);
		
		artefactMapping.setArtefactMappingId(valueArtefactMappingId);
		
		artefactMapping.setCompetencyId(valueCompetencyId);
		
		artefactMapping.setMappingId(valueMappingId);

	}

	
	@Test
	public void getArtefactMappingIdTest(){
		
		assertEquals("Model: Test that artefactMapping.artefactMappingId is correctly read.", valueArtefactMappingId, artefactMapping.getArtefactMappingId());
	}

	
	@Test
	public void setArtefactMappingIdTest(){
		
		artefactMapping.setArtefactMappingId(newValueArtefactMappingId);
	
		assertEquals("Model:Test that artefactMapping.artefactMappingId is correctly set.", newValueArtefactMappingId, artefactMapping.getArtefactMappingId());
	}		

	
	@Test
	public void getCompetencyIdTest(){
		
		assertEquals("Model: Test that artefactMapping.competencyId is correctly read.", valueCompetencyId, artefactMapping.getCompetencyId());
	}

	
	@Test
	public void setCompetencyTest(){
		
		artefactMapping.setCompetencyId(newValueCompetencyId);
	
		assertEquals("Model:Test that artefactMapping.competencyId is correctly set.", newValueCompetencyId, artefactMapping.getCompetencyId());
	}

	
	@Test
	public void getMappingIdTest(){
		
		assertEquals("Model: Test that artefactMapping.mappingId is correctly read.", valueMappingId, artefactMapping.getMappingId());
	}

	
	@Test
	public void setMappingTest(){
		
		artefactMapping.setMappingId(newValueMappingId);
	
		assertEquals("Model:Test that artefactMapping.mappingId is correctly set.", newValueMappingId, artefactMapping.getMappingId());
	}	

	
	@Test
	public void getArtefactTest(){
		
		assertSame("Model:Test that artefactMapping.artefact is correctly read.", artefact, artefactMapping.getArtefact());
	}	
	
}