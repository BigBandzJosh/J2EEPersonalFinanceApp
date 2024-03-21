<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <title>Personal Finance App</title>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
<body>
<div class="header">
    <h1>Personal Finance App</h1>
    <%
        String usernameHeader = (String) request.getSession().getAttribute("username");
    %> <h1>Welcome <%= usernameHeader%></h1>




</div>
<div class="container">
    <div class="login">
        <h1>Login</h1>
        <form method="post" action="${pageContext.request.contextPath}/login-servlet">
            <label for="username">Username:</label>
            <label for="login"></label><input type="text" id="login" name="username" required><br><br>
            <label for="password">Password:</label>
            <label for="loginPassword"></label><input type="password" id="loginPassword" name="password" required><br><br>
            <input type="submit" value="Login">
            <input type="reset" value="Reset">
        </form>
    </div>
<div class="registration">
    <h1>Registration</h1>

<form method="post" action="${pageContext.request.contextPath}/registration-servlet">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br><br>
    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" required><br><br>
    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" required><br><br>
    <label for="dob">Date of Birth:</label>
    <input type="date" id="dob" name="dob" required><br><br>
    <input type="submit" value="Register">
    <input type="reset" value="Reset">

    <a href="index.jsp">Login</a>
</form>
</div>
</div>

<div class="footer">
    <p>Personal Finance App</p>
</div>



</body>
</html>