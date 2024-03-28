package com.example.j2eepersonalfinanceapp.Servlets;

import com.example.j2eepersonalfinanceapp.DatabaseConnection.DBConnection;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.PreparedStatement;

@WebServlet(name = "deleteAccountServlet", urlPatterns = {"/delete-account-servlet"})
public class deleteAccountServlet extends HttpServlet {
    public deleteAccountServlet() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accountName = request.getParameter("delAccountName");
        deleteAccount(accountName);

            response.sendRedirect("financePage.jsp");

    }

    public void deleteAccount(String accountName) {
        String query = "DELETE FROM accounts WHERE accountName = ?";
        try {
            PreparedStatement preparedStatement = new DBConnection().getConnection().prepareStatement(query);
            preparedStatement.setString(1, accountName);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
