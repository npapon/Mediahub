package servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.MenuAdministration;
import constantes.Attributs;
import constantes.Vues;

@WebServlet( "/Administration" )
public class Administration extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        MenuAdministration menuadministation = new MenuAdministration();
        ServletContext context = getServletContext();
        context.setAttribute( Attributs.CONSTANTE_ATTRIBUT_MENUADMINISTRATION, menuadministation.initierMenu() );

        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_ADMINISTRATION ).forward( request,
                response );
    }

}
