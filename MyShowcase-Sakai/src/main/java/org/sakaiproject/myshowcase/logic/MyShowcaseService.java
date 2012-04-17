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

package org.sakaiproject.myshowcase.logic;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.genericdao.api.search.Search;
import org.sakaiproject.genericdao.api.search.Restriction;
import org.sakaiproject.genericdao.api.search.Order;
import org.sakaiproject.myshowcase.config.ConfigUtils;
import org.sakaiproject.myshowcase.config.MyShowcaseConfigValues;
import org.sakaiproject.myshowcase.dao.*;
import org.sakaiproject.myshowcase.model.*;
import org.sakaiproject.myshowcase.tool.MyShowcaseCompetencyFrameworkCV;



/**
 * This is the implementation of the business logic interface
 */
public class MyShowcaseService implements IMyShowcaseService {

 //   private static Log log = LogFactory.getLog(MyShowcaseLogicImpl.class);
      static Log log = LogFactory.getLog(MyShowcaseService.class);
    
    MyShowcaseDao dao;
    
    public void setDao(MyShowcaseDao dao) {
        this.dao = dao;
     }
    
    IMyShowcaseValidationService myShowcaseValidationService;
    
    public void setValidationService(IMyShowcaseValidationService validationService) {
        this.myShowcaseValidationService = validationService;
     } 
    
    /**
     * Place any code that should run when this class is initialized by spring here
     */
    public void init() {
       log.debug(getClassName() + "init()");
    }

  
    

	
//****************************************************************************************//		
//  		  		
//   GET Service Calls
//  		  		
//****************************************************************************************//    
    
    
    
    public String getCurrentUserId() {
        log.debug(getClassName() + " getCurrentUserId()");
 
        String currentUser = "admin";
        
        return currentUser;
    } 

    
    public String getClassName() {
    	return this.getClass().getName();
    } 

 
    
    /**
      * This method returns an owner instance based on a supplied id
      * @param Id of the owner to fetch
      * @return Owner instance
      */   
    public Owner getOwnerById(Long id) {
        log.debug(getClassName() + " getOwnerById(id) id: " + id);
        
        System.out.println("MyShowcaseService.getOwnerById...start");
        
        Owner owner = dao.findById(Owner.class, id);
        
        populateOwnerCollections(owner);
        
        System.out.println("MyShowcaseService.getOwnerById...finish");
        
        return owner;
    }

    
    /**
      * This method returns an owner instance based on a supplied user anme
      * @param String username to be used in search
      * @return Owner instance
      */    
	public Owner getOwnerByUserName(String userName) {
        log.debug(getClassName() + " getOwner()");
        		
        Owner owner = dao.findOneBySearch(Owner.class, new Search("userName",userName));
        
        populateOwnerCollections(owner);
  		
        return owner;
    } 

    
    /**
      * This method returns an owner instance based on a supplied email address
      * @param String email address to be used in search
      * @return Owner instance
      */  	
	public Owner getOwnerByEmail(String email) {
        log.debug(getClassName() + " getOwner()");
       		
        Owner owner = dao.findOneBySearch(Owner.class, new Search("email",email));
        
        populateOwnerCollections(owner);
 		
        return owner;
    } 	

    
    /**
      * This method returns an owner instance based on a supplied email address
      * @param String email address to be used in search
      * @return Owner instance
      */ 	
    public org.sakaiproject.myshowcase.config.MyShowcaseConfig getConfig(String configName) {
        log.debug(getClassName() + " getConfig()");
        
        org.sakaiproject.myshowcase.config.MyShowcaseConfig msc = null;
        
        try {
        	
        	msc = ConfigUtils.loadConfig(configName);
        }
        catch (Exception ex){
        	
        	msc = null;
        }
       
        return msc;
    }


    
    /**
      * Return a list of all possible artefact types used in myshowcase
      * @return List of ArtefactTypes
      */    
    public List<ArtefactType> getAllArtefactTypes() {
        log.debug(getClassName() + " getAllArtefactTypes()");
       
        return dao.findAll(ArtefactType.class);
    }
 
    
    /**
      * Return a list of all account sources used in myshowcase
      * @return List of ArtefactTypes
      */      
    public List<AccountSource> getAllAccountSources() {
        log.debug(getClassName() + " getAllAccountSources()");
       
        return dao.findAll(AccountSource.class);
    }
 
    
    /**
      * Return a list of all artefact types (including volume counts) included in the evidence stream of an owner.
      * @param Long owner id
      * @return List of ArtefactTypes
      */  	
    public List<ArtefactTypeList> getArtefactTypesForOwner(Long ownerId) {
        log.debug(getClassName() + " getArtefactTypesForOwner()");
        
        
        // List all artefact types the owner has included in their evidence stream
        Order[] order = {};
         
        List<Artefact> artefacts = getArtefacts(getOwnerById(ownerId),order, null, null);
        
        Set<String> types = new HashSet<String>();
        
        for (Artefact artefact: artefacts){
        	
       		types.add(artefact.getType().getName());
        }
 
        List<String> typesList = new ArrayList<String>(types);
        
        Collections.sort(typesList);
         
        // Use the retrieved list of types included in evidence stream and get their volumes(size) 
        ArtefactTypeList atl = new ArtefactTypeList();
        
        List<ArtefactTypeList> artefactTypeList = new ArrayList<ArtefactTypeList>();
        
        for (String type: typesList){
        	
        	atl = new ArtefactTypeList();
        	
            artefacts = getArtefacts(getOwnerById(ownerId),order, null,type);
            
        	atl.setName(type);
        	
        	atl.setCount(artefacts.size());
        	
       		artefactTypeList.add(atl);
        }

        return artefactTypeList;
    }

 /*	
    public List<ShowcaseListItem> getShowcasesForOwner(Long ownerId) {
        log.debug(getClassName() + " getShowcasesForOwner()");

        List<Showcase> showcases = getPublishedShowcases(getOwnerById(ownerId));
        
        List<ShowcaseListItem> showcaseItems  = new ArrayList<ShowcaseListItem>();
        
        ShowcaseListItem  item = new ShowcaseListItem();
        
        for (Showcase showcase: showcases){
        	
        	item = new ShowcaseListItem();
            
        	item.setShowcaseTitle(showcase.getName());
        	
        	item.setIllustration(showcase.getTheme().getIllustration());
        	
        	item.setShowcaseId(showcase.getShowcaseId());
        	
        	item.setShowcaseDesc(showcase.getFullDesc());
        	
        	item.setPublishedDateTime(showcase.getPublishedDate());
        	
        	item.setReviewStartDateTime(showcase.getReviewStartDate());
        	
        	item.setReviewEndDateTime(showcase.getReviewEndDate());
        	
        	showcaseItems.add(item);
       }

        return showcaseItems;
    } 

 */
    
