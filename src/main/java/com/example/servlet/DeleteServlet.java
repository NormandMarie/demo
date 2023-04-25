package com.example.servlet;

import com.example.dao.CategoriesDao;
import com.example.dao.PostDao;
import com.example.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/secured/delete")
public class DeleteServlet extends HttpServlet {
    CategoryService categoryService = new CategoryService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        PostDao postDao = new PostDao();
        postDao.deletePost(id);
        request.getRequestDispatcher("/secured/posts").forward(request, response);

    }

}
