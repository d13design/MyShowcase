



$(document).ready(function() {

//	
// Set Up Home page 
//	
	DefaultTheme.buildDefaultContent();
	
});

var DefaultTheme = {
		 

		   
	    buildDefaultContent: (function(){
        
	    	DefaultTheme.renderDefaultHeader();
	    	
	        
//	        var ownerUserId = $("#ownerUserId").val() ;
	        var showcaseName = $("#showcaseName").val();
	        var textToInsert = '<p>' + showcaseName + ' is a <a href="http://www.my-showcase.org">MyShowcase</a> showcase created by ' + 'admin' + ' using the <a href="http://www.my-showcase.org/themes">Default Theme</a> theme.</p>'
			$('#footer').append(textToInsert);

		    	
	    }),

		monthName: (function(date) {
			
			var monthString = "" ;
			var month = date.getMonth() + 1 ;
	        switch (month) {
		        case 1:  monthString = "January"; break;
		        case 2:  monthString = "February"; break;
		        case 3:  monthString = "March"; break;
		        case 4:  monthString = "April"; break;
		        case 5:  monthString = "May"; break;
		        case 6:  monthString = "June"; break;
		        case 7:  monthString = "July"; break;
		        case 8:  monthString = "August"; break;
		        case 9:  monthString = "September"; break;
		        case 10: monthString = "October"; break;
		        case 11: monthString = "November"; break;
		        case 12: monthString = "December"; break;
		        default: monthString = "Invalid month.";break;
	        }

			return monthString ;
		}),

		shortMonthName: (function(date) {
			
			var monthString = "" ;
			var month = date.getMonth() + 1 ;
	        switch (month) {
		        case 1:  monthString = "Jan"; break;
		        case 2:  monthString = "Feb"; break;
		        case 3:  monthString = "Mar"; break;
		        case 4:  monthString = "Apr"; break;
		        case 5:  monthString = "May"; break;
		        case 6:  monthString = "Jun"; break;
		        case 7:  monthString = "Jul"; break;
		        case 8:  monthString = "Aug"; break;
		        case 9:  monthString = "Sep"; break;
		        case 10: monthString = "Oct"; break;
		        case 11: monthString = "Nov"; break;
		        case 12: monthString = "Dec"; break;
		        default: monthString = "Inv";break;
	        }

			return monthString ;
		}),

	    ordinalOfDate: (function(date) {
			var ordinalString = "" ;
			var i = date.getDate() ;
			if ((i % 10 == 1) && (i != 11))
				ordinalString = i + "st" ;
			else if ((i % 10 == 2) && (i != 12))
				ordinalString = i + "nd" ;
			else if ((i % 10 == 3) && (i != 13))
				ordinalString = i + "rd" ;
			else
				ordinalString = i + "th" ;
				
			return ordinalString ;
	    }),
	    
	    shortMonthName: (function(date) {
	    	return '' ;
	    }),

	    renderDefaultDate: (function(date) {
			var textToInsert = '' ;
			textToInsert += '<div class="date">' ; 
			textToInsert += '<p><strong>' + DefaultTheme.ordinalOfDate(date) + '</strong> ' + DefaultTheme.shortMonthName(date) + '</p>' ; 
			textToInsert.append("</div>") ;
	    	return textToInsert.toString() ;
	    }),
	    
		renderDetail: (function(heading,list){

			var twitter = 'Twitter';
			var flickr = 'Flickr';
			var file = 'File';
			var weblink = 'Weblink';
			var pebblepad = 'PebblePad';
			var rss = 'Rss';
			var sakai = 'Sakai';			
			
			var textToInsert = '' ;
			var fullDesc = $("#showcaseFullDesc").val();
			textToInsert += '<div id="main">' ; 

			textToInsert += '<div class="artefacts">' ; 

			textToInsert += '<div class="description">' ; 
			textToInsert += '<p>' + fullDesc + '</p>' ; 
			textToInsert += '</div>';
				
					
   			$.each(list, function(index, artefact) {
						
    			if (artefact.type.name == twitter){
    				textToInsert += DefaultTheme.renderArtefactTwitter(artefact);
    			} else if (artefact.type.name == flickr){
    				textToInsert += DefaultTheme.renderArtefactFlickr(artefact);
    			} else if (artefact.type.name == rss){
    				textToInsert += DefaultTheme.renderArtefactRss(artefact);
    			} else if (artefact.type.name == file){
    				textToInsert += DefaultTheme.renderArtefactFile(artefact);
    			} else if (artefact.type.name == sakai){
    				textToInsert += DefaultTheme.renderArtefactSakai(artefact);
    			} else if (artefact.type.name == weblink){
    				textToInsert += DefaultTheme.renderArtefactWeblink(artefact);
    			} else if (artefact.type.name == pebblepad){
    				textToInsert += DefaultTheme.renderArtefactPebblePad(artefact);
    			}		   				
  
   			});	
		   		
			textToInsert += '</div>'
				
			textToInsert += '</div>';

			return textToInsert;
			
		}),	
	
		
		renderArtefactFile: (function(artefact){
		
			
			var textToInsert =  '';
			var dateCreated = new Date(artefact.createdDateTime);
				
			textToInsert += '<div class="artefact file">';
				textToInsert += '<h4>';
				textToInsert += artefact.name;
				textToInsert += '</h4>';
				textToInsert += '<p class="meta">A file based item added on ';
				textToInsert += dateCreated.toLocaleString();
				textToInsert += '- <a href="';
				textToInsert += artefact.artefactDetail.filePath + artefact.artefactDetail.fileName;
				textToInsert += '" target="_blank">click here to download</a></p>';
				textToInsert += '<p class="context">';
				textToInsert += artefact.description;
				textToInsert += '</p>';
			textToInsert += '</div>';
				
			return textToInsert;
			
		}),
		
		
		renderArtefactRss: (function(artefact){
		
			var dateCreated = new Date(artefact.createdDateTime);
			var textToInsert =  '';
			
			textToInsert += '<div class="artefact rss">';
				textToInsert += '<h4>';
				textToInsert += artefact.name;
				textToInsert += '</h4>';
				textToInsert += '<p class="meta">An RSS item added on ';
				textToInsert += dateCreated.toLocaleString();
				textToInsert += '</p>';
				textToInsert += '<blockquote>';
					textToInsert += artefact.artefactDetail.detail;
					textToInsert += '</blockquote>';
					textToInsert += '<p class="context">';
					textToInsert += artefact.description;
					textToInsert += '</p>';
			textToInsert += '</div>';	

			return textToInsert;
			
		}),
		
		
		renderArtefactWeblink: (function(artefact){
			
			var dateCreated = new Date(artefact.createdDateTime);
			var textToInsert =  '';
			
			textToInsert += '<div class="artefact link">';
				textToInsert += '<h4>';
				textToInsert += artefact.name;
				textToInsert += '</h4>';
				textToInsert += '<p class="meta">A link item added on ';
				textToInsert += dateCreated.toLocaleString();
				textToInsert += '</p>';
				textToInsert += '<p class="link"><a href="';
				textToInsert += artefact.artefactDetail.url;
				textToInsert += '">';
				textToInsert += artefact.artefactDetail.url;
				textToInsert += '</a></p>';
				textToInsert += '<p class="context">';
				textToInsert += artefact.description;
				textToInsert += '</p>';
				
			textToInsert += '</div>';			

			return textToInsert;
			
		}),				
		
		
		renderArtefactSakai: (function(artefact){
		
			var dateCreated = new Date(artefact.createdDateTime);
			var textToInsert =  '';
			
			textToInsert += '<div class="artefact sakai">';
				textToInsert += '<h4>';
				textToInsert += artefact.name;
				textToInsert += '</h4>';
				textToInsert += '<p class="meta">A Sakai item added on ';
				textToInsert += dateCreated.toLocaleString();
				textToInsert += '</p>';
				textToInsert += '<p class="link"><a href="';
				textToInsert += artefact.artefactDetail.url;
				textToInsert += '">';
				textToInsert += artefact.artefactDetail.url;
				textToInsert += '</a></p>';
				textToInsert += '<p class="context">';
				textToInsert += artefact.description;
				textToInsert += '</p>';
		    textToInsert += '</div>';		

			return textToInsert;
			
		}),	
		
		
		renderArtefactPebblePad: (function(artefact){
		
			alert ("Pebble Pad - Yes");
			
			var dateCreated = new Date(artefact.createdDateTime);
			var textToInsert =  '';
			
			textToInsert += '<div class="artefact pebblepad">';
				textToInsert += '<h4>';
				textToInsert += artefact.name;
				textToInsert += '</h4>';
				textToInsert += '<p class="meta">A Pebblepad item added on ';
				textToInsert += dateCreated.toLocaleString();
				textToInsert += '</p>';
				textToInsert += '<p class="link"><a href="';
				textToInsert += artefact.artefactDetail.url;
				textToInsert += '">';
				textToInsert += artefact.artefactDetail.url;
				textToInsert += '</a></p>';
				textToInsert += '<p class="context">';
				textToInsert += artefact.description;
				textToInsert += '</p>';
				textToInsert += '</div>';
			

			return textToInsert;
			
		}),	
		
		
		renderArtefactFlickr: (function(artefact){
		
			var dateCreated = new Date(artefact.createdDateTime);
			var textToInsert =  '';
			
			textToInsert += '<div class="artefact flickr">';
				textToInsert += '<h4>';
				textToInsert += artefact.name;
				textToInsert += '</h4>';
				textToInsert += '<p class="meta">A Flickr item added on ';
				textToInsert += dateCreated.toLocaleString();
				textToInsert += '</p>';
				textToInsert += '<img src="' + artefact.artefactDetail.url + '" alt="';
				textToInsert += artefact.name;
				textToInsert += '" />';
				textToInsert += '<p class="context">';
				textToInsert += artefact.description;
				textToInsert += '</p>';
			textToInsert += '</div>';		

			return textToInsert;
			
		}),
		
		
		renderArtefactTwitter: (function(artefact){
		
			var dateCreated = new Date(artefact.createdDateTime);
			var textToInsert =  '';
			
			textToInsert += '<div class="artefact twitter">';
				textToInsert += '<h4>&ldquo;';
				textToInsert += artefact.artefactDetail.detail;
				textToInsert += '&rdquo;</h4>';
				textToInsert += '<p class="meta">A Twitter item added on ';
				textToInsert += dateCreated.toLocaleString();
				textToInsert += '</p>';
				textToInsert += '<p class="context">';
				textToInsert += artefact.description;
				textToInsert += '</p>';
			textToInsert += '</div>';
				
			return textToInsert;
			
		}),	
		
		
		renderHeader: (function(){
			
			var textToInsert =  '';
			var owner = $("#ownerID").val();
			var name = $("#showcaseName").val();
			textToInsert += '<div id="header">';
			
			textToInsert += '<h1>';
			textToInsert += owner;	
			textToInsert += '</h1>';
			textToInsert += '<h2>';
			textToInsert += name;
			textToInsert += '</h2>';
			
			textToInsert += '<div id="headimage">&nbsp;</div>';
			
			textToInsert += '</div>';	

			$('#ms-header').append(textToInsert);	
			
		})
		
	
	};	
