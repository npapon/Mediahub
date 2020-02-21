package beans;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import constantes.MessagesErreur;
import constantes.Parametres;

public class EcrireFichierChannelNio2Bean {
    List<String> erreurs = new ArrayList<String>();

    public Fichier creerFichierChannelNio2( HttpServletRequest request, String repertoire ) {

        String textTape = request.getParameter( Parametres.CONSTANTE_PARAMETRE_TEXTTAPE );
        String nomFichier = request.getParameter( Parametres.CONSTANTE_PARAMETRE_NOMFICHIER ).trim();
        Fichier fichier = new Fichier();
        verifierNomFichier( nomFichier );
        fichier.setNom( nomFichier );
        fichier.setContenu( textTape );
        String cheminFichier = repertoire + "/" + nomFichier + ".txt";
        fichier.setChemin( cheminFichier );
        SeekableByteChannel seekablebytechannel = null;

        Path path = Paths.get( cheminFichier );

        try {
            if ( !Files.exists( path ) ) {
                Files.createFile( path );
            }
            seekablebytechannel = Files.newByteChannel( path, StandardOpenOption.WRITE );

            seekablebytechannel.write( ByteBuffer.wrap( textTape.getBytes() ) );

            seekablebytechannel.close();

        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            try {
                seekablebytechannel.close();
            } catch ( IOException e1 ) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
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
