/******************************************************************************
 * MyShowcaseLogicImplTest.java - created by Sakai App Builder -AZ
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
import java.util.Set;

import junit.framework.Assert;

import org.sakaiproject.genericdao.api.search.Order;
import org.sakaiproject.myshowcase.dao.*;
import org.sakaiproject.myshowcase.model.Artefact;
import org.sakaiproject.myshowcase.model.ArtefactDetail;
import org.sakaiproject.myshowcase.model.Owner;
import org.sakaiproject.myshowcase.model.ShowcaseTheme;
import org.sakaiproject.myshowcase.model.Showcase;
import org.sakaiproject.myshowcase.model.ArtefactType;
import org.sakaiproject.myshowcase.model.Reviewer;
//import org.sakaiproject.myshowcase.model.SampleItem;
import org.sakaiproject.myshowcase.model.Tag;
import org.sakaiproject.myshowcase.model.ArtefactTag;
import org.sakaiproject.myshowcase.model.ShowcaseReviewer;
import org.springframework.test.AbstractTransactionalSpringContextTests;



/**
 * Testing the Logic implementation methods
 * @author Sakai App Builder -AZ
 */
public class MyShowcaseLogicImplTest extends AbstractTransactionalSpringContextTests {

    protected MyShowcaseService myshowcaseService;

    private FakeDataPreload tdp;

    private Owner owner1;
    String currentUser = "TestUser";
    private int numberOfOwnerArtefacts;
    private int numberOfOwnerArtefactsNotShowcased;
    private int numberOfOwnerShowcases;
    private int numberOfOwnerReviewers;
   
    private ArtefactType type1;
    private ArtefactType type2;
    private ArtefactType type3;
    private int numberOfArtefactTypes;
   
    private ShowcaseTheme theme1;
    private ShowcaseTheme theme2;
    private ShowcaseTheme theme3;
    private int numberOfShowcaseThemes;
    private int numberOfShowcaseArtefacts;
   
    private Artefact artefact1;
    private Artefact artefact2;
    private Artefact artefact3;
    private Artefact artefact4;
    private Artefact artefact5;
    private Artefact artefact6;
    private ArtefactDetail artefactDetail;
   
    private ArtefactTag artefactTag1;
    private ArtefactTag artefactTag2;
   
    private Showcase showcase1;
    private Showcase showcase2;
   
    private Reviewer reviewer1;
    private ShowcaseReviewer showcaseReviewer1;
   
    private Tag tag1;
    private Tag tag2;
    private Tag tag3;
    private Tag tag4;
   
    private int numberOfOwnerTags;
    private int numberOfArtefactTags;
   
    private int defaultSearchCount;
   
    protected String[] getConfigLocations() {
        // point to the needed spring config files, must be on the classpath
        // (add component/src/webapp/WEB-INF to the build path in Eclipse),
        // they also need to be referenced in the project.xml file
        return new String[] {"hibernate-test.xml", "spring-hibernate.xml"};
    }

