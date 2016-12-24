package Servlets;

import Classes.Comment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Comments extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List <Comment> comments = new LinkedList<Comment>();
            String service = request.getParameter("service");
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            String url = "jdbc:postgresql://localhost:5432/clinic";
            String login = "admin";
            String passwordd = "zub";

            Connection con = DriverManager.getConnection(url, login, passwordd);
            PreparedStatement preparedStatement = con.prepareStatement("SELECT author,comment FROM comments WHERE service = ?");
            preparedStatement.setString(1,service);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment a = new Comment();
                a.setAuthor(resultSet.getString("author"));
                a.setComment(resultSet.getString("comment"));
                comments.add(a);
            }
            request.setAttribute("comments",comments);
            request.setAttribute("serice",service);
            request.getRequestDispatcher("jsp/Comments.jsp").forward(request,response);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/Comments.jsp").forward(request,response);
    }
}
