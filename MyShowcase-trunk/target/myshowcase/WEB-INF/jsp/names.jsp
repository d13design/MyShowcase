<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
</script>
<title>Names</title>
</head>
<body>
	<form>
	<table style="margin:0"> 
		<tr> 
			<td class="label">Forename:</td> 
			<td><input type="text" id="fname" name="fname" /></td> 
		</tr> 
		<tr> 
			<td class="label">Surname:</td> 
			<td><input type="text" id="sname" name="sname" /></td> 
		</tr>
		<tr>
			<td><button type="button" onClick="">Do Something</button></td>
		</tr>
	</table>
	</form>
<script type="text/javascript">
/*
function GetValue(key) {

	alert("Key") ;
	var s = window.location.search ;
	alert(s) ;
	if (s.length == 0)
		return("") ;
	s = s.replace("?", "") ;
	var a1 = s.split("&") ;
	for (i=0 ; i<=a1.length ; i++) {
		var a2 = a1[i].split("=") ;
		if (a2[0] == key)
			return (a2[1]) ;
	}
	
	return (s) ;
}

function LoadData() {
	alert("LoadData") ;

	var forename ;
	var surname ;
	forename = GetValue('forename') ;
	surname = GetValue('surname') ;
	var f =  document.getElementById("fname") ;
	f.value = forename ;
	var s =  document.getElementById("sname") ;
	s.value = surname ;
}
function TestFunction() {
	alert("Test Function") ;
}
$(document).ready(function() 
{
	alert("Ready Function") ;
	TestFunction() ;
	LoadData() ;
});
*/
</script>
</body>
</html>