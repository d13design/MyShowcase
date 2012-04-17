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
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.sakaiproject.myshowcase.config.MyShowcaseConfigValues;
import org.sakaiproject.myshowcase.model.*;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.sakaiproject.myshowcase.logic.IMyShowcaseService;
import org.sakaiproject.myshowcase.model.Artefact;


public class MyShowcaseSaveArtefactFileController extends AbstractController {

	final protected Log log = LogFactory.getLog(getClass());
	
	private String ownerId = "";
    
	private String aType = "";
    
	private String aTitle = "";
    
	private String aDescription = "";
    
	private String aDataValue = "";
	
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
		
		try {
			
			readRequest(request); 
			
	        Owner owner =  myshowcaseService.getOwnerById(new Long(ownerId));
			
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

	
	/**
	  * Extract parameters from the request.
	  * @param HttpServletRequest request
	  */   
    private void readRequest(HttpServletRequest request){
 		
		ownerId = request.getParameter("ownerId");
        
		aType = request.getParameter("type").toString();
        
		aTitle = request.getParameter("title").toString();
        
		aDescription = request.getParameter("desc").toString();
        
		aDataValue = request.getParameter("datavalue").toString();
    }	

    
	protected void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        try {

        	
	        if (ServletFileUpload.isMultipartContent(request)) {

				ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
				
				List fileItemsList = servletFileUpload.parseRequest(request);
				
				DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
				
				diskFileItemFactory.setSizeThreshold(40960); /* the unit is bytes */

	        	String homeDirId = MyShowcaseConfigValues.getInstance().getFileUploadHomeDir();
	
	        	File repositoryPath = null;

	        	String homeDir = "";
	        	
				if (homeDirId.equals("ownerName")){
					
//				    homeDir = getOwnerName(fileItemsList) ;
		
		        	
				} else if (homeDirId.equals("ownerId")){
					
//				    homeDir = getOwnerId(fileItemsList) ;

				}	
	
				repositoryPath = new File(getRepositoryPath(homeDir));
	        	
				diskFileItemFactory.setRepository(repositoryPath);
				
				servletFileUpload.setSizeMax(81920); /* the unit is bytes */
				
				Iterator it = fileItemsList.iterator();

				while (it.hasNext()){
					
					FileItem fileItem = (FileItem)it.next();
					
					if (fileItem.isFormField()){

					} else {

						
						String fileName = fileItem.getName();
						System.out.println("++++ A file(1) = " + fileName);
						
						
						fileName = getRepositoryPath(homeDir) + fileName ;
						System.out.println("++++ A file(2) = " + fileName);

						File uploadedFile = new File(fileName) ;
						
						fileItem.write(uploadedFile) ;
						System.out.println("YES --- File (" + fileName + ") has been written.");
						
						System.out.println("===========================");
//						isValidFileType(fileName); 
						System.out.println("===========================");
					}
				}
				
	        } else {
	        	
	        	System.out.println("Not Multipart Request") ;
	        }
	        
        }
        catch (Exception e) {
        	System.out.println(e.toString()) ;
        }   
	}

    
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
   
    
}
