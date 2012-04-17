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

package org.sakaiproject.myshowcase.model;


import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.sakaiproject.myshowcase.config.*;


public class MyShowcaseEmailClient {


	public void sendMessage(String fromAddress, String toAddress, String subject, String messageBody){
        
        String smtpHost = MyShowcaseConfigValues.getInstance().getEmailServer();
        
        String SMTP_MAIL = "smtp" ;
	    
        Properties props = new Properties();
	    
        props.setProperty("mail.smtp.host", smtpHost);

        // false equates to no authorization
        props.put("mail.smtp.auth", "false");
	   
        // maybe set to false in live version
        props.put("mail.debug", "true");

//	    Session session = Session.getDefaultInstance(props,null);
        
	    Session session = Session.getInstance(props,null);
	    
	    // create new mime message
		Message message = new MimeMessage(session);
		
		
		try {
			
			Transport bus = session.getTransport(SMTP_MAIL);
			
			bus.connect();
			
            message.setFrom(new InternetAddress(fromAddress));
            
            InternetAddress[] address = {new InternetAddress(toAddress)};
            
            message.setRecipients(Message.RecipientType.TO, address);
            
            message.setSubject(subject);
            
            message.setSentDate(new Date());
            
            // need to be html in order to reuse the generated URL to come back to view showcase
            message.setContent(messageBody, "text/html");
            
            message.setText(messageBody);
            
			Transport.send(message);
			
		    bus.close();
		}
		catch (Exception e) {
			
			System.out.println("Cannot send message " + e.toString());
		}
	}
	
	
	public static void main(String[] args){

		System.out.println("Email Client Start");
		
		String fromAddress = "myshowcase@myknowledgemap.com";
		
		String toAddress = "adrian.smy@myknowledgemap.com";
		
		String subject = "MyShowcase Email Client";
		
		String messageBody = "Dear User,\n\n";
		messageBody += "This email was generated by the new MyShowcase Email Client!\n\n";
		
		messageBody += "http://www.birdguides.com\n\n";
		
		messageBody += "Adrian.";
		
		MyShowcaseEmailClient msec = new MyShowcaseEmailClient();
		
		msec.sendMessage(fromAddress, toAddress, subject, messageBody);
		
		System.out.println("Email Client End");
	}
}
