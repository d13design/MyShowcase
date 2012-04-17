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
//*Email: <myshowcase@myknowledgemap.com> 
//*                                                                         *
//*                                                                         *
//***************************************************************************

package org.sakaiproject.myshowcase.logic;

import java.util.Date;
import java.util.List;

import org.sakaiproject.genericdao.api.search.Order;
import org.sakaiproject.myshowcase.model.AccountSource;
import org.sakaiproject.myshowcase.model.ArtefactMapping;
import org.sakaiproject.myshowcase.model.Login;
import org.sakaiproject.myshowcase.model.Owner;
import org.sakaiproject.myshowcase.model.Registration;
import org.sakaiproject.myshowcase.model.ShowcaseListItem;
import org.sakaiproject.myshowcase.model.ShowcaseReviewer;
import org.sakaiproject.myshowcase.model.ShowcaseReviewersListItem;
import org.sakaiproject.myshowcase.model.ShowcaseTheme; 
import org.sakaiproject.myshowcase.model.ArtefactType;
import org.sakaiproject.myshowcase.model.Artefact;
import org.sakaiproject.myshowcase.model.Reviewer;
import org.sakaiproject.myshowcase.model.Showcase;
import org.sakaiproject.myshowcase.model.Tag;
import org.sakaiproject.myshowcase.model.ArtefactTag;
import org.sakaiproject.myshowcase.model.ArtefactTypeList;
import org.sakaiproject.myshowcase.model.Account;

/**
 * This is the interface for the app Logic, 
 * @author MKM York
 */
public interface IMyShowcaseService {

   
    /**
     * This returns a showcase theme based on an id
     * @param Id of the showcase theme to fetch
     * @return Showcase theme or null if not found
     */
    public ShowcaseTheme getShowcaseThemeById(Long id);    

    
    /**
     * This returns a List of MyShowcase showcase themes
     * @return a List of showcase themes
     */
    public List<ShowcaseTheme> getAllShowcaseThemes();

    
    /**
     * Save (Create or Update) an owner
     * @param Owner to create or update
     */
    public void saveOwner(Owner owner);


    /**
     * Remove an owner
     * @param Owner to remove
     */
    public void removeOwner(Owner owner);

  
    /**
     * This returns an owner by id
     * @param Id of owner to retrieve
     * @return Owner or null if not found
     */
    public Owner getOwnerById(Long id); 

    
    /**
     * This returns an existing owner or creates a new one by user id (SSO)
     * @return Owner or null if not found
     */
    public Owner obtainOwner(); 

    
    /**
     * Save (Create or Update) an artefact
     * @param Artefact to create or update
     */
    public void saveArtefact(Artefact artefact);

    
    /**
     * Remove an Artefact
     * @param Artefact to remove
     */
    public void removeArtefact(Artefact artefact);  

    
    /**
     * This returns a List of MyShowcase artefact types
     * @return List of artefact types
     */
    public List<ArtefactType> getAllArtefactTypes();     

     
    /**
     * This returns an artefact type by id
     * @param Id of the artefact to search for
     * @return Artefact type or null if not found
     */
    public ArtefactType getArtefactTypeById(Long id); 

    /**
     * This returns an artefact type by name
     * @param Name of the artefact to search for
     * @return Artefact type or null if not found
     */
    public ArtefactType getArtefactTypeByName(String name); 
    
    /**
     * This returns an artefact by id
     * @param Id of artefact to search for
     * @return Artefact or null if not found
     */
    public Artefact getArtefactById(Long id); 

    
    /**
     * This returns a list of artefacts for an owner
     * @param Owner of the artefacts
     * @return Artefact list or null if none found
     */
    public List<Artefact> getArtefacts(Owner owner,Order[] order,String Tag, String Type);   

    
    /**
     * This returns a list of artefacts for an owner of a particular artefact type
     * @param Owner of the artefacts
     * @param Type of artefact to search for
     * @return Artefact list or null if none found
     */
    public List<Artefact> getArtefacts(Owner owner, ArtefactType artefactType);   

    
    /**
     * This returns a list of artefacts of a particular competency and mapping
     * @param competency
     * @param mapping
     * @return Artefact list or null if none found
     */
	public List<Artefact> getArtefacts(Owner owner, Long competencyId, Long mappingId);

	/**
     * This returns a list of artefacts for an owner of a particular competency and mapping
     * @param competency
     * @param mapping
     * @return Artefact list or null if none found
     */
	public List<Artefact> getArtefacts(Long competencyId, Long mappingId);

