package servlets;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.GererZip;
import constantes.Attributs;
import constantes.Parametres;
import constantes.Repertoires;
import constantes.Vues;

@WebServlet( "/GestionnaireDeZipServlet" )
public class GestionnaireDeZipServlet extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_GESTIONNAIREDEZIP ).forward( request,
                response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        GererZip gererZip = new GererZip();
        Map<FileSystem, Boolean> map = gererZip.definirFileSystemZip( request,
                Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_FICHIERSZIP,
                Parametres.CONSTANTE_PARAMETRE_CREERZIP );
        System.out.println( map );
        HttpSession session = request.getSession();
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_FICHIERZIPCREE, map );

        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_FICHIERZIPCREE ).forward( request,
                response );

    }

}
