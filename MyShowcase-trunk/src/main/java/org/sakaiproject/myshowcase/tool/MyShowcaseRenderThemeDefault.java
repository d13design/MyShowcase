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

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.myshowcase.config.ConfigUtils;
import org.sakaiproject.myshowcase.config.MyShowcaseConfig;
import org.sakaiproject.myshowcase.config.MyShowcaseConfigResource;
import org.sakaiproject.myshowcase.config.MyShowcaseConfigValues;
import org.sakaiproject.myshowcase.logic.IMyShowcaseService;
import org.sakaiproject.myshowcase.model.Artefact;
import org.sakaiproject.myshowcase.model.Owner;
import org.sakaiproject.myshowcase.model.Showcase;


public class MyShowcaseRenderThemeDefault implements IMyShowcaseRenderTheme {

	public static IMyShowcaseService myshowcaseService;
	
	final protected Log log = LogFactory.getLog(getClass());

	
	public MyShowcaseRenderThemeDefault(IMyShowcaseService myshowcaseService) {
		setMyshowcaseService(myshowcaseService);
		
    }

    public void setMyshowcaseService(IMyShowcaseService service) {
         this.myshowcaseService = service;
    }	

    
    private String renderDefaultDate(Date date) {

    	StringBuffer textToInsert = new StringBuffer();
    	
		textToInsert.append("<div class=\"date\">");
		
		textToInsert.append("<p><strong>" + myshowcaseService.ordinalOfInteger(date.getDate()) + "</strong> " + myshowcaseService.shortMonthName(date.getMonth()) + "</p>") ; 
		
		textToInsert.append("</div>");
		
    	return textToInsert.toString();
    }

    
    private String renderDefaultArtefactTwitter(Artefact artefact) {
    	
    	StringBuffer textToInsert = new StringBuffer() ;

    	textToInsert.append("<div class=\"artefact twitter\">");
    	
		textToInsert.append(renderDefaultDate(artefact.getCreatedDate())); 
		
		textToInsert.append("<div class=\"content\">"); 
		
		textToInsert.append("<h4>&ldquo;" + artefact.getArtefactDetail().getDetail() + "&rdquo;</h4>"); 
		
		textToInsert.append("<p class=\"context\">" + artefact.getDescription() + "</p>"); 
		
		textToInsert.append("<p class=\"meta\">A Twitter item added on " + myshowcaseService.formattedLongDate(artefact.getCreatedDate()) + "</p>") ; 
		
		textToInsert.append("</div>"); 
		
		textToInsert.append("</div>");

    	return textToInsert.toString() ;
    }
 
    
    private String renderDefaultArtefactFlickr(Artefact artefact) {

    	StringBuffer textToInsert = new StringBuffer() ;
    	
		textToInsert.append("<div class=\"artefact flickr\">");
		
		textToInsert.append(renderDefaultDate(artefact.getCreatedDate())); 
		
		textToInsert.append("<div class=\"content\">"); 
		
		textToInsert.append("<h4>" + artefact.getName() + "</h4>"); 
		
		textToInsert.append("<a href=\"" + artefact.getArtefactDetail().getUrl() + " target=\"_blank\"><img src=\"" + artefact.getArtefactDetail().getUrl() + "\" alt=\"" + artefact.getArtefactDetail().getDetail() + "\" /></a>") ; 
		
		textToInsert.append("<p class=\"context\">" + artefact.getDescription() + "</p>"); 
		
//		textToInsert.append("<p class=\"meta\">A Flickr item added on " + myshowcaseService.formattedLongDate(artefact.getCreatedDate()) + "</p>") ; 
		
		textToInsert.append("<p class=\"meta\">A  <a href=\"http://www.flickr.com/\" target=\"_blank\">Flickr</a> item added on " + myshowcaseService.formattedLongDate(artefact.getCreatedDate()) + "</p>") ; 
		
		textToInsert.append("</div>"); 
		
		textToInsert.append("</div>");

    	return textToInsert.toString() ;
    }

    
    private String renderDefaultArtefactFile(Artefact artefact,Owner owner) {
    	
    	String fileLocation = MyShowcaseConfigValues.getInstance().getUploadDir() + owner.getOwnerId() + "/" + artefact.getArtefactDetail().getFileName();
    	
    	System.out.println("Fileslocation = " + fileLocation);  	
    	
 //   	ownerId + '/' + artefact.artefactDetail.fileName;
    	
    	StringBuffer textToInsert = new StringBuffer() ;
    	
		textToInsert.append("<div class=\"artefact file\">");
		
		textToInsert.append(renderDefaultDate(artefact.getCreatedDate())); 
		
		textToInsert.append("<div class=\"content\">");
		
		textToInsert.append("<h4>" + artefact.getName() + "</h4>"); 
		
		textToInsert.append("<p class=\"context\">" + artefact.getDescription() + "</p>");
		
		textToInsert.append("<p class=\"meta\">A file based item added on " + myshowcaseService.formattedLongDate(artefact.getCreatedDate()) + " - <a href=\"" + fileLocation + "\">click here to download</a></p>") ; 
		
		textToInsert.append("</div>");
		
		textToInsert.append("</div>"); 

    	return textToInsert.toString() ;
    }

    
    private String renderDefaultArtefactWeblink(Artefact artefact) {

    	StringBuffer textToInsert = new StringBuffer() ;
    	
    	textToInsert.append("<div class=\"artefact link\">");
    	
		textToInsert.append(renderDefaultDate(artefact.getCreatedDate())); 
		
		textToInsert.append("<div class=\"content\">");
		
		textToInsert.append("<h4>" + artefact.getName() + "</h4>"); 
		
		textToInsert.append("<p class=\"link\"><a href=\"" + artefact.getArtefactDetail().getUrl() + "\">" + artefact.getArtefactDetail().getDetail() + "</a></p>") ;
		
		textToInsert.append("<p class=\"context\">" + artefact.getDescription() + "</p>"); 
		
		textToInsert.append("<p class=\"meta\">A link item added on ");
		
		textToInsert.append(myshowcaseService.formattedLongDate(artefact.getCreatedDate()));
		
		textToInsert.append("</p>");
		
		textToInsert.append("</div>");
		
		textToInsert.append("</div>"); 
		
		return textToInsert.toString();
    	
    }

    
    private String renderDefaultArtefactRss(Artefact artefact) {

    	StringBuffer textToInsert = new StringBuffer() ;

    	textToInsert.append("<div class=\"artefact rss\">");
		
    	textToInsert.append(renderDefaultDate(artefact.getCreatedDate())); 
		
    	textToInsert.append("<div class=\"content\">"); 
		
    	textToInsert.append("<h4>" + artefact.getName() + "</h4>"); 
		
    	textToInsert.append("<blockquote>"); 
		
    	textToInsert.append(artefact.getArtefactDetail().getDetail());
		
    	textToInsert.append("<p class=\"link\"><a href=\"" + artefact.getArtefactDetail().getUrl() + "\">" + artefact.getArtefactDetail().getDetail() + "</a></p>") ;
		
    	textToInsert.append("</blockquote>"); 
		
    	textToInsert.append("<p class=\"context\">" + artefact.getDescription() + "</p>"); 
		
    	textToInsert.append("<p class=\"meta\">An RSS item added on " + myshowcaseService.formattedLongDate(artefact.getCreatedDate()) + "</p>") ; 
		
    	textToInsert.append("</div>");
		
    	textToInsert.append("</div>"); 

		return textToInsert.toString() ;
    	
    }
    
