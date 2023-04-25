package com.example.service;

import com.example.dao.CategoriesDao;

public class CategoryService {
    CategoriesDao categoriesDao =new CategoriesDao();

    public void deletecategory(int categoryId) {
        CategoriesDao.deletecategory(categoryId);
    }
    public  void updateCategory(String newName,int categoryId){
        CategoriesDao.updateCategory(newName,categoryId);
    }
}
