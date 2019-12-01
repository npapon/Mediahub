package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;

import beans.Date;
import constantes.Attributs;
import constantes.Cookies;
import constantes.Vues;

@WebServlet( "/Profil" )
public class Profil extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        HttpSession session = request.getSession();
        DateTime datedujour = new DateTime();
        String datecreationcompte = getCookieValue( request, Cookies.CONSTANTE_COOKIE_DATECREATIONCOMPTE );
        Date date = new Date();
        DateTime datecreationcompteformatdatetime = date.DateStringFormatDateTime( datecreationcompte );
        String EcartDateFormatVerbeux = date.ecartDateFormatVerbeux( datecreationcompteformatdatetime, datedujour );

        session.setAttribute(
                Attributs.CONSTANTE_ATTRIBUT_ECARTDATEFORMATVERBEUX, EcartDateFormatVerbeux );

        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_PROFIL ).forward( request,
                response );
    }

    private String getCookieValue( HttpServletRequest request, String nomCookie ) {
        Cookie[] cookies = request.getCookies();
        for ( Cookie cookie : cookies ) {
            if ( cookie.getName().equals( nomCookie ) ) {
                return cookie.getValue();

            }
        }
        return null;
    }
}