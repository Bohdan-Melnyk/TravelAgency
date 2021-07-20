<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hotels</title>
</head>
<body>
<form class="btn__item" action="/auth/logout" method="POST">
    <button class="btn" type="submit">Logout </button>
</form>
<form class="btn__item" action="/auth/success" method="get">
    <button class="btn" type="submit">Home </button>
</form>
<h1>Add order</h1>
<table border="1">
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
</body>
</html>