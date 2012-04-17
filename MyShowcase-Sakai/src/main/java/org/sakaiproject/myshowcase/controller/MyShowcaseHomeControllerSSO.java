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

package org.sakaiproject.myshowcase.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.sakaiproject.myshowcase.config.MyShowcaseConfigValues;
import org.sakaiproject.myshowcase.logic.*;
import org.sakaiproject.myshowcase.model.*;


public class MyShowcaseHomeControllerSSO extends AbstractController {

	final protected Log log = LogFactory.getLog(getClass());
	
	private String source = null;
	private String location = null;
	private String user = null;
	private String parent = null;
	
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		readRequest(request);
		
		ModelAndView mav = new ModelAndView();
		
		mav = new ModelAndView("SSOFailure");
		
		Owner owner = null;
		
		Account account = new Account();
		
		AccountSource accountSource = new AccountSource();
		
		accountSource.setLocation(location);
		
		accountSource.setName(source);
		
		account.setAccountSource(accountSource);
		
		account.setUserId(user);
		
        Configuration config = new Configuration();

        config.setUploadDir(MyShowcaseConfigValues.getInstance().getUploadDir());
         
        config.setParent(parent); 
        
        request.getSession().setAttribute("config", config);
        
		Validation validation = myshowcaseService.validateSSORequest(account);

		// if a valid SSO request see if a match is found on a MyShowcase owner
		if (validation.isValid()){
			
			owner =  myshowcaseService.obtainOwner(account);
			
	        if (owner != null){
	    	   
	        	mav = new ModelAndView(MyShowcaseView.Home.view());
	            
	            // ensure the owner has at least a default showcase set up by obtaining current showcase
	           Showcase showcase = myshowcaseService.obtainCurrentShowcase(owner);
	           
	           mav.addObject(MyShowcaseModel.Owner.model(),owner);
	           
	           mav.addObject(MyShowcaseModel.Showcase.model(),showcase);
	        	
	        }
	        else{
	        	
	        	Login login = new Login();
	    		
	        	login.setUserName("");
	    		
	        	login.setPassword("");
	        	
	           	config.setLoginGreeting("It looks as if you haven't accessed MyShowcase from here before. Please provide your MyShowcase login details and we'll send you right along");

	        	mav = new ModelAndView(MyShowcaseView.Signin.view());
	        	
	        }
		}
		
		mav.addObject(MyShowcaseModel.Config.model(),config);
		
		mav.addObject(MyShowcaseModel.Account.model(),account); 
		
		mav.addObject(MyShowcaseModel.Validation.model(),validation);

		
		return mav;
	}

	
    private IMyShowcaseService myshowcaseService;
    public void setMyshowcaseService(IMyShowcaseService service) {
        this.myshowcaseService = service;
    }

    
	/**
	  * Extract parameters from the request.
	  * @param HttpServletRequest request
	  */   
    private void readRequest(HttpServletRequest request) {
		
    	source = request.getParameter("source");
		
    	location = request.getParameter("location");
		
    	user = request.getParameter("user");
		
    	parent = request.getParameter("parent");
		
		if (source == null){
			source = "";
		};
		
		if (location == null){
			location = "";
		};
		
		if (user == null){
			user = "";
		};	
		
		if (parent == null){
			parent = "";
		};
		
    }
  
    
}
