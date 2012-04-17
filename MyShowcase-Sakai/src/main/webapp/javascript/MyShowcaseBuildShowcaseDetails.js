
$(document).ready(function() {

	WizardDetails.loadChosenEvidence();	
	
	WizardDetails.setUp();
	
	$("#ms-wizard-details-next").click(function(){
		
		if (WizardDetails.validate() == true){
			WizardDetails.maintainShowcase();
		}
	
	});

	
	$("#showcaseTitle").focus(function(){
		
		if( WizardDetails.getTitle() == WizardDetails.getDefaultTitle() ) {
			WizardDetails.setTitle("");
		}
	
	});	

	$("#showcaseTitle").blur(function(){
		
		if( WizardDetails.getTitle().length == 0) {
			WizardDetails.setTitle(WizardDetails.getDefaultTitle());
		}
	
	});	
	
	$("#showcaseDesc").focus(function(){
		
		if( WizardDetails.getDescription() == WizardDetails.getDefaultDescription() ) {
			WizardDetails.setDescription("");
		}
	
	});	

	$("#showcaseDesc").blur(function(){
		
		if( WizardDetails.getDescription().length == 0) {
			WizardDetails.setDescription(WizardDetails.getDefaultDescription());
		}
	
	});	


	$(function() {
		$("#showcaseStartDate").datepicker({dateFormat: 'dd/mm/yy'});
	});

	$(function() {
		$("#showcaseEndDate").datepicker({dateFormat: 'dd/mm/yy'});
	});
//+++++++++++++ Functions and event management ++++++++++++++++++++++++++	
	
});


