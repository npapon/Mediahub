package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Fichier;
import beans.LectureFichierChannelNio2;
import constantes.Attributs;
import constantes.Repertoires;
import constantes.Vues;

@WebServlet( "/lecteurFichierChannelNio2" )
public class LecteurFichierChannelNio2 extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        LectureFichierChannelNio2 lectureFichierChannelNio2Bean = new LectureFichierChannelNio2();
        Fichier fichier = lectureFichierChannelNio2Bean.lireFichierChannelNio2Bean( "nigros.txt",
                Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_FICHIERSTEXT );
        HttpSession session = request.getSession();
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_FICHIERLU, fichier );
        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_LIREFICHIERAVECFILECHANNEL )
                .forward( request,
                        response );
    }

}
