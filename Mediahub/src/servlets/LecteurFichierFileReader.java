package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Fichier;
import beans.LecteurFichierFileReaderBean;
import constantes.Attributs;
import constantes.Repertoires;
import constantes.Vues;

/**
 * Servlet implementation class LecteurFichierFIleReader
 */
@WebServlet( "/LecteurFichierFileReader" )
public class LecteurFichierFileReader extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        LecteurFichierFileReaderBean lecteurFichierFileReaderBean = new LecteurFichierFileReaderBean();
        Fichier fichier = lecteurFichierFileReaderBean.lireFichierFileReaderBean( "nigros.txt",
                Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_FICHIERS );
        HttpSession session = request.getSession();
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_FICHIERLU, fichier );
        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_LIREFICHIERAVECFILEREADER ).forward( request,
                response );
    }

}
