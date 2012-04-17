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

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.sakaiproject.myshowcase.model.Artefact;
import org.sakaiproject.myshowcase.model.ArtefactMapping;
import org.sakaiproject.myshowcase.model.Owner;
import org.sakaiproject.myshowcase.model.Showcase;
import org.sakaiproject.myshowcase.model.ShowcaseTheme;
import org.sakaiproject.myshowcase.model.ShowcaseThemeSimpleCV;
import org.sakaiproject.myshowcase.config.ConfigUtils;
import org.sakaiproject.myshowcase.config.MyShowcaseConfig;
import org.sakaiproject.myshowcase.config.MyShowcaseConfigIdentifier;
import org.sakaiproject.myshowcase.config.MyShowcaseConfigResource;
import org.sakaiproject.myshowcase.config.MyShowcaseConfigValues;
import org.sakaiproject.myshowcase.dao.*;
import org.sakaiproject.myshowcase.model.*;
import org.sakaiproject.myshowcase.logic.*;


public class MyShowcaseRenderThemeSimpleCV implements IMyShowcaseRenderTheme{
    
	public static IMyShowcaseService myshowcaseService;
	
	final protected Log log = LogFactory.getLog(getClass());
	
	long competencyId = 1337;
	
	long mappingIdIntroduction = 1338;
	long mappingidContactDetails = 1339;
	long mappingIdEducation = 1340;
	long mappingIdExperience = 1341;	
	long mappingIdAdditionalSkills = 1342;
	long mappingIdHobbiesAndInterests = 1343;
	long mappingIdFurtherInfo = 1344;	
    
	Owner owner = new Owner();
	Showcase showcase = new Showcase();
	ShowcaseTheme theme = new ShowcaseTheme();
	
	ShowcaseThemeSimpleCV simpleCV = new ShowcaseThemeSimpleCV();	
    
	public MyShowcaseRenderThemeSimpleCV(IMyShowcaseService myshowcaseService) {
		setMyshowcaseService(myshowcaseService);
		
    }

    public void setMyshowcaseService(IMyShowcaseService service) {

        this.myshowcaseService = service;
    }
	
    private String renderCVArtefactTwitter(Artefact artefact) {

    	StringBuffer textToInsert = new StringBuffer() ;
		textToInsert.append("<div class=\"artefact twitter\">");
		textToInsert.append("<h4>&ldquo;");
		textToInsert.append(artefact.getArtefactDetail().getDetail());
		textToInsert.append("&rdquo;</h4>");
		textToInsert.append("<p class=\"meta\">A Twitter item added on ");
		textToInsert.append(myshowcaseService.formattedLongDate(artefact.getCreatedDate()));
		textToInsert.append("</p>");
		textToInsert.append("<p class=\"context\">");
		textToInsert.append(artefact.getDescription());
		textToInsert.append("</p>");
		textToInsert.append("</div>");
    	return textToInsert.toString() ;
    }
    
    private String renderCVArtefactFlickr(Artefact artefact) {

    	StringBuffer textToInsert = new StringBuffer() ;
		textToInsert.append("<div class=\"artefact flickr\">");
		textToInsert.append("<h4>");
		textToInsert.append(artefact.getName()) ;
		textToInsert.append("</h4>");
//		textToInsert.append("<p class=\"meta\">A Flickr item added on ");
		textToInsert.append("<p class=\"meta\">A  <a href=\"http://www.flickr.com/\" target=\"_blank\">Flickr</a> item added on "); 
		textToInsert.append(myshowcaseService.formattedLongDate(artefact.getCreatedDate()));
		textToInsert.append("</p>");
		textToInsert.append("<img src=\"" + artefact.getArtefactDetail().getUrl() + "\" alt=\"");
		textToInsert.append(artefact.getName());
		textToInsert.append("\"/>");
		textToInsert.append("<p class=\"context\">");
		textToInsert.append(artefact.getDescription());
		textToInsert.append("</p>");
		textToInsert.append("</div>");		
    	return textToInsert.toString() ;
    }

