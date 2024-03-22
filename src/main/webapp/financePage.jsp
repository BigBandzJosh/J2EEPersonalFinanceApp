
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.j2eepersonalfinanceapp.models.Account" %>
<%@ page import="com.example.j2eepersonalfinanceapp.Servlets.getAccountServlet" %>
<%@ page import="java.sql.ResultSet" %>
<%--
  Created by IntelliJ IDEA.
  User: bigbandzjosh
  Date: 2024-03-21
  Time: 4:00 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your Finance</title>
</head>
<body>
<%
    String usernameHeader = (String) request.getSession().getAttribute("username");
%> <h1>Welcome <%= usernameHeader%></h1>

<%
    int userId = (int) request.getSession().getAttribute("userid");
%>
<h1>welcome <%= userId%></h1>
<h1>Finance</h1>
<p>Here are your finances</p>
<div>
<h1>Enter your accounts here</h1>
<form action="${pageContext.request.contextPath}/account-servlet" method="post">
    <label for="accountName">Account Name:</label>
    <input type="text" id="accountName" name="accountName">
    <label for="accountType">Account Type:</label>
    <input type="text" id="accountType" name="accountType">
    <label for="balance">Account Balance:</label>
    <input type="text" id="balance" name="balance">
    <input type="submit" value="Add Account">
</form>

    <h3>Here are your accounts and balances</h3>
    <table>
        <tr>
            <th>Account Name</th>
            <th>Account Type</th>
            <th>Account Balance</th>
        </tr>
        <%

           getAccountServlet getAccountServlet = new getAccountServlet();
            ResultSet rs = getAccountServlet.getAccounts(userId);
            while(rs.next()){

        %>
        <tr>
            <td><%= rs.getString("accountName") %></td>
            <td><%= rs.getString("accountType") %></td>
            <td><%= rs.getDouble("balance") %></td>
        </tr>

        <%
            }
        %>


        </table>

</div>

</body>
</html>
