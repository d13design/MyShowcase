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
import org.sakaiproject.myshowcase.logic.IMyShowcaseService;
import org.sakaiproject.myshowcase.model.CollectionResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class MyShowcaseCollectionOutcomeController extends AbstractController {

	final protected Log log = LogFactory.getLog(getClass());
	
	private String collectionMessage = "";
	
	private String collectionLink = "";
	
	private CollectionResult collectionResult;

	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("CollectionOutcomeController... started");
		
		readRequest(request); 
 		
		//Add view
     	ModelAndView mav = new ModelAndView(MyShowcaseView.CollectionOutcome.view());
      
     	// add model
     	mav.addObject(MyShowcaseModel.CollectionResult.model(),collectionResult);
     	
     	System.out.println("CollectionOutcomeController... completed");
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
		
		collectionMessage = request.getParameter("collectionMessage");
		
		collectionLink = request.getParameter("collectionLink");
		
		if (collectionMessage == null){
			collectionMessage = "";
		};
		
		if (collectionLink == null){
			collectionLink = "";
		};
		
		collectionResult = new CollectionResult();
		
//		collectionResult.setMessage(message);
		collectionResult.setMessage(collectionMessage);
//		collectionResult.setMessage(message);
		collectionResult.setCollectionLink("\"" + collectionLink + "\"");
		
		
	} 
  
}