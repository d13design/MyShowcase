(function($) {

 $.fn.tagit = function(artefact,artefactTags,action,options) {
	 
	 

  var el = this;
  var BACKSPACE  = 8;
  var ENTER   = 13;
  var SPACE   = 32;
  var COMMA   = 44;
  var status = action;
  var artefactId = artefact;
  var OPENING = "open";
  var CLOSED = "closed";
  var ALIVE = "alive";
  
  var artefactTagList = artefactTags.slice(0);

  el.addClass("tagit");

  // create the input field.
  var html_input_field = "<li class=\"tagit-new\"><input class=\"tagit-input\" onFocus=\"clearText(this)\" type=\"text\" value=\"Type your tag here\"/></li>\n";
  el.html (html_input_field);

  tag_input  = el.children(".tagit-new").children(".tagit-input");

  
  if (status == OPENING){ 
	  
	  $.each(artefactTagList, function(index, tagValue) { 
		
		 create_choice (tagValue);
		 
	  });
	  
	  status = ALIVE;
  }
 
   
  $(this).click(function(e){
   
      if (e.target.tagName == 'A') {
	   
          // Removes a tag when the little 'x' is clicked.
          // Event is binded to the UL, otherwise a new tag (LI > A) wouldn't have this event attached to it.
          $(e.target).parent().remove();
          removeTag();
      }
      else {
          // Sets the focus() to the input field, if the user clicks anywhere inside the UL.
          // This is needed because the input field needs to be of a small size.
          tag_input.focus();
      }
  });

  tag_input.keypress(function(event){
  
   if (event.which == BACKSPACE) {
    if (tag_input.val() == "") {
     // When backspace is pressed, the last tag is deleted.
     $(el).children(".tagit-choice:last").remove();
     removeTag();
    }
   }
   // Comma/Space/Enter are all valid delimiters for new tags.
   else if (event.which == COMMA || event.which == SPACE || event.which == ENTER) {
   
   
    event.preventDefault();

    var typed = tag_input.val();
    typed = typed.replace(/,+$/,"");
    typed = typed.trim();

    if (typed != "") {
     if (is_new (typed)) {
      create_choice (typed);
     }
     // Cleaning the input.
     tag_input.val("");
    }
   }
  });

  tag_input.autocomplete({
  
     
   source: options.availableTags, 
   
   select: function(event,ui){
    if (is_new (ui.item.value)) {
     create_choice (ui.item.value);
    }
    // Cleaning the input.
    tag_input.val("");

    // Preventing the tag input to be update with the chosen value.
    return false;
   }
  });

  function is_new (value){
  
   var is_new = true;
   this.tag_input.parents("ul").children(".tagit-choice").each(function(i){
   
    n = $(this).children("input").val();
    
    if (value == n) {
     is_new = false;
    };
   });
   return is_new;
  }
  
  
  function create_choice (value){
  
	  if (status == ALIVE){
		  
		  MyShowcase.maintainArtefactTags(artefactId,value,"add"); 
		  
		  artefactTagList.push(value);

	  };
	  
	  var el = "";
	  el  = "<li class=\"tagit-choice\">\n";
	  el += value + "\n";
	  el += "<a class=\"close\">x</a>\n";
	  el += "<input type=\"hidden\" style=\"display:none;\" value=\""+value+"\" name=\"item[tags][]\">\n";
	  el += "</li>\n";
	  var li_search_tags = this.tag_input.parent();
	  $(el).insertBefore (li_search_tags);
	  this.tag_input.val("");
      
  }
 
  
  function getCurrentTags (){

	  var listOfTags = new Array();
      
	  this.tag_input.parents("ul").children(".tagit-choice").each(function(i){
   
		  n = $(this).children("input").val();
		  
		  listOfTags.push(n);

	  });
	  
	  return listOfTags;
  }  

  
  function getOriginalTags (){

	  var originalTags = new Array();
	  
	  var tag = "";
	  
	  artefactTagList.each(function(i){
		  
		  tag = artefactTagList[i];
		  
		  originalTags[i] = tag;
		  
	  });
	  
	  return originalTags;
  }   
 
  
  function removeTag(){

	  var removedTag = "";
	  var array1 = artefactTagList.slice(0);
	   
	  var array2 = getCurrentTags();

	 
	  for (var i = 0; i<array2.length; i++) {
	      var arrlen = array1.length;
	      for (var j = 0; j<arrlen; j++) {
	          if (array2[i] == array1[j]) {
	              array1 = array1.slice(0, j).concat(array1.slice(j+1, arrlen));
	          }
	      }
	  }

	   removedTag = array1[0];
	   
	   MyShowcase.maintainArtefactTags(artefactId,removedTag,"delete");
	   
	   var listLength = artefactTagList.length;
	   
	   for (var i = 0; i<listLength; i++) {
		   
	       if (artefactTagList[i] == removedTag) {
	          
	          artefactTagList.splice(i,1);
	       }
	   }
  }   
  
  
};

	String.prototype.trim = function() {
		
		return this.replace(/^\s+|\s+$/g,"");
	};

})(jQuery);

