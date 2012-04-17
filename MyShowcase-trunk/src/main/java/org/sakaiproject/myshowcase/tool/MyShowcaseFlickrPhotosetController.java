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
import org.sakaiproject.myshowcase.config.MyShowcaseConfigValues;
import org.sakaiproject.myshowcase.logic.IMyShowcaseService;
import org.sakaiproject.myshowcase.model.StatusMessage;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.REST;
import com.aetrion.flickr.Transport;
import com.aetrion.flickr.people.User;
import com.aetrion.flickr.photosets.Photoset;
import com.google.gson.Gson;


public class MyShowcaseFlickrPhotosetController extends AbstractController {

	final protected Log log = LogFactory.getLog(getClass());
	
	private String artefactDataValue = "";

	   
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
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		readRequest(request);
		
    	List<StatusMessage> sms = new ArrayList<StatusMessage>();
    	
    	// Make sure that the "All" option is always there
        StatusMessage sm = new StatusMessage("-1", "ALL");
        
    	sms.add(sm);

    	try {

	        Transport transport = new REST();
        
	        Flickr flickr = new Flickr(MyShowcaseConfigValues.getInstance().getFlickrKey(), MyShowcaseConfigValues.getInstance().getFlickrSecret(), transport);
	        
	        User user = flickr.getPeopleInterface().findByUsername(artefactDataValue);
	        
	        System.out.println("++++ User = " + user);
	        
	        String userId = user.getId();
 	        
	        Iterator photosets = flickr.getPhotosetsInterface().getList(userId).getPhotosets().iterator() ;
	        
	        while (photosets.hasNext()){
	        	
	        	Photoset ps = (Photoset) photosets.next();
	        	
		        sm = new StatusMessage(ps.getId(), ps.getTitle());
		        
		    	sms.add(sm);
	        }
	        
		}
		catch (Exception e){
			
			System.out.println("Exception: MyShowcaseFlickrPhotosetControll: " + e.toString());
		}
       
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

        artefactDataValue = request.getParameter("datavalue").toString();
    }

}
