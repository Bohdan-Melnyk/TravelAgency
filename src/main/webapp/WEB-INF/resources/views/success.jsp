<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gut</title>
</head>
<body>
<%@include file="/header.html" %>
<form class="btn__item" action="/auth/logout" method="POST">
    <button class="btn" type="submit">Logout </button>
</form>
<h1>Welcome</h1>

<form class="btn__item" action="/management/addUser" method="POST">
    <button class="btn" type="submit">Add user </button>
</form>
<form class="btn__item" action="/user/all" method="get">
    <button class="btn" type="submit">All users </button>
</form>
<form class="btn__item" action="/user/addOrder/" method="get">
    <button class="btn" type="submit">Make an Order </button>
</form>
<form class="btn__item" action="/management/addHotel" method="get">
    <button class="btn" type="submit">Add Hotel </button>
</form>
<form class="btn__item" action="/management/addRoom" method="get">
    <button class="btn" type="submit">Add room </button>
</form>
</body>
</html>
