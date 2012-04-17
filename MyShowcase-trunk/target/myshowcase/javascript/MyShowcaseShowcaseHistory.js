
$(document).ready(function() {

//	
// Set Up Details page 
//	
	ShowcaseHistory.load();	

//+++++++++++++ Functions and event management ++++++++++++++++++++++++++	

});

var ShowcaseHistory = {
	 
	load: (function(){
		

		var ownerId = MyShowcase.getOwnerId();
		 $.post("MyShowcaseShowcaseListController.json", {ownerId: ownerId}, function(list) {
		    	
			    list.sort();
	    	
		    	$.each(list, function(index, showcase) { 

//		    		alert("in Loop id: " + item.showcaseId);
//		    		alert("in Loop name: " + item.showcaseDesc);
//		    		alert("in Loop Desc: " + item.showcaseDesc);
//		    		alert("in Loop illustration: " + item.illustration);
		    		
		    		ShowcaseHistory.renderShowcase(showcase);
		    		
		    		MyShowcase.resize();
		    		
		    	});
		    	
		    	  	
		    	
		 }); 
	}),

	
    viewShowcase: (function(showcaseId){
    	
    	var showcaseIdentifier = "#viewShowcase-" + showcaseId;    	

    	$(showcaseIdentifier).submit();   	
	    
    }),
	
    
    renderShowcase:  (function(showcase){
		
		var textToInsert =  '';
		
		var datePublished = ShowcaseHistory.renderDate(showcase.publishedDateTime);
		var dateReviewStartDate = ShowcaseHistory.renderDate(showcase.reviewStartDateTime);
		var dateReviewEndDate = ShowcaseHistory.renderDate(showcase.reviewEndDateTime);
		var now = new Date() ;
		var endDateTime = new Date(showcase.reviewEndDateTime) ;
		textToInsert += '<form id="viewShowcase-';
		textToInsert += showcase.showcaseId;
		textToInsert += '" name="viewShowcase-';
		textToInsert += showcase.showcaseId;
		textToInsert += '" action="MyShowcaseViewShowcaseController.htm" method="post" target="_blank">';
    	textToInsert += '<input type="hidden" name="showcaseId" id="showcaseId" value="';
		textToInsert += showcase.showcaseId;
		textToInsert += '">';
		textToInsert += '</form>';
		
		textToInsert += '<div class="history" id="history-';
		textToInsert += showcase.showcaseId;
		textToInsert += '">';

		textToInsert += '<div class="history-info-holder">';
		textToInsert += '<p class="history-published"> SHOWCASE PUBLISHED ';
		textToInsert += datePublished;
		textToInsert += '</p>';
		if (dateReviewStartDate != "")
			if (dateReviewEndDate != "") {
				if (endDateTime.getTime() > now.getTime())
					textToInsert += '<p class="history-active">';
				else
					textToInsert += '<p class="history-elapsed">';
				textToInsert += "Active between " + dateReviewStartDate + " and " + dateReviewEndDate;
				textToInsert += '</p>';
			} else {
				textToInsert += '<p class="history-active">';
				textToInsert += "Active from " + dateReviewStartDate;
				textToInsert += '</p>';
			}
		textToInsert += '</div>';

		textToInsert += '<div class="history-content-holder">';
		    textToInsert += '<div class="history-image">';	
		    textToInsert += '<img src="img/showcaser-section/';
		    textToInsert += showcase.illustration;
		    textToInsert += '" alt="preview" width="229" height="229" />';
		    textToInsert += '<ul class="buttons">';
		   	textToInsert += '<li><a id="ms-view-showcase-button" onclick="ShowcaseHistory.viewShowcase(';
		   	textToInsert += showcase.showcaseId;
		   	textToInsert += '); return false;"';
		   	textToInsert += ' href="#" class="ms-post-action-view">View</a></li>';
		   	// Commented out for now!
		   	textToInsert += '<li><!--a href="#" class="ms-post-action-reissue">Reissue</a--></li>';
		   	textToInsert += '</ul>';
		   	textToInsert += '</div>';
		
		   	textToInsert += '<div class="history-info">';	
		   	textToInsert +='<p class="history-info-title">';
		   	textToInsert += showcase.showcaseTitle;
		   	textToInsert += '</p>';	
		   	textToInsert += '<p class="history-info-body">';
		   	textToInsert += showcase.showcaseDesc;
		   	textToInsert += '</p>';	
		   	textToInsert += '</div>';

		   	// REVIEWERS
		   	textToInsert += '<div class="history-reviewers">';	
		   	textToInsert += '<p class="history-info-title">Reviewers</p>';	
		   	textToInsert += '<ul>';
		   	
		   	var arLen=showcase.showcaseReviewers.length;
		   	for ( var i=0, len=arLen; i<len; ++i ){

		   		if (showcase.showcaseReviewers[i].hasViewed == false){
		   			textToInsert += '<li class="not-seen"><span>';
		   			textToInsert += showcase.showcaseReviewers[i].name;
		   			textToInsert += '</span></li>';
		   		} else {
		   			textToInsert += '<li class="seen"><span>';
		   			textToInsert += showcase.showcaseReviewers[i].name;
		   			textToInsert += '</span></li>';
		   		}
		   	}
	   	
		   	textToInsert += '</ul>';
		   	textToInsert += '</div>';
		   	
		   	textToInsert += '<div style="clear:both;">&nbsp;</div>';
		textToInsert += '</div>';
		
		textToInsert += '</div>';
		
		$('#published-showcases').append(textToInsert);
	}),
	

	
	renderDate:  (function(date){
		
		if (date == "")
			return "" ;
		else {
			var m_names = new Array("January", "February", "March", 
					"April", "May", "June", "July", "August", "September", 
					"October", "November", "December");
	
			var d = new Date(date);
			var curr_date = d.getDate();
			var sup = "";
			if (curr_date == 1 || curr_date == 21 || curr_date ==31)
			   {
				   sup = "st";
			   }
			else if (curr_date == 2 || curr_date == 22)
			   {
				   sup = "nd";
			   }
			else if (curr_date == 3 || curr_date == 23)
			   {
				   sup = "rd";
			   }
			else
			   {
				   sup = "th";
			   }
	
			var curr_month = d.getMonth();
			var curr_year = d.getFullYear();
	
			var newDate = curr_date + sup + " " + m_names[curr_month] + " " + curr_year;
					
			return newDate;
		}

	})	
	
	

};	