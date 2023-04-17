package com.example.demo.servlet;
import com.example.demo.dao.postDao;
import com.example.demo.dao.userDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class register extends HttpServlet {
    public userDao userDao;
    public static final String DB_URL = "jdbc:mysql://localhost:3306/databaseexo";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "Marie121";
    private static final String INSERT_SQL = "INSERT INTO user (username, password) VALUES (?, ?)";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userDao = new userDao();
        String username = req.getParameter("usernamere");
        String password = req.getParameter("passwordre");
        userDao.creat(username,password);
        /* try {
            // Connexion à la base de données
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String username = req.getParameter("usernamere");
            String password = req.getParameter("passwordre");

            // Insertion des informations de l'utilisateur dans la base de données
            PreparedStatement statement = connection.prepareStatement(INSERT_SQL);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            resp.sendRedirect(req.getContextPath() + "/secured/posts");
            connection.close();
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }*/
        resp.sendRedirect(req.getContextPath() + "/secured/posts");
    }
}
