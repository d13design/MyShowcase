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


import java.util.HashSet;
import java.util.Set;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;


public class ShowcaseThemeTest{
	
	final static String valueName = "name";	
	
	final static String newValueName = "nameNew";	
	
	final static String valueUsageDesc = "usageDesc";	
	
	final static String newValueUsageDesc = "usageDescNew";
	
	final static String valueIllustration = "illustration";	
	
	final static String newValueIllustration = "illustrationNew";	
	
	final static String valueView = "view";	
	
	final static String newValueView = ("viewNew");

	final static Long valueShowcaseThemeId = new Long("1");
	
	final static Long newValueShowcaseThemeId = new Long("2");
	
	Set<Showcase> showcases = new HashSet<Showcase>();
	
	Artefact artefact;
	
	ShowcaseTheme showcaseTheme;

	
	@Before
	public void instantiate(){
		
		showcaseTheme = new ShowcaseTheme();
		
		showcaseTheme.setShowcaseThemeId(valueShowcaseThemeId);
		
		showcaseTheme.setName(valueName);
		
		showcaseTheme.setUsageDesc(valueUsageDesc);
		
		showcaseTheme.setIllustration(valueIllustration);
		
		showcaseTheme.setView(valueView);
		
		artefact = new Artefact();
		
		Showcase showcase = new Showcase();
		
		showcases.add(showcase);
		
		showcaseTheme.setShowcases(showcases);		
	}

	
	@Test
	public void getUsageDescTest(){
		
		assertEquals("Model:Test that showcaseTheme.detail is correctly read.", valueUsageDesc, showcaseTheme.getUsageDesc());
	}	

	
	@Test
	public void setUsageDescTest(){
		
		showcaseTheme.setUsageDesc(newValueUsageDesc);
	
		assertEquals("Model:Test that showcaseTheme.detail is correctly set.", newValueUsageDesc, showcaseTheme.getUsageDesc());
	}		

	
	@Test
	public void getViewTest(){
		
		assertEquals("Model:Test that showcaseTheme.view is correctly read.", valueView, showcaseTheme.getView());
	}	

	
	@Test
	public void setViewTest(){
		
		showcaseTheme.setView(newValueView);
	
		assertEquals("Model:Test that showcaseTheme.view is correctly set.", newValueView, showcaseTheme.getView());
	}	

	
	@Test
	public void getIllustrationTest(){
		
		assertEquals("Model:Test that showcaseTheme.illustration is correctly read.", valueIllustration, showcaseTheme.getIllustration());
	}	

	
	@Test
	public void setIllustrationTest(){
		
		showcaseTheme.setIllustration(newValueIllustration);
	
		assertEquals("Model:Test that showcaseTheme.view is correctly set.", newValueIllustration, showcaseTheme.getIllustration());
	}		

	
	@Test
	public void getNameTest(){
		
		assertEquals("Model:Test that showcaseTheme.userName is correctly read.", valueName, showcaseTheme.getName());
	}	

	
	@Test
	public void setNameTest(){
		
		showcaseTheme.setName(newValueName);
	
		assertEquals("Model:Test that showcaseTheme.userName is correctly set.", newValueName, showcaseTheme.getName());
	}		

	
	@Test
	public void getShowcaseThemeIdTest(){
		
		assertEquals("Model: Test that showcaseTheme.showcaseThemeId is correctly read.", valueShowcaseThemeId, showcaseTheme.getShowcaseThemeId());
	}

	
	@Test
	public void setShowcaseThemeIdTest(){
		
		showcaseTheme.setShowcaseThemeId(newValueShowcaseThemeId);
	
		assertEquals("Model:Test that showcaseTheme.showcaseThemeId is correctly set.", newValueShowcaseThemeId, showcaseTheme.getShowcaseThemeId());
	}	

	
	@Test
	public void getShowcasesTest() {
	
		assertSame("Model:Test that showcaseTheme.showcases are correctly read.", showcases , showcaseTheme.getShowcases());
	}	

	
}
