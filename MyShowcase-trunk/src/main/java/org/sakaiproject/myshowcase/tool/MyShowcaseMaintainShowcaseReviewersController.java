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

package org.sakaiproject.myshowcase.tool;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.sakaiproject.myshowcase.logic.*;
import org.sakaiproject.myshowcase.model.*;
import com.google.gson.Gson;
import java.io.PrintWriter;
import java.util.*;


public class MyShowcaseMaintainShowcaseReviewersController extends AbstractController{

	final protected Log log = LogFactory.getLog(getClass());
	
	private String name = "";
	
	private String email = "";
	
	private String showcaseId = "";
	
	private String action = "";
	
	private String ownerId = "";
	
	/**
	  * Set MyShowcaseService
	  * @param IMyShowcaseService Interface of the MyShowcaseService implementation
	  */ 		
    private IMyShowcaseService myshowcaseService;
    public void setMyshowcaseService(IMyShowcaseService service){
        this.myshowcaseService = service;
    }
    
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		readRequest(request);
		
		Owner owner = myshowcaseService.getOwnerById(new Long(ownerId));
		
		Showcase showcase = myshowcaseService.getShowcaseById(new Long(showcaseId));
		
		Reviewer reviewer = myshowcaseService.getReviewer(email, owner);
		
		if (reviewer == null){
			
			reviewer = new Reviewer();
			
			reviewer.setName(name);
			
			reviewer.setOwner(owner);
			
			reviewer.setEmail(email);
			
			reviewer = myshowcaseService.saveReviewer(reviewer);
		};
		
		if (action.equals("add")){
			
			myshowcaseService.addReviewerToShowcase(reviewer, showcase);

		}
		else if (action.equals("remove")){
		
			myshowcaseService.removeReviewerFromShowcase(reviewer, showcase);

		};
		
		List<String> messages = new ArrayList<String>();
       
 	    response.setContentType("application/json"); 
 	    
	    response.setCharacterEncoding("UTF-8");
	    
	    PrintWriter out = response.getWriter();
	    
	    out.write(new Gson().toJson(messages)); 
	    
    	out.flush();
    	
		out.close();
	
		return null;
	}

	
	/**
	  * Extract parameters from the request.
	  * @param HttpServletRequest request
	  */   
    private void readRequest(HttpServletRequest request){

		name = request.getParameter("reviewerName");
		
		email = request.getParameter("reviewerEmail");
		
		showcaseId = request.getParameter("showcaseId");
		
		action = request.getParameter("action");
		
		ownerId = request.getParameter("ownerId");   	
    }
	
}