    // run this before each test starts
    protected void onSetUpBeforeTransaction() throws Exception {
    	
    	owner1 = new Owner(currentUser, new Date(), "testuserid");
	   
	    theme1 = new ShowcaseTheme("Theme1", new Date(), currentUser);
	    theme2 = new ShowcaseTheme("Theme2", new Date(), currentUser);
	    theme3 = new ShowcaseTheme("Theme3", new Date(), currentUser);
	    numberOfShowcaseThemes = 3;
	   
	    type1 = new ArtefactType("Type1","Description1", new Date(), currentUser);
	    type2 = new ArtefactType("Type2","Description2", new Date(), currentUser);
	    type3 = new ArtefactType("Type3","Description3", new Date(), currentUser);
	    numberOfArtefactTypes = 3;
       
	    artefact1 = new Artefact("name","short desc","description", owner1, type1);
        artefactDetail = new ArtefactDetail("detail");
        artefactDetail.setFileName("test File Name");
        artefactDetail.setFilePath("test File Path");
        artefactDetail.setFileType("test File Type");
        artefactDetail.setUrl("test url");
        artefactDetail.setFlickrUserName("Flickr User Name");
        artefactDetail.setTwitterUserName("Twitter User Name");
        
        artefact1.setArtefactDetail(artefactDetail);

       
        artefact2 = new Artefact("name","short desc","description", owner1, type2);
        artefactDetail = new ArtefactDetail("detail");
        artefact2.setArtefactDetail(artefactDetail);
       
	    artefact3 = new Artefact("name","short desc","description", owner1, type1);
        artefactDetail = new ArtefactDetail("detail");
        artefact3.setArtefactDetail(artefactDetail);
       
        artefact4 = new Artefact("name","short desc","description", owner1, type2);
        artefactDetail = new ArtefactDetail("detail");
        artefact4.setArtefactDetail(artefactDetail);
       
	    artefact5 = new Artefact("name","short desc","description", owner1, type1);
        artefactDetail = new ArtefactDetail("detail");
        artefact5.setArtefactDetail(artefactDetail);
       
        artefact6 = new Artefact("name","short desc","description", owner1, type2);
        artefactDetail = new ArtefactDetail("detail");
        artefact6.setArtefactDetail(artefactDetail);      
       
	    showcase1 = new Showcase("name1","short desc1","full desc1",owner1,theme1);
	    showcase2 = new Showcase("name2","short desc2","full desc2",owner1,theme2);

	    numberOfOwnerShowcases = 2;
	    numberOfOwnerArtefacts = 6;
	   
	    defaultSearchCount = 0;
	   
	    tag1 = new Tag("tag1",owner1,defaultSearchCount);
	    tag2 = new Tag("tag2",owner1,defaultSearchCount);
	    tag3 = new Tag("tag3",owner1,defaultSearchCount);
	    tag4 = new Tag("tag3",owner1,defaultSearchCount);
	   
	    reviewer1 = new Reviewer("Jack Jones", "jjones@googlemail.com",owner1);
	   
	    artefactTag1 = new ArtefactTag(artefact1, tag1);
	    artefactTag2 = new ArtefactTag(artefact1, tag2);
	      
	    numberOfOwnerArtefacts = 6;
	    numberOfOwnerArtefactsNotShowcased = 4;
	    numberOfOwnerShowcases = 2;
	    numberOfOwnerTags = 4;
	    numberOfArtefactTags = 2;
	    numberOfShowcaseArtefacts = 2;
    }

