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
import org.sakaiproject.myshowcase.model.Artefact;
import org.sakaiproject.myshowcase.model.ArtefactMapping;
import org.sakaiproject.myshowcase.model.ArtefactMappingEvidenceItem;
import org.sakaiproject.myshowcase.model.Owner;
import org.sakaiproject.myshowcase.model.Parameter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.google.gson.Gson;

public class MyShowcaseGetShowcaseEvidenceMappingsController extends AbstractController{

	
	final protected Log log = LogFactory.getLog(getClass());
	
	private String ownerId = "" ;
	
	private String artefactId = "" ;
	
	private String competencyId = "" ;
	
	private List<ArtefactMapping> artefactMappings; 
	
    private ArrayList<Parameter> competencyList = new ArrayList();
    
    private ArrayList<Parameter> mappingList = new ArrayList();
    
	private Owner owner =  null ;
	   
	private boolean started = false ;
	
	private String outputString = "" ;

	
    private IMyShowcaseService myshowcaseService;
    public void setMyshowcaseService(IMyShowcaseService service){
        this.myshowcaseService = service;
    }


	public String getOutputString(){
		return outputString ;
	}

	
	private void processElement(Element e){
		
		String nodeClass = "";
		
		if (e.getTagName().equals("competency")){
			
			if (!started)
				
				started = true ;
			
			if (e.hasChildNodes()) {
				
				nodeClass = "folder" ;
				
			} else {
				
				nodeClass = "file" ;
				
		        mappingList.add(new Parameter(e.getAttribute("id").toString(), e.getAttribute("label").toString())) ;
			}
		}
		
        NodeList children = e.getChildNodes();
        
        for (int ii = 0 ; ii < children.getLength() ; ii++)
        {
            Node child = children.item(ii);
            
            if (child instanceof Element)
            	
                processElement((Element)child);
        }
	}

	
	private void createMappingList(Document dom){
		
		Element e = dom.getDocumentElement();
		
		outputString = "" ;
		
		processElement(e) ;
		
	}

	private void getCompetencyList(){
		
		try {
			
			WebServiceConsumer wsc = new WebServiceConsumer();
			
			String studentId = "d289a01d-7cee-4569-a6c3-1a0a8e414582" ;
			
			String orgGroupId = "192" ;
			
			wsc.setURL("http://www.learning-slate.com/multiport/services/services.asmx");
			
			wsc.setWebService("Competency");
	
			wsc.clear();
			
			wsc.setWebMethod("GetCompetenciesForStudent");
			
			wsc.add(new Parameter("studentId", studentId));
			
			System.out.println("Full results :\n" + wsc.getResults().toString());
			
			Document dom = wsc.getXMLResults();
			
			System.out.println("Version : " + dom.getXmlVersion().toString());
	
			Element docEle = dom.getDocumentElement();
	
			NodeList nl = docEle.getElementsByTagName("competency");
			
			if(nl != null && nl.getLength() > 0){
				
				System.out.println("Found some : " + nl.getLength());
				
				for (int i = 0 ; i < nl.getLength() ; i++){
					
					Element el = (Element)nl.item(i);
					
					System.out.println("Attribute : " + el.getAttribute("id").toString());
					
					System.out.println("Attribute : " + el.getAttribute("label").toString());
					
			        competencyList.add(new Parameter(el.getAttribute("id").toString(), el.getAttribute("label").toString())) ;
				}
			}
			
		} catch(Exception e){
			
			System.out.println(e.toString());
		}
	}

	
	private void getMappingList(Long compID){

		try {
			
			System.out.println("compID : " + compID);
			
			String orgGroupId = "192";
			
			WebServiceConsumer wsc = new WebServiceConsumer();
			
			wsc.setURL("http://www.learning-slate.com/multiport/services/services.asmx");
			
			wsc.setWebService("Competency") ;
	
			wsc.clear();
			
			wsc.setWebMethod("GetTree");
			
			wsc.add(new Parameter("competencyId", compID.toString()));
			
			wsc.add(new Parameter("orgGroupId", orgGroupId));
			
			wsc.list();
			
			System.out.println("Full results :\n" + wsc.getResults().toString());
			
			Document dom;
			
			dom = wsc.getXMLResults();
			
			System.out.println("Version : " + dom.getXmlVersion().toString());
			
			System.out.println("Still going");
			
			createMappingList(dom) ;
			
		} catch (Exception e) {
			
			System.out.println("GetMappingList Error...") ;
			
			System.out.println(e.toString()) ;
		}
	}

	
	private String competencyNameFromID(Long compID){
		
		String compName = "" ;
		
		for (Parameter p: competencyList){
			
			if (Integer.parseInt(p.param.toString()) == compID){
				
				compName = p.val.toString();
			}
		}
		
		return compName;
	}

	
	private String mappingDetailsFromID(Long mappingID){
		
		String mappingName = "";
		
		for (Parameter p: mappingList){
			
			if (Integer.parseInt(p.param.toString()) == mappingID){
				
				mappingName = p.val.toString();
			}
		}
		
		return mappingName;
	}

	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception{

		System.out.println("MyShowcaseGetShowcaseEvidenceMappingsController: Start..");
		
		String artefactId = request.getParameter("artefactId");
		
	    Artefact artefact  =  myshowcaseService.getArtefactById(new Long(artefactId));
	    
	    ArtefactMappingEvidenceItem item = new ArtefactMappingEvidenceItem();

	    List<ArtefactMapping> artefactMappings = myshowcaseService.getArtefactMappings(artefact);
	    
	    List<ArtefactMappingEvidenceItem> list = new ArrayList<ArtefactMappingEvidenceItem>();

	    getCompetencyList() ;
	    
        for (ArtefactMapping artefactMapping: artefactMappings){ 
        	
        	item = new ArtefactMappingEvidenceItem();
        	
        	item.setMappingId(artefactMapping.getMappingId());
        	
        	item.setCompetencyId(artefactMapping.getCompetencyId());
        	
        	System.out.println("Competency ID " + competencyNameFromID(artefactMapping.getCompetencyId()));
        	
        	item.setCompetency(competencyNameFromID(artefactMapping.getCompetencyId()));
        	
        	getMappingList(artefactMapping.getCompetencyId());
        	
        	System.out.println("Mapping ID " + mappingDetailsFromID(artefactMapping.getMappingId()));
        	
    	    item.setMapping(mappingDetailsFromID(artefactMapping.getMappingId()));
    	    
        	list.add(item);
        }
  
	    response.setContentType("application/json");
	    
	    response.setCharacterEncoding("UTF-8"); 
	    
	    PrintWriter out = response.getWriter();
	    
	    out.write(new Gson().toJson(list));
	    
    	out.flush();
    	
		out.close();
		
		System.out.println("MyShowcaseGetTreeWSController End") ;
	
		return null;
	}
	
}

