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
     
    <script language="JavaScript" type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
    <script src="/library/js/headscripts.js" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/general.js"/>" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/jquery-1.4.js"/>" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/MyShowcase.js"/>" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/MyShowcaseBuildShowcase.js"/>" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/MyShowcaseBuildShowcaseSummary.js"/>" language="JavaScript" type="text/javascript"></script>
    
    <title>MyShowcase Build Showcase Summary</title>
 
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
						
						<li class="rightm unselected"><a href="#" class="step3">Step3 <br/> REVIEWERS</a></li>
						
						<li class="right selected"><a href="#" class="step4">Step4 <br/> SUMMARY</a></li>

						<li class="previous-next"><a id="ms-wizard-publish" href="#" class="next">PUBLISH!</a></li>

					</ul>
				</div>
				
			</div>		
			
			<div id="ms-tool-body">
					
				<form id="showcaseWizard" name="showcaseWizard" action="MyShowcaseBuildShowcaseController.htm" method="post"> 
					
					<input type="hidden" name="pageName" id="pageName" value="showcaseWizardSummary"}>
					<input type="hidden" name="direction" id="direction" value=""}>	
					<input type="hidden" name="showcaseId" id="showcaseId" value=${showcase.showcaseId}>
					<input type="hidden" name="owner" id="owner" value=${owner}>
					<input type="hidden" name="ownerId" id="ownerId" value=${owner.ownerId}>
					<input type="hidden" name="parent" id="parent" value=${config.parent}>
					<input type="hidden" name="iframeId" id="iframeId" value="<%=request.getAttribute("sakai.html.body.onload")%>">
					
				</form>	

				<!-- summary - topline -->
				<div class="summary-topline">				
					<div class="summary-template">

						<div class="summary-template-image">
							<p class="summary-template-title">${theme.name}</p>
							<img src="img/showcaser-section/${theme.illustration}" alt="photo" width="229" height="229" />
						</div>
					</div>	
					<div class="summary-details">
						<div class="summary-details-content">	
								<p class="summary-details-title">Showcase Title</p>
								<p class="summary-details-title-main">'${showcase.name}'</p>	
								<p class="summary-details-title">Showcase Description</p>
								<p class="summary-details-descr-body">${showcase.fullDesc} </p>	
						</div>
					</div>	
				</div>
				<!-- summary - reviewers -->
				<div id="heading7" class="showcaser-heading" >
					<h3><span>Summary Reviewers</span></h3>
				</div>
	
				<div class="column-title">
					<div class="listrow">				
						<ul>
							<li class="summary-reviewer-name-title"><p>Full Name</p></li>
							<li class="summary-reviewer-email-title"><p>Email Address</p></li>
						</ul>
					</div>
				</div>
				
				<div class="clear-line">
					<span></span>
				</div>

				<div id="summary-reviewer-list">
				</div>
	

				
				<div id="ms-chosen-evidence-stream">
				</div>
			
			<!-- end of ms-tool-body div -->		
			</div>			
															
<jsp:directive.include file="/WEB-INF/jsp/showcaseWizardFooter.jsp"/>