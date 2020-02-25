package beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import constantes.Parametres;

public class ChargerImageProfil {
    List<String> erreurs = new ArrayList<String>();

    public Fichier chargerImageProfil( HttpServletRequest request ) {

        Fichier imageProfil = new Fichier();
        String nomImage = request.getParameter( Parametres.CONSTANTE_PARAMETRE_NOMIMAGE );
        imageProfil.setNom( nomImage );

        // getParts permet de r�cup�rer les donn�es envoyer dans un formulaire
        // de type type enctype="multipart/form-data" (fichiers, champs
        // classiques ...
        // elle retourne une collection d'�l�ments de type Part
        // Collection<Part> parts = request.getParts();
        // getPart permet de r�cup�rer un �l�ment du formulaire en particulier
        // pass� en param�tre

        try {
            // r�cup�re le fichier
            Part part = request.getPart( Parametres.CONSTANTE_PARAMETRE_FICHIERIMAGE );
        } catch ( IOException | ServletException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return imageProfil;
    }

    // Comment savoir si on a un fichier dans les �l�ments du formulaire
    // une seule solution : v�rifier que la requ�te envoy� dans POST contient
    // l'attribut filename

    // Ex de requ�te post avec un fichier :
    /*
     * Content-Disposition: form-data;name="fichier";
     * filename="nom_du_fichier.ext"
     */
    // il faut v�rifier sa pr�sence dans l'entete http de la requ�te post
    // part. getHeader r�cup�re l'entete http et prend en param�tre le nom de
    // l'attribut de la requ�te
    // � r�cup�rer

    public Boolean champContientUnFichier( Part part ) {

        Boolean estUnFichier = null;
        String enteteRequetePostContentDisposition = part.getHeader( "Content-Disposition" );

        for ( String coupleAttributValeur : enteteRequetePostContentDisposition.split( ";" ) ) {
            if ( coupleAttributValeur.trim().startsWith( "filename" ) ) {
                estUnFichier = true;
            }
        }
        return estUnFichier;
    }

    public String recupererNomFichierImage( Part part ) {
        String enteteRequetePostContentDisposition = part.getHeader( "Content-Disposition" );
        String nomFichierImage;
        for ( String coupleAttributValeur : enteteRequetePostContentDisposition.split( ";" ) ) {
            if ( coupleAttributValeur.trim().startsWith( "filename" ) ) {
                nomFichierImage = coupleAttributValeur.substring( coupleAttributValeur.indexOf( "\"" ),
                        coupleAttributValeur.lastIndexOf( "\"" ) );
                System.out.println( "Nom du fichier de l'image " + nomFichierImage );
                return nomFichierImage;
            }
        }
        return null;

    }
}