
$(document).ready(function() {
	
		CollectFlickr.validate();

	
		$("#input1").focus(function(){
			
			if( CollectFlickr.getInput1() == CollectFlickr.getDefaultInput1() ) {
				CollectFlickr.setInput1("");
			}
		
		});	

		$("#input1").blur(function(){
			
			if( CollectFlickr.getInput1().length == 0) {
				CollectFlickr.setInput1(CollectFlickr.getDefaultInput1());
			};
			
			CollectFlickr.validate();
		
		});	

		$("#input1").change(function(){
			
			if( CollectFlickr.getInput1().length == 0) {
				CollectFlickr.setInput1(CollectFlickr.getDefaultInput1());
			};
			
			CollectFlickr.validate();
		
		});
		
		
		$("#input2").focus(function(){
			
			if( CollectFlickr.getInput2() == CollectFlickr.getDefaultInput2() ) {
				CollectFlickr.setInput2("");
			}
		
		});	

		$("#input2").blur(function(){
			
			if( CollectFlickr.getInput2().length == 0) {
				CollectFlickr.setInput2(CollectFlickr.getDefaultInput2());
			}; 
			
			CollectFlickr.validate();
		
		});	

		$("#input2").change(function(){
			
			if( CollectFlickr.getInput2().length == 0) {
				CollectFlickr.setInput2(CollectFlickr.getDefaultInput2());
			}; 
			
			CollectFlickr.validate();
		
		});	
		
		$("#input4").focus(function(){
			
			if( CollectFlickr.getInput4() == CollectFlickr.getDefaultInput4() ) {
				CollectFlickr.setInput4("");
			}
		
		});	

		$("#input4").blur(function(){
			
			if( CollectFlickr.getInput4().length == 0) {
				CollectFlickr.setInput4(CollectFlickr.getDefaultInput4());
			};
			
			CollectFlickr.validate();
		
		});	


		$("#input4").keypress(function(){
			
			CollectFlickr.validate();
		
		});	


		$("#input2").keypress(function(){
			
			CollectFlickr.validate();
		
		});		

		$("#input1").keypress(function(){
			
			CollectFlickr.validate();
		
		});	
		
	    $("#input1").change(function() {
		    
			$("#photoset >option").remove();

			var datavalue = document.getElementById("input1").value;
	    	
			$.blockUI({message: '<h3>Reading photosets ...</h3>',css: {top:  '100px'}});
			
			$.post("MyShowcaseFlickrPhotoset.json",
				{ datavalue : datavalue },
				function(list) {

				$.each(list, function(index, data) {
	        	   	$("#photoset").append($("<option></option>").val(data.status).html(data.message));
				});

				$.unblockUI();		 
		   	});

	        return false ;
	        
	    }) ;
	    
	    $("#getphotosets").click(function(){

			var datavalue = document.getElementById("input1").value;

			$.post("MyShowcaseFlickrPhotoset.json",
				{ datavalue : datavalue },
				function(list) {

					$.each(list, function(index, data) { 
		        	    $("#photoset").append($("<option></option>").val(data.status).html(data.message));
					}) ;
		    	});
			$("#getphotosets").attr("disabled", true);													


	        return false ;
		}); 

	    $("#preview").click(function(){
		    
			var preview = $("#input1").val();
			
			preview = preview.replace(/ /gi, "");
			
			var photoset = $("#photoset").val() ;
			
			var urltoview = 'http://www.flickr.com/photos/'+preview+'/';
			
			if ((photoset != "-1") && (photoset != "null"))
				
				urltoview += "sets/" + photoset + "/" ;
			
	        var newwin = window.open(urltoview, 'My Showcase Preview', '');
	        
	        newwin.focus() ;
	        
	        return false ;
	        
		}); 
});


//+++++++++++++ Functions and event management ++++++++++++++++++++++++++	
	



