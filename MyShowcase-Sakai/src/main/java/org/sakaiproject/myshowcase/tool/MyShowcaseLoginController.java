
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


public class MyShowcaseLoginController extends AbstractController {

	final protected Log log = LogFactory.getLog(getClass());
	
	private String userName = "";
	private String password = "";
	private String origin = "";
	
	private String ssoSource = "";
	private String ssoUser = "";
	private String ssoLocation = "";
	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		readRequest(request);
		
		// Build login instance
		Login login = new Login();
		login.setUserName(userName);
		login.setPassword(password);
		
		Account account = new Account();
		AccountSource accountSource = new AccountSource();
		account.setAccountSource(accountSource);
		accountSource.setName(ssoSource);
		accountSource.setLocation(ssoLocation);
		account.setUserId(ssoUser);	
		
		// Add login to model
		ModelAndView mav = new ModelAndView();
		mav = new ModelAndView("login");
		mav.addObject("login",login);		
		mav.addObject("account",account);
		
		boolean validateRequired = true;
		
		Validation validation = new Validation();
		
		// not called from a page
		if ((origin == null) || (origin.equals(""))){
			validateRequired = false;
		}

		if (validateRequired){
			
			validation = myshowcaseService.validateLogin(login);
			
			if (validation.isValid()){
				
				Owner owner = myshowcaseService.obtainOwner(userName, password);
				
				if (owner != null){
			
					// load home page
					mav = new ModelAndView("home");

					// ensure the owner has at least a default showcase set up by obtaining current showcase
					Showcase showcase = myshowcaseService.obtainCurrentShowcase(owner);
					
					mav.addObject("showcase",showcase);
					mav.addObject("owner",owner);
			        
					Configuration config = new Configuration();

			        config.setUploadDir(MyShowcaseConfigValues.getInstance().getUploadDir());

			        System.out.println("MyShowcaseLoginController() uploadDir = " + MyShowcaseConfigValues.getInstance().getUploadDir() );
			        
			        mav.addObject("config",config);
					
		        
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

				}
			}
		}
		
		mav.addObject("validation",validation);
		
		return mav;
	}

	
    private IMyShowcaseService myshowcaseService;
    public void setMyshowcaseService(IMyShowcaseService service) {
        this.myshowcaseService = service;
    }
    
    private void readRequest(HttpServletRequest request) {
    	
		userName = request.getParameter("username");
		password = request.getParameter("password");
		origin = request.getParameter("origin");
		
		ssoSource = request.getParameter("sso_source");
		ssoUser = request.getParameter("sso_user");
		ssoLocation = request.getParameter("sso_location");
		
		if (userName == null){
			userName = "";
		};
		
		if (password == null){
			password = "";
		};
		
		if (origin == null){
			origin = "";
		};	
		
		if (ssoSource == null){
			ssoSource = "";
		};
		
		if (ssoUser == null){
			ssoUser = "";
		};
		
		if (ssoLocation == null){
			ssoLocation = "";
		};

		
    }

}
