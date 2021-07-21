<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Register</title>

  <spring:url value="/resources/css/login.css" var="loginCss"/>
  <link type="text/css" href="${loginCss}" rel="stylesheet" media="screen,projection"/>

</head>
<body>

<div class="login-page">
  <div class="form">

<form:form class="login-form" method="post" action="/registration/register" modelAttribute="user">
<h2>Please sign in</h2>
  <div class="form-control">
<p>
  <label for="email" >Email </label>
  <form:input   type="text" path="email" class="email" id="Email" placeholder="Email"/>
  <form:errors path="email"/>
</p>
<p>
  <label for="firstName" >First name</label>
  <form:input type="text" id="firstName" path="firstName" class="firstname" placeholder="First name"/>
  <form:errors path="firstName"/>
</p>
<p>
  <label for="lastName" >Last name</label>
  <form:input type="text" id="lastName" path="lastName" class="lastname" placeholder="Last name"/>
  <form:errors path="lastName"/>
</p>
  <label for="password" >Password</label>
  <form:input type="password" id="password" path="password" class="password" placeholder="Password"/>
  <form:errors path="password"/>
  </p>
    <button type="submit">Sign up</button>
    </div>

  <p class="message" > Already registered? <a href="/auth/login"> Sign In</a> </p>
</form:form>
</div>
</div>
</body>
</html>

