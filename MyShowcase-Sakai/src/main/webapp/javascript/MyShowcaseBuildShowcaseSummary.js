

$(document).ready(function() {

//	
// Set Up Summary page 
//	

	WizardSummary.listReviewers();
		
	
//+++++++++++++ Functions and event management ++++++++++++++++++++++++++	

});


var WizardSummary = {
	 
	
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
    	
    	WizardSummary.clearChosenEvidence();   	
	
	    $.post("MyShowcaseShowcaseEvidenceListController.json", {showcaseId: showcaseId}, function(list) {
	    	
	    	$.each(list, function(index, artefact){
	    		
	    		count = count +1; 
	    	});
	
	    	var textToInsert =  '';
	    	textToInsert += '<div class="clear-line"><span>&nbsp;</span></div>';
			textToInsert += '<div id="heading8" class="showcaser-heading" >';
			textToInsert += '<h3><span>Summary Content</span></h3></div>';
			$('#ms-chosen-evidence-stream').append(textToInsert);
			
	    	$.each(list, function(index, artefact) { 

	    			if (artefact.type.name == twitter){
	    			
	    				WizardSummary.renderArtefactTwitter(artefact);
	    			
	    			} else if (artefact.type.name == flickr){
	    			
	    				WizardSummary.renderArtefactFlickr(artefact);
	    			
	    			} else if (artefact.type.name == rss){
	    			
	    				WizardSummary.renderArtefactRss(artefact);
	    			
	    			} else if (artefact.type.name == file){
	    			
	    				WizardSummary.renderArtefactFile(artefact);
	    			
	    			} else if (artefact.type.name == sakai){
	    			
	    				WizardSummary.renderArtefactSakai(artefact);
	    			
	    			} else if (artefact.type.name == weblink){
	    			
	    				WizardSummary.renderArtefactWeblink(artefact);
	    			
	    			} else if (artefact.type.name == pebblepad){
	    			
	    				WizardSummary.renderArtefactPebblePad(artefact);
	    			}
	    			
	    			MyShowcase.resize();
	    	});
  	
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

    	textToInsert += '</div>';		
		
		$('#ms-chosen-evidence-stream').append(textToInsert);	
	}),
	
	
	renderArtefactFlickr: (function(artefact){
		
		var textToInsert =  '';
		
		var dateCreated = new Date(artefact.createdDateTime);
//alert("renderArtefactFlick") ;
//alert(artefact.createdDateTime) ;
//alert(artefact.artefactDetail.url) ;
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

    	textToInsert += '</div>';		
		
		$('#ms-chosen-evidence-stream').append(textToInsert);
	}),

	
	
	listReviewers: (function(){

	    var showcaseId = $("#showcaseId").val();
    	var ownerId = $("#ownerId").val();	
    
 	
		$.post("MyShowcaseShowcaseReviewersListController.json", {ownerId: ownerId,
			                                                      showcaseId: showcaseId}, function(list) {

		    $.each(list, function(index, showcaseReviewer) {
			    	
	
			    WizardSummary.renderReviewer(showcaseReviewer);
		    });

		    WizardSummary.loadChosenEvidence();
		    
		 }); 
	}),	
	
	   
	renderReviewer: (function(showcaseReviewer){
	
		var textToInsert =  '';	
		
		textToInsert += '<div class="listrow" id="reviewer1">';
		textToInsert += '<ul>';
		textToInsert += '<li class="summary-reviwers-name"><p>';
		textToInsert += showcaseReviewer.name;
		textToInsert += '</p></li>';
		textToInsert += '<li class="summary-reviwers-email"><p>';
		textToInsert += showcaseReviewer.email;
		textToInsert += '</p></li>';
		textToInsert += '</ul>';
	    textToInsert += '</div>';

		$('#summary-reviewer-list').append(textToInsert);
		
	})
	

};
