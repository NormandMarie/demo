package com.example.demo.service;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
public class DDBconnect {

    private static Connection connection = null;
    static String URL = "jdbc:mysql://localhost:3306/databaseexo";
    static String USER = "root";
    static String PASSWORD = "Marie121";

    public static Connection getConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}