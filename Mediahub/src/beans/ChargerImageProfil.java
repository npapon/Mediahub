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

        // getParts permet de récupérer les données envoyer dans un formulaire
        // de type type enctype="multipart/form-data" (fichiers, champs
        // classiques ...
        // elle retourne une collection d'éléments de type Part
        // Collection<Part> parts = request.getParts();
        // getPart permet de récupérer un élément du formulaire en particulier
        // passé en paramètre

        try {
            // récupère le fichier
            Part part = request.getPart( Parametres.CONSTANTE_PARAMETRE_FICHIERIMAGE );
        } catch ( IOException | ServletException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return imageProfil;
    }

    // Comment savoir si on a un fichier dans les éléments du formulaire
    // une seule solution : vérifier que la requête envoyé dans POST contient
    // l'attribut filename

    // Ex de requête post avec un fichier :
    /*
     * Content-Disposition: form-data;name="fichier";
     * filename="nom_du_fichier.ext"
     */
    // il faut vérifier sa présence dans l'entete http de la requête post
    // part. getHeader récupère l'entete http et prend en paramètre le nom de
    // l'attribut de la requête
    // à récupérer

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