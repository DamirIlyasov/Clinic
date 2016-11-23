package Servlets;

import Classes.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

import static Servlets.ListOfServices.services;

/**
 * Created by damir on 09.11.16.
 */
public class AddingNewService extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String namee = request.getParameter("name");
        int costt = Integer.parseInt(request.getParameter("cost"));
        String description = request.getParameter("description");
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/clinic";
            String login = "admin";
            String passwordd = "zub";
            Connection con = DriverManager.getConnection(url, login, passwordd);
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO services " +
                    "(name, cost, description) " +
                    "VALUES (?,?,?)");

            preparedStatement.setString(1,namee);
            preparedStatement.setInt(2,costt);
            preparedStatement.setString(3,description);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM services");
            services.clear();
            while (resultSet.next()){
                Service a = new Service();
                a.setCost(Integer.parseInt(resultSet.getString("cost")));
                a.setName(resultSet.getString("name"));
                a.setDescription(resultSet.getString("description"));
                services.add(a);
                a.setNumber();
            }
            con.close();
            HttpSession session = request.getSession();
            session.setAttribute("services",services);
            session.setAttribute("text","Добавлено.");
            response.sendRedirect("/listofservices");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
