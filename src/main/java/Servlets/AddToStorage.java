package Servlets;

import Classes.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static Servlets.ListOfServices.services;
import static Servlets.LoginCheck.created;

/**
 * Created by damir on 12.11.16.
 */
public class AddToStorage extends HttpServlet {
    public static List storage = new LinkedList();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int number = new Integer(request.getParameter("number"));
        for (int i = 0; i < services.size(); i++){
            Service a = (Service) services.get(i);
            if (a.getNumber() == number){
                Service service = a;
                storage.add(service);
            }
        }
        response.sendRedirect("listofservices");
        //Cоздать клон подходящего сервиса и засунуть в корзину
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