    private String renderDefaultArtefactPebblePad(Artefact artefact) {

    	StringBuffer textToInsert = new StringBuffer();

    	textToInsert.append("<div class=\"artefact link\">"); 
		
    	textToInsert.append(renderDefaultDate(artefact.getCreatedDate())); 
		
    	textToInsert.append("<div class=\"content\">"); 
		
    	textToInsert.append("<h4>" + artefact.getName() + "</h4>"); 
		
    	textToInsert.append("<p class=\"link\"><a href=\"" + artefact.getArtefactDetail().getUrl() + "\">" + artefact.getArtefactDetail().getDetail() + "</a></p>") ;
		
    	textToInsert.append("<p class=\"context\">" + artefact.getDescription() + "</p>"); 
		
    	textToInsert.append("<p class=\"meta\">A Pebblead item added on ");
		
    	textToInsert.append(myshowcaseService.formattedLongDate(artefact.getCreatedDate()));
		
    	textToInsert.append("</p>");
		
    	textToInsert.append("</div>");
		
    	textToInsert.append("</div>"); 

		return textToInsert.toString();
    }
 
    
    private String renderDefaultArtefactSakai(Artefact artefact) {
 
    	StringBuffer textToInsert = new StringBuffer() ;

    	textToInsert.append("<div class=\"artefact link\">");
    	
		textToInsert.append(renderDefaultDate(artefact.getCreatedDate()));
		
		textToInsert.append("<div class=\"content\">");
		
		textToInsert.append("<h4>" + artefact.getName() + "</h4>"); 
		
		textToInsert.append("<p class=\"link\"><a href=\"" + artefact.getArtefactDetail().getUrl() + "\">" + artefact.getArtefactDetail().getDetail() + "</a></p>") ;
		
		textToInsert.append("<p class=\"context\">" + artefact.getDescription() + "</p>"); 
		
		textToInsert.append("<p class=\"meta\">A Sakai item added on ");
		
		textToInsert.append(myshowcaseService.formattedLongDate(artefact.getCreatedDate()));
		
		textToInsert.append("</p>");
		
		textToInsert.append("</div>");
		
		textToInsert.append("</div>"); 

    	return textToInsert.toString() ;
    }
  
    
    private String renderDefaultArtefact(Artefact artefact,Owner owner) {

    	StringBuffer textToInsert = new StringBuffer() ;

    	if (artefact.getType().getArtefactTypeId() == MyShowcaseArtefactType.TWITTER.artefactTypeId())
    		
    		textToInsert.append(renderDefaultArtefactTwitter(artefact));
    	
    	else if (artefact.getType().getArtefactTypeId() == MyShowcaseArtefactType.FLICKR.artefactTypeId())
    		
    		textToInsert.append(renderDefaultArtefactFlickr(artefact));
    	
		else if (artefact.getType().getArtefactTypeId() == MyShowcaseArtefactType.RSS.artefactTypeId())
			
			textToInsert.append(renderDefaultArtefactRss(artefact));
    	
		else if (artefact.getType().getArtefactTypeId() == MyShowcaseArtefactType.FILE.artefactTypeId())
			
			textToInsert.append(renderDefaultArtefactFile(artefact,owner));
    	
		else if (artefact.getType().getArtefactTypeId() == MyShowcaseArtefactType.SAKAI.artefactTypeId())
			
			textToInsert.append(renderDefaultArtefactSakai(artefact));
    	
		else if (artefact.getType().getArtefactTypeId() == MyShowcaseArtefactType.WEBLINK.artefactTypeId())
			
			textToInsert.append(renderDefaultArtefactWeblink(artefact));
    	
		else if (artefact.getType().getArtefactTypeId() == MyShowcaseArtefactType.PEBBLEPAD.artefactTypeId())
			
			textToInsert.append(renderDefaultArtefactPebblePad(artefact));

    	return textToInsert.toString() ;
    }	

    
    public String build(Showcase showcase, Owner owner, boolean footerRequired){
    	
    	StringBuffer textToInsert = new StringBuffer();

//    	Set<Artefact> artefacts = showcase.getArtefacts();
        
    	List<Artefact> artefacts = myshowcaseService.getArtefacts(showcase);
    	
		MyShowcaseConfig msc = null;
		
		try {
			
			msc = ConfigUtils.loadConfig(MyShowcaseConfigResource.MyShowcaseConfig.resourceName());
		}
		catch (Exception ex){
				System.out.println("++++ Unable to read MyShowcase.config.xml ++++++++");
		}

  	
    	textToInsert.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">");
    	
