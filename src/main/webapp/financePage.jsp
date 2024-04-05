
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.j2eepersonalfinanceapp.models.Account" %>
<%@ page import="com.example.j2eepersonalfinanceapp.Servlets.getAccountServlet" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.example.j2eepersonalfinanceapp.Servlets.ChartServlet" %>
<link rel="stylesheet" href="style.css">
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
<div class="header">
    <h1>Personal Finance App</h1>
<%
    String usernameHeader = (String) request.getSession().getAttribute("username");
%> <h1>Welcome <%= usernameHeader%> - username</h1>

<%
    int userId = (int) request.getSession().getAttribute("userid");
%>
<h1>welcome <%= userId%> - userId</h1>
<p>Here are your finances</p>
</div>

<div class="container">
    <div class="card">
        <h1>Add Account</h1>
<form action="${pageContext.request.contextPath}/account-servlet" method="post">
    <label for="accountName">Account Name:</label>
    <input type="text" id="accountName" name="accountName">
    <label for="accountType">Account Type:</label>
    <input type="text" id="accountType" name="accountType">
    <label for="balance">Account Balance:</label>
    <input type="text" id="balance" name="balance">
    <input type="submit" value="Add Account">
</form>
</div>

    <div class="card">
        <h1>Update Account</h1>
        <form method="post" action="${pageContext.request.contextPath}/update-account-servlet">
            <label for="updateAccountId">Account ID:</label>
            <input type="text" id="updateAccountId" name="updateAccountId">
            <label for="updateAccountName">Account Name:</label>
            <input type="text" id="updateAccountName" name="updateAccountName">
            <label for="updateAccountType">Account Type:</label>
            <input type="text" id="updateAccountType" name="updateAccountType">
            <label for="updateBalance">Account Balance:</label>
            <input type="text" id="updateBalance" name="updateBalance">
            <input type="submit" value="Update Account">
        </form>
    </div>
    <div class="card">
        <h1>Accounts</h1>
    <table>
        <tr>
            <th>Account ID</th>
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
            <td><%= rs.getInt("accountId") %></td>
            <td><%= rs.getString("accountName") %></td>
            <td><%= rs.getString("accountType") %></td>
            <td><%= rs.getDouble("balance") %></td>
        </tr>

        <%
            }
        %>
        </table>

</div>

<div class="card">
    <h1>Delete Account</h1>
    <form method="post" action="${pageContext.request.contextPath}/delete-account-servlet">
        <label for="delAccountName">Account Name:</label>
        <input type="text" id="delAccountName" name="delAccountName">
        <input type="submit" value="Delete Account" onclick="alertFunc()">
    </form>
</div>

    <script type="text/javascript">
        window.onload = function() {

            <%
            ChartServlet chartServlet = new ChartServlet();
            ResultSet accounts = chartServlet.getAccounts(userId);
            if (accounts.next() != false) {
            %>

            var chart = new CanvasJS.Chart("chartContainer", {
                animationEnabled: true,
                title: {
                    text: "Account Balances"
                },
                data: [{
                    // Change type to "bar", "area", "spline", "pie",etc.
                    type: "bar",
                    yValueFormatString: "$#,##0",
                    risingColor: "#50cdc8",
                    fallingColor: "#ff6969",
                    indexLabel: "{label} {y}",
                    dataPoints: [
                        <% while(accounts.next()){ %>
                        {y: <%= accounts.getDouble("balance") %>, label: "<%= accounts.getString("accountName") %>"},

                        <% } %>
                    ]
                }]
            });
            chart.render();
            <% } %>

        }
    </script>

    <div id="chartContainer" style="height: 600px; width: 100%;"></div>

    <script src="https://cdn.canvasjs.com/canvasjs.min.js"></script>

    <div class="card">
        <h1>Logout</h1>
        <form method="post" action="${pageContext.request.contextPath}/logout-servlet">
            <input type="submit" value="Logout">
        </form>
    </div>



</div>


</body>
<script src="javascript/alert.js"></script>
</html>
