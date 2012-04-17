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

public class MyShowcaseHomeController extends AbstractController {

	final protected Log log = LogFactory.getLog(getClass());


	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("MyShowcaseHomeController....starting");
		
        //Add view
        ModelAndView mav = new ModelAndView(MyShowcaseView.Home.view());
        
        //Add model *NB this is a sakai specific obtain owner call
        Owner owner =  myshowcaseService.obtainOwner();

 //       MyShowcaseConfig config = new MyShowcaseConfig();
        
//		MyShowcaseConfigValues configValues = new MyShowcaseConfigValues();		

//        config.setFileStoreAddress(MyShowcaseConfigValues.getInstance().getFileStore());
		
        Configuration config = new Configuration();

        config.setUploadDir(MyShowcaseConfigValues.getInstance().getUploadDir());
        
		request.getSession().setAttribute("config", config);
		
//        config.setUploadDir(MyShowcaseConfigValues.getInstance().getUploadDir());        
         // ensure the owner has at least a default showcase set up by obtaining current showcase
        Showcase showcase = myshowcaseService.obtainCurrentShowcase(owner);
        
        mav.addObject(MyShowcaseModel.Config.model(),config); 
        
        mav.addObject(MyShowcaseModel.Owner.model(),owner);
        
        mav.addObject(MyShowcaseModel.Showcase.model(),showcase);
        
        System.out.println("MyShowcaseHomeController....leaving");
        
		return mav;
	}

	
    private IMyShowcaseService myshowcaseService;
    public void setMyshowcaseService(IMyShowcaseService service) {
        this.myshowcaseService = service;
    }

}
