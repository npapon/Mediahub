package beans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import constantes.MessagesErreur;
import constantes.Parametres;

public class EnvoiFichiersForm {

    List<String>            erreurs       = new ArrayList<String>();
    public static final int TAILLE_TAMPON = 10240;                  // 10 ko

    public Fichier envoyerFichier( HttpServletRequest request ) throws IOException, ServletException {

        Fichier fichier = new Fichier();
        String description = request.getParameter( Parametres.CONSTANTE_PARAMETRE_DESCRIPTION );
        verifierDescription( description );
        fichier.setDescription( description );
        // récupère le champ imput type="file"
        Part part = request.getPart( Parametres.CONSTANTE_PARAMETRE_FICHIER );
        fichier.setFichierPhysique( part );

        String nom = getNomFichier( part );
        verifierFichier( nom );
        fichier.setNom( nom );

        return fichier;

    }

    private static String getNomFichier( Part part ) {

        /*
         * part.getHeader parse l'entete de la requete html qui démarre par
         * l'attribut de la méthode : Content-Disposition: form-data;
         * name="fichier"; filename="C:\Users\npapo\Desktop\ffbig.jpg"
         */
        String nomFichier;
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {

            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                // "C:\Users\npapo\Desktop\ffbig.jpg"
                nomFichier = contentDisposition.substring( contentDisposition.indexOf( "=" ) + 1 );
                // ffbig.jpg"
                nomFichier = nomFichier.substring( nomFichier.lastIndexOf( "\\" ) + 1 );
                // ffbig.jpg
                nomFichier = nomFichier.replace( "\"", "" );

                return nomFichier;
            }
        }

        return null;

    }

    public void verifierDescription( String description ) {
        if ( description.length() < 3 ) {
            try {
                throw ( new Exception( MessagesErreur.CONSTANTE_ERREUR_DESCRIPTIONFICHIER ) );
            } catch ( Exception e ) {
                System.out.println( e.getMessage() );
                erreurs.add( e.getMessage() );
            }
        }
    }

    public void verifierFichier( String nom ) {
        if ( nom == null || nom.isEmpty() ) {
            try {
                throw ( new Exception( MessagesErreur.CONSTANTE_ERREUR_NOMFICHIER ) );

            } catch ( Exception e ) {
                System.out.println( e.getMessage() );
                erreurs.add( e.getMessage() );
            }
        }
    }

    /*
     * Méthode utilitaire qui a pour but d'écrire le fichier passé en paramètre
     * sur le disque, dans le répertoire donné et avec le nom donné.
     */
    public void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
        /* Prépare les flux. */
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            /* Ouvre les flux. */
            entree = new BufferedInputStream( part.getInputStream(), TAILLE_TAMPON );
            sortie = new BufferedOutputStream( new FileOutputStream( new File( chemin + nomFichier ) ),
                    TAILLE_TAMPON );

            /*
             * Lit le fichier reçu et écrit son contenu dans un fichier sur le
             * disque.
             */
            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ( ( longueur = entree.read( tampon ) ) > 0 ) {
                sortie.write( tampon, 0, longueur );
            }
        } finally {
            try {
                sortie.close();
            } catch ( IOException ignore ) {
            }
            try {
                entree.close();
            } catch ( IOException ignore ) {
            }
        }
    }
}
