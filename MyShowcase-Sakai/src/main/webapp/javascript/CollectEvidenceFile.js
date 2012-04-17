
$(document).ready(function() {
	
		CollectFile.validate();

	
		$("#input1").focus(function(){
			
			if( CollectFile.getInput1() == CollectFile.getDefaultInput1() ) {
				CollectFile.setInput1("");
			}
		
		});	

		$("#input1").blur(function(){
			
			if( CollectFile.getInput1().length == 0) {
				CollectFile.setInput1(CollectFile.getDefaultInput1());
			};
			
			CollectFile.validate();
		
		});	

		$("#input1").change(function(){

			
			if( CollectFile.getInput1().length == 0) {
				CollectFile.setInput1(CollectFile.getDefaultInput1());
			};
			
			CollectFile.validate();
		
		});
		
		
		$("#input2").focus(function(){
			
			if( CollectFile.getInput2() == CollectFile.getDefaultInput2() ) {
				CollectFile.setInput2("");
			}
		
		});	

		$("#input2").blur(function(){
			
			if( CollectFile.getInput2().length == 0) {
				CollectFile.setInput2(CollectFile.getDefaultInput2());
			}; 
			
			CollectFile.validate();
		
		});	

		$("#input2").change(function(){

			if( CollectFile.getInput2().length == 0) {
				CollectFile.setInput2(CollectFile.getDefaultInput2());
			}; 
			
			CollectFile.validate();
		
		});	
		
		$("#input4").focus(function(){
			
			if( CollectFile.getInput4() == CollectFile.getDefaultInput4() ) {
				CollectFile.setInput4("");
			}
		
		});	

		$("#input4").blur(function(){
			
			if( CollectFile.getInput4().length == 0) {
				CollectFile.setInput4(CollectFile.getDefaultInput4());
			};
			
			CollectFile.validate();
		
		});	



		$("#input4").keypress(function(){
			
			CollectFile.validate();
		
		});	


		$("#input2").keypress(function(){
			
			CollectFile.validate();
		
		});		

		$("#input1").keypress(function(){
			
			CollectFile.validate();
		
		});				
	
		
//+++++++++++++ Functions and event management ++++++++++++++++++++++++++	
	
});


var CollectFile = {


	
    validate: (function(){
    	
    	$('#ms-actions').empty();
    	
    	var textToInsert = '';
    	
    	textToInsert += '<input id="justclose" onclick="CollectFile.close(); return false;" type="image" value="Cancel" src="img/ms-button-cancel.png"/>';
    	
    	if ((CollectFile.validInput1() == true) && (CollectFile.validInput2() == true) && (CollectFile.validInput4() == true)){
    		
    		textToInsert += '&nbsp;&nbsp;<input id="collect" onclick="CollectFile.collect(); onFocus="CollectFile.collect(); return false;" type="image" value="Collect!" src="img/ms-button-collect-now.png"/>';
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
      	 
      	if (CollectFile.getInput1().length > 0){
	
			if(CollectFile.getInput1() == CollectFile.getDefaultInput1() ){
				
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
      	
      	if (CollectFile.getInput2().length > 0){
	
			if(CollectFile.getInput2() == CollectFile.getDefaultInput2() ){
				
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
      	 
      	if (CollectFile.getInput4().length > 0){
	
			if(CollectFile.getInput4() == CollectFile.getDefaultInput4() ){
				
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
         	 
		$("#DataEntry").submit();

        return false ;
        	 
    }),   
    
    getDefaultInput2: (function(){
	   	 
	   	 var value = "e.g. Picture of XYZ";
	   	 
	   	 return value;
    }),
 
    
    getDefaultInput4: (function(){
   	 
   	 var value = "e.g. This could be useful for ABC";
   	 
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
