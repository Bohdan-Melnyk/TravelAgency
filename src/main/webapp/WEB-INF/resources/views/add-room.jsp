<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Add room</title>
</head>
<body>
<%@include file="/header.html" %>
<form:form action="/management/addRoom" method="post" modelAttribute="room" methodParam="hotelName">
    <p>${message}</p>
    <table>
        <div>
            <label for="hotelName">Hotel</label>
            <select id="hotelName" name="hotelName">
                <c:forEach var="hotel" items="${hotels}">
                    <option value="${hotel.name}">${hotel.name}</option>
                </c:forEach>
            </select>
        </div>

        <div>
            <label for="number">Number</label>
            <form:input path="number" id="Number"/>
        </div>

        <div >
            <label for="prise">Price</label>
            <form:input path="prise" id="Price"/>
        </div>

        <tr>
            <td><input type="submit" value="Add a room"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>
