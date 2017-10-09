<section class="logoSect">
	<img src="http://localhost:8088/bookshop/img/logo.png" id="logo"/>
	<p id="headText">Добро пожаловать!</p>
</section>

<section class="menuSect">
	<ul class="menu">
		<!--li><a href="index.jsp">На главную страницу</a></li-->
		<li><a href="/bookshop">На главную страницу</a></li>
        <li><a href="/bookshop/servlet/allbooks">Просмотреть все книги</a></li>
        <li><a href="/bookshop/servlet/formgen?condition=title&method=get&title=названию">Поиск по названию</a></li>
        <li><a href="/bookshop/servlet/formgen?condition=author&method=get&title=автору">Поиск по автору</a></li>
        <li><a href="/bookshop/servlet/formgen?condition=year&method=get&title=году">Поиск по году</a></li>
        <%--<li><a href="/bookshop/servlet/newbook">Добавить новую книгу</a></li>--%>
        <li><a href="/bookshop/jsp/newbook.jsp">Добавить новую книгу</a></li>
    </ul>
</section>

<section class="footerSect">
    <p class="copyright">Выполнено в качестве домашнего задания по курсу Java EE</p>
</section>
