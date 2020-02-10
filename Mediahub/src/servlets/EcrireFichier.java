package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.EcriteurFichier;
import beans.Fichier;
import beans.LecteurFichier;
import constantes.Attributs;
import constantes.Repertoires;
import constantes.Vues;

@WebServlet( "/EcrireFichier" )
public class EcrireFichier extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        LecteurFichier lecteurfichier = new LecteurFichier();
        Fichier fichierlu = lecteurfichier.lireFichier( "nico.txt", Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_FICHIERSTEXT );
        HttpSession session = request.getSession();
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_FICHIERLU, fichierlu );
        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_ECRITUREFICHIER ).forward( request,
                response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        EcriteurFichier ecriteurFichier = new EcriteurFichier();
        Fichier fichierecri = ecriteurFichier.ecrireFichierParDuplication( "nico.txt", "valoche.txt",
                Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_FICHIERSTEXT,
                Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_FICHIERSTEXT );
        HttpSession session = request.getSession();
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_FICHIERECRI, fichierecri );

        response.sendRedirect( Vues.CONSTANTE_VUE_ECRITUREFICHIER_NOMCOURT );

    }

}
