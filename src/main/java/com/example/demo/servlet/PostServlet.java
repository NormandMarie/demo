package com.example.demo.servlet;

import com.example.demo.dao.postDao;
import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.servlet.register.*;

@WebServlet(urlPatterns = "/secured/posts")
public class PostServlet extends HttpServlet {


    postDao postDao;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*final String requeteSQl = "SELECT * FROM post ";

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
        }*/
       // initialiser l'instance de postDao dans la méthode init()
        postDao = new postDao();
        List<Post> posts = postDao.index();
        req.setAttribute("posts", posts);
        RequestDispatcher viewpost = req.getRequestDispatcher("/WEB-INF/post-list.jsp");
        viewpost.forward(req, resp);

        resp.sendRedirect(req.getContextPath() + "/secured/posts");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        postDao = new postDao();
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String content = req.getParameter("content");
        Post newPost = postDao.createPost(title, author, content);
        resp.sendRedirect(req.getContextPath() + "/secured/posts");
        /* try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String title = req.getParameter("title");
            String author = req.getParameter("author");
            String content = req.getParameter("content");
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setString(3, content);
            statement.executeUpdate();
            resp.sendRedirect(req.getContextPath() + "/secured/posts");

            //connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/
    }
}