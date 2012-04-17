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
 
public class PasswordValidator{
 
	  private Pattern pattern;
	  private Matcher matcher;
 /*
   (               # Start of group
   (?=.*\d)		   #   must contains one digit from 0-9
   (?=.*[a-z])	   #   must contains one lowercase characters
   (?=.*[A-Z])	   #   must contains one uppercase characters
   .		       #   match anything with previous condition checking
   {6,20}	       #   length at least 6 characters and maximum of 20	
   )			   # End of group	  
*/	  
	  private static final String PASSWORD_PATTERN = 
              "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
//            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
      			
	  public PasswordValidator(){
		  pattern = Pattern.compile(PASSWORD_PATTERN);
	  }
 
	  /**
	   * Validate password with regular expression
	   * @param password password for validation
	   * @return true valid password, false invalid password
	   */
	  public boolean validate(final String password){
 
		  matcher = pattern.matcher(password);
		  return matcher.matches();
 
	  }
}
