
<jsp:directive.include file="/WEB-INF/jsp/showcaseWizardIncludes.jsp"/>

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link media="all" href="/library/skin/tool_base.css" rel="stylesheet" type="text/css" />
    <link media="all" href="/library/skin/default/tool.css" rel="stylesheet" type="text/css" />
    <link media="all" href="css/smoothness/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" />
    <link media="all" href="css/fss-layout.css" rel="stylesheet" type="text/css" />
    <link media="all" href="css/portlet.css" rel="stylesheet" type="text/css" />
    <link media="all" href="css/MyShowcase.css" rel="stylesheet" type="text/css" />
    <link media="all" href="css/jquery.ui.autocomplete.custom.css" rel="stylesheet" type="text/css"/> 
    <link media="all" href="css/ShowcaserTool_styles.css" rel="stylesheet" type="text/css" /> 
    <link media="all" href="css/ShowcaserTool_styles.css" rel="stylesheet" type="text/css" />
    
    <script language="JavaScript" type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
    <script src="/library/js/headscripts.js" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/general.js"/>" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/jquery-1.4.js"/>" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/MyShowcase.js"/>" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/MyShowcaseBuildShowcase.js"/>" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/MyShowcaseBuildShowcaseReviewers.js"/>" language="JavaScript" type="text/javascript"></script>
    
    <title>MyShowcase Build Showcase Reviewers</title>


 
	</head>
	<body onload="<%=request.getAttribute("sakai.html.body.onload")%>">
		
	<iframe id="ResizeHelperFrame" src='' height='0' width='0' frameborder='0'></iframe>
	
	<div class="portletBody">
		
		<div id="myshowcase">
			
			<div id="ms-header">
			
				<ul id="iconbar">
					<li>
						<a id="ms-home" href="#">
						
							<img src="img/showcaser-section/home-icon-word.png" alt="" />
							
							<span><img src="img/showcaser-section/home-icon.png" alt="" /></span>

						</a>
					</li>
				</ul>
				
				<div id="ms-tool-menu">
					<ul>
						<li class="previous-next"><a id="ms-wizard-previous" href="#" class="previous">Previous</a></li>
						
						<li class="left unselected"><a href="#" class="step1">Step1 <br/> DETAILS</a></li>
					
						<li class="leftm unselected"><a href="#" class="step2">Step2 <br/> TEMPLATE</a></li>
						
						<li class="rightm selected"><a href="#" class="step3">Step3 <br/> REVIEWERS</a></li>
						
						<li class="right unselected"><a href="#" class="step4">Step4 <br/> SUMMARY</a></li>

						<li class="previous-next"><a id="ms-wizard-reviewers-next" href="#" class="next">Next</a></li>

					</ul>
				</div>
	
				
			</div>		
			
			<div id="ms-tool-body">
				
				<div id="heading4" class="showcaser-heading" >
					<h3><span>Showcase Reviewers</span></h3>
				</div>
				
				<div class="description-text">
				
					<p>Below you can add the people into the list who you want to send the link to your new Showcase by filling 
					in their details and clicking the green 'add' button. If you wish to remove a reviewer, simply click on the red
					'delete' button next to their name to clear them from the list.
					</p>
				
				</div>
				
				<div id="ms-messages">	
				</div>	
											
				<form id="showcaseWizard" name="showcaseWizard" action="MyShowcaseBuildShowcaseController.htm" method="post"> 
					
					<input type="hidden" name="pageName" id="pageName" value="showcaseWizardReviewers"}>
					<input type="hidden" name="direction" id="direction" value=""}>	
					<input type="hidden" name="showcaseId" id="showcaseId" value=${showcase.showcaseId}>
					<input type="hidden" name="owner" id="owner" value=${owner}>
					<input type="hidden" name="ownerId" id="ownerId" value=${owner.ownerId}>
					<input type="hidden" name="reviewersCount" id="reviewersCount" value="0"}>
					<input type="hidden" name="iframeId" id="iframeId" value="<%=request.getAttribute("sakai.html.body.onload")%>">
					<input type="hidden" name="parent" id="parent" value=${config.parent}>
					
				</form>	
				
				<form>	
				
					<div class="name-title">
						<p class="reviewer-name-title">Full Name</p>
						<p class="reviewer-email-title">Email Address</p>
					</div>

					<div class="clear-line">
						<span></span>
					</div>

					
					<div id="rev-form-contents">
					</div>
					
				</form>
	
			</div>
											
<jsp:directive.include file="/WEB-INF/jsp/showcaseWizardFooter.jsp"/>