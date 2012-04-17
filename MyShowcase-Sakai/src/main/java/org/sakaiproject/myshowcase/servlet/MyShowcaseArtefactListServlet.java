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
import java.text.DateFormat;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;

import org.sakaiproject.myshowcase.logic.IMyShowcaseService;
import org.sakaiproject.myshowcase.model.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;


/**
 * Servlet implementation class MyShowcaseArtefactListServlet
 */
public class MyShowcaseArtefactListServlet extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyShowcaseArtefactListServlet() {
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

		
		String ownerId = request.getParameter("ownerId");
		
		Date createdDate = new Date();

		Artefact artefact1 = new Artefact();
		artefact1.setCreatedDate(createdDate);
		artefact1.setArtefactId(new Long(12345));
		artefact1.setName("My first tweet");
		artefact1.setDescription("This is the description for artefact1");
		ArtefactDetail artefactDetail1 = new ArtefactDetail();
		artefactDetail1.setDetail("This is the artefact1 detail");
		artefact1.setArtefactDetail(artefactDetail1);
		ArtefactType artefactType1 = new ArtefactType();
		artefactType1.setName("Twitter");
		artefact1.setType(artefactType1);
		
		Artefact artefact2 = new Artefact();
		artefact2.setCreatedDate(new Date());
		artefact2.setArtefactId(new Long(22345));
		artefact2.setName("My first baby picture");
		artefact2.setDescription("This is the description for artefact2");
		ArtefactDetail artefactDetail2 = new ArtefactDetail();
		artefactDetail2.setDetail("This is the artefact2 detail");
		artefact2.setArtefactDetail(artefactDetail2);
		ArtefactType artefactType2 = new ArtefactType();
		artefactType2.setName("Flickr");
		artefact2.setType(artefactType2);
		
		Artefact artefact3 = new Artefact();
		artefact3.setCreatedDate(new Date());
		artefact3.setArtefactId(new Long(32345));
		artefact3.setName("World Cup 2010 feed");
		artefact3.setDescription("This is the description for artefact3");
		ArtefactDetail artefactDetail3 = new ArtefactDetail();
		artefactDetail3.setDetail("This is the artefact3 detail");
		artefact3.setArtefactDetail(artefactDetail3);
		ArtefactType artefactType3 = new ArtefactType();
		artefactType3.setName("Rss");
		artefact3.setType(artefactType3);
		
		Artefact artefact4 = new Artefact();
		artefact4.setCreatedDate(new Date());
		artefact4.setArtefactId(new Long(42345));
		artefact4.setName("Website I was lead developer on");
		artefact4.setDescription("This is the description for artefact4");
		ArtefactDetail artefactDetail4 = new ArtefactDetail();
		artefactDetail4.setDetail("This is the artefact4 detail");
		artefactDetail4.setUrl("http://bbc.co.uk/sport");
		artefact4.setArtefactDetail(artefactDetail4);
		ArtefactType artefactType4 = new ArtefactType();
		artefactType4.setName("Weblink");
		artefact4.setType(artefactType4);		
		
		Artefact artefact5 = new Artefact();
		artefact5.setCreatedDate(new Date());
		artefact5.setArtefactId(new Long(52345));
		artefact5.setName("My CV");
		artefact5.setDescription("This is the description for artefact5");
		ArtefactDetail artefactDetail5 = new ArtefactDetail();
		artefactDetail5.setDetail("This is the artefact5 detail");
		artefactDetail5.setFileType("POWERPOINT");
		artefactDetail5.setFileName("expenses.ppt");
		artefactDetail5.setFilePath("c:/files/travel/");
		artefact5.setArtefactDetail(artefactDetail5);
		ArtefactType artefactType5 = new ArtefactType();
		artefactType5.setName("File");
		artefact5.setType(artefactType5);				
		
		Artefact artefact6 = new Artefact();
		artefact6.setCreatedDate(new Date());
		artefact6.setArtefactId(new Long(62345));
		artefact6.setName("My e-portfolio asset");
		artefact6.setDescription("This is the description for artefact6");
		ArtefactDetail artefactDetail6 = new ArtefactDetail();
		artefactDetail6.setDetail("This is the artefact6 detail");
		artefactDetail6.setUrl("http://www.some-website.com/folder/path/filename.html");
		artefact6.setArtefactDetail(artefactDetail6);
		ArtefactType artefactType6 = new ArtefactType();
		artefactType6.setName("PebblePad");
		artefact6.setType(artefactType6);
		
		Artefact artefact7 = new Artefact();
		artefact7.setCreatedDate(new Date());
		artefact1.setArtefactId(new Long(72345));
		artefact7.setName("Resource held on Sakai");
		artefact7.setDescription("This is the description for artefact7");
		ArtefactDetail artefactDetail7 = new ArtefactDetail();
		artefactDetail7.setDetail("This is the artefact7 detail");
		artefact7.setArtefactDetail(artefactDetail7);
		ArtefactType artefactType7 = new ArtefactType();
		artefactType7.setName("Sakai");
		artefact7.setType(artefactType7);		
		
		
		List<Artefact> artefacts = new ArrayList<Artefact>();		
		
		// Append an element to the list
		artefacts.add(artefact1);
		artefacts.add(artefact2);
		artefacts.add(artefact3);
		artefacts.add(artefact4);
		artefacts.add(artefact5);
		artefacts.add(artefact6);
		artefacts.add(artefact7);		
		
	    response.setContentType("application/json"); 
	    response.setCharacterEncoding("UTF-8"); 
	    PrintWriter out = response.getWriter();
	    out.write(new Gson().toJson(artefacts)); 
    	out.flush();
		out.close();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return null;
	}	

	
    private IMyShowcaseService myshowcaseService;
    
    public void setMyshowcaseService(IMyShowcaseService service) {
        this.myshowcaseService = service;
    }	
}
