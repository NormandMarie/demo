package com.example.servlet;

import com.example.dao.PostDao;
import com.example.model.Category;
import com.example.model.Post;
import com.example.service.CategoryService;
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

@WebServlet(urlPatterns = "/secured/updateC")
public class UpdateCatServlet extends HttpServlet {
    CategoryService categoryService = new CategoryService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String sid = req.getParameter("id");
        int id = Integer.parseInt(sid);
        PostService postService = new PostService();


           String category = postService.findCategoryById(id);
        req.setAttribute("id",id);
        req.setAttribute("category",category);
        RequestDispatcher viewpost = req.getRequestDispatcher("/WEB-INF/updateCat.jsp");
        viewpost.forward(req, resp);

    }

}
