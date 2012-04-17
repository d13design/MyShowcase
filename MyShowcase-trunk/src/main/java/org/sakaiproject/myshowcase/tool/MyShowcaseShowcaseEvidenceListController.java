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


public class MyShowcaseShowcaseEvidenceListController extends AbstractController{

	final protected Log log = LogFactory.getLog(getClass());

	private String showcaseId = "";
	
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
		
        // Read for Artefact list
		Long showcaseIdentifier = new Long(showcaseId);
		
		List<Artefact> artefacts = new ArrayList<Artefact>();
		
		List<Artefact> artefactList =  new ArrayList<Artefact>();

		Showcase showcase = myshowcaseService.getShowcaseById(showcaseIdentifier);
		
		artefactList =  myshowcaseService.getArtefacts(showcase);

	    // Process Artefact List
		Artefact jsonArtefact = null;
		
	    ArtefactDetail jsonArtefactDetail = null;
	    
	    ArtefactType jsonArtefactType = null;
		
	    for (Artefact artefact  : artefactList) {
	    	
        	jsonArtefactType = new ArtefactType();
        	
	    	jsonArtefactDetail = new ArtefactDetail();
	    	
	    	jsonArtefact = artefact.getDeepCopy();
	    	
	    	jsonArtefact.setArtefactId(artefact.getArtefactId());
	    	
	    	jsonArtefact.setDescription(artefact.getDescription());
	    	
	    	jsonArtefact.setName(artefact.getName());
	    	
	    	jsonArtefact.setCreatedDate(artefact.getCreatedDate());
	    	
	    	jsonArtefact.setCreatedDateTime(artefact.getCreatedDate());
	    	
	    	jsonArtefactType.setName(artefact.getType().getName());
	    	
	    	jsonArtefact.setType(jsonArtefactType);
	    	
	    	jsonArtefactDetail.setFileName(artefact.getArtefactDetail().getFileName());
	    	
	    	jsonArtefactDetail.setFilePath(artefact.getArtefactDetail().getFilePath());
	    	
	    	jsonArtefactDetail.setFileType(artefact.getArtefactDetail().getFileType());
	    	
	    	jsonArtefactDetail.setUrl(artefact.getArtefactDetail().getUrl());
	    	
	    	jsonArtefactDetail.setDetail(artefact.getArtefactDetail().getDetail());
	    	
	    	jsonArtefactDetail.setFlickrUserName(artefact.getArtefactDetail().getFlickrUserName());
	    	
	    	jsonArtefactDetail.setTwitterUserName(artefact.getArtefactDetail().getTwitterUserName());
	    	
	    	jsonArtefact.setArtefactDetail(jsonArtefactDetail);
	    	
//	    	jsonArtefact.setType(artefact.getType());
	    	artefacts.add(jsonArtefact);
	    }

	    response.setContentType("application/json"); 
	    
	    response.setCharacterEncoding("UTF-8"); 
	    
	    PrintWriter out = response.getWriter();
	    
	    out.write(new Gson().toJson(artefacts)); 
	    
    	out.flush();
    	
		out.close();
		
		return null;
	}

	
	/**
	  * Extract parameters from the request.
	  * @param HttpServletRequest request
	  */   
    private void readRequest(HttpServletRequest request){
		
    	showcaseId = request.getParameter("showcaseId");
    }	
}