   // run this before each test starts and as part of the transaction
   protected void onSetUpInTransaction() {
      // load the spring created dao class bean from the Spring Application Context
      MyShowcaseDao dao = (MyShowcaseDao) applicationContext.
 //        getBean("org.sakaiproject.myshowcase.dao.MyShowcaseDao");
      		getBean("myshowcase.dao.MyShowcaseDao");
      if (dao == null) {
         throw new NullPointerException("DAO could not be retrieved from spring context");
      }

      // load up the test data preloader from spring
 //     tdp = (FakeDataPreload) applicationContext.
 //        getBean("myshowcase.logic.test.FakeDataPreload");
 //     if (tdp == null) {
 //       throw new NullPointerException("FakeDataPreload could not be retrieved from spring context");
 //     }

      // reload the test objects in this session
 //     tdp.reloadTestData();
      
      // init the class if needed

      // setup the mock objects

      // create and setup the object to be tested
      myshowcaseService = new MyShowcaseService();
      
      myshowcaseService.setDao(dao);
      
 //     myshowcaseService.setExternalLogic( new ExternalLogicStub() );
      
//        myshowcaseService.setExternalLogic(new ExternalLogicStub());

      // can set up the default mock object returns here if desired
      // Note: Still need to activate them in the test methods though

      // run the init
      myshowcaseService.init();
      
      dao.save(owner1);
      
      
      dao.save(theme1);
      dao.save(theme2);
      dao.save(theme3);
      
      dao.save(type1);
      dao.save(type2);
      dao.save(type3);
      
      owner1.getArtefacts().add(artefact1);
      dao.save(artefact1);
      owner1.getArtefacts().add(artefact2);
      dao.save(artefact2);
      owner1.getArtefacts().add(artefact3);
      dao.save(artefact3);
      owner1.getArtefacts().add(artefact4);
      dao.save(artefact4);      
      owner1.getArtefacts().add(artefact5);
      dao.save(artefact5);
      owner1.getArtefacts().add(artefact6);
      dao.save(artefact6);
      
      owner1.getShowcases().add(showcase1);
      dao.save(showcase1);
      owner1.getShowcases().add(showcase2);
      dao.save(showcase2);
      
      owner1.getTags().add(tag1);
      dao.save(tag1);
      owner1.getTags().add(tag2);
      dao.save(tag2);
      owner1.getTags().add(tag3);
      dao.save(tag3);
      owner1.getTags().add(tag4);
      dao.save(tag4);
      
      tag1.getArtefactTags().add(artefactTag1);
      tag2.getArtefactTags().add(artefactTag2);
      artefact1.getArtefactTags().add(artefactTag1);
      artefact1.getArtefactTags().add(artefactTag2);
      dao.save(artefactTag1);
      dao.save(artefactTag2);
      dao.save(artefact1);
      dao.save(tag1);
      dao.save(tag2);
      
      showcase1.getArtefacts().add(artefact1);
      showcase1.getArtefacts().add(artefact2);
      artefact1.setShowcase(showcase1);
      artefact2.setShowcase(showcase1);
      
      dao.save(artefact1);
      dao.save(artefact2);
      dao.save(showcase1);
      
      dao.save(reviewer1);
      
      showcaseReviewer1 = new ShowcaseReviewer(showcase1,reviewer1);
      reviewer1.getShowcaseReviewers().add(showcaseReviewer1);
      showcase1.getShowcaseReviewers().add(showcaseReviewer1);
      dao.save(showcaseReviewer1);
      dao.save(reviewer1);
      dao.save(showcase1);
      
      
   }

   
   /**
    * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#getArtefactTypeById(id)}.
    */
    public void testGetArtefactTypeById() {
     
         ArtefactType type = myshowcaseService.getArtefactTypeById(type1.getArtefactTypeId());
         Assert.assertNotNull(type);
        
         type = myshowcaseService.getArtefactTypeById(new Long(999999999)); 
         Assert.assertNull(type);
    }    

    
    /**
     * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#getArtefactById(id)}.
     */
     public void testGetArtefactById() {
      
          Artefact artefact = myshowcaseService.getArtefactById(artefact1.getArtefactId());
          Assert.assertNotNull(artefact);
         
          artefact = myshowcaseService.getArtefactById(new Long(999999999)); 
          Assert.assertNull(artefact);
     }    

    
    /**
     * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#getAllArtefacts()}.
     */
    public void testGetAllArtefactTypes() {
      
         List <ArtefactType> types = myshowcaseService.getAllArtefactTypes();
         Assert.assertEquals(numberOfArtefactTypes, types.size());
    } 
    
    
    /**
     * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#getShowcaseThemeById()}.
     */
    public void testGetShowcaseThemeById() {
      
         ShowcaseTheme theme = myshowcaseService.getShowcaseThemeById(theme1.getShowcaseThemeId());
         Assert.assertNotNull(theme);
         
         theme = myshowcaseService.getShowcaseThemeById(new Long(999999999)); 
         Assert.assertNull(theme);
    }   

    
     /**
      * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#getAllShowcaseThemes()}.
      */
     public void testGetAllShowcaseThemes() {
       
          List <ShowcaseTheme> themes = myshowcaseService.getAllShowcaseThemes();
          Assert.assertEquals(numberOfShowcaseThemes, themes.size());
     }   

     
   /**
    * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#saveOwner(owner)}.
    */
    public void testSaveOwner() {
	    
    	Owner newOwner = new Owner("testuserid", new Date(), "testuserid");
	     
        myshowcaseService.saveOwner(newOwner);
        
        Assert.assertNotNull(newOwner.getOwnerId());
    }   

    
    /**
     * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#saveReviewer()}.
     */
     public void testSaveReviewer() {
 	     
    	 Owner newOwner = new Owner("testuserid", new Date(), "testuserid");
 	     myshowcaseService.saveOwner(newOwner);
         Assert.assertNotNull(newOwner.getOwnerId());
     } 


