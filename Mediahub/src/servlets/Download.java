package servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constantes.Tampon;

@WebServlet( "/Download" )
public class Download extends HttpServlet {

    private static final int DEFAULT_BUFFER_SIZE = 10240; // 10 ko

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        // chemin c'est la ou on stocke les fichier
        String Chemin = this.getServletConfig().getInitParameter( "chemin" );
        System.out.println( Chemin ); // chemin
        /*
         * Récupération du chemin du fichier demandé au sein de l'URL de la
         * requête
         */
        String fichierRequis = request.getPathInfo();
        System.out.println( "fichier url " + fichierRequis );

        /* Vérifie qu'un fichier a bien été fourni */
        if ( fichierRequis == null || "/".equals( fichierRequis ) ) {
            /*
             * Si non, alors on envoie une erreur 404, qui signifie que la
             * ressource demandée n'existe pas
             */
            response.sendError( HttpServletResponse.SC_NOT_FOUND );
            return;
        }

        // on converti avec URLDecoder les caractères spéciaux du chemin du
        // fichier
        fichierRequis = URLDecoder.decode( fichierRequis, "UTF-8" );

        System.out.println( "fichier url sans carac spéciaux " + fichierRequis );

        // File (parent, enfant) => permet de préciser un dossier parent (
        // dossier fichiers) suivi de l'enfant
        // (fichier caca.jpg de l'url par exemple)
        // ce qui donne C:\fichiers\caca.jpg

        File file = new File( Chemin, fichierRequis );
        System.out.println( "chemin fichier à télécharger" + file.getAbsolutePath() );

        if ( !file.exists() ) {

            response.sendError( HttpServletResponse.SC_NOT_FOUND );
            return;

        }
        // recupere le type de fichier en prenant le nom en argument
        String type = request.getServletContext().getMimeType( file.getName() );
        System.out.println( "type fichier " + type );
        // si le type est null, on en met un par défaut
        if ( type == null ) {
            type = "application/octet-stream";
        }

        /* Initialise la réponse HTTP */
        response.reset();
        response.setBufferSize( DEFAULT_BUFFER_SIZE );
        response.setContentType( type );
        response.setHeader( "Content-Length", String.valueOf( file.length() ) );
        response.setHeader( "Content-Disposition", "attachment; filename=\"" + file.getName() + "\"" );

        /*
         * Prépare les flux Cette classe se charge de lire sur le flux de
         * données un grand nombre d'octets qu'elle garde dans un tampon. Tous
         * les appels à la méthode read () ultérieurs sur une instance de cette
         * classe renvoient des données provenant de ce tampon tant qu'il y
         * reste des octets à lire ; le tampon est rechargé à partir du fichier
         * quand on a épuisé toutes les informations qui s'y trouvent.
         * L'utilisation de cette classe accélère la lecture des fichiers.
         * 
         * BufferedInputStream (InputStream in) Le tampon a la taille 8192 par
         * défaut. BufferedInputStream(InputStream in, int taille) Le tampon a
         * la taille taille
         */

        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            /* Ouvre les flux */
            // permet de lire le fichier file dans le tampon de la taille 10240
            // dans l'emplacement file
            entree = new BufferedInputStream( new FileInputStream( file ), Tampon.CONSTANTE_TAILLE_TAMPON_10240 );
            // permet d'exporter le fichier lu dans la reponse http
            sortie = new BufferedOutputStream( response.getOutputStream(), Tampon.CONSTANTE_TAILLE_TAMPON_10240 );

            /* ... */

            byte[] tampon = new byte[Tampon.CONSTANTE_TAILLE_TAMPON_10240];
            int longueur;
            // on lit les bytes du bufferedInputStream et on vide les bytes lu
            // dans tampon(en paramétre)
            // longueur contient le nombre exact de byte lu et à écrite

            // read retourne le nombre de byte lu dans le buffer et vaut -1
            // quand la lecture dans le
            // buffer est terminé
            // quand tout est lu que tout est chargé dans le buffer

            /*
             * ca marche aussi mais moins optimal
             * 
             * entree.read( tampon );
             * 
             * sortie.write( tampon );
             * 
             */

            // dans longeur on stocke la valeur de tous les byte à lire dans le
            // tampon donc tout ce qu'on va stocker le nombre de byte à écrire
            // dans le tampon

            // attention c'est pas une boucle, on passe une fois jusque ce que
            // la lecture soit terminé
            System.out.println( "avant" );
            List<Byte> bitesavant = new ArrayList<Byte>();
            for ( byte bite : tampon ) {
                bitesavant.add( bite );
            }
            System.out.println( bitesavant );
            // que des 0 car les bytes sont pas remplis

            while ( ( longueur = entree.read( tampon ) ) > 0 ) {

                /*
                 * au fur à mesue de la lecture et remplissage du tampon, on
                 * écrit les données du tampon dans l'outpustream
                 * 
                 * parametre write tampon − tableau de byte source de la lecture
                 * 0 offset de démarrage de lecture dans la source longueur :
                 * nomnre de bytes à écrire dans le ouptustream
                 * 
                 */

                sortie.write( tampon, 0, longueur );
            }

            System.out.println( "apres" );
            List<Byte> bitesapres = new ArrayList<Byte>();
            for ( byte bite : tampon ) {
                bitesapres.add( bite );
            }
            System.out.println( bitesapres );

            // 57 12 3 ...
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
