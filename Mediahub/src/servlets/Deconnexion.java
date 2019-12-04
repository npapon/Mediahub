package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constantes.SitesExternes;

@WebServlet( "/Deconnexion" )
public class Deconnexion extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession Session = request.getSession();
        Session.invalidate();
        response.sendRedirect( SitesExternes.CONSTANTE_SITEEXTERNE_OPENCLASSROOMFORMATIONJEE );
    }

}
