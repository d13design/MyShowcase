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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.myshowcase.model.Account;
import org.sakaiproject.myshowcase.model.Login;
import org.sakaiproject.myshowcase.model.Registration;

public class MyShowcaseValidationService implements IMyShowcaseValidationService {

	
	static Log log = LogFactory.getLog(MyShowcaseValidationService.class);
 
	
	/**
     * Place any code that should run when this class is initialized by spring here
     */
    public void init() {
       log.debug(getClassName() + "init()");
    }	

    
    private String getClassName() {
    	return this.getClass().getName();
    } 
 
    
    public Validation validateLogin(Login login) {
    	
    	Validation validation = new Validation();
 		
    	
		// TEST: if a nothing NO details have been captured
		if ((login.getUserName() == null) || (login.getUserName().equals(""))){

			validation.addMessage("No login supplied.");
			validation.setValid(false);
		}
		
		// TEST: if a nothing NO details have been captured
		if ((login.getPassword() == null) || (login.getPassword().equals(""))){
			
			validation.addMessage("No password supplied.");
			validation.setValid(false);
		}   	
 		
    	return validation;
    } 

 
    
    public Validation validateSSORequest(Account account) {
    	
    	Validation validation = new Validation();
 		
    	System.out.println("validation account = " + account);
    	System.out.println("validation accountSource = " + account.getAccountSource());
    	
		// TEST: if a source identifier has been included in request
		if ((account.getAccountSource().getName() == null) || (account.getAccountSource().getName().equals(""))){

			validation.addMessage("No source supplied on SSO request.");
			validation.setValid(false);
		}
		
		// TEST: if user identifier has been included in request
		if ((account.getUserId() == null) || (account.getUserId().equals(""))){
			
			validation.addMessage("No user supplied on SSO request.");
			validation.setValid(false);
		}   	
    	
    	return validation;
    }    

 
    
    public Validation validateRegistration(Registration registration) {
    	
    	Validation validation = new Validation();
 		
    	// Passed basic validation (all fields present..etc)
    	if (validateRegistrationBasic(registration, validation).isValid()){
    		
    		// TEST: email & email confirm mismatch
    		if ((!registration.getEmail().equals(registration.getEmailConfirm()))){

    			validation.addMessage("Email and email confirmation mismatch. They must contain the same value.");
    			validation.setValid(false);
    		}
    		
    		// TEST: password & password confirm mismatch
    		if ((!registration.getPassword().equals(registration.getPasswordConfirm()))){

    			validation.addMessage("Password and password confirmation mismatch. They must contain the same value.");
    			validation.setValid(false);
    		}   		
     		
    	}
		
    	return validation;
    } 
    
    
    private Validation validateRegistrationBasic(Registration registration, Validation validation ) {
    	
 		
		// TEST: validate UserName
		if ((registration.getUserName() == null) || (registration.getUserName().equals(""))){

			validation.addMessage("Missing userName.");
			validation.setValid(false);
			
		} else {

			UserNameValidator validator = new UserNameValidator();
			
			if (!validator.validate(registration.getUserName())){
				
				validation.addMessage("Invalid username format. The name can only contain characters and symbols in the list: "
						              + "a-z, A-Z, 0-9, underscore and hyphen. It must be at least 5 characters long and have a maximum length of 15 characters.");
				validation.setValid(false);			
			}
			
		}
		
		// TEST: validate password
		if ((registration.getPassword() == null) || (registration.getPassword().equals(""))){

			validation.addMessage("Missing password.");
			validation.setValid(false);
			
		} else {

			PasswordValidator validator = new PasswordValidator();
			
			if (!validator.validate(registration.getPassword())){
				
				validation.addMessage("Invalid password format. It must contain at least one lowercase character and " 
						              + " at least one uppercase character. It must be at least 6 characters long and have a maximum length of 20 characters.");

				validation.setValid(false);			
			}
			
		}
		
		// TEST: validate password
		if ((registration.getPasswordConfirm() == null) || (registration.getPasswordConfirm().equals(""))){

			validation.addMessage("Missing password confirmation.");
			validation.setValid(false);
			
		}
		
		// TEST: validate email
		if ((registration.getEmail() == null) || (registration.getEmail().equals(""))){

			validation.addMessage("Missing email address.");
			validation.setValid(false);
			
		} else {

			EmailValidator validator = new EmailValidator();
			
			if (!validator.validate(registration.getEmail())){
				
				validation.addMessage("Invalid email format.");
				validation.setValid(false);			
			}
			
		}
		
		// TEST: date of birth
		if ((registration.getBirthDate() == null) || (registration.getBirthDate().equals(""))){

			validation.addMessage("Missing Date Of Birth.");
			validation.setValid(false);
			
		} else {

			DateValidator validator = new DateValidator();
			
			if (!validator.validate(registration.getBirthDate())){
				
				validation.addMessage("Invalid Date Of Birth format.");
				validation.setValid(false);			
			}
			
		}
		
		// TEST: validate email confirm
		if ((registration.getEmailConfirm() == null) || (registration.getEmailConfirm().equals(""))){

			validation.addMessage("Missing email address confirmation.");
			validation.setValid(false);
			
		} 
		
		// TEST: validate forename
		if ((registration.getForename() == null) || (registration.getForename().equals(""))){

			validation.addMessage("Missing forename.");
			validation.setValid(false);
			
		}
		
		// TEST: validate surname
		if ((registration.getSurname() == null) || (registration.getSurname().equals(""))){

			validation.addMessage("Missing surname.");
			validation.setValid(false);
			
		} 
		
    	return validation;
    } 
       
    
}
