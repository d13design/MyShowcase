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
import java.util.HashSet;
import java.util.Set;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;


public class ShowcaseReviewerTest{
	
	final static String valueName = "name";	
	
	final static String newValueName = "nameNew";	
	
	final static String valueEmail = "email";	
	
	final static String newValueEmail = "emailNew";	
	
	final static Long valueShowcaseReviewerId = new Long("1");
	
	final static Long newValueShowcaseReviewerId = new Long("2");
	
	final static Date valueDate  = new Date();
	
	Set<ShowcaseReviewer> showcaseReviewers = new HashSet<ShowcaseReviewer>();	
	
	ShowcaseReviewer showcaseReviewer;
	
	Reviewer reviewer;
	
	Showcase showcase;

	
	
	@Before
	public void instantiate(){
		
		showcase = new Showcase();
		
		reviewer = new Reviewer();
		
		showcaseReviewer = new ShowcaseReviewer();
		
		showcaseReviewer.setShowcase(showcase);
		
		showcaseReviewer.setReviewer(reviewer);
		
		showcaseReviewer.setShowcaseReviewerId(valueShowcaseReviewerId);
		
		showcaseReviewer.setReviewedDate(valueDate);
	}

	
	@Test
	public void getShowcaseTest(){
		
		assertSame("Model:Test that showcaseReviewer.showcase is correctly read.", showcase, showcaseReviewer.getShowcase());
	}		

	
	@Test
	public void getReviewerTest(){
		
		assertSame("Model:Test that showcaseReviewer.reviewer is correctly read.", reviewer, showcaseReviewer.getReviewer());
	}
	
	@Test
	public void getReviewedDate(){
		
		assertSame("Model:Test that showcaseReviewer.reviewedDate is correctly read.", valueDate, showcaseReviewer.getReviewedDate());
	}	

	
	@Test
	public void setShowcaseReviewerIdTest(){
		
		showcaseReviewer.setShowcaseReviewerId(newValueShowcaseReviewerId);
	
		assertEquals("Model:Test that showcaseReviewer.ShowcaseReviewerId is correctly set.", newValueShowcaseReviewerId, showcaseReviewer.getShowcaseReviewerId());
	}		

	
	@Test
	public void getShowcaseReviewerIdTest(){
	
		assertSame("Model:Test that showcaseReviewer.showcaseReviewerId are correctly read.", valueShowcaseReviewerId , showcaseReviewer.getShowcaseReviewerId());
	}
	
}
