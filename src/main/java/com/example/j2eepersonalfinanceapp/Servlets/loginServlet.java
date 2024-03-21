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
        request.getSession().setAttribute("username", username);
        String password = request.getParameter("password");
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Select from database
        loginUser(username, hashedPassword);

        response.sendRedirect("index.jsp");
    }

    public boolean loginUser(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ?";
        try {
            DBConnection connection = new DBConnection();
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String hashedPassword = resultSet.getString("password");
                return BCrypt.checkpw(password, hashedPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}