
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

package org.sakaiproject.myshowcase.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.sakaiproject.myshowcase.logic.IMyShowcaseService;
import org.sakaiproject.myshowcase.model.Artefact;
import org.sakaiproject.myshowcase.model.ArtefactDetail;
import org.sakaiproject.myshowcase.model.ArtefactType;
import org.sakaiproject.myshowcase.model.Owner;
import org.sakaiproject.myshowcase.config.*;


public class MyShowcaseFileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 2740693677625051632L;

	
    public MyShowcaseFileUploadServlet() {
    	
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	private String getRepositoryPath(String owner) {
			
		String rp ;
	
		MyShowcaseConfigValues configValues = new MyShowcaseConfigValues();		
	
		rp = configValues.getAbsoluteFileStore();
		
		rp = rp + owner ; //+ "\\" ;
		
		// Create folder if necessary
		File rd = new File(rp) ;
		
		if (!rd.exists()){

			rd.mkdir();

		}

		if (!rd.exists()){
			
			System.out.println("NB* ** Failed to create folder: " + rp);

		}
		
		// Put trailing / on the end of the repository path
		rp = rp + "/";
		
		return rp ;
	}

	
	private String getOwnerName(List fileItemsList) {
		
		String ownerName = "" ;
		
		Iterator it = fileItemsList.iterator();

		while (it.hasNext()){
			
			FileItem fileItem = (FileItem)it.next();
			
			if (fileItem.isFormField()){

				if (fileItem.getFieldName().equalsIgnoreCase("ownerName")){
					ownerName = fileItem.getString() ;
				}
			}
		}
		
		return ownerName ;
	}
	
	
	private String getOwnerId(List fileItemsList) {
		
		String ownerId = "" ;
		
		Iterator it = fileItemsList.iterator();

		while (it.hasNext()){
			
			FileItem fileItem = (FileItem)it.next();
			
			if (fileItem.isFormField()){

				if (fileItem.getFieldName().equalsIgnoreCase("ownerId")){
					
					ownerId = fileItem.getString();
				}	

			}
		}
		
		return ownerId ;
	}	
	
	private String getTitle(List fileItemsList) {
		
		String title = "" ;
		
		Iterator it = fileItemsList.iterator();

		while (it.hasNext()){
			
			FileItem fileItem = (FileItem)it.next();
			
			if (fileItem.isFormField()){

				if (fileItem.getFieldName().equalsIgnoreCase("input2")){
					
					title = fileItem.getString();
				}	

			}
		}
		
		return title ;
	}	

	
	private String getDescription(List fileItemsList) {
		
		String description = "" ;
		
		Iterator it = fileItemsList.iterator();

		while (it.hasNext()){
			
			FileItem fileItem = (FileItem)it.next();
			
			if (fileItem.isFormField()){

				if (fileItem.getFieldName().equalsIgnoreCase("input4")){
					
					description = fileItem.getString();
				}	

			}
		}
		
		return description;
	}		

	
	private String getDataValue(List fileItemsList) {
		
		String dataValue = "" ;
		
		Iterator it = fileItemsList.iterator();

		while (it.hasNext()){
			
			FileItem fileItem = (FileItem)it.next();

				if (fileItem.getFieldName().equalsIgnoreCase("input1")){
					
					dataValue = fileItem.getName();
				}	

		}
		
		return dataValue;
	}			

	
	private String getType(List fileItemsList) {
		
		String type = "" ;
		
		Iterator it = fileItemsList.iterator();

		while (it.hasNext()){
			
			FileItem fileItem = (FileItem)it.next();
			
			if (fileItem.isFormField()){

				if (fileItem.getFieldName().equalsIgnoreCase("type")){
					
					type = fileItem.getString();
				}	

			}
		}
		
		return type;
	}			
	
	
	private String getFileType(String fName) {
		
		String fType ;
		
		String sFile[] ;
		
		sFile = fName.split("\\.") ;
		
		fType = sFile[sFile.length-1].toLowerCase() ;
		
		return fType ;
	}
	
	
	private boolean isValidFileType(String fName) {
		
		boolean isValid = false;
		
		String fileType = getFileType(fName) ;
		
		List<String> validFileTypes = MyShowcaseConfigValues.getInstance().getValidFileTypes();
		
		for (String type  : validFileTypes){
			
			if(fileType.equals(type)){
				
				isValid = true;

			}
		}
		
		return isValid;
	}	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        try {

        	List fileItemsList = null; 
        	
        	String input1 = request.getParameter("input1");
        	String input2 = request.getParameter("input2");
        	String input4 = request.getParameter("input4");
        	
	        if (ServletFileUpload.isMultipartContent(request)) {

				ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
				
				fileItemsList = servletFileUpload.parseRequest(request);
				
				DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
				
				diskFileItemFactory.setSizeThreshold(40960); /* the unit is bytes */

	        	String homeDirId = MyShowcaseConfigValues.getInstance().getFileUploadHomeDir();
	
	        	File repositoryPath = null;

	        	String homeDir = "";
	        	
				if (homeDirId.equals("ownerName")){
					
				    homeDir = getOwnerName(fileItemsList) ;
		
		        	
				} else if (homeDirId.equals("ownerId")){
					
				    homeDir = getOwnerId(fileItemsList) ;

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
						
						fileName = getRepositoryPath(homeDir) + fileName ;

						File uploadedFile = new File(fileName) ;
						
						fileItem.write(uploadedFile) ;

						isValidFileType(fileName); 

					}
				}
				
	        } else {
	        	
	        	System.out.println("Not Multipart Request") ;
	        }
	       
	        IMyShowcaseService myshowcaseService = getMyShowcaseService();
     
			String ownerId = getOwnerId(fileItemsList);
			
	        Owner owner =  myshowcaseService.getOwnerById(new Long(ownerId));
			
	        String aType = getType(fileItemsList);
	        
			String aTitle = getTitle(fileItemsList);
			
			String aDescription = getDescription(fileItemsList);
			
			String aDataValue = getDataValue(fileItemsList);
			
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
	        
        }
        catch (Exception e) {
        	System.out.println(e.toString()) ;
        }

//        response.setContentType("application/json");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8"); 

        PrintWriter out = response.getWriter();
       
        out.write("<html>");
        out.write("<head>");
        out.write("<title>File Upload</title>");
        out.write("</head>");
        out.write("<body>");
        out.write("File uploaded"); 
        out.write("<script type=\"text/javascript\">");
        out.write("parent.$.fancybox.close();");
        out.write("</script>");
        out.write("</body>"); 
        out.write("</html>"); 
        out.flush();
        out.close();

      }
	
	
    private IMyShowcaseService myshowcaseService;
    public void setMyshowcaseService(IMyShowcaseService service) {

        this.myshowcaseService = service;
        	
    }
    
    private IMyShowcaseService getMyShowcaseService()  
    {  
        ServletContext servletContext = this.getServletContext();  
    
        return (IMyShowcaseService) servletContext  
            .getAttribute("MyShowcaseServiceInServletContext");  
    }  


}
