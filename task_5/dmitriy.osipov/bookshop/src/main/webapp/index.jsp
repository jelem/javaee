<html>
<head>
    <title>Bookshop</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link rel="stylesheet" href="/bookshop/stylesheet.css" type="text/css"/>
    <meta http-equiv="Content-Type" content="text/html" ; charset="utf-8">
</head>
<body>

<div class="grid-container">
    <%@ include file="jsp/template-static-import.jsp" %>

    <section class="mainSect">
        <p class="itemGreet">
            <%@ include file="/greetings.txt" %>
        </p>
    </section>

</div>
</body>
</html>