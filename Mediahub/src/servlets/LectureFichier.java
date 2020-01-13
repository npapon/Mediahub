package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Fichier;
import beans.LecteurFichier;
import constantes.Attributs;
import constantes.Repertoires;
import constantes.Vues;

@WebServlet( "/LectureFichier" )
public class LectureFichier extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        LecteurFichier lecteurfichier = new LecteurFichier();
        Fichier fichier = lecteurfichier.lireFichier( "nico.txt", Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_FICHIERS );
        HttpSession session = request.getSession();
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_FICHIERLU, fichier );
        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_LECTUREFICHIER ).forward( request,
                response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    }

}
