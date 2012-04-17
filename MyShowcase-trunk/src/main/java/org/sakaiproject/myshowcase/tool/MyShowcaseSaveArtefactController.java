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


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.sakaiproject.myshowcase.logic.*;
import org.sakaiproject.myshowcase.model.*;

import com.google.gson.Gson;


public class MyShowcaseSaveArtefactController extends AbstractController{

	final protected Log log = LogFactory.getLog(getClass());
    
    private String aType = "";
    
    private String aTitle = "";
    
    private String aDescription = "";
    
    private String aDataValue = "";
    
    private String ownerId = "";
    
    private String artefactTypeDesc = "";
  
    
	
	/**
	  * Set MyShowcaseService
	  * @param IMyShowcaseService Interface of the MyShowcaseService implementation
	  */ 	
    private IMyShowcaseService myshowcaseService;
    public void setMyshowcaseService(IMyShowcaseService service){
        this.myshowcaseService = service;
    } 
    
    
	/**
	  * Implementation of AbstractController.handleRequestInternal
	  * @param HttpServletRequest request
	  * @param HttpServletResponse response
	  * @return ModelAndView
	  * @throws Exception
	  */      
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		try {
			
			readRequest(request);	
			
	        Owner owner =  myshowcaseService.getOwnerById(new Long(ownerId));

	        Artefact artefact = new Artefact();
	        
	        ArtefactDetail artefactDetail = new ArtefactDetail();
	        
	        ArtefactType artefactType = myshowcaseService.getArtefactTypeByName(aType);

	        artefact.setOwner(owner);
	        
	        artefact.setDescription(aDescription);
	        
	        artefact.setName(aTitle);
	        
	        artefact.setShortDesc(aTitle);
	        
	        artefact.setType(artefactType);
	        
	        artefactDetail.setUrl(aDataValue);
	        
	        artefactDetail.setDetail(aDataValue);
	        
	        artefact.setArtefactDetail(artefactDetail);
	        
	        myshowcaseService.saveArtefact(artefact);
	    	
            StatusMessage sm = new StatusMessage("OK", "A " + artefactTypeDesc + " artefact has been added to your evidence stream.");
	    	
	    	List<StatusMessage> sms = new ArrayList<StatusMessage>();
	    	
	    	sms.add(sm);
	    	
	        response.setContentType("application/json");
	        
	        response.setCharacterEncoding("UTF-8"); 

	        PrintWriter out = response.getWriter();
	        
	        out.write(new Gson().toJson(sms)); 
	        
	        out.flush();
	        
	        out.close();

		}
		catch (Exception e) {
			
			System.out.println("Exception: MyShowcaseSaveArtefactController: " + e.toString());
		}
       
		return null;
	}

	
	/**
	  * Extract parameters from the request.
	  * @param HttpServletRequest request
	  */   
    private void readRequest(HttpServletRequest request){
    	
    	ownerId = request.getParameter("ownerId");
    	
        aType = request.getParameter("type").toString();
        
        aTitle = request.getParameter("title").toString();
        
        aDescription = request.getParameter("desc").toString();
        
        aDataValue = request.getParameter("datavalue").toString(); 
        
        artefactTypeDesc = request.getParameter("artefactTypeDesc").toString();
    }	

}
