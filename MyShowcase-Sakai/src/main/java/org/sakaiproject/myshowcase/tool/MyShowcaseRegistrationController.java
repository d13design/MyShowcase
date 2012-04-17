
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

package org.sakaiproject.myshowcase.tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.sakaiproject.myshowcase.config.MyShowcaseConfigValues;
import org.sakaiproject.myshowcase.logic.*;
import org.sakaiproject.myshowcase.model.*;


public class MyShowcaseRegistrationController extends AbstractController {

	final protected Log log = LogFactory.getLog(getClass());

	private String sourceId = null;
	private String user = null;
	private String location = null;
	private String userName = null;
	private String email = null;	
	private String emailConfirm = null;
	private String password = null;	
	private String passwordConfirm = null;
	private String forename = null;	
	private String surname = null;
	private String birthDate = null;
	private String origin = null;
	private Registration registration = new Registration();
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		readRequest(request);
		
		ModelAndView mav = new ModelAndView();
		mav = new ModelAndView("registration");
		Owner owner = null;
		
		Account account = new Account();
		AccountSource accountSource = new AccountSource();
		account.setAccountSource(accountSource);
		accountSource.setName(sourceId);
		accountSource.setLocation(location);
		account.setUserId(user);
		
		Validation validation = new Validation();
		
		if (origin.equals("registrationPage")){
		
			validation = myshowcaseService.validateRegistration(registration);
		
			// if a valid SSO request see if a match is found on a MyShowcase owner
			if (validation.isValid()){
			
				owner =  myshowcaseService.register(registration);
			
				if (owner != null){
			        
					// if applicable maintain their SSO account details (Registration\Login may be the result of a failed SSO attempt) 
					if (!account.getAccountSource().getName().equals("")){
						
						Account existingAccount = myshowcaseService.getAccount(owner, account.getAccountSource());
						
						if (existingAccount == null){
							
							Account newAccount = new Account();
							newAccount.setUserId(account.getUserId());
							newAccount.setAccountSource(account.getAccountSource());
							newAccount.setOwner(owner);
							
//							newAccount.setSource(account.getSource());
//							newAccount.setLocation(account.getLocation());

							myshowcaseService.saveAccount(newAccount);
						}
						else {
							
							existingAccount.setUserId(account.getUserId());
							myshowcaseService.saveAccount(existingAccount);
						}
					}
					
					mav = new ModelAndView("home");
	            
					// ensure the owner has at least a default showcase set up by obtaining current showcase
					Showcase showcase = myshowcaseService.obtainCurrentShowcase(owner);
			        
					Configuration config = new Configuration();
			        
					config.setUploadDir(MyShowcaseConfigValues.getInstance().getUploadDir());
			
			        mav.addObject("config",config);	          
					mav.addObject("owner",owner); 
					mav.addObject("showcase",showcase);
	        	
				}
			}
		}
		
		mav.addObject("account",account); 
		mav.addObject("validation",validation);
		mav.addObject("registration",registration);
		
		return mav;
	}
	
    private IMyShowcaseService myshowcaseService;
    public void setMyshowcaseService(IMyShowcaseService service) {
        this.myshowcaseService = service;
    }
    
    private void readRequest(HttpServletRequest request) {

		sourceId = request.getParameter("sso_source");
		user = request.getParameter("sso_user");
		location = request.getParameter("sso_location");
		email = request.getParameter("email");
		emailConfirm = request.getParameter("emailConfirm"); 
		forename = request.getParameter("forename");
		surname = request.getParameter("surname");
		password = request.getParameter("password");
		passwordConfirm = request.getParameter("passwordConfirm");
		userName = request.getParameter("userName");
		origin = request.getParameter("origin");		
		birthDate = request.getParameter("birthDate");
		
		if (sourceId == null){
			sourceId = "";
		};
		
		if (location == null){
			location = "";
		};
		
		if (user == null){
			user = "";
		};	
		
		if (email == null){
			email = "";
		};
		
		if (emailConfirm == null){
			emailConfirm = "";
		};
		
		if (password == null){
			password = "";
		};
		
		if (passwordConfirm == null){
			passwordConfirm = "";
		};		
		
		if (userName == null){
			userName = "";
		};
		
		if (surname == null){
			surname = "";
		};
		
		if (forename == null){
			forename = "";
		};			
		
		if (origin == null){
			origin = "";
		};	
		
		if (birthDate == null){
			birthDate = "";
		};	
		
		registration.setEmail(email);
		registration.setSurname(surname);
		registration.setEmailConfirm(emailConfirm);
		registration.setUserName(userName);	
		registration.setForename(forename);
		registration.setPasswordConfirm(passwordConfirm);
		registration.setPassword(password);
		registration.setBirthDate(birthDate);
		
    }

}

