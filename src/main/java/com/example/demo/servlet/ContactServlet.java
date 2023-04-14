package com.example.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/contact")
public class ContactServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "bienvenue sur la page contact";
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getProtocol() + " " + req.getMethod() + " =>/contact");
        String name =req.getParameter("name");
        String nom =req.getParameter("nom");


        resp.setContentType("text/html");

        // Hello
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
        out.println("<a href=hello-servlet> servelet</a>");
        out.println("<p> bonjour "+name+" "+nom+"</p>");

    }
}
