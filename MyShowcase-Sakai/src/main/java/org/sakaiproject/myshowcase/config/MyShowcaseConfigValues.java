package org.sakaiproject.myshowcase.config;

import java.util.ArrayList;
import java.util.List;

import org.sakaiproject.myshowcase.model.Artefact;

public class MyShowcaseConfigValues {
	
	   
    public static MyShowcaseConfigValues getInstance(){
    	
		MyShowcaseConfigValues myShowcaseConfigValues = new MyShowcaseConfigValues();
   	
    	return myShowcaseConfigValues;
  	
   	}
	
    
    public String getFileStore(){
    	
		MyShowcaseConfig msc = null;
		
		String fileStore = null;
		
		try {
			
			msc = ConfigUtils.loadConfig(MyShowcaseConfigResource.MyShowcaseConfig.resourceName());
			
	        fileStore = "../webapps" + msc.getParameter(MyShowcaseConfigIdentifier.ConfigFileUpload.identifier(), MyShowcaseConfigIdentifier.ConfigFileUploadUploadDir.identifier()) ;
	        
		}
		catch (Exception ex){
				System.out.println("++++ Unable to loadMyShowcaseConfig ++++++++");
		}
   	
    	return fileStore;
  	
   	}
	   
    public String getUploadDir(){
    	
		MyShowcaseConfig msc = null;
		
		String fileStore = null;
		
		try {
			
			msc = ConfigUtils.loadMyShowcaseConfig();
			
	        fileStore =  msc.getParameter(MyShowcaseConfigIdentifier.ConfigFileUpload.identifier(), MyShowcaseConfigIdentifier.ConfigFileUploadUploadDir.identifier()) ;

		}
		catch (Exception ex){
				System.out.println("++++ Unable to loadMyShowcaseConfig ++++++++");
		}
   	
    	return fileStore;
  	
   	}    

	   
    public String getAbsoluteFileStore(){
    	
		MyShowcaseConfig msc = null;
		
		String fileStore = null;
		
		try {

	    	String catalinaHome = System.getenv("CATALINA_HOME");

	    	if ((catalinaHome == null) || (catalinaHome.equals("null"))){
	    		
	    		catalinaHome = System.getProperty("catalina.home");
	    		
	    		if ((catalinaHome == null) || (catalinaHome.equals("null"))){
	     		
	    			catalinaHome = "/opt/apache-tomcat-5.5.30";
	    		}
	    	}
	    	
	    	System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");	    	
			System.out.println("NB NB NB ++++ CATALINA_HOME = " + catalinaHome);			
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
			
			msc = ConfigUtils.loadMyShowcaseConfig();
			
	        fileStore =  catalinaHome +"/webapps/" + msc.getParameter(MyShowcaseConfigIdentifier.ConfigFileUpload.identifier(), MyShowcaseConfigIdentifier.ConfigFileUploadUploadDir.identifier()) ;

		}
		catch (Exception ex){
			
				System.out.println("++++ Unable to loadMyShowcaseConfig ++++++++");
		}
   	
    	return fileStore;
  	
   	}    
      
    
    public List<String> getValidFileTypes(){
    	
		MyShowcaseConfig msc = null;

		List<String> values = new ArrayList<String>();
		
		try {
			
			msc = ConfigUtils.loadMyShowcaseConfig();
			
	        values = msc.getParameterList(MyShowcaseConfigIdentifier.ConfigFileUpload.identifier(), MyShowcaseConfigIdentifier.ConfigFileUploadValidFileType.identifier()) ;

		}
		catch (Exception ex){
				System.out.println("++++ Unable to loadMyShowcaseConfig ++++++++");
		}
   	
    	return values;
  	
   	}  


	   
    public String getEmailServer(){
    	
		MyShowcaseConfig msc = null;
		
		String emailServer = null;
		
		try {
			
			msc = ConfigUtils.loadMyShowcaseConfig();
			
	        emailServer =  msc.getParameter(MyShowcaseConfigIdentifier.ConfigEmail.identifier(), MyShowcaseConfigIdentifier.ConfigEmailSMTPServer.identifier()) ;

		}
		catch (Exception ex){
				System.out.println("++++ Unable to loadMyShowcaseConfig ++++++++");
		}
   	
    	return emailServer;
  	
   	}       

    
    public String getEmailFromName(){
    	
		MyShowcaseConfig msc = null;
		
		String emailFromName = null;
		
		try {
			
			msc = ConfigUtils.loadMyShowcaseConfig();
			
	        emailFromName =  msc.getParameter(MyShowcaseConfigIdentifier.ConfigEmail.identifier(), MyShowcaseConfigIdentifier.ConfigEmailFromname.identifier()) ;

		}
		catch (Exception ex){
				System.out.println("++++ Unable to loadMyShowcaseConfig ++++++++");
		}
   	
    	return emailFromName;
  	
   	}          
    
