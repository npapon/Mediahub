package beans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import constantes.MessagesErreur;
import constantes.Parametres;
import constantes.Tampon;

public class ChargerImageProfil {
    List<String> erreurs = new ArrayList<String>();

    public Fichier chargerImageProfil( HttpServletRequest request ) {

        Fichier imageProfil = new Fichier();

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
            champContientUnFichier( part );
            imageProfil.setFichierPhysique( part );

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

    public void champContientUnFichier( Part part ) {

        Boolean estUnFichier = null;
        String enteteRequetePostContentDisposition = part.getHeader( "Content-Disposition" );

        for ( String coupleAttributValeur : enteteRequetePostContentDisposition.split( ";" ) ) {
            if ( coupleAttributValeur.trim().startsWith( "filename" ) ) {
                estUnFichier = true;
            }
        }
        if ( estUnFichier == false ) {
            try {
                throw ( new Exception( MessagesErreur.CONSTANTE_ERREUR_EXISTENCE_FICHIER_IMAGE ) );
            } catch ( Exception e ) {
                e.printStackTrace();
                erreurs.add( e.getMessage() );
            }
        }

    }

    public String recupererNomFichierImage( Part part ) {
        String enteteRequetePostContentDisposition = part.getHeader( "Content-Disposition" );

        String nomFichierImage;
        for ( String coupleAttributValeur : enteteRequetePostContentDisposition.split( ";" ) ) {
            if ( coupleAttributValeur.trim().startsWith( "filename" ) ) {

                nomFichierImage = coupleAttributValeur.substring( coupleAttributValeur.lastIndexOf( "\\" ) + 1,
                        coupleAttributValeur.lastIndexOf( "\"" ) );

                return nomFichierImage;
            }
        }
        return null;

    }

    public void verifierNomImageUtilisateur( String nomImageUtilisateur ) {
        if ( nomImageUtilisateur.trim().length() <= 3 )

        {
            try {
                throw ( new Exception( MessagesErreur.CONSTANTE_ERREUR_NOM_IMAGE_UTILISATEUR ) );
            } catch ( Exception e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                erreurs.add( e.getMessage() );
            }
        }

    }

    public void enregistrerImageProfil( Part part, String chemin ) {
        /* Pr�pare les flux. */

        String nomFichier = this.recupererNomFichierImage( part );
        System.out.println( "Fichier enregistr� ici : " + chemin + "/" + nomFichier );
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            /* Ouvre les flux. */
            entree = new BufferedInputStream( part.getInputStream(), Tampon.CONSTANTE_TAILLE_TAMPON_10240 );

            sortie = new BufferedOutputStream( new FileOutputStream( new File( chemin + "/" + nomFichier ) ),
                    Tampon.CONSTANTE_TAILLE_TAMPON_10240 );

            /*
             * Lit le fichier re�u et �crit son contenu dans un fichier sur le
             * disque.
             */
            byte[] tampon = new byte[Tampon.CONSTANTE_TAILLE_TAMPON_10240];
            int longueur;
            while ( ( longueur = entree.read( tampon ) ) > 0 ) {
                sortie.write( tampon, 0, longueur );
            }

        }

        catch ( FileNotFoundException e ) {
            e.printStackTrace();
        }

        catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        finally {
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