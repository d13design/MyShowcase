
$(document).ready(function() {
	
		CollectTwitter.validate();

	
		$("#input1").focus(function(){
			
			if( CollectTwitter.getInput1() == CollectTwitter.getDefaultInput1() ) {
				CollectTwitter.setInput1("");
			}
		
		});	

		$("#input1").blur(function(){
			
			if( CollectTwitter.getInput1().length == 0) {
				CollectTwitter.setInput1(CollectTwitter.getDefaultInput1());
			};
			
			CollectTwitter.validate();
		
		});	
		
		$("#input2").focus(function(){
			
			if( CollectTwitter.getInput2() == CollectTwitter.getDefaultInput2() ) {
				CollectTwitter.setInput2("");
			}
		
		});	

		$("#input2").blur(function(){
			
			if( CollectTwitter.getInput2().length == 0) {
				CollectTwitter.setInput2(CollectTwitter.getDefaultInput2());
			}; 
			
			CollectTwitter.validate();
		
		});	

		
		$("#input4").focus(function(){
			
			if( CollectTwitter.getInput4() == CollectTwitter.getDefaultInput4() ) {
				CollectTwitter.setInput4("");
			}
		
		});	

		$("#input4").blur(function(){
			
			if( CollectTwitter.getInput4().length == 0) {
				CollectTwitter.setInput4(CollectTwitter.getDefaultInput4());
			};
			
			CollectTwitter.validate();
		
		});	
		
		$("#input5").focus(function(){
			
			if( CollectTwitter.getInput5() == CollectTwitter.getDefaultInput5() ) {
				CollectTwitter.setInput5("");
			}
		
		});	

		$("#input5").blur(function(){
			
			if( CollectTwitter.getInput5().length == 0) {
				CollectTwitter.setInput5(CollectTwitter.getDefaultInput5());
			};
			
			CollectTwitter.validate();
		
		});	
		
		$("#input4").keypress(function(){
			
			CollectTwitter.validate();
		
		});	


		$("#input2").keypress(function(){
			
			CollectTwitter.validate();
		
		});		

		$("#input1").keypress(function(){
			
			CollectTwitter.validate();
		
		});			

		$("#input5").keypress(function(){
			
			CollectTwitter.validate();
		
		});				
//		$("#saveandclose").ajaxComplete(function() { 
//	        parent.$.fancybox.close();
//	        return false ;
//		}); 
		


		
//	    $("#justclose").click(function(){
//	    	alert("justclose");
//	        parent.$.fancybox.close();
//	        return false ;
//		}); 


	    
	    $("#preview").click(function(){
		    
			var preview = $("#input1").val();
			
			preview = "http://www.twitter.com/" + preview ;

	        var newwin = window.open(preview, 'My Showcase Preview', '');
	        
	        newwin.focus() ;

	        return false ;
		});	

//+++++++++++++ Functions and event management ++++++++++++++++++++++++++	
	
});


var CollectTwitter = {
		
	 
	outcome: (function(message){
		
		$('#collectionMessage').val(message);
		
		$('#collectionOutcome').submit();
				
	}),

	
    validate: (function(){
    	
    	$('#ms-actions').empty();
    	
    	var textToInsert = '';
    	
    	textToInsert += '<input id="justclose" onclick="CollectTwitter.close(); return false;" type="image" value="Cancel" src="img/ms-button-cancel.png"/>';
    	
    	if ((CollectTwitter.validInput1() == true) && (CollectTwitter.validInput2() == true) && (CollectTwitter.validInput4() == true)){
    		
    		textToInsert += '&nbsp;&nbsp;<input id="collect" onclick="CollectTwitter.collect(); return false;" type="image" value="Collect!" src="img/ms-button-collect-now.png"/>';
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
      	 
      	if (CollectTwitter.getInput1().length > 0){
	
			if(CollectTwitter.getInput1() == CollectTwitter.getDefaultInput1() ){
				
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
      	
      	if (CollectTwitter.getInput2().length > 0){
	
			if(CollectTwitter.getInput2() == CollectTwitter.getDefaultInput2() ){
				
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
      	 
      	if (CollectTwitter.getInput4().length > 0){
	
			if(CollectTwitter.getInput4() == CollectTwitter.getDefaultInput4() ){
				
				valid = false;
			}
			else {
				
				valid = true;
			}
		}
      	
		return valid;
      	 
    }), 

    validInput5: (function(){
      	 
      	var valid = false;
      	 
      	if (CollectTwitter.getInput5().length > 0){
	
			if(CollectTwitter.getInput5() == CollectTwitter.getDefaultInput5() ){
				
				valid = false;
			}
			else {
				
				valid = true;
			}
		}
      	
		return valid;
      	 
    }),    
    
    getDefaultInput1: (function(){
         	 
         	 var value = "e.g. bbcnews";
         	 
         	 return value;
         	 
    }),       
     
    
    close: (function(){


        parent.$.fancybox.close();
        
        return false ;        	 
    }),
   
    
    collect: (function(){
      	
	    var type = $("#type").val();
	    
		var title = $("#input2").val();
		
		var desc = $("#input4").val();
		
		var datavalue = $("#input1").val();
		
		var searchFilter = $("#input5").val();
		
		var ownerId = $("#ownerId").val();
		
		var firstChar;
		
		if (searchFilter == "")
			firstChar = "";
		else
			firstChar = searchFilter.substring(0,1);

//		provision of a tag should not be manadatory
//		if ((title != "") && (desc != "") && (datavalue != "") && ((firstChar == "#") || (firstChar == "@"))) {
		if ((title != "") && (desc != "") && (datavalue != "")) {

			$.blockUI({message: '<h3>Collecting tweets ...</h3>', css: {top:  '100px', width: '200px'}});
						
			$.post("MyShowcaseSaveArtefactsTwitter.json",{ ownerId: ownerId, type: type, title: title, desc: desc, datavalue: datavalue, searchFilter: searchFilter},
				function(list) {
				
				$.unblockUI();
				
				$.each(list, function(index, data){ 

					CollectTwitter.outcome(data.message);				

				});
	    	});
		}
        
//        parent.$.fancybox.close();
		
        return false ;
        	 
    }),   
    
    getDefaultInput2: (function(){
	   	 
	   	 var value = "e.g. Tweets about XYZ";
	   	 
	   	 return value;
    }),
 
    
    getDefaultInput4: (function(){
   	 
   	 var value = "e.g. This could be useful for ABC";
   	 
   	 return value;
   	 
    }),   
 
   
   getDefaultInput5: (function(){
	   	 
	   	 var value = "e.g. #bignews or @smithy";
	   	 
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
    }), 	
   
 	   
    setInput5: (function(desc){
    	 
    	 $('#input5').val(desc);
    }),
 
	   
    getInput5: (function(){
    	 
    	 return $('#input5').val();
    }) 	
	
};	
