package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.EcrireFichierChannelNio2Bean;
import beans.Fichier;
import constantes.Attributs;
import constantes.Repertoires;
import constantes.Vues;

@WebServlet( "/EcrireFichierChannelNio2" )
public class EcrireFichierChannelNio2 extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_ECRIREFICHIERAVECCHANNELNIO2 ).forward( request,
                response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        EcrireFichierChannelNio2Bean ecrireFichierChannelNio2Bean = new EcrireFichierChannelNio2Bean();
        Fichier fichier = ecrireFichierChannelNio2Bean.creerFichierChannelNio2( request,
                Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_FICHIERSTEXT );
        HttpSession session = request.getSession();
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_FICHIERCREE, fichier );
        response.sendRedirect( Vues.CONSTANTE_VUE_ECRIREGENERERFICHIERTEXTFILECHANNEL_NOMCOURT );

    }

}
