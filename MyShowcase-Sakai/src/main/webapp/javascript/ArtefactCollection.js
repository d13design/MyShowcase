
$(document).ready(function() {
	
	$("#input2").focus(function(){
		
		if( ArtefactCollection.getName() == ArtefactCollection.getDefaultName() ) {
			ArtefactCollection.setName("");
		}
	
	});	

	$("#input2").blur(function(){
		
		if( ArtefactCollection.getName().length == 0) {
			ArtefactCollection.setName(ArtefactCollection.getDefaultName());
		}
	
	});	
	
	$("#input4").focus(function(){
		
		if( ArtefactCollection.getDescription() == ArtefactCollection.getDefaultDescription() ) {
			ArtefactCollection.setDescription("");
		}
	
	});	

	$("#input4").blur(function(){
		
		if( ArtefactCollection.getDescription().length == 0) {
			ArtefactCollection.setDescription(ArtefactCollection.getDefaultDescription());
		}
	
	});	


//+++++++++++++ Functions and event management ++++++++++++++++++++++++++	
	
});


var ArtefactCollection = {

    
    getDefaultDescription: (function(){
   	 
   	 var desc = "description here";
   	 
   	 return desc;
   	 
    }),   
 
   
   getDefaultName: (function(){
	   	 
	   	 var title = "name here";
	   	 
	   	 return title;
    }),
	   
	   
    setDescription: (function(desc){
    	 
    	 $('#input4').val(desc);
    }),
 
	   
    getDescription: (function(){
    	 
    	 return $('#input4').val();
    }),
    
    
    setName: (function(name){
   	 
   	 $('#input2').val(name);
   }),
   
   getName: (function(){
  	 
  	 return $('#input2').val();
   })

};	
