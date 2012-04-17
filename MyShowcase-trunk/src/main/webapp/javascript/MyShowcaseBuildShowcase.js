
$(document).ready(function() {
	
	$("#ms-wizard-next").click(function(){
		 
    	MyShowcaseBuildShowcaseWizard.navigation("next");

	});

	
	$("#ms-wizard-previous").click(function(){
		
		MyShowcaseBuildShowcaseWizard.navigation("previous");
    	
	});	

	
	$("#ms-wizard-publish").click(function(){
		
		MyShowcaseBuildShowcaseWizard.publish();
    	
	});

	
	$("#ms-wizard-home").click(function(){
		
		MyShowcaseBuildShowcaseWizard.navigation("home");
    	
	});		
	
	$("#ms-home").click(function(){
		
		MyShowcaseBuildShowcaseWizard.navigation("home");
    	
	});	

});


var MyShowcaseBuildShowcaseWizard = {
	     
	    navigation: (function(direction){
	    	
	    	$('#direction').val(direction);

	    	$("#showcaseWizard").submit(); 
		    
	    }),

	    
	    publish: (function(direction){
	    	
	        var showcaseId = $("#showcaseId").val();
	        
		    $.post("MyShowcasePublishShowcaseController.json", {showcaseId: showcaseId}, function(list) {
		    														 
				 MyShowcaseBuildShowcaseWizard.navigation("next");
		    });	
		    
	    }),	

	    
	    test: (function(){
			
			alert("MyShowcaseBuildShowcaseWizard: test");
			
	    })  
    
};	