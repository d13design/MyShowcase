package org.sakaiproject.myshowcase.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class UserNameValidator{
 
	  private Pattern pattern;
	  private Matcher matcher;
 
/*	  
	  ^              # Start of the line
	  [a-z0-9_-]	 # Match characters and symbols in the list, a-z, 0-9, underscore, hyphen
	  {5,15}         # Length at least 5 characters and maximum length of 15 
	  $  	  
*/	  
	  private static final String USERNAME_PATTERN = "^[a-z0-9_-]{5,15}$";
 
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