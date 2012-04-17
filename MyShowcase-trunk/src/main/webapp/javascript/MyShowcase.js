	var MyShowcase = {
	     
  
		resize: (function(){
	    	
			
			// Sakai IFrame - special case 
	    	var iFrameId = $("#iframeId").val();
	    	
	    	if (iFrameId != "null"){
	    		
	    		if (iFrameId == "undefined"){
				}
				else {
					
					var word = iFrameId.split("'");
					
					setMainFrameHeight(word[1]);
				}
	    	}
	
	    	// All other Iframes
	    	var iFrameParent = MyShowcase.getIFrameParent();
	    	
	    	if (iFrameParent != "null") {
	    		
			    // What's the page height?
			     var height = document.body.scrollHeight;
			     
			     if (height < 800){
			    	
			    	 height = 800;

			     }	 
				     
			     // Going to 'pipe' the data to the parent through the helpframe..
			     var pipe = document.getElementById('ResizeHelperFrame');
			     
			     // Cachebuster a precaution here to stop browser caching interfering
			     pipe.src = iFrameParent + '?height=' + height + '&cacheb=' +Math.random();
			     
	    	}
	    	
	    	
		}),		
		 
		
	    getOwnerId: (function(){
	    	
	    	var ownerId = $("#ownerId").val();
	    	
	    	return ownerId;
	    	
		}),

		
	    getIFrameParent: (function(){
	    	
	    	var iFrameParent = $("#parent").val();
	    	
	    	return iFrameParent;
	    	
		}),
	
		
		loadCompetenciesForMappingFinder: (function(){
	    	
	       	var selectedCompetency = $('#competencyMapping').val();
	    	MyShowcaseArtefact.loadCompetencyList(selectedCompetency, 'Finder');	    	
	    	
	    	MyShowcaseArtefact.setChain("Competency",selectedCompetency);
	    }),	    

	    loadCompetenciesForMappingEdit: (function(){
	    	
	       	var selectedCompetency = $('#competencyMapping').val();
	    	MyShowcaseArtefact.loadCompetencyList(selectedCompetency, 'Checkbox');	    	
	    	
	    	MyShowcaseArtefact.setChain("Competency",selectedCompetency);
	    }),	    

	    maintainArtefactTags: function(artefactId,tag, action) {
	    	
	        $.post("MyShowcaseUpdateArtefactTagsController.json", {artefactId: artefactId, tag: tag, action: action}, function(list) {
	
			}); 
	        
	    },

	    
	    getExistingArtefactTags : function(artefactId) {
	        
	        var artefactTagsList = new Array();
	        
	        $.post("MyShowcaseGetArtefactTagsController.json", {artefactId: artefactId}, function(list) {
		        
		    	$.each(list, function(index, tag) { 

			    	artefactTagsList[index]=tag;

		    	});
	        }); 
	        
	        return artefactTagsList; 
	    }
	    
	    
};	

