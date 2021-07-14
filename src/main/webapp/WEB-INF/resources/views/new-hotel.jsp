<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>New Hotel</title>
</head>
<body>
<form:form action="/management/addHotel" method="post" modelAttribute="hotel">
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
