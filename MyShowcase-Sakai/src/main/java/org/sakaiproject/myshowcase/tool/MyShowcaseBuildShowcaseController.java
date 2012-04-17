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

import java.util.List;

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

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("MyShowcaseBuildShowcaseController .... starting");
		
		String direction = request.getParameter("direction");
		String currentPage = request.getParameter("pageName");


		String ownerId = request.getParameter("ownerId");

		String wizardTemplatePage = "showcaseWizardTemplates";
		String wizardReviewersPage = "showcaseWizardReviewers";
		String wizardDetailsPage = "showcaseWizardDetails";
		String wizardSummaryPage = "showcaseWizardSummary";
		String wizardLaunchPage = "showcaseWizardLaunch";
		String homePage = "home";
		String showcaseHistoryPage = "showcaseHistory";
		
		String wizardDirectionPrevious = "previous";
		String wizardDirectionNext = "next";
	

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
        mav.addObject("owner",owner); 
        mav.addObject("showcase",showcase);
        mav.addObject("theme",theme);
        
		return mav;
	}

	
    private IMyShowcaseService myshowcaseService;
    public void setMyshowcaseService(IMyShowcaseService service) {
        this.myshowcaseService = service;
    }

    
    
}

