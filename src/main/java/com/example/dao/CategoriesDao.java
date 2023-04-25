package com.example.dao;

import com.example.model.Category;
import com.example.model.Post;
import com.example.service.DDBconnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.service.DDBconnect.connection;

public class CategoriesDao {

    public static List<Category> indexCategory() {
        List<Category> categories = null;
        try {
            Connection connection = DDBconnect.getConnection();

            String requeteSQL = "SELECT * FROM categories";

            PreparedStatement statement = connection.prepareStatement(requeteSQL);
            ResultSet result = statement.executeQuery();
            categories = new ArrayList<>();
            while (result.next()) {
                String name = result.getString("name");
                int categoriesid = result.getInt("categoriesID");
                Category categorie = new Category(categoriesid, name);
                categories.add(categorie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public Category creatCategory(Category category) throws SQLException {
        final String Sql = "INSERT INTO categories (name) VALUES (?)";
        Connection connection = DDBconnect.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(Sql, Statement.RETURN_GENERATED_KEYS)) {
            System.out.println(category.getName());
            statement.setString(1, category.getName());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    category.setId(generatedKeys.getInt(1));
                }
                System.out.println("Category " + category.getName() + " created with id " + category.getId());
            } else {
                System.err.println("Failed to create category " + category.getName());
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la création de la catégorie : " + ex.getMessage());
            return null;
        }
        return category;
    }


    public int findCategoryIdByName(String categoryName) {
        final String categorySql = "SELECT categoriesID FROM categories WHERE name = ?";
        Connection connection = DDBconnect.getConnection();
        int categoryId = 0;
        try (PreparedStatement categoryStatement = connection.prepareStatement(categorySql)) {
            categoryStatement.setString(1, categoryName);
            ResultSet categoryResult = categoryStatement.executeQuery();
            if (categoryResult.next()) {
                categoryId = categoryResult.getInt("categoriesID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryId;
    }
    public String findCategoryById(int categoryId) {
        final String categorySql = "SELECT name FROM categories WHERE categoriesID = ?";
        Connection connection = DDBconnect.getConnection();
        String categoryName = null;
        try (PreparedStatement categoryStatement = connection.prepareStatement(categorySql)) {
            categoryStatement.setInt(1, categoryId);
            ResultSet categoryResult = categoryStatement.executeQuery();
            if (categoryResult.next()) {
                categoryName = categoryResult.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryName;
    }
    public static void deletecategory(int categoryId) {
        final String sql = "DELETE FROM categories WHERE categoriesID = ?";
        Connection connection = DDBconnect.getConnection();
        try (
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, categoryId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateCategory(String newName, int categoryId){
  final String sql = "UPDATE categories SET name = ('?')WHERE categoriesID = ?";
        Connection connection = DDBconnect.getConnection();
        try (
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, newName);
            statement.setInt(2, categoryId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
