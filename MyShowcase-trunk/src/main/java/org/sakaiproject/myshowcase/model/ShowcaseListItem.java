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


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


	public class ShowcaseListItem implements Serializable{
		
		private static final long serialVersionUID = 1L;
		
		private String showcaseTitle;
		
		private String showcaseDesc;
		
		private Long showcaseId;
		
		private String illustration;
		
		private String publishedDate;
		
		private Long publishedDateTime;
		
		private String reviewStartDate;
		
		private Long reviewStartDateTime;
		
		private String reviewEndDate;
		
		private Long reviewEndDateTime;
		
		private int count;
		
        private List<ShowcaseReviewersListItem> showcaseReviewers = new ArrayList<ShowcaseReviewersListItem>();	
        
        
		/**
		  * Default constructor
		  */
		public ShowcaseListItem(){
		}

		
		public String getShowcaseTitle(){
			
			return showcaseTitle;
		}

		
		public void setShowcaseTitle(String showcaseTitle){
			
			this.showcaseTitle = showcaseTitle;
		}

		
		public Long getShowcaseId(){
			
			return showcaseId;
		}

		
		public void setShowcaseId(Long showcaseId){
			
			this.showcaseId = showcaseId;
		}	
		
		
		public String getShowcaseDesc(){
			
			return showcaseDesc;
		}

		
		public void setShowcaseDesc(String showcaseDesc){
			
			this.showcaseDesc = showcaseDesc;
		}
		
		
		public String getIllustration(){
			
			return illustration;
		}

		
		public void setIllustration(String illustration){
			
			this.illustration = illustration;
		}
		
		
		public String getPublishedDate(){
			
			return publishedDate;
		}

		
		public void setPublisheDate(String publishedDate){
			
			this.publishedDate = publishedDate;
		}	
	
		
		public Long getPublishedDateTime(){
			
			return publishedDateTime;
		}

		
		public void setPublishedDateTime(Date publishedDate){
			
			this.publishedDateTime = publishedDate.getTime();
		}

		
		public String getReviewStartDate(){
			
			return reviewStartDate;
		}
	
		
		public void setReviewStartDate(String reviewStartDate){
			
			this.reviewStartDate = reviewStartDate;
		}	
	
		
		public Long getReviewStartDateTime(){
			
			return reviewStartDateTime;
		}

		
		public void setReviewStartDateTime(Date reviewStartDate){
			
			if (reviewStartDate == null)
				
				this.reviewStartDateTime = new Long(0);
			
			else
				
				this.reviewStartDateTime = reviewStartDate.getTime();
		}
	
		
		public String getReviewEndDate(){
			
			return reviewEndDate;
		}

		
		public void setReviewEndDate(String reviewEndDate){
			
			this.reviewEndDate = reviewEndDate;
		}	
		
		public Long getReviewEndDateTime(){
			
			return reviewEndDateTime;
		}

		public void setReviewEndDateTime(Date reviewEndDate){
			
			if (reviewEndDate == null)
				this.reviewEndDateTime = new Long(0);
			else
				this.reviewEndDateTime = reviewEndDate.getTime();
		}
		
		public int getCount(){
			
			return count;
		}

		
		public void setCount(int count){
			
			this.count = count;
		}

		
		public List<ShowcaseReviewersListItem> getShowcaseReviewers(){
			
			return this.showcaseReviewers;
		}

		
		public void setShowcaseReviewers(List<ShowcaseReviewersListItem> showcaseReviewers){
			
			this.showcaseReviewers = showcaseReviewers;
		}			

}