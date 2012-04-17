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


public class TagTest {
	
	final static String valueName = "name";	
	
	final static String newValueName = "nameNew";	
	
	final static Long valueTagId = new Long("1");
	
	final static Long newValueTagId = new Long("2");
	
	Set<ArtefactTag> artefactTags = new HashSet<ArtefactTag>();	
	
	Tag tag;

	
	@Before
	public void instantiate() {
		
		tag = new Tag();
		
		tag.setName(valueName);
		
		tag.setTagId(valueTagId);
		
		ArtefactTag artefactTag = new ArtefactTag();
		
		artefactTags.add(artefactTag);
		
		tag.setArtefactTags(artefactTags);		
	}

	
	@Test
	public void getTagIdByTest() {
		assertEquals("Model: Test that tag.tagId is correctly read.", valueTagId, tag.getTagId());
	}	

	
	@Test
	public void getNameTest() {
		assertSame("Model:Test that tag.name is correctly read.", valueName, tag.getName());
	}	

	
	@Test
	public void setNameTest() {
		
		tag.setName(newValueName);
	
		assertEquals("Model:Test that tag.name is correctly set.", newValueName, tag.getName());
	}		

	
	@Test
	public void setTagIdTest() {
		
		tag.setTagId(newValueTagId);
	
		assertEquals("Model:Test that tag.tagId is correctly set.", newValueTagId, tag.getTagId());
	}		

	
	@Test
	public void getArtefactTagsTest() {
	
		assertSame("Model:Test that tag.artefactTags are correctly read.", artefactTags ,tag.getArtefactTags());
	}	
	
}