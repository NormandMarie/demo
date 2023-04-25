package com.example.servlet;

import com.example.dao.PostDao;
import com.example.model.Post;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/secured/update")
public class updateServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       PostDao postDao = new PostDao();
        String sid = req.getParameter("id");
        int id = Integer.parseInt(sid);
        Post post;
        try {
            post = postDao.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("post", post);
        RequestDispatcher viewpost = req.getRequestDispatcher("/WEB-INF/update.jsp");
        viewpost.forward(req, resp);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String content = request.getParameter("content");
        String categoryName = request.getParameter("categoryName");
        PostDao postDao = new PostDao();
        postDao.updatePost(id,title,author,content,categoryName);
        System.out.println(categoryName);
        System.out.println(content);
        System.out.println(postDao);

    }
}
