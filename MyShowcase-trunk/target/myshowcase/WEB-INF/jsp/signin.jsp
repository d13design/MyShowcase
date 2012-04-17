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
	
	<link type="text/html" rel="home" href="/" />
	<link type="image/vnd.microsoft.icon" rel="shortcut icon" href="lib/favicon.ico" />
	
	<link type="text/css" rel="stylesheet" href="http://www.my-showcase.org/wp-content/themes/myshowcase/style.css" />
	<link type="text/css" rel="stylesheet" href="signup-style.css" />

</head>

<body id="signin">
	
	<div class="container_12">
		<div id="header" class="grid_12">
			<h1 id="logo"><a href="http://www.my-showcase.org/" title="MyShowcase" rel="home">MyShowcase</a></h1>

			<ul id="navigation-primary" class="list-horizontal">
				<li><a href="http://www.my-showcase.org/"><strong>Home</strong><br/>Welcome</a></li><li class="current"><a href="http://www.my-showcase.org/about/"><strong>About</strong><br/>Find out more</a></li><li><a href="http://www.my-showcase.org/community/"><strong>Community</strong><br/>Join in</a></li>			</ul>
			<h2 id="banner">Sign in to MyShowcase</h2>
		</div><div id="content">
					
			<div class="grid_12">
			
				<div class="grid_6 alpha">
				
					<h3>Sign in to MyShowcase</h3>
					
					<p>${config.loginGreeting}</p>
					
					 <p>
					   	<c:forEach var="message" items="${validation.messages}" varStatus="loopStatus">
									${validation.messages[loopStatus.index]}
									<br/>
    					</c:forEach>
					</p>
					
					<form action="MyShowcaseLoginController.htm" method="post">
						<fieldset class="form-signin">
							<legend>Account details</legend>
							<ol class="textentry">
								<li><label for="username">Username</label><br />
								<input id="username" name="username" type="text" size="30" value=${login.userName}></input></li>
								<li><label for="password">Password</label><br />
								<input id="password" name="password" type="password" size="30" value=${login.password}></input></li>
							</ol>
						</fieldset>
						
						<input type="hidden" name="origin" id="origin" value="page"}>
						<input type="hidden" name="sso_source" id="sso_source" value=${account.accountSource.name}>
						<input type="hidden" name="sso_location" id="sso_location" value=${account.accountSource.location}>
						<input type="hidden" name="sso_user" id="sso_user" value=${account.userId}>
						<input type="hidden" name="parent" id="parent" value=${config.parent}>
	  					
						<p class="submit">
							<input type="submit" value="Sign in" />
						</p>
					</form>
					
					<p><a href="#">Forgotten your sign in details?</a></p>
				
				</div>
				
				<div class="grid_6 omega">
				
					<h3>Don't have an account?</h3>
					
					<p><a href="MyShowcaseRegistrationController.htm?origin=page&sso_source=${account.accountSource.name}&sso_location=${account.accountSource.location}&sso_user=${account.userId}&parent=${config.parent}">
					Sign up now to access a range of exclusive benefits</a>. It won't take long, and it's <strong>completely free</strong>.</p>


					
				</div>
				
			</div>
			<div class="clear"></div>

		</div>
		
		<div id="footer" class="grid_12">

			<p><!--<a href="http://www.mkmlabs.com/"><img src="http://www.my-showcase.org/wp-content/themes/myshowcase/css/img/mkmlabs-small.png" alt="MKMlabs" class="footer-logo" /></a> &copy; 2010 <a href="http://www.myknowledgemap.com/">MyKnowledgeMap</a>.-->&nbsp;</p>
	
		</div>
		
	</div>
	
	<script type="text/javascript" src="http://www.my-showcase.org/wp-content/themes/myshowcase/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="http://www.my-showcase.org/wp-content/themes/myshowcase/js/jquery-ui-1.8.1.custom.min.js"></script>
	<!-- Dave added the next bit... -->
	<script language="JavaScript" src="http://www.my-showcase.org/wp-content/themes/myshowcase/js/jquery.d13twitter.js"></script>

	<script type="text/javascript" src="http://www.my-showcase.org/wp-content/themes/myshowcase/js/countdown/jquery.countdown.js"></script>
	<!-- Dave stopped adding stuff now. -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("input, textarea, select").focus(function() {
				$(this).addClass("focus");
			});
			$("input, textarea, select").blur(function() {
				$(this).removeClass("focus");
			});
			$("table tr").hover(function() {
				$(this).addClass("hover");
			}, function() {
				$(this).removeClass("hover");
			});
			$("#tabs").tabs();
			$("a.message-close").click(function(){
				$(this).parents(".message").animate({ opacity: 'hide' }, 300);
				return false;
			});
			/* Dave added the next bit... */
			$('#twitter-tweet').twitterize('myshowcasetool',{
				count:1,
				tweetBefore:'<p class="tweet"><em>',
				tweetAfter:'</em></p>',
				before:'',
				after:'',
				showTitle:false,
				showProfileLink:false,
				showTimestamp:true
			});
			$('#countdown').countdown('October 18, 2010 17:00');
			/* Dave stopped adding stuff now. */

			
		});
	</script>

</body>

</html>