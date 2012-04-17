
<jsp:directive.include file="/WEB-INF/jsp/showcaseHistoryIncludes.jsp"/>


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
    	<script src="<c:url value="/javascript/MyShowcaseShowcaseHistory.js"/>" language="JavaScript" type="text/javascript"></script>
    
    	<title>MyShowcase Showcase History</title>

	</head>
	
	<body onload="<%=request.getAttribute("sakai.html.body.onload")%>">
		
	<iframe id="ResizeHelperFrame" src='' height='0' width='0' frameborder='0'></iframe>
	
		<div class="portletBody">
		
			<div id="myshowcase">

				<!-- History -->			
				<div id="ms-header">
				
					<ul id="iconbar">
						<li>
							<a id="ms-home" href="#">
						
								<img src="img/showcaser-section/home-icon-word.png" alt="" />
							
								<span><img src="img/showcaser-section/home-icon.png" alt="" /></span>

							</a>
						</li>
					</ul>

					<div id="heading9">
						<h3><span>Showcase History</span></h3>
					</div>
				</div>
			
				<div id="ms-history-body">
			
					<form id="showcaseWizard" name="showcaseWizard" action="MyShowcaseBuildShowcaseController.htm" method="post"> 
						
						<input type="hidden" name="pageName" id="pageName" value="showcaseHistory"}></input>
						<input type="hidden" name="direction" id="direction" value=""}></input>
						<input type="hidden" name="owner" id="owner" value=${owner}></input>
						<input type="hidden" name="ownerId" id="ownerId" value=${owner.ownerId}></input>
						<input type="hidden" name="iframeId" id="iframeId" value="<%=request.getAttribute("sakai.html.body.onload")%>"></input>
						<input type="hidden" name="parent" id="parent" value=${config.parent}></input>

						
					</form>	
					
					<div class="clear-line">
				
						<span></span>
					
					</div>
				
					<div id="description9" class="history-description-text">
						Below is a list of your previously published Showcases ordered by date of publication. If a reviewer has viewed the Showcase a tick will appear by their name. If a Showcase's viewable time limit has elapsed, you can reissue it.
					</div>
				
				</div><!-- End ms-history body -->
			
				<div id="published-showcases">
				</div>
			
				<div id="ms-footer">
				</div>
			
			</div>
		
		</div>
		
	</body>

</html>

				
