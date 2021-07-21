<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Welcome</title>

    <spring:url value="/resources/css/login.css" var="loginCss"/>
    <link type="text/css" href="${loginCss}" rel="stylesheet" media="screen,projection"/>
</head>
<body>
<div class="form">
    <div class="form-control">
<form  action="/auth/logout" method="POST">
    <button style="background: #EF3B3A" type="submit">Logout </button>
</form>
<h1>Welcome ${user.firstName} ${user.lastName}</h1>
<sec:authorize access="hasAuthority('developers:user')">
<form class="btn__item" action="/user/getHotels" method="get">
    <button class="btn" type="submit">Make an Order </button>
</form>
</sec:authorize>
<%--<c:if test = "${user.role eq 'ADMIN'}">--%>
<sec:authorize access="hasAuthority('developers:admin')">
    <form action="/user/all" method="get">
        <button type="submit">All users </button>
    </form>
    <form action="/management/addHotel" method="get">
        <button type="submit">Add Hotel </button>
    </form>
    <form  action="/management/addRoom" method="get">
        <button type="submit">Add room </button>
    </form>
</sec:authorize>
<%--</c:if>--%>
    </div>
</div>
</body>
</html>
