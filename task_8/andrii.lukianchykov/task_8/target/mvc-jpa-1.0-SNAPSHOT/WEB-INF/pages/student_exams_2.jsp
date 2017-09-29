<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student exams</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Subject</th>
        <th>Teacher's name</th>
        <th>Grade</th>
    </tr>
    <c:forEach var="exam" items="${exams}">
        <tr>
            <td><c:out value="${exam.subject}"/></td>
            <td><c:out value="${exam.teacherName}"/></td>
            <td><c:out value="${exam.grade}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
