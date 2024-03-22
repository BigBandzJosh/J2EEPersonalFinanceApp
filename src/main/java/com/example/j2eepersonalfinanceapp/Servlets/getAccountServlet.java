package com.example.j2eepersonalfinanceapp.Servlets;

import com.example.j2eepersonalfinanceapp.DatabaseConnection.DBConnection;
import com.example.j2eepersonalfinanceapp.models.Account;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "getAccountServlet", urlPatterns = {"/get-account-servlet"})
public class getAccountServlet extends HttpServlet {

    public getAccountServlet() {
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public ResultSet getAccounts(int userId) {
        String query = "SELECT * FROM accounts WHERE user_id = ?";
        try {
            PreparedStatement preparedStatement = new DBConnection().getConnection().prepareStatement(query);
            preparedStatement.setInt(1, userId);
            return preparedStatement.executeQuery();

            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userid");

        ResultSet resultSet = getAccounts(userId);
        session.setAttribute("accounts", resultSet);

        response.sendRedirect("financePage.jsp");
    }


}