var WizardDetails = {
	 
			
    maintainShowcase: (function(){
    	
    	var title = "";
    	
    	var description = "";
    	
        var showcaseId = $("#showcaseId").val();
    	
        var ownerId = $("#ownerId").val();
        
        var title = $("#showcaseTitle").val();
        var description = $("#showcaseDesc").val();
        var action = "add";
		var startDate = $("#showcaseStartDate").val() ;
		var endDate = $("#showcaseEndDate").val() ;
        
	    $.post("MyShowcaseMaintainShowcaseController.json", {showcaseId: showcaseId,
	    													 title:title,
	    													 description: description,
	    													 startDate: startDate,
	    													 endDate: endDate,
	    													 action: action}, function(list) {
	    														 
			 MyShowcaseBuildShowcaseWizard.navigation("next");
	    });	    	
    }),
 
    
    getDefaultDescription: (function(){
   	 
   	 var desc = "Showcase Description ....";
   	 
   	 return desc;
   	 
    }),   
 
   
   getDefaultTitle: (function(){
	   	 
	   	 var title = "Showcase Title ....";
	   	 
	   	 return title;
    }),
	   
	   
    setDescription: (function(desc){
    	 
    	 $('#showcaseDesc').val(desc);
    }),
 
	   
    getDescription: (function(){
    	 
    	 return $('#showcaseDesc').val();
    }),
    
    
    setTitle: (function(title){
   	 
   	 $('#showcaseTitle').val(title);
   }),
   
   getTitle: (function(){
  	 
  	 return $('#showcaseTitle').val();
   }),

   setStartDate: (function(dt){
  	 
  	 $('#showcaseStartDate').val(dt);
   }),
  
  getStartDate: (function(){
 	 
 	 return $('#showcaseStartDate').val();
  }),

  setEndDate: (function(dt){
 	 
 	 $('#showcaseEndDate').val(dt);
  }),
 
  getEndDate: (function(){
	 
	 return $('#showcaseEndDate').val();
  }),
    
   
    addMessage: (function(message){
    	 
    	var textToInsert = "";
  
    	textToInsert += '<div class="warning-background">';
    		textToInsert += '<div class="warning-contents">';
    			textToInsert += '<p class="warning-text">';
    			textToInsert += message;
   			textToInsert += '</div>';
 		textToInsert += '</div>';	  	
    	
    	
    	 $('#ms-messages').append(textToInsert);
    	 
    	 MyShowcase.resize();
    }),
    
    emptyMessages: (function(){
   	 
   	 $('#ms-messages').empty();
   	 
   }), 
    
   validate: (function(){
	    
        var title = $("#showcaseTitle").val();
        var description = $("#showcaseDesc").val();
        var startDate = $("#showcaseStartDate").val();
        var endDate = $("#showcaseEndDate").val();
        
        var isValid = true;
        var isValidStartDate = true ;
        var isValidEndDate = true ;
        
        var textoInsert = '';
        
        WizardDetails.emptyMessages();
        
        if ((title.length == 0) || (title == WizardDetails.getDefaultTitle())){
 
        	isValid = false;
        	
        	WizardDetails.setTitle(WizardDetails.getDefaultTitle());

        	
        	WizardDetails.addMessage("You must enter a showcase title.");
        }	
        
        if ((description.length == 0) || (description == WizardDetails.getDefaultDescription())){

        	isValid = false;
        	
        	WizardDetails.setDescription(WizardDetails.getDefaultDescription());

        	WizardDetails.addMessage("You must enter a showcase description.");

        }

        
        if (WizardDetails.getChosenEvidenceCount() == 0){

        	isValid = false;
 
        	WizardDetails.addMessage("You have not added any evidence to this showcase.");

        }
        /* Date validation stuff */
        if (Validation.noDateEntered(startDate)) {

        	isValid = false;
        	isValidStartDate = false;
        	
        	WizardDetails.addMessage("You must enter a start date for showcase review.");

        } else if (Validation.invalidDateFormat(startDate)) {

        	isValid = false;
        	isValidStartDate = false;
        	
        	WizardDetails.addMessage("Start date format entered is invalid(use dd/mm/yyyy).");

        } else if (Validation.invalidDate(startDate)) {

        	isValid = false;
        	isValidStartDate = false;
        	
        	WizardDetails.addMessage("Start date entered is invalid.");

        } else if (Validation.dateInPast(startDate)) {

        	isValid = false;
        	isValidStartDate = false;
        	
        	WizardDetails.addMessage("Start date entered is in the past.");

        }
        
        // It is possible to have an open ended showcase
        if (!Validation.noDateEntered(endDate))
	        if (Validation.invalidDateFormat(endDate)) {
	
	        	isValid = false;
	        	isValidEndDate = false;
	        	
	        	WizardDetails.addMessage("End date format entered is invalid(use dd/mm/yyyy).");
	
	        } else if (Validation.invalidDate(endDate)) {
	
	        	isValid = false;
	        	isValidEndDate = false;
	        	
	        	WizardDetails.addMessage("End date entered is invalid.");
	
	        } else if (Validation.dateInPast(endDate)) {
	
	        	isValid = false;
	        	isValidEndDate = false;
	        	
	        	WizardDetails.addMessage("End date entered is in the past.");
	
	        }
 
        if (isValidStartDate && isValidEndDate)
        	 if (Validation.endDateBeforeStartDate(startDate, endDate)) {

             	isValid = false;
             	
             	WizardDetails.addMessage("End date entered is before start date.");

             }

        return isValid;
	    	
    }),   

    
    setUp: (function(){
	    
        var title = $("#showcaseTitle").val();
        var description = $("#showcaseDesc").val();
        
        if (title.length == 0){
        	
        	WizardDetails.setTitle(WizardDetails.getDefaultTitle());
        	
        }	
        
        if (description.length == 0){
        	
        	WizardDetails.setDescription(WizardDetails.getDefaultDescription());
        	
        }
	    	
    }), 
    
    
    getShowcase: (function(){
     	
        var showcaseId = $("#showcaseId").val();
        
	    $.post("MyShowcaseGetShowcaseController.json", {showcaseId: showcaseId}, function(list) {
		    	
	    });	    	
    }),	    
		
		
	setChosenEvidenceCount: (function(count){
	  	$('#chosenEvidenceCount').text(count); 
	}),	   

     
	getChosenEvidenceCount: (function(){
	
		var count = $('#chosenEvidenceCount').text();
	    		
	   	return  parseInt(count);
	}),		

	
    loadChosenEvidence: (function(){

    	var showcaseId = $("#showcaseId").val();
    	var count = 0;
		var twitter = 'Twitter';
		var flickr = 'Flickr';
		var file = 'File';
		var weblink = 'Weblink';
		var pebblepad = 'PebblePad';
		var rss = 'Rss';
		var sakai = 'Sakai';
    	
    	WizardDetails.clearChosenEvidence();   	
    	
	    $.post("MyShowcaseShowcaseEvidenceListController.json", {showcaseId: showcaseId}, function(list) {
	    	
	    	$.each(list, function(index, artefact){
	    		
	    		count = count +1; 
	    	});
	
	    	$.each(list, function(index, artefact) { 

	    			if (artefact.type.name == twitter){
	    			
	    				WizardDetails.renderArtefactTwitter(artefact);
	    			
	    			} else if (artefact.type.name == flickr){
	    			
	    				WizardDetails.renderArtefactFlickr(artefact);
	    			
	    			} else if (artefact.type.name == rss){
	    			
	    				WizardDetails.renderArtefactRss(artefact);
	    			
	    			} else if (artefact.type.name == file){
	    			
	    				WizardDetails.renderArtefactFile(artefact);
	    			
	    			} else if (artefact.type.name == sakai){
	    			
	    				WizardDetails.renderArtefactSakai(artefact);
	    			
	    			} else if (artefact.type.name == weblink){
	    			
	    				WizardDetails.renderArtefactWeblink(artefact);
	    			
	    			} else if (artefact.type.name == pebblepad){
	    			
	    				WizardDetails.renderArtefactPebblePad(artefact);
	    			}
	    			
	    			MyShowcase.resize();
	    	});
	    	
	    	WizardDetails.setChosenEvidenceCount(count);
	    	
	    	MyShowcase.resize();
	    	
	    });	    	
    }),
    
    
    clearChosenEvidence: (function(){
    	
		$('#ms-chosen-evidence-stream').empty();	
  
    }),
    
	renderArtefactRss: (function(artefact){
		
		var textToInsert =  '';	
		
		var dateCreated = new Date(artefact.createdDateTime);

		textToInsert += '<div id="ms-post-';
		textToInsert += artefact.artefactId;
		textToInsert += '" class="ms-post rss">'; 
		textToInsert += '<div class="ms-post-badge">';
		textToInsert += '<span>RSS:</span>';
		textToInsert += '</div>';
		textToInsert += '<div class="ms-post-contents">';
		textToInsert += '<p class="ms-post-title">';
		textToInsert += artefact.name;
		textToInsert += '</p>';
		textToInsert += '<p class="ms-post-metadata">Imported on ';
		textToInsert += dateCreated.toLocaleString();
		textToInsert += '<p>';
		textToInsert += '</div>';
		textToInsert += '<div class="ms-post-bodytext">';
		textToInsert += '<p>';
		textToInsert += artefact.description;
		textToInsert += '</p>';
		textToInsert += '<p class="ms-post-url"><a href="';
		textToInsert += artefact.artefactDetail.url;
		textToInsert += '" target="_blank">';
		textToInsert += artefact.artefactDetail.detail;
		textToInsert += '</a>';
		
		textToInsert += WizardDetails.renderEvidenceActions(artefact.artefactId);
		
		textToInsert += WizardDetails.renderMapPanel(artefact.artefactId);		
		
    	textToInsert += '</div>';
    	
		$('#ms-chosen-evidence-stream').append(textToInsert);		
	}),	

	
	renderArtefactPebblePad: (function(artefact){
		
		var textToInsert =  '';
		
		var dateCreated = new Date(artefact.createdDateTime);
		
		textToInsert += '<div id="ms-post-';
		textToInsert += artefact.artefactId; 
		textToInsert += '" class="ms-post pebblepad">'; 
		
		textToInsert += '<div class="ms-post-badge">';
		textToInsert += '<span>Pebblepad:</span>';
		textToInsert += '</div>';
		textToInsert += '<div class="ms-post-contents">';
		textToInsert += '<p class="ms-post-title">';
		textToInsert += artefact.name;
		textToInsert += '</p>';		
		textToInsert += '<p class="ms-post-url"><a href="';
		textToInsert += artefact.artefactDetail.url;
		textToInsert += '" target="_blank">';
		textToInsert += artefact.artefactDetail.url;
		textToInsert +=	'</a>';
		textToInsert += '<p class="ms-post-metadata">Collected on ';
		textToInsert += dateCreated.toLocaleString();
		textToInsert += '</p>';
		textToInsert += '</div>';
		textToInsert += '<div class="ms-post-bodytext">';
		textToInsert += '<p>';
		textToInsert += artefact.description;
		textToInsert += '</p>';
		textToInsert += '</div>';
		
		textToInsert += WizardDetails.renderEvidenceActions(artefact.artefactId);
		

		textToInsert += WizardDetails.renderMapPanel(artefact.artefactId);		
		
		textToInsert += '</div>';
		
		$('#ms-chosen-evidence-stream').append(textToInsert);
	}),

	
	renderArtefactFile:  (function(artefact){
		
		var textToInsert =  '';
		
		var dateCreated = new Date(artefact.createdDateTime);
		
		textToInsert += '<div id="ms-post-';
		textToInsert +=	artefact.artefactId;
		textToInsert += '" class="ms-post file file-';
		textToInsert += artefact.artefactDetail.fileType;
		textToInsert += '">';
		textToInsert += '<div class="ms-post-badge">';
		textToInsert += '<span>File:</span>';
		textToInsert += '</div>';
		textToInsert += '<div class="ms-post-contents">';
		textToInsert += '<p class="ms-post-title">';
		textToInsert += artefact.name;
		textToInsert += '</p>';		
		textToInsert += '<div class="ms-post-info-holder">';
		textToInsert += '<div class="ms-post-info-icon">';
		textToInsert += '<span>Image:</span>';
		textToInsert += '</div>'; 
		textToInsert += '<div class="ms-post-info-text">';	
		textToInsert += '<p class="ms-post-file"><a href="';
		textToInsert += artefact.artefactDetail.filePath + artefact.artefactDetail.fileName;
		textToInsert += '" target="_blank">';
		textToInsert += artefact.artefactDetail.fileName;
		textToInsert += '</a>';
		textToInsert += '<p class="ms-post-metadata">Collected on ';
		textToInsert += dateCreated.toLocaleString();
		textToInsert += '</p>';
		textToInsert += '</div>';	
		textToInsert += '</div>';
		textToInsert += '</div>';
		textToInsert += '<div class="ms-post-bodytext">';
		textToInsert += '<p>';
		textToInsert += artefact.description;
		textToInsert += '</p>';
		textToInsert += '</div>';
		
		textToInsert += WizardDetails.renderEvidenceActions(artefact.artefactId);
		

		textToInsert += WizardDetails.renderMapPanel(artefact.artefactId);		
		
    	textToInsert += '</div>';
		
		$('#ms-chosen-evidence-stream').append(textToInsert);
	}),

	
	renderArtefactWeblink: (function(artefact){
		
		var textToInsert =  '';
		
		var dateCreated = new Date(artefact.createdDateTime);
		textToInsert += '<div id="ms-post-';
		textToInsert += artefact.artefactId;
		textToInsert += '" class="ms-post weblink">'; 
		textToInsert += '<div class="ms-post-badge">';
		textToInsert += '<span>Weblink:</span>';
		textToInsert += '</div>';
		textToInsert += '<div class="ms-post-contents">';
		textToInsert += '<p class="ms-post-title">';
		textToInsert += artefact.name;
		textToInsert += '</p>';		
		textToInsert += '<p class="ms-post-url"><a href="';
		textToInsert += artefact.artefactDetail.url;
		textToInsert += '" target="_blank">';
		textToInsert += artefact.artefactDetail.url;
		textToInsert += '</a>';
		textToInsert += '<p class="ms-post-metadata"> Collected on ';
		textToInsert += dateCreated.toLocaleString(); 
		textToInsert += '</p>';
		textToInsert += '</div>';
		textToInsert += '<div class="ms-post-bodytext">';
		textToInsert += '<p>';
		textToInsert += artefact.description;
		textToInsert += '</p>';
		textToInsert += '</div>';
		
		textToInsert += WizardDetails.renderEvidenceActions(artefact.artefactId);
		

		textToInsert += WizardDetails.renderMapPanel(artefact.artefactId);		
		
    	textToInsert += '</div>';		
		
		$('#ms-chosen-evidence-stream').append(textToInsert);
	}),

	
	renderArtefactTwitter: (function(artefact){

		var textToInsert =  '';
		
		var dateCreated = new Date(artefact.createdDateTime);
		
		textToInsert += '<div id="ms-post-';
		textToInsert += artefact.artefactId;
		textToInsert += '" class="ms-post twitter">';
		textToInsert += '<div class="ms-post-badge">';
		textToInsert += '<span>Twitter:</span>';
		textToInsert += '</div>';
		textToInsert += '<div class="ms-post-contents">';
		textToInsert += '<p class="ms-post-quote">&ldquo;' + artefact.artefactDetail.detail + '&rdquo;</p>';
		textToInsert += '<p class="ms-post-metadata">Posted by <a href="http://www.twitter.com/' + artefact.artefactDetail.twitterUserName +'" target="_blank">' + artefact.artefactDetail.twitterUserName +'</a> on ';
		textToInsert += dateCreated.toLocaleString(); 
		textToInsert += '</p>';
		textToInsert += '</div>';
		
		textToInsert += WizardDetails.renderEvidenceActions(artefact.artefactId);
		

		textToInsert += WizardDetails.renderMapPanel(artefact.artefactId);
		
		
    	textToInsert += '</div>';		
    	
    	
		$('#ms-chosen-evidence-stream').append(textToInsert);	
	}),
	
	
	renderArtefactFlickr: (function(artefact){
		
		var textToInsert =  '';
		
		var dateCreated = new Date(artefact.createdDateTime);

		textToInsert += '<div id="ms-post-';
		textToInsert += artefact.artefactId;
		textToInsert += '" class="ms-post flickr">'; 
		textToInsert += '<div class="ms-post-badge">';
		textToInsert += '<span>Flickr:</span>';
		textToInsert += '</div>';
		textToInsert += '<div class="ms-post-contents">';
		textToInsert += '<p class="ms-post-title">';
		textToInsert += artefact.name;
		textToInsert += '</p>';		
		textToInsert += '<p class="ms-post-metadata">Drawn from <a href="http://www.flickr.com/" target="_blank">Flickr</a> on ';
		textToInsert += dateCreated.toLocaleString(); 		
		textToInsert += '</p>';
		textToInsert += '</div>';
		textToInsert += '<div class="ms-post-bodytext">';
		textToInsert += '<p>';
		textToInsert += artefact.description;
		textToInsert += '</p>';
		textToInsert += '<p>';
		textToInsert += artefact.artefactDetail.detail;
		textToInsert += '</p>';
		textToInsert += '<p>';
		
//		textToInsert += '<img src="' + artefact.artefactDetail.url + '" alt="' + artefact.artefactDetail.url + '"/>';
		textToInsert += '<img src="' + artefact.artefactDetail.url + '" onload="MyShowcase.resize();" alt="' + artefact.artefactDetail.url + '"/>';
		
		textToInsert += '</p>';
		textToInsert += '</div>';
		
		textToInsert += WizardDetails.renderEvidenceActions(artefact.artefactId);
			
		textToInsert += WizardDetails.renderMapPanel(artefact.artefactId);		
    	
		textToInsert += '</div>';
    	
    	
		$('#ms-chosen-evidence-stream').append(textToInsert);
	}),

	
	renderArtefactSakai: (function(artefact){

		var textToInsert =  '';
		
		var dateCreated = new Date(artefact.createdDateTime);

		textToInsert += '<div id="ms-post-';
		textToInsert += artefact.artefactId;
		textToInsert += '" class="ms-post sakai">';
		textToInsert += '<div class="ms-post-badge">';
		textToInsert += '<span>Sakai:</span>';
		textToInsert += '</div>';
		textToInsert += '<div class="ms-post-contents">';
		textToInsert += '<p class="ms-post-title">';
		textToInsert += artefact.name;
		textToInsert += '</p>';		
		textToInsert += '<p class="ms-post-metadata">Collected on ';
		textToInsert += dateCreated.toLocaleString();
		textToInsert += '</p>';
		textToInsert += '</div>';
		
		textToInsert += WizardDetails.renderEvidenceActions(artefact.artefactId);
	
		textToInsert += WizardDetails.renderMapPanel(artefact.artefactId);
		
    	textToInsert += '</div>';		
		
		$('#ms-chosen-evidence-stream').append(textToInsert);
	}),
	

	renderEvidenceActions: (function(artefactId){
		
		var textToInsert =  '';

	    textToInsert += '<div class="showcaser-post-actions">';
		textToInsert += '<ul>';

		textToInsert += '<li><a onclick="WizardDetails.maps(';
		textToInsert += artefactId;
		textToInsert += ');return false;" href="javascript: return null;" class="ms-post-action-tag">View &amp; Remove mappings</a></li>';
		
		textToInsert += '</ul>';
		textToInsert += '</div>';

		return textToInsert;
	}),

	

	renderMapPanel: (function(artefactId){
		
		var textToInsert =  '';

		textToInsert += '<div id="ms-tagmap-';
		textToInsert += artefactId;
		textToInsert += '" class="ms-tagmap" style="display:none;">';
		
			textToInsert += ' <div class="ms-tagmap-contents">';
	    		textToInsert += '<div id="ms-tagger-';
	    		textToInsert += artefactId;
	    		textToInsert += '">';
	    		textToInsert += '</div>'
	    	textToInsert += '</div>';
   		textToInsert += '</div>';
	    		
		return textToInsert;
	}),				
 
	
	renderMap: (function(artefactId){
		
		var textToInsert =  '';
		
		textToInsert += '<div id="ms-tagmap-';
		textToInsert += artefactId;
		textToInsert += '" class="ms-tagmap" style="display:none;">';
/*		
		textToInsert += ' <div class="ms-tagmap-contents">';
		textToInsert += '<p>Below are displayed the maps you allocated to this piece of evidence in your evidence stream.';
		textToInsert += 'Untick the boxes if you wish to remove any that you wish to disassociate with this evidence in your final template.</p>';

			textToInsert += '<div class="mapholder">';
				textToInsert += '<ul>';
					textToInsert += '<li id="comp-0003" style="position:relative;"><span class="file">I know how much coffee to add<input type="checkbox" checked="checked" /></span></li>';
				textToInsert += '</ul>';

			textToInsert += '</div>';

			textToInsert += '<div class="ms-tagmap-close">';
			textToInsert += '<a href="#"><span>Close</span></a>';
			textToInsert += '</div>';
			
        textToInsert += '</div>';
*/
		
		return textToInsert;
		
	}),	
    
    maps: (function(artefactId){
    	
  //  	$('#ms-tagger-' + artefactId).empty();
    	$('#ms-tagger-' + artefactId).empty();
    	
  		var textToInsert =  '';
	
		$.blockUI({
			 message: '<h3>Please Wait...</h3>',
			css: { 
				top:  ($(window).height() - 20) /2 + 'px' 
			}
		});
		
		$.post("MyShowcaseGetShowcaseEvidenceMappingsController.json", {artefactId: artefactId}, function(list) {

			
				textToInsert += ' <div class="ms-tagmap-contents">';
				textToInsert += '<p>Below are displayed the maps you allocated to this piece of evidence in your evidence stream.';
				textToInsert += 'Untick the boxes if you wish to remove any that you wish to disassociate with this evidence in your final template.</p>';
				
				textToInsert += '<div class="mapholder">';
				textToInsert += '<ul>';	
			 
		    	$.each(list, function(index, data) { 
		    		
		    		textToInsert += '<li id="comp-';
		    		textToInsert += data.artefactMappingId;
		    		textToInsert += '" style="position:relative;"><span class="file">';
		    		textToInsert += data.competency;
		    		textToInsert += ' : ';
		    		textToInsert += data.mapping;
		    		textToInsert += '<input type="checkbox" checked="checked" /></span></li>';
	    		
		    	});
				
		    	textToInsert += '</ul>';
				
				textToInsert += '<div class="ms-tagmap-close">';
				textToInsert += '<a href="#" onclick="WizardDetails.mapsClose(';
				textToInsert += artefactId;
				textToInsert += ');" ><span>Close</span></a>';
			    textToInsert += '</div>';
					
	            textToInsert += '</div>';
	        
	            $('#ms-tagger-' + artefactId).append(textToInsert);
	        
	            $('#ms-tagmap-' + artefactId).slideDown();
	            
				$.unblockUI();
				
				MyShowcase.resize();
				
	            return false;			
					
		 });   		

/*		
		textToInsert += '<div class="mapholder">';
				textToInsert += '<ul>';
					textToInsert += '<li id="comp-0003" style="position:relative;"><span class="file">I know how much coffee to add<input type="checkbox" checked="checked" /></span></li>';
			textToInsert += '</ul>';

		textToInsert += '</div>';
*/




/*	  
    	  
    	  
    	  
    	  textToInsert += '<h4 class="tag">Tag this artefact:</h4>';
    	  textToInsert += '<p>Tags are words that you associate with this artefact. ';
    	  textToInsert += 'They are helpful as you can search for items later using the tags you have provided!</p>';
    	  textToInsert += '<ul id="ms-mytags-';
    	  textToInsert += artefactId;
    	  textToInsert += '" class="tagit" >';
    	  textToInsert += '</ul>';

    	  $('#ms-tagger-' + artefactId).append(textToInsert);

				var ownerId = $("#owner.ownerId").val();
				
				 $.post("MyShowcaseGetCompetenciesForStudentWSController.json", {ownerIdentifier: ownerId}, function(list) {

				    	textToInsert =  '';
						textToInsert += '<p>Select an element in one of your frameworks to tag the evidence against it.';
			    		textToInsert += '<p>Select one of your frameworks:<select name="competencyMapping" id="competencyMapping-' + artefactId + '" onchange="MyShowcaseArtefact.loadCompetenciesForMappingEdit(' + artefactId + ');"">';
				    	textToInsert += '<option selected="selected" value="default">Select type to search by</option>';		        
				    	
				    	$.each(list, function(index, data) { 
				    		
				    		textToInsert += '<option value="';
				    		textToInsert += data.param;
				    		textToInsert += '">';
				    		textToInsert += data.val;
				    		textToInsert += '</option>';
				    		
				    	});
				    	
						textToInsert += '</select>';

//				    	$('#ms-findby-mapping-dropdown').append(textToInsert);
						textToInsert += '<div id="ms-tagmap-list-' + artefactId + '" class="ms-tagmap-list">';
						textToInsert += '</div>';
						
						textToInsert += '<div class="ms-tagmap-close">';
						textToInsert += '<a href="#" onclick="WizardDetails.mapsClose(';
						textToInsert += artefactId;
						textToInsert += ');" ><span>Close</span></a>';
						textToInsert += '</div>';
						$('#ms-tagger-' + artefactId).append(textToInsert);
				    	
				 });    

		    	$('#ms-tagmap-' + artefactId).slideDown();
		    	return false;
		     	
	      });
*/
	
	
    }),
    
    
    mapsClose: (function(artefactId){

    	 
    	$('#ms-tagmap-' + artefactId).slideUp(); return false;
    	
    	$('#ms-tagger-' + artefactId).empty();

    	MyShowcase.resize();
    })	

};		
