package servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.MenuEditeur;
import constantes.Attributs;
import constantes.Vues;

@WebServlet( "/Menu" )
public class Menu extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        MenuEditeur menuediteur = new MenuEditeur();
        ServletContext context = getServletContext();
        context.setAttribute( Attributs.CONSTANTE_ATTRIBUT_MENU, menuediteur.initierMenu() );

        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_MENU ).forward( request,
                response );
    }

}
