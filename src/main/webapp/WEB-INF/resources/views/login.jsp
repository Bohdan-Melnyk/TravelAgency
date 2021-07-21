<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <%--<meta name="viewport"
              content="width=device-width, initial-scale=1, shrink-to-fit=no">--%>
    <title>Login</title>
    <spring:url value="/resources/css/login.css" var="loginCss"/>
    <link type="text/css" href="${loginCss}" rel="stylesheet" media="screen,projection"/>

</head>
<body>

<div class="login-page">
    <div class="form">
        <form class="login-form" method="post" action="/auth/login">
            <h2>Please sign in</h2>
            <div class="form-control">
                <p>
                    <label for="username" class="email">Username</label>
                    <input type="text" id="username" name="username" placeholder="Username" required autofocus>
                </p>
                <p>
                    <label for="password" class="password">Password</label>
                    <input type="password" id="password" name="password"  placeholder="Password" required>
                </p>
            </div>

            <button class="login" type="submit">Sign in</button>
            <p class="message"><a href="/registration/register">Create an account</a></p>

        </form>
    </div>
</div>
</body>

</html>
