


$(document).ready(function() {

//	
// Set Up Home page 
//	
	
	var introduction = $("#introduction").val();
	var contactDetails = $("#contactDetails").val();
	var education  = $("#education").val();
	var experience = $("#experience").val();
	var additionalSkills = $("#additionalSkills").val();
	var hobbiesAndInterests  = $("#hobbiesAndInterests").val();
	var furtherInfo = $("#furtherInfo").val();

	SimpleCV.buildCVContent();
	
});

var SimpleCV = {
		 
		retrieveCVContent: (function(showcaseId,competencyId,mappingId,section){
	

			$.post("MyShowcaseGetEvidenceArtefactMappingsController.json", {showcaseId: showcaseId,
				                                                            competencyId: competencyId,
				                                                            mappingId: mappingId}, function(list) {
                                 	
				                                                            	
				var textToInsert = '';
				
				if (section == SimpleCV.getCodeIntroduction()){
					
					textToInsert += SimpleCV.renderDetail("Introduction",list);
					$('#ms-cv-detail-introduction').append(textToInsert);
				}
				else if (section == SimpleCV.getCodeContactDetails()){
					
					textToInsert += SimpleCV.renderDetail("Contact Details",list);
					$('#ms-cv-detail-contact-details').append(textToInsert);
				}
				else if (section == SimpleCV.getCodeEducation()){
					
					textToInsert += SimpleCV.renderDetail("Education",list);
					$('#ms-cv-detail-education').append(textToInsert);
				}	
				else if (section == SimpleCV.getCodeExperience()){
					
					textToInsert += SimpleCV.renderDetail("Experience",list);
					$('#ms-cv-detail-experience').append(textToInsert);
				}				
				else if (section == SimpleCV.getCodeAdditionalSkills()){
					
					textToInsert += SimpleCV.renderDetail("Additional Skills",list);
					$('#ms-cv-detail-additional-skills').append(textToInsert);
				}				
				else if (section == SimpleCV.getCodeHobbiesAndInterests()){
					
					textToInsert += SimpleCV.renderDetail("Hobbies and Interests",list);
					$('#ms-cv-detail-hobbies-interests').append(textToInsert);
				}
				else if (section == SimpleCV.getCodeFurtherInfo()){
					
					textToInsert += SimpleCV.renderDetail("Further Info",list);
					$('#ms-cv-detail-further-info').append(textToInsert);
				}				
				
			 }); 
		}),	

		   
	    buildCVContent: (function(){
	
	    	
	    	
	    	var introduction = $("#introduction").val();
	    	var contactDetails = $("#contactDetails").val();
	    	var education  = $("#education").val();
	    	var experience = $("#experience").val();
	    	var additionalSkills = $("#additionalSkills").val();
	    	var hobbiesAndInterests  = $("#hobbiesAndInterests").val();
	    	var furtherInfo = $("#furtherInfo").val();
	    	
	    	var competencyId = $("#competencyId").val();
	        var showcaseId = $("#showcaseId").val();
	        
	    	var introductionId = $("#introductionId").val();
	    	var contactDetailsId = $("#contactDetailsId").val();
	    	var educationId  = $("#educationId").val();
	    	var experienceId = $("#experienceId").val();
	    	var additionalSkillsId = $("#additionalSkillsId").val();
	    	var hobbiesAndInterestsId  = $("#hobbiesAndInterestsId").val();
	    	var furtherInfoId = $("#furtherInfoId").val();
        
	    	SimpleCV.renderCVHeader();
	    	
	        if (introduction > 0){

	        	SimpleCV.retrieveCVContent(showcaseId,competencyId,introductionId,SimpleCV.getCodeIntroduction());
	        };
	        
	        if (contactDetails > 0){

	        	SimpleCV.retrieveCVContent(showcaseId,competencyId,contactDetailsId,SimpleCV.getCodeContactDetails());
	        };	
	        
	        if (education > 0){

	        	SimpleCV.retrieveCVContent(showcaseId,competencyId,educationId,SimpleCV.getCodeEducation());
	        };
	        
	        if (experience > 0){

	        	SimpleCV.retrieveCVContent(showcaseId,competencyId,experienceId,SimpleCV.getCodeExperience());
	        };		        
	        
	        if (additionalSkills > 0){

	        	SimpleCV.retrieveCVContent(showcaseId,competencyId,additionalSkillsId,SimpleCV.getCodeAdditionalSkills());
	        };	
	        
	        if (hobbiesAndInterests > 0){

	        	SimpleCV.retrieveCVContent(showcaseId,competencyId,hobbiesAndInterestsId,SimpleCV.getCodeHobbiesAndInterests());
	        };
	        
	        if (furtherInfo > 0){

	        	SimpleCV.retrieveCVContent(showcaseId,competencyId,furtherInfoId,SimpleCV.getCodeFurtherInfo());
	        };	
	        
//	        var ownerUserId = $("#ownerUserId").val() ;
	        var showcaseName = $("#showcaseName").val();
	        var textToInsert = '<p>' + showcaseName + ' is a <a href="http://www.my-showcase.org">MyShowcase</a> showcase created by ' + 'admin' + ' using the <a href="http://www.my-showcase.org/themes">simpleCV</a> theme.</p>'
			$('#footer').append(textToInsert);

		    	
	    }),
	
	    
		renderDetail: (function(heading,list){


			var twitter = 'Twitter';
			var flickr = 'Flickr';
			var file = 'File';
			var weblink = 'Weblink';
			var pebblepad = 'PebblePad';
			var rss = 'Rss';
			var sakai = 'Sakai';			
			
			var textToInsert =  '';
			
			var location = "#ms-cv-detail";
			var singleQuote = "'";
			
			textToInsert += '<div id="main">';
			
			textToInsert += '<div class="mapping">';
				
			textToInsert += '<div class="left">';
					textToInsert += '<h3>';
					textToInsert += heading;
					textToInsert += '</h3>';
			textToInsert += '</div>';
				
			textToInsert += '<div class="right">';
					
		   			$.each(list, function(index, artefact) {
	    	
						
		    			if (artefact.type.name == twitter){
			    			
		    				textToInsert += SimpleCV.renderArtefactTwitter(artefact);
		    			
		    			} else if (artefact.type.name == flickr){
		    			
		    				textToInsert += SimpleCV.renderArtefactFlickr(artefact);
		    			
		    			} else if (artefact.type.name == rss){
		    			
		    				textToInsert += SimpleCV.renderArtefactRss(artefact);
		    			
		    			} else if (artefact.type.name == file){
		    			
		    				textToInsert += SimpleCV.renderArtefactFile(artefact);
		    			
		    			} else if (artefact.type.name == sakai){
		    			
		    				textToInsert += SimpleCV.renderArtefactSakai(artefact);
		    			
		    			} else if (artefact.type.name == weblink){
		    			
		    				textToInsert += SimpleCV.renderArtefactWeblink(artefact);
		    			
		    			} else if (artefact.type.name == pebblepad){
		    			
		    				textToInsert += SimpleCV.renderArtefactPebblePad(artefact);
		    			}		   				
  
		   			});	
		   		
				textToInsert += '</div>'
				
			textToInsert += '</div>';

			return textToInsert;
			
		}),	
	
		
		renderArtefactFile: (function(artefact){
		
			
			var textToInsert =  '';
			var dateCreated = new Date(artefact.createdDateTime);
				
			textToInsert += '<div class="artefact file">';
				textToInsert += '<h4>';
				textToInsert += artefact.name;
				textToInsert += '</h4>';
				textToInsert += '<p class="meta">A file based item added on ';
				textToInsert += dateCreated.toLocaleString();
				textToInsert += '- <a href="';
				textToInsert += artefact.artefactDetail.filePath + artefact.artefactDetail.fileName;
				textToInsert += '" target="_blank">click here to download</a></p>';
				textToInsert += '<p class="context">';
				textToInsert += artefact.description;
				textToInsert += '</p>';
			textToInsert += '</div>';
				
			return textToInsert;
			
		}),
		
		
		renderArtefactRss: (function(artefact){
		
			var dateCreated = new Date(artefact.createdDateTime);
			var textToInsert =  '';
			
			textToInsert += '<div class="artefact rss">';
				textToInsert += '<h4>';
				textToInsert += artefact.name;
				textToInsert += '</h4>';
				textToInsert += '<p class="meta">An RSS item added on ';
				textToInsert += dateCreated.toLocaleString();
				textToInsert += '</p>';
				textToInsert += '<blockquote>';
					textToInsert += artefact.artefactDetail.detail;
					textToInsert += '</blockquote>';
					textToInsert += '<p class="context">';
					textToInsert += artefact.description;
					textToInsert += '</p>';
			textToInsert += '</div>';	

			return textToInsert;
			
		}),
		
		
		renderArtefactWeblink: (function(artefact){
			
			var dateCreated = new Date(artefact.createdDateTime);
			var textToInsert =  '';
			
			textToInsert += '<div class="artefact link">';
				textToInsert += '<h4>';
				textToInsert += artefact.name;
				textToInsert += '</h4>';
				textToInsert += '<p class="meta">A link item added on ';
				textToInsert += dateCreated.toLocaleString();
				textToInsert += '</p>';
				textToInsert += '<p class="link"><a href="';
				textToInsert += artefact.artefactDetail.url;
				textToInsert += '">';
				textToInsert += artefact.artefactDetail.url;
				textToInsert += '</a></p>';
				textToInsert += '<p class="context">';
				textToInsert += artefact.description;
				textToInsert += '</p>';
				
			textToInsert += '</div>';			

			return textToInsert;
			
		}),				
		
		
		renderArtefactSakai: (function(artefact){
		
			var dateCreated = new Date(artefact.createdDateTime);
			var textToInsert =  '';
			
			textToInsert += '<div class="artefact sakai">';
				textToInsert += '<h4>';
				textToInsert += artefact.name;
				textToInsert += '</h4>';
				textToInsert += '<p class="meta">A Sakai item added on ';
				textToInsert += dateCreated.toLocaleString();
				textToInsert += '</p>';
				textToInsert += '<p class="link"><a href="';
				textToInsert += artefact.artefactDetail.url;
				textToInsert += '">';
				textToInsert += artefact.artefactDetail.url;
				textToInsert += '</a></p>';
				textToInsert += '<p class="context">';
				textToInsert += artefact.description;
				textToInsert += '</p>';
		    textToInsert += '</div>';		

			return textToInsert;
			
		}),	
		
		
		renderArtefactPebblePad: (function(artefact){
		
			alert ("Pebble Pad - Yes");
			
			var dateCreated = new Date(artefact.createdDateTime);
			var textToInsert =  '';
			
			textToInsert += '<div class="artefact pebblepad">';
				textToInsert += '<h4>';
				textToInsert += artefact.name;
				textToInsert += '</h4>';
				textToInsert += '<p class="meta">A Pebblepad item added on ';
				textToInsert += dateCreated.toLocaleString();
				textToInsert += '</p>';
				textToInsert += '<p class="link"><a href="';
				textToInsert += artefact.artefactDetail.url;
				textToInsert += '">';
				textToInsert += artefact.artefactDetail.url;
				textToInsert += '</a></p>';
				textToInsert += '<p class="context">';
				textToInsert += artefact.description;
				textToInsert += '</p>';
				textToInsert += '</div>';
			

			return textToInsert;
			
		}),	
		
		
		renderArtefactFlickr: (function(artefact){
		
			var dateCreated = new Date(artefact.createdDateTime);
			var textToInsert =  '';
			
			textToInsert += '<div class="artefact flickr">';
				textToInsert += '<h4>';
				textToInsert += artefact.name;
				textToInsert += '</h4>';
				textToInsert += '<p class="meta">A Flickr item added on ';
				textToInsert += dateCreated.toLocaleString();
				textToInsert += '</p>';
				textToInsert += '<img src="' + artefact.artefactDetail.url + '" alt="';
				textToInsert += artefact.name;
				textToInsert += '" />';
				textToInsert += '<p class="context">';
				textToInsert += artefact.description;
				textToInsert += '</p>';
			textToInsert += '</div>';		

			return textToInsert;
			
		}),
		
		
		renderArtefactTwitter: (function(artefact){
		
			var dateCreated = new Date(artefact.createdDateTime);
			var textToInsert =  '';
			
			textToInsert += '<div class="artefact twitter">';
				textToInsert += '<h4>&ldquo;';
				textToInsert += artefact.artefactDetail.detail;
				textToInsert += '&rdquo;</h4>';
				textToInsert += '<p class="meta">A Twitter item added on ';
				textToInsert += dateCreated.toLocaleString();
				textToInsert += '</p>';
				textToInsert += '<p class="context">';
				textToInsert += artefact.description;
				textToInsert += '</p>';
			textToInsert += '</div>';
				
			return textToInsert;
			
		}),	
		
		
		renderCVHeader: (function(){
			
			var textToInsert =  '';
			var title = $("#showcaseName").val();
			var desc = $("#showcaseFullDesc").val();
			textToInsert += '<div id="header">';
			
				textToInsert += '<h1>Jack Jones</h1>';
				textToInsert += '<h2>';
				textToInsert += title;	
				textToInsert += '</h2>';
				textToInsert += '<p>';
				textToInsert += desc;
				textToInsert += '</p>';
			
				textToInsert += '<div id="headimage">&nbsp;</div>';
			
			textToInsert += '</div>';	

			$('#ms-cv-header').append(textToInsert);	
			
			
		}),	
		
		
		getCodeIntroduction: (function(){
			return "introduction";
		}),	
		
		getCodeContactDetails: (function(){
			return "contactDetails";
		}),	
		
		getCodeExperience: (function(){
			return "experience";
		}),
		
		getCodeEducation: (function(){
			return "education";
		}),	
		
		getCodeHobbiesAndInterests: (function(){
			return "hobbiesInterests";
		}),	
		
		getCodeAdditionalSkills: (function(){
			return "additionalSkills";
		}),	
		
		getCodeFurtherInfo: (function(){
			return "furtherInfo";
		})		
	};	

