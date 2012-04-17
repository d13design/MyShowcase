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


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.sakaiproject.myshowcase.logic.*;
import org.sakaiproject.myshowcase.model.*;
import com.google.gson.Gson;
import java.io.PrintWriter;
import java.util.*;


public class MyShowcaseMaintainShowcaseController extends AbstractController{

	final protected Log log = LogFactory.getLog(getClass());
	
	private String title = "";
	
	private String description = "";
	
	private String startDate = "";
	
	private String endDate = "";
	
	private String themeId = "";
	
	private String showcaseId = "";
	
	private String action = "";
	
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
		
		Showcase showcase = myshowcaseService.getShowcaseById(new Long(showcaseId));

		if (title.length() > 0){
			
			showcase.setName(title);
		}
		
		if (description.length() > 0){
			
			showcase.setFullDesc(description);
		}

		if (startDate.length() > 0){
			
			Date sdt = newDateFromString(startDate);
			
			showcase.setReviewStartDate(sdt);
		}
		
		if (endDate.length() > 0){
			
			Date edt = newDateFromString(endDate);
			
			showcase.setReviewEndDate(edt);
		}
		
		if (themeId.length() > 0){
			
			showcase.setTheme(myshowcaseService.getShowcaseThemeById(new Long(themeId)));
		}
		
		myshowcaseService.saveShowcase(showcase);
		
		List<String> messages = new ArrayList<String>();
       
 	    response.setContentType("application/json"); 
 	    
	    response.setCharacterEncoding("UTF-8"); 
	    
	    PrintWriter out = response.getWriter();
	    
	    out.write(new Gson().toJson(messages)); 
	    
    	out.flush();
    	
		out.close();
		
		return null;
	}

	
	/**
	  * Extract parameters from the request.
	  * @param HttpServletRequest request
	  */   
    private void readRequest(HttpServletRequest request){
    	
		title = request.getParameter("title");
		
		description = request.getParameter("description");
		
		startDate = request.getParameter("startDate");
		
		endDate = request.getParameter("endDate");
		
		themeId = request.getParameter("themeId");
		
		showcaseId = request.getParameter("showcaseId");
		
		action = request.getParameter("action");
		
		ownerId = request.getParameter("ownerId");
		
		if ((title == null) || (title.equals("null"))){
			title = "";
		};
		
		if ((description == null) || (description.equals("null"))){
			description = "";
		};

		if ((startDate == null) || (startDate.equals("null"))){
			startDate = "";
		};
		
		if ((endDate == null) || (endDate.equals("null"))){
			endDate = "";
		};
		
		if ((themeId == null) || (themeId.equals("null"))){
			themeId = "";
		};
		
		if ((action == null) || (action.equals("null"))){
			action = "";
		};		
		
		if ((ownerId == null) || (ownerId.equals("null"))){
			ownerId = "";
		};
		
		if ((showcaseId == null) || (showcaseId.equals("null"))){
			showcaseId = "";
		};
    }		

    
	/**
	  * Create new date from a supplied string
	  * @param String 
	  * @return Date
	  */    
    private Date newDateFromString(String sDate){
  	
   		String dateParts[] = sDate.split("/");
   	
   		if (dateParts.length != 3){
   		
   			return null ;
   		}
   		else {
   		
   			int dd = Integer.parseInt(dateParts[0], 10);
   		
   			int mm = Integer.parseInt(dateParts[1], 10) - 1;
   		
   			int yy = Integer.parseInt(dateParts[2], 10) - 1900;
   		
   			Date oDate = new Date(yy,mm,dd);
   		
   			return oDate ;
   		}
    }	
}
