package org.sakaiproject.myshowcase.logic;

import org.sakaiproject.genericdao.api.search.Search;
import org.sakaiproject.myshowcase.model.MyShowcaseEmailClient;
import org.sakaiproject.myshowcase.model.Owner;
import org.sakaiproject.myshowcase.model.Reviewer;
import org.sakaiproject.myshowcase.model.Showcase;

public class MyShowcaseServiceSakai extends MyShowcaseService {

    private ExternalLogic externalLogic;
 
    
    public void setExternalLogic(ExternalLogic externalLogic) {
       this.externalLogic = externalLogic;
    }	

    
	public Owner obtainOwner() {

		System.out.println("Sakai service obtainOwner");
		
        String userId = getCurrentUserId();		
        		
        Owner owner = dao.findOneBySearch(Owner.class, new Search("userId",userId));
		
        if (owner!=null){
        
            owner.getShowcases().toArray();
            owner.getArtefacts().toArray();
            owner.getReviewers().toArray();
            owner.getTags().toArray();
    		owner.setFullname(externalLogic.getUserDisplayName(userId));
     		saveOwner(owner);
            
    	} else{
			
    		owner = new Owner(userId);
    		owner.setFullname(externalLogic.getUserDisplayName(userId));
     		saveOwner(owner);
    	}
		
        return owner;
    }    
    
    public String getCurrentUserId() {

        String currentUser = externalLogic.getCurrentUserId();

        return currentUser;
    } 	

	
	public String getEmailReviewUrl(Showcase showcase, Reviewer reviewer, Owner owner) {
 		
		String msgUrl = "/direct/myshowcase/showcaseID=" + showcase.getShowcaseId() + "/reviewerID=" + reviewer.getReviewerId() ;
		
		return msgUrl;
	}	

	
	public void sendEmailMessage(String fromStr, String emailAddress, String subject, String emailMessage) {
       	
		MyShowcaseEmailClient msec = new MyShowcaseEmailClient();
    	
		msec.sendMessage(fromStr, emailAddress, subject, emailMessage);

// Email service no loner injected for Sakai use. Using the MyShowcaseSakaiEmailClient		
//    	EmailService.send(fromStr, emailAddress, subject, emailMessage, null, null, null) ;

	}		
	
}
