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
 
public class DateValidator{
 
	private Pattern pattern;
	private Matcher matcher;
 
/*  
  
    (			  # start of group #1
	0?[1-9]		  #  01-09 or 1-9
    |             #  ..or
    [12][0-9]	  #  10-19 or 20-29
    |			  #  ..or
    3[01]		  #  30, 31
    ) 			  # end of group #1
    /			  #  follow by a "/"
    (			  #    start of group #2
    0?[1-9]		  #	01-09 or 1-9
    |			  #	..or
    1[012]		  #	10,11,12
    )			  #    end of group #2
    /			  #	follow by a "/"
   (			  #	  start of group #3
   (19|20)\\d\\d  #	    19[0-9][0-9] or 20[0-9][0-9]
   )		      #	  end of group #3 
 
 */ 
  
	private static final String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
 
	public DateValidator(){
		pattern = Pattern.compile(DATE_PATTERN);
	}
 
  
	/**
	  * Validate date format with regular expression
	  * @param date date address for validation
	  * @return true valid date format, false invalid date format
	  */
	public boolean validate(final String date){
 
		matcher = pattern.matcher(date);
 
		if(matcher.matches()){
 
			matcher.reset();
 
			if(matcher.find()){
 
				String day = matcher.group(1);
				
				String month = matcher.group(2);
				
				int year = Integer.parseInt(matcher.group(3));
 
				if (day.equals("31") && 
					(month.equals("4") || month .equals("6") || month.equals("9") ||
					 month.equals("11") || month.equals("04") || month .equals("06") ||
                     month.equals("09"))) {
			
						return false; // only 1,3,5,7,8,10,12 has 31 days
						
				} else if (month.equals("2") || month.equals("02")){
					
					//leap year
					if(year % 4==0){
						
						if(day.equals("30") || day.equals("31")){
							
							return false;
							
						} else{
							
							return true;
						}
					
					} else{
						
						if(day.equals("29")||day.equals("30")||day.equals("31")){
						
							return false;
						}else {
						
							return true;
						}
					}
				} else{
					
					return true;				 
				}
			}else{
				
				return false;
			}		  
		} else{
    	 
			return false;
		}			    
	}
}