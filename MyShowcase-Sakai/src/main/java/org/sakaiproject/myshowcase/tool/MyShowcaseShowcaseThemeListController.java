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
import org.sakaiproject.myshowcase.logic.IMyShowcaseService;
import org.sakaiproject.myshowcase.model.ShowcaseTheme;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.google.gson.Gson;


public class MyShowcaseShowcaseThemeListController extends AbstractController{

	final protected Log log = LogFactory.getLog(getClass());

	
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
	
		ShowcaseTheme jsonTheme = new ShowcaseTheme();
		
		List<ShowcaseTheme> themes = new ArrayList<ShowcaseTheme>();
		
        List<ShowcaseTheme> themeList = myshowcaseService.getAllShowcaseThemes();
        
	    for (ShowcaseTheme theme  : themeList){
	    	
        	jsonTheme = new ShowcaseTheme();
        	
        	jsonTheme.setName(theme.getName());
        	
        	jsonTheme.setIllustration(theme.getIllustration());
        	
        	jsonTheme.setUsageDesc(theme.getUsageDesc());
        	
	    	jsonTheme.setShowcaseThemeId(theme.getShowcaseThemeId());

	    	themes.add(jsonTheme);
	    }        
        
	    response.setContentType("application/json"); 
	    
	    response.setCharacterEncoding("UTF-8"); 
	    
	    PrintWriter out = response.getWriter();
	    
	    out.write(new Gson().toJson(themes)); 
	    
    	out.flush();
    	
		out.close();
	
		return null;
	}
	
}
