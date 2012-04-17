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

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.sakaiproject.myshowcase.config.MyShowcaseConfigValues;
import org.sakaiproject.myshowcase.logic.*;
import org.sakaiproject.myshowcase.model.*;

import com.google.gson.Gson;

public class MyShowcaseSaveArtefactFileController extends AbstractController {

	final protected Log log = LogFactory.getLog(getClass());

	private String getRepositoryPath(String ownerId) {
		
		String rp ;

		MyShowcaseConfigValues configValues = new MyShowcaseConfigValues();		
		
		rp = configValues.getFileStore(); 		
		
		rp = rp + ownerId + "\\" ;
		
		File rd = new File(rp) ;
		
		if (!rd.exists()){
			
			rd.mkdir();
		}
		
		return rp ;
	
	}

	private String getFileType(String fName) {
		
		String fType ;
		
		String sFile[] ;
		
		sFile = fName.split("\\.") ;
		
		fType = sFile[sFile.length-1].toLowerCase() ;
		
		return fType ;
	}


	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		try {
			
			String ownerId = request.getParameter("ownerId");
			
	        Owner owner =  myshowcaseService.getOwnerById(new Long(ownerId));
	       
			String aType = request.getParameter("type").toString();
	        
			String aTitle = request.getParameter("title").toString();
	        
			String aDescription = request.getParameter("desc").toString();
	        
			String aDataValue = request.getParameter("datavalue").toString();

			
			// Save Artefact
	        Artefact artefact = new Artefact() ;
	        
	        ArtefactDetail artefactDetail = new ArtefactDetail() ;
	        
	        ArtefactType artefactType = myshowcaseService.getArtefactTypeByName(aType) ;

	        artefact.setOwner(owner) ;
	        
	        artefact.setDescription(aDescription) ;
	        
	        artefact.setName(aTitle) ;
	        
	        artefact.setShortDesc(aTitle) ;
	        
	        artefact.setType(artefactType) ;
	        
	        artefactDetail.setFileName(aDataValue) ;
	        
	        artefactDetail.setFileType(getFileType(aDataValue)) ;

	        artefactDetail.setFilePath(getRepositoryPath(ownerId)) ;	
	        
	        artefactDetail.setDetail(aDataValue) ;
	       
	        artefact.setArtefactDetail(artefactDetail) ;
	        
	        myshowcaseService.saveArtefact(artefact) ;
        
	    	
	        //
	        StatusMessage sm = new StatusMessage("status", "OK") ;
	    	
	        List<StatusMessage> sms = new ArrayList<StatusMessage>() ;
	    	
	        sms.add(sm) ;
	        
	        
	        response.setContentType("application/json"); 
	        
	        response.setCharacterEncoding("UTF-8"); 

	        PrintWriter out = response.getWriter();
	        
	        out.write(new Gson().toJson(sms)); 
	        
	        out.flush();
	        
	        out.close();

		}
		catch (Exception e) {
			System.out.println(e.toString()) ;
		}
       
		return null;
	}

	
    private IMyShowcaseService myshowcaseService;
    public void setMyshowcaseService(IMyShowcaseService service) {
        this.myshowcaseService = service;
    }

}
