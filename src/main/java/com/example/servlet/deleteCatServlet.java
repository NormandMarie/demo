package com.example.servlet;

import com.example.dao.PostDao;
import com.example.model.Post;
import com.example.service.CategoryService;
import com.example.service.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns ="/secured/deletec" )
public class deleteCatServlet extends HttpServlet {
    CategoryService categoryService = new CategoryService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        PostService postService = new PostService();

        List<Post> posts = null;
        try {
            posts = postService.getPostsByCategory(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (!posts.isEmpty()) {
            request.setAttribute("errorMessage", "Vous ne pouvez pas supprimer cette catégorie car elle contient des posts.");
            request.getRequestDispatcher("/Vous ne pouvez pas supprimer cette catégorie car elle contient des posts.").forward(request, response);
            return;
        }
        categoryService.deletecategory(id);
        request.getRequestDispatcher("/secured/posts").forward(request, response);
    }
}
