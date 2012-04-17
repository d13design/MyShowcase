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

import org.sakaiproject.myshowcase.model.Account;
import org.sakaiproject.myshowcase.model.Login;
import org.sakaiproject.myshowcase.model.Registration;

public interface IMyShowcaseValidationService {

    /**
     * Validate a login
     * @param Login details
     * @return validation object 
     */	
	public Validation validateLogin(Login login);

	
    /**
     * Validate an single sign on request
     * @param account details
     * @return validation object 
     */		
	public Validation validateSSORequest(Account account);

	
    /**
     * Validate registration object
     * @param registration object
     * @return validation object 
     */		
	public Validation validateRegistration(Registration registration);	
}