    /**
      * Return a collection of published showcases for a given owner.
      * @param Long owner id
      * @return List of ShowcaseListItems
      */     
     public List<ShowcaseListItem> getPublishedShowcasesForOwner(Long ownerId) {
    	log.debug(getClassName() + " getShowcasesForOwner()");

    	List<Showcase> showcases = getPublishedShowcases(getOwnerById(ownerId));

    	List<ShowcaseListItem> showcaseItems = new ArrayList<ShowcaseListItem>();

    	ShowcaseListItem item = new ShowcaseListItem();

    	for (Showcase showcase: showcases){

    		item = new ShowcaseListItem();

    		item.setShowcaseTitle(showcase.getName());

    		item.setIllustration(showcase.getTheme().getIllustration());

    		item.setShowcaseId(showcase.getShowcaseId());

    		item.setShowcaseDesc(showcase.getFullDesc());

    		item.setPublishedDateTime(showcase.getPublishedDate());

    		item.setReviewStartDateTime(showcase.getReviewStartDate());

    		item.setReviewEndDateTime(showcase.getReviewEndDate());

    		item.setShowcaseReviewers(getShowcaseReviewersList(showcase));

    		showcaseItems.add(item);
    	}
    	
    	return showcaseItems ;

   	}
 
     
     /**
       * Return an ArtefactType for a given id.
       * @param Long ArtefactType identifier
       * @return ArtefactType
       */     
     public ArtefactType getArtefactTypeById(Long id){
        log.debug(getClassName() + " getArtefactTypeById(id) id: " + id);
       
        ArtefactType artefactType = dao.findById(ArtefactType.class, id);
        
        populateArtefactTypeCollections(artefactType);
         
        return artefactType;
    } 
 
     
     /**
       * Return an ArtefactType for a given name.
       * @param String ArtefactType name
       * @return ArtefactType
       */  
     public ArtefactType getArtefactTypeByName(String name){
        log.debug(getClassName() + " getArtefactTypeByName(name) name: " + name);
        
        Search search = new Search();
        
        search.addRestriction(new Restriction("name", name));
        
        ArtefactType artefactType = dao.findOneBySearch(ArtefactType.class, search) ;
        
        populateArtefactTypeCollections(artefactType);
        
        return artefactType;
    } 
 
     
     /**
       * Return the list of all ShowcaseThemes.
       * @return List ShowcaseTheme
       */     
     public List<ShowcaseTheme> getAllShowcaseThemes() {
         log.debug(getClassName() + " getAllShowcaseThemes()");
       
         List<ShowcaseTheme> list = dao.findAll(ShowcaseTheme.class);
        
         for (ShowcaseTheme theme: list){
        	 
        	 populateShowcaseThemeCollections(theme);       	 

         }
         
         return list;
    }
  
     
    /**
      * Return a ShowcaseTheme using the supplied identifier.
      * @param Long ShowcaseTheme identifier
      * @return ShowcaseTheme
      */     
    public ShowcaseTheme getShowcaseThemeById(Long id) {
        log.debug(getClassName() + " getShowcaseThemeById(id): " + id);
       
        return dao.findById(ShowcaseTheme.class, id);
    }
    
    
    /**
      * Return a ShowcaseReviewer using the supplied identifier.
      * @param Long ShowcaseReviewer identifier
      * @return ShowcaseReviewer
      */  
     public ShowcaseReviewer getShowcaseReviewerById(Long id) {
        log.debug(getClassName() + " getShowcaseReviewerById(id): " + id);
       
        return dao.findById(ShowcaseReviewer.class, id);
    }
     
     
     /**
       * Return a Reviewer using the supplied identifier.
       * @param Long Reviewer identifier
       * @return Reviewer
       */ 
     public Reviewer getReviewerById(Long id) {
        log.debug(getClassName() + " getReviewerById(id) id: " + id);
       
        return dao.findById(Reviewer.class, id);
    }  
    
     
    /**
      * Clear all evidence Artefact's attached to a Showcase.
      * @param String Showcase identifier
      */ 
    public void clearEvidenceFromShowcase(String showcaseId) {
	    log.debug(getClassName() + " clearEvidenceFromShowcase(id): " + showcaseId);
	
	    Showcase showcase = getShowcaseById(new Long(showcaseId));
	    
	    List<Artefact> artefacts = getArtefacts(showcase);

        for (Artefact artefact: artefacts){ 
    	    
    	    artefact.setShowcase(null);
    	    
    	    saveArtefact(artefact);
    	    
    	    showcase.getArtefacts().remove(artefact);
    	    
    	    saveShowcase(showcase);
    	    
    	    removeArtefact(artefact);
        }
    }       
    
    
    /**
      * Return an Artefact using the supplied identifier.
      * @param Long Artefact identifier
      * @return Artefact
      */ 
     public Artefact getArtefactById(Long id) {
        log.debug(getClassName() + " getArtefactById(id) id: " + id);
       
        Artefact artefact = dao.findById(Artefact.class, id);
        
        populateArtefactCollections(artefact);
        
        return artefact;
    } 
     
     
    /**
      * Return an Account using the supplied identifier.
      * @param Long Account identifier
      * @return Account
      */  
    public Account getAccountById(Long id) {
        log.debug(getClassName() + " getAccountById(id) id: " + id);
       
        Account account = dao.findById(Account.class, id);
        
        populateAccountCollections(account);
        
        return account;
    } 
    
    
    /**
      * Return a collection of Artefact's using the supplied search criteria.
      * @param Owner Owner instance
      * @param Order 
      * @param String tag
      * @param String type
      * @return List Artefact's
      */  
 	public List<Artefact> getArtefacts(Owner owner,Order[] order, String tag, String type) {
        log.debug(getClassName() + " getArtefacts(Owner) ownerId: " + owner.getOwnerId());
		
        
        Showcase showcase = new Showcase();
         
     // Default search - by Owner and Showcase
        String[] properties = {"owner","showcase" };
        
        Object[] values = {owner,showcase};
        
        int[] comparisons = {Restriction.EQUALS,Restriction.NULL}; 
        
        // If present , only get artefact's of a certain type
        if (type != null){
        	
        	if ((!type.equals("")) && (!type.equals("null"))) {
        	
        		ArtefactType artefactType = getArtefactType(type);
        	
        		String[] newProperties = {"owner","showcase","type" };
        		
        		Object[] newValues = {owner,showcase,artefactType};
        		
        		int[] newComparisons = {Restriction.EQUALS,Restriction.NULL,Restriction.EQUALS};
            
        		properties = newProperties;
        		
        		values = newValues;
        		
        		comparisons = newComparisons;
            
        	}
        }
        
        // Perform search
        List<Artefact> artefacts = dao.findBySearch(Artefact.class, new Search(properties,values,comparisons,order));
        
        for (Artefact artefact: artefacts){
        	
        	populateArtefactCollections(artefact);
        }
        
		return artefacts;
	}
    
    
    /**
      * Return a collection of Artefact's based upon supplied search text.
      * @param Owner Owner instance
      * @param Order 
      * @param String Text to search by
      * @return List Artefact's
      */  
    public List<Artefact> getArtefactsBySearchTerm(Owner owner, Order[] order, String searchTerm) {
            log.debug(getClassName() + " getArtefactsBySearchTerm");
            
        // Default search - owner, showcase
        // String likeSymbol = "%";
        String term = "%" + searchTerm + "%";
            
        Showcase showcase = new Showcase();
            
        int[] comparisons = {Restriction.EQUALS,Restriction.NULL,Restriction.LIKE,Restriction.NOT_EQUALS};
         
        // WHY?????????????
        ArtefactType twitter = getArtefactType("Twitter");
            
        String[] matchNameProperties = {"owner","showcase","name","type"};
            
        Object[] matchNameValues = {owner,showcase,term,twitter};
            
        List<Artefact> matchNameArtefacts = dao.findBySearch(Artefact.class, new Search(matchNameProperties,matchNameValues,comparisons,order));

        for (Artefact artefact: matchNameArtefacts){ 
        	
        	populateArtefactCollections(artefact);	
        }
    
        return matchNameArtefacts;
    } 
    
    
    /**
      * Return a collection of Artefact's for an owner of a specific type.
      * @param Owner Owner instance
      * @param ArtefactType  
      * @return List Artefact's
      */  	
	public List<Artefact> getArtefacts(Owner owner,ArtefactType type) {
        log.debug(getClassName() + " getArtefacts(Owner,Type) ownerId: " + owner.getOwnerId());
       
        Showcase showcase = new Showcase();
        
        String[] properties = {"owner","type","showcase" };
        
        Object[] values = {owner,type,showcase};
        
        int[] comparisons = {Restriction.EQUALS,Restriction.EQUALS,Restriction.NULL}; 
        
        List<Artefact> artefacts = dao.findBySearch(Artefact.class, new Search(properties,values,comparisons));
        
        for (Artefact artefact: artefacts){
        	
        	populateArtefactCollections(artefact);
        }
        
        return artefacts;
    } 
    
    
    /**
      * Return a collection of Artefact's for a specific Showcase.
      * @param Showcase Owner
      * @return List Artefact's
      */  
	public List<Artefact> getArtefacts(Showcase showcase) {
        log.debug(getClassName() + " getArtefacts(Showcase) showcaseId: " + showcase.getShowcaseId());
 
        String[] properties = {"showcase"};
        
        Object[] values = {showcase};
        
        int[] comparisons = {Restriction.EQUALS};
        
     // false sets the order to descending
		Order createdDateOrder = new Order("createdDate",false);
		
		Order[] order = {createdDateOrder};
        
        List<Artefact> artefacts = dao.findBySearch(Artefact.class, new Search(properties,values,comparisons,order));
  
        for (Artefact artefact: artefacts){ 
        	
        	populateArtefactCollections(artefact);
        }
        
        return artefacts;
    } 	
    
    
    /**
      * Return a collection of Artefact's for a specific framework and competency (ArtefactMapping).
      * @param Showcase 
      * @param Long Competency id (framework)
      * @param Long Mapping id (specific framework competency)
      * @return List Artefact's
      */ 	
	public List<Artefact> getShowcaseEvidenceArtefactMappings(Showcase showcase,Long competencyId,Long mappingId) {
        log.debug(getClassName() + " getShowcaseEvidenceArtefactMappings(Showcase,long,long) showcaseId: " + showcase.getShowcaseId());
        
        List<Artefact> showcaseArtefacts = dao.findBySearch(Artefact.class, new Search("showcase",showcase));
        
        List<Artefact> evidenceArtefacts = new ArrayList<Artefact>();
        
        ArtefactMapping artefactMapping = new ArtefactMapping();
        
        for (Artefact artefact: showcaseArtefacts){ 
        	
        	artefactMapping = getArtefactMapping(artefact,competencyId,mappingId);
        	
        	if (artefactMapping.getArtefactMappingId() != null){
        		
        		populateArtefactCollections(artefact);
        		
        		evidenceArtefacts.add(artefact);
        	}
        }
         
        return evidenceArtefacts;
    } 		
    
    
    /**
      * Return a collection of Artefact's mapped to a specific Tag.
      * @param  Tag 
      * @return List Artefact's
      */ 
	public List<Artefact> getArtefacts(Tag tag) {
        log.debug(getClassName() + " getArtefacts(Tag) tagId: " + tag.getTagId());
        
        List<ArtefactTag> artefactTags = dao.findBySearch(ArtefactTag.class, new Search("tag",tag));
       
        List<Artefact> artefacts = new ArrayList<Artefact>();
       
        Artefact artefact;
        
        for (ArtefactTag artefactTag: artefactTags){ 
        	
        	artefact = artefactTag.getArtefact();
     	
        	populateArtefactCollections(artefact);
        	
        	artefacts.add(artefact);
        }
        
        return artefacts;
    } 
    
    
    /**
      * Return a collection of Artefact's mapped to a specific competency.
      * @param  Long competency id
      * @param Long mapping id
      * @return List Artefact's
      */
	public List<Artefact> getArtefacts(Long competencyId, Long mappingId) {
        log.debug(getClassName() + " getArtefacts(CompetencyID,MappingID) : " + competencyId + ":" + mappingId);
        
        List<ArtefactMapping> artefactMappings = getArtefactMappings(competencyId, mappingId) ;
        
        List<Artefact> artefacts = new ArrayList<Artefact>();
       
        Artefact artefact;
        
        for (ArtefactMapping artefactMapping: artefactMappings){ 
        	
        	artefact = artefactMapping.getArtefact();
        	
        	populateArtefactCollections(artefact);
        	
        	artefacts.add(artefact);
        }
        
        return artefacts;
    } 
   
    
    /**
      * Return a collection of evidence Artefact's for an Owner that are mapped to a specific competency. The results do not include
      * showcase artefacts.
      * @param Owner
      * @param  Long competency id
      * @param Long mapping id
      * @return List Artefact's
      */   	
	public List<Artefact> getEvidenceArtefacts(Owner owner, Long competencyId, Long mappingId) {
        log.debug(getClassName() + " getArtefacts(CompetencyID,MappingID) : " + competencyId + ":" + mappingId);
        
        List<ArtefactMapping> artefactMappings = getArtefactMappings(competencyId, mappingId) ;
        
        List<Artefact> artefacts = new ArrayList<Artefact>();
       
        Artefact artefact;
        
        for (ArtefactMapping artefactMapping: artefactMappings){ 
        	
        	artefact = artefactMapping.getArtefact();
        	
        	if ((owner.getOwnerId().equals(artefact.getOwner().getOwnerId())) && (artefact.getShowcase() == null) ){
	        	
        		populateArtefactCollections(artefact);
	        	
	        	artefacts.add(artefactMapping.getArtefact());
        	}
        }
        
        return artefacts;
    } 
  
    
    /**
      * Return a collection of Artefact's for an Owner that are mapped to a specific competency. The results include
      * Artefact's attached to published Showcases as well as the existing evidence stream.
      * @param Owner
      * @param  Long competency id
      * @param Long mapping id
      * @return List Artefact's
      */   	
	public List<Artefact> getArtefacts(Owner owner, Long competencyId, Long mappingId) {
        log.debug(getClassName() + " getArtefacts(CompetencyID,MappingID) : " + competencyId + ":" + mappingId);
        
        List<ArtefactMapping> artefactMappings = getArtefactMappings(competencyId, mappingId) ;
       
        List<Artefact> artefacts = new ArrayList<Artefact>();
       
        Artefact artefact;
        
        for (ArtefactMapping artefactMapping: artefactMappings){ 
        	
        	artefact = artefactMapping.getArtefact();
        	
        	if (owner.getOwnerId().equals(artefact.getOwner().getOwnerId())) {
        		
	        	populateArtefactCollections(artefact);
	        	
	        	artefacts.add(artefactMapping.getArtefact());
        	}
        }
        
        return artefacts;
    } 
  
    
    /**
      * Return a collection of ArtefactTag's that are mapped to a specific Tag.
      * @param Tag
      * @return List ArtefactTag's
      */  
	public List<ArtefactTag> getArtefactTags(Tag tag) {
        log.debug(getClassName() + " getArtefactTags(Tag) tagId: " + tag.getTagId());
        
        List<ArtefactTag> artefactTags = dao.findBySearch(ArtefactTag.class, new Search("tag",tag));
         
        return artefactTags;
    } 	
 
    
    /**
      * Return a Showcase for the specific identifier.
      * @param Long Showcase identifier
      * @return Showcase
      */  
    public Showcase getShowcaseById(Long id) {
        log.debug(getClassName() + " getShowcaseById(id) showcaseId: " + id);
       
        Showcase showcase = dao.findById(Showcase.class, id);
        
        if (showcase!=null){
        	
        	populateShowcaseCollections(showcase);
         }
        
        return showcase;
    } 	
 
    
    /**
      * Return an Account for a given Owner and AccountSource.
      * @param Owner 
      * @param AccountSource
      * @return Account
      */  	
	public Account getAccount(Owner owner,AccountSource as) {
        log.debug(getClassName() + " obtainCurrentShowcase(Owner) ownerId: " + owner.getOwnerId());
 
        AccountSource accountSource = getAccountSource(as);
        
        Account account = null;
         
        if (accountSource != null){

        	String[] properties = {"owner","accountSource" };
        
        	Object[] values = {owner,accountSource};
        
        	int[] comparisons = {Restriction.EQUALS,Restriction.EQUALS};
        
//        	Order[] order = {};
        
        	account = dao.findOneBySearch(Account.class, new Search(properties,values,comparisons));
        }
        
        return account;
    }       

    
    /**
      * Return a List of Showcase's (published and unpublished)for a specific Owner.
      * @param Owner 
      * @return Account
      */ 	
	public List<Showcase> getShowcases(Owner owner) {
        log.debug(getClassName() + " getShowcases(Owner) ownerId: " + owner.getOwnerId());
        
        List<Showcase> showcases = dao.findBySearch(Showcase.class, new Search("owner",owner));
        
        for (Showcase showcase: showcases){ 
        	
        	populateShowcaseCollections(showcase);
        }
     
        return showcases;
    } 

    
    /**
      * Return a List of published Showcase's for a specific Owner.
      * @param Owner 
      * @return Account
      */ 
 	public List<Showcase> getPublishedShowcases(Owner owner) {
        log.debug(getClassName() + " getPublishedShowcases(Owner) ownerId: " + owner.getOwnerId());
        
        String[] properties = {"owner","published" };
        
        Object[] values = {owner,true};
        
        int[] comparisons = {Restriction.EQUALS,Restriction.EQUALS};
        
     // false sets the order to descending
		Order publishedDateOrder = new Order("publishedDate",false);
		
        Order[] order = {publishedDateOrder};
            
        List<Showcase> showcases = dao.findBySearch(Showcase.class, new Search(properties,values,comparisons,order));
        
        for (Showcase showcase: showcases){
        	
        	populateShowcaseCollections(showcase);
        }
     
        return showcases;
    } 

    
    /**
      * Return the url used as part of the Showcase review.
      * @param Showcase
      * @param Reviewer
      * @param Owner 
      * @return String url used by the reviewer to review the Showcase.
      */ 
	public String getEmailReviewUrl(Showcase showcase, Reviewer reviewer, Owner owner) {
 		
        String msgUrl =  MyShowcaseConfigValues.getInstance().getShowcaseReviewBaseUrl() + "showcaseId=" + showcase.getShowcaseId() + "&reviewerId=" + reviewer.getReviewerId();
 
		return msgUrl;
	}	

    
    /**
      * Send an email message.
      * @param String email from value
      * @param String email address
      * @param String email subject
      * @param String email message
      * @param Owner 
      */ 
	public void sendEmailMessage(String fromStr, String emailAddress, String subject, String emailMessage) {
       	
		MyShowcaseEmailClient msec = new MyShowcaseEmailClient();
    	
		msec.sendMessage(fromStr, emailAddress, subject, emailMessage);

	}		

    
    /**
      * Publish a Showcase.
      * @param Showcase 
      */ 	
	public void publishShowcase(Showcase showcase) {
        
		log.debug(getClassName() + " publishShowcase(Showcase) showcase: " + showcase.getName());
		
        String fromStr = MyShowcaseConfigValues.getInstance().getEmailFromName();
        
        String subject = MyShowcaseConfigValues.getInstance().getEmailSubject();
       
        //lock the Showcase
        showcase.setPublished(true);
        
        showcase.setPublishedDate(new Date());
        
        Set<ShowcaseReviewer> showcaseReviewers = showcase.getShowcaseReviewers();

        Owner owner = showcase.getOwner();

        owner = getOwnerById(owner.getOwnerId());

        String emailAddress;

        for (ShowcaseReviewer showcaseReviewer : showcaseReviewers) {

        	Reviewer reviewer = getReviewerById(showcaseReviewer.getReviewer().getReviewerId());

        	emailAddress = reviewer.getEmail();
 
        	String emailMessage = getEmailMessage(showcase, reviewer, owner);
        	
        	sendEmailMessage(fromStr, emailAddress, subject, emailMessage);
        }
        
        Set<Artefact> artefacts = showcase.getArtefacts();
        
        for (Artefact artefact: artefacts){ 
 
        	artefact.setLocked(true);

        	Artefact artefactToSave = getArtefactById(artefact.getArtefactId());
  
        	saveArtefact(artefactToSave);
        }

        Showcase showcaseToSave = getShowcaseById(showcase.getShowcaseId());
        
        //lock showcase
        showcaseToSave.setPublished(true);
        
        showcaseToSave.setPublishedDate(new Date());

        saveShowcase(showcaseToSave);
    } 

    
    /**
      * Find a Tag with a specific id.
      * @param Long Tag identifier
      * @return Tag 
      */   
    public Tag getTagById(Long id) {
        log.debug(getClassName() + " getTagById(id) id: " + id);
       
        return dao.findById(Tag.class, id);
    }

    
    /**
      * Find an ArtefactTag with a specific id.
      * @param Long ArtefactTag identifier
      * @return ArtefactTag 
      */      
    public ArtefactTag getArtefactTagById(Long id) {
        log.debug(getClassName() + " getArtefactTagById(id) id: " + id);
       
        return dao.findById(ArtefactTag.class, id);
    }
 
    
    /**
      * Increment the tag search count.
      * @param String Tag name
      * @param Owner
      */     
    public void incrementTagSearchCount(String tagName,Owner owner) {
        log.debug(getClassName() + " incrementTagSearchCount tag: " + tagName);
       
        Tag tag = getTag(tagName,owner);
        
        tag.setSearchCount(tag.getSearchCount() + 1);
        
        dao.save(tag);
    }
 
    
    /**
      * Find a Tag for a specific Owner.
      * @param String Tag name
      * @param Owner
      * @return Tag
      */      
	public Tag getTag(String name,Owner owner) {
        log.debug(getClassName() + " getTag(name) name: " + name);
        
        String[] properties = {"owner","name" };
        
        Object[] values = {owner,name};
        
        Tag tag = dao.findOneBySearch(Tag.class, new Search(properties,values));
        
        if (tag!=null){
        	
        	populateTagCollections(tag);
    	}
       
        return tag;
    }    	
 
    
    /**
      * Find a reviewer for an owner based upon the Reviewer email address.
      * @param String email address
      * @param Owner
      * @return Reviewer
      */ 
	public Reviewer getReviewer(String email,Owner owner) {
        log.debug(getClassName() + " getReviewer(email) email: " + email);
        
        String[] properties = {"owner","email" };
        Object[] values = {owner,email};
        
        Reviewer reviewer = dao.findOneBySearch(Reviewer.class, new Search(properties,values));
        
        if (reviewer!=null){
 
        	populateReviewerCollections(reviewer);
     	}
       
        return reviewer;
    } 
 
    
    /**
      * Find an ArtefactType for a given ArtefactType name.
      * @param String name
      * @return ArtefactType
      */ 
	public ArtefactType getArtefactType(String name) {
        log.debug(getClassName() + " getArtefactType(name) name: " + name);
       
        ArtefactType artefactType = dao.findOneBySearch(ArtefactType.class, new Search("name",name));
        
        populateArtefactTypeCollections(artefactType);
        
        return artefactType;
    } 
 
    
    /**
      * Find an ArtefactSource for a given AccountSource name.
      * @param String name
      * @return AccountSource
      */ 
	public AccountSource getAccountSource(String name) {
        log.debug(getClassName() + " getAccountSource(name) name: " + name);
       
        AccountSource accountSource = dao.findOneBySearch(AccountSource.class, new Search("name",name));
        
        return accountSource;
    } 

    
    /**
      * Find an existing AccountSource.
      * @param AccountSource
      * @return AccountSource
      */ 	
	public AccountSource getAccountSource(AccountSource accountSource) {
        log.debug(getClassName() + " getAccountSource(accountSource)");
        
        String[] properties = {"name","location" };
        
        Object[] values = {accountSource.getName(),accountSource.getLocation()};
        
        int[] comparisons = {Restriction.EQUALS,Restriction.EQUALS}; 
        
        AccountSource as = dao.findOneBySearch(AccountSource.class, new Search(properties,values,comparisons));
        
        return as;
    } 	

    
    /**
      * Find a ShowcaseTheme with the specified name.
      * @param String name
      * @return ShowcaseTheme
      */ 
 	public ShowcaseTheme getShowcaseTheme(String name) {
        log.debug(getClassName() + " getShowcasehemename) name: " + name);
       
        ShowcaseTheme showcaseTheme = dao.findOneBySearch(ShowcaseTheme.class, new Search("name",name));
         
        return showcaseTheme;
    } 	

    
    /**
      * Find an ArtefactTag for the supplied Artefact and Tag.
      * @param Artefact
      * @param Tag
      * @return Artefacttag
      */  
	public ArtefactTag getArtefactTag(Artefact artefact,Tag tag) {
        log.debug(getClassName() + " getArtefactTag(Artefact,Tag) tag: " + tag.getName());
       
        String[] properties = {"artefact","tag" };
        
        Object[] values = {artefact,tag};
        
        ArtefactTag artefactTag = dao.findOneBySearch(ArtefactTag.class, new Search(properties,values));
        
        return artefactTag;
    } 

    
    /**
      * Find a collection of Tags for an Owner.
      * @param Owner
      * @return List Tag
      */	
	public List<Tag> getTags(Owner owner) {
        log.debug(getClassName() + " getTags(Owner) ownerId: " + owner.getOwnerId());
        
        List<Tag> tags = dao.findBySearch(Tag.class, new Search("owner",owner));
        
        for (Tag tag: tags){ 

        	populateTagCollections(tag);
        }
        
        return tags;
    } 	

    
    /**
      * Find a collection of all Tags for an Owner.
      * @param Long identifier
      * @return List Tag
      */	
    public List<Tag> getAllTagsForOwner(Long ownerId) {
        log.debug(getClassName() + " getAllTagsForOwner()");
         
        List<Tag> tags = getTags(getOwnerById(ownerId));
        
        for (Tag tag: tags){ 

        	populateTagCollections(tag);
        } 
        
        return tags;
    }

    
    /**
      * Find a collection of all Tags for an Artefact.
      * @param Long identifier
      * @return List Tag
      */	
    public List<String> getAllTagsForArtefact(Long artefactId) {
        log.debug(getClassName() + " getAllTagsForArtefact()");
         
        List<Tag> tags = getTags(getArtefactById(artefactId));
        
        Set<String> allTags = new HashSet<String>();
        
        for (Tag tag: tags){
        	
       		allTags.add(tag.getName());
        }
        
        List<String> tagList = new ArrayList<String>(allTags); 
        
        return tagList;
    }	

    
    /**
      * Find a collection of all ShowcaseReviewersListItems for a Showcase.
      * @param Showcase
      * @return List ShowcaseReviewersListItem
      */   
	public List<ShowcaseReviewersListItem> getShowcaseReviewersList(Showcase showcase) {
        log.debug(getClassName() + " getShowcaseReviwersList()");
        
        List<ShowcaseReviewersListItem> items = new ArrayList<ShowcaseReviewersListItem>();
        
        List<ShowcaseReviewer> showcaseReviewers = dao.findBySearch(ShowcaseReviewer.class, new Search("showcase",showcase));
        
        ShowcaseReviewersListItem  item = new ShowcaseReviewersListItem();
        
        for (ShowcaseReviewer showcaseReviewer: showcaseReviewers){
        	
        	item = new ShowcaseReviewersListItem();
            
        	item.setName(showcaseReviewer.getReviewer().getName());
        	
        	item.setEmail(showcaseReviewer.getReviewer().getEmail());
        	
        	item.setShowcaseReviewerId(showcaseReviewer.getShowcaseReviewerId());
        	
        	item.setReviewerId(showcaseReviewer.getReviewer().getReviewerId());
        	
        	if (showcaseReviewer.getReviewedDate()!= null){
        		item.setViewed(true);
        	}
        	else{
        		item.setViewed(false);
        	}
        		
        	items.add(item);
      	
        }
        
        return items;
    }

    
    /**
      * Find a collection of all ShowcaseReviewers for a Showcase.
      * @param Showcase
      * @return List ShowcaseReviewers
      */      
	public List<ShowcaseReviewer> getShowcaseReviewers(Showcase showcase) {
        log.debug(getClassName() + " getShowcaseReviwewers()");
        
        List<Reviewer> reviewers = new ArrayList<Reviewer>();

        List<ShowcaseReviewer> showcaseReviewers = dao.findBySearch(ShowcaseReviewer.class, new Search("showcase",showcase));
        
        for (ShowcaseReviewer showcaseReviewer: showcaseReviewers){ 
        	
        	reviewers.add(showcaseReviewer.getReviewer());

        }
        
        return showcaseReviewers;
    }	
 
    
    /**
      * Find a collection of Tags for a given Artefact.
      * @param Artefact
      * @return List Tag
      */   
	public List<Tag> getTags(Artefact artefact) {
        log.debug(getClassName() + " getTags(Artefact) artefactId: " + artefact.getArtefactId());
        
        List<Tag> tags = new ArrayList<Tag>();
        
        List<ArtefactTag> artefactTags = dao.findBySearch(ArtefactTag.class, new Search("artefact",artefact));
        
        for (ArtefactTag artefactTag: artefactTags){ 
        	
        	tags.add(artefactTag.getTag());
        }
        
        return tags;
    }	

    
    /**
      * Find a ShowcaseReviewer.
      * @param Showcase
      * @param Reviewer
      * @return ShowcaseReviewer
      */   
	public ShowcaseReviewer getShowcaseReviewer(Showcase showcase, Reviewer reviewer) {
        log.debug(getClassName() + " getShowcaseReviewer()");
       
        String[] properties = {"showcase","reviewer" };
        
        Object[] values = {showcase,reviewer};
        
        ShowcaseReviewer showcaseReviewer = dao.findOneBySearch(ShowcaseReviewer.class, new Search(properties,values));
        
        return showcaseReviewer;
    } 

    
    /**
      * Maintain Showcase reviewed status.
      * @param ShowcaseReviewer
      */   	
    public void showcaseReviewed(ShowcaseReviewer showcaseReviewer) {
	    log.debug(getClassName() + " saveShowcaseReviewer(showcaseReviewer): " + showcaseReviewer.getShowcaseReviewerId());
	
	    Date date = new Date();
	    
   	    showcaseReviewer.setReviewedDate(date);
        
        dao.save(showcaseReviewer);

    }

    
    /**
      * Read an ArtefactMapping.
      * @param Artefact
      * @param Long competency id
      * @param Long mapping id
      * @return ArtefactMapping
      */  
	public ArtefactMapping getArtefactMapping(Artefact artefact, Long competencyId, Long id) {
		
		Search search = new Search(new Restriction[] {
			new Restriction("artefact", artefact),
			new Restriction("competencyId", competencyId),
			new Restriction("mappingId", id)
		});
		
		ArtefactMapping mapping = dao.findOneBySearch(ArtefactMapping.class, search);
		
		if (mapping == null){
			
			mapping = new ArtefactMapping(artefact, competencyId, id) ;
		}
			
		return mapping ;
	}

    
    /**
      * Read an existing ArtefactMapping.
      * @param Artefact
      * @param Long competency id
      * @param Long mapping id
      * @return ArtefactMapping
      */ 	
	public ArtefactMapping getArtefactMappingExists(Artefact artefact, Long competencyId, Long id) {
		Search search = new Search(new Restriction[] {
			new Restriction("artefact", artefact),
			new Restriction("competencyId", competencyId),
			new Restriction("mappingId", id)
		});
		
		ArtefactMapping mapping = dao.findOneBySearch(ArtefactMapping.class, search);
		
		return mapping;
	}

    
    /**
      * Read a list ArtefactMappings for the given Artefact and Competence id.
      * @param Artefact
      * @param Long competency id
      * @return List ArtefactMapping
      */ 	
	public List<ArtefactMapping> getArtefactMappings(Artefact artefact, Long competencyId) {
		
		Search search = new Search(new Restriction[] {
				new Restriction("artefact", artefact),
				new Restriction("competencyId", competencyId)
			});
		
		return dao.findBySearch(ArtefactMapping.class, search);
		
	}

	
   /**
     * Read a list ArtefactMappings for the given Artefact.
     * @param Artefact
     * @return List ArtefactMapping
     */ 
	public List<ArtefactMapping> getArtefactMappings(Artefact artefact) {
		Search search = new Search(new Restriction[] {
				new Restriction("artefact", artefact),
			});
		
		return dao.findBySearch(ArtefactMapping.class, search);
		
	}

	
	/**
	  * Read a list ArtefactMappings for the given competenct id and mapping id combination.
	  * @param Long competency id
	  * @param Long mapping id
	  * @return List ArtefactMapping
	  */ 	
	public List<ArtefactMapping> getArtefactMappings(Long competencyId, Long mappingId) {
		Search search = new Search(new Restriction[] {
				new Restriction("mappingId", mappingId),
				new Restriction("competencyId", competencyId)
			});
		
		return dao.findBySearch(ArtefactMapping.class, search);
		
	}

	
	/**
	  * Obtain a count of Owner Artefact's mapped against a given competency id and mapping id combination.
	  * @param Owner
	  * @param Long competency id
	  * @param Long mapping id
	  * @return int Total of matching ArtefactMapping's
	  */ 	
	public int getArtefactMappingsCount(Owner owner, Long competencyId, Long id) {
		
		int aCount = 0 ;

		List<Artefact> artefacts = getEvidenceArtefacts(owner);
		
		for (Artefact a : artefacts) {
			
			ArtefactMapping am = getArtefactMappingExists(a, competencyId, id) ;
			
			if (am != null){
				
				aCount++ ;
			}
		}
		
		return aCount ;
	}

	
	/**
	  * Obtain a list of owner evidence Artefact's. Excludes Artefact's attached to a Showcase.
	  * @param Owner
	  * @return List Artefact
	  */ 	
	public List<Artefact> getEvidenceArtefacts(Owner owner) {
        log.debug(getClassName() + " getArtefacts(Owner) ownerId: " + owner.getOwnerId());
		
        // Default search - owner, showcase
        Showcase showcase = new Showcase();
        
        String[] properties = {"owner","showcase"};
        
        Object[] values = {owner,showcase};
        
        int[] comparisons = {Restriction.EQUALS,Restriction.NULL}; 
        
        // false sets the order to descending
		Order createdDateOrder = new Order("createdDate",false);
		
        Order[] order = {createdDateOrder};
        
        List<Artefact> artefacts = dao.findBySearch(Artefact.class, new Search(properties,values,comparisons,order));
        
        for (Artefact artefact: artefacts){ 
        	
        	populateArtefactCollections(artefact);	
        }

		return artefacts;
	}	

	
	/**
	  * A count of ArtefactMappings for a Showcase that match a given combination of competency and mapping id.
	  * @param Showcase
	  * @param Long competency id
	  * @param Long mapping id
	  * @return int Total of matching ArtefactMapping's
	  */ 	
	public int getArtefactMappingsCount(Showcase showcase, Long competencyId, Long id){
		
		int aCount = 0 ;
		
		Set<Artefact> artefacts = showcase.getArtefacts();

		for (Artefact a : artefacts) {
			
			ArtefactMapping am = getArtefactMappingExists(a, competencyId, id) ;
			
			if (am != null){
				
				aCount++ ;
			}
		}

		return aCount ;
	}

	
	/**
	  * A count of ArtefactMappings for a Showcase that match a given competency id.
	  * @param Showcase
	  * @param Long competency id
	  * @return int Total of matching ArtefactMapping's
	  */ 
	public int getArtefactMappingsCount(Showcase showcase, Long competencyId) {
		
		int aCount = 0 ;
		
		Set<Artefact> artefacts = showcase.getArtefacts();

		if (competencyId == MyShowcaseCompetencyFrameworkCV.COMPETENCY_ID.identifier()){
		
			aCount = getShowcaseSimpleCvMappingsCount(artefacts,showcase);
		}
		
		return aCount ;
	}

	
	/**
	  * A count of ArtefactMappings for a given SimpleCV themed Showcase.
	  * @param Set Artefacts
	  * @param Showcase
	  * @return int Total of matching ArtefactMapping's
	  */ 	
	public int getShowcaseSimpleCvMappingsCount(Set<Artefact> artefacts,Showcase showcase){
		
		int aCount = 0 ;
		
		ArtefactMapping am = null;

		for (Artefact artefact : artefacts){
			
			am = getArtefactMappingExists(artefact,
					                      MyShowcaseCompetencyFrameworkCV.COMPETENCY_ID.identifier(),
					                      MyShowcaseCompetencyFrameworkCV.INTRODUCTION.identifier());
			if (am != null) aCount++;
			
			am = getArtefactMappingExists(artefact,
                                          MyShowcaseCompetencyFrameworkCV.COMPETENCY_ID.identifier(),
                                          MyShowcaseCompetencyFrameworkCV.ADDITIONAL_SKILLS.identifier());
			if (am != null) aCount++;		
			am = getArtefactMappingExists(artefact,
                    					  MyShowcaseCompetencyFrameworkCV.COMPETENCY_ID.identifier(),
                                          MyShowcaseCompetencyFrameworkCV.CONTACT_DETAILS.identifier());
			if (am != null) aCount++;

			am = getArtefactMappingExists(artefact,
                                          MyShowcaseCompetencyFrameworkCV.COMPETENCY_ID.identifier(),
                                          MyShowcaseCompetencyFrameworkCV.EDUCATION.identifier());
			if (am != null) aCount++;
			
			am = getArtefactMappingExists(artefact,
                                          MyShowcaseCompetencyFrameworkCV.COMPETENCY_ID.identifier(),
                                          MyShowcaseCompetencyFrameworkCV.EXPERIENCE.identifier());
			if (am != null) aCount++;
			
			am = getArtefactMappingExists(artefact,
					                      MyShowcaseCompetencyFrameworkCV.COMPETENCY_ID.identifier(),
                                          MyShowcaseCompetencyFrameworkCV.FURTHER_INFO.identifier());
            if (am != null) aCount++;

            am = getArtefactMappingExists(artefact,
                                          MyShowcaseCompetencyFrameworkCV.COMPETENCY_ID.identifier(),
                                          MyShowcaseCompetencyFrameworkCV.HOBBIES_AND_INTERESTS.identifier());
            if (am != null) aCount++;			

		}

		return aCount;
	}	

	
	/**
	  * Mantain an ArtefactMapping (update or delete).
	  * @param Artefact
	  * @param Long competency id
	  * @param Long mapping id
	  * @param Boolean
	  */ 
	public void updateArtefactMapping(Artefact artefact, Long competencyId, Long id, Boolean checked){

		ArtefactMapping mapping = getArtefactMapping(artefact, competencyId, id);
		
		if (checked){

		    Date date = new Date();
		    
		    String currentUser = getCurrentUserId();
		    
		    if (mapping.getCreatedDate() == null){
		    	
		    	mapping.setCreatedDate(date);
		    	
		        mapping.setCreatedBy(currentUser);
		        
		        dao.save(mapping);

		    }
		    
		} else {

			if (mapping.getCreatedDate() != null) {
				
				dao.delete(mapping);

			}
		}
	}

	
	/**
	  * Validate login.
	  * @param Login
	  * @return Validation Instance containing status of validation and any associated messages.
	  */ 		
	public Validation validateLogin(Login login){
			
		Validation validation = myShowcaseValidationService.validateLogin(login);
			
		if (validation.isValid()){
				
			Owner owner = obtainOwner(login.getUserName(), login.getPassword());
				
			if (owner == null){
					
				validation.addMessage("Unable to locate an account that matched the supplied user and password.");
				
				validation.setValid(false);
					
			}
		}
			
		return validation;
			
	}

	
	/**
	  * Validate Single Signon request.
	  * @param Account
	  * @return Validation Instance containing status of validation and any associated messages.
	  */ 		
	public Validation validateSSORequest(Account account){

		Validation validation = myShowcaseValidationService.validateSSORequest(account);
			
	    AccountSource as = account.getAccountSource();
		    
		if (validation.isValid()){

			AccountSource accountSource = getAccountSource(as);
				
			if (accountSource == null){
					
				validation.addMessage("SSO source and location (" + as.getName() + " and " + as.getLocation() + " ) are not recognised by MyShowcase.");
					
				validation.setValid(false);
			}
		}
			
		return validation;
			
	} 

	
	/**
	  * Validate registration.
	  * @param Registration
	  * @return Validation Instance containing status of validation and any associated messages.
	  */		
	public Validation validateRegistration(Registration registration){
			
		Validation validation = myShowcaseValidationService.validateRegistration(registration);
			
		if (validation.isValid()){
				
			if (getOwnerByEmail(registration.getEmail()) != null){
					
				validation.addMessage("Email address must be unique. The email address supplied is already used by an existing MyShowcase account.");
				
				validation.setValid(false);
			}
				
			if (getOwnerByUserName(registration.getUserName()) != null){
					
				validation.addMessage("UserName must be unique. " + registration.getUserName() + " is in use by an existing MyShowcase account.");
				
				validation.setValid(false);
			}				
				
		}
			
		return validation;
	} 	 		

	
	/**
	  * Register a new Owner.
	  * @param Registration
	  * @return Owner
	  */
	public Owner register(Registration registration){
        log.debug(getClassName() + " getOwner()");
	        
        Owner owner = new Owner();
	        
        owner.setUserName(registration.getUserName());
        
        owner.setEmail(registration.getEmail());
        
        owner.setForename(registration.getForename());
        
        owner.setSurname(registration.getSurname());
        
        owner.setPassword(registration.getPassword());

   		saveOwner(owner);
			
        owner.getShowcases().toArray();
        
        owner.getArtefacts().toArray();
        
        owner.getReviewers().toArray();
        
        owner.getAccounts().toArray();
        
        owner.getTags().toArray();
           
        return owner;
    }    		

		
//****************************************************************************************//		
//		
//   SAVE Service Calls
//		
//****************************************************************************************//

	
	/**
	  * Save an Account.
	  * @param Account
	  */		   
    public void saveAccount(Account account){
  	    log.debug(getClassName() + " saveAccount() account:" + account.getUserId());
	  	    
    	Date date = new Date();
    	
    	String currentUser = getCurrentUserId();
	        
    	if (account.getCreatedDate() == null){
    		
	   		account.setCreatedDate(date);
	   		
	   		account.setCreatedBy(currentUser);
	    		
	   		AccountSource accountSource =  getAccountSource(account.getAccountSource());
	   		
	   		account.setAccountSource(accountSource);
	   		
	   		accountSource.getAccounts().add(account);
	   		
	        Owner owner = dao.findById(Owner.class, account.getOwner().getOwnerId());
	        
	        owner.getAccounts().add(account);
	            
    	}
    	else {
    		
	   		account.setUpdatedDate(date);
	   		
    		account.setUpdatedBy(currentUser);
    	}
		    
        dao.save(account);
    } 		

	
	/**
	  * Save a ShowcaseTheme.
	  * @param ShowcaseTheme
	  */	
    public void saveShowcaseTheme(ShowcaseTheme theme){
   	    log.debug(getClassName() + " saveShowcaseTheme() :");
	 	    
  	    if (theme.getShowcaseThemeId()== null){
  	    	
  	    	ShowcaseTheme existingTheme = getShowcaseTheme(theme.getName());
  	    	
  	    	if (existingTheme != null){
  	    		
  	    		theme= existingTheme;
  	    	}
  	    }

    	Date date = new Date();
	    	
    	String currentUser = getCurrentUserId();
	        
    	if (theme.getCreatedDate() == null) {
    		
	   		theme.setCreatedDate(date);
	   		
	   		theme.setCreatedBy(currentUser);
	   	}
	   	else {
	   		
    		theme.setUpdatedDate(date);
    		
    		theme.setUpdatedBy(currentUser);
    	}
	    
        dao.save(theme);
	       
    } 	  		

	
	/**
	  * Save an Artefact (update or create).
	  * @param Artefact
	  */
    public void saveArtefact(Artefact artefact){
	    log.debug(getClassName() + " saveArtefact(artefact): " + artefact.getName());
		    
	    Date date = new Date();
    
	    String currentUser = getCurrentUserId();
		    
	    if (artefact.getCreatedDate() == null){
	    	
	       	artefact.setCreatedDate(date);
	       	
	        artefact.setCreatedBy(currentUser);
	        
	        artefact.getArtefactDetail().setCreatedDate(date);
	        
	        artefact.getArtefactDetail().setCreatedBy(currentUser);
	        
	        Owner owner = dao.findById(Owner.class, artefact.getOwner().getOwnerId());
	        
	        owner.getArtefacts().add(artefact);
	    }
        else {
        	
	  	    artefact.setUpdatedDate(date);
	  	    
	        artefact.setUpdatedBy(currentUser);
	        
	        artefact.getArtefactDetail().setUpdatedDate(date);
	        
	        artefact.getArtefactDetail().setUpdatedBy(currentUser);
	    }
	      
        dao.save(artefact);
	        
	    artefact.getArtefactDetail().setArtefact(artefact);
	    
	    dao.save(artefact.getArtefactDetail());
    } 		

	
	/**
	  * Save an ArtefactTag (update or create).
	  * @param ArtefactTag
	  */
    public void saveArtefactTag(ArtefactTag artefactTag){
	    log.debug(getClassName() + " saveArtefactTag(artefactTag): " + artefactTag.getArtefactTagId());
		
	    Date date = new Date();
	    
	    String currentUser = getCurrentUserId();
		    
	    if (artefactTag.getCreatedDate() == null){
	        	
	    	artefactTag.setCreatedDate(date);
	    	
	        artefactTag.setCreatedBy(currentUser);
	        
	        Artefact artefact = dao.findById(Artefact.class, artefactTag.getArtefact().getArtefactId());
	        
	        artefact.getArtefactTags().add(artefactTag);
	        
	        Tag tag = dao.findById(Tag.class, artefactTag.getTag().getTagId());
	       
	        tag.getArtefactTags().add(artefactTag);
            
	        // maintain count of tag usage 
	        tag.setSearchCount(tag.getSearchCount() + 1);
	        
	        dao.save(tag);
        }
        else {
     	    
        	artefactTag.setUpdatedDate(date);
            
        	artefactTag.setUpdatedBy(currentUser);
        }
	        
        dao.save(artefactTag);
    } 	

	
	/**
	  * Save an ArtefactType (update or create).
	  * @param ArtefactTag
	  */	    
    public void saveArtefactType(ArtefactType type){
	    log.debug(getClassName() + " saveArtefactType() artefactType:" + type.getName());

	  	    
  	    if (type.getArtefactTypeId()== null){
  	    	
	    	ArtefactType existingType = getArtefactType(type.getName());
	    	
	    	if (existingType != null){
	    		
	    		type= existingType;
	    	}
	    }

    	Date date = new Date();
    	
    	String currentUser = getCurrentUserId();
	        
	   	if (type.getCreatedDate() == null) {
	   		
	   		type.setCreatedDate(date);
	   		
	   		type.setCreatedBy(currentUser);
	   	}
	   	else {
	   		
	   		type.setUpdatedDate(date);
	   		
	   		type.setUpdatedBy(currentUser);
	   	}
	   	
	       dao.save(type);
    }

	
	/**
	  * Save a Showcase (update or create).
	  * @param Showcase
	  */	 	    
    public void saveShowcase(Showcase showcase){
	    log.debug(getClassName() + " saveShowcase(showcase): " + showcase.getName());
		
	    Date date = new Date();
	    
	    String currentUser = getCurrentUserId();
		    
	    if (showcase.getCreatedDate() == null) {
	        	
	      	showcase.setCreatedDate(date);
	      	
	        showcase.setCreatedBy(currentUser);
	        
	        Owner owner = dao.findById(Owner.class, showcase.getOwner().getOwnerId());
	        
	        owner.getShowcases().add(showcase);
	        
	    }
	    else {
	    	
	  	    showcase.setUpdatedDate(date);
	  	    
	        showcase.setUpdatedBy(currentUser);
	    }
	        
	    dao.save(showcase);
    } 

	
	/**
	  * Save a Tag (update or create).
	  * @param Tag
	  */
    public void saveTag(Tag tag){
  	    log.debug(getClassName() + " saveTag(tag) tag:" + tag.getName());
	 	    
  	    Owner owner = tag.getOwner();
	  	    
  	    if (owner == null){
  	    	
 	    	owner = obtainOwner();
  	    }
	  	    
  	    if (tag.getTagId() == null){
	  	    	
  	    	Tag existingTag = getTag(tag.getName(),owner);
  	    	
  	    	if (existingTag != null){
  	    		
  	    		tag = existingTag;
  	    	}
  	    }

    	Date date = new Date();
    	
    	String currentUser = getCurrentUserId();
	        
    	if (tag.getCreatedDate() == null) {
    		
    		tag.setCreatedDate(date);
    		
    		tag.setCreatedBy(currentUser);
    	}
    	else {
    		
    		tag.setUpdatedDate(date);
    		
    		tag.setUpdatedBy(currentUser);
    	}
		    
        dao.save(tag);
    } 	

	
	/**
	  * Save a ShowcaseReviewer (update or create).
	  * @param ShowcaseReviewer
	  */	    
    public void saveShowcaseReviewer(ShowcaseReviewer showcaseReviewer){
	    log.debug(getClassName() + " saveShowcaseReviewer(showcaseReviewer): " + showcaseReviewer.getShowcaseReviewerId());
		
	    Date date = new Date();
	    
	    String currentUser = getCurrentUserId();
		    
        if (showcaseReviewer.getCreatedDate() == null) {
	        	
        	showcaseReviewer.setCreatedDate(date);
        	
            showcaseReviewer.setCreatedBy(currentUser);
            
            Showcase showcase = dao.findById(Showcase.class, showcaseReviewer.getShowcase().getShowcaseId());
           
            showcase.getShowcaseReviewers().add(showcaseReviewer);
	        
            Reviewer reviewer = dao.findById(Reviewer.class, showcaseReviewer.getReviewer().getReviewerId());
	       
            reviewer.getShowcaseReviewers().add(showcaseReviewer);
	            
        }
	    else {
	        	
    	    showcaseReviewer.setUpdatedDate(date);
    	    
	        showcaseReviewer.setUpdatedBy(currentUser);
        }
	        
	    dao.save(showcaseReviewer);
    }

    
	/**
	  * Save an Owner (update or create).
	  * @param Owner
	  */
    public void saveOwner(Owner owner){
  	    log.debug(getClassName() + " saveOwner(owner) userId:" + owner.getUserId());
	 	    
	    Date date = new Date();
	    
	    String currentUser = getCurrentUserId();
	        
	    if (owner.getCreatedDate() == null){
	    	
	      	owner.setCreatedDate(date);
	      	
	        owner.setCreatedBy(currentUser);
	    }
	    else {
	    	
	   	    owner.setUpdatedDate(date);
	   	    
	        owner.setUpdatedBy(currentUser);
	    }
	      
	    dao.save(owner);
	        
	    if (owner!=null){
	    	
	    	populateOwnerCollections(owner);    
	    }
    } 

    
	/**
	  * Save a Reviewer (update or create).
	  * @param Reviewer
	  */	    
    public Reviewer saveReviewer(Reviewer reviewer){
 	    log.debug(getClassName() + " saveReviewer(reviewer) reviewer:" + reviewer.getName());
	 	    
        if (reviewer.getCreatedDate() == null){
        	
           reviewer.setCreatedDate( new Date());
           
           reviewer.setCreatedBy(getCurrentUserId());
           
           Owner owner = dao.findById(Owner.class, reviewer.getOwner().getOwnerId());
           
	       owner.getReviewers().add(reviewer);
	    }
        else {
        	
    	    reviewer.setUpdatedDate( new Date());
    	    
	        reviewer.setUpdatedBy(getCurrentUserId());  
	    }
	       
	    dao.save(reviewer);
	        
        return reviewer;
	} 

		
//****************************************************************************************//		
//	  		
//   REMOVE Service calls
//	  		
//****************************************************************************************//	    

    
	/**
	  * Remove an Artefact.
	  * @param Artefact
	  */
	public void removeArtefact(Artefact artefact) {
	    log.debug(getClassName() + " removeArtefact(artefact): " + artefact.getName());
		    
	    Owner owner = dao.findById(Owner.class, artefact.getOwner().getOwnerId());
		    
	    owner.getArtefacts().remove(artefact);
		    
	    Artefact a = getArtefactById(artefact.getArtefactId());
		
		    
	    // remove artefact tags
	    Tag tag;
		    
	    ArtefactDetail artefactDetail = a.getArtefactDetail();
		    
	    Set<ArtefactTag> artefactTags =  a.getArtefactTags();
		    
		List<ArtefactTag> tagsToRemove = new ArrayList<ArtefactTag>();

		// To avoid a ConcurrentModificationException extract the artefactTags into a seperate list then remove them
		for (ArtefactTag artefactTag: artefactTags){
	     		
	    	tagsToRemove.add(artefactTag);
	   	}
		    
		// remove all tags associated with the artefact   
	    for (ArtefactTag artefactTag: tagsToRemove){
		            
	        tag = dao.findById(Tag.class, artefactTag.getTag().getTagId());
		            
	        removeTagFromArtefact(artefact,tag);
	    }
	    	
	    // remove artefact mappings
	    Set<ArtefactMapping> artefactMappings =  a.getArtefactMappings(); 
	    	
		List<ArtefactMapping> mappingsToRemove = new ArrayList<ArtefactMapping>();

		// To avoid a ConcurrentModificationException extract the artefactTags into a seperate list then remove them
		for (ArtefactMapping artefactMapping: artefactMappings){
	     		
	   	    mappingsToRemove.add(artefactMapping);
	   	}
		    
		// remove all mappings associated with the artefact   
	    for (ArtefactMapping artefactMapping: mappingsToRemove){
		            
	        artefactMapping = dao.findById(ArtefactMapping.class, artefactMapping.getArtefactMappingId());
	            
	        removeArtefactMapping(artefactMapping);
	    }  	
	    	
	    dao.delete(artefactDetail);

		dao.delete(artefact);
		    
		dao.save(owner);
	}	    

    
	/**
	  * Remove an Artefact from a Showcase.
	  * @param Artefact
	  */
    public void removeArtefactFromShowcase(Artefact artefact) {
	    log.debug(getClassName() + " removeArtefactFromShowcase(artefact): " + artefact.getName());
		
	    Showcase showcase = getShowcaseById(artefact.getShowcase().getShowcaseId());
		    
	    artefact = getArtefactById(artefact.getArtefactId());
		    
	    artefact.setShowcase(null);
	    
	    saveArtefact(artefact);
		    
	    showcase.getArtefacts().remove(artefact);
	    
	    saveShowcase(showcase);
    } 

	    
	/**
	  * Remove an ArtefactMapping.
	  * @param ArtefactMapping
	  */		
	public void removeArtefactMapping(ArtefactMapping artefactMapping) {
	    log.debug(getClassName() + " removeArtefactMapping(artefactMapping): " + artefactMapping.getArtefactMappingId());
			    
	    Artefact artefact = dao.findById(Artefact.class, artefactMapping.getArtefact().getArtefactId());
		        
	    artefact.getArtefactMappings().remove(artefactMapping);
		 
	    dao.delete(artefactMapping);
		        
	}  

    
	/**
	  * Remove an ArtefactTag.
	  * @param ArtefactTag
	  */
    public void removeArtefactTag(ArtefactTag artefactTag) {
	    log.debug(getClassName() + " removeArtefactTag(artefactTag): " + artefactTag.getArtefactTagId());
			    
        Artefact artefact = dao.findById(Artefact.class, artefactTag.getArtefact().getArtefactId());
		        
        artefact.getArtefactTags().remove(artefactTag);
		        
        Tag tag = dao.findById(Tag.class, artefactTag.getTag().getTagId());
		        
        // maintain count of tag usage 
        tag.setSearchCount(tag.getSearchCount() - 1);
        dao.save(tag); 
		        
        tag.getArtefactTags().remove(artefactTag);
        
        dao.delete(artefactTag);
    } 		

    
	/**
	  * Remove an Owner.
	  * @param Owner
	  */	    
    public void removeOwner(Owner owner) {
       log.debug(getClassName() + " removeOwner(owner) userId:" + owner.getUserId());
		       
       dao.delete(owner);
    }  

    
	/**
	  * Remove a Reviewer.
	  * @param Reviewer
	  */		    
    public void removeReviewer(Reviewer reviewer) {
 	    log.debug(getClassName() + " deleteReviewer(reviewer) reviewer:" + reviewer.getReviewerId());
		       
	    Owner owner = dao.findById(Owner.class, reviewer.getOwner().getOwnerId());
	    
	    owner.getReviewers().remove(reviewer);
 	    
	    dao.delete(reviewer);
    }    

    
	/**
	  * Remove a Reviewer from a Showcase.
	  * @param Reviewer
	  * @param Showcase
	  */		 
    public void removeReviewerFromShowcase(Reviewer reviewer, Showcase showcase) {
  	    log.debug(getClassName() + " removeReviewerToShowcase(reviewer,showcase)");
		  	    
  	    ShowcaseReviewer existingShowcaseReviewer = getShowcaseReviewer(showcase, reviewer);
		  	    
  	    if (existingShowcaseReviewer != null){
		  	    	
 	    	reviewer.getShowcaseReviewers().remove(existingShowcaseReviewer);
 	    	
  	    	showcase.getShowcaseReviewers().remove(existingShowcaseReviewer);
  	    	
  	    	removeShowcaseReviewer(existingShowcaseReviewer);
  	    }
    } 			    

    
	/**
	  * Remove a Showcase.
	  * @param Showcase
	  */	
    public void removeShowcase(Showcase showcase) {
	    log.debug(getClassName() + " removeShowcase(showcase) showcaseId: " + showcase.getShowcaseId());
			   
	    dao.delete(showcase);
    }	

    
	/**
	  * Remove a ShowcaseReviewer.
	  * @param ShowcaseReviewer
	  */
    public void removeShowcaseReviewer(ShowcaseReviewer showcaseReviewer) {
	    log.debug(getClassName() + " removeShowcaseReviewer(showcaseReviewer): " + showcaseReviewer.getShowcaseReviewerId());
			    
        Showcase showcase = dao.findById(Showcase.class, showcaseReviewer.getShowcase().getShowcaseId());
		        
        showcase.getShowcaseReviewers().remove(showcaseReviewer);
		        
        Reviewer reviewer = dao.findById(Reviewer.class, showcaseReviewer.getReviewer().getReviewerId());
		        
	    reviewer.getShowcaseReviewers().remove(showcaseReviewer);
		 
        dao.delete(showcaseReviewer);
		        
    } 

    
	/**
	  * Remove a Tag.
	  * @param Tag
	  */		    
    public void removeTag(Tag tag) {
       log.debug(getClassName() + " removetag(tag) tag:" + tag.getName());
		       
       dao.delete(tag);
    }  	

    
	/**
	  * Remove a Tag from an Artefact.
	  * @param Artefact
	  * @param Tag
	  */			   
    public void removeTagFromArtefact(Artefact artefact, Tag tag) {
  	    log.debug(getClassName() + " removeTagFromArtefact(tag) tag:" + tag.getName());
		  	    
  	    ArtefactTag artefactTag = getArtefactTag(artefact,tag);
		  	    
  	    artefact.getArtefactTags().remove(artefactTag);
  	    
  	    tag.getArtefactTags().remove(artefactTag);
  	    
  	    removeArtefactTag(artefactTag);
    } 			    

