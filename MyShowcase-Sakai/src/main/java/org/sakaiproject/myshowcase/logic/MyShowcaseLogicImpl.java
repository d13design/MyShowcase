/******************************************************************************
 * MyShowcaseLogicImpl.java - created by Sakai App Builder -AZ
 * 
 * Copyright (c) 2008 Sakai Project/Sakai Foundation
 * Licensed under the Educational Community License version 1.0
 * 
 * A copy of the Educational Community License has been included in this 
 * distribution and is available at: http://www.opensource.org/licenses/ecl1.php
 * 
 *****************************************************************************/

package org.sakaiproject.myshowcase.logic;

import java.util.Date;

import java.util.List;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.sakaiproject.genericdao.api.search.Search;
import org.sakaiproject.genericdao.api.search.Order;
import org.sakaiproject.genericdao.api.search.Restriction;
import org.sakaiproject.myshowcase.logic.*;

import org.sakaiproject.myshowcase.dao.MyShowcaseDao;
import org.sakaiproject.myshowcase.logic.MyShowcaseLogic;
import org.sakaiproject.myshowcase.model.SampleItem;
import org.sakaiproject.myshowcase.model.Owner;
import org.sakaiproject.myshowcase.model.Reviewer;
import org.sakaiproject.myshowcase.model.ArtefactType;
import org.sakaiproject.myshowcase.model.Showcase;
import org.sakaiproject.myshowcase.model.ShowcaseTheme;
import org.sakaiproject.myshowcase.model.Artefact;
import org.sakaiproject.myshowcase.model.ArtefactDetail;
import org.sakaiproject.myshowcase.model.Tag;
import org.sakaiproject.myshowcase.model.ArtefactTag;
import org.sakaiproject.myshowcase.model.ShowcaseReviewer;

/**
 * This is the implementation of the business logic interface
 * @author Sakai App Builder -AZ
 */
public class MyShowcaseLogicImpl implements MyShowcaseLogic {

    private static Log log = LogFactory.getLog(MyShowcaseLogicImpl.class);

