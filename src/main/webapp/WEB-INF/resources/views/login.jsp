
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div class="container">
    <form class="form-signin" method="post" action="/auth/login">
        <h2 class="form-signin-heading">Login</h2>
        <p>
            <label for="username">Username</label>
            <input type="text" name="username" id="username" class="form-control" required>
        </p>
        <p>
            <label for="password">Password</label>
            <input type="password" name="password" id="password" class="form-control" required>
        </p>
        <button class="btn btn-lg btn-primary btn-block">Sign in</button>
    </form>
</div>
</body>
</html>
