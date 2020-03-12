package beans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import constantes.MessagesErreur;
import constantes.Parametres;
import constantes.Tampon;
import eu.medsea.mimeutil.MimeUtil;

public class ChargerImageProfil {
    List<String> erreurs = new ArrayList<String>();

    public Fichier chargerImageProfil( HttpServletRequest request ) {

        Fichier imageProfil = new Fichier();

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
            champContientUnFichier( part );

            imageProfil.setFichierPhysique( part );

        } catch ( IOException | ServletException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        catch ( IllegalStateException e ) {
            e.printStackTrace();
            erreurs.add( MessagesErreur.CONSTANTE_ERREUR_TAILLE_FICHIER );
            System.out.println( MessagesErreur.CONSTANTE_ERREUR_TAILLE_FICHIER );

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

    private void champContientUnFichier( Part part ) {

        Boolean estUnFichier = false;
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

    private void verifierNomImageUtilisateur( String nomImageUtilisateur ) {
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

    public void enregistrerImageProfil( Part part, String chemin ) throws Exception {
        /* Prépare les flux. */

        String nomFichier = this.recupererNomFichierImage( part );

        validationFormatImage( part );

        System.out.println( "Fichier enregistré ici : " + chemin + "/" + nomFichier );
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            entree = new BufferedInputStream( part.getInputStream(), Tampon.CONSTANTE_TAILLE_TAMPON_10240 );

            sortie = new BufferedOutputStream( new FileOutputStream( new File( chemin + "/" + nomFichier ) ),
                    Tampon.CONSTANTE_TAILLE_TAMPON_10240 );

            /*
             * Lit le fichier reçu et écrit son contenu dans un fichier sur le
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

    private void validationFormatImage( Part part ) throws Exception {

        InputStream contenuFichier = null;
        // recupération du contenu du fichier

        contenuFichier = part.getInputStream();

        /* Extraction du type MIME du fichier depuis l'InputStream */
        MimeUtil.registerMimeDetector( "eu.medsea.mimeutil.detector.MagicMimeMimeDetector" );
        // <?> correspond à une collection qui accepte tout type
        Collection<?> mimeTypes = MimeUtil.getMimeTypes( contenuFichier );
        System.out.println( mimeTypes );// ex : image/jpeg

        /*
         * Si le fichier est bien une image, alors son en-tête MIME commence par
         * la chaîne "image"
         */
        if ( !mimeTypes.toString().startsWith( "image" ) ) {

            erreurs.add( MessagesErreur.CONSTANTE_ERREUR_TYPE_FICHIER );
            throw new Exception( MessagesErreur.CONSTANTE_ERREUR_TYPE_FICHIER );

        }

    }

}