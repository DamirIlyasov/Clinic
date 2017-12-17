package com.ilyasov.Servlets;

import com.ilyasov.Classes.Service;
import com.ilyasov.Classes.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

import static com.ilyasov.Servlets.ListOfServices.services;

/**
 * Created by damir on 09.11.16.
 */
public class LoginCheck extends HttpServlet {
    public static boolean created = false;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/clinic";
            String login = "admin";
            String passwordd = "zub";
            Connection con = DriverManager.getConnection(url, login, passwordd);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                String emaill = resultSet.getString("email");
                String pass = resultSet.getString("password");
                if (email.equals("Admin") & password.equals("zub")) {
                    if (created) {
                        HttpSession session = request.getSession();
                        session.setAttribute("services", services);
                    } else {
                        resultSet = statement.executeQuery("SELECT * FROM services");
                        try {
                            while (resultSet.next()) {
                                Service a = new Service();
                                a.setCost(Integer.parseInt(resultSet.getString("cost")));
                                a.setName(resultSet.getString("name"));
                                services.add(a);
                                a.setNumber();
                            }
                            HttpSession session = request.getSession();
                            session.setAttribute("services", services);
                            created = true;
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    User user = new User(3, "Admin");
                    HttpSession session = request.getSession();
                    if (request.getParameter("createcookie") != null) {
                        Cookie cookie = new Cookie("status", "3");
                        response.addCookie(cookie);
                    }
                    session.setAttribute("user", user);
                    response.sendRedirect("/listofservices");
                    con.close();
                    return;
                }
                if (email.equals(emaill) && password.equals(pass)) {
                    String firstname = resultSet.getString("firstname");
                    String lastName = resultSet.getString("lastname");
                    String phonenumber = resultSet.getString("phonenumber");
                    String age = resultSet.getString("age");
                    User user = new User(2, firstname, lastName, phonenumber, email, age);
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", user);
                    if (request.getParameter("createcookie") != null) {
                        Cookie cookie = new Cookie("status", "2");
                        response.addCookie(cookie);
                    }
                    if (created) {
                        session.setAttribute("services", services);
                    } else {
                        resultSet = statement.executeQuery("SELECT * FROM services");
                        try {
                            while (resultSet.next()) {
                                Service a = new Service();
                                a.setCost(Integer.parseInt(resultSet.getString("cost")));
                                a.setName(resultSet.getString("name"));
                                services.add(a);
                                a.setNumber();
                            }
                            session.setAttribute("services", services);
                            created = true;
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    response.sendRedirect("/listofservices");
                    con.close();
                    return;
                }
            }
            con.close();
            HttpSession session = request.getSession();
            session.setAttribute("errormessage", "Вы ввели неверные данные.");
            response.sendRedirect("/login");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