    /**
     * This returns a list of artefacts for a showcase
     * @param Showcase containing the artefacts
     * @return Artefact list or null if none found
     */
     public List<Artefact> getArtefacts(Showcase showcase); 
     
   
    /**
     * Save (Create or Update) a showcase
     * @param Showcase to create or update
     */
     public void saveShowcase(Showcase showcase);

    /**
     * Save (Create or Update) an artefact type
     * 
     * @param Type create or update
     */   
     public void saveArtefactType(ArtefactType type);
     
    /**
     * Remove a showcase
     * @param Showcase to remove
     */
    public void removeShowcase(Showcase showcase);    

      
    /**
     * This returns a showcase by id
     * @param Id of showcase to search for
     * @return Showcase or null if not found
     */
    public Showcase getShowcaseById(Long id); 
  
       
    /**
     * This returns a list of showcases for an owner
     * @param Owner of the showcases
     * @return Showcase list or null if none found
     */
    public List<Showcase> getShowcases(Owner owner);   
 
    
    public Showcase obtainCurrentShowcase(Owner owner);
    
	/**
     * Returns the ordinal of an integer i.e. 1 -> 1st 22 -> 22nd etc.
     * @param integer to be converted
     */
	public String ordinalOfInteger(int i);

	/**
     * Returns the first three letters of month name from a number e.g. 3 -> "Mar"
     * @param integer to be converted
     */
	public String shortMonthName(int month);

	/**
     * Returns the month name from a number e.g. 3 -> "March"
     * @param integer to be converted
     */
	public String monthName(int month);

	/**
     * Returns a Date object to the form 12th August 2010
     * @param Date object to be converted
     */
	public String formattedLongDate(Date date);
	
	/**
     * Builds up an email message
     * @param Showcase to be published
     * @param Reviewer of showcase (recipient of email)
     * @param Owner of showcase
     */
//	public String getEmailMessage(Showcase showcase, Reviewer reviewer, Owner owner);

	/**
     * Publish the showcase
     * @param Showcase to be published
     */
    public void publishShowcase(Showcase showcase);          

    
    /**
     * This returns a list of tags attached to an artefact
     * @param Artefact to be used in search for tags
     * @return Tag list or null if none found
     */
    public List<Tag> getTags(Artefact artefact);
        
        
    /**
     * This returns a list of tags used by an owner
     * @param Owner to be used in search for tags
     * @return Tag list or null if none found
     */
    public List<Tag> getTags(Owner owner);       
    
    
    /**
     * This returns a Tag by name
     * @param name of tag to search for 
     * @return Tag or null if not found
     */
    public Tag getTag(String name,Owner owner);        
    
    
    /**
     * This returns a Tag by its id
     * @param Id of the tag to retrieve 
     * @return Tag or null if not found
     */
    public Tag getTagById(Long id);     

    
    /**
     * This saves a tag
     * @param Tag to be saved  
     */
    public void saveTag(Tag tag); 

    
    /**
     * This increments the count of how many times the tag has been included in a search 
     * @param Tag to be incremented  
     */
    public void incrementTagSearchCount(String tag,Owner owner);  

    
    /**
     * This returns an ArtefactTag object
     * @return ArtefactTag or null if not found
     */
    public ArtefactTag getArtefactTag(Artefact artefact, Tag tag);       
 
    
    /**
     * This adds a tag to an artefact 
     * @param Artefactt to recieve tag 
     * @param Tag to be added to artefact
     */
    public void addTagToArtefact(Artefact artefact, Tag tag);
    
    
    /**
     * This removes a tag from an artefact  
     * @param ArtefactId 
     * @param Tag to remove from artefact
     */
    public void removeTagFromArtefact(String artefactId, String tagName);  
    
    
    /**
     * This removes an artefact from a showcase  
     * @param Artefact to be removed
     */
    public void removeArtefactFromShowcase(Artefact artefact);     
    
    
    /**
     * This adds an artefact to a showcase  
     * @param Artefact to be added
     * @param Showcase to accept the artefact
     */
    public void addArtefactToShowcase(Artefact artefact, Showcase showcase);         

    
    /**
     * This returns a showcase reviewer by id
     * @param Id of showcase reviewer to search for
     * @return Reviewer or null if not found
     */
    public Reviewer getReviewerById(Long id); 

    
    /**
     * Save (Create or Update) a showcase reviewer
     * @param Reviewer to create or update
     */
    public Reviewer saveReviewer(Reviewer reviewer);

    
    /**
     * Remove a showcase reviewer
     * @param Reviewer to remove
     */
    public void removeReviewer(Reviewer reviewer);      

    
    /**
     * This returns an artefact type by artefact type name
     * @param Name of artefact
     * @return instance of ArtefactType
     */ 
	public ArtefactType getArtefactType(String name);

	
    /**
     * Returns a list of Artefact types that the owner has on their evidence list
     * @param Owner Id
     * @return List of artefact types
     */ 	
	public List<ArtefactTypeList> getArtefactTypesForOwner(Long ownerId);

	
    /**
     * Returns a list of tags that the owner has used to tag existing evidence
     * @param Owner Id
     * @return List of artefact tags
     */ 	
	public List<Tag> getAllTagsForOwner(Long ownerId);
	
	
    /**
     * Returns a list of tags that are assigned to an artefact
     * @param Artefact Id
     * @return List of tag names
     */ 	
	public List<String> getAllTagsForArtefact(Long artefactId); 

	
    /**
     * Returns a list of Artefacts tagged with a specific tag
     * @param Tag
     * @return List of Artefacts
     */ 	
	public List<Artefact> getArtefacts(Tag tag); 
	
