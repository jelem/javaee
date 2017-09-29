<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add a new exam</title>
</head>
<body>
<form:form modelAttribute="exam" action="/jpa/exams/add" method="post">
    <table>
        <tr>
            <td><form:label path="student.id"/>Student: </td>
            <td><form:select path="student.id" items="${studentsMap}"/></td>
        </tr>
        <tr>
            <td><form:label path="subject.id"/>Subject: </td>
            <td><form:select path="subject.id" items="${subjectsMap}"/></td>
        </tr>
        <tr>
            <td><form:label path="grade"/>Grade: </td>
            <td><form:input path="grade"/></td>
        </tr>
    </table>
    <br/>
    <input type="submit" value="Add"/>
</form:form>
</body>
</html>
