
$(document).ready(function() {
	
		CollectWeblink.validate();

	    $("#preview").click(function(){
			var preview = document.getElementById("input1").value;
	        var newwin = window.open(preview, 'My Showcase Preview', '');
	        newwin.focus() ;
	        return false ;
		});
	    
		$("#saveandclose").click(function(){
//		    alert("Save And Close From Common Include") ;
			var ownerId = $("#owner.ownerId").val();
		    var type = document.getElementById("type").value;
			var title = document.getElementById("input2").value;
			var desc = document.getElementById("input4").value;
			var datavalue = document.getElementById("input1").value;
			var ownerId = document.getElementById("ownerId").value;
			if ((title != CollectWeblink.getDefaultInput2()) && (title != "") && (desc != CollectWeblink.getDefaultInput4()) && (desc != "") && (datavalue != "http://") && (datavalue != "")) {
				$.post("MyShowcaseSaveArtefacts.json",
					{ ownerId: ownerId, type: type, title: title, desc: desc, datavalue: datavalue},
					function(list) {
						$.each(list, function(index, data) { 
						}) ;
				        parent.$.fancybox.close();
			    	});
			}
	        return false ;
		}); 

	    $("#justclose").click(function(){
	        parent.$.fancybox.close();
	        return false ;
		}); 	    
		$("#input1").focus(function(){
			
			if( CollectWeblink.getInput1() == CollectWeblink.getDefaultInput1() ) {
				CollectWeblink.setInput1("");
			}
		
		});	

		$("#input1").blur(function(){
			
			if( CollectWeblink.getInput1().length == 0) {
				CollectWeblink.setInput1(CollectWeblink.getDefaultInput1());
			};
			
			CollectWeblink.validate();
		
		});	

		$("#input1").change(function(){
			
			if( CollectWeblink.getInput1().length == 0) {
				CollectWeblink.setInput1(CollectWeblink.getDefaultInput1());
			};
			
			CollectWeblink.validate();
		
		});
		
		
		$("#input2").focus(function(){
			
			if( CollectWeblink.getInput2() == CollectWeblink.getDefaultInput2() ) {
				CollectWeblink.setInput2("");
			}
		
		});	

		$("#input2").blur(function(){
			
			if( CollectWeblink.getInput2().length == 0) {
				CollectWeblink.setInput2(CollectWeblink.getDefaultInput2());
			}; 
			
			CollectWeblink.validate();
		
		});	

		$("#input2").change(function(){
			
			if( CollectWeblink.getInput2().length == 0) {
				CollectWeblink.setInput2(CollectWeblink.getDefaultInput2());
			}; 
			
			CollectWeblink.validate();
		
		});	
		
		$("#input4").focus(function(){
			
			if( CollectWeblink.getInput4() == CollectWeblink.getDefaultInput4() ) {
				CollectWeblink.setInput4("");
			}
		
		});	

		$("#input4").blur(function(){
			
			if( CollectWeblink.getInput4().length == 0) {
				CollectWeblink.setInput4(CollectWeblink.getDefaultInput4());
			};
			
			CollectWeblink.validate();
		
		});	


		$("#input4").keypress(function(){
			
			CollectWeblink.validate();
		
		});	


		$("#input2").keypress(function(){
			
			CollectWeblink.validate();
		
		});		

		$("#input1").keypress(function(){
			
			CollectWeblink.validate();
		
		});				

//+++++++++++++ Functions and event management ++++++++++++++++++++++++++	
	
});


var CollectWeblink = {
	 
	    
	outcome: (function(message){
		
		$('#collectionMessage').val(message);

		$('#collectionOutcome').submit();
			
	}), 

	
    validate: (function(){
    	
    	$('#ms-actions').empty();
    	
    	var textToInsert = '';
    	
    	textToInsert += '<input id="justclose" onclick="CollectWeblink.close(); return false;" type="image" value="Cancel" src="img/ms-button-cancel.png" />';
    	
    	if ((CollectWeblink.validInput1() == true) && (CollectWeblink.validInput2() == true) && (CollectWeblink.validInput4() == true)){
    		
    		textToInsert += '&nbsp;&nbsp;<input id="collect" onclick="CollectWeblink.collect(); return false;" type="image" value="Collect!" src="img/ms-button-collect-now.png" />';
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
      	 
      	if (CollectWeblink.getInput1().length > 0){
	
			if(CollectWeblink.getInput1() == CollectWeblink.getDefaultInput1() ){
				
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
      	
      	if (CollectWeblink.getInput2().length > 0){
	
			if(CollectWeblink.getInput2() == CollectWeblink.getDefaultInput2() ){
				
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
      	 
      	if (CollectWeblink.getInput4().length > 0){
	
			if(CollectWeblink.getInput4() == CollectWeblink.getDefaultInput4() ){
				
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
   
		var ownerId = $("#owner.ownerId").val();
	    var type = document.getElementById("type").value;
		var title = document.getElementById("input2").value;
		var desc = document.getElementById("input4").value;
		var datavalue = document.getElementById("input1").value;
		var ownerId = document.getElementById("ownerId").value;
		if ((title != CollectWeblink.getDefaultInput2()) && (title != "") && (desc != CollectWeblink.getDefaultInput4()) && (desc != "") && (datavalue != "http://") && (datavalue != "")) {
			$.post("MyShowcaseSaveArtefacts.json",
				{ ownerId: ownerId, type: type, title: title, desc: desc, datavalue: datavalue, artefactTypeDesc: " Weblink" },
				function(list) {
					
				$.each(list, function(index, data){
						
					CollectWeblink.outcome(data.message);	
						
				}) ;
					
	    	});
			
		}
        return false ;
        	 
    }),   
    
    getDefaultInput2: (function(){
	   	 
	   	 var value = "e.g. bbc website";
	   	 
	   	 return value;
    }),
 
    
    getDefaultInput4: (function(){
   	 
   	 var value = "e.g. web page showning XYZ. This could be useful for ABC.";
   	 
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