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
import java.util.List;

import static com.example.service.PostService.categoriesDao;

@WebServlet(urlPatterns = "/secured/category")
public class CategoryListServlet extends HttpServlet {
    PostService postService = new PostService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        int id = Integer.parseInt(sid);

        List<Post> posts = postService. getPostsByCategory(id);
        req.setAttribute("posts", posts);

        String category = postService.findCategoryById(id);

        req.setAttribute("category", category);
        RequestDispatcher viewpost = req.getRequestDispatcher("/WEB-INF/list-post-category.jsp");
        viewpost.forward(req, resp);

    }
}
