package com.example.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/secured/dashboard")
public class DashboardServlet extends HttpServlet {


    @Override
    protected void  doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        String   name = req.getParameter("name");
        String nom =req.getParameter("nom");
        req.setAttribute("name",name);
        req.setAttribute("nom",nom);
        req.setAttribute("MA_Cle",10);
        RequestDispatcher viewdash = req.getRequestDispatcher("/WEB-INF/dashboard.jsp");
        viewdash.forward(req,resp);
    }
}