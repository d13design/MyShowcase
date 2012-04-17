<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Collection Result</title>

	<link rel="stylesheet" type="text/css" href="<c:url value="/javascript/fancybox/jquery.fancybox-1.3.1.css"/>" media="screen" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/MyShowcase.css"/>" media="screen" />
	
	<script src="<c:url value="/javascript/jquery-1.4.js"/>" language="JavaScript" type="text/javascript"></script>
	<script src="<c:url value="/javascript/jquery.form.js"/>" language="JavaScript" type="text/javascript"></script>
	<script src="<c:url value="/javascript/CollectionOutcome.js"/>" language="JavaScript" type="text/javascript"></script> 	
	
	
</head>

	<body>
	
		<p id="box-title">Collection Result</p>
		
		<p>&nbsp;</p>
		
		<div id="ms-warnings" class="box-warning">
			<p><span id="box-warning-star">*&nbsp;</span>${collectionResult.message}
			</p>
		</div>
		
		<p>&nbsp;</p>
		
		<div id="ms-warnings" class="box-warning">
			<p>Select 'Cancel' to return to your evidence stream or 'Collect' to continue collecting.
			</p>
		</div>
		
		<p>&nbsp;</p>
								
		<div id="ms-actions" class="ms-formrow-submit">
			<input id="justclose" onclick="CollectionOutcome.close(); return false;" type="image" value="Cancel" src="img/ms-button-cancel.png"/>
 			&nbsp;&nbsp;
 			<a href=${collectionResult.collectionLink}/>
 				<img src="img/ms-button-collect-now.png">
 			</a>
 		</div>

	</body>
	
</html>