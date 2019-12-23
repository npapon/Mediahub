package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Utilisateur;
import constantes.Attributs;
import constantes.Parametres;
import constantes.Vues;
import scripts.Script;

@WebServlet( "/ListeScripts" )
public class ListeScripts extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_LISTESCRIPTSVUE, Vues.CONSTANTE_VUE_LISTESCRIPTS_NOMCOURT );
        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_LISTESCRIPTS ).forward( request,
                response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String scriptsasupprimer = request.getParameter( Parametres.CONSTANTE_PARAMETRE_SCRIPTS_A_SUPPRIMER );
        String[] scriptsasupprimerTableau = scriptsasupprimer.split( "," );

        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute( Attributs.CONSTANTE_ATTRIBUT_SESSION );

        for ( String code : scriptsasupprimerTableau ) {
            Script script = new Script();
            script.setCode( code );

            utilisateur.getListeScripts().remove( script.getCode() );
        }

        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_SESSION, utilisateur );
        response.sendRedirect( Vues.CONSTANTE_VUE_LISTESCRIPTS_NOMCOURT );
    }

}
