package beans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constantes.Tampon;

public class TelechargerFichiers {

    public Fichier telechargerFichier( String repertoire, HttpServletRequest request, HttpServletResponse response ) {
        Fichier fichier = new Fichier();
        // Récupération du chemin du fichier demandé au sein de l'URL de la
        // requete
        String nomFichier = request.getPathInfo();

        // si le fichier n'est pas renseigné dans l'url, on renvoie une page 404
        // dans la réponse
        // quand on appelle une autre méthode
        // son return n'arrete pas la méthode qu'il l'appelle
        // d'où le return ici
        boolean presenceUrlFichier = verifierPresenceUrlFichier( response, nomFichier );
        if ( presenceUrlFichier == false ) {
            return null;
        }

        try {
            // URLDecoder converti les caractères spéciaux
            nomFichier = URLDecoder.decode( nomFichier, "UTF-8" );
        } catch ( UnsupportedEncodingException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // on définit le chemin complet du fichier à télécharger
        // si repertoire : C:\fichiers
        // nomFichier : caca.jpg
        // alors chemin complet = C:\fichiers\caca.jpg
        File cheminComplet = new File( repertoire, nomFichier );
        System.out.println( cheminComplet.getAbsolutePath() );

        boolean existenceFichier = verifierExistenceFichier( response, cheminComplet );
        if ( existenceFichier == false ) {

            return null;

        }
        // recupere le type de fichier de l'url

        String typeFichier = recupererTypeFichier( request, cheminComplet );

        /* Initialise la réponse HTTP */

        initialiserReponseHttp( response, cheminComplet, typeFichier );
        try {
            renvoyerLeFichierDansLaReponse( response, cheminComplet );
        } catch ( NullPointerException e ) {
            try {
                response.sendError( HttpServletResponse.SC_FOUND );
            } catch ( IOException e1 ) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        return fichier;
    }

    public boolean verifierPresenceUrlFichier( HttpServletResponse response, String nomFichier ) {

        boolean presenceUrlFichier = true;
        if ( nomFichier == null || "/".equals( nomFichier ) ) {
            try {
                response.sendError( HttpServletResponse.SC_NOT_FOUND );
                presenceUrlFichier = false;

            } catch ( IOException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }
        }
        return presenceUrlFichier;

    }

    public boolean verifierExistenceFichier( HttpServletResponse response, File cheminComplet ) {

        boolean existenceFichier = true;
        if ( !cheminComplet.exists() ) {
            try {
                response.sendError( HttpServletResponse.SC_NOT_FOUND );
                existenceFichier = false;

            } catch ( IOException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }
        }
        return existenceFichier;

    }

    // recuperer le type du fichier en prenant son nom en argument
    public String recupererTypeFichier( HttpServletRequest request, File cheminComplet )

    {
        String typeFichier = request.getServletContext().getMimeType( cheminComplet.getName() );
        if ( typeFichier == null ) {
            typeFichier = "application/octet-stream";
        }
        return typeFichier;
    }

    public void initialiserReponseHttp( HttpServletResponse response, File cheminComplet, String typeFichier ) {

        response.reset();
        response.setBufferSize( Tampon.CONSTANTE_TAILLE_TAMPON_10240 );
        response.setContentType( typeFichier );
        response.setHeader( "Content-Length", String.valueOf( cheminComplet.length() ) );
        response.setHeader( "Content-Disposition", "attachment; filename=\"" + cheminComplet.getName() + "\"" );
    }

    public void erreur404( HttpServletResponse response ) {
        try {
            response.sendError( HttpServletResponse.SC_NOT_FOUND );
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void renvoyerLeFichierDansLaReponse( HttpServletResponse response, File cheminComplet ) {
        // stocke le fichier à lire
        BufferedInputStream bufferedInputStream = null;
        // permet de renvoyer le fichier lu dans la réponse
        BufferedOutputStream bufferedOutputStream = null;

        // on ouvre le flux vers le fichier à l'emplacement cheminComplet dans
        // un tampon de 10240
        try {
            bufferedInputStream = new BufferedInputStream( new FileInputStream( cheminComplet ),
                    Tampon.CONSTANTE_TAILLE_TAMPON_10240 );
        } catch ( FileNotFoundException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // on ouvrer un flux pour écrire dans la réponse Http dans un tampon de
        // 10240
        try {
            bufferedOutputStream = new BufferedOutputStream( response.getOutputStream(), Tampon.CONSTANTE_TAILLE_TAMPON_10240 );
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // on créé un tableau de bytes dans lequel on va vers transiter les
        // bytes du bufferedInputStream vers le bufferedOutputStream
        // on va lire/stocker les bytes avec read du bufferedInputStream dans le
        // tableau tampon de byte tableauBytesFichierATelecharger
        // puis on les écrit les bytes stockés dans le tableau tampon de byte
        // tableauBytesFichierATelecharger dans le bufferedOutputStream
        // qui permettra de renvoyer le fichier dans la réponse

        byte[] tableauBytesFichierATelecharger = new byte[Tampon.CONSTANTE_TAILLE_TAMPON_10240];
        int longueurBitesAEcrire;

        // dans longueurBitesAlire, on va stocker directement le nombre de bytes
        // dans le bufferedInputStream
        // avant qu'on les vide
        // mettre la variable dans le while permet de récupérer une valeur avant
        // qu'elle ne change

        try {
            while ( ( longueurBitesAEcrire = bufferedInputStream.read( tableauBytesFichierATelecharger ) ) > 0 ) {
                // tableauBytesFichierATelecharger source à récupérer
                // 0 offset de démarrage
                // longueurBitesAEcrire nombre de bytes à écrire (récupéré dans
                // le tableau de byte tampon)
                bufferedOutputStream.write( tableauBytesFichierATelecharger, 0, longueurBitesAEcrire );
            }
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            try {
                bufferedInputStream.close();
            } catch ( IOException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                bufferedOutputStream.close();
            } catch ( IOException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
