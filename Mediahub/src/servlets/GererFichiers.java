package servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.AdministrationImages;
import beans.Image;
import constantes.Attributs;
import constantes.Images;
import constantes.Parametres;
import constantes.Repertoires;
import constantes.Vues;

@WebServlet( "/GererFichiers" )
public class GererFichiers extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        List<Image> images = new ArrayList<Image>();

        AdministrationImages administrationimages = new AdministrationImages();
        List<Image> imagesProfil = administrationimages.retournerImages( Images.CONSTANTE_TYPE_PROFIL,
                Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_IMAGESPROFIL, Repertoires.CONSTANTE_REPERTOIRE_RELATIF_IMAGESPROFIL );
        HttpSession session = request.getSession();
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_IMAGESPROFIL, imagesProfil );
        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_GERERFICHIERS ).forward( request,
                response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        String imagesasupprimer = request.getParameter( Parametres.CONSTANTE_PARAMETRE_IMAGES_A_SUPPRIMER );
        String[] imagesasupprimerTableau = imagesasupprimer.split( "," );

        for ( String imagelibelle : imagesasupprimerTableau ) {

            File file = new File( Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_IMAGESPROFIL + "/" + imagelibelle );
            System.out.println( file.getAbsolutePath() );
            System.out.println( file.exists() );
            file.delete();

        }

        response.sendRedirect( Vues.CONSTANTE_VUE_GERERFICHIERS_NOMCOURT );
    }

}
