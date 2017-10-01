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
        <div class="oneBookItemContainer">
            <%@page import="com.task.bookshop.model.Book" %>
            <%@page import="com.task.bookshop.utils.LinkGenerator" %>
            <%
                Object attr = request.getAttribute("oneBook");
                Book book;
                if (attr != null && attr instanceof Book) {
                    book = (Book) attr;
            %>
            <div class="oneBookItemEntry">
                <%
                    String image = LinkGenerator.getBookImageAsTag(book.getImage());
                    if (image == null) {
                      image = "<img src=\"/bookshop/img/book.png\" class=\"bookImg\">";
                    }
                %>
                <%=image%>
            </div>
            <div class="oneBookItem">
                <div class="oneBookItemEntry"><%=book.getTitle()%>
                </div>
                <div class="oneBookItemEntry">Автор: <%=LinkGenerator
                        .getAuthorsLink(book.getAuthor())%>
                </div>
                <div class="oneBookItemEntry">Год: <%=LinkGenerator.getYearLink(book.getYear())%>
                </div>
                <%
                } else {
                %>
                <div class="warning">Книга не найдена!</div>
                <%
                    }
                %>
            </div>
        </div>
    </section>

</body>
</html>
