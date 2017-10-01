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
            <%--<%=request.getAttribute("newBookForm")%>--%>
            <form action="/bookshop/servlet/newbook" enctype="multipart/form-data" method="post" class="requestForm">
                <h2>Автор:</h2>
                <input type="text" name="author" class="formEntry">
                <h2>Название:</h2>
                <input type="text" name="title" class="formEntry">
                <h2>Год:</h2>
                <input type="number" name="year" value="2017" class="formEntry">
                <h2>Обложка:</h2>
                <%--<form method="POST"  action="fup.cgi">--%>
                Файл для загрузки: <input type="file" name="upfile"><br/>
                    <%--Notes about the file: <input type="text" name="note"><br/>--%>
                    <%--<br/>--%>
                    <%--<input type="submit" value="Загрузить">--%>
                <%--</form>--%>
                <br/>
                <input type="submit" value="Добавить книгу" class="formEntry">
            </form>
        </div>
    </section>

</body>
</html>
