<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<!--html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en"-->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <script src="/library/js/headscripts.js" language="JavaScript" type="text/javascript"></script>
	<script src="<c:url value="/javascript/jquery-1.4.js"/>" language="JavaScript" type="text/javascript"></script>
	<script src="<c:url value="/javascript/fancybox/jquery.mousewheel-3.0.2.pack.js"/>" language="JavaScript" type="text/javascript"></script>
	<script src="<c:url value="/javascript/fancybox/jquery.fancybox-1.3.1.js"/>" language="JavaScript" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value="/javascript/fancybox/jquery.fancybox-1.3.1.css"/>" media="screen" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/MyShowcase.css"/>" media="screen" />

	<script src="<c:url value="/javascript/tag-it.js"/>" language="JavaScript" type="text/javascript"></script>
	<script src="<c:url value="/javascript/jquery-ui/jquery-ui-1.8.core-and-interactions.min.js"/>" type="text/javascript" charset="utf-8"></script>
	<link href="/javascript/jquery-ui/jquery.ui.autocomplete.custom.css" rel="stylesheet" type="text/css"/>
	<script src="<c:url value="/javascript/jquery-ui/jquery-ui-1.8.autocomplete.min.js"/>" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<p id="box-title">Sakai</p>
	<form class="ms-inputform-link" id="DataEntry" id="DataEntry" method="post">
		<div class="ms-formrow" id="ms-formrow-vle">
			<label for="input1">Link:</label>  
			<div class="form-line-clear"></div>  
			<input id="input1" type="text" value="http://" />
		</div>
		<jsp:directive.include file="/WEB-INF/jsp/artefactHeader.jsp"/>
		<div><input id="type" type="hidden" name="type" value="Sakai"/>
		
			<input type="hidden" name="owner" id="owner" value=${owner}>
			<input type="hidden" name="ownerId" id="ownerId" value=${owner.ownerId}>
			<input type="hidden" name="ownerName" id="ownerName" value=${owner.userId}>		
		</div>
	</form>
	<script src="<c:url value="/javascript/MyShowcaseSaveAndClose.js"/>" language="JavaScript" type="text/javascript"></script>
</body>
</html>