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