    /**
      * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#saveArtefact(artefact)}.
      */
    public void testSaveArtefact() {
  	    
    	// Set up data
        String name = "name-1";
        String detail = "detail-1"; 
        String amendedName = "name-2";
        String amendedDetail = "detail-2";
        int originalNumberOfArtefacts = owner1.getArtefacts().size();
        
        // Create
        Artefact newArtefact = new Artefact(name,"short desc","description", owner1, type1);
        ArtefactDetail newArtefactDetail = new ArtefactDetail(detail);
        newArtefact.setArtefactDetail(newArtefactDetail);
        myshowcaseService.saveArtefact(newArtefact);
        
        // Test create
        Assert.assertNotNull(newArtefact.getArtefactId());
        Assert.assertNotNull(newArtefactDetail.getArtefactId());
        Assert.assertEquals(newArtefact.getArtefactId(), newArtefactDetail.getArtefactId());
        Assert.assertNotNull(newArtefact.getCreatedDate());
        Assert.assertNotNull(newArtefact.getCreatedBy());
        Assert.assertNull(newArtefact.getUpdatedDate());
        Assert.assertNull(newArtefact.getUpdatedBy());
        
        // Update
        newArtefact.setName(amendedName);
        newArtefact.getArtefactDetail().setDetail(amendedDetail);
        myshowcaseService.saveArtefact(newArtefact);
        
        // Test update
        Assert.assertNotNull(newArtefact.getUpdatedDate());
        Assert.assertNotNull(newArtefact.getUpdatedBy());
        Assert.assertNotNull(newArtefact.getArtefactDetail().getUpdatedDate());
        Assert.assertNotNull(newArtefact.getArtefactDetail().getUpdatedBy());
        Owner owner = myshowcaseService.getOwnerById(owner1.getOwnerId());
        Assert.assertEquals(owner.getArtefacts().size(),originalNumberOfArtefacts + 1);
       
    }      


    /**
      * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#removeArtefact(artefact)}.
      */
    public void testRemoveArtefact() {
/* 	    
       int originalNumberOfArtefacts = owner1.getArtefacts().size();
       
    	long id = artefact1.getArtefactId();
    	
    	Artefact artefact = myshowcaseService.getArtefactById(id);
    	
    	System.out.println("############# in test artefactId = : " + id);
    	    	
    	myshowcaseService.removeArtefact(artefact);
   	
   	    Artefact retrievedArtefact = myshowcaseService.getArtefactById(id);
   	    
        Assert.assertNull(retrievedArtefact);
       
       Owner owner = myshowcaseService.getOwnerById(owner1.getOwnerId());
       Assert.assertEquals(owner.getArtefacts().size(),originalNumberOfArtefacts - 1);
*/
    }          
 
    
    /**
     * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#getArtefactsByOwner(owner)}.
     */
    public void testGetArtefactsByOwner() {
    		
    	 Order[] order = {};
    	 List <Artefact> artefacts = myshowcaseService.getArtefacts(owner1,order, null, null);
         Assert.assertEquals(numberOfOwnerArtefactsNotShowcased, artefacts.size());
    }      
 
    
    /**
     * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#getArtefacts(showcase)}.
     */
    public void testGetArtefactsByShowcase() {
      
         List <Artefact> artefacts = myshowcaseService.getArtefacts(showcase1);
         Assert.assertEquals(numberOfShowcaseArtefacts, artefacts.size());
    }       

    
    /**
     * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#getArtefacts(Owner,ArtefactType)}.
     */
    public void testGetArtefactsByOwnerArtefactType() {
      
    	 List <Artefact> artefacts = myshowcaseService.getArtefacts(owner1,type2);
         Assert.assertEquals(2, artefacts.size());
    }      


