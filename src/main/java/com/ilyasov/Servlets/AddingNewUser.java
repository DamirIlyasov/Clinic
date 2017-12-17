package com.ilyasov.Servlets;

import com.ilyasov.Classes.Service;
import com.ilyasov.Classes.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ilyasov.Servlets.ListOfServices.services;
import static com.ilyasov.Servlets.LoginCheck.created;

/**
 * Created by damir on 09.11.16.
 */
public class AddingNewUser extends HttpServlet {
    String firstname;
    String lastname;
    String password;
    String passwordconf;
    String phoneNumber;
    String email;
    String age;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        firstname = request.getParameter("firstname");
        lastname = request.getParameter("lastname");
        password = request.getParameter("password");
        passwordconf = request.getParameter("passwordconf");
        phoneNumber = request.getParameter("phonenumber");
        email = request.getParameter("email");
        age = request.getParameter("age");


        try {
            if (!password.equals(passwordconf)){
                request.setAttribute("errorMessage","Ваши пароли не совпадают");
                request.getRequestDispatcher("jsp/RegistrationForm.jsp").forward(request,response);
            }
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/clinic";
            String login = "admin";
            String passwordd = "zub";
            Connection con = DriverManager.getConnection(url, login, passwordd);
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO users " +
                    "(firstname,lastname,password,phonenumber,email,age) " +
                    "VALUES (?,?,?,?,?,?)");
            if (checkNames(firstname)) {
                preparedStatement.setString(1, firstname);
            }else {
                request.setAttribute("errorMessage","Имя должно начинаться с большой буквы. Пример: Damir");
                request.getRequestDispatcher("jsp/RegistrationForm.jsp").forward(request,response);
            }
            if (checkNames(lastname)){
                preparedStatement.setString(2,lastname);
            }
            else {
                request.setAttribute("errorMessage","Фамилия должна начинаться с большой буквы. Пример: Ilyasov");
                request.getRequestDispatcher("jsp/RegistrationForm.jsp").forward(request,response);
            }
            if (checkPassword(password)){
                preparedStatement.setString(3,password);
            }
            else {
                request.setAttribute("errorMessage","Пароль может содержать только буквы и цифры.");
                request.getRequestDispatcher("jsp/RegistrationForm.jsp").forward(request,response);
            }
            if (checkPhoneNumber(phoneNumber)){
                preparedStatement.setString(4,phoneNumber);
            }
            else  {
                request.setAttribute("errorMessage","Некоректный номер телефона. Пример: 8-903-388-59-46");
                request.getRequestDispatcher("jsp/RegistrationForm.jsp").forward(request,response);
            }
            if (checkEmail(email)){
                preparedStatement.setString(5,email);
            }
            else {
                request.setAttribute("errorMessage","Некоректный email.");
                request.getRequestDispatcher("jsp/RegistrationForm.jsp").forward(request,response);
            }
            if (checkAge(age)){
                preparedStatement.setString (6,age);
            }
            else {
                request.setAttribute("errorMessage","Неверный возраст. Пример: 18");
                request.getRequestDispatcher("jsp/RegistrationForm.jsp").forward(request,response);
            }
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        User user = new User(2,firstname,lastname,phoneNumber,email,age);
        session.setAttribute("user",user);
        if (!created){
            try {
                services.clear();
                Class.forName("org.postgresql.Driver");
                String url = "jdbc:postgresql://localhost:5432/clinic";
                String login = "admin";
                String passwordd = "zub";
                Connection con = DriverManager.getConnection(url, login, passwordd);
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM services");

                while (resultSet.next()){
                    Service a = new Service();
                    a.setCost(Integer.parseInt(resultSet.getString("cost")));
                    a.setName(resultSet.getString("name"));
                    services.add(a);
                }
                session.setAttribute("services",services);
                created = true;

            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            session.setAttribute("services",services);
        }
        response.sendRedirect("/listofservices");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       response.sendRedirect("/registration");
    }
    boolean checkNames(String string){
        Pattern pattern = Pattern.compile("([A-z][a-z]*)");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
    boolean checkPassword(String string){
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
    boolean checkEmail(String string){
        Pattern pattern = Pattern.compile("^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
    boolean checkAge(String string){
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
    boolean checkPhoneNumber(String string){
        Pattern pattern = Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
