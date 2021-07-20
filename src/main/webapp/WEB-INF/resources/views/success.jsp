<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<form class="btn__item" action="/auth/logout" method="POST">
    <button class="btn" type="submit">Logout </button>
</form>
<h1>Welcome ${user.firstName} ${user.lastName}</h1>

<form class="btn__item" action="/user/getHotels" method="get">
    <button class="btn" type="submit">Make an Order </button>
</form>

<c:if test = "${user.role eq 'ADMIN'}">
    <form class="btn__item" action="/user/all" method="get">
        <button class="btn" type="submit">All users </button>
    </form>
    <form class="btn__item" action="/management/addHotel" method="get">
        <button class="btn" type="submit">Add Hotel </button>
    </form>
    <form class="btn__item" action="/management/addRoom" method="get">
        <button class="btn" type="submit">Add room </button>
    </form>
</c:if>

</body>
</html>
