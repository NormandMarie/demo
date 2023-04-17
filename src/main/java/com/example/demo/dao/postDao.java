package com.example.demo.dao;

import com.example.demo.model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.servlet.register.*;

public class postDao {
    public List<Post> index() {
        final String requeteSQl = "SELECT * FROM post ";
        List<Post> posts = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            PreparedStatement statement = connection.prepareStatement(requeteSQl);
            ResultSet result = statement.executeQuery();
            posts = new ArrayList<>();
            while (result.next()) {
                String title = result.getString("title");
                String author = result.getString("author");
                String content = result.getString("content");
                Post post = new Post(title, author, content);
                posts.add(post);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public Post createPost(String title, String author, String content) {
        final String sql = "INSERT INTO post (title, author, content) VALUES (?, ?, ?)";

        Post newPost = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);


            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setString(3, content);
            statement.executeUpdate();
            newPost = new Post(title, author, content);


            //connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return newPost;
    }
}