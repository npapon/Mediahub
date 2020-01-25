package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CreerFichierTextFormFileChannel;
import beans.Fichier;
import constantes.Attributs;
import constantes.Repertoires;
import constantes.Vues;

@WebServlet( "/EcrireGenererFichierTextFileChannel" )
public class EcrireGenererFichierTextFileChannel extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_ECRIREGENERERFICHIERTEXTFILECHANNEL ).forward( request,
                response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        CreerFichierTextFormFileChannel creerFichierTextFormFileChannel = new CreerFichierTextFormFileChannel();
        Fichier fichier = creerFichierTextFormFileChannel.creerFichierTextFileChannel( request,
                Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_FICHIERS );
        HttpSession session = request.getSession();
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_FICHIERCREE, fichier );
        response.sendRedirect( Vues.CONSTANTE_VUE_ECRIREGENERERFICHIERTEXTFILECHANNEL_NOMCOURT );
    }

}