    private String renderCVArtefactFile(Artefact artefact) {

       	String fileLocation = MyShowcaseConfigValues.getInstance().getUploadDir() + getOwner().getOwnerId() + "/" + artefact.getArtefactDetail().getFileName();
   	
    	StringBuffer textToInsert = new StringBuffer() ;
		textToInsert.append("<div class=\"artefact file\">");
		textToInsert.append("<h4>");
		textToInsert.append(artefact.getName());
		textToInsert.append("</h4>");
		textToInsert.append("<p class=\"meta\">A file based item added on ");
		textToInsert.append(myshowcaseService.formattedLongDate(artefact.getCreatedDate()));
		
		textToInsert.append("- <a href=\"" + fileLocation + "\" target=\"_blank\">click here to download</a></p>");
		
//		textToInsert.append("- <a href=\"/filestore/" + owner.getUserId() + "/");
//		textToInsert.append("\" target=\"_blank\">click here to download</a></p>");
		
		textToInsert.append("<p class=\"context\">");
		textToInsert.append(artefact.getDescription());
		textToInsert.append("</p>");
		textToInsert.append("</div>");
    	return textToInsert.toString() ;
    }

    private String renderCVArtefactWeblink(Artefact artefact) {
 
    	StringBuffer textToInsert = new StringBuffer() ;
		textToInsert.append("<div class=\"artefact link\">");
		textToInsert.append("<h4>");
		textToInsert.append(artefact.getName());
		textToInsert.append("</h4>");
		textToInsert.append("<p class=\"meta\">A link item added on ");
		textToInsert.append(myshowcaseService.formattedLongDate(artefact.getCreatedDate()));
		textToInsert.append("</p>");
		textToInsert.append("<p class=\"link\"><a href=\"");
		textToInsert.append(artefact.getArtefactDetail().getUrl());
		textToInsert.append("\">");
		textToInsert.append(artefact.getArtefactDetail().getUrl());
		textToInsert.append("</a></p>");
		textToInsert.append("<p class=\"context\">");
		textToInsert.append(artefact.getDescription());
		textToInsert.append("</p>");
		textToInsert.append("</div>");			
    	return textToInsert.toString() ;
    }

    private String renderCVArtefactRss(Artefact artefact) {

    	StringBuffer textToInsert = new StringBuffer() ;
		textToInsert.append("<div class=\"artefact rss\">");
		textToInsert.append("<h4>");
		textToInsert.append(artefact.getName());
		textToInsert.append("</h4>");
		textToInsert.append("<p class=\"meta\">An RSS item added on ");
		textToInsert.append(myshowcaseService.formattedLongDate(artefact.getCreatedDate()));
		textToInsert.append("</p>");
		textToInsert.append("<blockquote>");
		textToInsert.append(artefact.getArtefactDetail().getDetail());
		textToInsert.append("</blockquote>");
		textToInsert.append("<p class=\"context\">");
		textToInsert.append(artefact.getDescription());
		textToInsert.append("</p>");
		textToInsert.append("</div>");	
    	return textToInsert.toString() ;
    }
    
    private String renderCVArtefactPebblePad(Artefact artefact) {

    	StringBuffer textToInsert = new StringBuffer() ;
		textToInsert.append("<div class=\"artefact pebblepad\">");
		textToInsert.append("<h4>");
		textToInsert.append(artefact.getName());
		textToInsert.append("</h4>");
		textToInsert.append("<p class=\"meta\">A Pebblepad item added on ");
		textToInsert.append(myshowcaseService.formattedLongDate(artefact.getCreatedDate()));
		textToInsert.append("</p>");
		textToInsert.append("<p class=\"link\"><a href=\"");
		textToInsert.append(artefact.getArtefactDetail().getUrl());
		textToInsert.append("\">");
		textToInsert.append(artefact.getArtefactDetail().getDetail());
		textToInsert.append("</a></p>");
		textToInsert.append("<p class=\"context\">");
		textToInsert.append(artefact.getDescription()) ;
		textToInsert.append("</p>");
		textToInsert.append("</div>");
    	return textToInsert.toString() ;
    }
    
    private String renderCVArtefactSakai(Artefact artefact) {
 
    	StringBuffer textToInsert = new StringBuffer() ;
		textToInsert.append("<div class=\"artefact sakai\">");
		textToInsert.append("<h4>");
		textToInsert.append(artefact.getName());
		textToInsert.append("</h4>");
		textToInsert.append("<p class=\"meta\">A Sakai item added on ");
		textToInsert.append(myshowcaseService.formattedLongDate(artefact.getCreatedDate()));
		textToInsert.append("</p>");
		textToInsert.append("<p class=\"link\"><a href=\"");
		textToInsert.append(artefact.getArtefactDetail().getUrl());
		textToInsert.append("\">");
		textToInsert.append(artefact.getArtefactDetail().getUrl());
		textToInsert.append("</a></p>");
		textToInsert.append("<p class=\"context\">");
		textToInsert.append(artefact.getDescription()) ;
		textToInsert.append("</p>");
		textToInsert.append("</div>");		

    	return textToInsert.toString() ;
    }
    
