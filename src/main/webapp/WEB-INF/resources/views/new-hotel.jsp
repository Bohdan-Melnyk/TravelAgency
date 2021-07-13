<%--
  Created by IntelliJ IDEA.
  User: БОГДАН
  Date: 10.07.2021
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>New Hotel</title>
</head>
<body>
<form:form action="/managment/addHotel" method="post" modelAttribute="hotel">
    <p>
        <label for="name">Name</label>
        <form:input  path="name" id="Name"/>
    </p>
    <p>
        </label for="country">Country</label>
        <form:input path="country" id="Country" />
    </p>
    <p>
        <label for="city">City</label>
        <form:input path="city" id="city"/>
    </p>
    <button type="submit">Add a hotel</button>
</form:form>
</body>
</html>
