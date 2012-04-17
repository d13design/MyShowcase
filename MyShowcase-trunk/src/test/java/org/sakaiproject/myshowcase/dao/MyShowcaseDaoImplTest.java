/******************************************************************************
 * MyShowcaseDaoImplTest.java - created by Sakai App Builder -AZ
 * 
 * Copyright (c) 2008 Sakai Project/Sakai Foundation
 * Licensed under the Educational Community License version 1.0
 * 
 * A copy of the Educational Community License has been included in this 
 * distribution and is available at: http://www.opensource.org/licenses/ecl1.php
 * 
 *****************************************************************************/

package org.sakaiproject.myshowcase.dao;

import java.util.Date;

import junit.framework.Assert;

import org.sakaiproject.myshowcase.dao.MyShowcaseDao;
//import org.sakaiproject.myshowcase.logic.FakeDataPreload;
import org.springframework.test.AbstractTransactionalSpringContextTests;


import org.sakaiproject.myshowcase.model.ArtefactType;


/**
 * Testing for the specialized DAO methods (do not test the Generic Dao methods)
 * @author Sakai App Builder -AZ
 */
public class MyShowcaseDaoImplTest extends AbstractTransactionalSpringContextTests {

   protected MyShowcaseDao dao;

   private ArtefactType artefactType;
   private final static String NAME = "Twitter";
   private final static String DESCRIPTION = "Test artefact type - Twitter";
   private final static String USER = "test-user";
   
    
   protected String[] getConfigLocations() {
      // point to the needed spring config files, must be on the classpath
      // (add component/src/webapp/WEB-INF to the build path in Eclipse),
      // they also need to be referenced in the project.xml file
      return new String[] {"hibernate-test.xml", "spring-hibernate.xml"};
   }

   // run this before each test starts
   protected void onSetUpBeforeTransaction() throws Exception {
      // create test objects
      artefactType = new ArtefactType(NAME, DESCRIPTION, new Date(),USER);
   }

   // run this before each test starts and as part of the transaction
   protected void onSetUpInTransaction() {
      // load the spring created dao class bean from the Spring Application Context
      dao = (MyShowcaseDao) applicationContext.
         getBean("myshowcase.dao.MyShowcaseDao");
      if (dao == null) {
         throw new NullPointerException("DAO could not be retrieved from spring context");
      }


      // check the preloaded data
//      Assert.assertTrue("Error preloading data", dao.countAll(SampleItem.class) > 0);

      // preload data if desired
      dao.save(artefactType);
   }
      
   public void testArtefactTypeSave() {
	      ArtefactType artefactType1 = new ArtefactType(NAME, DESCRIPTION, new Date(),USER);
	      int numberOfArtefactTypes = dao.countAll(ArtefactType.class);
	      dao.save(artefactType1);
	      Long artefactTypeId = artefactType1.getArtefactTypeId();
	      Assert.assertNotNull(artefactTypeId);
	      Assert.assertEquals(numberOfArtefactTypes + 1, dao.countAll(ArtefactType.class));
   }
   
   public void testArtefactTypeUpdate() {
	      int numberOfArtefactTypesBefore = dao.countAll(ArtefactType.class);
	      String updatedName = "Facebook";
	      artefactType.setName(updatedName);
	      dao.save(artefactType);
	      int numberOfArtefactTypesAfter = dao.countAll(ArtefactType.class);
	      Assert.assertEquals(numberOfArtefactTypesBefore,numberOfArtefactTypesAfter);
	      artefactType = (ArtefactType) dao.findById(ArtefactType.class, artefactType.getArtefactTypeId());
	      Assert.assertEquals(artefactType.getName(),updatedName);
   } 
    
   public void testArtefactTypeFindById() {
	      Long id = artefactType.getArtefactTypeId();
	      Assert.assertNotNull(id);
	      ArtefactType artefactType1 = (ArtefactType) dao.findById(ArtefactType.class, id);
	      Assert.assertNotNull(artefactType1);
	      Assert.assertEquals(artefactType, artefactType1);
   }
       
   public void testArtefactTypeDelete() {
	 	   int numberOfArtefactTypes = dao.countAll(ArtefactType.class);
	 	   dao.delete(artefactType);
	       Assert.assertEquals(dao.countAll(ArtefactType.class),numberOfArtefactTypes - 1 );
    }
    
   /**
    * Add anything that supports the unit tests below here
    */
   
}