    	textToInsert.append("<html>");
    	
		textToInsert.append("<head>");
		
		textToInsert.append("<title>Default Showcase</title>");
		
		textToInsert.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">");
		
//		textToInsert.append("<link media=\"all\" href=\"/" + myshowcaseVersion + "/css/DefaultShowcase.css\" rel=\"stylesheet\" type=\"text/css\" />");
		textToInsert.append("<link media=\"all\" href=\"/" + "myshowcase/css/DefaultShowcase.css\" rel=\"stylesheet\" type=\"text/css\" />");

		textToInsert.append("</head>");
		
		textToInsert.append("<body>");
		
		textToInsert.append("<div id=\"wrapper\">"); 
		
		textToInsert.append("<div id=\"header\">"); 
		
		textToInsert.append("<h1>" + owner.getForename() + " " + owner.getSurname() + "</h1>");
		
		textToInsert.append("<h2>" + showcase.getName() + "</h2>"); 
		
		textToInsert.append("<div class=\"description\">");
		
		textToInsert.append("<p>" + showcase.getFullDesc() + "</p>"); 
		
		textToInsert.append("</div>");		
		
		textToInsert.append("</div>"); 
		
		textToInsert.append("<div id=\"main\">"); 

		textToInsert.append("<div class=\"artefacts\">"); 


		
		for (Artefact artefact : artefacts){
			
			textToInsert.append(renderDefaultArtefact(artefact,owner));
		}

		
		textToInsert.append("</div>");
		
		textToInsert.append("</div>");
		

		if (footerRequired){
			
			textToInsert.append("<div id=\"footer\">") ;
			
			textToInsert.append("<p>" + showcase.getName() + " is a <a href=\"http://www.my-showcase.org\">MyShowcase</a> showcase created by " + owner.getForename() + " " + owner.getSurname() + " using the <a href=\"http://www.my-showcase.org/themes\">content stream</a> theme.</p>") ;
			
			textToInsert.append("</div>") ;
		}
		
		
		textToInsert.append("</div>");
		
		textToInsert.append("</body>");

    	return textToInsert.toString();
    }
	
}
