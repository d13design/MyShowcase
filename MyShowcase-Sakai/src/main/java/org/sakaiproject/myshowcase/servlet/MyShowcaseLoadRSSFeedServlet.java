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

package org.sakaiproject.myshowcase.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sakaiproject.myshowcase.model.RssFeed;
import org.sakaiproject.myshowcase.model.StatusMessage;
import org.sakaiproject.myshowcase.tool.RSSFeedRecord;

import com.google.gson.Gson;

public class MyShowcaseLoadRSSFeedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyShowcaseLoadRSSFeedServlet() {
        super();
        System.out.println("LoadRSSFeedServlet constructor") ;
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	    	List<RSSFeedRecord> rssf = new ArrayList<RSSFeedRecord>() ;

	        String rssFeedName = request.getParameter("feed").toString();

	        RssFeed rss = new RssFeed(rssFeedName) ;
	        rss.readRSSDocument() ;

	        if (rss.hasNext()) {
				while (rss.hasNext()) {
					rss.getNext() ;

					rssf.add(new RSSFeedRecord(rss.getGUID(), rss.getLink(), rss.getTitle(), rss.getDescription())) ;
				}
			}

	        response.setContentType("application/json"); 
	        response.setCharacterEncoding("UTF-8"); 

	        PrintWriter out = response.getWriter();
	        out.write(new Gson().toJson(rssf)); 
	        out.flush();
	        out.close();

		}
		catch (Exception e) {
			System.out.println(e.toString()) ;
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("LoadRSSFeedServlet doGet") ;
		}
		catch (Exception e) {
			System.out.println(e.toString()) ;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	        System.out.println("LoadRSSFeedServlet doPost") ;
		}
		catch (Exception e) {
			System.out.println(e.toString()) ;
		}
	}

}
