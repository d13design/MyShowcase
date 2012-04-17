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
    <link media="all" href="css/MyShowcaseEvidenceSlider_styles.css" rel="stylesheet" type="text/css" />
    <link media="all" href="css/jquery.ui.autocomplete.custom.css" rel="stylesheet" type="text/css"/>   
    
    
<!-- 
    
    <script language="JavaScript" type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
    
  --> 
  
    <script src="/library/js/headscripts.js" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/jquery-1.4.js"/>" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/jquery.blockUI.js"/>" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/MyShowcaseFancybox.js"/>" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/MyShowcase.js"/>" language="JavaScript" type="text/javascript"></script>
	<script src="<c:url value="/javascript/fancybox/jquery.mousewheel-3.0.2.pack.js"/>" language="JavaScript" type="text/javascript"></script>
	<script src="<c:url value="/javascript/fancybox/jquery.fancybox-1.3.1.js"/>" language="JavaScript" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value="/javascript/fancybox/jquery.fancybox-1.3.1.css"/>" media="screen" />
    <script src="<c:url value="/javascript/MyShowcaseArtefact.js"/>" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/MyShowcaseShowcaseSlider.js"/>" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/MyShowcaseArtefactList.js"/>" language="JavaScript" type="text/javascript"></script>
    <script src="<c:url value="/javascript/tag-it-jquery/js/tag-it.js"/>" language="JavaScript" type="text/javascript"></script>
	<script src="<c:url value="/javascript/tag-it-jquery/js/jquery-ui/jquery-ui-1.8.core-and-interactions.min.js"/>" type="text/javascript" charset="utf-8"></script>
	<link href="/javascript/tag-it-jquery/js/jquery-ui/jquery.ui.autocomplete.custom.css" rel="stylesheet" type="text/css"/>
	<script src="<c:url value="/javascript/tag-it-jquery/js/jquery-ui/jquery-ui-1.8.autocomplete.min.js"/>" type="text/javascript" charset="utf-8"></script>


	  
    <title>Test</title>
	</head>
	<body onload="<%=request.getAttribute("sakai.html.body.onload")%>">
	
	<iframe id="ResizeHelperFrame" src='' height='0' width='0' frameborder='0'></iframe>
	
	<div class="portletBody">
	<div id="myshowcase">
    <!--script language="JavaScript" type="text/javascript">alert("<%=request.getAttribute("sakai.html.body.onload")%>") ;</script-->
			<div id="ms-header">				
				<div id="ms-menu">
					<ul>
						<li><a id="ms-collection" href="#" class="collect" >Collect evidence</a></li>
						
						<li class="divider">&nbsp;</li>
						
						<li><a id="ms-search-tag" href="#" class="fb-tag">Find by tag</a></li>
					
						<li><a id="ms-search-mapping" href="#" class="fb-mapping">Find by mapping</a></li>

						<li><a id="ms-search-type" href="#" class="fb-type">Find by type</a></li>
						
						<li class="divider">&nbsp;</li>
						
						<li><a id="ms-showcase" href="#" class="showcase" >Showcase evidence</a><span id="ms-counter">0</span></li>						
					</ul>
				</div>
			<div id="ms-collectibles" class="ms-dropdown-panel ms-dropdown-panel-large" style="display:none;">
				<div class="ms-dropdown-panel-close">
					<a href="#" onclick="$('#ms-collectibles').slideUp(); MyShowcaseArtefact.resetEvidenceStream(); return false;"><span>Close</span></a>
				</div>
				<div id="ms-collectibles-dropdown">
				</div>
			</div>
				
			<div id="ms-findby-tag" class="ms-dropdown-panel ms-dropdown-panel-large" style="display:none;">
				<div class="ms-dropdown-panel-close">
					<a href="#" onclick="$('#ms-findby-tag').slideUp(); MyShowcaseArtefact.resetEvidenceStream(); return false;"><span>Close</span></a>
				</div>
				<div id="ms-findby-tag-dropdown">
				</div>
			</div>				
					
			<div id="ms-findby-mapping" class="ms-dropdown-panel ms-dropdown-panel-large" style="display:none;">
				<div class="ms-dropdown-panel-close">
					<a href="#" onclick="$('#ms-findby-mapping').slideUp(); MyShowcaseArtefact.resetEvidenceStream(); return false;"><span>Close</span></a>
				</div>
				<div id="ms-findby-mapping-dropdown">
				</div>
				<div id="ms-mapholder" class="ms-mapholder">
				</div>
			</div>
				
			<div id="ms-findby-type" class="ms-dropdown-panel" style="display:none;">
				<div class="ms-dropdown-panel-close">
					<a href="#" onclick="$('#ms-findby-type').slideUp(); MyShowcaseArtefact.resetEvidenceStream(); return false;"><span>Close</span></a>
				</div>
				<div id="ms-findby-type-dropdown">
				</div>
			</div>

			<div id="ms-view-showcase" class="ms-dropdown-panel ms-dropdown-panel-large" style="display:none;">
				<div class="ms-dropdown-panel-close">
					<a href="#" onclick="$('#ms-view-showcase').slideUp(); MyShowcaseArtefact.resetEvidenceStream(); return false;"><span>Close</span></a>
				</div>
				<h3>View your current draft showcase:</h3>

				<div id="showcase-slider" class="showcase-slider">
					<div id="scroller-button-left">
						<!--input id="left" type="image" value="left" src="img/showcaser-section/scroll-left.png" style="height:84px; width: 17px"-->
					</div>
						
					<div id="scroller">
						<div id="scroller-contents">	
						</div>	
					</div>			
					
					<div id="scroller-button-right">
						<!--input id="right" type="image" value="right" src="img/showcaser-section/scroll-right.png" style="height:84px; width: 17px"></input-->
					</div>
				</div>

				<div id="ms-view-showcase-buttons" style="float: none">
				</div>
				
			</div>
				
			<div id="ms-searchbar">
					
				<div id="ms-search">
      				<form onsubmit="MyShowcaseArtefact.loadArtefactsForSearchTerm();return false;">
       					<input type="text" id="ms-search-term" value="Search" />
       					<input type="image" id="ms-search-button" src="img/ms-search-button.png" />
     				 </form>
    			 </div>

				<div id="ms-tag-chain">
					<p><span class="ms-tc-cat">&nbsp;</span> <span class="ms-tc-tag">&nbsp;</span><a id="ms-tc-exit" onclick="MyShowcaseArtefact.resetEvidenceStream();" href="#"><img  src="img/ms-tag-chain-clear.png" /></a></p>
				</div>

			</div>
				<div id="ms-page-header">				
				<form>
					<input type="hidden" name="owner" id="owner" value=${owner}>
					<input type="hidden" name="ownerId" id="ownerId" value=${owner.ownerId}>
					<input type="hidden" name="userId" id="userId" value=${owner.userId}>
					<input type="hidden" name="forename" id="forename" value=${owner.forename}>
					<input type="hidden" name="surname" id="surname" value=${owner.surname}>
					<input type="hidden" name="showcaseId" id="showcaseId" value=${showcase.showcaseId}>
					<input type="hidden" name="fileStoreAddress" id="fileStoreAddress" value=${config.uploadDir}>
					<input type="hidden" name="artefactsList" id="artefactsList" value=${owner.artefacts}>
					<input type="hidden" name="parent" id="parent" value=${config.parent}>
					<input type="hidden" name="selectedSearchType" id="selectedSearchType" value="">
					<input type="hidden" name="selectedSearchTag" id="selectedSearchTag" value="">
					<input type="hidden" name="pageNo" id="pageNo" value="1">	
					<input type="hidden" name="pageName" id="pageName" value="home">
					<input type="hidden" name="maxSlides" id="maxSlides" value="0">
					<input type="hidden" name="scrollPos" id="scrollPos" value="0">
					<input type="hidden" name="currentSlide" id="currentSlide" value="0">
					
					<input type="hidden" name="iframeId" id="iframeId" value="<%=request.getAttribute("sakai.html.body.onload")%>">
					
				</form>
				
										
				<form id="ms-stream-filter">
				    
					<select name="artefactsPerPage" id="artefactsPerPage">
						<option value="10" selected="selected">Show 10 items per page</option>
						<option value="5" >Show 5 items per page</option>
						<option value="20" >Show 20 items per page</option>
						<option value="999999" >Show all items on one page</option>
					</select>
					
					<select name="artefactOrder" id="artefactOrder">
						<option value="createdDate" selected="selected">Order by date</option>
						<option value="type" >Order by type</option>
					</select>
										
				</form>
				
				
				
				<h3><span>My evidence stream</span><a id="ms-evidence-stream-counter">0</a></h3> 
				<div id="ms-pagination-top">
				</div>
			</div>	
		</div>
			
			<div id="ms-footer">
			
				<div id="ms-pagination-bottom">
				</div>
				
				<div id="ms-top-link">
				</div>
			</div>
		</div>
	</div>	
	</body>
</html>	
			
			