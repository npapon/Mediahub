package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CreerScriptForm;
import beans.Date;
import beans.Utilisateur;
import constantes.Attributs;
import constantes.MessagesErreur;
import constantes.Vues;
import scripts.Script;

@WebServlet( "/CreerScript" )
public class Creerscript extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_CREERSCRIPT ).forward( request,
                response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        CreerScriptForm creerscriptform = new CreerScriptForm();
        Script script = creerscriptform.creerScript( request );

        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute( Attributs.CONSTANTE_ATTRIBUT_SESSION );

        Date date = new Date();
        try {
            utilisateur.getListeScripts().put( script.getCode(), script );
        } catch ( NullPointerException e ) {
            System.out.println( MessagesErreur.CONSTANTE_ERREUR_CONNEXION );
        }
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_SESSION, utilisateur );
        response.sendRedirect( Vues.CONSTANTE_VUE_LISTESCRIPTS_NOMCOURT );
    }

}
