package com.example.j2eepersonalfinanceapp.Servlets;

import com.example.j2eepersonalfinanceapp.DatabaseConnection.DBConnection;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.PreparedStatement;

@WebServlet(name = "updateAccountServlet", urlPatterns = {"/update-account-servlet"})
public class updateAccountServlet extends HttpServlet {

    public updateAccountServlet() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int accountid = Integer.parseInt(request.getParameter("updateAccountId"));
        String accountName = request.getParameter("updateAccountName");
        String accountType = request.getParameter("updateAccountType");
        String balance = request.getParameter("updateBalance");
        updateAccount(accountid, accountName, accountType, balance);

        response.sendRedirect("financePage.jsp");
    }

    public void updateAccount(int accountid, String accountName, String accountType, String balance) {
        String query = "UPDATE accounts SET accountName= ?, accountType = ?, balance = ? WHERE accountid = ?";
        try {
            PreparedStatement preparedStatement = new DBConnection().getConnection().prepareStatement(query);
            preparedStatement.setString(1, accountName);
            preparedStatement.setString(2, accountType);
            preparedStatement.setString(3, balance);
            preparedStatement.setInt(4, accountid);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
