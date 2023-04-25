package com.example.dao;

import com.example.model.User;
import com.example.service.DDBconnect;

import java.sql.*;


public class UserDao {
    public User verif(String username, String password) throws SQLException {
        String requeteSQl = "SELECT * FROM user WHERE username = ? AND password = ?";

        try {
            Connection connection = DDBconnect.getConnection();
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
        }


    }

    public User creat(String username, String password) {
        final String INSERT_SQL = "INSERT INTO user (username, password) VALUES (?, ?)";
        User user = null;
        try {
            Connection connection = DDBconnect.getConnection();

            PreparedStatement statement = connection.prepareStatement(INSERT_SQL);
            statement.setString(1, username);
            statement.setString(2, password);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                user = new User(username, password);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}