    private String renderCVArtefact(Artefact artefact) {

    	StringBuffer textToInsert = new StringBuffer() ;

    	if (artefact.getType().getArtefactTypeId() == MyShowcaseArtefactType.TWITTER.artefactTypeId())
    		textToInsert.append(renderCVArtefactTwitter(artefact));
    	else if (artefact.getType().getArtefactTypeId() == MyShowcaseArtefactType.FLICKR.artefactTypeId())
    		textToInsert.append(renderCVArtefactFlickr(artefact));
		else if (artefact.getType().getArtefactTypeId() == MyShowcaseArtefactType.RSS.artefactTypeId())
			textToInsert.append(renderCVArtefactRss(artefact));
		else if (artefact.getType().getArtefactTypeId() == MyShowcaseArtefactType.FILE.artefactTypeId())
			textToInsert.append(renderCVArtefactFile(artefact));
		else if (artefact.getType().getArtefactTypeId() == MyShowcaseArtefactType.SAKAI.artefactTypeId())
			textToInsert.append(renderCVArtefactSakai(artefact));
		else if (artefact.getType().getArtefactTypeId() == MyShowcaseArtefactType.WEBLINK.artefactTypeId())
			textToInsert.append(renderCVArtefactWeblink(artefact));
		else if (artefact.getType().getArtefactTypeId() == MyShowcaseArtefactType.PEBBLEPAD.artefactTypeId())
			textToInsert.append(renderCVArtefactPebblePad(artefact));

    	return textToInsert.toString() ;
    }	

    private String renderCVHeading(String heading) {
       	StringBuffer textToInsert = new StringBuffer() ;
       	System.out.println("Render heading " + heading) ;
		textToInsert.append("<div id=\"main\">");
		textToInsert.append("<div class=\"mapping\">");
		textToInsert.append("<div class=\"left\">");
		textToInsert.append("<h3>");
		textToInsert.append(heading);
		textToInsert.append("</h3>");
		textToInsert.append("</div>");
		textToInsert.append("<div class=\"right\">");
       	return textToInsert.toString();
    }

