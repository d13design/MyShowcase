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
   (?=.*[@#$%])	   #   must contains one special symbols in the list "@#$%"
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
