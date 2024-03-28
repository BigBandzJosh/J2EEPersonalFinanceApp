package com.example.j2eepersonalfinanceapp.Servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


import com.example.j2eepersonalfinanceapp.DatabaseConnection.DBConnection;

import java.util.*;
import java.sql.*;
import com.google.gson.Gson;

@WebServlet(name = "chartServlet", urlPatterns = {"/chart-servlet"})
public class ChartServlet extends HttpServlet {

    public ChartServlet() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userid");

        Gson gsonObj = new Gson();
        Map<Object, Object> map;
        List<Map<Object, Object>> list = new ArrayList<>();
        String dataPoints;

        ResultSet resultSet = displayChart(userId);
        try {
            while (resultSet.next()) {
                map = new HashMap<>();
                map.put("label", resultSet.getString("accountName"));
                map.put("y", resultSet.getInt("balance"));
                list.add(map);
            }
            dataPoints = gsonObj.toJson(list);
            response.setContentType("application/json");
            response.getWriter().print(dataPoints);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public ResultSet getAccounts(int userId){
            String query = "SELECT * FROM accounts WHERE user_id = ?";
            try {
                PreparedStatement preparedStatement = new DBConnection().getConnection().prepareStatement(query);
                preparedStatement.setInt(1, userId);
                return preparedStatement.executeQuery();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    public ResultSet displayChart(int userId) {
        String query = "SELECT accountName, balance FROM accounts WHERE user_id = ?";
        try {
            PreparedStatement preparedStatement = new DBConnection().getConnection().prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
