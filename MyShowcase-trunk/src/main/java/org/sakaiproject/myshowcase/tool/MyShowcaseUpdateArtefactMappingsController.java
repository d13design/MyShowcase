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


public class MyShowcaseUpdateArtefactMappingsController extends AbstractController {

	final protected Log log = LogFactory.getLog(getClass());
	
	private String artefactId = "";
	
	private String action = "";
	
	private String competencyValue = "";
	
	private String idValue = "";
	
	private String checkedValue = "";
	
	/**
	  * Set MyShowcaseService
	  * @param IMyShowcaseService Interface of the MyShowcaseService implementation
	  */  	
    private IMyShowcaseService myshowcaseService;
    public void setMyshowcaseService(IMyShowcaseService service) {
        this.myshowcaseService = service;
    }

    
	/**
	  * Implementation of AbstractController.handleRequestInternal
	  * @param HttpServletRequest request
	  * @param HttpServletResponse response
	  * @return ModelAndView
	  * @throws Exception
	  */    
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		readRequest(request);
		
		Long competencyId = Long.parseLong(competencyValue);
		
		Long id = Long.parseLong(idValue);
		
		Boolean checked = Boolean.parseBoolean(checkedValue);
		
		Artefact artefact = myshowcaseService.getArtefactById(new Long(artefactId));

		if (action.equals("update")){
			
			myshowcaseService.updateArtefactMapping(artefact, competencyId, id, checked);
		}

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
    	
		artefactId = request.getParameter("artefactId");
		
		action = request.getParameter("action");
		
		competencyValue = request.getParameter("competencyId");
		
		idValue = request.getParameter("id");
		
		checkedValue = request.getParameter("checked");
    }	
	
}