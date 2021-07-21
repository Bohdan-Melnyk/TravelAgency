<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>New Hotel</title>
</head>
<body>
<form class="btn__item" action="/auth/logout" method="POST">
    <button class="btn" type="submit">Logout </button>
</form>
<form class="btn__item" action="/auth/success" method="get">
    <button class="btn" type="submit">Home </button>
</form>
<form:form action="/management/addHotel" method="post" modelAttribute="hotel">
    <p>
        <br>
        <label for="name">Name</label>
        <form:input path="name" id="Name"/>
        <span style="color: red">
        <form:errors path="name"/>
    </span>
    </p>
    <p>
        </label for="country">Country</label>
        <form:input path="country" id="Country"/>
        <span style="color: red">
        <form:errors path="country"/>
    </span>
    </p>
    <p>
        <label for="city">City</label>
        <form:input path="city" id="city"/>
        <span style="color: red">
        <form:errors path="city"/>
    </span>
    </p>
    <button type="submit">Add a hotel</button>
</form:form>
</body>
</html>
