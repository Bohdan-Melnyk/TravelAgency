<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>New Hotel</title>

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
        <form:form action="/management/addHotel" method="post" modelAttribute="hotel">

            <h2>Please Add hotel</h2>
            <p>
                <form:errors path="country"/>
                <br>
            <label for="name">Name</label>
            <form:input path="name" id="Name"/>
            </p>
            <p>
                <form:errors path="name"/>
                </label for="country">Country</label>
            <form:input path="country" id="Country"/>
            </p>
            <p>
                <form:errors path="city"/>
                <label for="city">City</label>
            <form:input path="city" id="city"/>
            </p>
            <button type="submit">Add a hotel</button>
        </form:form>
    </div>
</div>

</body>
</html>
