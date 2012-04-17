
$(document).ready(function() {
	
		MyShowcaseLogin.messages();
		
			

//+++++++++++++ Functions and event management ++++++++++++++++++++++++++	
	
});


var MyShowcaseLogin = {
	 
	messages: (function(){
		
		if (MyShowcaseLogin.getNumberOfMessages() > 0){
			
			$("#message").removeAttr('style');
		}
		
	}),
    

	getNumberOfMessages: (function(){
		
		var numberOfMessages = parseInt($('#numberOfMessages').val());
		
		return numberOfMessages;		
	})
	
};	
	