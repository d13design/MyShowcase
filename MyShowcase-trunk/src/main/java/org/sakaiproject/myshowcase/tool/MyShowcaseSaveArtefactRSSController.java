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


public class MyShowcaseSaveArtefactRSSController extends AbstractController{

	final protected Log log = LogFactory.getLog(getClass());
	
    private String aType = "";
    
    private String aTitle = "";
    
    private String aDescription = "";
    
    private String aDataValue = "";
    
    private String ownerId = "";
    
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
		
		readRequest(request);
		
        Owner owner =  myshowcaseService.getOwnerById(new Long(ownerId));
        
        List<StatusMessage> sms = new ArrayList<StatusMessage>();
        
        StatusMessage sm = new StatusMessage();
        
        List<Artefact> artefacts = new ArrayList<Artefact>();       
		
		try {

	        RssFeed rssFeed = new RssFeed(aDataValue);

	    	
	        while (rssFeed.hasNext()){
	        	
	        	rssFeed.getNext();
	        	
    			Artefact artefact = new Artefact();
    			
    	        ArtefactDetail artefactDetail = new ArtefactDetail();
    	        
    	        ArtefactType artefactType = myshowcaseService.getArtefactTypeByName(aType);
 
    	        artefact.setOwner(owner);
    	        
    	        artefact.setDescription(aDescription);
    	        
    	        artefact.setName(aTitle);
    	        
    	        artefact.setShortDesc(aTitle);
    	        
    	        artefact.setType(artefactType);
    	        
    	        artefactDetail.setDetail(rssFeed.getTitle().toString());
    	        
    	        artefactDetail.setUrl(rssFeed.getGUID().toString());
    	        
    	        artefact.setArtefactDetail(artefactDetail);
    	        
    	        artefacts.add(artefact);

	        }
	        
			if (artefacts.size() > 0){
				
	            sm = new StatusMessage("OK", "A total of " + artefacts.size() + " items from the Rss feed have been added to your evidence stream.");
	            
	            for (Artefact artefact  : artefacts) {
	            	
	            	myshowcaseService.saveArtefact(artefact) ;
	            }
	            
			} else {
				
				sm = new StatusMessage("ERROR", "MyShowcase has been unable to collect items from the Rss feed. No evidence has been added to your evidence stream.");
			};
			
			sms.add(sm);
			
	        response.setContentType("application/json"); 
	        
	        response.setCharacterEncoding("UTF-8"); 

	        PrintWriter out = response.getWriter();
	        
	        out.write(new Gson().toJson(sms)); 
	        
	        out.flush();
	        
	        out.close();
		}
		catch (Exception e) {
			System.out.println("Exception: MyShowcaseSaveArtefactTSSController: " + e.toString());
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
    }

}
