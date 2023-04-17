package com.example.demo.servlet;

import com.example.demo.dao.postDao;
import com.example.demo.dao.userDao;
import com.example.demo.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;

import static com.example.demo.servlet.register.*;

@WebServlet(urlPatterns = "/login")
public class login extends HttpServlet {
   public userDao userDao;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userDao = new userDao();
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            User user = userDao.verif(username, password);
            if (user != null) {
                // Si les informations de connexion sont correctes, on stocke les données de l'utilisateur dans la session et on redirige vers la page d'accueil
                HttpSession session = req.getSession();
                session.setAttribute("username", user.getUsername());
                session.setAttribute("password", user.getPassword());
                resp.sendRedirect(req.getContextPath());
            } else {
                // Si les informations de connexion sont incorrectes, on renvoie l'utilisateur vers la page de connexion avec un message d'erreur
                req.setAttribute("isError", true);
                req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestion de l'erreur
        }
    }
       /* String username = req.getParameter("username");
        String password = req.getParameter("password");
        final String requeteSQl = "SELECT * FROM user WHERE username = ? AND password = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            PreparedStatement statement = connection.prepareStatement(requeteSQl);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                resp.sendRedirect(req.getContextPath());
            } else {
                // Si les informations de connexion sont incorrectes, on renvoie l'utilisateur vers la page de connexion avec un message d'erreur
                req.setAttribute("isError", true);
                req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
                System.out.println("error");
            }

            // Fermeture de la connexion
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        }*/
 }

