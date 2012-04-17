
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


public class ArtefactTagTest{

	final static Long valueArtefactTagId = new Long("1");
	
	final static Long newValueArtefactTagId = new Long("2");
	
	Artefact artefact;
	
	Tag tag;
	
	ArtefactTag artefactTag;
	
	
	@Before
	public void instantiate(){
		
		artefactTag = new ArtefactTag();
		
		artefact = new Artefact();
		
		tag = new Tag();
		
		artefactTag.setArtefact(artefact);
		
		artefactTag.setTag(tag);
		
		artefactTag.setArtefactTagId(valueArtefactTagId);

	}

	
	@Test
	public void getArtefactTagIdTest(){
		
		assertEquals("Model: Test that artefactTag.artefactTagId is correctly read.", valueArtefactTagId, artefactTag.getArtefactTagId());
	}

	
	@Test
	public void setArtefactTagIdTest(){
		
		artefactTag.setArtefactTagId(newValueArtefactTagId);
	
		assertEquals("Model:Test that artefactTag.artefactTagId is correctly set.", newValueArtefactTagId, artefactTag.getArtefactTagId());
	}		

	
	@Test
	public void getTagTest(){
		
		assertSame("Model:Test that artefactTag.tag is correctly read.", tag, artefactTag.getTag());
	}	

	
	@Test
	public void getArtefactTest(){
		
		assertSame("Model:Test that artefactTag.artefact is correctly read.", artefact, artefactTag.getArtefact());
	}	
	
}