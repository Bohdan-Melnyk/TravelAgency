<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Order</title>
</head>
<body>
<%@include file="/header.html" %>
<%--<form:form action="/management/addOrder/${userId}" method="post" >--%>
<h1>Add order</h1>
<div style="color:red;">${error}</div>
<%--    <div>--%>
<%--        <label for="country">Country</label>--%>
<%--        <select id="country" name="country">--%>
<%--            <c:forEach var="country" items="${countries}">--%>
<%--                <option value="${country}">${country}</option>--%>
<%--            </c:forEach>--%>
<%--        </select>--%>
<%--    </div>--%>
<%--    <div>--%>
<%--        <label for="hotel">Hotel</label>--%>
<%--        <select id="hotel" name="hotel">--%>
<%--            <c:forEach var="hotel" items="${hotels}">--%>
<%--                <option value="${hotel.name}">${hotel.name}</option>--%>
<%--            </c:forEach>--%>
<%--        </select>--%>
<%--    </div>--%>
<%--    <div>--%>
<%--        <label for="room">Room</label>--%>
<%--        <select id="room" name="room">--%>
<%--            <c:forEach var="room" items="${rooms}">--%>
<%--                <option value="${room.number}">${room.number}</option>--%>
<%--            </c:forEach>--%>
<%--        </select>--%>
<%--    </div>--%>

<table border="1">
    <thead>
    <tr>
        <th>Id</th>
        <th>Hotel</th>
        <th>Price</th>
        <th>Number</th>
        <th>Country</th>
        <th>Arrival</th>
        <th>Departure</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="room" items="${rooms}">
        <c:url var="orderLink" value="/management/addOrder/${userId}">
            <c:param name="roomId" value="${room.id}"/>
        </c:url>
        <sf:form method="post" action="${orderLink}">
            <tr>
                <td> ${room.id}</td>
                <td>${room.hotelinroom.name}</td>
                <td>${room.prise}</td>
                <td>${room.number}</td>
                <td>${room.hotelinroom.country}</td>
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
