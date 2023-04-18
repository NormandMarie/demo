package com.example.dao;

import com.example.model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.servlet.register.*;

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
                int id = result.getInt("id");
                String title = result.getString("title");
                String author = result.getString("author");
                String content = result.getString("content");
                Post post = new Post(id,title, author, content);
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
    public void deletePost(int postId) {
        final String sql = "DELETE FROM post WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, postId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}