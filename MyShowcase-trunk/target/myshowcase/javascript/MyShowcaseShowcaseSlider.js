
var MyShowcaseShowcaseSlider = {

		resize: (function(n) {
			$("#scroller-contents").width(n * 318) ;
		}),
		
		left: (function(str, n){
			if (n <= 0)
			    return "";
			else if (n > String(str).length)
			    return str;
			else
			    return String(str).substring(0,n);
		}),
		
		right: (function(str, n){
		    if (n <= 0)
		       return "";
		    else if (n > String(str).length)
		       return str;
		    else {
		       var iLen = String(str).length;
		       return String(str).substring(iLen, iLen - n);
		    }
		}),
		
		tooBig: (function(str, n){
		    if (n <= 0)
		       return true;
		    else if (String(str).length > n)
		       return true;
		    else
		       return false;
		}),

		 enableDisableButtons: (function(p) {
			if (p == MyShowcaseShowcaseSlider.getMaxSlides())
				MyShowcaseShowcaseSlider.disableRight() ;
			else
				MyShowcaseShowcaseSlider.enableRight() ;

			if (p == 1)
				MyShowcaseShowcaseSlider.disableLeft() ;
			else
				MyShowcaseShowcaseSlider.enableLeft() ;
		}),
		
		clickLeft: (function() {
			var cs = MyShowcaseShowcaseSlider.getCurrentSlide() - 1  ;
			if (cs >= 1) {
				MyShowcaseShowcaseSlider.setCurrentSlide(cs) ;
				MyShowcaseShowcaseSlider.scrollLeft() ;
			}
			MyShowcaseShowcaseSlider.enableDisableButtons(cs) ;
		}),
		
		clickRight: (function() {
			var cs = MyShowcaseShowcaseSlider.getCurrentSlide() + 1  ;
			if (cs <= MyShowcaseShowcaseSlider.getMaxSlides()) {
				MyShowcaseShowcaseSlider.setCurrentSlide(cs) ;
				MyShowcaseShowcaseSlider.scrollRight() ;
			}
			MyShowcaseShowcaseSlider.enableDisableButtons(cs) ;
		}),
		
		disableLeft: (function() {
//			$("#left").attr('disabled', 'disabled') ;
		}),

		enableLeft: (function() {
//			$("#left").removeAttr('disabled') ;
		}),

		disableRight: (function() {
//			$("#right").attr('disabled', 'disabled') ;
		}),

		enableRight: (function() {
//			$("#right").removeAttr('disabled') ;
		}),
		
		getImagebarScrollPos: (function() {
			return $("#scroller").scrollLeft() ;
		}),

		scrollLeft: (function() {
			var ps = MyShowcaseShowcaseSlider.getImagebarScrollPos() ;
			$("#scroller").scrollLeft(ps-318) ;
			MyShowcaseShowcaseSlider.setImagebarScrollPos() ;
		}),

		scrollRight: (function() {
			var ps = MyShowcaseShowcaseSlider.getImagebarScrollPos() ;
			$("#scroller").scrollLeft(ps+318) ;
			MyShowcaseShowcaseSlider.setImagebarScrollPos() ;
		}),

		setImagebarScrollPos: (function() {
			$("#scrollPos").val($("#scroller").scrollLeft()) ;
		}),
		
		getCurrentSlide: (function() {
			return parseInt($("#currentSlide").val()) ;
		}),

		setCurrentSlide: (function(s) {
			$("#currentSlide").val(s) ;
		}),

		getMaxSlides: (function() {
			return parseInt($("#maxSlides").val()) ;
		}),

		setMaxSlides: (function (s) {
			$("#maxSlides").val(s) ;
		}),
		
		mouseup: (function () {
//			alert("Mouseup");
			var offset ;
			var scrollPos ;
			scrollPos = MyShowcaseShowcaseSlider.getImagebarScrollPos() ;
			offset = scrollPos % 318 ;
			if (offset > 159)
				$("#scroller").scrollLeft(scrollPos + 318 - offset) ;
			else
				$("#scroller").scrollLeft(scrollPos - offset) ;
			MyShowcaseShowcaseSlider.setImagebarScrollPos() ;
			MyShowcaseShowcaseSlider.setCurrentSlide(1+MyShowcaseShowcaseSlider.getImagebarScrollPos()/400) ;
			MyShowcaseShowcaseSlider.enableDisableButtons(MyShowcaseShowcaseSlider.getCurrentSlide()) ;
		}),

		loadShowcase: (function() {
	    	var showcaseId = $("#showcaseId").val();
	    	var count = 0;
			var twitter = 'Twitter';
			var flickr = 'Flickr';
			var file = 'File';
			var weblink = 'Weblink';
			var pebblepad = 'PebblePad';
			var rss = 'Rss';
			var sakai = 'Sakai';
			var focused = '' ;
			var finalCount ;

			
			MyShowcaseShowcaseSlider.clearSlider() ;
		    $.post("MyShowcaseShowcaseEvidenceListController.json", {showcaseId: showcaseId}, function(list) {


		    	$.each(list, function(index, artefact){
		    		
		    		count = count + 1 ;
		    	});
		    	finalCount = count ;

		    	
//				MyShowcaseShowcaseSlider.renderBlank();
		    	$.each(list, function(index, artefact) { 

/*
		    			if (count == 1)
		    				focused = ' focused' ;
		    			else
		    				focused = '' ;
		    			
		    			if (count == 1)
		    				alert(count + focused) ;
*/		    			
		    			if (artefact.type.name == twitter){
		    			
		    				MyShowcaseShowcaseSlider.renderArtefactTwitter(artefact,focused);
		    			
		    			} else if (artefact.type.name == flickr){
		    			
		    				MyShowcaseShowcaseSlider.renderArtefactFlickr(artefact,focused);
		    			
		    			} else if (artefact.type.name == rss){
		    			
		    				MyShowcaseShowcaseSlider.renderArtefactRss(artefact,focused);
		    			
		    			} else if (artefact.type.name == file){
		    			
		    				MyShowcaseShowcaseSlider.renderArtefactFile(artefact,focused);
		    			
		    			} else if (artefact.type.name == sakai){
		    			
		    				MyShowcaseShowcaseSlider.renderArtefactSakai(artefact,focused);
		    			
		    			} else if (artefact.type.name == weblink){
		    			
		    				MyShowcaseShowcaseSlider.renderArtefactWeblink(artefact,focused);
		    			
		    			} else if (artefact.type.name == pebblepad){
		    			
		    				MyShowcaseShowcaseSlider.renderArtefactPebblePad(artefact,focused);
		    			}
	                
		    	});
//				MyShowcaseShowcaseSlider.renderBlank();
//			    alert("Final Count " + finalCount) ;
			    MyShowcaseShowcaseSlider.setCurrentSlide(1) ;
			    MyShowcaseShowcaseSlider.setMaxSlides(finalCount) ;
			    MyShowcaseShowcaseSlider.resize(MyShowcaseShowcaseSlider.getMaxSlides()) ;
//			    MyShowcaseShowcaseSlider.enableDisableButtons(1) ;
			    MyShowcaseShowcaseSlider.setImagebarScrollPos() ;

			    if (count == 0)
			    	MyShowcaseShowcaseSlider.hideSlider() ;
			    else
			    	MyShowcaseShowcaseSlider.showSlider() ;
  	
		    });
		   
		}),
		
	    clearSlider: (function(){
			$('#scroller-contents').empty();	
	    }),
	    
	    hideSlider: (function(){
//			$('#showcase-slider').hide();	
	    }),
	    
	    showSlider: (function(){
//			$('#showcase-slider').show();	
	    }),
	    
		renderBlank: (function(){
			
			var textToInsert =  '';	
			textToInsert += '<!-- Blank version for buffer -->';
			textToInsert += '<div id="mini-post-000" class="mini-post blank">';
			textToInsert += '</div>';      
			$('#scroller-contents').append(textToInsert);		
		}),
			
		renderArtefactRss: (function(artefact,focused){
				
			var textToInsert =  '';	
			
			var dateCreated = new Date(artefact.createdDateTime);

			textToInsert += '<div id="mini-post-';
			textToInsert += artefact.artefactId;
			textToInsert += '" class="mini-post' + focused + ' rss">'; 
			textToInsert += '<div class="mini-post-badge">';
			textToInsert += '<span>RSS:</span>';
			textToInsert += '</div>';
			textToInsert += '<div class="mini-post-contents">';
			textToInsert += '<p class="mini-post-title">';
			textToInsert += artefact.name;
			textToInsert += '</p>';
			textToInsert += '<p class="mini-post-metadata">Imported on ';
			textToInsert += dateCreated.toLocaleString();
			textToInsert += '<p>';
			textToInsert += '</div>';
			textToInsert += '<div class="mini-post-bodytext">';
			textToInsert += '<p>';
			textToInsert += artefact.description;
			textToInsert += '</p>';
			textToInsert += '<p class="mini-post-url"><a href="';
			textToInsert += artefact.artefactDetail.url;
			textToInsert += '" target="_blank">';
			textToInsert += artefact.artefactDetail.detail;
			textToInsert += '</a>';
			textToInsert += '<div class="mini-post-actions">';
			textToInsert += '<a href="#" class="mini-post-action-delete" onclick="MyShowcaseArtefact.deleteFromShowcase(' + artefact.artefactId + ')"></a>';
			textToInsert += '</div>';
			
	    	textToInsert += '</div>';
	    	
			$('#scroller-contents').append(textToInsert);		
		}),	

		
		renderArtefactPebblePad: (function(artefact,focused){
			
			var textToInsert =  '';
			
			var dateCreated = new Date(artefact.createdDateTime);
			
			textToInsert += '<div id="mini-post-';
			textToInsert += artefact.artefactId; 
			textToInsert += '" class="mini-post' + focused + ' pebblepad">'; 
			
			textToInsert += '<div class="mini-post-badge">';
			textToInsert += '<span>Pebblepad:</span>';
			textToInsert += '</div>';
			textToInsert += '<div class="mini-post-contents">';
			textToInsert += '<p class="mini-post-title">';
			textToInsert += artefact.name;
			textToInsert += '</p>';		
			textToInsert += '<p class="mini-post-url"><a href="';
			textToInsert += artefact.artefactDetail.url;
			textToInsert += '" target="_blank">';
			textToInsert += artefact.artefactDetail.url;
			textToInsert +=	'</a>';
			textToInsert += '<p class="mini-post-metadata">Collected on ';
			textToInsert += dateCreated.toLocaleString();
			textToInsert += '</p>';
			textToInsert += '</div>';
			textToInsert += '<div class="mini-post-bodytext">';
			textToInsert += '<p>';
			textToInsert += artefact.description;
			textToInsert += '</p>';
			textToInsert += '</div>';
			textToInsert += '<div class="mini-post-actions">';
			textToInsert += '<a href="#" class="mini-post-action-delete" onclick="MyShowcaseArtefact.deleteFromShowcase(' + artefact.artefactId + ')"></a>';
			textToInsert += '</div>';
			textToInsert += '</div>';
			
			$('#scroller-contents').append(textToInsert);
		}),

		
		renderArtefactFile:  (function(artefact,focused){
			
			var textToInsert =  '';
			
 			var ownerId = $("#ownerId").val();
			var userId = $("#userId").val();
			var dateCreated = new Date(artefact.createdDateTime);
			var fileStoreAddress = MyShowcaseArtefact.getFileStoreAddress();
			
			textToInsert += '<div id="mini-post-';
			textToInsert +=	artefact.artefactId;
			textToInsert += '" class="mini-post' + focused + ' file file-';
			textToInsert += artefact.artefactDetail.fileType;
			textToInsert += '">';
			textToInsert += '<div class="mini-post-badge">';
			textToInsert += '<span>File:</span>';
			textToInsert += '</div>';
			textToInsert += '<div class="mini-post-contents">';
			textToInsert += '<p class="mini-post-title">';
			textToInsert += artefact.name;
			textToInsert += '</p>';		
			textToInsert += '<div class="mini-post-info-holder">';
//			textToInsert += '<div class="mini-post-info-icon">';
//			textToInsert += '<span>Image:</span>';
//			textToInsert += '</div>'; 
			textToInsert += '<div class="mini-post-info-text">';	
			textToInsert += '<p class="mini-post-file"><a href="';
			
			textToInsert += fileStoreAddress + ownerId + '/' + artefact.artefactDetail.fileName;
			
//			textToInsert += '/filestore/' + userId + '/' + artefact.artefactDetail.fileName;
//			textToInsert += '" ">';
			
			textToInsert += '" target="_blank">';

			textToInsert += artefact.artefactDetail.fileName;
			textToInsert += '</a>';
			textToInsert += '<p class="mini-post-metadata">Collected on ';
			textToInsert += dateCreated.toLocaleString();
			textToInsert += '</p>';
			textToInsert += '</div>';	
			textToInsert += '</div>';
			textToInsert += '</div>';
			textToInsert += '<div class="mini-post-bodytext">';
			textToInsert += '<p>';
			textToInsert += artefact.description;
			textToInsert += '</p>';
			textToInsert += '</div>';
			textToInsert += '<div class="mini-post-actions">';
			textToInsert += '<a href="#" class="mini-post-action-delete" onclick="MyShowcaseArtefact.deleteFromShowcase(' + artefact.artefactId + ')"></a>';
			textToInsert += '</div>';
	    	textToInsert += '</div>';
			
			$('#scroller-contents').append(textToInsert);
		}),

		
		renderArtefactWeblink: (function(artefact,focused){
			
			var textToInsert =  '';
			
			var dateCreated = new Date(artefact.createdDateTime);
			textToInsert += '<div id="mini-post-';
			textToInsert += artefact.artefactId;
			textToInsert += '" class="mini-post' + focused + ' weblink">'; 
			textToInsert += '<div class="mini-post-badge">';
			textToInsert += '<span>Weblink:</span>';
			textToInsert += '</div>';
			textToInsert += '<div class="mini-post-contents">';
			textToInsert += '<p class="mini-post-title">';
			textToInsert += artefact.name;
			textToInsert += '</p>';		
			textToInsert += '<p class="mini-post-url"><a href="';
			textToInsert += artefact.artefactDetail.url;
			textToInsert += '" target="_blank">';
			textToInsert += artefact.artefactDetail.url;
			textToInsert += '</a>';
			textToInsert += '<p class="mini-post-metadata"> Collected on ';
			textToInsert += dateCreated.toLocaleString(); 
			textToInsert += '</p>';
			textToInsert += '</div>';
			textToInsert += '<div class="mini-post-bodytext">';
			textToInsert += '<p>';
			textToInsert += artefact.description;
			textToInsert += '</p>';
			textToInsert += '</div>';
			textToInsert += '<div class="mini-post-actions">';
			textToInsert += '<a href="#" class="mini-post-action-delete" onclick="MyShowcaseArtefact.deleteFromShowcase(' + artefact.artefactId + ')"></a>';
			textToInsert += '</div>';
			
	    	textToInsert += '</div>';		
			
			$('#scroller-contents').append(textToInsert);
		}),

		
		renderArtefactTwitter: (function(artefact,focused){

			var textToInsert =  '';
			
			var dateCreated = new Date(artefact.createdDateTime);
			
			textToInsert += '<div id="mini-post-';
			textToInsert += artefact.artefactId;
			textToInsert += '" class="mini-post' + focused + ' twitter">';
			textToInsert += '<div class="mini-post-badge">';
			textToInsert += '<span>Twitter:</span>';
			textToInsert += '</div>';
			textToInsert += '<div class="mini-post-contents">';
			textToInsert += '<p class="mini-post-quote">&ldquo;' + artefact.artefactDetail.detail + '&rdquo;</p>';
			textToInsert += '<p class="mini-post-metadata">Posted by <a href="http://www.twitter.com/' + artefact.artefactDetail.twitterUserName +'" target="_blank">' + artefact.artefactDetail.twitterUserName +'</a> on ';
			textToInsert += dateCreated.toLocaleString(); 
			textToInsert += '</p>';
			textToInsert += '</div>';
			textToInsert += '<div class="mini-post-actions">';
			textToInsert += '<a href="#" class="mini-post-action-delete" onclick="MyShowcaseArtefact.deleteFromShowcase(' + artefact.artefactId + ')"></a>';
			textToInsert += '</div>';
			
	    	textToInsert += '</div>';		
			
			$('#scroller-contents').append(textToInsert);	
		}),
		

		renderArtefactFlickr: (function(artefact,focused){
			
			var textToInsert =  '';
			
			var dateCreated = new Date(artefact.createdDateTime);
			textToInsert += '<div id="mini-post-';
			textToInsert += artefact.artefactId;
			textToInsert += '" class="mini-post' + focused + ' flickr">'; 
			textToInsert += '<div class="mini-post-badge">';
			textToInsert += '<span>Flickr:</span>';
			textToInsert += '</div>';
			textToInsert += '<div class="mini-post-contents">';
			textToInsert += '<p class="mini-post-title">';
			textToInsert += artefact.name;
			textToInsert += '</p>';		
			textToInsert += '<p class="mini-post-metadata">Drawn from <a href="http://www.flickr.com/" target="_blank">Flickr</a> on ';
			textToInsert += dateCreated.toLocaleString(); 		
			textToInsert += '</p>';
			textToInsert += '</div>';
			textToInsert += '<div class="mini-post-image">';
			textToInsert += '<img src="' + artefact.artefactDetail.url + '" alt="' + artefact.artefactDetail.url + '" width="268" height="201"/>';
			textToInsert += '</div>';
			textToInsert += '<div class="mini-post-bodytext">';
			textToInsert += '<p>';
			textToInsert += artefact.description;
			textToInsert += '</p>';
			textToInsert += '<p>';
			textToInsert += artefact.artefactDetail.detail;
			textToInsert += '</p>';
			textToInsert += '</div>';
			textToInsert += '<div class="mini-post-actions">';
			textToInsert += '<a href="#" class="mini-post-action-delete" onclick="MyShowcaseArtefact.deleteFromShowcase(' + artefact.artefactId + ')"></a>';
			textToInsert += '</div>';
	    	textToInsert += '</div>';		
			$('#scroller-contents').append(textToInsert);
		}),
	
		
		renderArtefactSakai: (function(artefact,focused){

			var textToInsert =  '';
			
			var dateCreated = new Date(artefact.createdDateTime);

			textToInsert += '<div id="mini-post-';
			textToInsert += artefact.artefactId;
			textToInsert += '" class="mini-post' + focused + ' sakai">';
			textToInsert += '<div class="mini-post-badge">';
			textToInsert += '<span>Sakai:</span>';
			textToInsert += '</div>';
			textToInsert += '<div class="mini-post-contents">';
			textToInsert += '<p class="mini-post-title">';
			textToInsert += artefact.name;
			textToInsert += '</p>';		
			textToInsert += '<p class="mini-post-metadata">Collected on ';
			textToInsert += dateCreated.toLocaleString();
			textToInsert += '</p>';
			textToInsert += '</div>';
			textToInsert += '<div class="mini-post-actions">';
			textToInsert += '<a href="#" class="mini-post-action-delete" onclick="MyShowcaseArtefact.deleteFromShowcase(' + artefact.artefactId + ')"></a>';
			textToInsert += '</div>';
	    	textToInsert += '</div>';		
			
			$('#scroller-contents').append(textToInsert);
		})


			
}