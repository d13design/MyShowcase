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

package org.sakaiproject.myshowcase.model;


import java.util.HashSet;
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;


public class OwnerTest {
	
	final static String valueUsername = "userName";
	
	final static String valueEmail = "email@address.com";
	
	final static String valueForename = "forename";
	
	final static String valueSurname = "surname";	
	
	final static String valuePassword = "password";
	
	final static String valueUpdatedBy = "updatedBy";
	
	final static String valueCreatedBy = "createdBy";
	
	final static String valueUserId = "userId";
	
	final static Long valueOwnerId = new Long("1");	
	
	final static String newValueUsername = "userNameNew";
	
	final static String newValueEmail = "email@address.comNew";
	
	final static String newValueForename = "forenameNew";
	
	final static String newValueSurname = "surnameNew";	
	
	final static String newValuePassword = "passwordNew";
	
	final static String newValueUpdatedBy = "updatedByNew";
	
	final static String newValueCreatedBy = "createdByNew";
	
	final static String newValueUserId = "userIdNew";
	
	final static Long newValueOwnerId = new Long("2");
	
	Set<Showcase> showcases = new HashSet<Showcase>();
	
	Set<Reviewer> reviewers = new HashSet<Reviewer>();
	
	Set<Artefact> artefacts = new HashSet<Artefact>();
	
	Set<Account> accounts = new HashSet<Account>();
	
	Set<Tag> tags = new HashSet<Tag>();

	Set<ArtefactMapping> artefactMappings = new HashSet<ArtefactMapping>();	
	
	private Owner owner;

	
	@Before
	public void instantiate() {
		
		Tag tag = new Tag();

		Artefact artefact = new Artefact();
		
		Account account = new Account();
		
		Showcase showcase = new Showcase();
		
		Reviewer reviewer = new Reviewer();
		
		ArtefactMapping artefactMapping = new ArtefactMapping();
		
		artefactMappings.add(artefactMapping);
		
		showcases.add(showcase);
		
		reviewers.add(reviewer);
		
		artefacts.add(artefact);
		
		accounts.add(account);
		
		tags.add(tag);
		
		owner = new Owner();
		
		owner.setUserName(valueUsername);
		
		owner.setEmail(valueEmail);
		
		owner.setForename(valueForename);
		
		owner.setSurname(valueSurname);
		
		owner.setUserId(valueUserId);
		
		owner.setPassword(valuePassword);
		
		owner.setOwnerId(valueOwnerId);
		
		owner.setCreatedBy(valueCreatedBy);
		
		owner.setUpdatedBy(valueUpdatedBy);
		
		owner.setAccounts(accounts);
		
		owner.setArtefacts(artefacts);
		
		owner.setMappings(artefactMappings);
		
		owner.setReviewers(reviewers);
		
		owner.setShowcases(showcases);
		
		owner.setTags(tags);
	}

	
	@Test
	public void getUserNameTest() {
		assertEquals("Model: Test owner.username correctly read.",valueUsername ,owner.getUserName());
	}

	
	@Test
	public void getEmailTest() {
		assertEquals("Model: Test owner.email correctly read.", valueEmail, owner.getEmail());
	}

	
	@Test
	public void getForenameTest() {
		assertEquals("Model: Test that owner.forename correctly read.", valueForename,owner.getForename());
	}

	
	@Test
	public void getSurnameTest() {
		assertEquals("Model:Test that owner.surname correctly read.", valueSurname,owner.getSurname());
	}

	
	@Test
	public void getUserIdTest() {
		assertEquals("Model: Test that owner.userid is correctly read.", valueUserId,owner.getUserId());
	}	

	
	@Test
	public void getUpdatedByTest() {
		assertEquals("Model: Test that owner.updatedBy is correctly read.", valueUpdatedBy,owner.getUpdatedBy());
	}	

	
	@Test
	public void getCreatedByTest() {
		assertEquals("Model:Test that owner.createdBy is correctly read.", valueCreatedBy,owner.getCreatedBy());
	}	

	
	@Test
	public void getPasswordTest() {
		assertEquals("Model:Test that owner.password is correctly read.", valuePassword,owner.getPassword());
	}	

	
	@Test
	public void setUserNameTest() {
		
		owner.setUserName(newValueUsername);
		
		assertEquals("Model: Test owner.username correctly set.",newValueUsername ,owner.getUserName());
	}

	
	@Test
	public void setEmailTest() {
		
		owner.setEmail(newValueEmail);
		
		assertEquals("Model: Test owner.email correctly set.", newValueEmail, owner.getEmail());
	}

	
	@Test
	public void setForenameTest() {
		
		owner.setForename(newValueForename);
		
		assertEquals("Model: Test that owner.forename correctly set.", newValueForename,owner.getForename());
	}

	
	@Test
	public void setSurnameTest() {
		
		owner.setSurname(newValueSurname);
		
		assertEquals("Model:Test that owner.surname correctly set.", newValueSurname,owner.getSurname());
	}

	
	@Test
	public void setUserIdTest() {
		
		owner.setUserId(newValueUserId);
		
		assertEquals("Model: Test that owner.userid is correctly set.", newValueUserId,owner.getUserId());
	}	

	
	@Test
	public void setUpdatedByTest() {
		
		owner.setUpdatedBy(newValueUpdatedBy);
		
		assertEquals("Model: Test that owner.updatedBy is correctly set.", newValueUpdatedBy,owner.getUpdatedBy());
	}	

	
	@Test
	public void setCreatedByTest() {
		
		owner.setCreatedBy(newValueCreatedBy);
		
		assertEquals("Model:Test that owner.createdBy is correctly set.", newValueCreatedBy,owner.getCreatedBy());
	}	

	
	@Test
	public void setPasswordTest() {
		
		owner.setPassword(newValuePassword);
		
		assertEquals("Model:Test that owner.password is correctly set.", newValuePassword,owner.getPassword());
	}		

	
	@Test
	public void getArtefactsTest() {
	
		assertSame("Model:Test that owner.artefacts are correctly read.", artefacts ,owner.getArtefacts());
	}		

	
	@Test
	public void getShowcasesTest() {
	
		assertSame("Model:Test that owner.showcases are correctly read.", showcases ,owner.getShowcases());
	}	

	
	@Test
	public void getTagsTest() {
	
		assertSame("Model:Test that owner.tags are correctly read.", tags ,owner.getTags());
	}		

	
	@Test
	public void getReviewersTest() {
	
		assertSame("Model:Test that owner.reviewers are correctly read.", reviewers ,owner.getReviewers());
	}		

	
	@Test
	public void getAccountsTest() {
	
		assertSame("Model:Test that owner.accounts are correctly read.", accounts ,owner.getAccounts());
	}		

	
	@Test
	public void getArtefactMappingsTest() {
	
		assertSame("Model:Test that owner.mappings are correctly read.", artefactMappings ,owner.getMappings());
	}		
}
