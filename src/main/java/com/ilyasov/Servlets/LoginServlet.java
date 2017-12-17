package com.ilyasov.Servlets;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by damir on 08.11.16.
 */
public class LoginServlet extends HttpServlet {
    static boolean check = false;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/LoginForm.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie[] = request.getCookies();

       if (cookie.length != 0) {
           for (int i = 0; i < cookie.length; i++) {
               if (!cookie[i].getName().equals("status")){
                   request.getRequestDispatcher("jsp/LoginForm.jsp").forward(request, response);
               }
           }
       } else {
           response.sendRedirect("/listofservice");
       }

    }
}

