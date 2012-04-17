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
 
public class EmailValidator{
 
	  private Pattern pattern;
	  private Matcher matcher;

/*
	  ^			        #start of the line
	  [_A-Za-z0-9-]+	#  must start with string in the bracket [ ], must contains one or more (+)
      (			        #  start of group #1
      \\.[_A-Za-z0-9-]+	#     follow by a dot "." and string in the bracket [ ], must contains one or more (+)
      )*			    #  end of group #1, this group is optional (*)
      @			        #     must contains a "@" symbol
      [A-Za-z0-9]+      #        follow by string in the bracket [ ], must contains one or more (+)
      (			        #	   start of group #2 - first level TLD checking
      \\.[A-Za-z0-9]+   #	     follow by a dot "." and string in the bracket [ ], must contains one or more (+)
      )*		        #	   end of group #2, this group is optional (*)
      (			        #	   start of group #3 - second level TLD checking
      \\.[A-Za-z]{2,}   #	     follow by a dot "." and string in the bracket [ ], with minimum length of 2
      )			        #	   end of group #3
	  $			        #end of the line	  
*/
	  
	  private static final String EMAIL_PATTERN = 
                   "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
 
	  public EmailValidator(){
		  pattern = Pattern.compile(EMAIL_PATTERN);
	  }
 
	  /**
	   * Validate hex with regular expression
	   * @param hex hex for validation
	   * @return true valid hex, false invalid hex
	   */
	  public boolean validate(final String hex){
 
		  matcher = pattern.matcher(hex);
		  return matcher.matches();
 
	  }
}
