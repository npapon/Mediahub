package beans;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LectureFichierChannelNio2 {

    public Fichier lireFichierChannelNio2Bean( String nom, String repertoireAbsolu ) {

        Fichier fichier = new Fichier();
        fichier.setNom( nom );
        String chemin = repertoireAbsolu + "/" + nom;
        Path path = Paths.get( chemin );
        fichier.setChemin( chemin );
        String fichierlu = "";
        SeekableByteChannel seekableByteChannel = null;

        try {
            seekableByteChannel = Files.newByteChannel( path );
            int taille = (int) seekableByteChannel.size();
            ByteBuffer bytebuffer = ByteBuffer.allocate( taille );
            seekableByteChannel.read( bytebuffer );
            bytebuffer.flip();

            for ( int j = 0; j < taille; j++ ) {
                fichierlu += (char) bytebuffer.get();
            }
            bytebuffer.clear();
            seekableByteChannel.close();

        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            try {
                seekableByteChannel.close();
            } catch ( IOException e1 ) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        fichier.setContenu( fichierlu );

        return fichier;
    }

}
