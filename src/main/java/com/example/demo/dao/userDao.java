package com.example.demo.dao;

import com.example.demo.model.Post;
import com.example.demo.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.servlet.register.*;

public class userDao {
    public User verif(String username, String password) throws SQLException {
        String requeteSQl = "SELECT * FROM user WHERE username = ? AND password = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement statement = connection.prepareStatement(requeteSQl);
            {

                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet result = statement.executeQuery();

                if (result.next()) {
                    String userUsername = result.getString("username");
                    String userPassword = result.getString("password");
                    return new User(userUsername, userPassword);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public User creat(String username, String password) {
            final String INSERT_SQL = "INSERT INTO user (username, password) VALUES (?, ?)";
            User user = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

                PreparedStatement statement = connection.prepareStatement(INSERT_SQL);
                statement.setString(1, username);
                statement.setString(2, password);
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    user = new User(username, password);
                }
                connection.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return user;
        }

    }