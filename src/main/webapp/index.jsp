<html>

<head>
    <title>Login Page</title>

</head>
<body>
<%@include file="header.html" %>

<div>

    <form method="POST" action="${pageContext.request.contextPath}/login">
        <h2>Log in</h2>

        <div>
            <label>
                <input name="email" type="text" placeholder="Email"/>
            </label>
            <label>
                <input name="password" type="password" placeholder="Password"/>
            </label>

            <button type="submit" ><a href="hello-world.jsp"> Log In</a></button>
            <h4><a href="${pageContext.request.contextPath}/management/addUser">Create an account</a></h4>
        </div>

    </form>

</div>
</body>
</html>
