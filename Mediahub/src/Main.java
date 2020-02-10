
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Main {
    public static void main( String[] args ) throws IOException {
        // le buffer est prêt pour la lecture

        Path path = Paths.get( "C:\\Users\\npapo\\git\\Mediahub\\Mediahub\\WebContent\\fichiers\\leclown.txt" );
        // SeekableByteChannel ouvre un fichier à lire (c'est le channel qui lit
        // le path)

        SeekableByteChannel seekableByteChannel = Files.newByteChannel( path );
        int taille = (int) seekableByteChannel.size();
        ByteBuffer bytebuffer = ByteBuffer.allocate( taille );
        System.out.println( "Position / capacité : " + bytebuffer.position() + "/" + bytebuffer.limit() );

        String fichierlu = "";
        /*
         * FileChannel‘s read() lit les bites du bytebuffer. la méthode retourne
         * le nombre de bite lu ou -1 quand c'est fini
         */

        seekableByteChannel.read( bytebuffer );
        System.out.println( "Position / capacité : " + bytebuffer.position() + "/" + bytebuffer.limit() );

        bytebuffer.flip();
        System.out.println( "Position / capacité : " + bytebuffer.position() + "/" + bytebuffer.limit() );
        for ( int j = 0; j < taille; j++ ) {
            fichierlu += (char) bytebuffer.get();
        }

        System.out.println( fichierlu );
        bytebuffer.clear();

        System.out.println( "Position / capacité : " + bytebuffer.position() + "/" + bytebuffer.limit() );
        seekableByteChannel = Files.newByteChannel( path, StandardOpenOption.WRITE );
        String ecrasertextfichier = "Salut coquin ";
        seekableByteChannel.write( ByteBuffer.wrap( ecrasertextfichier.getBytes() ) );
        System.out.println( "Position / capacité : " + bytebuffer.position() + "/" + bytebuffer.limit() );
        seekableByteChannel.close();

    }
}
