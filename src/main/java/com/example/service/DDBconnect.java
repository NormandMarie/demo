package com.example.service;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
public class DDBconnect {

    public static Connection connection = null;
    public static String URL = "jdbc:mysql://localhost:3306/databaseexo";
    public static String USER = "root";
    public static String PASSWORD = "Marie121";

    public static Connection getConnection() {
        try {

            if (connection == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}