    public String getEmailSubject(){
    	
		MyShowcaseConfig msc = null;
		
		String emailSubject = null;
		
		try {
			
			msc = ConfigUtils.loadMyShowcaseConfig();
			
	        emailSubject =  msc.getParameter(MyShowcaseConfigIdentifier.ConfigEmail.identifier(), MyShowcaseConfigIdentifier.ConfigEmailSubjectPublishedShowcase.identifier()) ;

		}
		catch (Exception ex){
				System.out.println("++++ Unable to loadMyShowcaseConfig ++++++++");
		}
   	
    	return emailSubject;
  	
   	}        
    
    
    public String getApplicationHost(){
    	
		MyShowcaseConfig msc = null;
		
		String applicationHost = null;
		
		try {
			
			msc = ConfigUtils.loadMyShowcaseConfig();
			
	        applicationHost =  msc.getParameter(MyShowcaseConfigIdentifier.ConfigApplciation.identifier(), MyShowcaseConfigIdentifier.ConfigApplciationHostname.identifier()) ;

		}
		catch (Exception ex){
				System.out.println("++++ Unable to loadMyShowcaseConfig ++++++++");
		}
   	
    	return applicationHost;
  	
   	} 
   
    
    public String getShowcaseReviewBaseUrl(){
    	
		MyShowcaseConfig msc = null;
		
		String baseUrl = null;
		
		try {
			
			msc = ConfigUtils.loadConfig(MyShowcaseConfigResource.MyShowcaseConfig.resourceName());
			
	        baseUrl = msc.getParameter(MyShowcaseConfigIdentifier.ConfigShowcaseReview.identifier(), MyShowcaseConfigIdentifier.ConfigShowcaseReviewBaseUrl.identifier()) ;
	        
		}
		catch (Exception ex){
				System.out.println("++++ Unable to loadMyShowcaseConfig ++++++++");
		}		
  	
    	return baseUrl;
  	
   	}    
 
    
    public String getFileUploadHomeDir(){
    	
		MyShowcaseConfig msc = null;
		
		String homeDir = null;
		
		try {
			
			msc = ConfigUtils.loadConfig(MyShowcaseConfigResource.MyShowcaseConfig.resourceName());
			
	        homeDir = msc.getParameter(MyShowcaseConfigIdentifier.ConfigFileUpload.identifier(), MyShowcaseConfigIdentifier.ConfigFileUploadHomeDir.identifier()) ;
	        
		}
		catch (Exception ex){
				System.out.println("++++ Unable to loadMyShowcaseConfig ++++++++");
		}		
  	
    	return homeDir;
  	
   	}    
    
    public String getFlickrKey(){
    	
		MyShowcaseConfig msc = null;
		
		String flickrKey = null;
		
		try {
			
			msc = ConfigUtils.loadConfig(MyShowcaseConfigResource.MyShowcaseConfig.resourceName());
			
	        flickrKey = msc.getParameter(MyShowcaseConfigIdentifier.ConfigFlickr.identifier(), MyShowcaseConfigIdentifier.ConfigFlickrKey.identifier()) ;
	        
		}
		catch (Exception ex){
				System.out.println("++++ Unable to loadMyShowcaseConfig ++++++++");
		}		
  	
    	return flickrKey;
  	
   	}       

    
    public String getFlickrSecret(){
    	
		MyShowcaseConfig msc = null;
		
		String flickrSecret = null;
		
		try {
			
			msc = ConfigUtils.loadConfig(MyShowcaseConfigResource.MyShowcaseConfig.resourceName());
			
	        flickrSecret = msc.getParameter(MyShowcaseConfigIdentifier.ConfigFlickr.identifier(), MyShowcaseConfigIdentifier.ConfigFlickrSecret.identifier()) ;
	        
		}
		catch (Exception ex){
				System.out.println("++++ Unable to loadMyShowcaseConfig ++++++++");
		}		
  	
    	return flickrSecret;
  	
   	}        
}
