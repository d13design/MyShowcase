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



	public class ShowcaseReviewersListItem implements Serializable  {
		
		private static final long serialVersionUID = 1L;
		private String name;
		private String email;
		private Long showcaseReviewerId;
		private Long reviewerId;
		private boolean hasViewed;

		
		/**
		 * Default constructor
		 */
		public ShowcaseReviewersListItem() {
		}

		
		public String getName() {
			return name;
		}

		
		public void setName(String name) {
			this.name = name;
		}

		
		public void setShowcaseReviewerId(Long showcaseReviewerId) {
			this.showcaseReviewerId = showcaseReviewerId;
		}	

		
		public Long getShowcaseReviewerId() {
			return showcaseReviewerId;
		}

		
		public void setReviewerId(Long reviewerId) {
			this.reviewerId = reviewerId;
		}	

		
		public Long getReviewerId() {
			return reviewerId;
		}
		
		public String getEmail() {
			return email;
		}

		
		public void setEmail(String email) {
			this.email = email;
		}

		
		public void setViewed(boolean hasViewed) {
			this.hasViewed = hasViewed;
		}		
		
		public boolean hasViewed() {
			return hasViewed;
		}
		
		
		public void hasViewed(boolean hasViewed) {
			 this.hasViewed = hasViewed;
		}

}
