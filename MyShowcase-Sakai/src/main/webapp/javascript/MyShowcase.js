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
