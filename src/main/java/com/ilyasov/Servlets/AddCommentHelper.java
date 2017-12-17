package com.ilyasov.Servlets;

import com.ilyasov.Classes.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

public class AddCommentHelper extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:postgresql://localhost:5432/clinic";
        String login = "admin";
        String passwordd = "zub";
        try {
            HttpSession session = request.getSession();

            String service = request.getParameter("service");
            String comment = request.getParameter("comm");
            User user = (User) session.getAttribute("user");
            String author = user.getFirstName();
            Connection con = DriverManager.getConnection(url, login, passwordd);
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO comments (author, comment, service, created_at) VALUES (?,?,?,?)");
            preparedStatement.setString(1,author);
            preparedStatement.setString(2,comment);
            preparedStatement.setString(3,service);
            preparedStatement.setDate(4,new Date(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
            response.sendRedirect("/listofservices");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
