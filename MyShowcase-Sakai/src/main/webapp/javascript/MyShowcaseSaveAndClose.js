$(document).ready(function() {
//	$("#saveandclose").ajaxComplete(function() { 
//        parent.$.fancybox.close();
//        return false ;
//	}); 

	$("#saveandclose").click(function(){
//	    alert("Save And Close From Common Include") ;
		var ownerId = $("#owner.ownerId").val();
	    var type = document.getElementById("type").value;
		var title = document.getElementById("input2").value;
		var desc = document.getElementById("input4").value;
		var datavalue = document.getElementById("input1").value;
		var ownerId = document.getElementById("ownerId").value;
		if ((title != "Name here") && (title != "") && (desc != "Description here:") && (desc != "") && (datavalue != "http://") && (datavalue != "")) {
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
});
