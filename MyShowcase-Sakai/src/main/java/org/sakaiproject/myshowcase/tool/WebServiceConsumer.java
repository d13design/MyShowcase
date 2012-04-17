
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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.sakaiproject.myshowcase.model.Parameter;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class WebServiceConsumer {

//	Test code
//	GetCompetenciesForStudent studentId = d289a01d-7cee-4569-a6c3-1a0a8e414582
//	GetTree competencyId = 1315 orgGroupId = 192
//	URL = http://www.learning-slate.com/multiport/services/services.asmx/Competency.
    private String URL ;
    private String method ;
    private String ws ;
    
    private ArrayList<Parameter> prms = new ArrayList<Parameter>() ;
    
    public WebServiceConsumer() {
		System.out.println("New WebServiceConsumer") ;
	}

    public String getURL() {
    	return URL ;
    }
    
    public void setURL(String u) {
    	URL = u ;
    }

    public String getWebService() {
    	return ws ;
    }
    
    public void setWebService(String w) {
    	ws = w ;
    }
    
    public String getWebMethod() {
    	return method ;
    }
    
    public void setWebMethod(String m) {
    	method = m ;
    }
    
    public void add(Parameter p) {
    	prms.add(p) ;
    }

    public void list() {
    	Iterator<Parameter> i = prms.iterator() ;
    	while (i.hasNext()) {
    		Parameter p = i.next() ;
    		System.out.println(p.param + ":" + p.val) ;
    	}
    }

    public void clear() {
    	prms.clear() ;
    }
    
    public int size() {
    	return prms.size() ;
    }
    
    private String fullServiceString() {
		String serviceString = URL + "/" + ws + "." + method ;
		if (prms.size() > 0) {
	    	Iterator<Parameter> i = prms.iterator() ;
	    	int ii = 0 ;
	    	while (i.hasNext()) {
	    		Parameter p = i.next() ;
	    		char c = (ii == 0 ? '?' : '&') ;
		    	serviceString += c + p.param + "=" + p.val ;
	    		ii++ ;
	    	}
			
		}
    	return serviceString ;
    }
    public String getResults() {
    	
		try {
			
			URL u = new URL(fullServiceString());
			
			BufferedReader in = new BufferedReader(
					
					new InputStreamReader(
							
					u.openStream()));

			String inputLine;
			
			StringBuffer outputXML = new StringBuffer();
			
			while ((inputLine = in.readLine()) != null)
				
			    outputXML.append(inputLine + "\n");
	
			in.close(); 
			
			return outputXML.toString() ;
			
		} catch (Exception e) {
			return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<message>\n\t<error exception=\"" + e.toString() + "\"/>\n</message>" ;
		}
	}

    public Document getXMLResults() throws Exception {
    	
    	Document doc = null ;
    	
        DocumentBuilderFactory docBuilderFactory ;
        
        DocumentBuilder docBuilder ;
    	
        docBuilderFactory = DocumentBuilderFactory.newInstance();
        
        docBuilderFactory.setIgnoringComments(true);
        
        docBuilderFactory.setValidating(false);
        
        docBuilder = docBuilderFactory.newDocumentBuilder();

    	try {
    		
            URL u = new URL(fullServiceString());
            
			DataInputStream in = new DataInputStream(new BufferedInputStream(u.openStream()));

            doc = docBuilder.parse(in);

			in.close();
			
		} catch (Exception e) {
			
			String ds = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<message>\n\t<error exception=\"" + e.toString().replace("&", "&amp;") + "\"/>\n</message>";
			
			doc = docBuilder.parse(new InputSource(new StringReader(ds))) ;
		}
		return doc ;
	}
}

