<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyShowcase Test SSO Login</title>
</head>
<body>

<br></br>

<form action="MyShowcaseHomeControllerSSO.htm" method="post">
    <h1>MyShowcase Test SSO Login</h1>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Source: <input type="text" name="source" value="">
    <br/>
    &nbsp;
    <br/>
    UserId: <input type="text" name="accountId" value="">
    <br/>
    &nbsp;
    <br/>
    <input type=submit value="Submit SSO Request">

</form>

</html>