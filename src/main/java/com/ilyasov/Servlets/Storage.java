package com.ilyasov.Servlets;

import com.ilyasov.Classes.Service;
import com.ilyasov.Classes.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.ilyasov.Servlets.AddToStorage.storage;

/**
 * Created by damir on 12.11.16.
 */
public class Storage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sumcost = 0;
        request.setAttribute("storage", storage);
        for (int i = 0; i < storage.size(); i++){
            Service a = (Service) storage.get(i);
            sumcost += a.getCost();
        }
        request.setAttribute("sumcost",sumcost);
        request.getRequestDispatcher("jsp/Storage.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sumcost=0;
        HttpSession session = request.getSession();
        try {
            User user = (User) session.getAttribute("user");
            if (user.getStatus() == 1) {
                throw new NullPointerException();
            }
            for (int i = 0; i < storage.size(); i++){
                Service a = (Service) storage.get(i);
                sumcost += a.getCost();
            }
            request.setAttribute("sumcost",sumcost);
            request.setAttribute("storage", storage);
            request.getRequestDispatcher("jsp/Storage.jsp").forward(request, response);
        } catch (NullPointerException e) {
            request.setAttribute("storage", storage);
            response.sendRedirect("/listofservices");
        }
    }
}
