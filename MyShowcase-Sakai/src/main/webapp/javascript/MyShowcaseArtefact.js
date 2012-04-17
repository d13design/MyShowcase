	var savedTitleText ;
	var savedBodyText ;

	var MyShowcaseArtefact = {

		    
	   maintainShowcaseOptions: (function(){
		   
		   		MyShowcaseArtefact.showcaseOptions();
		    	
				$('#ms-view-showcase-buttons').empty();
				$('#ms-view-showcase-buttons').append(MyShowcaseArtefact.showcaseOptions());
			    
		}),			

	    
		showcaseOptions: (function(){
			
			var count = MyShowcaseArtefact.getMyShowcaseArtefactCount();

			var ownerId = $("#ownerId").val();
			
			var textToInsert = '';
			
			textToInsert += '<p>';
			
			if (count > 0){
				
				textToInsert += '<a href="MyShowcaseBuildShowcaseController.htm?ownerId=';
				textToInsert += ownerId;
				textToInsert += '">';
				textToInsert += '<img src="img/showcaser-section/build_showcase-button.png"/>';
				textToInsert += '</a>';
			};
			
			textToInsert += '<a href="MyShowcaseShowcaseHistoryController.htm?ownerId=';
			textToInsert += ownerId;
			textToInsert += '">';
			textToInsert += '<img src="img/showcaser-section/showcase-history-button.png"/>';
			textToInsert += '</a>';
			
			if (count > 0){
				
				textToInsert += '<input type="image" id="ms-clear-evidence-button" src="img/showcaser-section/clear-evidence-button.png" />';
			};
			
			textToInsert += '</p>';
			
			return textToInsert;
			    	
//			$('#ms-view-showcase-buttons').empty();
//			$('#ms-view-showcase-buttons').append(MyShowcaseArtefact.showcaseOptions);
				    
		}),
			
	    addingArtefact: (function(){
	    	
	    	alert("addingArtefact");
	    }),
	    
	    edit: (function(artefactId){
	    	
		    $.post("MyShowcaseGetArtefactController.json", {artefactId: artefactId}, function(artefact) {
		    	
		    	var artefactType = artefact.type.name;
		    	
				MyShowcaseArtefact.hideArtefactActions(artefactId);
				MyShowcaseArtefact.showArtefactEditActions(artefactId);
				MyShowcaseArtefact.editTitleText(artefactId);
				
				if (artefactType != "Twitter") {
					MyShowcaseArtefact.editBodyText(artefactId);
				}
		    });
	    }),
	    
	    editTitleText: (function(artefactId){

			var titleText = $('#ms-post-title-' + artefactId).html() ;
			
			savedTitleText = titleText;
			
			titleText = titleText.replace("<p>", "");
			
			titleText = titleText.replace("</p>", "");
			
			var editTitleText = '<p><input id="edit-title-' + artefactId + '" type="text" value="' + titleText + '"/></p>' ;
			
			$('#ms-post-title-' + artefactId).empty();
			
			$('#ms-post-title-' + artefactId).append(editTitleText);
			

	    }),

	    
	    editBodyText: (function(artefactId){
	    	
			var bodyText = $('#ms-post-bodytext-' + artefactId).html();
			
			var bodyValue = $('#ms-post-bodytext-' + artefactId).val();
			
			savedBodyText = bodyText;
			
			bodyText = bodyText.replace("<p>", "");
			
			bodyText = bodyText.replace("</p>", "");
			
			var editBodyText = '<p><textarea id="edit-bodytext-' + artefactId + '" rows="5" cols="100" value=""/></textarea></p>' ;

			$('#ms-post-bodytext-' + artefactId).empty();
			
			$('#ms-post-bodytext-' + artefactId).append(editBodyText);
			
			$('#edit-bodytext-' + artefactId).val(bodyText);
			
	    }),
    
	    
	    cancelEdit: (function(artefactId){

			MyShowcaseArtefact.showArtefactActions(artefactId);
			MyShowcaseArtefact.hideArtefactEditActions(artefactId);
			MyShowcaseArtefact.cancelEditTitleText(artefactId);
			MyShowcaseArtefact.cancelEditBodyText(artefactId);
			
	    }),
	    
	    cancelEditTitleText: (function(artefactId){
	    	
			$('#ms-post-title-' + artefactId).empty();
		    $('#ms-post-title-' + artefactId).append(savedTitleText);
	    }),
	    
	    cancelEditBodyText: (function(artefactId){
	    	
			$('#ms-post-bodytext-' + artefactId).empty();
			$('#ms-post-bodytext-' + artefactId).append(savedBodyText);
		    
	    }),
	    
	    update: (function(artefactId) {

	    	var title = $("#edit-title-" + artefactId).val() ;
	    	var description = $("#edit-bodytext-" + artefactId).val() ;
	    	var validUpdate = true;
	    	
	    	if (MyShowcaseArtefact.containsHtmlTag(title) || MyShowcaseArtefact.containsHtmlTag(description)){
	    		validUpdate = false;
	    		alert("Invalid artefact edit. The amended text cannot contain HTML tags (combination of '<' and '>' characters)");
	    	}	
	    	
	    	if (validUpdate){
	    		$.post("MyShowcaseEditArtefactController.json", {artefactId: artefactId, title: title, description: description}, function(list) {
		    	
	    			MyShowcaseArtefact.showArtefactActions(artefactId);
	    			MyShowcaseArtefact.hideArtefactEditActions(artefactId);
	    			$('#ms-post-title-' + artefactId).empty();
	    			$('#ms-post-title-' + artefactId).append('<p>' + title + '</p>');
	    			$('#ms-post-bodytext-' + artefactId).empty();
	    			$('#ms-post-bodytext-' + artefactId).append('<p>' + description + '</p>');
	    		});	
	    	}
	    }),

	    
	    containsHtmlTag: (function(value) {

	    	var containsTag = false;
	    	
	    	if ((value.indexOf("<")!=-1) && (value.indexOf(">")!=-1)){
	    		
	    		if ((value.indexOf("<")) < (value.indexOf(">"))){

	    			containsTag = true;
	    		}
	    	}
	    		
	    	return containsTag;
	    }),	    
	
	    
	    remove: (function(artefactId){
	    	
	    	var r = confirm("Are you certain you want to delete this artefact?");
	    	
	    	if (r==true){
	    		
			    $.post("MyShowcaseArtefactRemoveController.json", {artefactId: artefactId}, function(list) {
			    	
			    	// remove artefact from current list
				    $('#ms-post-' + artefactId).remove();
				    
				    MyShowcaseArtefact.loadArtefacts();
			    
			    });
	    	}
	    }),

	    
	    tagMap: (function(artefactId){
	    	
	    	$('#ms-tagger-' + artefactId).empty();
	    	  
	    	  var textToInsert = '';
    	   
	    	  textToInsert += '<h4 class="tag">Tag this artefact:</h4>';
	    	  textToInsert += '<p>Tags are words that you associate with this artefact. ';
	    	  textToInsert += 'They are helpful as you can search for items later using the tags you have provided!</p>';
	    	  textToInsert += '<ul id="ms-mytags-';
	    	  textToInsert += artefactId;
	    	  textToInsert += '" class="tagit" >';
	    	  textToInsert += '</ul>';
	    	  textToInsert += '<br>&nbsp;</br>';

	    	  $('#ms-tagger-' + artefactId).append(textToInsert);
	    	  
		        
		      $.post("MyShowcaseGetArtefactTagsController.json", {artefactId: artefactId}, function(list) {
		    	  
		    	  var artefactTagsList = new Array();
			      
		    	  $.each(list, function(index, tag) { 
			    		
				    	artefactTagsList[index]=tag;

			      });
			        
			    	$('#ms-mytags-' + artefactId).tagit(artefactId,artefactTagsList,"open",{
			    	  
			    	   availableTags: ["java", "php"]
			    	});  

					var ownerId = $("#owner.ownerId").val();
					
					$.blockUI({
						 message: '<h3>Please Wait...</h3>',
						css: { 
							top:  '20px' 
						}
					});
					 $.post("MyShowcaseGetCompetenciesForStudentWSController.json", {ownerIdentifier: ownerId}, function(list) {

					    	textToInsert =  '';
					    	textToInsert += '<h4 class="map">Map this artefact to your frameworks:</h4>';
							textToInsert += '<p>Mapping artefacts helps to create relationships between your items and your frameworks - maybe this artefact demonstrates your abilities in a particular competency or maybe it relates to a specific assessment criteria, for example.</p>';
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

//					    	$('#ms-findby-mapping-dropdown').append(textToInsert);
							textToInsert += '<div id="ms-tagmap-list-' + artefactId + '" class="ms-tagmap-list">';
							textToInsert += '</div>';
							textToInsert += '<div class="ms-tagmap-close">';
							textToInsert += '<a href="#" onclick="MyShowcaseArtefact.tagMapClose(';
							textToInsert += artefactId;
							textToInsert += ');" ><span>Close</span></a>';
							textToInsert += '</div>';
							$('#ms-tagger-' + artefactId).append(textToInsert);
							$.unblockUI();
							
							MyShowcase.resize();
					 });    

			    	$('#ms-tagmap-' + artefactId).slideDown();
			    	
			    	MyShowcase.resize();
			    	
			    	return false;
			     	
		      });
    	
	    }),
	    
	    
	    tagMapClose: (function(artefactId){
  
	    	 
	    	$('#ms-tagmap-' + artefactId).slideUp(); return false;
	    	
	    	$('#ms-tagger-' + artefactId).empty();

	    	
	    }),	
	    
	    
	    addToShowcase: (function(artefactId){
	    	
	    	var ownerId = MyShowcase.getOwnerId();
	    	
		    $.post("MyShowcaseAddArtefactToShowcaseController.json", {artefactId: artefactId, ownerId: ownerId}, function(list) {
		    	
		    	MyShowcaseArtefact.setMyShowcaseArtefactCount();
		    	MyShowcaseShowcaseSlider.loadShowcase();
		    	
		    	
		    });
    	
	    }),

	    deleteFromShowcase: (function(artefactId){
	    	
	    	var ownerId = MyShowcase.getOwnerId();
	    	
		    $.post("MyShowcaseDeleteArtefactFromShowcaseController.json", {artefactId: artefactId, ownerId: ownerId}, function(list) {
		    	
		    	MyShowcaseArtefact.setMyShowcaseArtefactCount();
		    	MyShowcaseShowcaseSlider.loadShowcase(); 
		    	
		    });
    	
	    }),
	    
	    
	    clearEvidenceFromShowcase: (function(){
	    	
	    	var showcaseId = $("#showcaseId").val();
	    	
		    $.post("MyShowcaseClearEvidenceController.json", {showcaseId:showcaseId}, function(list) {
		    	
		    	MyShowcaseArtefact.setMyShowcaseArtefactCount();
				MyShowcaseShowcaseSlider.loadShowcase() ;
				
		    });
        }),	    

        
	    setMyShowcaseArtefactCount: (function(){
	    	
	    	var ownerId = MyShowcase.getOwnerId();
	    	
		    $.post("MyShowcaseShowcaseArtefactsCounterController.json", {ownerId: ownerId}, function(resultsList) {
		    	
		    	var showcaseArtefactCount = 0;
		    	
		    	showcaseArtefactCount = resultsList[0];
			    	
		    	$('#ms-counter').text(showcaseArtefactCount);
		    	
		    	MyShowcaseArtefact.maintainShowcaseOptions();
		    });
        }),	    

	    setMyShowcaseEvidenceStreamCountCount: (function(count){
	    	
	    	$('#ms-evidence-stream-counter').text(count); 
	    }),	   

     
	    getMyShowcaseEvidenceStreamCountCount: (function(){
	
	    		var count = $('#ms-evidence-stream-counter').text();
	    		
		    	return  parseInt(count);
	    }),	

	    
	    getMyShowcaseArtefactCount: (function(){
	
	    		var count = $('#ms-counter').text();
	    		
		    	return  parseInt(count);
	    }),		    
	    
	    closeAllSliders: (function(){
	    	
	    	 MyShowcaseArtefact.closeCollectSlider();
	    	 MyShowcaseArtefact.closeTypeSlider();
	    	 MyShowcaseArtefact.closeTagSlider();
	    	 MyShowcaseArtefact.closeShowcaseSlider();
	    	 MyShowcaseArtefact.closeMappingSlider();
	    	 MyShowcaseArtefact.clearBuffer();
	    	 MyShowcase.resize();
	    }),

	    
	    closeCollectSlider: (function(){

	    	 $('#ms-collectibles').slideUp(400);
	    	 
	    	 MyShowcaseArtefact.clearBuffer();
	    	 
	    	 MyShowcase.resize();
	    	 
	    }),	    

	    
	    closeShowcaseSlider: (function(){
	    	
	    	 $('#ms-view-showcase').slideUp(400);
	    	 
	    	 MyShowcaseArtefact.clearBuffer();
	    	 
	    	 MyShowcaseArtefact.clearBuffer(); 
	    	 
	    	 MyShowcase.resize();
	    	 
	    }),	
	
	    
	    closeMappingSlider: (function(){
	    	
	    	 $('#ms-findby-mapping').slideUp(400);
	    	 
	    	 MyShowcaseArtefact.clearBuffer();
	    	 
	    	 MyShowcase.resize();
	    }),	
	
	    
	    closeTypeSlider: (function(){
	    	 
	    	 $('#ms-findby-type').slideUp(400);
	    	 
	    	 $("#ms-findby-type-dropdown").empty();
	    	 
	    	 $('#selectedSearchType').val("");
	    	 
	    	 MyShowcaseArtefact.clearBuffer(); 
	    	 
	    	 MyShowcase.resize();
	    }),	    
	    
	     
	    closeTagSlider: (function(){
	    	
	    	 $('#ms-findby-tag').slideUp(400);
	    		
	    	 $("#ms-findby-tag-dropdown").empty();
	    	 
	    	 $('#selectedSearchTag').val("");
	    	 
	    	 MyShowcaseArtefact.clearBuffer();
	    	 
	    	 MyShowcase.resize();
   	
	    }),
	
	    
	    openViewShowcaseSlider: (function(){
	    	
	    	 $('#ms-view-showcase').slideDown(400);
	    	 
	    	 MyShowcaseArtefact.renderBuffer(4);
	    	 
	    	 MyShowcase.resize();
	    	 
	    }),	    
	
	    
	    openCollectSlider: (function(){
	    	
	    	 $('#ms-collectibles').slideDown(400);
	    	 
	    	 MyShowcaseArtefact.renderBuffer(4);
	    	 
	    	 MyShowcase.resize();
	    	 
	    }),
	
	    
	    openMappingSlider: (function(){
	    	
	    	 $('#ms-findby-mapping').slideDown(400);
	    	 
	    	 MyShowcaseArtefact.renderBuffer(3);
	    	 
	    	 MyShowcase.resize();
	    	 
	    }),	    

	    
	    openTypeSlider: (function(){
	    	
	    	 $('#ms-findby-type').slideDown(400);
	    	 
	    	 MyShowcaseArtefact.renderBuffer(4);
	    	 
	    	 MyShowcase.resize();
	    	 
	    }),	    
	    
	     
	    openTagSlider: (function(){
	    	
	    	 $('#ms-findby-tag').slideDown(400);
	    	 
	    	 MyShowcaseArtefact.renderBuffer(4);    	 
	    	
	    	 MyShowcase.resize();

	    }),    
	    
	    
	     
	    openShowcaseSlider: (function(){
	    	
	    	 $('#ms-view-showcase').slideDown(400);
	    	 
	    	 MyShowcaseArtefact.renderBuffer(7);
	    	 
	    	 MyShowcase.resize(); 

	    }),

	    
	    
	    tagCloudStyle: (function(searchCountValue){
	    	
	    	var style = "";
	    	
	    	var searchCount = parseInt(searchCountValue);
	    	
	    	if (searchCount > 100){
	    	
	    	    style += 'style="font-size:3.5em;"';
	    	}
	    	else if (searchCount > 50){
	    		
	    		style += 'style="font-size:3.2em;"';
	    	}
	    	else if (searchCount > 20){
	    		
	    		style += 'style="font-size:2.7em;"';
	    	}
	    	else if (searchCount > 10){
	    		
	    		style += 'style="font-size:2.1em;"';
	    	}
	    	else if (searchCount > 5){
	    		
	    		style += 'style="font-size:1.6em;"';
	    	}
	    	else {
	    		
	    		style += 'style="font-size:1em;"';
	    	}
    	
	        return style;
	    }),  

	    
	    
	    resetEvidenceStream: (function(){
	    	
	    	var chainName = $('#ms-tc-name').text();
	    	
	    	if (chainName != " "){
	    		
	    		MyShowcaseArtefact.setCurrentPageNo(1);
	    		
	    		MyShowcaseArtefact.setChain(" "," ");
	    	
	    		MyShowcaseArtefact.loadArtefactList(null,null,null,null,null,null,null,null);
	    	}
    		
    	    MyShowcaseArtefact.closeAllSliders();
    	    //clear any search content
    	    $("#ms-search-term").val("Search");
	    }),  
	     
	    resetSearchTerm: (function(){
	    	
    	    $("#ms-search-term").val("Search");
    	    MyShowcaseArtefact.setChain(" "," ");
    	    
	    }),

	    setChain: (function(name,value){
	    	
			$("#ms-tag-chain").empty();
		
			textToInsert =  '';	
						
			textToInsert += '<p><span id="ms-tc-name" class="ms-tc-cat">';
			textToInsert += name;
			textToInsert += '</span> <span id="ms-tc-tag" class="ms-tc-tag">';
			textToInsert += value;
			textToInsert += '</span><a id="ms-tc-exit" onclick="MyShowcaseArtefact.resetEvidenceStream();" href="#"><img src="img/ms-tag-chain-clear.png" /></a></p>';
	    	
			$('#ms-tag-chain').append(textToInsert);
	    }),	

	    
	    loadArtefactsForTag: (function(tag){
	    	
	    	$('#selectedSearchTag').val(tag);
	    	
// No longer required. Tag cloud count maintained in MyShowcaseService adding and removing an ArtefactTag.
// The count reflects the number of times a tag is applied to any artefact.	    	
//	    		
//		    $.post("MyShowcaseMaintainTagSearchCountController.json", {tagName: tag}, function(list) {
//	    
//		    });
   
		    MyShowcaseArtefact.resetSearchTerm();
		    
		    MyShowcaseArtefact.setCurrentPageNo(1);
		    
            MyShowcaseArtefact.loadArtefacts(null,null);
	    }),     

	    
	    loadArtefactsForType: (function(){

	    	var selectedType = $('#artefactSearchType').val();
	    	
	    	$('#selectedSearchType').val(selectedType);
	    	
	    	MyShowcaseArtefact.resetSearchTerm();
		    
	    	MyShowcaseArtefact.setCurrentPageNo(1);
	    	
		    MyShowcaseArtefact.loadArtefacts(null,null);

	    }), 
	    
	    loadArtefactsForMapping: (function(competency, id) {
	    	
	    	
	       	var selectedCompetencyVal = $('#competencyMapping').val();
	       	var selectedCompetencyText = $('#competencyMapping option:selected').text() ;

	       	MyShowcaseArtefact.resetSearchTerm();

	    	MyShowcaseArtefact.setCurrentPageNo(1);

	    	MyShowcaseArtefact.loadArtefacts(competency, id);
	    	MyShowcaseArtefact.setChain("Framework",selectedCompetencyText);
    	    MyShowcaseArtefact.closeAllSliders();	    	
	    }),	 
    	
	    
	    loadArtefactsForSearchTerm: (function(){
	    	
	    	var searchTerm = $("#ms-search-term").val();
	    	
	    	if ((searchTerm == "Search") || (searchTerm == "") || (searchTerm == null)){
	    		
	    		alert("You have not specified any value to search for. The search will be ignored.");
	    	}
	    	else
	    	{
		    	MyShowcaseArtefact.setCurrentPageNo(1);
		    	
			    MyShowcaseArtefact.loadArtefacts(null,null);
	
	    	};
	    }),
    
	    loadArtefacts: (function(competency, id){
	    	
		    var selectedType = $('#selectedSearchType').val();
		    var selectedTag = $('#selectedSearchTag').val();
	       	var selectedOrder = $("#artefactOrder").val();		    
	       	var selectedArtefactsPerPage = $("#artefactsPerPage").val();
	       	var searchTerm = $("#ms-search-term").val();
	       	
	       	var undefinedValue = "undefined";
	    	var defaultValue = "default";
	    	var chainName = " ";
	    	var chainValue = " ";
	    	var chainNameTag = "Tag";
	    	var chainNameType = "Type";
	    	var chainNameSearch = "Search";
	    	// A term serach overides all other settings
	    	if ((searchTerm == undefinedValue) || (searchTerm == defaultValue) || (searchTerm == "Search") || (searchTerm == "") || (searchTerm == null)){
	    		
	    		searchTerm = null;
	    	}
	    	else
	    	{
	    		
	    		selectedType = null;
	    		selectedTag = null;
	    		MyShowcaseArtefact.closeAllSliders();
	    		chainName = chainNameSearch;
	    		chainValue = searchTerm;	
	    	};
	    	
		    	
	    	if ((selectedType == undefinedValue) || (selectedType == defaultValue) || (selectedType == "") || (selectedTag == null)){
	    		
	    		selectedType = null;
	    	};
	    	
	    	if ((selectedTag == undefinedValue) || (selectedTag == defaultValue) || (selectedTag == "") || (selectedTag == null)){
	    		
	    		selectedTag = null;
	    	};
	    	
	    	if (selectedTag != null){
	    		
	    		chainName = chainNameTag;
	    		chainValue = selectedTag;
	    	}
	    	else if (selectedType != null){
	    		
	    		chainName = chainNameType;
	    		chainValue = selectedType;
	    	}
	    	else if (competency != null){
	    		
	    		chainName = "Competency";
	    		chainValue = competency;
	    	};
	    	
	    	MyShowcaseArtefact.setChain(chainName,chainValue);
	    	
	    	MyShowcaseArtefact.loadArtefactList(selectedTag,selectedType,selectedOrder,searchTerm,competency,id);
	    	
//	    	MyShowcaseArtefact.renderPagination();
		    	
	    }),     
	    
	    
	    loadArtefactList: (function(tagValue,typeValue,orderValue,searchTerm,competencyId,mappingId){

			var twitter = 'Twitter';
			var flickr = 'Flickr';
			var file = 'File';
			var weblink = 'Weblink';
			var pebblepad = 'PebblePad';
			var rss = 'Rss';
			var sakai = 'Sakai';
			
		   	var ownerId = $("#ownerId").val();
	    	
	    	MyShowcaseArtefact.clearArtefactList();

		    $.post("MyShowcaseArtefactListController.json", {ownerId: ownerId, order: orderValue, tag: tagValue, type: typeValue, searchTerm: searchTerm, competencyId: competencyId, mappingId: mappingId}, function(list) {
		    	
		    	// Determine pagination detail
		    	
		    	var count = 0;
		    	var startPoint = 0;
		    	var pointPoint = 0;
		    	var pageNo = MyShowcaseArtefact.getCurrentPageNo();
		    	var pageSize = MyShowcaseArtefact.getEvidenceStreamBlockSize(); 
		    	
		    	// minus one because index starts at zero.
		    	endPoint = (pageSize * pageNo) - 1;
		    	startPoint = (pageSize * pageNo) - pageSize;
		    	
		    	$.each(list, function(index, artefact){
		    		
		    		count = count +1; 
		    	});
    	
		    	$.each(list, function(index, artefact) { 
		    		
		    		if (index >= startPoint && index <= endPoint){ 

		    			if (artefact.type.name == twitter){
		    			
		    				MyShowcaseArtefact.renderArtefactTwitter(artefact);
		    			
		    			} else if (artefact.type.name == flickr){
		    			
		    				MyShowcaseArtefact.renderArtefactFlickr(artefact);
		    			
		    			} else if (artefact.type.name == rss){
		    			
		    				MyShowcaseArtefact.renderArtefactRss(artefact);
		    			
		    			} else if (artefact.type.name == file){
		    			
		    				MyShowcaseArtefact.renderArtefactFile(artefact);
		    			
		    			} else if (artefact.type.name == sakai){
		    			
		    				MyShowcaseArtefact.renderArtefactSakai(artefact);
		    			
		    			} else if (artefact.type.name == weblink){
		    			
		    				MyShowcaseArtefact.renderArtefactWeblink(artefact);
		    			
		    			} else if (artefact.type.name == pebblepad){
		    			
		    				MyShowcaseArtefact.renderArtefactPebblePad(artefact);
		    			}

		    		}
		    		
	    		
		    	});
    	
		    		MyShowcaseArtefact.setMyShowcaseEvidenceStreamCountCount(count);
		    		MyShowcaseArtefact.renderPagination();
//		    	MyShowcase.resizeIFrame();
		    		MyShowcase.resize();
	    		
    	
		    }); 
		    
	    }),
 

		
	    loadCompetencyList: (function(competencyValue, type, artefactId){

	    	var ownerId = $("#ownerId").val();
	    	var len = 0 ;
	    	
			$.blockUI({
				 message: '<h3>Please Wait...</h3>',
				css: { 
					top:  '20px' 
				}
			});
			
			$.post("MyShowcaseGetTreeWSController.json", {ownerId: ownerId, competencyId: competencyValue, returnType: type, artefactId: artefactId}, function(list) {
			   	
				$.unblockUI();
				
				textToInsert =  '';
			    	
			   	$.each(list, function(index, data) { 
			   		
			   		textToInsert += data.val;
			   		
			   	});
			   	
			   	len = textToInsert.length ;
			   	
			   	if (type == 'Finder') {
			   		
			   		if (len == 0)
						$('#ms-mapholder').hide();	
			   		else
						$('#ms-mapholder').show();
			   		
					$('#ms-mapholder').empty();
					
					$('#ms-mapholder').append(textToInsert);
					
			   	} else {
			   		if (len == 0)
						$('#ms-tagmap-list-' + artefactId).hide();	
			   		else
						$('#ms-tagmap-list-' + artefactId).show();
			   		
					$('#ms-tagmap-list-' + artefactId).empty();	
					
					$('#ms-tagmap-list-' + artefactId).append(textToInsert);
			   	}
				
			});
		}),
   

	    updateCompetency: (function(artefact, competency, id) {
	    	
	    	var idString = "#chk" + artefact + "-" + id ;
	    	
	    	var checked = $(idString).attr('checked') ;

			$.post("MyShowcaseUpdateArtefactMappingsController.json", {action: "update", artefactId: artefact, competencyId: competency, id: id, checked: checked}, function(list) {

			   	$.each(list, function(index, data) { 
			   		
//			   		alert(data.val);
			   		
			   	});
			});
	    }),
	    
	    loadCompetenciesForMappingFinder: (function(){
	    	
	       	var selectedCompetencyVal = $('#competencyMapping').val();
	       	var selectedCompetencyText = $('#competencyMapping option:selected').text() ;
	    	MyShowcaseArtefact.loadCompetencyList(selectedCompetencyVal, 'Finder', null);	    	
	    	
	    	MyShowcaseArtefact.setChain("Framework",selectedCompetencyText);
	    	
	    	MyShowcase.resize();
	    }),	    

	    
	    loadCompetenciesForMappingEdit: (function(artefactId){
	    	
	       	var selectedCompetency = $('#competencyMapping-' + artefactId).val();
	    	MyShowcaseArtefact.loadCompetencyList(selectedCompetency, 'Checkbox', artefactId);	    	
	    }),	    


	    
	    clearArtefactList: (function(){
	    	
			$('#ms-stream').empty();	
	  
	    }),   	    


	    
	    clearBuffer: (function(){
	    	
			$('#ms-buffer').empty();	
	  
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
			textToInsert += '<div id="ms-post-title-';
			textToInsert += artefact.artefactId;
			textToInsert += '" class="ms-post-title">';
			textToInsert += '<p>' +artefact.name + '</p>' ;
			textToInsert += '</div>';		
			textToInsert += '<p class="ms-post-metadata">Imported on ';
			textToInsert += dateCreated.toLocaleString();
			textToInsert += '<p>';
			textToInsert += '</div>';
			textToInsert += '<div class="ms-post-bodytext">';
			textToInsert += '<div id="ms-post-bodytext-';
			textToInsert += artefact.artefactId;
			textToInsert += '">';
			textToInsert += '<p>' + artefact.description + '</p>';
			textToInsert += '</div>';
			textToInsert += '<p class="ms-post-url"><a href="';
			textToInsert += artefact.artefactDetail.url;
			textToInsert += '" target="_blank">';
			textToInsert += artefact.artefactDetail.detail;
			textToInsert += '</a>';
			
			textToInsert += MyShowcaseArtefact.renderArtefactActions(artefact.artefactId);
			textToInsert += MyShowcaseArtefact.renderArtefactEditActions(artefact.artefactId);
			
			textToInsert += MyShowcaseArtefact.renderTagMapPanel(artefact.artefactId);				
			
	    	textToInsert += '</div>';		
			
			$('#ms-stream').append(textToInsert);
			MyShowcaseArtefact.showArtefactActions(artefact.artefactId);
			MyShowcaseArtefact.hideArtefactEditActions(artefact.artefactId);
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
			textToInsert += '<div id="ms-post-title-';
			textToInsert += artefact.artefactId;
			textToInsert += '" class="ms-post-title">';
			textToInsert += '<p>' +artefact.name + '</p>' ;
			textToInsert += '</div>';		
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
			textToInsert += '<div id="ms-post-bodytext-';
			textToInsert += artefact.artefactId;
			textToInsert += '">';
			textToInsert += '<p>' + artefact.description + '</p>';
			textToInsert += '</div>';
			textToInsert += '</div>';
			
			textToInsert += MyShowcaseArtefact.renderArtefactActions(artefact.artefactId);
			textToInsert += MyShowcaseArtefact.renderArtefactEditActions(artefact.artefactId);
			
			textToInsert += MyShowcaseArtefact.renderTagMapPanel(artefact.artefactId);				
			
	    	textToInsert += '</div>';		
			
			$('#ms-stream').append(textToInsert);
			MyShowcaseArtefact.showArtefactActions(artefact.artefactId);
			MyShowcaseArtefact.hideArtefactEditActions(artefact.artefactId);
		}),

		
		renderArtefactFile:  (function(artefact){
			
			var textToInsert =  '';
			var fileType = artefact.artefactDetail.fileType;
			
 			var ownerId = $("#ownerId").val();
			var userId = $("#userId").val();
			var dateCreated = new Date(artefact.createdDateTime);
			var fileStoreAddress = MyShowcaseArtefact.getFileStoreAddress();
			
			textToInsert += '<div id="ms-post-';
			textToInsert +=	artefact.artefactId;
			textToInsert += '" class="ms-post file file-';
			textToInsert += artefact.artefactDetail.fileType;
			textToInsert += '">';
			textToInsert += '<div class="ms-post-badge">';
			textToInsert += '<span>File:</span>';
			textToInsert += '</div>';
			textToInsert += '<div class="ms-post-contents">';
			textToInsert += '<div id="ms-post-title-';
			textToInsert += artefact.artefactId;
			textToInsert += '" class="ms-post-title">';
			textToInsert += '<p>' +artefact.name + '</p>' ;
			textToInsert += '</div>';		
			textToInsert += '<div class="ms-post-info-holder">';
			
			if ((fileType == "png") || (fileType == "jpg")){
				
				textToInsert += '<div class="mini-post-image">';
				textToInsert += '<img src="' + fileStoreAddress + ownerId + '/' + artefact.artefactDetail.fileName + '" alt="' + artefact.artefactDetail.url + '" width="268" height="201"/>';
				textToInsert += '</div>';
				
			} else {
				
				textToInsert += '<div class="ms-post-info-icon">';
				textToInsert += '<span>' + artefact.artefactDetail.fileType + '</span>';
				textToInsert += '</div>';				
			};
			

			
			textToInsert += '<div class="ms-post-info-text">';	
			textToInsert += '<p class="ms-post-file"><a href="';
//			textToInsert += '/filestore/' + ownerId + '/' + artefact.artefactDetail.fileName;
//			textToInsert += fileStoreAddress'/FileStore/MyShowcase/' + ownerId + '/' + artefact.artefactDetail.fileName;
			
			textToInsert += fileStoreAddress + ownerId + '/' + artefact.artefactDetail.fileName;
			
//			textToInsert += '" ">';
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
			textToInsert += '<div id="ms-post-bodytext-';
			textToInsert += artefact.artefactId;
			textToInsert += '">';
			textToInsert += '<p>' + artefact.description + '</p>';
			textToInsert += '</div>';
			textToInsert += '</div>';
			
			textToInsert += MyShowcaseArtefact.renderArtefactActions(artefact.artefactId);
			textToInsert += MyShowcaseArtefact.renderArtefactEditActions(artefact.artefactId);
			
			textToInsert += MyShowcaseArtefact.renderTagMapPanel(artefact.artefactId);				
			
	    	textToInsert += '</div>';		
			
			$('#ms-stream').append(textToInsert);
			MyShowcaseArtefact.showArtefactActions(artefact.artefactId);
			MyShowcaseArtefact.hideArtefactEditActions(artefact.artefactId);
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
			textToInsert += '<div id="ms-post-title-';
			textToInsert += artefact.artefactId;
			textToInsert += '" class="ms-post-title">';
			textToInsert += '<p>' +artefact.name + '</p>' ;
			textToInsert += '</div>';		
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
			textToInsert += '<div id="ms-post-bodytext-';
			textToInsert += artefact.artefactId;
			textToInsert += '">';
			textToInsert += '<p>' + artefact.description + '</p>';
			textToInsert += '</div>';
			textToInsert += '</div>';
			
			textToInsert += MyShowcaseArtefact.renderArtefactActions(artefact.artefactId);
			textToInsert += MyShowcaseArtefact.renderArtefactEditActions(artefact.artefactId);
			
			textToInsert += MyShowcaseArtefact.renderTagMapPanel(artefact.artefactId);				
			
	    	textToInsert += '</div>';		
			
			$('#ms-stream').append(textToInsert);
			MyShowcaseArtefact.showArtefactActions(artefact.artefactId);
			MyShowcaseArtefact.hideArtefactEditActions(artefact.artefactId);
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
			textToInsert += '<div id="ms-post-title-';
			textToInsert += artefact.artefactId;
			textToInsert += '" class="ms-post-title">';
			textToInsert += '<p>' +artefact.name + '</p>' ;
			textToInsert += '</div>';		
			textToInsert += '<p class="ms-post-quote">&ldquo;' + artefact.artefactDetail.detail + '&rdquo;</p>';
			textToInsert += '<p class="ms-post-metadata">Posted by <a href="http://www.twitter.com/' + artefact.artefactDetail.twitterUserName +'" target="_blank">' + artefact.artefactDetail.twitterUserName +'</a> on ';
			textToInsert += dateCreated.toLocaleString(); 
			textToInsert += '</p>';
			textToInsert += '</div>';
			
			textToInsert += MyShowcaseArtefact.renderArtefactActions(artefact.artefactId);
			textToInsert += MyShowcaseArtefact.renderArtefactEditActions(artefact.artefactId);
			
			textToInsert += MyShowcaseArtefact.renderTagMapPanel(artefact.artefactId);				
			
	    	textToInsert += '</div>';		
			
			$('#ms-stream').append(textToInsert);
			MyShowcaseArtefact.showArtefactActions(artefact.artefactId);
			MyShowcaseArtefact.hideArtefactEditActions(artefact.artefactId);
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
			textToInsert += '<div id="ms-post-title-';
			textToInsert += artefact.artefactId;
			textToInsert += '" class="ms-post-title">';
			textToInsert += '<p>' +artefact.name + '</p>' ;
			textToInsert += '</div>';		
			textToInsert += '<p class="ms-post-metadata">Drawn from <a href="http://www.flickr.com/" target="_blank">Flickr</a> on ';
			textToInsert += dateCreated.toLocaleString(); 		
			textToInsert += '</p>';
			textToInsert += '</div>';
			textToInsert += '<div class="ms-post-bodytext">';
			textToInsert += '<div id="ms-post-bodytext-';
			textToInsert += artefact.artefactId;
			textToInsert += '">';
			textToInsert += '<p>' + artefact.description + '</p>';
			textToInsert += '</div>';
			textToInsert += '<p>';
			textToInsert += artefact.artefactDetail.detail;
			textToInsert += '</p>';
			textToInsert += '<p>';
			textToInsert += '<img src="' + artefact.artefactDetail.url + '" onload="MyShowcase.resize();" alt="' + artefact.artefactDetail.url + '"/>';
			textToInsert += '</p>';
			textToInsert += '</div>';
			
			textToInsert += MyShowcaseArtefact.renderArtefactActions(artefact.artefactId);
			textToInsert += MyShowcaseArtefact.renderArtefactEditActions(artefact.artefactId);
			
			textToInsert += MyShowcaseArtefact.renderTagMapPanel(artefact.artefactId);				
			
	    	textToInsert += '</div>';		
			
			$('#ms-stream').append(textToInsert);
			MyShowcaseArtefact.showArtefactActions(artefact.artefactId);
			MyShowcaseArtefact.hideArtefactEditActions(artefact.artefactId);
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
			textToInsert += '<div id="ms-post-title-';
			textToInsert += artefact.artefactId;
			textToInsert += '" class="ms-post-title">';
			textToInsert += '<p>' +artefact.name + '</p>' ;
			textToInsert += '</div>';		
			textToInsert += '<div id="ms-post-bodytext-';
			textToInsert += artefact.artefactId;
			textToInsert += '">';
			textToInsert += '<p>' + artefact.description + '</p>';
			textToInsert += '</div>';
			textToInsert += '<p class="ms-post-metadata">Collected on ';
			textToInsert += dateCreated.toLocaleString();
			textToInsert += '</p>';
			textToInsert += '</div>';
			
			textToInsert += MyShowcaseArtefact.renderArtefactActions(artefact.artefactId);
			textToInsert += MyShowcaseArtefact.renderArtefactEditActions(artefact.artefactId);
			
			textToInsert += MyShowcaseArtefact.renderTagMapPanel(artefact.artefactId);				
			
	    	textToInsert += '</div>';		
			
			$('#ms-stream').append(textToInsert);
			MyShowcaseArtefact.showArtefactActions(artefact.artefactId);
			MyShowcaseArtefact.hideArtefactEditActions(artefact.artefactId);
		}),
	
		
		renderBuffer: (function(bufferSize){

			$('#ms-buffer').empty();
			
			var textToInsert =  '';
			
			var i=1;
			
			while (i <= bufferSize){
				
				textToInsert += '<p>&nbsp;</p>';
				
				i++;
		    }
			
			$('#ms-buffer').append(textToInsert);
			
			MyShowcase.resize();

		}),
		
		
		showArtefactActions: (function(artefactId){
			$("#ms-post-actions-" + artefactId).show() ;
		}),

		hideArtefactActions: (function(artefactId){
			$("#ms-post-actions-" + artefactId).hide() ;
		}),
		
		showArtefactEditActions: (function(artefactId){
			$("#ms-post-edit-actions-" + artefactId).show() ;
		}),

		hideArtefactEditActions: (function(artefactId){
			$("#ms-post-edit-actions-" + artefactId).hide() ;
		}),
		
		renderArtefactActions: (function(artefactId){
			
			var textToInsert =  '';

		    textToInsert += '<div id="ms-post-actions-' + artefactId + '" class="ms-post-actions">';
			textToInsert += '<ul>';
			textToInsert += '<li><a onclick="MyShowcaseArtefact.remove(';
			textToInsert += artefactId;
			textToInsert += ');return false;" id="artefactDelete" href="javascript: return null;" class="ms-post-action-delete">Delete</a></li>';
			
			textToInsert += '<li><a onclick="MyShowcaseArtefact.edit(';
			textToInsert += artefactId;
			textToInsert += ');return false;" href="javascript: return null;" class="ms-post-action-edit">Edit</a></li>';
			
			textToInsert += '<li><a onclick="MyShowcaseArtefact.addToShowcase(';
			textToInsert += artefactId;
			textToInsert += '); return false;" href="#" class="ms-post-action-add">Add to showcase</a></li>';
			
			textToInsert += '<li><a onclick="MyShowcaseArtefact.tagMap(';
			textToInsert += artefactId;
			textToInsert += ');return false;" href="javascript: return null;" class="ms-post-action-tag">Tag &amp; map</a></li>';
			
			textToInsert += '</ul>';
			textToInsert += '</div>';
	
			return textToInsert;
		}),

		renderArtefactEditActions: (function(artefactId){
			
			var textToInsert =  '';

		    textToInsert += '<div id="ms-post-edit-actions-' + artefactId + '" class="ms-post-edit-actions">';
			textToInsert += '<ul>';
			textToInsert += '<li><a onclick="MyShowcaseArtefact.cancelEdit(';
			textToInsert += artefactId ;
			textToInsert += ');return false;" id="artefactCancel" href="javascript: return null;" class="ms-post-edit-action-cancel">Cancel</a></li>';
			
			textToInsert += '<li><a onclick="MyShowcaseArtefact.update(';
			textToInsert += artefactId;
			textToInsert += ');return false;" href="javascript: return null;" class="ms-post-edit-action-collect">Collect</a></li>';
			
			
			textToInsert += '</ul>';
			textToInsert += '</div>';
	
			return textToInsert;
		}),
		
		

		renderTagMapPanel: (function(artefactId){
			
			var textToInsert =  '';

			textToInsert += '<div id="ms-tagmap-';
			textToInsert += artefactId;
			textToInsert += '" class="ms-tagmap" style="display:none;">';
			textToInsert += ' <div class="ms-tagmap-contents">';
		    textToInsert += '<div id="ms-tagger-';
	        textToInsert += artefactId;
	        textToInsert += '">';
	        textToInsert += '</div>';
			textToInsert += '</div>';
	    	textToInsert += '</div>';
			textToInsert += '<div id="ms-tagmap-';
			textToInsert += artefactId;
			textToInsert += '" class="ms-tagmap" style="display:none;">';
			textToInsert += ' <div class="ms-tagmap-contents">';
		    textToInsert += '<div id="ms-tagger-';
	        textToInsert += artefactId;
	        textToInsert += '">';
	        textToInsert += '</div>';
	
			return textToInsert;
		}),				
	    
	    
	    clearArtefactList: (function(){
	    	
	    	MyShowcaseArtefact.clearPagination();
	    	$('#ms-stream').empty();
		
	    }), 			

	    
		renderPagination: (function(){
			
			var evidenceStreamSize = MyShowcaseArtefact.getMyShowcaseEvidenceStreamCountCount();
            var currentPageNo = MyShowcaseArtefact.getCurrentPageNo();
			var selectedArtefactsPerPage = $("#artefactsPerPage").val();
			var lastPossiblePageNo = MyShowcaseArtefact.getLastPossiblePage();
			
			MyShowcaseArtefact.pagination(evidenceStreamSize,currentPageNo,selectedArtefactsPerPage,lastPossiblePageNo);

		}),				
	    
	    
		pagination: (function(evidenceStreamSize,currentPageNo,selectedArtefactsPerPage,lastPossiblePageNo){
			
			MyShowcaseArtefact.clearPagination();

			var textToInsert =  '';
			
			textToInsert += '<p>';
			
//			var evidenceStreamSize = MyShowcaseArtefact.getMyShowcaseEvidenceStreamCountCount();
//            var currentPageNo = MyShowcaseArtefact.getCurrentPageNo();
//			var selectedArtefactsPerPage = $("#artefactsPerPage").val();
//			var lastPossiblePageNo = MyShowcaseArtefact.getLastPossiblePage();
			
			if ( currentPageNo > 1){
				
				textToInsert += '<a href="#" onclick="MyShowcaseArtefact.paginationFirstPage();">&laquo;First</a>';
				textToInsert += '<a href="#" onclick="MyShowcaseArtefact.paginationPreviousPage();">&lt;Previous</a>';
				textToInsert += '<a href="#" onclick="MyShowcaseArtefact.paginationNewSelection(';
				textToInsert += currentPageNo - 1;
				textToInsert += ');return false;">';
				textToInsert += currentPageNo - 1;
				textToInsert += '</a>';
			
			}
			else {
				
				textToInsert += '<span>&laquo;First</span>';
				textToInsert += '<span>&lt;Previous</span>';
			};
			
			textToInsert += '<span class="selected">';
			textToInsert += currentPageNo;			
			textToInsert += '</span>';	
//			textToInsert += '<a href="#" onclick=MyShowcaseArtefact.paginationNewSelection(2);return false;">2</a>';
//			textToInsert += '<a href="#" onclick=MyShowcaseArtefact.paginationNewSelection(3);return false;">3</a>';
		
			
			if (currentPageNo >= lastPossiblePageNo){
				
				textToInsert += '<span>Next&gt;</span>';
				textToInsert += '<span>Last&raquo;</span>';
			}
			else
			{
				var count = 0;
				
				var insideLimits = true;
				
				while (insideLimits){
					
					textToInsert += '<a href="#" onclick="MyShowcaseArtefact.paginationNewSelection(';
					textToInsert += currentPageNo + 1;
					textToInsert += ');return false;">';
					textToInsert += currentPageNo + 1;
					textToInsert += '</a>';
										
					currentPageNo++;
					count++;
					
					if (currentPageNo > lastPossiblePageNo){
						
						insideLimits = false;
						
					};
					
					if (count > 10) {
						
						insideLimits = false;
						
					};
				};

				

				
				if (currentPageNo == lastPossiblePageNo){

					// output last page
					textToInsert += '<a href="#" onclick="MyShowcaseArtefact.paginationNewSelection(';
					textToInsert += lastPossiblePageNo;
					textToInsert += ');return false;">';
					textToInsert += lastPossiblePageNo;
					textToInsert += '</a>';
					// remove last and next links
					textToInsert += '<span>Next&gt;</span>';
					textToInsert += '<span>Last&raquo;</span>';	
					
				} else if (currentPageNo > lastPossiblePageNo){

					// remove last and next links
					textToInsert += '<span>Next&gt;</span>';
					textToInsert += '<span>Last&raquo;</span>';	
					
				} else if (currentPageNo <= (lastPossiblePageNo - 1)){
					

					
					textToInsert += '....';
					textToInsert += '<a href="#" onclick="MyShowcaseArtefact.paginationNewSelection(';
					textToInsert += lastPossiblePageNo;
					textToInsert += ');return false;">';
					textToInsert += lastPossiblePageNo;
					textToInsert += '</a>';					
					
					// make last and next links available
					textToInsert += '<a href="#" onclick=MyShowcaseArtefact.paginationNextPage();return false;">Next&gt;</a>';
					textToInsert += '<a href="#" onclick=MyShowcaseArtefact.paginationLastPage();return false;">Last&raquo;</a>';
				}	
				

			};
			
			textToInsert += '</p>';
			
			if(evidenceStreamSize > 0){
			
				$('#ms-pagination-top').append(textToInsert);
				$('#ms-pagination-bottom').append(textToInsert);
				MyShowcaseArtefact.setTopLink();
			}
			else {
				
				MyShowcaseArtefact.clearPagination();
				MyShowcaseArtefact.clearTopLink();
				// NB need to add a set method for this to set it up
			};

		}),				

	    
		getCurrentPageNo: (function(){
			
			var currentPageNo = parseInt($('#pageNo').val());
			return currentPageNo;
		}),	
	    
		getEvidenceStreamBlockSize: (function(){
			
			var currentPageNo = parseInt($('#artefactsPerPage').val());
			return currentPageNo;
		}),
		
	    
		getLastPossiblePage: (function(){
						
			var evidenceStreamSize = MyShowcaseArtefact.getMyShowcaseEvidenceStreamCountCount();
			var selectedArtefactsPerPage = $("#artefactsPerPage").val();
			
			return Math.ceil(evidenceStreamSize/selectedArtefactsPerPage);	
			
		}),
		
		setCurrentPageNo: (function(pageNo){
			
			$('#pageNo').val(pageNo);

		}),	
	    
		paginationNextPage: (function(){
			
		    var nextPageNo = (MyShowcaseArtefact.getCurrentPageNo() + 1);
		    
			MyShowcaseArtefact.setCurrentPageNo(nextPageNo);

			MyShowcaseArtefact.loadArtefacts();
		}),	
		
	    
		paginationPreviousPage: (function(){
			
			var previousPageNo = (MyShowcaseArtefact.getCurrentPageNo() - 1);

			MyShowcaseArtefact.setCurrentPageNo(previousPageNo);

			MyShowcaseArtefact.loadArtefacts();
			
		}),	
		
	    
		paginationFirstPage: (function(){
			
			MyShowcaseArtefact.setCurrentPageNo(1);

			MyShowcaseArtefact.loadArtefacts();
		}),	

	    
		paginationLastPage: (function(){
			
			MyShowcaseArtefact.setCurrentPageNo(MyShowcaseArtefact.getLastPossiblePage());

			MyShowcaseArtefact.loadArtefacts();

		}),			

	    
		paginationNewSelection: (function(page){
			
			MyShowcaseArtefact.setCurrentPageNo(page);
			
			MyShowcaseArtefact.loadArtefacts();
		}),		
		
		
	    clearPagination: (function(){
	    	
			$('#ms-pagination-top').empty();	
			$('#ms-pagination-bottom').empty();
			$('#ms-top-link').empty();
	    }),  	    
		
		
	    clearTopLink: (function(){
	    	
			$('#ms-top-link').empty();	
			
	    }),  					    
		
		
	    setTopLink: (function(){
	
//			MyShowcaseArtefact.clearTopLink();
			
			var textToInsert =  '';
			
//			textToInsert += '<p><a href="#">Go back to top(icon required)</a></p>';
			textToInsert += '<div id="ms-back-to-top">';
			textToInsert += '<a href="#" class="back-to-top"><img src="img/ms-back-to-top2.png" alt="top-of-page" width="42" height="36" /></a>';
			textToInsert += '</div>';

//			textToInsert += '<p><a href="#"><img src="img/ms-back-to-top.png" /></a></p>';
			$('#ms-top-link').append(textToInsert);
			
	    }),
	
	    
	    getFileStoreAddress: (function(){
	    	
	    	var fileStoreAddress = $("#fileStoreAddress").val();
	    	
	    	return fileStoreAddress;
	    	
		}),

		
	    fileDownload: (function(artefactId, artefactFilename) {
	    	
	    	alert("fileDownload Start");
	    	alert(artefactFilename);
		    $.post("MyShowcaseFileDownloadController.json", {artefactId: artefactId}, function(list) {
		    	alert("fileDownload Return 01");
		    	alert(list) ;
		    	alert("fileDownload Return 02a " + artefactFilename);
		    	var fh = fopen("C:\\Tomcat\\apache-tomcat-5.5.28\\webapps\\filestore\\e8be5767-aabd-495e-9ed0-2672d21a3a59\\" + artefactFilename, 3); // Open the file for writing
		    	alert("fileDownload Return 02b " + artefactFilename);
		    	alert("fh : " + fh) ;
		    	if(fh!=-1) {
			    	alert("fileDownload Return 02c " + artefactFilename);
		    	    fwrite(fh, list);
		    	    fclose(fh); 
		    	}
		    	alert("fileDownload Return 03");
		    });
	    	alert("fileDownload End");
	    	return false ;
	    })
	};	
	