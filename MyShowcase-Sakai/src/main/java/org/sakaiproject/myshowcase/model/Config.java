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

package org.sakaiproject.myshowcase.model;

import java.io.IOException;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Config {

	private String configFilename ;
	
	public Config () {
		setConfigFilename("") ;
	}
	
	public Config (String fn) {
		setConfigFilename(fn) ;
	}
	
	void setConfigFilename (String fn) {
		configFilename = fn ;
	}
	
	public String getConfigFilename () {
		return configFilename ;
	}

	private String getParameterValue(Element el, String pn) {
		String pv = "";
		NodeList nl = el.getElementsByTagName(pn);
		if(nl != null && nl.getLength() > 0) {
			Element ele = (Element)nl.item(0);
			pv = ele.getFirstChild().getNodeValue();
		}
		return pv ;
	}

	public String getParameter(String tg, String pn) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		String pValue = "" ;

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(getConfigFilename());
			Element docEle = dom.getDocumentElement();

			NodeList nl = docEle.getElementsByTagName(tg);
			if(nl != null && nl.getLength() > 0) {
				// Always get the first value
				Element el = (Element)nl.item(0);
				System.out.println("Tag " + tg + " exists") ;
				pValue = getParameterValue(el, pn) ;
			} else {
				System.out.println("Tag " + tg + " does not exist") ;
			}

		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pValue ;
	}
}
