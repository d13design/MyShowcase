
$(document).ready(function() {

//	
// Set Up Details page 
//	
	
	WizardTemplate.templatesList();
	
	$("#ms-wizard-template-next").click(function(){

//		var messages=new Array(); 
		
		WizardTemplate.validateEligibleSelection();

	});	
	
	
//+++++++++++++ Functions and event management ++++++++++++++++++++++++++	

});

var WizardTemplate = {
	 
	templatesList: (function(){
		
		 $.post("MyShowcaseShowcaseThemeListController.json", {}, function(list) {
		    	
			    list.sort();
	    	
		    	$.each(list, function(index, theme) { 
		    		$('#ms-template-stream').append('<form>');
		    		WizardTemplate.renderTheme(theme);
		    		$('#ms-template-stream').append('</form>');
		    		
		    		MyShowcase.resize();
		    	});
		    	
		    	MyShowcase.resize();
		 }); 
	}),

	
    maintainShowcase: (function(){
    	
        var showcaseId = $("#showcaseId").val();
        var chosenTemplate = $("input[@name='chose-template']:checked").val();
        var action = "add";
        
	    $.post("MyShowcaseMaintainShowcaseController.json", {showcaseId: showcaseId,
	    													 themeId: chosenTemplate,
	    													 action: action}, function(list) {
	    														 
	    	 MyShowcaseBuildShowcaseWizard.navigation("next");
	    	 
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

   
   validateEligibleSelection: (function(){
	    
   	   WizardTemplate.emptyMessages();
      
       var showcaseId = $("#showcaseId").val();
       
       var chosenTemplate = $("input[@name='chose-template']:checked").val();
       
	   $.post("MyShowcaseValidateTemplateSelectionController.json", {showcaseId: showcaseId,
			 												themeId: chosenTemplate}, function(messages) {
		
	
		   if (messages.length == 0){
 														
        		WizardTemplate.maintainShowcase();
				MyShowcaseBuildShowcaseWizard.navigation("next");
           }
           else
           {
	    	    $.each(messages, function(index, message) { 
				    		
  		    	    messages[index]=message;
   				    WizardTemplate.addMessage(message);

	    	    });
           }		 													
       });
       
    	
   }),   
   
   
	renderTheme: (function(theme){
		
		var textToInsert =  '';	
		
		textToInsert += '<div id="chose-template-';
		textToInsert += theme.showcaseThemeId;
		textToInsert += '" class="chose-template">';
		
		textToInsert += '<div class="radio-button">';	
		textToInsert += '<input type="radio" ';
		
		if(theme.showcaseThemeId == $("#themeId").val()){
			
			textToInsert += 'Checked ';
		}
		
		textToInsert += 'name=chose-template value="';
		textToInsert += theme.showcaseThemeId;
		textToInsert += '" />';
		textToInsert += '</div>';
		
		textToInsert += '<div class="left-block">';
		textToInsert += '<div class="chose-template-image">';
		textToInsert += '<p class="image-title">';
		textToInsert += theme.name;
		textToInsert += '</p>';
		textToInsert += '<img src="img/showcaser-section/';
		textToInsert += theme.illustration;
		textToInsert += '" alt="preview" width="229" height="229" />';
		textToInsert += '</div>';
		textToInsert += '</div>';
		
		textToInsert += '<div class="right-block">';
		textToInsert += '<div class="chose-template-content">';
		textToInsert += '<p class="chose-template-title">This template would suite this and this and this</p>';
		textToInsert += '<p class="chose-template-description">';
		textToInsert += theme.usageDesc;
		textToInsert += '</p>'; 
		textToInsert += '</div>';
		textToInsert += '</div>';	
		textToInsert += '</div>';

    	
		$('#ms-template-stream').append(textToInsert);		
	})	
	

};	
