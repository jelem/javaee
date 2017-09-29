<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language=" java" %>
<html>
<head>
    <title>Exams List</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Student firstname</th>
        <th>Student lastname</th>
        <th>Subject name</th>
        <th>Grade</th>
    </tr>
    <c:forEach var="exam" items="${exams}">
        <tr>
            <td><c:out value="${exam.id}"/></td>
            <td><c:out value="${exam.student.firstname}"/></td>
            <td><c:out value="${exam.student.lastname}"/></td>
            <td><c:out value="${exam.subject.name}"/></td>
            <td><c:out value="${exam.grade}"/></td>
        </tr>
    </c:forEach>
</table>
<br/>
<input type="button" onclick="location.href='/jpa/exams/add';" value="Add an exam" />
</body>
</html>
