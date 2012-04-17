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
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sakaiproject.entitybroker.EntityView;
import org.sakaiproject.entitybroker.access.EntityViewAccessProvider;
import org.sakaiproject.myshowcase.logic.*;
import org.sakaiproject.myshowcase.model.*;

import com.google.gson.Gson;

public class MyShowcaseEntityViewAccessProvider implements EntityViewAccessProvider {

	Owner owner = new Owner();
	Showcase showcase = new Showcase();
	ShowcaseTheme theme = new ShowcaseTheme();

    public static IMyShowcaseService myshowcaseService;
    
    public void MyShowcaseEntityViewProvider() {
    }
 
    
    public void setMyshowcaseService(IMyShowcaseService service) {

        this.myshowcaseService = service;
    }

    private Long getValueFromParameter(String qs, String p) {
    	Long pVal = new Long(0) ;
    	String qsArr[] ;
    	String sArr[];
    	
    	qsArr = qs.split("/") ;
    	for (int i = 0 ; i < qsArr.length ; i++) {
    		sArr = qsArr[i].split("=") ;
    		if (sArr.length > 1)
    			if (sArr[0].equalsIgnoreCase(p.toString()))
    				pVal = new Long(sArr[1]) ;
    	}
    	return pVal ;
    	
    }
    
    private Long getShowcaseID(String qs) {
    	return getValueFromParameter(qs, "showcaseid") ;
    }

    private Long getReviewerID(String qs) {
    	return getValueFromParameter(qs, "reviewerid") ;
    }
 
 
    public void handleAccess(EntityView view, HttpServletRequest req,
			HttpServletResponse res) {

    	System.out.println("MyShowcaseEntityViewAccessProvider ... starting");
    	
		try {

			String requestQueryString = req.getRequestURI() ;

			Long showcaseId = getShowcaseID(requestQueryString) ;

			Long reviewerId = getReviewerID(requestQueryString) ;
			
			Showcase showcase = myshowcaseService.getShowcaseById(showcaseId) ;

			Reviewer reviewer = myshowcaseService.getReviewerById(reviewerId) ;

			ShowcaseReviewer showcaseReviewer = myshowcaseService.getShowcaseReviewer(showcase, reviewer);
			
			myshowcaseService.showcaseReviewed(showcaseReviewer);
			
			owner = showcase.getOwner() ;
			
			owner = myshowcaseService.getOwnerById(owner.getOwnerId()) ;

			Date now = new Date() ;
			
		    PrintWriter out = res.getWriter();
	
//		    out.write("EVAP Start<br>");
//		    out.write("EVAP end<br>");

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
			
		} catch (Exception e) {
			

		}
		
	}

}
