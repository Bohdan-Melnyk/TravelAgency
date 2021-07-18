
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Login</title>
</head>
<body>
<div class="container">
    <form class="form-signin" method="post" action="/auth/login">
        <h2 class="form-signin-heading">Please sign in</h2>
        <div class="form-control">
            <p>
                <label for="username" class="sr-only">Username</label>
                <input type="text" id="username" name="username" placeholder="Username" required autofocus>
            </p>
            <p>
                <label for="password" class="sr-only">Password</label>
                <input type="password" id="password" name="password"  placeholder="Password" required>
            </p>
        </div>
        <div class="btn__block">
            <button class="btn" type="submit">Sign in</button>
            <button class="btn"><a class="btn_out" href="/registration/register">Sign up</a></button>
        </div>
    </form>
</div>
</body>

</html>
