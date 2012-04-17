
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
   
    <title>MyShowcase - Simple CV Theme</title>

 
	</head>


	
	<body>

		<form id="showcaseThemeSimpleCV" name="showcaseThemeSimpleCv"> 

			<input type="hidden" name="introduction" id="introduction" value=${simpleCV.mappingIdIntroductionCount}>
			<input type="hidden" name="contactDetails" id="contactDetails" value=${simpleCV.mappingIdContactDetailsCount}>
			<input type="hidden" name="education" id="education" value=${simpleCV.mappingIdEducationCount}>
			<input type="hidden" name="experience" id="experience" value=${simpleCV.mappingIdExperienceCount}>
			<input type="hidden" name="additionalSkills" id="additionalSkills" value=${simpleCV.mappingIdAdditionalSkillsCount}>
			<input type="hidden" name="hobbiesAndInterests" id="hobbiesAndInterests" value=${simpleCV.mappingIdHobbiesAndInterestsCount}>
			<input type="hidden" name="furtherInfo" id="furtherInfo" value=${simpleCV.mappingIdFurtherInfoCount}>
			
			<input type="hidden" name="introductionId" id="introductionId" value=${simpleCV.mappingIdIntroduction}>
			<input type="hidden" name="contactDetailsId" id="contactDetailsId" value=${simpleCV.mappingIdContactDetails}>
			<input type="hidden" name="educationId" id="educationId" value=${simpleCV.mappingIdEducation}>
			<input type="hidden" name="experienceId" id="experienceId" value=${simpleCV.mappingIdExperience}>
			<input type="hidden" name="additionalSkillsId" id="additionalSkillsId" value=${simpleCV.mappingIdAdditionalSkills}>
			<input type="hidden" name="hobbiesAndInterestsId" id="hobbiesAndInterestsId" value=${simpleCV.mappingIdHobbiesAndInterests}>
			<input type="hidden" name="furtherInfoId" id="furtherInfoId" value=${simpleCV.mappingIdFurtherInfo}>
			
			<input type="hidden" name="competencyId" id="competencyId" value=${simpleCV.competencyId}>
			<input type="hidden" name="showcaseId" id="showcaseId" value=${showcase.showcaseId}>
			<input type="hidden" name="ownerId" id="ownerId" value=${owner.ownerId}>
			<input type="hidden" name="showcaseName" id="showcaseName" value=${showcase.name}>
			<input type="hidden" name="showcaseFullDesc" id="showcaseFullDesc" value=${showcase.fullDesc}>		
		</form>

	
		<div id="wrapper">
			
			
			<div id="header">
				<h2>${showcase.name}</h2>
				<p>${showcase.fullDesc}</p>
				
				<div id="headimage">&nbsp;</div>
			</div>
							
			<div id="main">
			
				<div id="ms-cv-detail-introduction">
				</div>
				<div id="ms-cv-detail-contact-details">
				</div>	
				<div id="ms-cv-detail-education">
				</div>
				<div id="ms-cv-detail-experience">
				</div>				
				<div id="ms-cv-detail-additional-skills">
				</div>	
				<div id="ms-cv-detail-hobbies-interests">
				</div>
				<div id="ms-cv-detail-further-info">
				</div>
											
			</div>
			
			<div id="footer">
			</div>
			
		</div>
		
	</body>
</html>
