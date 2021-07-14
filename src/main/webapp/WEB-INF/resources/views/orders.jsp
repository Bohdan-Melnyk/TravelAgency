<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orers</title>
</head>
<body>
<h1>Orders for ${user.firstName} ${user.lastName}</h1>
<br>
<table border="1">
    <thead>
    <tr>
        <th>Order Id</th>
        <th>Hotel</th>
        <th>Room</th>
        <th>Arrival Date</th>
        <th>Departure Date</th>
        <th>OrderDate</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var="order" items="${orders}">
    <sf:form method="get" action="/user/all">
        <tr>
            <td>${order.id}</td>
            <td>${order.hotelinorder.name}</td>
            <td>${order.room.number}</td>
            <td>${order.arrivalDate}</td>
            <td>${order.departureDate}</td>
            <td>${order.orderDate}</td>
        </tr>
    </sf:form>
</c:forEach>
</table>
</body>
</html>