	public void removeTagFromArtefact(Long artefactId, String tagName) {
	} 

    
	/**
	  * Remove a Tag from an Artefact.
	  * @param Artefact
	  * @param Tag
	  */			    
    public void removeTagFromArtefact(String artefactId, String tagName) {
  	    log.debug(getClassName() + " removeTagFromArtefact(tag) tag:" + tagName);
		  	   
  	    Artefact artefact = getArtefactById(new Long(artefactId));

  	    Tag tag = getTag(tagName,artefact.getOwner());
		  	    
  	    ArtefactTag artefactTag = getArtefactTag(artefact,tag);
		  	    
  	    artefact.getArtefactTags().remove(artefactTag);
  	    
  	    tag.getArtefactTags().remove(artefactTag);
  	    
  	    removeArtefactTag(artefactTag);
		  	    
  	    // Housekeep the tag. If not attached to any other artefacts, then delete the tag.
        List<ArtefactTag> attachedTags = getArtefactTags(tag);
        
        if (attachedTags.isEmpty()){
        	
    		removeTag(tag);
     	}
    } 	

			
//****************************************************************************************//		
//		  		
//   Add Service Methods
//		  		
//****************************************************************************************//

    
	/**
	  * Add a Tag to an Artefact.
	  * @param String Artefact id
	  * @param String Tag name
	  */				   
    public void addArtefactTag(String artefactId, String tagName) {
  	    log.debug(getClassName() + " addArtefactTag:" + tagName);
		 	    
  	    Artefact artefact = getArtefactById(new Long(artefactId));
		  	    
  	    Tag tag = getTag(tagName,artefact.getOwner());
		  	    
  	    if (tag == null){
		  	    	
  	    	tag = new Tag();
  	    	tag.setName(tagName);
  	    	tag.setOwner(artefact.getOwner());
		  	    	
  	    	saveTag(tag);
  	    }
		  	    
  	    ArtefactTag artefactTag = new ArtefactTag(artefact,tag);
		  	    
  	    artefact.getArtefactTags().add(artefactTag);
		  	    
  	    tag.getArtefactTags().add(artefactTag);
		  	    
  	    saveArtefactTag(artefactTag);
    } 	

    
	/**
	  * Add a Reviewer to a Showcase.
	  * @param Reviewer
	  * @param Showcase
	  */	
    public void addReviewerToShowcase(Reviewer reviewer, Showcase showcase) {
  	    log.debug(getClassName() + " addReviewerToShowcase(reviewer,showcase)");
		   	    
  	    ShowcaseReviewer existingShowcaseReviewer = getShowcaseReviewer(showcase, reviewer);
		  	    
  	    if (existingShowcaseReviewer == null){
		  	    	
  	    	ShowcaseReviewer showcaseReviewer = new ShowcaseReviewer(showcase,reviewer);
  	    	
  	    	reviewer.getShowcaseReviewers().add(showcaseReviewer);
  	    	
  	    	showcase.getShowcaseReviewers().add(showcaseReviewer);
  	    	
  	    	saveShowcaseReviewer(showcaseReviewer);
  	    }
    }	

    
	/**
	  * Add a Tag to an Artefact.
	  * @param Artefact
	  * @param Tag
	  */			    
    public void addTagToArtefact(Artefact artefact, Tag tag) {
  	    log.debug(getClassName() + " saveArtefactTag(tag) tag:" + tag.getName());
		 	    
  	    saveTag(tag);
		  	    
  	    artefact = getArtefactById(artefact.getArtefactId());
		  	    
  	    ArtefactTag artefactTag = new ArtefactTag(artefact,tag);
		  	    
  	    artefact.getArtefactTags().add(artefactTag);
  	    
  	    tag.getArtefactTags().add(artefactTag);
  	    
  	    saveArtefactTag(artefactTag);
    } 

    
	/**
	  * Add an Artefact to a Showcase.
	  * @param Artefact
	  * @param Showcase
	  */	
    public void addArtefactToShowcase(Artefact artefact, Showcase showcase) {
	    log.debug(getClassName() + " addArtefactToShowcase(artefact): " + artefact.getName());

	    Artefact showcaseArtefact = artefact.getDeepCopy();
			    
	    ArtefactType artefactType = artefact.getType();
			    
	    showcaseArtefact.setType(artefactType);
	    
	    showcaseArtefact.setOwner(artefact.getOwner());
	    
	    showcaseArtefact.setShowcase(showcase);
			    
	    saveArtefact(showcaseArtefact);
			    
	    showcase.getArtefacts().add(showcaseArtefact);
			    
	    saveShowcase(showcase);
			    
	    // add any artefact framework/competency mappings
	    List<ArtefactMapping> artefactMappings = getArtefactMappings(artefact);
			    
	    for (ArtefactMapping artefactMapping: artefactMappings){
			    	
	    	updateArtefactMapping(showcaseArtefact,artefactMapping.getCompetencyId(),artefactMapping.getMappingId(),true);
	    }
    }    		    

			
//****************************************************************************************//		
//		  		  		
//   OBTAIN Service Calls
//		  		  		
//****************************************************************************************//

    
	/**
	  * Obtain an Owner.
	  * @return Owner
	  */	
	public Owner obtainOwner() {
        log.debug(getClassName() + " obtainOwner()");
				
        String userId = getCurrentUserId();		
		        		
        Owner owner = dao.findOneBySearch(Owner.class, new Search("userId",userId));
				
        if (owner!=null){
		        
	        owner.getShowcases().toArray();
	            
		    owner.getArtefacts().toArray();
		        
		    owner.getReviewers().toArray();
		            
		    owner.getAccounts().toArray();
		            
	        owner.getTags().toArray();
		            
		  	saveOwner(owner);
		            
    	} else{
					
    		owner = new Owner(userId);

    		saveOwner(owner);
    	}
				
        return owner;
    } 
	
	
	/**
	  * Obtain an Owner associated with an Account.
	  * @param Account
	  * @return Owner
	  */
	public Owner obtainOwner(Account account) {
       log.debug(getClassName() + " obtainOwner()");
		        
       AccountSource accountSource = getAccountSource(account.getAccountSource());
		       
        String[] properties = {"accountSource","userId" };
        
        Object[] values = {accountSource,account.getUserId()};
        
        int[] comparisons = {Restriction.EQUALS,Restriction.EQUALS}; 
		        
        Owner owner = null;
		      
        Account existingAccount = dao.findOneBySearch(Account.class, new Search(properties,values,comparisons));

        if (existingAccount != null){
		        	
			owner = existingAccount.getOwner();
					
			populateOwnerCollections(owner);
		}
		         
        return owner;
    }    
	
	
	/**
	  * Obtain an Owner associated with an Account.
	  * @param Account
	  * @return Owner
	  */
	public Owner obtainOwner(String userName, String password) {
       log.debug(getClassName() + " obtainOwner()");
		        
        String[] properties = {"userName","password"};
        Object[] values = {userName,password};
        int[] comparisons = {Restriction.EQUALS,Restriction.EQUALS}; 
		        
        Owner owner = dao.findOneBySearch(Owner.class, new Search(properties,values,comparisons));

        if (owner != null){

        	populateOwnerCollections(owner);
		}
		         
        return owner;
    }    		    
	
	
	/**
	  * Obtain current Showcase (not published) for an Owner.
	  * @param Owner
	  * @return Showcase
	  */	
	public Showcase obtainCurrentShowcase(Owner owner) {
        log.debug(getClassName() + " obtainCurrentShowcase(Owner) ownerId: " + owner.getOwnerId());
		        
        String[] properties = {"owner","published" };
		        
	    Object[] values = {owner,false};
		        
        int[] comparisons = {Restriction.EQUALS,Restriction.EQUALS};
		        
        Order[] order = {};
		        
        //Default Showcase Theme
        ShowcaseTheme theme = getShowcaseTheme("default");
		        
        List<Showcase> showcases = dao.findBySearch(Showcase.class, new Search(properties,values,comparisons,order));
		        
        Showcase currentShowcase = new Showcase();
		      
        if (showcases.isEmpty()){
		   	
		        	currentShowcase.setPublished(false);
		        	
		        	currentShowcase.setName("My Showcase");
		        	
		        	currentShowcase.setOwner(owner);
		        	
		        	currentShowcase.setTheme(theme);
		        	
		        	saveShowcase(currentShowcase);
        }
        else
        {
        	for (Showcase showcase: showcases){ 
		        	       	
        		currentShowcase = showcase;
		        		
        		currentShowcase.getArtefacts().toArray();
		        		
        		currentShowcase.getShowcaseReviewers().toArray();
		        		
        		currentShowcase.getTheme().getShowcases().toArray();
           	}
        }
		       
        return currentShowcase;
    }    

			
//****************************************************************************************//		
//					  		  		
//   MISC Service Calls
//					  		  		
//****************************************************************************************//


		    
    /**
     * This method populates all collections that form part of an Owner instance. 
     * @param Owner 
     */    
	private void populateOwnerCollections(Owner owner){
	        log.debug(getClassName() + " populateOwnerCollections()");

        if (owner!=null){
		        
            owner.getShowcases().toArray();
		            
            owner.getArtefacts().toArray();
		            
            owner.getReviewers().toArray();
		            
            owner.getAccounts().toArray();
		            
            owner.getTags().toArray();
    	}
    } 

    
    /**
     * This method populates all collections that form part of an ArtefactType instance
     * @param ArtefactType 
     */    
	private void populateArtefactTypeCollections(ArtefactType artefactType){
	        log.debug(getClassName() + " populateArtefactTypeCollections()");
	        
        if (artefactType != null){
	        	
        	artefactType.getArtefacts().toArray();	
        }
    } 

    
    /**
     * This method populates all collections that form part of an Artefact instance
     * @param Owner 
     */    
	private void populateArtefactCollections(Artefact artefact){
	        log.debug(getClassName() + " populateArtefactCollections()");
	        
        if (artefact!=null){
	            
       		artefact.getArtefactDetail();
       		
       		artefact.getShowcase();
       		
        	artefact.getOwner();
      	
        	artefact.getType();
        	
       		artefact.getOwner().getArtefacts().toArray();
	       		
       		artefact.getOwner().getReviewers().toArray();
	       		
       		artefact.getOwner().getShowcases().toArray();
	       		
       		artefact.getOwner().getTags().toArray();
       		
       		artefact.getType().getArtefacts().toArray();
       		
       		artefact.getArtefactTags().toArray();
    	}     

    } 

    
    /**
     * This method populates all collections that form part of an Artefact instance
     * @param Owner 
     */    
	private void populateAccountCollections(Account account){
	        log.debug(getClassName() + " populateAccountCollections()");
	        
	    if (account!=null){

	   		account.getOwner().getArtefacts().toArray();
	   		
	   		account.getOwner().getReviewers().toArray();
	   		
	   		account.getOwner().getShowcases().toArray();
	   		
	   		account.getOwner().getTags().toArray();
	   	}	        
 
    }  
	
	
    /**
     * This method populates all collections that form part of a ShowcaseTheme
     * @param ShowcaseTheme 
     */    
	private void populateShowcaseThemeCollections(ShowcaseTheme showcaseTheme){
	        log.debug(getClassName() + " populateShowcaseThemeCollections()");
	        
        if (showcaseTheme != null){
        	
        	showcaseTheme.getShowcases().toArray();	
        }
    } 	
	
	
    /**
     * This method populates all collections that form part of a Tag
     * @param tag 
     */    
	private void populateTagCollections(Tag tag){
	        log.debug(getClassName() + " populateTagCollections()");
	        
        if (tag != null){
        	
        	tag.getArtefactTags().toArray();
        }
    } 		
	
	
    /**
     * This method populates all collections that form part of a Reviewer
     * @param tag 
     */    
	private void populateReviewerCollections(Reviewer reviewer){
	        log.debug(getClassName() + " populateReviewerCollections()");
	        
        if (reviewer != null){

        	reviewer.getShowcaseReviewers().toArray();
        }
    } 
	
	
    /**
     * This method populates all collections that form part of a Showcase
     * @param Showcase 
     */    
	private void populateShowcaseCollections(Showcase showcase){
	        log.debug(getClassName() + " populateShowcaseCollections()");
	        
        if (showcase != null){
        	
    	    showcase.getTheme().getShowcases().toArray();
    	    
            showcase.getOwner();
            
            showcase.setOwnerId(showcase.getOwner().getOwnerId());
            
            showcase.getArtefacts().toArray();
            
            showcase.getShowcaseReviewers().toArray();
            
            showcase.getTheme().getShowcases();
        }
    } 	

	
	

 	
 	
 	
	public String ordinalOfInteger(int i) {
		
		String ordinalString = "" ;
		
		if ((i % 10 == 1) && (i != 11))
			
			ordinalString = i + "st" ;
		
		else if ((i % 10 == 2) && (i != 12))
			
			ordinalString = i + "nd" ;
		
		else if ((i % 10 == 3) && (i != 13))
			
			ordinalString = i + "rd" ;
		
		else
			
			ordinalString = i + "th" ;
			
		return ordinalString ;
	}

	
	public String shortMonthName(int month) {
		return monthName(month).substring(0,3) ;
	}
	
