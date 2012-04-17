
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

import java.util.List;
import org.sakaiproject.myshowcase.model.SampleItem;
import org.sakaiproject.myshowcase.model.Owner;
import org.sakaiproject.myshowcase.model.ShowcaseTheme; 
import org.sakaiproject.myshowcase.model.ArtefactType;
import org.sakaiproject.myshowcase.model.Artefact;
import org.sakaiproject.myshowcase.model.Reviewer;
import org.sakaiproject.myshowcase.model.Showcase;
import org.sakaiproject.myshowcase.model.Tag;
import org.sakaiproject.myshowcase.model.ArtefactTag;

/**
 * This is the interface for the app Logic, 
 * @author MKM
 */
public interface MyShowcaseLogic {

   /**
    * This returns an item based on an id
    * @param id the id of the item to fetch
    * @return a SampleItem or null if none found
    */
   public SampleItem getItemById(Long id);

   
   /**
    * Save (Create or Update) an item (uses the current site)
    * @param item the SampleItem to create or update
    */
   public void saveItem(SampleItem item);

   
  /**
   * Remove an item
   * @param item the SampleItem to remove
   */
  public void removeItem(SampleItem item);


   /**
    * Check if a specified user can write this item in a specified site
    * @param item to be modified or removed
    * @param locationId a unique id which represents the current location of the user (entity reference)
    * @param userId the internal user id (not username)
    * @return true if item can be modified, false otherwise
    */
    public boolean canWriteItem(SampleItem item, String locationId, String userId);

   
    /**
    * This returns a List of items for a specified site that are
    * visible to the specified user
    * @param locationId a unique id which represents the current location of the user (entity reference)
    * @param userId the internal user id (not username)
    * @return a List of SampleItem objects
    */
    public List<SampleItem> getAllVisibleItems(String locationId, String userId);

   
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
     * This returns an owner by user id (SSO)
     * @param UserId of owner to retrieve
     * @return Owner or null if not found
     */
    public Owner getOwner(String userId); 

    
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
    public List<Artefact> getArtefacts(Owner owner);   

    
    /**
     * This returns a list of artefacts for an owner of a particular artefact type
     * @param Owner of the artefacts
     * @param Type of artefact to search for
     * @return Artefact list or null if none found
     */
    public List<Artefact> getArtefacts(Owner owner, ArtefactType artefactType);   

    
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
    public Tag getTag(String name);        
    
    
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
    public void incrementTagSearchCount(Tag tag);  

    
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
     * @param Artefact 
     * @param Tag to remove from artefact
     */
    public void removeTagFromArtefact(Artefact artefact, Tag tag);  
    
    
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
    public void saveReviewer(Reviewer reviewer);

    
    /**
     * Remove a showcase reviewer
     * @param Reviewer to remove
     */
    public void removeReviewer(Reviewer reviewer);      
    
}
