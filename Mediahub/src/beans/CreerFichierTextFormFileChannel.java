package beans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import constantes.MessagesErreur;
import constantes.Parametres;

public class CreerFichierTextFormFileChannel {

    List<String> erreurs = new ArrayList<String>();

    public Fichier creerFichierTextFileChannel( HttpServletRequest request, String repertoire ) {

        String textTape = request.getParameter( Parametres.CONSTANTE_PARAMETRE_TEXTTAPE );
        String nomFichier = request.getParameter( Parametres.CONSTANTE_PARAMETRE_NOMFICHIER ).trim();
        Fichier fichier = new Fichier();
        verifierNomFichier( nomFichier );
        fichier.setNom( nomFichier );
        fichier.setContenu( textTape );
        String cheminFichier = repertoire + "/" + nomFichier + ".txt";
        fichier.setChemin( cheminFichier );

        File file = new File( cheminFichier );
        FileOutputStream fileOutputStream = null;
        FileChannel fileChannel = null;
        ByteBuffer byteBuffer;
        byte[] bites = textTape.getBytes();
        try {
            fileOutputStream = new FileOutputStream( file );
            fileChannel = fileOutputStream.getChannel();

            byteBuffer = ByteBuffer.allocateDirect( bites.length );

            byteBuffer = ByteBuffer.wrap( bites );
            fileChannel.write( byteBuffer );

            fileChannel.close();
            fileOutputStream.close();
        } catch ( FileNotFoundException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        finally {

            try {
                fileChannel.close();
                fileOutputStream.close();
            } catch ( IOException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return fichier;
    }

    public void verifierNomFichier( String nom ) {
        if ( nom.length() < 3 ) {
            try {
                throw ( new Exception( MessagesErreur.CONSTANTE_ERREUR_LONGUEURNOMFICHIER ) );
            } catch ( Exception e ) {
                // TODO Auto-generated catch block
                System.out.println( e.getMessage() );
                erreurs.add( e.getMessage() );

            }
        }
    }

}