	public String monthName(int month) {
		
		String monthString = "" ;
        switch (month) {
	        case 1:  monthString = "January"; break;
	        case 2:  monthString = "February"; break;
	        case 3:  monthString = "March"; break;
	        case 4:  monthString = "April"; break;
	        case 5:  monthString = "May"; break;
	        case 6:  monthString = "June"; break;
	        case 7:  monthString = "July"; break;
	        case 8:  monthString = "August"; break;
	        case 9:  monthString = "September"; break;
	        case 10: monthString = "October"; break;
	        case 11: monthString = "November"; break;
	        case 12: monthString = "December"; break;
	        default: monthString = "Invalid month.";break;
        }

		return monthString ;
	}

	
	
	public String formattedLongDate(Date date) {
		return ordinalOfInteger(date.getDate()) + " " + monthName(date.getMonth() + 1) + " " + (1900 + date.getYear()) ;
	}	

	
	   
    /**
      * Return the email message sent out to ShowcaseReviewer's.
      * @param Showcase
      * @param Reviewer
      * @param Owner
      * @return String email message
      */ 	
	public String getEmailMessage(Showcase showcase, Reviewer reviewer, Owner owner) {
      
        String hostName = MyShowcaseConfigValues.getInstance().getApplicationHost();
		
        StringBuffer msg = new StringBuffer();
        
//		String msgURL = "/direct/myshowcase/showcaseID=" + showcase.getShowcaseId() + "/reviewerID=" + reviewer.getReviewerId() ;
		
//        String msgURL =  MyShowcaseConfigValues.getInstance().getShowcaseReviewBaseUrl() + "showcaseId=" + showcase.getShowcaseId() + "&reviewerId=" + reviewer.getReviewerId();

        String msgURL = getEmailReviewUrl(showcase,reviewer,owner);
        
		Date startDate = showcase.getReviewStartDate();
        
		Date endDate = showcase.getReviewEndDate();
		
		String userName = owner.getForename() + " " + owner.getSurname();

        msg.append("Dear ") ;
        msg.append(reviewer.getName()) ;
        msg.append(",\n") ;
        msg.append("\n") ;
        msg.append("You have been invited to view an online showcase created by ") ;
        msg.append(userName) ;
        msg.append(".\n") ;
        msg.append("\n") ;
        msg.append("You can access the showcase by visiting ") ;
        msg.append(hostName) ;
        msg.append(msgURL) ;
        msg.append(" or by pasting the address into your internet browser.\n") ;
        msg.append("\n") ;
        if (startDate != null) {
	        if (endDate == null) {
	            msg.append("This showcase will be available from ") ;
	        	msg.append(formattedLongDate(startDate)) ;
	            msg.append(".\n") ;
	        } else {
	        	msg.append("This showcase will be available between ") ;
	        	msg.append(formattedLongDate(startDate)) ;
	        	msg.append(" and ") ;
	        	msg.append(formattedLongDate(endDate)) ;
	        	msg.append(".\n") ;
	        }
	        msg.append("\n") ;
        }
        msg.append("Best Regards,\n") ;
        msg.append("\n") ;
        msg.append("The MyShowcase Team.\n") ;
        msg.append("\n") ;
        msg.append("--\n") ;
        msg.append("This is an automated message from MyShowcase. PLEASE DO NOT REPLY TO THIS EMAIL.\n") ;
        msg.append("--\n") ;
        msg.append("Find out more about MyShowcase at http://www.my-showcase.org\n") ;
        msg.append("\n") ;
       	msg.append("Follow http://www.twitter.com/myshowcasetool on Twitter for My Showcase announcements\n") ;
        msg.append("\n") ;
       	msg.append("Join us on Facebook at http://www.facebook.com/pages/MyShowcase/140331095994186\n") ;
        msg.append("\n") ;
       	msg.append("Join us on LinkedIn at http://www.linkedin.com/groups?mostPopular=&amp;gid=3239426\n") ;
        msg.append("\n") ;

		return msg.toString();
	}		
}

