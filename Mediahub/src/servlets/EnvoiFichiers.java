package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.EnvoiFichiersForm;
import beans.Fichier;
import constantes.Attributs;
import constantes.Repertoires;
import constantes.Vues;

@WebServlet( "/EnvoiFichiers" )
public class EnvoiFichiers extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_ENVOIFICHIERS ).forward( request,
                response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        EnvoiFichiersForm envoifichiersform = new EnvoiFichiersForm();
        Fichier fichier = envoifichiersform.envoyerFichier( request );
        HttpSession session = request.getSession();
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_FICHIER, fichier );

        fichier.setChemin( this.getServletConfig().getInitParameter(
                Repertoires.CONSTANTE_REPERTOIRE_VARIABLE_CHEMIN ) );
        String chemin = fichier.getChemin();

        envoifichiersform.ecrireFichier( fichier.getParametresFichier(), fichier.getNom(), chemin );

        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_ENVOIFICHIERS ).forward( request, response );
    }

}
