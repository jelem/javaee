<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bookshop</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page import="com.task.bookshop.model.Book" %>
    <link rel="stylesheet" href="/bookshop/stylesheet.css" type="text/css"/>
    <meta http-equiv="Content-Type" content="text/html" ; charset="utf-8">
</head>
<body>

<div class="grid-container">
    <%@ include file="/jsp/template-static-import.jsp" %>
    <section class="mainSect">
        <div class="booklistTitle">
            <%=(request.getAttribute("pageTitle")!=null) ? request.getAttribute("pageTitle") : (Object)""%>
        </div>
        <c:if test="${not empty books}">
            <div class="booksContainer">
                <%@page import="java.util.List" %>
                <%@page import="com.task.bookshop.model.Book" %>
                <%@page import="com.task.bookshop.utils.LinkGenerator" %>
                <%
                    for (Book book : (List<Book>) request.getAttribute("books")) { %>
                <div class="bookItem">
                    <p class="bookEntry">
                        <%=LinkGenerator.getAuthorsLink(book.getAuthor())%>
                    </p>
                    <p class="itemEntry">
                        <%=LinkGenerator.getBookLink(book.getId().toString(), book.getTitle())%>
                    </p>
                    <p class="itemEntry">
                        <%=LinkGenerator.getYearLink(book.getYear())%>
                    </p>
                </div>
                <% }
                %>
            </div>
        </c:if>
        <c:if test="${empty books}">
            <div class="contentItem">
                <p class="warning">Ничего не найдено!</p>
            </div>
        </c:if>

    </section>

</body>
</html>
