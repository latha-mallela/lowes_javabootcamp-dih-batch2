<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
	<title>New User Registration</title>
</head>

<body>
<p> ${exception.errorMsg}</p>
<br>
<h2>Enter user details</h2>

<spring:url value="/" var="contextPath" htmlEscape="true" />

<form:form method="POST" action="${contextPath}/registration">
   <table>
    <tr>
        <td><form:label path="firstName">FirstName:</form:label></td>
        <td><form:input path="firstName" /></td>
    </tr>
    <tr>
        <td><form:label path="lastName">LastName:</form:label></td>
        <td><form:input path="lastName" /></td>
    </tr>    <tr>
        <td><form:label path="userName">UserName:</form:label></td>
        <td><form:input path="userName" /></td>
    </tr>    
    <tr>
        <td><form:label path="password">Password:</form:label></td>
        <td><form:password path="password" /></td>
    </tr>
    <tr>
        <td><form:label path="location">Location:</form:label></td>
        <td><form:input path="location" /></td>
    </tr>    <tr>
        <td><form:label path="contact">Contact:</form:label></td>
        <td><form:input path="contact" /></td>
    </tr>    
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
        <td colspan="2">
            <a href="${contextPath}">Home</a> 
        </td>
    </tr>
</table>  
</form:form>
</body>
</html>