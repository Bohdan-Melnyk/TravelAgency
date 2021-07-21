<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Gut</title>

    <spring:url value="/resources/css/login.css" var="loginCss"/>
    <link type="text/css" href="${loginCss}" rel="stylesheet" media="screen,projection"/>
</head>
<body>
<%@include file="/header.html" %>
<div class="form">
<div class="form-control">
<form action="/auth/logout" method="POST">
    <button style="background: #EF3B3A" type="submit">Logout </button>
</form>
<h1>Welcome</h1>
<sec:authorize access="hasAuthority('developers:admin')">
<form class="btn__item" action="/management/addUser" method="POST">
    <button class="btn" type="submit">Add user </button>
</form>
</sec:authorize>
<sec:authorize access="hasAuthority('developers:user')">
<form class="btn__item" action="/user/all" method="get">
    <button class="btn" type="submit">All users </button>
</form>
</sec:authorize>
<sec:authorize access="hasAuthority('developers:user')">
<form class="btn__item" action="/user/addOrder/" method="get">
    <button class="btn" type="submit">Make an Order </button>
</form>
</sec:authorize>
<sec:authorize access="hasAuthority('developers:admin')">
<form class="btn__item" action="/management/addHotel" method="get">
    <button class="btn" type="submit">Add Hotel </button>
</form>
</sec:authorize>

<sec:authorize access="hasAuthority('developers:admin')">
<form class="btn__item" action="/management/addRoom" method="get">
    <button class="btn" type="submit">Add room </button>
</form>
</sec:authorize>
</div>
    </div>
</body>
</html>
