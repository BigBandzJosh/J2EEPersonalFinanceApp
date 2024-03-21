package com.example.j2eepersonalfinanceapp.Servlets;

import com.example.j2eepersonalfinanceapp.DatabaseConnection.DBConnection;
import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.PreparedStatement;

@WebServlet(name = "registrationServlet", urlPatterns = {"/registration-servlet"})
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dateOfBirth = request.getParameter("dob");

        // Insert into database
        registerUser(username, hashedPassword, email, firstName, lastName, dateOfBirth);

        response.sendRedirect("index.jsp");
    }

    private void registerUser(String username, String hashedPassword, String email, String firstName, String lastName, String dateOfBirth) {
        String query = "INSERT INTO users (username, password, email, first_name, last_name, DateOfBirth) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = new DBConnection().getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, firstName);
            preparedStatement.setString(5, lastName);
            preparedStatement.setString(6, dateOfBirth);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

