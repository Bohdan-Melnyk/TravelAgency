<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Add room</title>

    <spring:url value="/resources/css/login.css" var="loginCss"/>
    <link type="text/css" href="${loginCss}" rel="stylesheet" media="screen,projection"/>
</head>
<body>
<div class="login-page">
    <div class="form">
<form action="/auth/logout" method="POST">
    <button style="background: #EF3B3A" type="submit">Logout </button>
</form>
<form action="/auth/success" method="get">
    <button style="background: lightblue" type="submit">Home </button>
</form>
        <form:form action="/management/addRoom" method="post" modelAttribute="room">
            <p>${message}</p>

            <div class="form-control">
                <label sfor="hotelName"><b>Hotel</b></label>
                <select style="font-family: Roboto, sans-serif" id="hotelName" name="hotelName">
                    <c:forEach var="hotel" items="${hotels}">
                        <option style="font-family: Roboto, sans-serif" value="${hotel.name}">${hotel.name}</option>
                    </c:forEach>
                </select>
                <p>  </p>


                <c:choose>
                    <c:when test="${numberError.defaultMessage.contains('Failed to convert property value of')}">
                        <div style="color:red;">Invalid type, please enter a number</div>
                    </c:when>
                    <c:otherwise>
                        <div style="color:red;">${numberError.defaultMessage}</div>
                    </c:otherwise>
                </c:choose>
                <label for="number">Number</label>
                <form:input path="number" id="Number"/>



                <c:choose>
                    <c:when test="${priceError.defaultMessage.contains('Failed to convert property value of')}">
                        <div style="color:red;">Invalid type, please enter a number</div>
                    </c:when>
                    <c:otherwise>
                        <div style="color:red;">${priceError.defaultMessage}</div>
                    </c:otherwise>
                </c:choose>
                <label for="prise">Price</label>
                <form:input path="prise" id="Price"/>


                <button type="submit">Add a room</button>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
