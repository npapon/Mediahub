package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ConnexionForm;
import beans.Utilisateur;
import constantes.Attributs;
import constantes.Vues;

@WebServlet( "/Connexion" )
public class Connexion extends HttpServlet {

    private String vueGet          = Vues.CONSTANTE_VUE_CONNEXION;
    private String vuePost         = Vues.CONSTANTE_VUE_HOME;
    private String attributSession = Attributs.CONSTANTE_ATTRIBUT_SESSION;

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( vueGet ).forward( request,
                response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        ConnexionForm connexionform = new ConnexionForm();
        Utilisateur utilisateur = connexionform.connecterUtilisateur( request );
        HttpSession session = request.getSession();
        session.setAttribute( attributSession, utilisateur );
        this.getServletContext().getRequestDispatcher( vuePost ).forward( request,
                response );
    }

}
