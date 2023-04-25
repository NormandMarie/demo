package com.example.servlet;

import com.example.dao.CategoriesDao;
import com.example.dao.PostDao;
import com.example.model.Category;
import com.example.model.Post;
import com.example.service.PostService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/secured/posts")
public class PostServlet extends HttpServlet {


    PostDao postDao;
    CategoriesDao categoriesDao;
    PostService postService;
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        categoriesDao = new CategoriesDao();
        postDao = new PostDao();
        List<Post> posts = postDao.findAll();
        req.setAttribute("posts", posts);
        List<Category>  categories = categoriesDao.indexCategory();
        req.setAttribute("categories", categories);
        RequestDispatcher viewpost = req.getRequestDispatcher("/WEB-INF/post-list.jsp");
        viewpost.forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        postDao = new PostDao();
        postService = new PostService();
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String content = req.getParameter("content");
        String category = req.getParameter("category");
        PostService postService = new PostService();
        Post  newPost = postService.creatPost(title, author, content, category);


        System.out.println(title);
        System.out.println(author);
        System.out.println(category);
        System.out.println(newPost);
        resp.sendRedirect(req.getContextPath() + "/secured/posts");

    }
}