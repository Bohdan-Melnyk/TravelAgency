<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Rooms</title>

    <spring:url value="/resources/css/login.css" var="loginCss"/>
    <link type="text/css" href="${loginCss}" rel="stylesheet" media="screen,projection"/>
</head>
<body>

<div class="form_table">
<form action="/auth/logout" method="POST">
    <button style="background: #EF3B3A" type="submit">Logout </button>
</form>
<form  action="/auth/success" method="get">
    <button style="background: lightgreen" type="submit">Home </button>
</form>
<h1>Add order</h1>
<div style="color:red;">${error}</div>
<div style="color:#ff0000;">${dateError}</div>
<h3>Looking for a certain date?</h3>
<c:url var="freeRooms" value="/user/checkRooms/${hotelId}"></c:url>
<sf:form method="get" action="${freeRooms}">
    <input type="date" name="arrivalDate" value="${arrivalDate}">
    <input type="date" name="departureDate" value="${departureDate}">
    <button type="submit">Check</button>
</sf:form>
<table class="tables" border="1">
    <thead>
    <tr>
        <th>Number</th>
        <th>Price per night</th>
        <th>Arrival</th>
        <th>Departure</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="room" items="${rooms}">
        <c:url var="bookLink" value="/user/addOrder/${hotelId}">
            <c:param name="roomId" value="${room.id}"/>
        </c:url>
        <sf:form method="post" action="${bookLink}">
            <tr>
                <td>${room.number}</td>
                <td>${room.prise}</td>
                <td>
                    <input type="date" name="arrivalDate" value="${arrivalDate}">
                </td>
                <td>
                    <input type="date" name="departureDate" value="${departureDate}">
                </td>
                <td>
                    <button type="submit">Order</button>
                </td>
            </tr>
        </sf:form>
    </c:forEach>
    </tbody>
</table>
<%--<input name="arrivalDate" value="${arrivalDate}">--%>
<%--<br>--%>
<%--<input name="departureDate" value="${departureDate}">--%>
<%----%>
</div>
</body>
</html>