    public void addArtefactTag(String artefactId, String tagName);
    
    public void removeTagFromArtefact(Long artefactId, String tagName);
    
    public String getCurrentUserId();

    public ArtefactMapping getArtefactMapping(Artefact artefact, Long competencyId, Long id);

    public ArtefactMapping getArtefactMappingExists(Artefact artefact, Long competencyId, Long id);

	public List<ArtefactMapping> getArtefactMappings(Long competencyId, Long mappingId) ;

	public List<ArtefactMapping> getArtefactMappings(Artefact artefact, Long competencyId);
    
    public List<Artefact> getArtefactsBySearchTerm(Owner owner,Order[] order,String searchTerm);
	
	public int getArtefactMappingsCount(Owner owner, Long competencyId, Long id);
	
    public void updateArtefactMapping(Artefact artefact, Long competencyId, Long id, Boolean checked);
    
//    public List<ShowcaseListItem> getShowcasesForOwner(Long ownerId); 
    
    public List<ShowcaseListItem> getPublishedShowcasesForOwner(Long ownerId); 
    
    public List<ShowcaseReviewer> getShowcaseReviewers(Showcase showcase);
    
    public Reviewer getReviewer(String email,Owner owner);
    
    public void addReviewerToShowcase(Reviewer reviewer, Showcase showcase);
    
    public ShowcaseReviewer getShowcaseReviewer(Showcase showcase, Reviewer reviewer);

    public void showcaseReviewed(ShowcaseReviewer showcaseReviewer);
    
    public void removeReviewerFromShowcase(Reviewer reviewer, Showcase showcase);
    
    public List<ShowcaseReviewersListItem> getShowcaseReviewersList(Showcase showcase); 
    
    public void clearEvidenceFromShowcase(String showcaseId);
    
    public List<ArtefactMapping> getArtefactMappings(Artefact artefact); 
    
    public int getArtefactMappingsCount(Showcase showcase, Long competencyId, Long id); 
    
	public List<Artefact> getShowcaseEvidenceArtefactMappings(Showcase showcase,Long competencyId,Long mappingId); 
	
	public int getArtefactMappingsCount(Showcase showcase, Long competencyId);
	
	public Owner obtainOwner(Account account);
	
	public Owner obtainOwner(String userName, String password);
	
	public AccountSource getAccountSource(String name);
	
	public List<AccountSource> getAllAccountSources(); 
	
	public AccountSource getAccountSource(AccountSource accountSource); 
	
	public org.sakaiproject.myshowcase.config.MyShowcaseConfig getConfig(String configName); 
	
//	public Account getAccount(Owner owner,String source,String location);
	
	public Account getAccount(Owner owner,AccountSource accountSource);	
	
	public void saveAccount(Account account);
	
    /**
     * Checks to see if the login details match a MyShowcase Account
     * @param Login details
     * @return Validation object
     */	
	public Validation validateLogin(Login login);

	
    /**
     * Checks to see if a valid SSO Request
     * @param SSO Account
     * @return Validation object
     */	
	public Validation validateSSORequest(Account account); 

	
    /**
     * Checks to see if valid registration detail
     * @param Registration object
     * @return Validation object
     */	
	
	
	public Validation validateRegistration(Registration registration);
	
	public Owner register(Registration registration);
	
	public Owner getOwnerByUserName(String userName);
	
	public Owner getOwnerByEmail(String email);	
	
	public List<Artefact> getEvidenceArtefacts(Owner owner, Long competencyId, Long mappingId); 
}