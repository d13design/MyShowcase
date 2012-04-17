<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-gb">

<head>

	<title>MyShowcase</title>
	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="Content-Language" content="en-gb" />
	<meta name="language" content="en-gb" />
	<meta name="keywords" content="" />
	<meta name="description" content="" />	

	<link media="all" href="css/signup-styles/signup-style.css" rel="stylesheet" type="text/css" />
	
	<script src="<c:url value="/javascript/jquery-1.4.js"/>" language="JavaScript" type="text/javascript"></script>
	<script src="<c:url value="/javascript/MyShowcaseLogin.js"/>" language="JavaScript" type="text/javascript"></script>
	
</head>

<body id="signin">

<div class="container_12">

	<div class="header">
		<div class="grid_5">
			<h1><span>MyShowcase</span></h1>
		</div>
		<div class="grid_7">
			<form action="MyShowcaseLoginController.htm" method="post">
				<table>
					<tr>
						<td><label for="username">Username</label></td>
						<td><label for="password">Password</label></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td><input id="username" name="username" type="text" class="text" value=${login.userName}></input></td>
						<td><input id="password" name="password" type="password" class="text"value=${login.password}></td>
						<td><input type="submit" value="Sign in" /></td>
					</tr>
				</table>
				<input type="hidden" name="origin" id="origin" value="page">
				<input type="hidden" name="sso_source" id="sso_source" value=${account.accountSource.name}>
				<input type="hidden" name="sso_location" id="sso_location" value=${account.accountSource.location}>
				<input type="hidden" name="sso_user" id="sso_user" value=${account.userId}>
				<input type="hidden" name="parent" id="parent" value=${config.parent}>
			</form>
		</div>
		<div class="clear">&nbsp;</div>
	</div>
	
	<div class="main">
		<div class="advert grid_7">
			<h3>MyShowcase is an open source web application which offers personalised evidence gathering for e-portfolios.</h3>
			<p>Users can gather, map and showcase content from a range of online sources, including social network spaces, to support learning and personal development. MyShowcase offers brand new possibilities for the e-portfolio in the age of social networking and even plugs straight into your VLE or LMS.</p>
		</div>
		<div class="grid_5">
			<h3>Register for MyShowcase</h3>
			<p>If you've not used MyShowcase before then please provide some simple details and we'll get you up and running in no time.</p>
			
			<div id="messages">
				<div id="message" class="message" style="display:none;">
					<p>
					<c:forEach var="message" items="${validation.messages}" varStatus="loopStatus">
						${validation.messages[loopStatus.index]}
							<br/>
    				</c:forEach> 					
					</p>
				</div>		
			</div>	
			<form action="MyShowcaseRegistrationController.htm" method="post">
				<p><label for="userName">Username:</label> <input class="text" id="userName" name="userName" type="text" value=${registration.userName}></input></p>
				<p><label for="password">Password (twice):</label> <input class="text" id="password" name="password" type="password" value=${registration.password}></input> <input class="text" id="passwordConfirm" name="passwordConfirm" type="password" value=${registration.passwordConfirm}></input></p>
				<p><label for="email">Email address (twice):</label> <input class="text" id="email" name="email" type="text" value=${registration.email}></input> <input class="text" id="emailConfirm" name="emailConfirm" type="text" value=${registration.emailConfirm}></input></p>
				<hr/>
				<p><label for="forename">First name:</label> <input class="text" id="forename" name="forename" type="text" value=${registration.forename}></input></p>
				<p><label for="surname">Family name:</label> <input class="text" id="surname" name="surname" type="text" value=${registration.surname}></input></p>
				<p><label for="birthDate">Date of birth (dd/mm/yyyy):</label> <input class="text" id="birthDate" name="birthDate" type="text" value=${registration.birthDate}></input></p>
				
				<input type="hidden" name="sso_source" id="sso_source" value=${account.accountSource.name}>
				<input type="hidden" name="sso_user" id="sso_user" value=${account.userId}> 
				<input type="hidden" name="sso_location" id="sso_location" value=${account.accountSource.location}> 
				<input type="hidden" name="origin" id="origin" value="registrationPage"> 
				<input type="hidden" name="numberOfMessages" id="numberOfMessages" value=${validation.numberOfMessages}> 
					
				<p><input type="submit" value="Sign up" /></p>
			</form>
		</div>
		<div class="clear">&nbsp;</div>
	</div>
	
</div>

<div class="footer">
	<div class="container_12">
		<div class="grid_12 footer_contents">
			<p>MyShowcase is an <a href="http://www.mkmlabs.com/">MKM Labs</a> / <a href="http://www.hull.ac.uk/" target="_blank">University of Hull</a> production - 2010</p>
			<div class="clear">&nbsp;</div>
		</div>
	</div>
	<div class="clear">&nbsp;</div>
</div>


</body>

</html>

