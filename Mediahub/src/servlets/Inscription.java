package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.InscriptionForm;
import beans.Utilisateur;
import constantes.Cookies;
import constantes.Vues;

@WebServlet( "/Inscription" )
public class Inscription extends HttpServlet {

    private String vueGet  = Vues.CONSTANTE_VUE_INSCRIPTION;
    private String vuePost = Vues.CONSTANTE_VUE_HOME;

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( vueGet ).forward( request,
                response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        InscriptionForm inscriptionform = new InscriptionForm();
        Utilisateur utilisateur = inscriptionform.inscrireUtilisateur( request );
        Cookie cookieLogin = new Cookie( Cookies.CONSTANTE_COOKIE_LOGIN, utilisateur.getLogin() );
        Cookie cookieMotdepasse = new Cookie( Cookies.CONSTANTE_COOKIE_MOTDEPASSE, utilisateur.getMotDePasse() );
        Cookie cookieEmail = new Cookie( Cookies.CONSTANTE_COOKIE_EMAIL, utilisateur.getEmail() );
        response.addCookie( cookieLogin );
        response.addCookie( cookieMotdepasse );
        response.addCookie( cookieEmail );
        this.getServletContext().getRequestDispatcher( vuePost ).forward( request,
                response );
    }

}