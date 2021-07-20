<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--<%@ include file="/WEB-INF/jsp/include.jsp" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>

<head>
    <title>Users</title>
</head>

<body>
<table border="1">
    <thead>
    <tr>
        <th>Id</th>
        <th>First Name</th>
        <th>SurName</th>
        <th>Email</th>
        <th>Action</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="user" items="${myusers}">
        <sf:form method="get" action="/user/all">
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>
                    <a href = "<c:url value ="/management/order/${user.id}"/>">See Orders</a>
                </td>
            </tr>
        </sf:form>
    </c:forEach>
    </tbody>
</table>
</body>

</html>

