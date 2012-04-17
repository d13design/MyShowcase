<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyShowcase Login</title>
</head>
<body>

<br></br>



<form action="MyShowcaseLoginController.htm" method="post">
    <h1>MyShowcase Login</h1>
   <br/>
    ${config.loginGreeting}
    <br/>
    &nbsp;
    <br/>    
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Login: <input type="text" name="username" value=${login.userName}>
    <br/>
    &nbsp;
    <br/>
    Password: <input type="password" name="password" value=${login.password}>
    <br/>
    &nbsp;
    <br/>
    <input type="hidden" name="origin" id="origin" value="page"}>
    <input type="hidden" name="sso_source" id="sso_source" value=${account.accountSource.name}>
    <input type="hidden" name="sso_location" id="sso_location" value=${account.accountSource.location}>
    <input type="hidden" name="sso_user" id="sso_user" value=${account.userId}>
    <input type="hidden" name="parent" id="parent" value=${config.parent}>
    <input type=submit value="Login">
</form>

<br/>

<form action="MyShowcaseRegistrationController.htm" method="post">
    <input type="hidden" name="origin" id="origin" value="page"}>
    <input type="hidden" name="sso_source" id="sso_source" value=${account.accountSource.name}>
    <input type="hidden" name="sso_location" id="sso_location" value=${account.accountSource.location}>
    <input type="hidden" name="parent" id="parent" value=${config.parent}>
    <input type="hidden" name="sso_user" id="sso_user" value=${account.userId}>
    
    <input type=submit value="Register?"> If you don't yet have a MyShowcase account then please create one here.
</form>

<br/>

    <c:forEach var="message" items="${validation.messages}" varStatus="loopStatus">
			${validation.messages[loopStatus.index]}
			<br/>
    </c:forEach>
    
     <br/>
    &nbsp;
    <br/>  
 	
</body>
</html>