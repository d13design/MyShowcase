
$(document).ready(function() {

	MyShowcaseArtefact.loadArtefacts();	
	
	MyShowcaseArtefact.setMyShowcaseArtefactCount();

	MyShowcaseShowcaseSlider.loadShowcase(); 
	
	
//+++++++++++++ Functions and event management ++++++++++++++++++++++++++	
	
	
	$("#ms-search-term").blur(function(){

//		alert("search blur");

	});		

	
	$("#ms-search-term").focus(function(){
//	     alert("search focus");
	});		

	
	$("#ms-clear-evidence-button").click(function(){

		MyShowcaseArtefact.clearEvidenceFromShowcase();

	});		

	
	$("#closeAllSliders").click(function(){
	     MyShowcaseArtefact.closeAllSliders();
	});		

	
	$("#collectEvidence").click(function(){
	     MyShowcase.closeAllSliders();
	     MyShowcase.openCollectSlider();
	});	

	
	$("#ms-showcase").click(function(){
		
		 MyShowcaseArtefact.closeAllSliders();
		 
		 MyShowcaseArtefact.maintainShowcaseOptions();

	     MyShowcaseArtefact.openShowcaseSlider();
    
	});


	
	$("#ms-collection").click(function(){
		
		 MyShowcaseArtefact.closeAllSliders();
		 

		 var ownerId = MyShowcase.getOwnerId();
		 
		 $("#ms-collectibles-dropdown").empty();
		 
	     textToInsert =  '';

	     textToInsert += '<h3>Collect evidence from:</h3>';
	     textToInsert += '<p>';
	     
	     textToInsert += '<a id="fileupload" href="fileupload.htm?ownerId=';
	     textToInsert += ownerId;
	     textToInsert += '"   class="file">A file</a>' ;
	     
	     textToInsert += '<a id="url" href="url.htm?ownerId=';
	     textToInsert += ownerId;
	     textToInsert += '" class="link"> A link</a>' ;
	     
	     textToInsert += '<a id="rss" href="rss.htm?ownerId=';
	     textToInsert += ownerId;
	     textToInsert += '" class="rss">RSS feed</a>' ;
	     
	     textToInsert += '<a id="flickr" href="flickr.htm?ownerId=';
	     textToInsert += ownerId;
	     textToInsert += '" class="flickr" >Flickr</a>' ;
	     
	     textToInsert += '<a id="twitter" href="twitter.htm?ownerId=';
	     textToInsert += ownerId;
	     textToInsert += '" class="twitter" >Twitter</a>' ;
	     
	     textToInsert += '<a id="portfolio" href="portfolio.htm?ownerId=';
	     textToInsert += ownerId;
	     textToInsert += '" class="portfolio" >PebblePad</a>' ;
	     
	     textToInsert += '<a id="vle" href="vle.htm?ownerId=';
	     textToInsert += ownerId;
	     textToInsert += '" class="vle"  >Sakai</a>' ;
	     
	     textToInsert += '</p>' ;
	     
	     //textToInsert ='<a href="url.htm" id="url" class="link">A link</a>' ;
	     $('#ms-collectibles-dropdown').append(textToInsert);
	     
		 MyShowcaseFancyBox.init() ;

	     MyShowcaseArtefact.openCollectSlider();
    
	});	
	
	$("#ms-search-type").click(function(){
		
		 MyShowcaseArtefact.closeAllSliders();

		 var ownerId = MyShowcase.getOwnerId();
		
		 $("#ms-findby-type-dropdown").empty();
		 
		 $.post("MyShowcaseArtefactTypeListController.json", {ownerIdentifier: ownerId}, function(list) {
		    	
			    list.sort();
			 
		    	textToInsert =  '';
		    	textToInsert += '<h3>Find evidence by type:</h3>';
				textToInsert += '<p>Choose an evidence type to view all those items that you have added.';
	    		textToInsert += '<form id="ms-type-filter">';
	    		textToInsert += '<select name="artefactSearchType" id="artefactSearchType" onchange="MyShowcaseArtefact.loadArtefactsForType();">';
		    	textToInsert += '<option selected="selected" value="default">Select type to search by</option> ';		        
		    	
		    	$.each(list, function(index, type) { 
		    		
		    		textToInsert += '<option value="';
		    		textToInsert += type.name;
		    		textToInsert += '">';
		    		textToInsert += type.name;
		    		textToInsert += ' (';
		    		textToInsert += type.count;
		    		textToInsert += ')';
		    		textToInsert += '</option>';
		    		
		    	});
		    	
				textToInsert += '</select>';
				textToInsert += '</form>';	

		    	$('#ms-findby-type-dropdown').append(textToInsert);
		    	MyShowcaseArtefact.openTypeSlider();
		    	
		});    
     
	});

	$("#ms-search-tag").click(function(){

		 MyShowcaseArtefact.closeAllSliders();

		 var ownerId = MyShowcase.getOwnerId();
		 
		 $("#ms-findby-tag-dropdown").empty();
		 
		 $.post("MyShowcaseGetAllTagsController.json", {ownerIdentifier: ownerId}, function(list) {
			 
			    var tagname;
			    var count = 0;
			    
		    	textToInsert =  '';
		    	textToInsert += '<h3>Find evidence by tag:</h3>';
				textToInsert += '<p>Select a word to view the evidence that has been tagged with that word - the larger the word, the more popular the tag.</p>';
				textToInsert += '<p>';
				
		    	$.each(list, function(index, tag) { 
					
					count = count + 1;
					
		    		tagname = "'" + tag.name + "'";
					textToInsert += '<a href="#" onclick="MyShowcaseArtefact.loadArtefactsForTag(';
					textToInsert += tagname;
					textToInsert += ');return false;" ';
					textToInsert += MyShowcaseArtefact.tagCloudStyle(tag.searchCount);
					textToInsert += '>';
					textToInsert += tag.name;
					textToInsert += '</a>&nbsp;&nbsp;';
					
					if (count == 10){
						textToInsert += '</p><p>'; 
						count = 0;
					};

		    		
		    	});
		    	
		    	textToInsert += '</p>';
		    	
		    	$('#ms-findby-tag-dropdown').append(textToInsert);
		    	
		    	MyShowcaseArtefact.openTagSlider();
		    	
		 });    
    
	});		

	//	$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);

	$("#ms-search-mapping").click(function(){
		MyShowcaseArtefact.closeAllSliders();
		var ownerId = $("#owner.ownerId").val();
		
		 $("#ms-findby-mapping-dropdown").empty();
//		 $("#ms-findby-mapping-dropdown").height('70px');
		 $("#ms-mapholder").empty();
		 $("#ms-mapholder").height('auto');
		 $("#ms-mapholder").hide();
		 $.blockUI({
			 message: '<h3>Please Wait...</h3>',
			css: { 
				top:  '20px' 
			}
		 });
		 
		 $.post("MyShowcaseGetCompetenciesForStudentWSController.json", {ownerIdentifier: ownerId}, function(list) {

			 	
			 $.unblockUI();
			 
		    	textToInsert =  '';
		    	textToInsert += '<h3>Find evidence by mapping:</h3>';
				textToInsert += '<p>Select an element in one of your frameworks to view the evidence mapped against it.';
	    		textToInsert += '<form id="ms-tag-filter">';
	    		textToInsert += '<p>Select one of your frameworks:<select name="competencyMapping" id="competencyMapping" onchange="MyShowcaseArtefact.loadCompetenciesForMappingFinder();"">';
		    	textToInsert += '<option selected="selected" value="default">Select type to search by</option>';		        
		    	
		    	$.each(list, function(index, data) { 
		    		
		    		textToInsert += '<option value="';
		    		textToInsert += data.param;
		    		textToInsert += '">';
		    		textToInsert += data.val;
		    		textToInsert += '</option>';
		    		
		    	});
		    	
				textToInsert += '</select>';
				textToInsert += '</form>';	

		    	$('#ms-findby-mapping-dropdown').append(textToInsert);
		    	
		    	MyShowcaseArtefact.openMappingSlider();
		    	
				$("#ms-mapholder").show();
				
				MyShowcase.resize();
		    	
		 });    
	});	
    
    function clearArtefactList(){
    	
		$('#ms-stream').empty();	
  
    };   
    
    
    $("#artefactOrder").change(function(){
    	
    	MyShowcaseArtefact.setCurrentPageNo(1);
    	
    	MyShowcaseArtefact.loadArtefacts();	    	
   	
    });     
 
    
    $("#artefactsPerPage").change(function(){
    	
    	MyShowcaseArtefact.setCurrentPageNo(1);
    	
    	MyShowcaseArtefact.loadArtefacts();	    	
    	
    });     
   
    
    $("#getMyShowcaseData").click(function(){
		   
	    $ownerName = document.getElementById("ownerName").value;
	        
	    $.post("test.ajax", {ownerName:$ownerName}, function(xml) {
	    	
	       	$("#myShowcaseData").html($("data", xml).text());          
	    });
    }); 

	$("#ms-showcase").click(function(){
		MyShowcaseShowcaseSlider.loadShowcase() ;
	});		

	$("#scroller").mouseup(function() {
		MyShowcaseShowcaseSlider.mouseup() ;
	}) ;
	
	$("#left").click(function() {
		MyShowcaseShowcaseSlider.clickLeft() ;
	}) ;

	$("#right").click(function() {
		MyShowcaseShowcaseSlider.clickRight() ;
	}) ;
    
});