    /**
     * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#saveShowcase()}.
     */
     public void testSaveShowcase() {
 	             
         String amendedName = "name-2";
         
         int originalNumberOfShowcases = owner1.getShowcases().size();
         
         // Create
	     Showcase newShowcase = new Showcase("name","short desc","full desc",owner1,theme1);
	     myshowcaseService.saveShowcase(newShowcase);
	     
         //Test create
         Assert.assertNotNull(newShowcase.getShowcaseId());
         Assert.assertNotNull(newShowcase.getCreatedDate());
         Assert.assertNotNull(newShowcase.getCreatedBy());
         Assert.assertNull(newShowcase.getUpdatedDate());
         Assert.assertNull(newShowcase.getUpdatedBy());
         
         //Update
         newShowcase.setName(amendedName);
         myshowcaseService.saveShowcase(newShowcase);
         
         //Test update
         Assert.assertNotNull(newShowcase.getUpdatedDate());
         Assert.assertNotNull(newShowcase.getUpdatedBy());
         Owner owner = myshowcaseService.getOwnerById(owner1.getOwnerId());
         Assert.assertEquals(owner.getShowcases().size(),originalNumberOfShowcases + 1);
         
     }      

     
     /**
      * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#getShowcases(owner)}.
      */
     public void testGetShowcasesByOwner() {
       
          List <Showcase> showcases = myshowcaseService.getShowcases(owner1);
          Assert.assertEquals(2, showcases.size());
     }          

     
     /**
      * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#getShowcaseById(id)}.
      */
     public void testGetShowcasesById() {
       
          Showcase showcase = myshowcaseService.getShowcaseById(showcase1.getShowcaseId());
          Assert.assertNotNull(showcase);
     }          
     

    
    /**
     * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#getTag(name)}.
     */
 //   public void testGetTagByName() {
 //      
 //       Tag tag = myshowcaseService.getTag(tag1.getName());
 //       Assert.assertNotNull(tag);
 //         
 //       tag = myshowcaseService.getTag("not-exist-tag-name");
 //       Assert.assertNull(tag);
 //   }        

    
    /**
     * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#getTagById(id)}.
     */
    public void testGetTagById() {
       
    	Tag tag = new Tag("New-Tag",owner1,defaultSearchCount);
        myshowcaseService.saveTag(tag);
        Assert.assertNotNull(tag.getTagId());
          
        tag = myshowcaseService.getTag("not-exist-tag-name",owner1); 
        Assert.assertNull(tag);
    }       

    
    /**
     * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#getArtefactTagById(id)}.
     */
    public void testGetArtefactTagById() {
       
    	ArtefactTag artefactTag = myshowcaseService.getArtefactTagById(artefactTag1.getArtefactTagId());
    	Assert.assertNotNull(artefactTag1);
          
        artefactTag = myshowcaseService.getArtefactTagById(new Long(9999999)); 
        Assert.assertNull(artefactTag);
    }     
 
    
    /**
     * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#addTagToArtefact(artefact,tag)}.
     */
    public void testAddTagToArtefact() {
       
    	  //Create 
    	  Tag tag = new Tag("New-Tag",owner1,defaultSearchCount);
    	  
    	  int originalNumberOfArtefactTags = artefact1.getArtefactTags().size();
     	  
    	  myshowcaseService.addTagToArtefact(artefact1, tag); 
    	  
          //Test create
          Assert.assertNotNull(tag.getArtefactTags());
          
          Set <ArtefactTag> artefactTags = artefact1.getArtefactTags();
          for (ArtefactTag artefactTag: artefactTags){ 
         	  Assert.assertNotNull(artefactTag.getArtefactTagId());
          }
          
          Set <ArtefactTag> artefactTags1 = tag.getArtefactTags();
          for (ArtefactTag artefactTag: artefactTags1){ 
         	  Assert.assertNotNull(artefactTag.getArtefactTagId());
          }
          
          Assert.assertEquals(artefact1.getArtefactTags().size(),originalNumberOfArtefactTags + 1 );
          Assert.assertEquals(tag.getArtefactTags().size(), 1 );
          ArtefactTag artefactTag = myshowcaseService.getArtefactTag(artefact1,tag);
          Assert.assertNotNull(artefactTag.getArtefactTagId());
     }      

     
     /**
      * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#removeTagFromArtefact(tag)}.
      */
     public void testRemoveTagFromArtefact() {
       
    	  //Create 
    	  Tag tag = new Tag("New-Tag",owner1,defaultSearchCount);
    	  
    	  int originalNumberOfArtefactTags = artefact1.getArtefactTags().size();
    	  
    	  myshowcaseService.addTagToArtefact(artefact1, tag); 
    	  
          //Test create
          Assert.assertNotNull(tag.getArtefactTags());
          
          Set <ArtefactTag> artefactTags = artefact1.getArtefactTags();
          for (ArtefactTag artefactTag: artefactTags){ 
         	  Assert.assertNotNull(artefactTag.getArtefactTagId());
          }
          
          Set <ArtefactTag> artefactTags1 = tag.getArtefactTags();
          for (ArtefactTag artefactTag: artefactTags1){ 
         	  Assert.assertNotNull(artefactTag.getArtefactTagId());
          }
          
          Assert.assertEquals(artefact1.getArtefactTags().size(),originalNumberOfArtefactTags + 1 );
          Assert.assertEquals(tag.getArtefactTags().size(), 1 );
          
          ArtefactTag artefactTag = myshowcaseService.getArtefactTag(artefact1,tag);
          Assert.assertNotNull(artefactTag.getArtefactTagId());
          
          //Remove
          myshowcaseService.removeTagFromArtefact(artefact1, tag);
          Assert.assertEquals(artefact1.getArtefactTags().size(),originalNumberOfArtefactTags );
          Assert.assertEquals(tag.getArtefactTags().size(), 0 );
          Assert.assertNull(myshowcaseService.getArtefactTagById(artefactTag.getArtefactTagId()));
    
     }       
 
     
     /**
      * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#getSaveTag(tag)}.
      */
     public void testSaveTag() {
       
    	  //Create 
    	  Tag tag = new Tag("New-Tag",owner1,defaultSearchCount);
     	  myshowcaseService.saveTag(tag);
     	  
          //Test create
          Assert.assertNotNull(tag.getTagId());
          Assert.assertNotNull(tag.getCreatedDate());
          Assert.assertNotNull(tag.getCreatedBy());
          Assert.assertNull(tag.getUpdatedDate());
          Assert.assertNull(tag.getUpdatedBy());

          //Update
//          int newSearchCount = 5;
		  int newSearchCount = 5;
          tag.setSearchCount(newSearchCount);
          myshowcaseService.saveTag(tag);
          
          //Test update
          Assert.assertNotNull(tag.getUpdatedDate());
          Assert.assertNotNull(tag.getUpdatedBy());
          Assert.assertEquals(tag.getSearchCount(),newSearchCount);

     }      

     
     /**
      * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#incrementTagSearchCount(tag)}.
      */
//     public void testIncrementTagSearchCount() {
//       
//     	  Tag tag = myshowcaseService.getTag(tag4.getName());
//          Assert.assertNotNull(tag);
//          
//          int originalSearchCount = tag.getSearchCount();
//          
//          myshowcaseService.incrementTagSearchCount(tag);
//          Assert.assertEquals(tag.getSearchCount(), originalSearchCount + 1);
          
//     }          

     
    /**
     * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#getTag(name)}.
     */
    public void testGetTagsForOwner() {
        
         List <Tag> tags = myshowcaseService.getTags(owner1);
         Assert.assertNotNull(tags);
         Assert.assertEquals(tags.size(), numberOfOwnerTags);
     } 

     
    /**
     * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#getTagsForArtefact(artefact)}.
     */
    public void testGetTagsForArtefact() {
        
         List <Tag> tags = myshowcaseService.getTags(artefact1);
         Assert.assertNotNull(tags);
         Assert.assertEquals(tags.size(), numberOfArtefactTags);
      } 
 
     
    /**
     * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#getArtefactTag(id)}.
     */
    public void testGetArtefactTag() {
      	 
         ArtefactTag artefactTag = myshowcaseService.getArtefactTag(artefact1, tag1);
         Assert.assertNotNull(artefactTag.getArtefactTagId());
           
         artefactTag = myshowcaseService.getArtefactTag(artefact2,tag1); 
         Assert.assertNull(artefactTag);
    }           

     
    /**
     * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#addArtefactToShowcase(artefact,showcase)}.
     */
    public void testAddArtefactToShowcase() {
    	  
    	 int originalNumberOfArtefacts = showcase1.getArtefacts().size();
    	       	  
    	 myshowcaseService.addArtefactToShowcase(artefact3, showcase1); 
    	  
         //Test add
         Assert.assertEquals(showcase1.getArtefacts().size(),originalNumberOfArtefacts +1);
          
         for (Artefact artefact: showcase1.getArtefacts()){
             //Test original artefact was only deep copied
             Assert.assertNotSame(artefact3.getArtefactId(),artefact.getArtefactId());
             Assert.assertEquals(showcase1.getShowcaseId(),artefact.getShowcase().getShowcaseId());
         }    
    }      

    
    /**
     * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#removeArtefactFromShowcase(artefact)}.
     */
    public void testRemoveArtefactFromShowcase() {
    	  
    	 int originalNumberOfArtefacts = showcase1.getArtefacts().size();
    	       	  
    	 myshowcaseService.removeArtefactFromShowcase(artefact2); 
    	  
         //Test add
         Assert.assertEquals(showcase1.getArtefacts().size(),originalNumberOfArtefacts - 1);
          
         for (Artefact artefact: showcase1.getArtefacts()){
             //Test original artefact was only deep copied
             Assert.assertNotSame(artefact2.getArtefactId(),artefact.getArtefactId());
         } 
         
         Assert.assertNull(artefact2.getShowcase());
         
    }     

