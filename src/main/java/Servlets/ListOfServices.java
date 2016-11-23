package Servlets;

import Classes.Service;
import Classes.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static Servlets.LoginCheck.created;

/**
 * Created by damir on 09.11.16.
 */
public class ListOfServices extends HttpServlet {
    public static List services = new LinkedList();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        if (user.getStatus() == 1) {
            if (!created) {
                try {
                    services.clear();
                    Class.forName("org.postgresql.Driver");
                    String url = "jdbc:postgresql://localhost:5432/clinic";
                    String login = "admin";
                    String passwordd = "zub";
                    Connection con = DriverManager.getConnection(url, login, passwordd);
                    Statement statement = con.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM services");

                    while (resultSet.next()) {
                        Service a = new Service();
                        a.setCost(Integer.parseInt(resultSet.getString("cost")));
                        a.setName(resultSet.getString("name"));
                        a.setDescription(resultSet.getString("description"));
                        services.add(a);
                        a.setNumber();
                    }
                    session.setAttribute("services", services);
                    created = true;
                    request.getRequestDispatcher("jsp/ListOfServicesForGuest.jsp").forward(request, response);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                session.setAttribute("services", services);
                request.getRequestDispatcher("jsp/ListOfServicesForGuest.jsp").forward(request, response);
            }
            request.getRequestDispatcher("jsp/ListOfServicesForGuest.jsp").forward(request, response);
        }

        request.getRequestDispatcher("jsp/ListOfServicesForUser.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        try {
            session.getAttribute("user");
        } catch (NullPointerException e) {
            request.getRequestDispatcher("jsp/ListOfServicesForGuest.jsp").forward(request, response);
        }
        if (session.isNew()) {
            if (!created) {
                try {
                    services.clear();
                    Class.forName("org.postgresql.Driver");
                    String url = "jdbc:postgresql://localhost:5432/clinic";
                    String login = "admin";
                    String passwordd = "zub";
                    Connection con = DriverManager.getConnection(url, login, passwordd);
                    Statement statement = con.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM services");

                    while (resultSet.next()) {
                        Service a = new Service();
                        a.setCost(Integer.parseInt(resultSet.getString("cost")));
                        a.setName(resultSet.getString("name"));
                        a.setDescription(resultSet.getString("description"));
                        services.add(a);
                        a.setNumber();
                    }
                    session.setAttribute("services", services);
                    created = true;
                    request.getRequestDispatcher("jsp/ListOfServicesForGuest.jsp").forward(request, response);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                session.setAttribute("services", services);
                request.getRequestDispatcher("jsp/ListOfServicesForGuest.jsp").forward(request, response);
            }
        } else {
            try {
                User user = (User) session.getAttribute("user");
                if (user.getStatus() == 2) {
                    request.getRequestDispatcher("jsp/ListOfServicesForUser.jsp").forward(request, response);
                }
                if (user.getStatus() == 3) {
                    if (!created) {
                        try {
                            services.clear();
                            Class.forName("org.postgresql.Driver");
                            String url = "jdbc:postgresql://localhost:5432/clinic";
                            String login = "admin";
                            String passwordd = "zub";
                            Connection con = DriverManager.getConnection(url, login, passwordd);
                            Statement statement = con.createStatement();
                            ResultSet resultSet = statement.executeQuery("SELECT * FROM services");

                            while (resultSet.next()) {
                                Service a = new Service();
                                a.setCost(Integer.parseInt(resultSet.getString("cost")));
                                a.setName(resultSet.getString("name"));
                                a.setDescription(resultSet.getString("description"));
                                services.add(a);
                                a.setNumber();
                            }
                            session.setAttribute("services", services);
                            created = true;

                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else {
                        session.setAttribute("services", services);
                    }
                    request.getRequestDispatcher("jsp/AdminPage.jsp").forward(request, response);
                }
            } catch (NullPointerException e) {
                request.getRequestDispatcher("jsp/ListOfServicesForGuest.jsp").forward(request, response);
            }
        }

        try {
            User user = (User) session.getAttribute("user");
            if (user.getStatus() == 1) {
                if (!created) {
                    try {
                        services.clear();
                        Class.forName("org.postgresql.Driver");
                        String url = "jdbc:postgresql://localhost:5432/clinic";
                        String login = "admin";
                        String passwordd = "zub";
                        Connection con = DriverManager.getConnection(url, login, passwordd);
                        Statement statement = con.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM services");

                        while (resultSet.next()) {
                            Service a = new Service();
                            a.setCost(Integer.parseInt(resultSet.getString("cost")));
                            a.setName(resultSet.getString("name"));
                            a.setDescription(resultSet.getString("description"));
                            services.add(a);
                            a.setNumber();
                        }
                        session.setAttribute("services", services);
                        created = true;
                        request.getRequestDispatcher("jsp/ListOfServicesForGuest.jsp").forward(request, response);

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    session.setAttribute("services", services);
                    request.getRequestDispatcher("jsp/ListOfServicesForGuest.jsp").forward(request, response);
                }

            }
        } catch (NullPointerException e) {
            if (!created) {
                try {
                    services.clear();
                    Class.forName("org.postgresql.Driver");
                    String url = "jdbc:postgresql://localhost:5432/clinic";
                    String login = "admin";
                    String passwordd = "zub";
                    Connection con = DriverManager.getConnection(url, login, passwordd);
                    Statement statement = con.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM services");

                    while (resultSet.next()) {
                        Service a = new Service();
                        a.setCost(Integer.parseInt(resultSet.getString("cost")));
                        a.setName(resultSet.getString("name"));
                        a.setDescription(resultSet.getString("description"));
                        services.add(a);
                        a.setNumber();
                    }
                    session.setAttribute("services", services);
                    created = true;
                    request.getRequestDispatcher("jsp/ListOfServicesForGuest.jsp").forward(request, response);

                } catch (ClassNotFoundException q) {
                    q.printStackTrace();
                } catch (SQLException c) {
                    c.printStackTrace();
                }
            } else {
                session.setAttribute("services", services);
                request.getRequestDispatcher("jsp/ListOfServicesForGuest.jsp").forward(request, response);
            }
        }
    }
}
