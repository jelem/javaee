<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bookshop</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link rel="stylesheet" href="/bookshop/stylesheet.css" type="text/css"/>
    <meta http-equiv="Content-Type" content="text/html" ; charset="utf-8">
</head>
<body>

<div class="grid-container">
    <%@ include file="/jsp/template-static-import.jsp" %>
    <section class="mainSect">
        <div class="formContainer">
            <h1>Добавление новой книги:</h1>
            <%=request.getAttribute("newBookForm")%>
        </div>
    </section>

</body>
</html>
