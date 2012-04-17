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

import java.net.URL;
import java.util.Iterator;
import java.util.List;

import com.sun.syndication.feed.synd.*;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class RssFeed {

	private URL feedURL ;
	private String feedName ;
    private List feedItems ;
    private SyndEntry feedItem ;
    private Iterator<SyndEntry> iFeedItems ;

	private SyndFeedInput sfi ;
	private SyndFeed sf ;
	private XmlReader xmlReader ;
	
	public RssFeed() {
		this.setFeedName("http://rss.cnn.com/rss/cnn_world.rss") ;

		try {

			this.readRSSDocument() ;

		}
		catch (Exception e) {

			feedItems = null ;

		}

	}

	public RssFeed(String fn) {
		this.setFeedName(fn) ;
		try {
			this.readRSSDocument() ;
		}
		catch (Exception e) {

			feedItems = null ;
		}
	}

	public List getFeedItems () {
		return feedItems;
	}

	public String getFeedName() {
		return feedName ;
	}
	
	public void setFeedName(String fn) {
		feedName = fn ;
	}
	
	public String getTitle() {
		return feedItem.getTitle().toString() ;
	}
	
	public String getLink() {
		return feedItem.getLink().toString() ;
	}
	
	public String getDescription() {
		return feedItem.getDescription().toString() ;
	}

	public String getGUID() {
		return feedItem.getUri().toString() ;
	}
	
	public boolean hasNext() {
		return iFeedItems.hasNext() ;
	}
	
	public void getNext() {
		if (iFeedItems.hasNext())
			feedItem = (SyndEntry)iFeedItems.next() ;
		else
			feedItem = null ;
	}
	
	public void readRSSDocument() throws Exception{
	    
		feedURL = new URL(feedName) ;
		sfi = new SyndFeedInput() ;
		xmlReader = new XmlReader(feedURL) ;
		sf = sfi.build(xmlReader) ;
		
		feedItems = sf.getEntries() ;
	    if (feedItems != null && !feedItems.isEmpty())
	    	iFeedItems = feedItems.iterator() ;
	    else
	    	iFeedItems = null ;
	    
	}	
	
	public static void main(String[] args) {
		try {
			RssFeed r = new RssFeed("http://newsrss.bbc.co.uk/rss/newsonline_uk_edition/front_page/rss.xml") ;
			r.readRSSDocument() ;

  			int c ;
 
			c = 0 ;
			if (r.hasNext()) {
				while (r.hasNext()) {

					r.getNext() ;

					c++ ;
				}
			}

		}
		catch (Exception e) {
			System.out.println(e.toString()) ;
		}
	}

}
