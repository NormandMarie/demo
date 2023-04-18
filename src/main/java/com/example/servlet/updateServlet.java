package com.example.servlet;

import com.example.model.Post;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/secured/update")
public class updateServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       com.example.dao.postDao postDao = new com.example.dao.postDao();
        String sid = req.getParameter("id");
        int id = Integer.parseInt(sid);
        Post post = postDao.findById(id);
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
        com.example.dao.postDao postDao = new com.example.dao.postDao();
        postDao.updatePost(id,title,author,content);
        response.sendRedirect(request.getContextPath() + "/secured/posts");
    }
}
