package com.example.dao;

import com.example.model.Category;
import com.example.model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.service.DDBconnect;
public class PostDao {


    public static List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        Connection connection = DDBconnect.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("SELECT post.id AS id,  post.title, post.author, post.content, categories.name ,categories.categoriesID FROM post INNER JOIN categories ON post.category_id = categories.categoriesID");
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                int id = result.getInt("id");
                String title = result.getString("title");
                String author = result.getString("author");
                String content = result.getString("content");
                String categoryName = result.getString("name");
                int categoriesid = result.getInt("categoriesID");
                Category category = new Category(categoriesid, categoryName);
                Post post = new Post(id, title, author, content, category);
                posts.add(post);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }



    public static Post createPost(String title, String author, String content, Category category) {
        final String postSql = "INSERT INTO post (title, author, content, category_id) VALUES (?, ?, ?, ?)";
        Connection connection = DDBconnect.getConnection();
        Post newPost = null;
        try (PreparedStatement postStatement = connection.prepareStatement(postSql, Statement.RETURN_GENERATED_KEYS)) {
            postStatement.setString(1, title);
            postStatement.setString(2, author);
            postStatement.setString(3, content);
            if (category != null) {
                postStatement.setInt(4, category.getId());
            } else {
                postStatement.setNull(4, java.sql.Types.INTEGER);
            }
            postStatement.executeUpdate();
            ResultSet generatedKeys = postStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                newPost = new Post(id, title, author, content, category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newPost;
    }



    public static Post createPostapi(Post post) {
        final String sql = "INSERT INTO post (title, content, author) VALUES (?, ?, ?)";
        Connection connection = DDBconnect.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getContent());
            statement.setString(3, post.getAuthor());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Insertion du post a échoué, aucune ligne n'a été insérée.");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                post.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Insertion du post a échoué, aucun ID n'a été généré.");
            }

            return post;

        } catch (SQLException ex) {
            System.err.println("Erreur lors de la création du post : " + ex.getMessage());
            return null;
        }
    }



    public static void deletePost(int postId) {
        final String sql = "DELETE FROM post WHERE id = ?";
                Connection connection = DDBconnect.getConnection();
        try (
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, postId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Post> getPostsByCategory(int categoryId) {
        final String sql = "SELECT * FROM post WHERE category_id = ?";
        List<Post> posts = new ArrayList<>();
        Connection connection = DDBconnect.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int postId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String content = resultSet.getString("content");


                Post post = new Post(title, author, content);
                post.setId(postId);
                Category category = new Category();
                category.setId(categoryId);
                post.setCategory(category);

                posts.add(post);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return posts;
    }
    public Post findById(int id) throws SQLException {
        final String sql = "SELECT post.*, categories.name AS category_name FROM post JOIN categories ON post.category_id = categoriesID WHERE post.id = ?";

        try {
            Connection connection = DDBconnect.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                int idr = result.getInt("id");
                String title = result.getString("title");
                String author = result.getString("author");
                String content = result.getString("content");
                String categoryName = result.getString("name");

                Category category = new Category(categoryName);
                return new Post(idr, title, author, content, category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public void updatePost(int id, String title, String author,String name, String content) {
        try { Connection connection = DDBconnect.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE post SET title=?, author=?, content=?, category_id=(SELECT categoriesID FROM categories WHERE name=?) WHERE id=?");
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setString(4, content);
            statement.setString(3, name);
            statement.setInt(5, id);
            System.out.println(statement);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Post updatePostApi(Post post) {
        try {
            Connection connection = DDBconnect.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE post SET title=?, author=?, content=? WHERE id=?");
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getAuthor());
            statement.setString(3, post.getContent());
            statement.setInt(4, post.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }
}