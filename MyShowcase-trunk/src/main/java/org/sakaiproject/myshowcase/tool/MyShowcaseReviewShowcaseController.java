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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.sakaiproject.myshowcase.model.*;
import org.sakaiproject.myshowcase.logic.*;


public class MyShowcaseReviewShowcaseController extends AbstractController {

	final protected Log log = LogFactory.getLog(getClass());
	
	Owner owner = new Owner();
	
	Showcase showcase = new Showcase();
	
	ShowcaseTheme theme = new ShowcaseTheme();
	
	Reviewer reviewer = new Reviewer();
	
	ShowcaseReviewer showcaseReviewer = new ShowcaseReviewer();
	
	private String showcaseId = "";
	
	private String reviewerId = "";


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
		
		showcase = myshowcaseService.getShowcaseById(new Long(showcaseId));
		
		reviewer = myshowcaseService.getReviewerById(new Long(reviewerId));

		showcaseReviewer = myshowcaseService.getShowcaseReviewer(showcase, reviewer);

		owner = myshowcaseService.getOwnerById(showcase.getOwner().getOwnerId()) ;
		
		theme = showcase.getTheme();
		
	    PrintWriter out = response.getWriter();

	    String textToInsert = "" ;
	    
	    // It's the Default
	    if (showcase.getTheme().getShowcaseThemeId() == MyShowcaseShowcaseTheme.DEFAULT.themeId()){
	    	
	    	MyShowcaseRenderThemeDefault renderDefault = new MyShowcaseRenderThemeDefault(myshowcaseService);
	    	
			textToInsert = renderDefault.build(showcase, owner, true);
	    }
	    // It's a CV
	    else if (showcase.getTheme().getShowcaseThemeId() == MyShowcaseShowcaseTheme.SIMPLE_CV.themeId()){ 
	
	    	MyShowcaseRenderThemeSimpleCV renderSimpleCV = new MyShowcaseRenderThemeSimpleCV(myshowcaseService);
	    	
	    	textToInsert = renderSimpleCV.build(showcase, owner, true);
	    }	
	    else
		    textToInsert = "Unformatted Showcase Type " + showcase.getTheme().getName()+ "<br>" ;

	    
    	out.write(textToInsert) ;
    	
    	out.flush();
    	
		out.close(); 
		
		myshowcaseService.showcaseReviewed(showcaseReviewer);
		
 		return null;
	}

	
	/**
	  * Extract parameters from the request.
	  * @param HttpServletRequest request
	  */   
    private void readRequest(HttpServletRequest request){
    	
		showcaseId = request.getParameter("showcaseId");
		
		reviewerId = request.getParameter("reviewerId");  	
    		
    }	

}
