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
    <link media="all" href="css/ShowcaserTool_styles.css" rel="stylesheet" type="text/css" />
    <link media="all" href="css/MyShowcase.css" rel="stylesheet" type="text/css" />
    <link media="all" href="css/smoothness/jquery-ui-1.8.2.custom.css" rel="stylesheet" type="text/css" />
    
    <script language="JavaScript" type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
    <script src="/library/js/headscripts.js" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/jquery.js"/>" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/MyShowcase.js"/>" language="JavaScript" type="text/javascript"></script>   
    <script src="<c:url value="/javascript/MyShowcaseValidation.js"/>" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/MyShowcaseBuildShowcase.js"/>" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/MyShowcaseBuildShowcaseDetails.js"/>" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/general.js"/>" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/jquery-1.4.js"/>" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/jquery-ui-1.8.2.js"/>" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/jquery.blockUI.js"/>" language="JavaScript" type="text/javascript"></script>
 
 
    
    <title>MyShowcase Build Showcase Wizard</title>

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
						
						<li class="left selected"><a href="#" class="step1">Step1 <br/> DETAILS</a></li>
					
						<li class="leftm unselected"><a href="#" class="step2">Step2 <br/> TEMPLATE</a></li>
						
						<li class="rightm unselected"><a href="#" class="step3">Step3 <br/> REVIEWERS</a></li>
						
						<li class="right unselected"><a href="#" class="step4">Step4 <br/> SUMMARY</a></li>

						<li class="previous-next"><a id="ms-wizard-details-next" href="#" class="next">Next</a></li>

					</ul>
				</div>
				
			</div>
			
			<div id="ms-tool-body">
				
				
				<div id="heading1" class="showcaser-heading">
					<h3><span>Showcase Details</span></h3>
				</div>
				
				<div id="description1" class="description-text">
					<p>The details below form the main information of your Showcase. The title will appear at the top of your exported Showcase and remain the 
					name by which the specific Showcase is referred. Use the description to give any reviewers a brief explanation
					of the Showcase's intentions and content.
					</p>
				</div>	
				
				<div id="ms-messages">	
				</div>
				
				<div id="ms-tool-form">	
					<form id="showcaseWizard" name="showcaseWizard" action="MyShowcaseBuildShowcaseController.htm" method="post"> 

						<input type="hidden" name="pageName" id="pageName" value="showcaseWizardDetails"}>
						<input type="hidden" name="direction" id="direction" value=""}>
						<input type="hidden" name="showcaseId" id="showcaseId" value=${showcase.showcaseId}>
						<input type="hidden" name="owner" id="owner" value=${owner}>
						<input type="hidden" name="ownerId" id="ownerId" value=${owner.ownerId}>
						<input type="hidden" name="chosenEvidenceCount" id="chosenEvidenceCount" value=""}>
						<input type="hidden" name="currentSlide" id="currentSlide">
						<input type="hidden" name="maxSlides" id="maxSlides">
						<input type="hidden" name="scrollPos" id="scrollPos">
						<input type="hidden" name="parent" id="parent" value=${config.parent}>
						<input type="hidden" name="iframeId" id="iframeId" value="<%=request.getAttribute("sakai.html.body.onload")%>">
						
						<ul>
							<li><textarea name="showcaseTitle" id="showcaseTitle" >${showcase.name}</textarea></li>
							<li><textarea name="showcaseDesc" id="showcaseDesc" >${showcase.fullDesc}</textarea></li>
							<div id="ms-review-message" class="description-text">
							<p>Set the start date from which the link to your new showcase will be live and the date which the showcase will become inactive. Any reviewers you add will then be able to view your showcase in-between these two dates. You can always reissue the identical showcase after the expiry date if you decide to in the future without re-making it.
							</p>
							</div>
							<li class="review-time">Review Start Date: <input type="text" name="showcaseStartDate" id="showcaseStartDate" value="${showcase.formattedReviewStartDate}"/> End Date: <input type="text" name="showcaseEndDate" id="showcaseEndDate" value="${showcase.formattedReviewEndDate}"/></li>
						</ul>
						
					</form>
				</div>

				<div id="heading2" class="showcaser-heading" >
					<h3>
						<span>Chosen Evidence</span>
						<a id="ms-chosen-counter">0</a>
					</h3>
					<div id="description2" class="description-text">
						<p>Below is the evidence you chosen to add to this Showcase. You can re-edit each piece of evidence by 
						clicking on both the individual titles and descriptions. This will not change the details of the evidence in your
						 live evidence stream
						 </p>
					</div>
				</div> 
				
				<div id="ms-chosen-evidence-stream">
				</div>
				
				
			</div>			
										
<jsp:directive.include file="/WEB-INF/jsp/showcaseWizardFooter.jsp"/>