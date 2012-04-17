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
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import org.sakaiproject.myshowcase.model.*;


/**
 * Servlet implementation class MyShowcaseHomeServlet
 */
public class MyShowcaseTagCloudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyShowcaseTagCloudServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		Tag tag1 = new Tag();
		tag1.setName("Building");
		tag1.setSearchCount(26);
		
		Tag tag2 = new Tag();
		tag2.setName("Castle");
		tag2.setSearchCount(12);
		
		List<Tag> tags = new ArrayList<Tag>(); 
		
		// Append an element to the list
		tags.add(tag1);
		tags.add(tag2);

	    response.setContentType("application/json"); 
	    response.setCharacterEncoding("UTF-8"); 
	    PrintWriter out = response.getWriter();
	    out.write(new Gson().toJson(tags)); 
    	out.flush();
		out.close();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

	}
}