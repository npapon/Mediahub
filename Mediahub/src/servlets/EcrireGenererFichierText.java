package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CreerFichierTextForm;
import beans.Fichier;
import constantes.Attributs;
import constantes.Repertoires;
import constantes.Vues;

@WebServlet( "/EcrireGenererFichierText" )
public class EcrireGenererFichierText extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_ECRIREGENERERFICHIERTEXT ).forward( request,
                response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        CreerFichierTextForm creerFichierTextForm = new CreerFichierTextForm();
        Fichier fichier = creerFichierTextForm.CreerFichierText( request, Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_FICHIERS );
        HttpSession session = request.getSession();
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_FICHIERCREE, fichier );
        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_ECRIREGENERERFICHIERTEXT ).forward( request,
                response );
    }

}
