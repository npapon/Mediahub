package beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class LecteurFichierFileChannelBean {

    public Fichier lireFichierFileChannelBean( String nom, String repertoireAbsolu ) {

        Fichier fichier = new Fichier();
        fichier.setNom( nom );
        String chemin = repertoireAbsolu + "/" + nom;
        File file = new File( repertoireAbsolu + "/" + nom );
        fichier.setChemin( chemin );
        FileChannel fileChannel = null;
        FileInputStream fileInputStream = null;
        ByteBuffer byteBuffer;
        String fichierlu = "";

        try {
            fileInputStream = new FileInputStream( file );
            fileChannel = fileInputStream.getChannel();
            int taille = (int) fileChannel.size();
            byteBuffer = ByteBuffer.allocate( taille );

            fileChannel.read( byteBuffer );
            byteBuffer.flip();
            for ( int i = 0; i < taille; i++ ) {
                fichierlu += (char) byteBuffer.get();
            }

            fileChannel.close();
            fileInputStream.close();
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
                fileInputStream.close();
            } catch ( IOException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        fichier.setContenu( fichierlu );

        return fichier;
    }

}