    private MyShowcaseDao dao;

    
    public void setDao(MyShowcaseDao dao) {
       this.dao = dao;
    }

private ExternalLogic externalLogic;
 
    
    public void setExternalLogic(ExternalLogic externalLogic) {
       this.externalLogic = externalLogic;
    }

    
    /**
     * Place any code that should run when this class is initialized by spring here
     */
    public void init() {
       log.debug(getClassName() + "init()");
    }

    
    public String getCurrentUserId() {
        log.debug(getClassName() + " getCurrentUserId()");
        return externalLogic.getCurrentUserId();
    } 

    
    public String getClassName() {
    	return this.getClass().getName();
    } 

    
    public void saveOwner(Owner owner) {
  	    log.debug(getClassName() + " saveOwner(owner) userId:" + owner.getUserId());
 	    
	    Date date = new Date();
	    String currentUser = getCurrentUserId();
        
        if (owner.getCreatedDate() == null) {
        	owner.setCreatedDate(date);
            owner.setCreatedBy(currentUser);
        }
        else {
     	    owner.setUpdatedDate(date);
            owner.setUpdatedBy(currentUser);
        }
      
        dao.save(owner);
    } 

    
    public void removeOwner(Owner owner) {
       log.debug(getClassName() + " removeOwner(owner) userId:" + owner.getUserId());
       
       dao.delete(owner);
    }  

    
    public Owner getOwnerById(Long id) {
        log.debug(getClassName() + " getOwnerById(id) id: " + id);
       
        Owner owner = dao.findById(Owner.class, id);

        if (owner!=null){
            owner.getShowcases();
            owner.getArtefacts();
            owner.getReviewers();
            owner.getTags();
        }
       
        return owner;
    }

    
	public Owner getOwner(String userId) {
        log.debug(getClassName() + " getOwner(userId) userId: " + userId);
       
        Owner owner = dao.findOneBySearch(Owner.class, new Search("userId",userId));
        
        if (owner!=null){
        	
            owner.getShowcases();
            owner.getArtefacts();
            owner.getReviewers();
            owner.getTags();
            
    	} else{
    		
    		owner = new Owner(userId);
    		saveOwner(owner);
    	}
      
        return owner;
    }    
 
	
    public List<ArtefactType> getAllArtefactTypes() {
        log.debug(getClassName() + " getAllArtefactTypes()");
       
        return dao.findAll(ArtefactType.class);
    }
 
    
    public ArtefactType getArtefactTypeById(Long id) {
        log.debug(getClassName() + " getArtefactTypeById(id) id: " + id);
       
        return dao.findById(ArtefactType.class, id);
    } 
 
    
    public List<ShowcaseTheme> getAllShowcaseThemes() {
        log.debug(getClassName() + " getAllShowcaseThemes()");
       
        return dao.findAll(ShowcaseTheme.class);
    }

    
    public ShowcaseTheme getShowcaseThemeById(Long id) {
        log.debug(getClassName() + " getShowcaseThemeById(id): " + id);
       
        return dao.findById(ShowcaseTheme.class, id);
    }

    
    public ShowcaseReviewer getShowcaseReviewerById(Long id) {
        log.debug(getClassName() + " getShowcaseReviewerById(id): " + id);
       
        return dao.findById(ShowcaseReviewer.class, id);
    }

    
    public Reviewer getReviewerById(Long id) {
        log.debug(getClassName() + " getReviewerById(id) id: " + id);
       
        return dao.findById(Reviewer.class, id);
    }  

    
    public void saveReviewer(Reviewer reviewer) {
 	    log.debug(getClassName() + " saveReviewer(reviewer) reviewer:" + reviewer.getName());
 	    
        if (reviewer.getCreatedDate() == null) {
           reviewer.setCreatedDate( new Date() );
           reviewer.setCreatedBy(getCurrentUserId());
           Owner owner = dao.findById(Owner.class, reviewer.getOwner().getOwnerId());
           owner.getReviewers().add(reviewer);
        }
        else {
     	    reviewer.setUpdatedDate( new Date() );
            reviewer.setUpdatedBy(getCurrentUserId());  
        }
       
        dao.save(reviewer);
    } 

    
    public void removeReviewer(Reviewer reviewer) {
 	    log.debug(getClassName() + " deleteReviewer(reviewer) reviewer:" + reviewer.getReviewerId());
       
	    Owner owner = dao.findById(Owner.class, reviewer.getOwner().getOwnerId());
	    owner.getReviewers().remove(reviewer);
 	    dao.delete(reviewer);
    }    
 
    
    public void saveArtefact(Artefact artefact) {
	    log.debug(getClassName() + " saveArtefact(artefact): " + artefact.getName());
	
	    Date date = new Date();
	    String currentUser = getCurrentUserId();
	    
        if (artefact.getCreatedDate() == null) {
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
        System.out.println("Save Artefact") ;
        dao.save(artefact);
        
        System.out.println("Save ArtefactDetail 01") ;
        artefact.getArtefactDetail().setArtefact(artefact);
        System.out.println("Save ArtefactDetail 02") ;
        dao.save(artefact.getArtefactDetail());
        System.out.println("Saved Artefact") ;
    } 

    
    public void removeArtefact(Artefact artefact) {
	    log.debug(getClassName() + " removeArtefact(artefact): " + artefact.getName());
	    
	    Owner owner = dao.findById(Owner.class, artefact.getOwner().getOwnerId());
	    owner.getArtefacts().remove(artefact);
	    dao.delete(artefact);
    }

    
    public void addArtefactToShowcase(Artefact artefact, Showcase showcase) {
	    log.debug(getClassName() + " addArtefactToShowcase(artefact): " + artefact.getName());
	
	    Artefact showcaseArtefact = artefact.getDeepCopy();
	    showcaseArtefact.setShowcase(showcase);
	    
	    saveArtefact(showcaseArtefact);
	    
	    showcase.getArtefacts().add(showcaseArtefact);
	    saveShowcase(showcase);
    }    

    
    public void removeArtefactFromShowcase(Artefact artefact) {
	    log.debug(getClassName() + " removeArtefactFromShowcase(artefact): " + artefact.getName());
	
	    Showcase showcase = getShowcaseById(artefact.getShowcase().getShowcaseId());
	    
	    artefact = getArtefactById(artefact.getArtefactId());
	    
	    artefact.setShowcase(null);
	    saveArtefact(artefact);
	    
	    showcase.getArtefacts().remove(artefact);
	    saveShowcase(showcase);
    }       

    
    public Artefact getArtefactById(Long id) {
        log.debug(getClassName() + " getArtefactById(id) id: " + id);
       
        Artefact artefact = dao.findById(Artefact.class, id);
        
        if (artefact!=null){
    	   artefact.getArtefactDetail();
           artefact.getOwner();
           artefact.getShowcase();
           artefact.getType();
           artefact.getArtefactTags();
    	}
        
        return artefact;
    } 

    
	public List<Artefact> getArtefacts(Owner owner) {
        log.debug(getClassName() + " getArtefacts(Owner) ownerId: " + owner.getOwnerId());
       
        Showcase showcase = new Showcase();
        String[] properties = {"owner","showcase" };
        Object[] values = {owner,showcase};
        int[] comparisons = {Restriction.EQUALS,Restriction.NULL}; 
        
        List<Artefact> artefacts = dao.findBySearch(Artefact.class, new Search(properties,values,comparisons));
        
        for (Artefact artefact: artefacts){ 
        	artefact.getArtefactDetail();
        	artefact.getOwner();
        	artefact.getShowcase();
        	artefact.getType();
        	artefact.getArtefactTags();
        }
     
        return artefacts;
    } 

	
	public List<Artefact> getArtefacts(Owner owner,ArtefactType type) {
        log.debug(getClassName() + " getArtefacts(Owner,Type) ownerId: " + owner.getOwnerId());
       
        Showcase showcase = new Showcase();
        String[] properties = {"owner","type","showcase" };
        Object[] values = {owner,type,showcase};
        int[] comparisons = {Restriction.EQUALS,Restriction.EQUALS,Restriction.NULL}; 
        
        List<Artefact> artefacts = dao.findBySearch(Artefact.class, new Search(properties,values,comparisons));
        
        for (Artefact artefact: artefacts){
        	
        	artefact.getArtefactDetail();
        	artefact.getOwner();
        	artefact.getShowcase();
        	artefact.getType();
        }
        
        return artefacts;
    } 

	
	public List<Artefact> getArtefacts(Showcase showcase) {
        log.debug(getClassName() + " getArtefacts(Showcase) showcaseId: " + showcase.getShowcaseId());
        
        List<Artefact> artefacts = dao.findBySearch(Artefact.class, new Search("showcase",showcase));
 
        for (Artefact artefact: artefacts){ 
        	
        	artefact.getArtefactDetail();
        	artefact.getOwner();
        	artefact.getShowcase();
        	artefact.getType();
        	artefact.getArtefactTags();
       	
        }
        
        return artefacts;
    } 	

	
    public void saveShowcase(Showcase showcase) {
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

    
    public void removeShowcase(Showcase showcase) {
	    log.debug(getClassName() + " removeShowcase(showcase) showcaseId: " + showcase.getShowcaseId());
	   
	    dao.delete(showcase);
    }	

    
    public Showcase getShowcaseById(Long id) {
        log.debug(getClassName() + " getShowcaseById(id) showcaseId: " + id);
       
        Showcase showcase = dao.findById(Showcase.class, id);
        
        if (showcase!=null){
        	
    	    showcase.getTheme();
            showcase.getOwner();
            showcase.getArtefacts();
            showcase.getShowcaseReviewers();
        }
        
        return showcase;
    } 	

    
	public List<Showcase> getShowcases(Owner owner) {
        log.debug(getClassName() + " getShowcases(Owner) ownerId: " + owner.getOwnerId());
        
        List<Showcase> showcases = dao.findBySearch(Showcase.class, new Search("owner",owner));
        
        for (Showcase showcase: showcases){ 
        	showcase.getArtefacts();
        	showcase.getOwner();
        	showcase.getTheme();
        	showcase.getShowcaseReviewers();
        }
     
        return showcases;
    } 

    
	public List<Showcase> getPublishedShowcases(Owner owner) {
        log.debug(getClassName() + " getPublishedShowcases(Owner) ownerId: " + owner.getOwnerId());
        
        List<Showcase> showcases = dao.findBySearch(Showcase.class, new Search("owner",owner));
        
        for (Showcase showcase: showcases){
        	
        	showcase.getArtefacts();
        	showcase.getOwner();
        	showcase.getTheme();
        	showcase.getShowcaseReviewers();
        }
     
        return showcases;
    } 

	
	public void publishShowcase(Showcase showcase) {
        log.debug(getClassName() + " publishShowcase(Showcase) showcase: " + showcase.getName());
        
        showcase = getShowcaseById(showcase.getShowcaseId());
        //lock showcase
        showcase.setPublished(true);
        //lock artefacts
        Set<Artefact> artefacts = showcase.getArtefacts();
            for (Artefact artefact: artefacts){ 
        	
        	artefact.setLocked(true);
        	saveArtefact(artefact);
        }
        
        // invite  reviewers 
        
        saveShowcase(showcase);
    } 

	
	
    public void saveTag(Tag tag) {
  	    log.debug(getClassName() + " saveTag(tag) tag:" + tag.getName());
 	    
  	    if (tag.getTagId() == null){
  	    	Tag existingTag = getTag(tag.getName());
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

    
    public Tag getTagById(Long id) {
        log.debug(getClassName() + " getTagById(id) id: " + id);
       
        return dao.findById(Tag.class, id);
    }

    
    public ArtefactTag getArtefactTagById(Long id) {
        log.debug(getClassName() + " getArtefactTagById(id) id: " + id);
       
        return dao.findById(ArtefactTag.class, id);
    }
 
    
    public void incrementTagSearchCount(Tag tag) {
        log.debug(getClassName() + " incrementTagSearchCount tag: " + tag.getName());
       
        tag.setSearchCount(tag.getSearchCount() + 1);
        dao.save(tag);
    }

    
	public Tag getTag(String name) {
        log.debug(getClassName() + " getTag(name) name: " + name);
       
        Tag tag = dao.findOneBySearch(Tag.class, new Search("name",name));
        
        if (tag!=null){
           tag.getArtefactTags();
    	}
       
        return tag;
    }    	

	   
    public void addTagToArtefact(Artefact artefact, Tag tag) {
  	    log.debug(getClassName() + " saveArtefactTag(tag) tag:" + tag.getName());
 	    
  	    saveTag(tag);
  	    
  	    artefact = getArtefactById(artefact.getArtefactId());
  	    
  	    ArtefactTag artefactTag = new ArtefactTag(artefact,tag);
  	    
  	    artefact.getArtefactTags().add(artefactTag);
  	    tag.getArtefactTags().add(artefactTag);
  	    saveArtefactTag(artefactTag);
    } 	

	   
    public void removeTagFromArtefact(Artefact artefact, Tag tag) {
  	    log.debug(getClassName() + " removeTagFromArtefact(tag) tag:" + tag.getName());
 	    
  	    tag = getTagById(tag.getTagId());
  	    
  	    artefact = getArtefactById(artefact.getArtefactId());
  	    
  	    ArtefactTag artefactTag = getArtefactTag(artefact,tag);
  	    
  	    artefact.getArtefactTags().remove(artefactTag);
  	    tag.getArtefactTags().remove(artefactTag);
  	    removeArtefactTag(artefactTag);
    } 	

    
    private void saveArtefactTag(ArtefactTag artefactTag) {
	    log.debug(getClassName() + " saveArtefactTag(artefactTag): " + artefactTag.getArtefactTagId());
	
	    Date date = new Date();
	    String currentUser = getCurrentUserId();
	    
        if (artefactTag.getCreatedDate() == null) {
        	
        	artefactTag.setCreatedDate(date);
            artefactTag.setCreatedBy(currentUser);
            Artefact artefact = dao.findById(Artefact.class, artefactTag.getArtefact().getArtefactId());
            artefact.getArtefactTags().add(artefactTag);
            Tag tag = dao.findById(Tag.class, artefactTag.getTag().getTagId());
            tag.getArtefactTags().add(artefactTag);
        }
        else {
     	    artefactTag.setUpdatedDate(date);
            artefactTag.setUpdatedBy(currentUser);
        }
        
        dao.save(artefactTag);
    } 

    
    private void removeArtefactTag(ArtefactTag artefactTag) {
	    log.debug(getClassName() + " removeArtefactTag(artefactTag): " + artefactTag.getArtefactTagId());

        Artefact artefact = dao.findById(Artefact.class, artefactTag.getArtefact().getArtefactId());
        artefact.getArtefactTags().remove(artefactTag);
        
        Tag tag = dao.findById(Tag.class, artefactTag.getTag().getTagId());
        tag.getArtefactTags().remove(artefactTag);
 
        dao.delete(artefactTag);
    } 

	
	public ArtefactTag getArtefactTag(Artefact artefact,Tag tag) {
        log.debug(getClassName() + " getArtefactTag(Artefact,Tag) tag: " + tag.getName());
       
        String[] properties = {"artefact","tag" };
        Object[] values = {artefact,tag};
        ArtefactTag artefactTag = dao.findOneBySearch(ArtefactTag.class, new Search(properties,values));
        
        return artefactTag;
    } 

	
	public List<Tag> getTags(Owner owner) {
        log.debug(getClassName() + " getTags(Owner) ownerId: " + owner.getOwnerId());
        
        List<Tag> tags = dao.findBySearch(Tag.class, new Search("owner",owner));
        
        return tags;
    } 	

	
	public List<Tag> getTags(Artefact artefact) {
        log.debug(getClassName() + " getTags(Artefact) artefactId: " + artefact.getArtefactId());
         
        List<Tag> tags = new ArrayList<Tag>(); 
        List<ArtefactTag> artefactTags = dao.findBySearch(ArtefactTag.class, new Search("artefact",artefact));
        
        for (ArtefactTag artefactTag: artefactTags){ 
        	tags.add(artefactTag.getTag());
        }
        
        return tags;
    }

	   
    public void addReviewerToShowcase(Reviewer reviewer, Showcase showcase) {
  	    log.debug(getClassName() + " addReviewerToShowcase(reviewer,showcase)");
 	    
  	    saveReviewer(reviewer);
  	    
  	    showcase = getShowcaseById(showcase.getShowcaseId());
  	    
  	    ShowcaseReviewer showcaseReviewer = new ShowcaseReviewer(showcase,reviewer);
  	    
  	    reviewer.getShowcaseReviewers().add(showcaseReviewer);
  	    showcase.getShowcaseReviewers().add(showcaseReviewer);
  	    saveShowcaseReviewer(showcaseReviewer);
    } 		

    
    private void saveShowcaseReviewer(ShowcaseReviewer showcaseReviewer) {
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

/*	   
    public void removeReviewerFromShowcase(Reviewer reviewer, Showcase showcase) {
  	    log.debug(getClassName() + " removeReviewerFromShowcase(reviewer,showcase) reviewer:" + reviewer.getName());
 	    
  	    showcase = getShowcaseById(showcase.getShowcaseId());
  	    
  	    reviewer = getReviewerById(reviewer.getReviewerId());
  	    
  	    ShowcaseReviewer showcaseReviewer = getShowcaseReviewer(artefact,tag);
  	    
  	    artefact.getArtefactTags().remove(artefactTag);
  	    tag.getArtefactTags().remove(artefactTag);
  	    removeArtefactTag(artefactTag);
   
    } 	   
*/
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /* (non-Javadoc)
     * @see org.sakaiproject.myshowcaselogic.MyShowcaseogic#getAllVisibleItems(java.lang.String, java.lang.String)
     */
    public List<SampleItem> getAllVisibleItems(String locationId, String userId) {
       log.debug("Fetching visible items for " + userId + " in site: " + locationId);
       List<SampleItem> l = null;
       if (locationId == null) {
          // get all items
          l = dao.findAll(SampleItem.class);
       } else {
          l = dao.findBySearch(SampleItem.class, 
                new Search("locationId", locationId) );
       }
       // check if the current user can see all items (or is super user)
       if ( externalLogic.isUserAdmin(userId) || 
             externalLogic.isUserAllowedInLocation(userId, ExternalLogic.ITEM_READ_HIDDEN, locationId) ) {
          log.debug("Security override: " + userId + " able to view all items");
       } else {
          // go backwards through the loop to avoid hitting the "end" early
          for (int i=l.size()-1; i >= 0; i--) {
             SampleItem item = (SampleItem) l.get(i);
             if ( item.getHidden().booleanValue() &&
                   !item.getOwnerId().equals(userId) ) {
                l.remove(item);
             }
          }
       }
       return l;
    }

    /* (non-Javadoc)
     * @see org.sakaiproject.myshowcase.logic.MyShowcaseLogic#removeItem(org.sakaiproject.myshowcase.model.SampleItem)
     */
    public void removeItem(SampleItem item) {
       log.debug("In removeItem with item:" + item.getId() + ":" + item.getTitle());
       // check if current user can remove this item
       if ( canWriteItem(item, externalLogic.getCurrentLocationId(), externalLogic.getCurrentUserId() ) ) {
          dao.delete(item);
          log.info("Removing item: " + item.getId() + ":" + item.getTitle());
       } else {
          throw new SecurityException("Current user cannot remove item " + 
                item.getId() + " because they do not have permission");
       }
    }

    /* (non-Javadoc)
     * @see org.sakaiproject.myshowcase.logic.MyShowcaseLogic#saveItem(org.sakaiproject.myshowcase.model.SampleItem)
     */
    public void saveItem(SampleItem item) {
       log.debug("In saveItem with item:" + item.getTitle());
       // set the owner and site to current if they are not set
       if (item.getOwnerId() == null) {
          item.setOwnerId( externalLogic.getCurrentUserId() );
       }
       if (item.getLocationId() == null) {
          item.setLocationId( externalLogic.getCurrentLocationId() );
       }
       if (item.getDateCreated() == null) {
          item.setDateCreated( new Date() );
       }
       // save item if new OR check if the current user can update the existing item
       if ( (item.getId() == null) || 
             canWriteItem(item, externalLogic.getCurrentLocationId(), externalLogic.getCurrentUserId()) ) {
          dao.save(item);
          log.info("Saving item: " + item.getId() + ":" + item.getTitle());
       } else {
          throw new SecurityException("Current user cannot update item " + 
                item.getId() + " because they do not have permission");
       }
    }
    
    
    /* (non-Javadoc)
     * @see org.sakaiproject.myshowcase.logic.MyShowcaseLogic#getItemById(java.lang.Long)
     */
     public SampleItem getItemById(Long id) {
         log.debug("Getting item by id: " + id);
         return dao.findById(SampleItem.class, id);
     }

      /* (non-Javadoc)
       * @see org.sakaiproject.myshowcase.logic.MyShowcaseLogic#canWriteItem(org.sakaiproject.myshowcase.model.SampleItem, java.lang.String, java.lang.String)
       */
     public boolean canWriteItem(SampleItem item, String locationId, String userId) {
         log.debug("checking if can write for: " + userId + ", " + locationId + ": and item=" + item.getTitle() );
         
         if (item.getOwnerId().equals( userId ) ) {
          // owner can always modify an item
          return true;
       } else if ( externalLogic.isUserAdmin(userId) ) {
          // the system super user can modify any item
          return true;
       } else if ( locationId.equals(item.getLocationId()) &&
             externalLogic.isUserAllowedInLocation(userId, ExternalLogic.ITEM_WRITE_ANY, locationId) ) {
          // users with permission in the specified site can modify items from that site
          return true;
       }
       return false;
    }
   
    
}
