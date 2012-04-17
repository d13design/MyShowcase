
$(document).ready(function() {

//	
// Set Up Details page 
//	
   
    var showcaseId = $("#showcaseId").val();
    	
    var ownerId = $("#ownerId").val();	
    
	WizardReviewers.list();	

//+++++++++++++ Functions and event management ++++++++++++++++++++++++++	

	
	$("#ms-wizard-reviewers-next").click(function(){
		
		if (WizardReviewers.getReviewersCount() > 0){
			MyShowcaseBuildShowcaseWizard.navigation("next");
		}
		else
		{
			WizardReviewers.addMessage("You must enter at least one showcase reviewer.");
		}
	
	});
	
	
});

var WizardReviewers = {
	 
	list: (function(){

	    var showcaseId = $("#showcaseId").val();
    	var ownerId = $("#ownerId").val();	

		$.post("MyShowcaseShowcaseReviewersListController.json", {ownerId: ownerId,
			                                                      showcaseId: showcaseId}, function(list) {
 	    	
			
        	$('#rev-form-contents').empty();                                                    	  
        	var count = 0;
				                                        		    	
	    	$.each(list, function(index, artefact){
	    		count = count +1; 
	    	});
			
	    	WizardReviewers.setReviewersCount(count);
	    	
	    	
	    	WizardReviewers.renderNewReviewer();

		    $.each(list, function(index, showcaseReviewer) {
			    	
			    	WizardReviewers.renderReviewer(showcaseReviewer);
//		    		alert("in Loop name: " + showcaseReviewer.name);
//		    		alert("in Loop email: " + showcaseReviewer.email);
//		    		alert("in Loop id: " + showcaseReviewer.showcaseReviewerId);
		    		
	    	});
			
		    MyShowcase.resize();
		}); 
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
	   
    validateNewReviewer: (function(){
	    
    	WizardReviewers.emptyMessages();
    	
    	var name = $("#newReviewerName").val();
        var email = $("#newReviewerEmail").val();
        
        var isValid = true;
        
        if (name.length == 0){
 
        	isValid = false;
        	WizardReviewers.addMessage("You must enter the name of the reviewer.");
        }	
        
        if (email.length == 0){

        	isValid = false;
        	WizardReviewers.addMessage("You must enter an email address for the reviewer.");
       	
        } else {
        
        	if(WizardReviewers.validateEmail(email) == false )
        		isValid = false;
        		WizardReviewers.addMessage("You email address you have entered is not in the correct format.");
        	;
        }
        
        return isValid;
	    	
    }), 
    

   	validateEmail: (function(str) {

   			var at="@"
   			var dot="."
   			var lat=str.indexOf(at)
   			var lstr=str.length
   			var ldot=str.indexOf(dot)
   			
   			if (str.indexOf(at)==-1){
 
   			   return false
   			}

   			if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
  
   			   return false
   			}

   			if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){

   			    return false
   			}

   			 if (str.indexOf(at,(lat+1))!=-1){

   			    return false
   			 }

   			 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){

   			    return false
   			 }

   			 if (str.indexOf(dot,(lat+2))==-1){

   			    return false
   			 }
   			
   			 if (str.indexOf(" ")!=-1){

   			    return false
   			 }

   	 		 return true;					
   		}),  	

   		
	addReviewer: (function(){

		if (WizardReviewers.validateNewReviewer() == true){
			
			WizardReviewers.emptyMessages();			
			
			var name = $("#newReviewerName").val();
			var email = $("#newReviewerEmail").val();
			var reviewerId = "";
    	
			WizardReviewers.maintainReviewers("add",name,email,null);
		}

	}),		

	
	removeReviewer: (function(reviewerId){

		var nameId = "#reviewer" + reviewerId + "name";
		var emailId = "#reviewer" + reviewerId + "email";
		var name = $(nameId).val();
    	var email = $(emailId).val();
    	var reviewerId = "";
    	
    	WizardReviewers.maintainReviewers("remove",name,email,reviewerId);
    	
	}),			
	
	
	maintainReviewers: (function(action,revName,revEmail,revId){

	    var showcaseId = $("#showcaseId").val();
 	    var ownerId = $("#ownerId").val();
 	    
		var name = revName;
    	var email = revEmail;
    	var reviewerId = revId;
    	
		$.post("MyShowcaseMaintainShowcaseReviewersController.json", {ownerId: ownerId,
																	  showcaseId: showcaseId,
																	  reviewerName: name,
				                                                      reviewerEmail: email,
				                                                      reviewerId: reviewerId,
																	  action: action}, function(list) {
																		  
				WizardReviewers.list();
		 }); 
	}),	

    
	renderNewReviewer: (function(showcaseReviewer){
		
		var textToInsert =  '';	
		textToInsert += '<div class="formrow" id="newReviewer">';				
		textToInsert += '<input id="newReviewerName" type="text" name="name" value=""/>';
		textToInsert += '<input id="newReviewerEmail" input type="text" name="email" value=""/>';
		textToInsert += '<a class= "" onclick="WizardReviewers.addReviewer(); return false;">';
		textToInsert += '<img src="img/showcaser-section/reviewer-add.png" alt="add reviewer row"/></a>';
		textToInsert += '</div>';

		$('#rev-form-contents').append(textToInsert);		
	}),
	
   
	renderReviewer: (function(showcaseReviewer){
	
		var textToInsert =  '';	
		textToInsert += '<div class="formrow" id="reviewer';
		textToInsert += showcaseReviewer.reviewerId;
		textToInsert += '">';				
		textToInsert += '<input id="reviewer';
		textToInsert += showcaseReviewer.reviewerId;
		textToInsert += 'name" type="text" name="name" value="';
		textToInsert += showcaseReviewer.name;
		textToInsert += '" DISABLED/>';
		textToInsert += '<input id="reviewer';
		textToInsert += showcaseReviewer.reviewerId;
		textToInsert += 'email" type="text" name="email" value="';
		textToInsert += showcaseReviewer.email;
		textToInsert += '" DISABLED/>';
		textToInsert += '<a class= "" onclick="WizardReviewers.removeReviewer(';
		textToInsert += showcaseReviewer.reviewerId;
		textToInsert += '); return false;">';
		textToInsert += '<img src="img/showcaser-section/reviewer-delete.png" alt="remove reviewer row"/></a>';
		textToInsert += '</div>';

		$('#rev-form-contents').append(textToInsert);
		
	}),	
	
	setReviewersCount: (function(count){
	  	
		$('#reviewersCount').val(count); 

	}),	   

     
	getReviewersCount: (function(){
	
		var count = $('#reviewersCount').val();
	    		
	   	return  parseInt(count);
	})		
	
	
};	

