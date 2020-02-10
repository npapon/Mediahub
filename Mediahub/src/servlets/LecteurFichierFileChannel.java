package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Fichier;
import beans.LecteurFichierFileChannelBean;
import constantes.Attributs;
import constantes.Repertoires;
import constantes.Vues;

@WebServlet( "/LecteurFichierFileChannel" )
public class LecteurFichierFileChannel extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        LecteurFichierFileChannelBean lecteurFichierFileChannelBean = new LecteurFichierFileChannelBean();
        Fichier fichier = lecteurFichierFileChannelBean.lireFichierFileChannelBean( "nigros.txt",
                Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_FICHIERSTEXT );
        HttpSession session = request.getSession();
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_FICHIERLU, fichier );
        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_LIREFICHIERAVECFILECHANNEL )
                .forward( request,
                        response );
    }

}
