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
//import org.jdom.Element;
import org.sakaiproject.myshowcase.logic.IMyShowcaseService;
import org.sakaiproject.myshowcase.model.ArtefactMapping;
import org.sakaiproject.myshowcase.model.Owner;
import org.sakaiproject.myshowcase.model.Parameter;
import org.sakaiproject.myshowcase.tool.WebServiceConsumer;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.google.gson.Gson;


public class MyShowcaseGetTreeWSController extends AbstractController{

	final protected Log log = LogFactory.getLog(getClass());
	
	private String ownerId = "";
	
	private String artefactId = "";
	
	private String competencyId = "";
	
	private List<ArtefactMapping> artefactMappings; 
	
	private Owner owner =  null;
	
    private String returnType = "";
    
	private boolean started = false;
	
	private String outputString = "";
	
	private int nodeLevel = 0;	
	
	
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
			HttpServletResponse response) throws Exception{
		
		readRequest(request); 
		
	    owner =  myshowcaseService.getOwnerById(new Long(ownerId));
	    
		WebServiceConsumer wsc = new WebServiceConsumer();
		
		// Hard coded for now
		String studentId = "d289a01d-7cee-4569-a6c3-1a0a8e414582";
		
		String orgGroupId = "192";
		
		wsc.setURL("http://www.learning-slate.com/multiport/services/services.asmx");
		
		wsc.setWebService("Competency");

		wsc.clear();
		
		wsc.setWebMethod("GetTree");
		
		wsc.add(new Parameter("competencyId", competencyId));
		
		wsc.add(new Parameter("orgGroupId", orgGroupId));
		
		wsc.list();
		
		Document dom;
		
		dom = wsc.getXMLResults() ;
		
		if (returnType.equals("Checkbox")){
			
			if (competencyId.equalsIgnoreCase("default")){
				
				artefactMappings = null;
				
			} else{
				
				artefactMappings = myshowcaseService.getArtefactMappings(myshowcaseService.getArtefactById(new Long(artefactId)), new Long(competencyId));
			}
		}
		
		createOutputString(dom);
		
		System.out.println(getOutputString());
		
        ArrayList<Parameter> list = new ArrayList();
        
        list.add(new Parameter("0", getOutputString()));

	    response.setContentType("application/json");
	    
	    response.setCharacterEncoding("UTF-8");
	    
	    PrintWriter out = response.getWriter();
	    
	    out.write(new Gson().toJson(list)); 
	    
    	out.flush();
    	
		out.close();
	
		return null;
	}

	
	/**
	  * Extract parameters from the request.
	  * @param HttpServletRequest request
	  */   
    private void readRequest(HttpServletRequest request){
 		
		ownerId = request.getParameter("ownerId");
		
		artefactId = request.getParameter("artefactId");
		
		competencyId = request.getParameter("competencyId");
		
		returnType = request.getParameter("returnType");
    }		
	
	

	public String getOutputString(){
		
		return outputString ;
	}

	
	private boolean checked(Long id){
		
		boolean isChecked = false ;
		
		for (ArtefactMapping am : artefactMappings){
			
			if (am.getMappingId().equals(id)) isChecked = true ;
		}

		return isChecked ;
	}

	
	private int mappingCount(Long id){
		
		int aCount = 0;
		
		aCount = myshowcaseService.getArtefactMappingsCount(owner, new Long(competencyId), id);
		
		return aCount ;
	}

	
	private void processElement(Element e){
		
		String nodeClass = "";
		
		String nodeString = "";
		
		String checkedString = "";
		
		int aCount = 0;

		if (e.getTagName().equals("competency")){
			
			if (!started){
				
				nodeString = "<ul id=\"tree\"><li class=\"compNode\" id=\"" + e.getAttribute("id").toString() + "\">\n" ;
				
				started = true ;
				
			} else{
				
				nodeString = "<ul><li class=\"compNode\" id=\"" + e.getAttribute("id").toString() + "\">\n";
			}
			
			outputString += nodeString ;
			
			if (e.hasChildNodes()){
				
				nodeClass = "folder";
				
				nodeString = "\t<span class=\"" + nodeClass + "\">" + e.getAttribute("label").toString() + "</span>\n";
				
			} else{
				
				nodeClass = "file";
				
				if (returnType.equals("Finder")){
					
					aCount = mappingCount(new Long(e.getAttribute("id")));
					
					if (aCount > 0){
						nodeString = "\t<span class=\"" + nodeClass + "\"><a id=\"href" + e.getAttribute("id") + "\" href=\"#\" OnClick=\"MyShowcaseArtefact.loadArtefactsForMapping(" + competencyId + "," + e.getAttribute("id") +  ")\">" + e.getAttribute("label").toString() + "(" + aCount + ")</a>" ;
					}
					else{
						nodeString = "\t<span class=\"" + nodeClass + "\">" + e.getAttribute("label").toString();
					}	
				}
				
				if (returnType.equals("Checkbox")){
					
					nodeString = "\t<span class=\"" + nodeClass + "\">" + e.getAttribute("label").toString();
					
					if (checked(new Long(e.getAttribute("id")))){

						checkedString = " checked=\"yes\"";
					}
					else{
						
						checkedString = "";
					}
					
					nodeString += "<input type=\"checkbox\" name=\"chk" + artefactId + "-" + e.getAttribute("id") + "\" id=\"chk" + artefactId + "-" + e.getAttribute("id") + "\" " + "OnClick=\"MyShowcaseArtefact.updateCompetency(" + artefactId + "," + competencyId + "," + e.getAttribute("id") + ")\"" + checkedString + ">" ;
				}
				
				nodeString += "</span>\n" ;
			}
			
			outputString += nodeString ;
		}
		
        NodeList children = e.getChildNodes();
        
        for (int ii = 0 ; ii < children.getLength() ; ii++)
        {
            Node child = children.item(ii);
            
            if (child instanceof Element)
            	
                processElement((Element)child);
        }
        
		if (e.getTagName().equals("competency")){
			
			outputString += "</li></ul>\n" ;
		}
	}

	
	private void createOutputString(Document dom){
		
		Element e = dom.getDocumentElement();
		
		outputString = "";
		
		processElement(e);
	}
	
	
	
}