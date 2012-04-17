
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
package org.sakaiproject.myshowcase.config; 
/*
 * Import Statements
 */
import java.util.Properties;
import java.net.URL;
 
/**
 * Utility for loading configuration details 
 * @author MKM
 */
public class ConfigUtils {
 
	/**
	  * Private constructor to disallow instantiation
	  */
	private ConfigUtils(){
	}

	
    /**
      * Load a properties file from the classpath
      * @param propsName
      * @return Properties
      * @throws Exception
      */
    public static Properties loadProperties(String propsName)
        throws Exception {
		
		Properties props = new Properties();
		
        URL url = ConfigUtils.class.getResource(propsName);
        
        props.load(url.openStream());
        
        return props;
    }
 
    
    /**
      * Load a MyShowcaseConfig instance with a supplied configuration file taken from the classpath.
      * For example, org.sakaiproject.myshowcase.config.config.xml
      * @param configName
      * @return MyShowcaseConfig 
      * @throws Exception
      */
    public static MyShowcaseConfig loadConfig(String configName)
        throws Exception{

        MyShowcaseConfig msc = new MyShowcaseConfig(ConfigUtils.class.getResource(configName).toString());

        return msc;
    }
 
    
    /**
      * Load the main MyShowcase configuration file (myshowcaseConfig.xml)
      * @return MyShowcaseConfig
      * @throws Exception
      */
    public static MyShowcaseConfig loadMyShowcaseConfig()
        throws Exception{
        
    	String catalinaHome = System.getenv("CATALINA_HOME");

    	if ((catalinaHome == null) || (catalinaHome.equals("null"))){
    		
    		catalinaHome = System.getProperty("catalina.home");
    	}
		
    	String configLocation = catalinaHome + "/MyShowcase/myshowcaseConfig.xml";
    	
    	MyShowcaseConfig msc = new MyShowcaseConfig(configLocation);

        return msc;
    }
   
}