    /*

    public void testPublishShowcase() {
    	  
    	 myshowcaseService.publishShowcase(showcase1); 
    	  
         Assert.assertTrue(showcase1.getPublished());
          
         for (Artefact artefact: showcase1.getArtefacts()){
             
             Assert.assertEquals(showcase1.getShowcaseId(),artefact.getShowcase().getShowcaseId());
             Assert.assertTrue(artefact.getLocked());
         } 
    }         
*/
  
    /**
     * Test method for {@link org.sakaiproject.myshowcase.logic.impl.MyShowcaseLogicImpl#addReviewerToShowcase(showcase,reviewer)}.
     */
/*    
    public void testAddReviewerToShowcase() {
      
   	    int originalNumberOfShowcaseReviewers = showcase2.getShowcaseReviewers().size();
   	    ShowcaseReviewer newShowcaseReviewer = null;
   	    
   	    myshowcaseService.addReviewerToShowcase(reviewer1,showcase2); 
   	  
        Set <ShowcaseReviewer> showcaseReviewers = showcase2.getShowcaseReviewers();
        for (ShowcaseReviewer showcaseReviewer: showcaseReviewers){ 
            Assert.assertNotNull(showcaseReviewer.getShowcaseReviewerId());
            newShowcaseReviewer = showcaseReviewer;
        }
     	
        showcaseReviewers = reviewer1.getShowcaseReviewers();
        for (ShowcaseReviewer showcaseReviewer: showcaseReviewers){ 
          Assert.assertNotNull(showcaseReviewer.getShowcaseReviewerId());
        }
         
        Assert.assertEquals(showcase2.getShowcaseReviewers().size(),originalNumberOfShowcaseReviewers + 1 );
        Assert.assertNotNull(myshowcaseService.getShowcaseReviewerById(newShowcaseReviewer.getShowcaseReviewerId()));
        Assert.assertEquals(newShowcaseReviewer.getShowcase(),showcase2);
        Assert.assertEquals(newShowcaseReviewer.getReviewer(),reviewer1);
        
    }      
*/    
    
}
