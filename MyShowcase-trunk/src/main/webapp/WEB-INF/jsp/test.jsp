<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyShowcase Testing</title>
</head>
<body>

<form action="MyShowcaseLoginController.htm" method="post">
    <h1>Valid SSO: Moodle/bipitybop</h1>
    Login:    <input type="text" name="username"><br>
    Password: <input type="password" name="password"><br>
    <input type=submit value="Login">
    <h1>Invalid SSO - Missing Source: ?/bipitybop</h1>
    <h1>Invalid SSO - Missing UserId: Moodle/?</h1>
    <h1>Invalid SSO - Missing Source & UserId: ?/?</h1>
    <h1>Invalid SSO - Unidentified Source: xxxxx/bipitybop</h1>
</form>

</body>
</html>