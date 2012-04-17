<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <script src="<c:url value="/javascript/MyShowcaseAccount.js"/>" language="JavaScript" type="text/javascript"></script>


<title> MyShowcase Registration</title>
</head>
<body>

<form action="MyShowcaseRegistrationController.htm" method="post">

    <h1>MyShowcase Registration</h1>
    
    It looks as if MyShowcase doesn't know who you are yet. Please provide some simple details and we'll get you up and running in no time.
    <br/>
    &nbsp;
    <br/>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Username: <input type="text" name="userName" value=${registration.userName}><br/>
    &nbsp;<br/>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Password: <input type="password" name="password" value=${registration.password}><br/>
    &nbsp;<br/>
    Pwd Confirm: <input type="password" name="passwordConfirm" value=${registration.passwordConfirm}><br/>
    &nbsp;<br/>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Email: <input type="text" name="email" value=${registration.email}></input><br/>
    &nbsp;<br/>
    Email Confirm: <input type="text" name="emailConfirm" value=${registration.emailConfirm}><br/>
    &nbsp;<br/>
    &nbsp;&nbsp;&nbsp;&nbsp;Forename: <input type="text" name="forename" value=${registration.forename}><br/>
    &nbsp;<br/>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Surname: <input type="text" name="surname" value=${registration.surname}><br/>
    &nbsp;<br/>
    Date of Birth: <input type="text" name="birthDate" value=${registration.birthDate}><br/>
    (dd/mm/yyyy) <br/>
    &nbsp;<br/>
    
    <input type=submit value="Submit">
      
 	<br/>
 	
    <c:forEach var="message" items="${validation.messages}" varStatus="loopStatus">
			${validation.messages[loopStatus.index]}
			<br/>
    </c:forEach> 
    
    <input type="hidden" name="sso_source" id="sso_source" value=${account.accountSource.name}>
    <input type="hidden" name="sso_user" id="sso_user" value=${account.userId}> 
    <input type="hidden" name="sso_location" id="sso_location" value=${account.accountSource.location}> 
    <input type="hidden" name="origin" id="origin" value="registrationPage">  
</form>

</body>
</html>