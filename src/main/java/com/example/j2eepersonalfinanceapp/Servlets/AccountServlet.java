package com.example.j2eepersonalfinanceapp.Servlets;

import com.example.j2eepersonalfinanceapp.DatabaseConnection.DBConnection;
import com.example.j2eepersonalfinanceapp.models.Account;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;


@WebServlet(name = "accountServlet", urlPatterns = {"/account-servlet"})
public class AccountServlet extends HttpServlet {
    public AccountServlet() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void createAccount(int user_id, String accountName, String accountType, String balance) {
        String query = "INSERT INTO accounts (user_id, accountName, accountType, balance) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = new DBConnection().getConnection().prepareStatement(query);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setString(2, accountName); // Set index to 2 for accountName
            preparedStatement.setString(3, accountType); // Set index to 3 for accountType
            preparedStatement.setString(4, balance); // Set index to 4 for balance
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userid");

        String accountName = request.getParameter("accountName");
        String accountType = request.getParameter("accountType");
        String balance = request.getParameter("balance");

        createAccount(userId, accountName, accountType, balance);


        response.sendRedirect("financePage.jsp");
    }


    public void deleteAccount() {

    }

    public void updateAccount() {

    }

    public void getAccount() {

    }

    public void getAllAccounts() {

    }

}
