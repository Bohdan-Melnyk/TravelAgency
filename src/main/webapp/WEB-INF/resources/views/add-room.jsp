<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Add room</title>
</head>
<body>
<form class="btn__item" action="/auth/logout" method="POST">
    <button class="btn" type="submit">Logout </button>
</form>
<form class="btn__item" action="/auth/success" method="get">
    <button class="btn" type="submit">Home </button>
</form>
<form:form action="/management/addRoom" method="post" modelAttribute="room">
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
            <c:choose>
                <c:when test="${numberError.defaultMessage.contains('Failed to convert property value of')}">
                    <div style="color:red;">Invalid type, please enter a number</div>
                </c:when>
            </c:choose>
            <label for="number">Number</label>
            <form:input path="number" id="Number"/>
            <span style="color: red">
        <form:errors path="number"/>
    </span>
        </div>

        <div>
            <c:choose>
                <c:when test="${priceError.defaultMessage.contains('Failed to convert property value of')}">
                    <div style="color:red;">Invalid type, please enter a number</div>
                </c:when>
            </c:choose>
            <label for="prise">Price</label>
            <form:input path="prise" id="Price"/>
            <span style="color: red">
        <form:errors path="prise"/>
    </span>
        </div>

        <tr>
            <td><input type="submit" value="Add a room"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>
