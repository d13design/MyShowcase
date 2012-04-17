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
import org.sakaiproject.myshowcase.model.*;
import org.sakaiproject.myshowcase.logic.*;


public class MyShowcaseBuildShowcaseController extends AbstractController {

	final protected Log log = LogFactory.getLog(getClass());
	
	private String direction = "";
	
	private String currentPage = "";
	
	private String ownerId = "";
	
	private String wizardTemplatePage = "showcaseWizardTemplates";
	
	private String wizardReviewersPage = "showcaseWizardReviewers";
	
	private String wizardDetailsPage = "showcaseWizardDetails";
	
	private String wizardSummaryPage = "showcaseWizardSummary";
	
	private String homePage = "home";
	
	private String showcaseHistoryPage = "showcaseHistory";

	private String wizardDirectionNext = "next";

	
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("MyShowcaseBuildShowcaseController...starting");
		
		readRequest(request);
		
		System.out.println("Direction: " + direction);
		System.out.println("CurrentPage: " + currentPage);
		System.out.println("ownerId: " + ownerId);
		
		Configuration config = (Configuration) request.getSession().getAttribute("config");
		
		System.out.println("config=" + config);

		
		Owner owner =  myshowcaseService.getOwnerById(new Long(ownerId));
		
		Showcase showcase = myshowcaseService.obtainCurrentShowcase(owner);
		
		ShowcaseTheme theme = showcase.getTheme();

		ModelAndView mav = new ModelAndView();
		
		if (direction == null){
			direction = " ";
		};
		
		if (currentPage == null){
			currentPage = homePage;
		};		
		
		if (direction.equals(homePage)){
			
			mav = new ModelAndView(homePage);
		}
		else{

			// Determine what page to display next
			if (currentPage.equals(homePage)){

				mav = new ModelAndView(wizardDetailsPage);
			}
			else if (currentPage.equals(wizardDetailsPage)){

				mav = direction.equals(wizardDirectionNext) ? new ModelAndView(wizardTemplatePage) : new ModelAndView(homePage);
			
			}
			else if (currentPage.equals(wizardTemplatePage)){
			
				mav = direction.equals(wizardDirectionNext) ? new ModelAndView(wizardReviewersPage) : new ModelAndView(wizardDetailsPage);
			}
			else if (currentPage.equals(wizardReviewersPage)){
			
				mav = direction.equals(wizardDirectionNext) ? new ModelAndView(wizardSummaryPage) : new ModelAndView(wizardTemplatePage);
			}
			else if (currentPage.equals(wizardSummaryPage)){
			
				mav = direction.equals(wizardDirectionNext) ? new ModelAndView(showcaseHistoryPage) : new ModelAndView(wizardReviewersPage);
			}
			else {
			
				mav = new ModelAndView(homePage);
			}
			
		}
		
		mav.addObject(MyShowcaseModel.Config.model(),config);
		
        mav.addObject(MyShowcaseModel.Owner.model(),owner);
        
        mav.addObject(MyShowcaseModel.Showcase.model(),showcase);
        
        mav.addObject(MyShowcaseModel.Theme.model(),theme);
        
        System.out.println("MyShowcaseBuildShowcaseController...leaving");
        
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

		direction = request.getParameter("direction");
		
		currentPage = request.getParameter("pageName");
		
		ownerId = request.getParameter("ownerId");

    }
}


