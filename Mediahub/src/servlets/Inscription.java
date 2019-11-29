package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.InscriptionForm;
import beans.Utilisateur;
import constantes.Attributs;
import constantes.Cookies;
import constantes.Vues;

@WebServlet( "/Inscription" )
public class Inscription extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_INSCRIPTION ).forward( request,
                response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        InscriptionForm inscriptionform = new InscriptionForm();
        Utilisateur utilisateur = inscriptionform.inscrireUtilisateur( request );

        Cookie cookieLogin = creerCookie( response, Cookies.CONSTANTE_COOKIE_LOGIN, utilisateur.getLogin(),
                Cookies.CONSTANTE_COOKIE_MAX_AGE );
        Cookie cookieMotdepasse = creerCookie( response, Cookies.CONSTANTE_COOKIE_MOTDEPASSE, utilisateur.getMotDePasse(),
                Cookies.CONSTANTE_COOKIE_MAX_AGE );
        Cookie cookieEmail = creerCookie( response, Cookies.CONSTANTE_COOKIE_EMAIL, utilisateur.getEmail(),
                Cookies.CONSTANTE_COOKIE_MAX_AGE );
        Cookie cookieDateCreationCompte = creerCookie( response, Cookies.CONSTANTE_COOKIE_DATECREATIONCOMPTE,
                utilisateur.getDateCreationCompte(),
                Cookies.CONSTANTE_COOKIE_MAX_AGE );

        HttpSession session = request.getSession();
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_COOKIELOGIN, cookieLogin );
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_COOKIEMOTDEPASSE, cookieMotdepasse );
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_COOKIEEMAIL, cookieEmail );
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_COOKIEDATECREATIONCOMPTE, cookieDateCreationCompte );

        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_PROFIL ).forward( request,
                response );
    }

    public Cookie creerCookie( HttpServletResponse response, String nom, String valeur, int duree ) {
        Cookie cookie = new Cookie( nom, valeur );
        cookie.setMaxAge( duree );
        response.addCookie( cookie );
        return cookie;
    }
}