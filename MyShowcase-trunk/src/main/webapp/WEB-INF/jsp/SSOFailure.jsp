<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>MyShowcase SSO Request Failure</title>
</head>

<body>
<h1>SSO Request Failure : <%= new java.util.Date() %></h1>
<br/>
<h2>Supplied source = ${account.accountSource.name}</h2>
<br/>
<h2>Supplied location = ${account.accountSource.location}</h2>
<br/>
<h2>Supplied user = ${account.userId}</h2><br/>
<h2><font color="#AA0000">Messages :</font></h2>
<br/>
    <c:forEach var="message" items="${validation.messages}" varStatus="loopStatus">
			${validation.messages[loopStatus.index]}
			<br/>
    </c:forEach>
 
</body>
</html>

