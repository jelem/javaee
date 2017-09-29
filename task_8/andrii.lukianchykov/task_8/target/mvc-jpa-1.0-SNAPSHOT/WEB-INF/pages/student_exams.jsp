<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Exams</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Subject</th>
        <th>Teacher's name</th>
        <th>Grade</th>
    </tr>
    <c:forEach var="exam" items="${student.examSet}">
        <tr>
            <td><c:out value="${exam.subject.name}"/></td>
            <td><c:out value="${exam.subject.teacherName}"/></td>
            <td><c:out value="${exam.grade}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
