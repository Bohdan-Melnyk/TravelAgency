<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rooms</title>
</head>
<body>
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
<table border="1">
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
</body>
</html>