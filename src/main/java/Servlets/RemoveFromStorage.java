package Servlets;

import Classes.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static Servlets.AddToStorage.storage;

/**
 * Created by damir on 13.11.16.
 */
public class RemoveFromStorage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int number = new Integer(request.getParameter("number"));
        for (int i = 0; i < storage.size() ; i++){
            Service asa = (Service) storage.get(i);
            if (asa.getNumber() == number){
                storage.remove(i);
                break;
            }
        }
        request.setAttribute("storage",storage);
        response.sendRedirect("/storage");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
