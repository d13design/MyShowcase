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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.myshowcase.logic.IMyShowcaseService;
import org.sakaiproject.myshowcase.model.Parameter;
import org.sakaiproject.myshowcase.tool.WebServiceConsumer;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import com.google.gson.Gson;


public class MyShowcaseGetCompetenciesForStudentWSController extends AbstractController {

	final protected Log log = LogFactory.getLog(getClass());

	
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
		
		readRequest(request);
		
		WebServiceConsumer wsc = new WebServiceConsumer();
		
		String studentId = "d289a01d-7cee-4569-a6c3-1a0a8e414582";
		
		wsc.setURL("http://www.learning-slate.com/multiport/services/services.asmx");
		
		wsc.setWebService("Competency");

		wsc.clear();
		
		wsc.setWebMethod("GetCompetenciesForStudent");
		
		wsc.add(new Parameter("studentId", studentId));
		
		Document dom = wsc.getXMLResults();
		
		Element docEle = dom.getDocumentElement();

        ArrayList<Parameter> list = new ArrayList<Parameter>();
        
		NodeList nl = docEle.getElementsByTagName("competency");
		
		if(nl != null && nl.getLength() > 0){
			
			for (int i = 0 ; i < nl.getLength() ; i++){
				
				Element el = (Element)nl.item(i);
				
		        list.add(new Parameter(el.getAttribute("id").toString(), el.getAttribute("label").toString()));
			}
		}
        
		nl = docEle.getElementsByTagName("error");
		
		if(nl != null && nl.getLength() > 0){
			
			for (int i = 0 ; i < nl.getLength() ; i++){
				
				Element el = (Element)nl.item(i);
				
		        list.add(new Parameter("Error", el.getAttribute("exception").toString()));
			}
		}
		
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
 		
//		ownerId = request.getParameter("ownerId");

    }	
}