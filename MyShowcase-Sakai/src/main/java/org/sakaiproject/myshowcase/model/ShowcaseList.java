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
import java.util.Date;


	public class ShowcaseList implements Serializable  {
		
		private static final long serialVersionUID = 1L;
		private String showcaseTitle;
		private String showcaseDesc;
		private Long showcaseId;
		private String illustration;
		private String publishedDate;
		private Long publishedDateTime ;
		private int count;

		
		/**
		 * Default constructor
		 */
		public ShowcaseList() {
		}

		
		public String getShowcaseTitle() {
			return showcaseTitle;
		}

		
		public void setShowcaseTitle(String showcaseTitle) {
			this.showcaseTitle = showcaseTitle;
		}

		
		public Long getShowcaseId() {
			return showcaseId;
		}

		
		public void setShowcaseId(Long showcaseId) {
			this.showcaseId = showcaseId;
		}	
		
		
		public String getShowcaseDescd() {
			return showcaseDesc;
		}

		
		public void setShowcaseList(String showcaseDesc) {
			this.showcaseDesc = showcaseDesc;
		}
		
		
		public String getIllustration() {
			return illustration;
		}

		
		public void setIllustration(String illustration) {
			this.illustration = illustration;
		}
		
		
		public String getPublishedDate() {
			return publishedDate;
		}

		
		public void setPublisheDate(String publishedDate) {
			this.publishedDate = publishedDate;
		}	
		
		public int getCount() {
			return count;
		}

		
		public void setCount(int count) {
			this.count = count;
		}

		public Long getPublishedDateTime() {
			return publishedDateTime;
		}

		public void setPublishedDateTime(Date publishedDate) {
			this.publishedDateTime = publishedDate.getTime();
		}
		

}
