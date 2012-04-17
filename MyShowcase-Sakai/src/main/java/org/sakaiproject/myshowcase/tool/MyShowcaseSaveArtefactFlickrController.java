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
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.sakaiproject.myshowcase.config.MyShowcaseConfigValues;
import org.sakaiproject.myshowcase.logic.*;
import org.sakaiproject.myshowcase.model.*;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.REST;
import com.aetrion.flickr.Transport;
import com.aetrion.flickr.people.User;
import com.aetrion.flickr.photos.Photo;
import com.aetrion.flickr.photos.PhotoList;
import com.aetrion.flickr.photosets.Photoset;
import com.google.gson.Gson;


public class MyShowcaseSaveArtefactFlickrController extends AbstractController{

	final protected Log log = LogFactory.getLog(getClass());

	private String ownerId = "";
	
    private String aType = "";
    
    private String aTitle = "";
    
    private String aDescription = "";
    
    private String aDataValue = "";
    
    private String aPhotoset = "";	
    
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

	        Transport transport = new REST();
	        
	        Flickr flickr = new Flickr(MyShowcaseConfigValues.getInstance().getFlickrKey(), MyShowcaseConfigValues.getInstance().getFlickrSecret(), transport);
	        
	        User user = flickr.getPeopleInterface().findByUsername(aDataValue);
	        
	        String userId = user.getId();

	        Iterator photosets = flickr.getPhotosetsInterface().getList(userId).getPhotosets().iterator();
	        
	        while (photosets.hasNext()){
	        	
	        	Photoset ps = (Photoset) photosets.next() ;
	        	
	        	if ((aPhotoset.equals(ps.getId())) || (aPhotoset.equals("-1"))){
	        		
	        		PhotoList photolist = flickr.getPhotosetsInterface().getPhotos(ps.getId(), 200, 1);
	
	        		Iterator pl = photolist.iterator();
	        		
	        		while (pl.hasNext()){
	        			
	        			Photo ph = (Photo)pl.next();

	        			Artefact artefact = new Artefact();
	        			
	        	        ArtefactDetail artefactDetail = new ArtefactDetail();
	        	        
	        	        ArtefactType artefactType = myshowcaseService.getArtefactTypeByName(aType);
	        	        
	        	        artefact.setOwner(owner);
	        	        
	        	        artefact.setDescription(aDescription);
	        	        
	        	        artefact.setName(aTitle);
	        	        
	        	        artefact.setShortDesc(aTitle);
	        	        
	        	        artefact.setType(artefactType);
	        	        
	        	        artefactDetail.setFlickrUserName(aDataValue);
	        	        
	        	        artefactDetail.setDetail(ph.getTitle());
	        	        
	        	        artefactDetail.setUrl(ph.getMediumUrl());
	        	        
	        	        artefact.setArtefactDetail(artefactDetail);
	        	        
	        	        artefacts.add(artefact);
	        	        
//	        	        myshowcaseService.saveArtefact(artefact);
	        		}
	        		
	        	}
	        }
	        
	        sm = new StatusMessage("status", "OK");
		}
		catch (Exception e){
        	
	        sm = new StatusMessage("status", "Invalid Flickr Username");
		}
 
		if (artefacts.size() > 0){
			
            sm = new StatusMessage("OK", "A total of " + artefacts.size() + " images from Flickr have been added to your evidence stream.");
            
            for (Artefact artefact  : artefacts) {
            	
            	myshowcaseService.saveArtefact(artefact) ;
            }
            
		} else {
			
			sm = new StatusMessage("ERROR", "MyShowcase has been unable to collect any images from Flickr for your request. No evidence has been added to your evidence stream.");
		};		
    	
    	sms.add(sm) ;
    	
        response.setContentType("application/json"); 
        
        response.setCharacterEncoding("UTF-8"); 

        PrintWriter out = response.getWriter();
        
        out.write(new Gson().toJson(sms)); 
        
        out.flush();
        
        out.close();

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
        
        aPhotoset = request.getParameter("photoset").toString();   	
    }

}
