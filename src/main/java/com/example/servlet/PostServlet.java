package com.example.servlet;

import com.example.dao.postDao;
import com.example.model.Post;
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

import static com.example.servlet.register.*;

@WebServlet(urlPatterns = "/secured/posts")
public class PostServlet extends HttpServlet {


    postDao postDao;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        postDao = new postDao();
        List<Post> posts = postDao.index();
        req.setAttribute("posts", posts);
        RequestDispatcher viewpost = req.getRequestDispatcher("/WEB-INF/post-list.jsp");
        viewpost.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        postDao = new postDao();
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String content = req.getParameter("content");
        Post newPost = postDao.createPost(title, author, content);
        resp.sendRedirect(req.getContextPath() + "/secured/posts");

    }
}