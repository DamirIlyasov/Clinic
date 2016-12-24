package Servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static Servlets.LoginCheck.created;

public class EditServiceHelper extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String oldName = request.getParameter("oldName");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            int cost = Integer.parseInt(request.getParameter("cost"));

            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/clinic";
            String login = "admin";
            String passwordd = "zub";
            Connection con = DriverManager.getConnection(url, login, passwordd);
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE services SET name = (?) ,description = (?) ,cost = (?) WHERE name = (?) ");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, cost);
            preparedStatement.setString(4, oldName);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
            created = false;
            HttpSession session = request.getSession();
            session.setAttribute("text","Услуга успешно изменена!");
            response.sendRedirect("/listofservices");
        } catch (Exception e) {
            HttpSession session = request.getSession();
            session.setAttribute("text", "Вы ввели неверную цену услуги.");
            response.sendRedirect("/listofservices");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
