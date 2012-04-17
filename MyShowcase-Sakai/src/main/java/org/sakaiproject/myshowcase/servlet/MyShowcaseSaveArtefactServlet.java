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

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sakaiproject.myshowcase.model.StatusMessage;
import org.sakaiproject.myshowcase.model.StatusMessages;

import com.google.gson.*;

public class MyShowcaseSaveArtefactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyShowcaseSaveArtefactServlet() {
        super();

    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

	        String artefactType = request.getParameter("type").toString();

	        String artefactTitle = request.getParameter("title").toString();

	        String artefactDescription = request.getParameter("desc").toString();

	        String artefactDataValue = request.getParameter("datavalue").toString();

	        if (artefactType.equals("fileupload")) {

	        }

	    	StatusMessage sm = new StatusMessage("status", "OK") ;

	    	List<StatusMessage> sms = new ArrayList<StatusMessage>() ;
	    	sms.add(sm) ;
	        response.setContentType("application/json"); 
	        response.setCharacterEncoding("UTF-8"); 

	        PrintWriter out = response.getWriter();
	        out.write(new Gson().toJson(sms)); 
	        out.flush();
	        out.close();

		}
		catch (Exception e) {
			System.out.println(e.toString()) ;
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("SaveArtefactServlet doGet") ;
		}
		catch (Exception e) {
			System.out.println(e.toString()) ;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	        System.out.println("SaveArtefactServlet doPost") ;
		}
		catch (Exception e) {
			System.out.println(e.toString()) ;
		}
	}

}
