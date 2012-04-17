
package org.sakaiproject.myshowcase.config;

public enum MyShowcaseConfigIdentifier {
	
	ConfigApplciation("Application"),
	ConfigApplciationHostname("Hostname"),
	ConfigApplciationVersion("Version"),
	ConfigEmail("Email"),
	ConfigEmailFromname("Fromname"),
	ConfigEmailSMTPServer("SMTPServer"),
	ConfigEmailSubjectPublishedShowcase("SubjectPublishedShowcase"),
	ConfigShowcaseReview("ShowcaseReview"),
	ConfigShowcaseReviewBaseUrl("BaseUrl"),
	ConfigFileUpload("FileUpload"),
	ConfigFileUploadUploadDir("UploadDir"),
	ConfigFileUploadMaxFileSize("MaxFileSize"),
	ConfigFileUploadValidFileType("ValidFileType"),
	ConfigFileUploadHomeDir("HomeDir"),	
	ConfigFlickr("Flickr"),
	ConfigFlickrKey("Key"),
	ConfigFlickrSecret("Secret");	
	
    private final String identifier; 
    
    MyShowcaseConfigIdentifier(String identifier) {
        this.identifier = identifier;
    }
    
    public String identifier(){
    	return identifier;
   	}
    
}
