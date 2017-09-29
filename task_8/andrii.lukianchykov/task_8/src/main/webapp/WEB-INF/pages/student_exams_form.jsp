<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student exams form</title>
</head>
<body>
<form:form modelAttribute="student" action="/jpa/students/exams" method="get">
    <table>
        <tr>
            <td><form:label path="firstname"/>Student firstname: </td>
            <td><form:input path="firstname"/></td>
        </tr>
        <tr>
            <td><form:label path="lastname"/>Student lastname: </td>
            <td><form:input path="lastname"/></td>
        </tr>
    </table>
    <br/>
    <input type="submit" value="Show exams"/>
</form:form>
</body>
</html>
