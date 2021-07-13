<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: БОГДАН
  Date: 13.07.2021
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User info</title>
</head>
<body>
<form:form modelAttribute="user">
    <h1>Info</h1>
    <p>
        ${user.firstName}
    </p>
    <p>
        ${user.lastName}
    </p>
</form:form>
</body>
</html>
