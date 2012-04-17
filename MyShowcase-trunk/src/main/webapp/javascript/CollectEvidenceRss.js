
$(document).ready(function() {
	
		CollectRss.validate();
		
		CollectRss.setInput2(CollectRss.getDefaultInput2());
		CollectRss.setInput4(CollectRss.getDefaultInput4());
	    
	    $("#preview").click(function(){
			var preview = document.getElementById("input1").value;
	        var newwin = window.open(preview, 'My Showcase Preview', '');
	        newwin.focus() ;
	        return false ;
		}); 	    

	    
		$("#input1").focus(function(){
			
			if( CollectRss.getInput1() == CollectRss.getDefaultInput1() ) {
				CollectRss.setInput1("");
			}
		
		});	

		$("#input1").blur(function(){
			
			if( CollectRss.getInput1().length == 0) {
				CollectRss.setInput1(CollectRss.getDefaultInput1());
			};
			
			CollectRss.validate();
		
		});	

		$("#input1").change(function(){
			
			if( CollectRss.getInput1().length == 0) {
				CollectRss.setInput1(CollectRss.getDefaultInput1());
			};
			
			CollectRss.validate();
		
		});
		
		
		$("#input2").focus(function(){
			
			if( CollectRss.getInput2() == CollectRss.getDefaultInput2() ) {
				CollectRss.setInput2("");
			}
		
		});	

		$("#input2").blur(function(){
			
			if( CollectRss.getInput2().length == 0) {
				CollectRss.setInput2(CollectRss.getDefaultInput2());
			}; 
			
			CollectRss.validate();
		
		});	

		$("#input2").change(function(){
			
			if( CollectRss.getInput2().length == 0) {
				CollectRss.setInput2(CollectRss.getDefaultInput2());
			}; 
			
			CollectRss.validate();
		
		});	
		
		$("#input4").focus(function(){
			
			if( CollectRss.getInput4() == CollectRss.getDefaultInput4() ) {
				CollectRss.setInput4("");
			}
		
		});	

		$("#input4").blur(function(){
			
			if( CollectRss.getInput4().length == 0) {
				CollectRss.setInput4(CollectRss.getDefaultInput4());
			};
			
			CollectRss.validate();
		
		});	


		$("#input4").keypress(function(){
			
			CollectRss.validate();
		
		});	


		$("#input2").keypress(function(){
			
			CollectRss.validate();
		
		});		

		$("#input1").keypress(function(){
			
			CollectRss.validate();
		
		});				

//+++++++++++++ Functions and event management ++++++++++++++++++++++++++	
	
});


var CollectRss = {
	 
	outcome: (function(message){
		
		$('#collectionMessage').val(message);

		$('#collectionOutcome').submit();
				
	}),
	
    validate: (function(){
    	
    	$('#ms-actions').empty();
    	
    	var textToInsert = '';
    	
    	textToInsert += '<input id="justclose" onclick="CollectRss.close(); return false;" type="image" value="Cancel" src="img/ms-button-cancel.png" />';
    	
    	if ((CollectRss.validInput1() == true) && (CollectRss.validInput2() == true) && (CollectRss.validInput4() == true)){
    		
    		textToInsert += '&nbsp;&nbsp;<input id="collect" onclick="CollectRss.collect(); return false;" type="image" value="Collect!" src="img/ms-button-collect-now.png" />';
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
      	 
      	if (CollectRss.getInput1().length > 0){
	
			if(CollectRss.getInput1() == CollectRss.getDefaultInput1() ){
				
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
      	
      	if (CollectRss.getInput2().length > 0){
	
			if(CollectRss.getInput2() == CollectRss.getDefaultInput2() ){
				
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
      	 
      	if (CollectRss.getInput4().length > 0){
	
			if(CollectRss.getInput4() == CollectRss.getDefaultInput4() ){
				
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
    	
    	var type = document.getElementById("type").value;
	    
		var title = document.getElementById("input2").value;
		
		var desc = document.getElementById("input4").value;
		
		var datavalue = document.getElementById("input1").value;
		
		var ownerId = document.getElementById("ownerId").value;

		$.blockUI({message: '<h3>Collecting Rss feeds ...</h3>',css: {top:  '100px', width: '200px'}});
		
		$.post("MyShowcaseSaveArtefactsRSS.json",{ ownerId: ownerId, type: type, title: title, desc: desc, datavalue: datavalue},function(list) {
			
			$.unblockUI();
			
			$.each(list, function(index, data) { 
				
				CollectRss.outcome(data.message);	

			}) ;
		
    	});
									
//        alert('Finished') ;
//        parent.$.fancybox.close();
        return false ;
        
    }),
 
    
    getDefaultInput4: (function(){
   	 
   	 var value = "e.g. rss feed showing XYZ. This could be useful for ABC.";
   	 
   	 return value;
   	 
    }),   
 
    
    getDefaultInput2: (function(){
   	 
   	 var value = "e.g. rss feed of XYZ";
   	 
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