    public String build(Showcase showcase, Owner owner, boolean footerRequired) {
    	
    	StringBuffer textToInsert = new StringBuffer() ;
    	
    	setOwner(owner);
    	
    	Set<Artefact> artefacts = showcase.getArtefacts() ; 
		Set<ArtefactMapping> artefactMappings ;
    	
    	simpleCV.setCompetencyId(MyShowcaseCompetencyFrameworkCV.COMPETENCY_ID.identifier());
       	
    	simpleCV.setMappingIdIntroduction(MyShowcaseCompetencyFrameworkCV.INTRODUCTION.identifier());
		simpleCV.setMappingIdContactDetails(MyShowcaseCompetencyFrameworkCV.CONTACT_DETAILS.identifier());
		simpleCV.setMappingIdEducation(MyShowcaseCompetencyFrameworkCV.EDUCATION.identifier());
		simpleCV.setMappingIdExperience(MyShowcaseCompetencyFrameworkCV.EXPERIENCE.identifier());
		simpleCV.setMappingIdAdditionalSkills(MyShowcaseCompetencyFrameworkCV.ADDITIONAL_SKILLS.identifier());
		simpleCV.setMappingIdHobbiesAndInterests(MyShowcaseCompetencyFrameworkCV.HOBBIES_AND_INTERESTS.identifier());
		simpleCV.setMappingIdFurtherInfo(MyShowcaseCompetencyFrameworkCV.FURTHER_INFO.identifier());   	
    	
    	simpleCV.setMappingIdIntroductionCount(myshowcaseService.getArtefactMappingsCount(showcase,MyShowcaseCompetencyFrameworkCV.COMPETENCY_ID.identifier(),MyShowcaseCompetencyFrameworkCV.INTRODUCTION.identifier()));
		simpleCV.setMappingIdContactDetailsCount(myshowcaseService.getArtefactMappingsCount(showcase,MyShowcaseCompetencyFrameworkCV.COMPETENCY_ID.identifier(),MyShowcaseCompetencyFrameworkCV.CONTACT_DETAILS.identifier()));
		simpleCV.setMappingIdEducationCount(myshowcaseService.getArtefactMappingsCount(showcase,MyShowcaseCompetencyFrameworkCV.COMPETENCY_ID.identifier(),MyShowcaseCompetencyFrameworkCV.EDUCATION.identifier()));
		simpleCV.setMappingIdExperienceCount(myshowcaseService.getArtefactMappingsCount(showcase,MyShowcaseCompetencyFrameworkCV.COMPETENCY_ID.identifier(),MyShowcaseCompetencyFrameworkCV.EXPERIENCE.identifier()));
		simpleCV.setMappingIdAdditionalSkillsCount(myshowcaseService.getArtefactMappingsCount(showcase,MyShowcaseCompetencyFrameworkCV.COMPETENCY_ID.identifier(),MyShowcaseCompetencyFrameworkCV.ADDITIONAL_SKILLS.identifier()));
		simpleCV.setMappingIdHobbiesAndInterestsCount(myshowcaseService.getArtefactMappingsCount(showcase,MyShowcaseCompetencyFrameworkCV.COMPETENCY_ID.identifier(),MyShowcaseCompetencyFrameworkCV.HOBBIES_AND_INTERESTS.identifier()));
		simpleCV.setMappingIdFurtherInfoCount(myshowcaseService.getArtefactMappingsCount(showcase,MyShowcaseCompetencyFrameworkCV.COMPETENCY_ID.identifier(), MyShowcaseCompetencyFrameworkCV.FURTHER_INFO.identifier()));

		MyShowcaseConfig msc = null;
		
		try {
			
			msc = ConfigUtils.loadConfig(MyShowcaseConfigResource.MyShowcaseConfig.resourceName());
		}
		catch (Exception ex){
				System.out.println("++++ Unable to read MyShowcase.config.xml ++++++++");
		}
		
		String myshowcaseVersion = msc.getParameter(MyShowcaseConfigIdentifier.ConfigApplciation.identifier(), MyShowcaseConfigIdentifier.ConfigApplciationVersion.identifier()) ;
		
		
		textToInsert.append("<html>") ;
		textToInsert.append("<head>") ;
		textToInsert.append("<title>Simple CV</title>") ;
		textToInsert.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");

//		textToInsert.append("<link media=\"all\" href=\"/" + myshowcaseVersion + "/css/SimpleCV.css\" rel=\"stylesheet\" type=\"text/css\" />");
		textToInsert.append("<link media=\"all\" href=\"/" + "myshowcase/css/SimpleCV.css\" rel=\"stylesheet\" type=\"text/css\" />");

		textToInsert.append("</head>") ;
		textToInsert.append("<body>") ;

		textToInsert.append("<div id=\"wrapper\">") ;
		textToInsert.append("<div id=\"header\">") ;
		textToInsert.append("<h2>"+showcase.getName()+"</h2>") ;
		textToInsert.append("<p>"+showcase.getFullDesc()+"</p>") ;
		textToInsert.append("<div id=\"headimage\">&nbsp;</div>") ;
		textToInsert.append("</div>") ;

		textToInsert.append("<div id=\"main\">") ;
		
		if (simpleCV.getMappingIdIntroductionCount() > 0) {
			textToInsert.append("<div id=\"ms-cv-detail-introduction\">") ;
			textToInsert.append(renderCVHeading("Introduction")) ;

			textToInsert.append(renderMappingsOfArtefact(artefacts,simpleCV.getMappingIdIntroduction())) ;

			textToInsert.append("</div>") ;
			textToInsert.append("</div>") ;
			textToInsert.append("</div>") ;
			textToInsert.append("</div>") ;
		}
	
		if (simpleCV.getMappingIdContactDetailsCount() > 0) {
			textToInsert.append("<div id=\"ms-cv-detail-contact-details\">") ;
			textToInsert.append(renderCVHeading("Contact Details")) ;

			textToInsert.append(renderMappingsOfArtefact(artefacts,simpleCV.getMappingIdContactDetails())) ;

			textToInsert.append("</div>") ;
			textToInsert.append("</div>") ;
			textToInsert.append("</div>") ;
			textToInsert.append("</div>") ;
		}
		
		if (simpleCV.getMappingIdEducationCount() > 0) {
			textToInsert.append("<div id=\"ms-cv-detail-education\">") ;
			textToInsert.append(renderCVHeading("Education")) ;

			textToInsert.append(renderMappingsOfArtefact(artefacts,simpleCV.getMappingIdEducation())) ;

			textToInsert.append("</div>") ;
			textToInsert.append("</div>") ;
			textToInsert.append("</div>") ;
			textToInsert.append("</div>") ;
		}

		
		if (simpleCV.getMappingIdExperienceCount() > 0) {
			textToInsert.append("<div id=\"ms-cv-detail-experience\">") ;
			textToInsert.append(renderCVHeading("Experience")) ;

			textToInsert.append(renderMappingsOfArtefact(artefacts,simpleCV.getMappingIdExperience())) ;

			textToInsert.append("</div>") ;
			textToInsert.append("</div>") ;
			textToInsert.append("</div>") ;
			textToInsert.append("</div>") ;
		}

		
		if (simpleCV.getMappingIdAdditionalSkillsCount() > 0) {
			textToInsert.append("<div id=\"ms-cv-detail-additional-skills\">") ;
			textToInsert.append(renderCVHeading("Additional Skills")) ;

			textToInsert.append(renderMappingsOfArtefact(artefacts,simpleCV.getMappingIdAdditionalSkills())) ;

			textToInsert.append("</div>") ;
			textToInsert.append("</div>") ;
			textToInsert.append("</div>") ;
			textToInsert.append("</div>") ;
		}

		
		if (simpleCV.getMappingIdHobbiesAndInterestsCount() > 0) {
			textToInsert.append("<div id=\"ms-cv-detail-hobbies-interests\">") ;
			textToInsert.append(renderCVHeading("Hobbies and Interests")) ;

			textToInsert.append(renderMappingsOfArtefact(artefacts,simpleCV.getMappingIdHobbiesAndInterests())) ;

			textToInsert.append("</div>") ;
			textToInsert.append("</div>") ;
			textToInsert.append("</div>") ;
			textToInsert.append("</div>") ;
		}

		
		if (simpleCV.getMappingIdFurtherInfoCount() > 0) {
			textToInsert.append("<div id=\"ms-cv-detail-further-info\">") ;
			textToInsert.append(renderCVHeading("Further Info")) ;

			textToInsert.append(renderMappingsOfArtefact(artefacts,simpleCV.getMappingIdFurtherInfo())) ;

			textToInsert.append("</div>") ;
			textToInsert.append("</div>") ;
			textToInsert.append("</div>") ;
			textToInsert.append("</div>") ;
		}

		
		textToInsert.append("</div>") ;
		
		if (footerRequired){
			textToInsert.append("<div id=\"footer\">") ;
			textToInsert.append("<p>" + showcase.getName() + " is a <a href=\"http://www.my-showcase.org\">MyShowcase</a> showcase created by " + owner.getForename() + " " + owner.getSurname() + " using the <a href=\"http://www.my-showcase.org/themes\">simpleCV</a> theme.</p>") ;
			textToInsert.append("</div>") ;
		}

		textToInsert.append("</div>") ;
		textToInsert.append("</body>") ;
		textToInsert.append("</html>") ;

	
	
	
		return textToInsert.toString();
    }   



    private String renderMappingsOfArtefact(Set<Artefact> artefacts, Long mappingId) {
    	StringBuffer textToInsert = new StringBuffer() ;

		for (Artefact artefact : artefacts) {
			Artefact localArtefact = artefact.getDeepCopy();
			localArtefact.setArtefactId(localArtefact.getSavedArtefactId()) ;
			
			for (ArtefactMapping artefactMapping : myshowcaseService.getArtefactMappings(localArtefact)) {
				if (artefactMapping.getMappingId().longValue() == mappingId) {
					textToInsert.append(renderCVArtefact(artefact)) ;
				}
			}
		}
		
		
     	return textToInsert.toString() ;
    }

    private Owner getOwner(){
 
     	return owner ;
    }   

    private void setOwner(Owner owner){
 
     	this.owner = owner;
    }      
}
