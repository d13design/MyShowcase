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


public class ReviewerTest{
	
	final static String valueName = "name";	
	
	final static String newValueName = "nameNew";	
	
	final static String valueEmail = "email";	
	
	final static String newValueEmail = "emailNew";	
	
	final static Long valueReviewerId = new Long("1");
	
	final static Long newValueReviewerId = new Long("2");
	
	Set<ShowcaseReviewer> showcaseReviewers = new HashSet<ShowcaseReviewer>();	
	
	Reviewer reviewer;
	
	Owner owner;

	
	@Before
	public void instantiate(){
		
		owner = new Owner();
		
		reviewer = new Reviewer();
		
		reviewer.setName(valueName);
		
		reviewer.setReviewerId(valueReviewerId);
		
		reviewer.setEmail(valueEmail);
		
		ShowcaseReviewer showcaseReviewer = new ShowcaseReviewer();
		
		showcaseReviewers.add(showcaseReviewer);
		
		reviewer.setShowcaseReviewers(showcaseReviewers);
		
		reviewer.setOwner(owner);
	}

	
	@Test
	public void getReviewerIdByTest(){
		
		assertEquals("Model: Test that reviewer.reviewerId is correctly read.", valueReviewerId, reviewer.getReviewerId());
	}	

	
	@Test
	public void getNameTest(){
		
		assertSame("Model:Test that reviewer.name is correctly read.", valueName, reviewer.getName());
	}	

	
	@Test
	public void setNameTest(){
		
		reviewer.setName(newValueName);
	
		assertEquals("Model:Test that reviewer.name is correctly set.", newValueName, reviewer.getName());
	}		

	
	@Test
	public void getEmailTest(){
		assertSame("Model:Test that reviewer.email is correctly read.", valueEmail, reviewer.getEmail());
	}	

	
	@Test
	public void setEmailTest(){
		
		reviewer.setEmail(newValueEmail);
	
		assertEquals("Model:Test that reviewer.email is correctly set.", newValueEmail, reviewer.getEmail());
	}		


	
	@Test
	public void getOwnerTest() {
		assertSame("Model:Test that reviewer.owner is correctly read.", owner, reviewer.getOwner());
	}		

	
	@Test
	public void setReviewerIdTest(){
		
		reviewer.setReviewerId(newValueReviewerId);
	
		assertEquals("Model:Test that reviewer.reviewerId is correctly set.", newValueReviewerId, reviewer.getReviewerId());
	}		

	
	@Test
	public void getShowcaseREviewersTest(){
	
		assertSame("Model:Test that reviewer.showcaseReviewers are correctly read.", showcaseReviewers ,reviewer.getShowcaseReviewers());
	}	
	
}
