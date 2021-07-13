<%--
  Created by IntelliJ IDEA.
  User: БОГДАН
  Date: 08.07.2021
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>New User</title>
</head>
<body>
<form:form action="/managment/addUser" method="post" modelAttribute="user">
    <p>
        <label for="firstName">Name</label>
        <form:input path="firstName" id="Name" />
    </p>
    <p>
        <label for="lastName">SurName</label>
        <form:input path="lastName" id="Surname"/>
    </p>
    <p>
        <label for="email">Email</label>
        <form:input path="email" id="email"/>
    </p>
    <p>
        <label for="password">Password</label>
        <form:input path="password" id="Password"/>
    </p>
    <button type="submit">Create new user</button>
</form:form>>
</body>
</html>
