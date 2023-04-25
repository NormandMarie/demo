package com.example.service;

import com.example.dao.CategoriesDao;
import com.example.dao.PostDao;
import com.example.model.Category;
import com.example.model.Post;

import java.sql.SQLException;
import java.util.List;

import static com.example.dao.PostDao.createPost;

public class PostService {
    private static PostDao postDao = new PostDao();
    public static CategoriesDao categoriesDao = new CategoriesDao();

    public Post creatPost(String title, String author, String content, String categoryName) {
        try {
            int categoryId = categoriesDao.findCategoryIdByName(categoryName);
            Category newCategory = null;
            if (categoryId == 0) {
                newCategory = categoriesDao.creatCategory(new Category(categoryName));

            }
            Post newPost = postDao.createPost(title, author, content, newCategory);
            return newPost;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
      public List<Post> getPostsByCategory(int categoryId){
          return postDao.getPostsByCategory(categoryId);
      }

      public String findCategoryById (int categoryId){
          return categoriesDao.findCategoryById(categoryId);
      }
}