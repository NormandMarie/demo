package com.example.demo.servlet;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/secured/posts")
public class PostServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostService postService = new PostService();
        List<Post> postList = postService.indexPosts();

        req.setAttribute("posts",postList);
        RequestDispatcher viewpost = req.getRequestDispatcher("/WEB-INF/post-list.jsp");
        viewpost.forward(req,resp);

        resp.sendRedirect(req.getContextPath() + "/secured/posts");
    }
}
