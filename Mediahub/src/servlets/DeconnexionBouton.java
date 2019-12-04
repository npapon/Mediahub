package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constantes.Attributs;
import constantes.Vues;

@WebServlet( "/DeconnexionBouton" )
public class DeconnexionBouton extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_LIENDECONNEXION, Vues.CONSTANTE_VUE_DECONNEXION_NOMCOURT );

        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_DECONNEXIONBOUTON ).forward( request,
                response );
    }

}
