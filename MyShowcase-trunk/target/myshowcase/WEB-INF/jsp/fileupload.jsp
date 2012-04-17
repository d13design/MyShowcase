<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<!--meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"-->
    <script src="/library/js/headscripts.js" language="JavaScript" type="text/javascript"></script>
	<script src="<c:url value="/javascript/jquery-1.4.js"/>" language="JavaScript" type="text/javascript"></script>
	<script src="<c:url value="/javascript/jquery.form.js"/>" language="JavaScript" type="text/javascript"></script>
	<script src="<c:url value="/javascript/fancybox/jquery.mousewheel-3.0.2.pack.js"/>" language="JavaScript" type="text/javascript"></script>
	<script src="<c:url value="/javascript/fancybox/jquery.fancybox-1.3.1.js"/>" language="JavaScript" type="text/javascript"></script>
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/javascript/fancybox/jquery.fancybox-1.3.1.css"/>" media="screen" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/MyShowcase.css"/>" media="screen" />

	<script src="<c:url value="/javascript/tag-it.js"/>" language="JavaScript" type="text/javascript"></script>
	<script src="<c:url value="/javascript/CollectEvidenceFile.js"/>" language="JavaScript" type="text/javascript"></script> 	
	<script src="<c:url value="/javascript/jquery-ui/jquery-ui-1.8.core-and-interactions.min.js"/>" type="text/javascript" charset="utf-8"></script>
	<link href="/javascript/jquery-ui/jquery.ui.autocomplete.custom.css" rel="stylesheet" type="text/css"/>
	<script src="<c:url value="/javascript/jquery-ui/jquery-ui-1.8.autocomplete.min.js"/>" type="text/javascript" charset="utf-8"></script>
	<title>File Upload</title>
</head>
<body>
	<p id="box-title">collect a file</p>
	<form name="DataEntry" class="ms-inputform-link" id="DataEntry" enctype="multipart/form-data" action="MyShowcaseFileUpload.MyShowcaseFileUploadServlet" method="post">
			<div class="ms-formrow" id="ms-formrow-file">
			<label for="input1">Browse your hard drive for the file you wish to collect into your evidence stream:</label>  
			<div class="form-line-clear"></div>  
			<input id="input1" name="input1" type="file" name="File" />
		</div>
		<div class="ms-formrow" id="ms-formrow-name">
			<label for="input2">Add a reference name for your file. This will appear against this item in your evidence stream:</label>  
			<div class="form-line-clear"></div>
			<input id="input2" name="input2" type="text" value="e.g. Picture of XYZ" />
		</div>
		<div class="ms-formrow">
			<label for="input4">Add a reference description for your file. This will appear against this item in your evidence stream:</label> 
			<div class="form-line-clear"></div> 
			<textarea id="input4" name="input4" type="text" rows="4" cols="51" value="" >e.g. This could be useful for ABC</textarea>
		</div>
		
		<div id="ms-warnings" class="box-warning">
		</div>
		
		<div id="ms-actions" class="ms-formrow-submit">
		</div>		
		<div>
		
		<input id="type" type="hidden" name="type" value="File"/>
		<input type="hidden" name="owner" id="owner" value=${owner}>
		<input type="hidden" name="ownerId" id="ownerId" value=${owner.ownerId}>
		<input type="hidden" name="ownerName" id="ownerName" value=${owner.userId}>
		
		
		</div>
		
	</form>

	
</body>
</html>