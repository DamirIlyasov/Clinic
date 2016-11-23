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
public class RemoveService extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name ="";
        Integer number = new Integer(request.getParameter("number"));
        for (int i = 0; i < services.size(); i++){
            Service a = (Service) services.get(i);
            if (a.getNumber() == number){
                name = a.getName();
                break;
            }
        }
        services.clear();
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/clinic";
            String login = "admin";
            String passwordd = "zub";
            Connection con = DriverManager.getConnection(url, login, passwordd);
            PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM services WHERE name = (?)");
            preparedStatement.setString(1,name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM services");
            while (resultSet.next()){
                Service a = new Service();
                a.setName(resultSet.getString("name"));
                a.setCost(Integer.parseInt(resultSet.getString("cost")));
                a.setDescription(resultSet.getString("description"));
                services.add(a);
                a.setNumber();
            }
            HttpSession session = request.getSession();
            session.setAttribute("services",services);
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
