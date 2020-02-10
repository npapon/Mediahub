package servlets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.AdministrationFichiersText;
import beans.AdministrationImages;
import beans.Fichier;
import beans.Image;
import constantes.Attributs;
import constantes.Fichiers;
import constantes.Images;
import constantes.Parametres;
import constantes.Repertoires;
import constantes.Vues;

@WebServlet( "/GererFichiers" )
public class GererFichiers extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        AdministrationImages administrationimages = new AdministrationImages();
        administrationimages
                .setDossierRangement( Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_IMAGESPROFIL );
        String dossierRangementImagesProfil = administrationimages.getDossierRangement();
        List<Image> imagesProfil = administrationimages.retournerImages( Images.CONSTANTE_TYPE_PROFIL,
                Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_IMAGESPROFIL, Repertoires.CONSTANTE_REPERTOIRE_RELATIF_IMAGESPROFIL );
        ;

        AdministrationFichiersText administrationfichiers = new AdministrationFichiersText();
        administrationfichiers.setDossierRangement( Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_FICHIERSTEXT );
        String dossierRangementFichiersText = administrationfichiers.getDossierRangement();
        List<Fichier> fichiersText = administrationfichiers.retournerFichiersText( Fichiers.CONSTANTE_TYPE_FICHIERSTEXT,
                Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_FICHIERSTEXT );

        HttpSession session = request.getSession();
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_IMAGESPROFIL, imagesProfil );
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_FICHIERSTEXT, fichiersText );
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_GERERFICHIERSVUE, Vues.CONSTANTE_VUE_GERERFICHIERS_NOMCOURT );
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_DOSSIERRANGEMENTIMAGESPROFIL, dossierRangementImagesProfil );
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_DOSSIERRANGEMENTFICHIERSTEXT, dossierRangementFichiersText );

        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_GERERFICHIERS ).forward( request,
                response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        String elementsasupprimer = request.getParameter( Parametres.CONSTANTE_PARAMETRE_ELEMENTS_A_SUPPRIMER );
        String[] elementsasupprimerTableau = elementsasupprimer.split( "," );

        for ( String elementlibelle : elementsasupprimerTableau ) {

            File file = new File( Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_IMAGESPROFIL + "/" + elementlibelle );
            if ( file.exists() ) {
                file.delete();
            }

            Path path = Paths.get( Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_FICHIERSTEXT + "/" + elementlibelle );

            try {
                if ( Files.exists( path ) ) {
                    Files.delete( path );
                }
            } catch ( NoSuchFileException e ) {
                System.out.println( "Le fichier suivant n'existe pas : " + path.getFileName() );
            }

        }

        response.sendRedirect( Vues.CONSTANTE_VUE_GERERFICHIERS_NOMCOURT );
    }

}
