<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Register</title>
</head>
<body>
<form:form class="form-signin" method="post" action="/registration/register" modelAttribute="user">
<h2>Please sign in</h2>
<p>
  <label for="email" >Email </label>
  <form:input type="text" path="email" id="Email" placeholder="Email"/>
  <form:errors path="email"/>
</p>
<p>
  <label for="firstName" >First name</label>
  <form:input type="text" id="firstName" path="firstName" class="form-control" placeholder="First name"/>
  <form:errors path="firstName"/>
</p>
<p>
  <label for="lastName" >Last name</label>
  <form:input type="text" id="lastName" path="lastName" class="form-control" placeholder="Last name"/>
  <form:errors path="lastName"/>
</p>
  <label for="password" >Password</label>
  <form:input type="password" id="password" path="password" class="form-control" placeholder="Password"/>
  <form:errors path="password"/>
  </p>
  <button type="submit">Sign up</button>
</form:form>
</div>
</body>
</html>