var CollectFlickr = {
	 
	outcome: (function(message){
		
		$('#collectionMessage').val(message);
		
		$('#collectionOutcome').submit();
				
	}),
		
    validate: (function(){
    	
    	$('#ms-actions').empty();
    	
    	var textToInsert = '';
    	
    	textToInsert += '<input id="justclose" onclick="CollectFlickr.close(); return false;" type="image" value="Cancel" src="img/ms-button-cancel.png"/>';
    	
    	if ((CollectFlickr.validInput1() == true) && (CollectFlickr.validInput2() == true) && (CollectFlickr.validInput4() == true)){
    		
    		textToInsert += '&nbsp;&nbsp;<input id="collect" onclick="CollectFlickr.collect(); return false;" type="image" value="Collect!" src="img/ms-button-collect-now.png"/>';
     	}; 	
  
     	$('#ms-actions').append(textToInsert);
     	
    }),   
 
 
    addMessage: (function(message){
    	
    	$('#ms-warnings').empty();
    	
    	var textToInsert = '';
    	
    	textToInsert += '<p><span id="box-warning-star">* </span>';
    	textToInsert += message;
    	textToInsert += '</p>';
  
     	$('#ms-warnings').append(textToInsert);
     	
    }),   
 
    
    validInput1: (function(){
      	 
      	var valid = false;
      	 
      	if (CollectFlickr.getInput1().length > 0){
	
			if(CollectFlickr.getInput1() == CollectFlickr.getDefaultInput1() ){
				
				valid = false;
			}
			else {
				
				valid = true;
			}
		}
      	
		return valid;
      	 
    }),

    validInput2: (function(){
      	 
      	var valid = false;
      	
      	if (CollectFlickr.getInput2().length > 0){
	
			if(CollectFlickr.getInput2() == CollectFlickr.getDefaultInput2() ){
				
				valid = false;
			}
			else {
				
				valid = true;
			}
		}
      	
		return valid;
      	 
    }), 
    

    validInput4: (function(){
      	 
      	var valid = false;
      	 
      	if (CollectFlickr.getInput4().length > 0){
	
			if(CollectFlickr.getInput4() == CollectFlickr.getDefaultInput4() ){
				
				valid = false;
			}
			else {
				
				valid = true;
			}
		}
      	
		return valid;
      	 
    }), 
 
    
    getDefaultInput1: (function(){
         	 
         	 var value = "";
         	 
         	 return value;
         	 
    }),       

    
    close: (function(){

        parent.$.fancybox.close();
        
        return false ;        	 
    }),
   
    
    collect: (function(){

		canClose = true ;
		
	    var type = document.getElementById("type").value;
	    
		var title = document.getElementById("input2").value;
		
		var desc = document.getElementById("input4").value;
		
		var datavalue = document.getElementById("input1").value;
		
		var ownerId = document.getElementById("ownerId").value;
		
		var photoset = document.getElementById("photoset").value;
    	
		$.blockUI({
			 message: '<h3>Collecting images ...</h3>',
			css: {	top:  '100px', width: '200px'}});

		$.post("MyShowcaseSaveArtefactsFlickr.json",
			{ ownerId: ownerId, type: type, title: title, desc: desc, datavalue: datavalue, photoset : photoset },
			function(list) {
				
			$.unblockUI();

			$.each(list, function(index, data) { 

				CollectFlickr.outcome(data.message);		
				
			}) ;

    	});
		
        return false ;  	 
    }),   
    
    getDefaultInput2: (function(){
	   	 
	   	 var value = "e.g. Photo set of XYZ";
	   	 
	   	 return value;
    }),
 
    
    getDefaultInput4: (function(){
   	 
   	 var value = "e.g. Photo set showing XYZ. This could be useful for ABC.";
   	 
   	 return value;
   	 
    }),   
 
   
    
    setInput1: (function(desc){
    	 
    	 $('#input1').val(desc);
    }),
 
	   
    getInput1: (function(){
    	 
    	 return $('#input1').val();
    }),
    
 	   
    setInput2: (function(desc){
    	 
    	 $('#input2').val(desc);
    }),
 
	   
    getInput2: (function(){
    	 
    	 return $('#input2').val();
    }), 

    
 	   
    setInput4: (function(desc){
    	 
    	 $('#input4').val(desc);
    }),
 
	   
    getInput4: (function(){
    	 
    	 return $('#input4').val();
    }) 	
 
 
	
};	
