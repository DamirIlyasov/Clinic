package com.ilyasov.Servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by damir on 09.11.16.
 */
public class LogOut extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        Cookie cookie[] = request.getCookies();
        for (int i = 0; i < cookie.length; i++) {
            if (cookie[i].getName().equals("status")) {
                cookie[i].setMaxAge(0);
                response.addCookie(cookie[i]);
                break;
            }
        }
        response.sendRedirect("/login");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
