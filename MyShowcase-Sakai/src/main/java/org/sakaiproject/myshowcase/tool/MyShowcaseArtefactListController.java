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
import org.sakaiproject.genericdao.api.search.Order;
import org.sakaiproject.myshowcase.logic.*;
import org.sakaiproject.myshowcase.model.*;
import com.google.gson.Gson;
import java.io.PrintWriter;
import java.util.*;

public class MyShowcaseArtefactListController extends AbstractController{

	final protected Log log = LogFactory.getLog(getClass());
	
	private String ownerId = "";
	
	private String tagValue = "";
	
	private String typeValue = "";
	
	private String orderBy = "";
	
	private String searchTerm = "";
	
	private String competencyId = "";
	
	private String mappingId = "";
	
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
		
        Owner owner =  myshowcaseService.getOwnerById(new Long(ownerId));
	
        // Read for Artefact list
        
		List<Artefact> artefacts = new ArrayList<Artefact>();
		
		List<Artefact> artefactList =  new ArrayList<Artefact>();
		
		// false sets the order to descending
		Order createdDateOrder = new Order("createdDate",false);
		
		Order[] order = {};
		
        if ((orderBy.equals("null")) || (orderBy.equals("") || (orderBy.equals("createdDate")))){
        	
            Order[] defaultOrder = {createdDateOrder};
            
            order = defaultOrder;

        }
        else{	
        	
            Order order2 = new Order(orderBy);
            
            Order[] listOrder = {order2,createdDateOrder};
            
            order = listOrder;
        }

        // if a term search
        if (isTermSearch(searchTerm)){

        	artefactList =  myshowcaseService.getArtefactsBySearchTerm(owner, order, searchTerm);
        }
        
        // if a tag search
        else if (isTagSearch(tagValue)){

        	Tag tag = myshowcaseService.getTag(tagValue,owner);

        	artefactList =  myshowcaseService.getArtefacts(tag);
        	
        	if (orderBy.equals("type")){
        		
        		int index = 0;
        		
        		Map<String,Artefact> orderedArtefacts = new HashMap<String,Artefact>();
        		
        		for (Artefact artefact  : artefactList) {
        			
        			index++;
        			
        			orderedArtefacts.put(artefact.getType().getName() + index, artefact);
         		}
         		
        		SortedSet<String> sortedset= new TreeSet<String>(orderedArtefacts.keySet());
        		
        		Iterator<String> it = sortedset.iterator();
        		
        		artefactList.clear();
        		
         		String keyVal = "";
        		
        		while (it.hasNext()) {
        			
        			keyVal = it.next();
  		
        			artefactList.add(orderedArtefacts.get(keyVal));
         		}
        	}
			
        }
        else if (!(competencyId.equals("null"))){

        	artefactList =  myshowcaseService.getEvidenceArtefacts(owner, new Long(competencyId), new Long(mappingId));

       	 } else {
			
        	artefactList =  myshowcaseService.getArtefacts(owner,order,tagValue,typeValue);

       	 }
         
	    // Process Artefact List
		Artefact jsonArtefact = null;
		
	    ArtefactDetail jsonArtefactDetail = null;
	    
	    ArtefactType jsonArtefactType = null;

	    for (Artefact artefact  : artefactList){
	    	
        	jsonArtefactType = new ArtefactType();
        	
	    	jsonArtefactDetail = new ArtefactDetail();
	    	
	    	jsonArtefact = artefact.getDeepCopy();
	    	
	    	jsonArtefact.setArtefactId(artefact.getArtefactId());
	    	
	    	jsonArtefact.setDescription(artefact.getDescription());
	    	
	    	jsonArtefact.setName(artefact.getName());
	    	
	    	jsonArtefact.setCreatedDate(artefact.getCreatedDate());
	    	
	    	jsonArtefact.setCreatedDateTime(artefact.getCreatedDate()) ;
	    	
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
    private void readRequest(HttpServletRequest request) {
		
		ownerId = request.getParameter("ownerId");
		
		tagValue = request.getParameter("tag");
		
		typeValue = request.getParameter("type");
		
		orderBy = request.getParameter("order");
		
		searchTerm = request.getParameter("searchTerm");
		
		competencyId = request.getParameter("competencyId");
		
		mappingId = request.getParameter("mappingId");
		
		if (orderBy == null){
			orderBy = "null";
		};

		if (competencyId == null){
			competencyId = "null";
		};
		
		if (mappingId == null){
			mappingId = "null";
		};
		
//		if (ownerId == null){
//			ownerId = "";
//		};	
		
//		if (tagValue == null){
//			tagValue = "";
//		};
		
//		if (typeValue == null){
//			typeValue = "";
//		};	
		
//		if (searchTerm == null){
//			searchTerm = "";
//		};		
    }
	
    
    
	/**
	  * Determine if the Artefact list is based on a tag search
	  * @param String tag values to search for eg BBC or baby
	  */ 
	private boolean isTagSearch(String value){
		
		boolean isTag = false;
		
		if (value != null){
	        	
			if ((!value.equals("")) && (!value.equals("null"))){
				
				isTag = true;
			}
		}
			
		return isTag;
	}

	
	/**
	  * Determine if the Artefact list is based on a term search
	  * @param String term to search for eg BBC or baby
	  */  	
	private boolean isTermSearch(String value){
		
		boolean isTerm = false;
		
		if (value != null){
	        	
			if ((!value.equals("")) && (!value.equals("null"))){
				
				isTerm = true;
			}
		}
			
		return isTerm;
	}

}
