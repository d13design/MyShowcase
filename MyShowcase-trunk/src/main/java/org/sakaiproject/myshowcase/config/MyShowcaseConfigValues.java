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


import java.util.ArrayList;
import java.util.List;


public class MyShowcaseConfigValues {

	
    /**
      * Get an instance of MyShowcaseConfigValues
      * @return MyShowcaseConfigValues
      */	   
    public static MyShowcaseConfigValues getInstance(){
    	
		MyShowcaseConfigValues myShowcaseConfigValues = new MyShowcaseConfigValues();
   	
    	return myShowcaseConfigValues;
  	
   	}

    
    /**
      * Retrieve the file store address.
      * @return String FileStore address 
      */   
    public String getFileStore(){
    	
		MyShowcaseConfig msc = null;
		
		String fileStore = null;
		
		try {
			
			msc = ConfigUtils.loadConfig(MyShowcaseConfigResource.MyShowcaseConfig.resourceName());
			
	        fileStore = "../webapps" + msc.getParameter(MyShowcaseConfigIdentifier.ConfigFileUpload.identifier(), MyShowcaseConfigIdentifier.ConfigFileUploadUploadDir.identifier()) ;
	        
		}
		catch (Exception ex){
			
			System.out.println("Exception MyShowcaseConfigValues.getFileStore(): " + ex.getMessage());
		}
   	
    	return fileStore;
   	}

    
    /**
      * Retrieve the upload directory.
      * @return String upload directory value
      */   
    public String getUploadDir(){
    	
		MyShowcaseConfig msc = null;
		
		String fileStore = null;
		
		try {
			
			msc = ConfigUtils.loadMyShowcaseConfig();
			
	        fileStore =  msc.getParameter(MyShowcaseConfigIdentifier.ConfigFileUpload.identifier(), MyShowcaseConfigIdentifier.ConfigFileUploadUploadDir.identifier()) ;

		}
		catch (Exception ex){
			
			System.out.println("Exception MyShowcaseConfigValues.getUploadDir(): " + ex.getMessage());
		}
   	
    	return fileStore;
  	
   	}    

    
    /**
      * Retrieve the absolute file store location.
      * @return String Absolute value of the file store address
      */	   
    public String getAbsoluteFileStore(){
    	
		MyShowcaseConfig msc = null;
		
		String fileStore = null;
		
		try {

	    	String catalinaHome = System.getenv("CATALINA_HOME");

	    	if ((catalinaHome == null) || (catalinaHome.equals("null"))){
	    		
	    		catalinaHome = System.getProperty("catalina.home");
	    	}
			
			msc = ConfigUtils.loadMyShowcaseConfig();
			
	        fileStore =  catalinaHome +"/webapps/" + msc.getParameter(MyShowcaseConfigIdentifier.ConfigFileUpload.identifier(), MyShowcaseConfigIdentifier.ConfigFileUploadUploadDir.identifier()) ;

		}
		catch (Exception ex){
			
			System.out.println("Exception MyShowcaseConfigValues.getAbsoluteFileStore(): " + ex.getMessage());
		}
   	
    	return fileStore;
  	
   	}    
 
    
    /**
      * Retrieve a collection of valid file types that can be saved as Artefact's.  
      * @return List Collection of file types
      */   
    public List<String> getValidFileTypes(){
    	
		MyShowcaseConfig msc = null;

		List<String> values = new ArrayList<String>();
		
		try {
			
			msc = ConfigUtils.loadMyShowcaseConfig();
			
	        values = msc.getParameterList(MyShowcaseConfigIdentifier.ConfigFileUpload.identifier(), MyShowcaseConfigIdentifier.ConfigFileUploadValidFileType.identifier()) ;

		}
		catch (Exception ex){
			
			System.out.println("Exception MyShowcaseConfigValues.getValidFileTypes(): " + ex.getMessage());
		}
   	
    	return values;
  	
   	}  

    
    /**
      * Retrieve the email server identifier
      * @return String email server
      */	   
    public String getEmailServer(){
    	
		MyShowcaseConfig msc = null;
		
		String emailServer = null;
		
		try {
			
			msc = ConfigUtils.loadMyShowcaseConfig();
			
	        emailServer =  msc.getParameter(MyShowcaseConfigIdentifier.ConfigEmail.identifier(), MyShowcaseConfigIdentifier.ConfigEmailSMTPServer.identifier()) ;

		}
		catch (Exception ex){
			
			System.out.println("Exception MyShowcaseConfigValues.getEmailServer(): " + ex.getMessage());
		}
   	
    	return emailServer;
  	
   	}       

    
    /**
      * Retrieve the email from name.
      * @return String email from name
      */   
    public String getEmailFromName(){
    	
		MyShowcaseConfig msc = null;
		
		String emailFromName = null;
		
		try {
			
			msc = ConfigUtils.loadMyShowcaseConfig();
			
	        emailFromName =  msc.getParameter(MyShowcaseConfigIdentifier.ConfigEmail.identifier(), MyShowcaseConfigIdentifier.ConfigEmailFromname.identifier()) ;

		}
		catch (Exception ex){
			
			System.out.println("Exception MyShowcaseConfigValues.getEmailFromName(): " + ex.getMessage());
		}
   	
    	return emailFromName;
  	
   	}          

    
    /**
      * Retrieve the email subject
      * @return String email subject
      */   
    public String getEmailSubject(){
    	
		MyShowcaseConfig msc = null;
		
		String emailSubject = null;
		
		try {
			
			msc = ConfigUtils.loadMyShowcaseConfig();
			
	        emailSubject =  msc.getParameter(MyShowcaseConfigIdentifier.ConfigEmail.identifier(), MyShowcaseConfigIdentifier.ConfigEmailSubjectPublishedShowcase.identifier()) ;

		}
		catch (Exception ex){
			
			System.out.println("Exception MyShowcaseConfigValues.getEmailSubject(): " + ex.getMessage());
		}
   	
    	return emailSubject;
  	
   	}        
    

    
    /**
      * Retrieve the application host.
      * @return String application host
      */   
    public String getApplicationHost(){
    	
		MyShowcaseConfig msc = null;
		
		String applicationHost = null;
		
		try {
			
			msc = ConfigUtils.loadMyShowcaseConfig();
			
	        applicationHost =  msc.getParameter(MyShowcaseConfigIdentifier.ConfigApplciation.identifier(), MyShowcaseConfigIdentifier.ConfigApplciationHostname.identifier()) ;

		}
		catch (Exception ex){
			
			System.out.println("Exception MyShowcaseConfigValues.getApplicationHost(): " + ex.getMessage());
		}
   	
    	return applicationHost;
  	
   	} 
 
    
    /**
      * Retrieve the Showcase review base url 
      * @return String Showcase review base url
      */   
    public String getShowcaseReviewBaseUrl(){
    	
		MyShowcaseConfig msc = null;
		
		String baseUrl = null;
		
		try {
			
			msc = ConfigUtils.loadConfig(MyShowcaseConfigResource.MyShowcaseConfig.resourceName());
			
	        baseUrl = msc.getParameter(MyShowcaseConfigIdentifier.ConfigShowcaseReview.identifier(), MyShowcaseConfigIdentifier.ConfigShowcaseReviewBaseUrl.identifier()) ;
	        
		}
		catch (Exception ex){
			
			System.out.println("Exception MyShowcaseConfigValues.getShowcaseReviewBaseUrl(): " + ex.getMessage());
		}		
  	
    	return baseUrl;
  	
   	}    
 
    
    /**
      * Retrieve the Flickr key.
      * @return String Flickr key
      */   
    public String getFlickrKey(){
    	
		MyShowcaseConfig msc = null;
		
		String flickrKey = null;
		
		try {
			
			msc = ConfigUtils.loadConfig(MyShowcaseConfigResource.MyShowcaseConfig.resourceName());
			
	        flickrKey = msc.getParameter(MyShowcaseConfigIdentifier.ConfigFlickr.identifier(), MyShowcaseConfigIdentifier.ConfigFlickrKey.identifier()) ;
	        
		}
		catch (Exception ex){
			
			System.out.println("Exception MyShowcaseConfigValues.getFlickrKey(): " + ex.getMessage());
		}		
  	
    	return flickrKey;
  	
   	}       

    
    /**
      * Retrieve the secret key for Flickr.
      * @return String Flickr secret key
      */   
    public String getFlickrSecret(){
    	
		MyShowcaseConfig msc = null;
		
		String flickrSecret = null;
		
		try {
			
			msc = ConfigUtils.loadConfig(MyShowcaseConfigResource.MyShowcaseConfig.resourceName());
			
	        flickrSecret = msc.getParameter(MyShowcaseConfigIdentifier.ConfigFlickr.identifier(), MyShowcaseConfigIdentifier.ConfigFlickrSecret.identifier()) ;
	        
		}
		catch (Exception ex){
			
			System.out.println("Exception MyShowcaseConfigValues.getFlickrSecret(): " + ex.getMessage());
		}		
  	
    	return flickrSecret;
  	
   	} 

    
    /**
      * Get an instance of MyShowcaseConfigValues
      * @return MyShowcaseConfigValues
      */    
    public String getFileUploadHomeDir(){
    	
		MyShowcaseConfig msc = null;
		
		String homeDir = null;
		
		try {
			
			msc = ConfigUtils.loadConfig(MyShowcaseConfigResource.MyShowcaseConfig.resourceName());
			
	        homeDir = msc.getParameter(MyShowcaseConfigIdentifier.ConfigFileUpload.identifier(), MyShowcaseConfigIdentifier.ConfigFileUploadHomeDir.identifier()) ;
	        
		}
		catch (Exception ex){
			
			System.out.println("Exception MyShowcaseConfigValues.getFileUploadHomeDir(): " + ex.getMessage());
		}		
  	
    	return homeDir;
  	
   	}        
    
}
