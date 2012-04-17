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

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class UserNameValidator{
 
	  private Pattern pattern;
	  private Matcher matcher;
 
/*	  
	  ^              # Start of the line
	  [a-zA-Z0-9_-]	 # Match characters and symbols in the list, a-z, A-Z,0-9, underscore, hyphen
	  {5,15}         # Length at least 5 characters and maximum length of 15 
	  $  	  
*/	  
	  private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_-]{5,15}$";
 
	  public UserNameValidator(){
		  pattern = Pattern.compile(USERNAME_PATTERN);
	  }
 
	  /**
	   * Validate username with regular expression
	   * @param username username for validation
	   * @return true valid username, false invalid username
	   */
	  public boolean validate(final String username){
 
		  matcher = pattern.matcher(username);
		  return matcher.matches();
 
	  }
}