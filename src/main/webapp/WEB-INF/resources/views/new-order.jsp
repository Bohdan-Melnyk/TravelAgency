<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Order</title>
</head>
<body>
<form:form action="/management/addOrder/${userId}" method="post" modelAttribute="order">
    <h1>Add order</h1>
    <div>
        <label for="country">Country</label>
        <select id="country" name="country">
            <c:forEach var="country" items="${countries}">
                <option value="${country}">${country}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <label for="hotel">Hotel</label>
        <select id="hotel" name="hotel">
            <c:forEach var="hotel" items="${hotels}">
                <option value="${hotel}">${hotel}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <label for="room">Country</label>
        <select id="room" name="room">
            <c:forEach var="room" items="${rooms}">
                <option value="${room}">${room}</option>
            </c:forEach>
        </select>
    </div>
</form:form>
</body>
</html>
