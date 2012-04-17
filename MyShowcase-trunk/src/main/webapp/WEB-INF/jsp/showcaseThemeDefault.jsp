
<%@ page contentType="text/html" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link media="all" href="/library/skin/tool_base.css" rel="stylesheet" type="text/css" />
    <link media="all" href="/library/skin/default/tool.css" rel="stylesheet" type="text/css" />
    <link media="all" href="css/SimpleCV.css" rel="stylesheet" type="text/css" />
    
    <script language="JavaScript" type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
    <script src="/library/js/headscripts.js" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/general.js"/>" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/jquery-1.4.js"/>" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/simpleCV.js"/>" language="JavaScript" type="text/javascript"></script>
   
    <title>MyShowcase - Default Theme</title>

 
	</head>


	
	<body>

		<form id="showcaseThemeDefault" name="showcaseDefault"> 
			<input type="hidden" name="showcaseId" id="showcaseId" value=${showcase.showcaseId}>
			<input type="hidden" name="ownerId" id="ownerId" value=${owner.ownerId}>
			<input type="hidden" name="showcaseName" id="showcaseName" value=${showcase.name}>
			<input type="hidden" name="showcaseFullDesc" id="showcaseFullDesc" value=${showcase.fullDesc}>		
		</form>

	
		<div id="wrapper">
			<div id="ms-header" class="ms-header"></div>
			<div id="ms-detail" class="ms-class"></div>
			<div id="footer"></div>
		</div>
		
	</body>
</html>
