package com.example.j2eepersonalfinanceapp.Servlets;

import com.example.j2eepersonalfinanceapp.DatabaseConnection.DBConnection;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "loginServlet", urlPatterns = {"/login-servlet"})
public class loginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Authenticate user
        int userId = authenticateUser(username, password);

        if (userId != -1) {
            // Set user ID and username in session upon successful login
            request.getSession().setAttribute("userid", userId);
            request.getSession().setAttribute("username", username);
            response.sendRedirect("financePage.jsp"); // Redirect to finance page
        } else {
            // Handle unsuccessful login
            response.sendRedirect("index.jsp?loginError=true"); // Redirect to login page with error message
        }
    }


    public int authenticateUser(String username, String password) {
        String query = "SELECT userid, password FROM users WHERE username = ?";
        try {
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String hashedPassword = resultSet.getString("password");
                if (BCrypt.checkpw(password, hashedPassword)) {
                    // If password matches, return user ID
                    return resultSet.getInt("userid");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if authentication fails
    }

}
