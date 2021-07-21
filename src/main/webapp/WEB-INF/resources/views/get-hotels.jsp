<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Hotels</title>

    <spring:url value="/resources/css/login.css" var="loginCss"/>
    <link type="text/css" href="${loginCss}" rel="stylesheet" media="screen,projection"/>
</head>
<body>
<div class="form">
<form action="/auth/logout" method="POST">
    <button style="background: #EF3B3A" type="submit">Logout </button>
</form>
<form action="/auth/success" method="get">
    <button style="background: lightblue" type="submit">Home </button>
</form>
<h1>Add order</h1>
<h3>Find hotel by country</h3>
<h4>Enter a country name</h4>
<c:url var="checkByCountry" value="/user/checkHotels"></c:url>
<sf:form method="get" action="${checkByCountry}">
    <input  name="countryName" value="${countryName}">
    <button type="submit">Check</button>
</sf:form>
<form class="btn__item" action="/user/getHotels" method="get">
    <button style="background: lightblue" type="submit">Reset </button>
</form>
<table class="tables" border="1">
    <thead>
    <tr>
        <th>Name</th>
        <th>Country</th>
        <th>City</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="hotel" items="${hotels}">
        <c:url var="bookLink" value="/user/getRooms/${hotel.id}"/>
        <sf:form method="get" action="${bookLink}">
            <tr>
                <td>${hotel.name}</td>
                <td>${hotel.country}</td>
                <td>${hotel.city}</td>
                <td>
                    <button type="submit">Order</button>
                </td>
            </tr>
        </sf:form>
    </c:forEach>
    </tbody>
</table>
</div>
</body>
</html>