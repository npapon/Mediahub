import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Main {

    public static void main(
            String[] args ) {

        File file = new File( "nigros.txt" );
        FileChannel fileChannel = null;
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream( file );
            // on r�cup�re le canal du flux
            fileChannel = fileInputStream.getChannel();
            // on peut r�cup�rer la taille
            int taille = (int) fileChannel.size();
            // on cr�� le buffer avec la bonne taille
            ByteBuffer byteBuffer = ByteBuffer.allocate( taille );
            // fileChannel.read remplit le tampon
            fileChannel.read( byteBuffer );
            // La m�moire tampon a une position, une limite et une capacit�
            // Alors qu'un tampon est �crit avec des donn�es, sa position
            // augmente progressivement (part de 0)
            // Pour lire des donn�es depuis le d�but d'un tampon, il faut mettre
            // la position � z�ro.
            // La m�thode flip () modifie la position
            byteBuffer.flip();
            int i;
            String fichierlu = "";
            // tu initialises en premier ton ByteBuffer pour la lecture, donc il
            // se rempli.

            for ( i = 0; i < fileChannel.size(); i++ ) {

                fichierlu += (char) byteBuffer.get();

            }

            System.out.println( fichierlu );
            // tu as fini de lire tu veux �crire dans un fichier
            // ou dans ici dans on tableau ce que tu as charg� dans ton
            // ByteBuffer.
            // Pour ce faire tu 'retourne' ( "flip()" ) ton ByteBuffer pour le
            // vider

            // nous avons utilis� un buffer de byte afin de r�cup�rer les
            // donn�es
            // on peut les stocker dans un tableau de byte
            // la m�thode array() transforme le byteBuffer en tableau de bytes

            byteBuffer.clear();
            fileChannel.close();

        } catch ( FileNotFoundException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        finally {
            try {
                fileInputStream.close();
                fileChannel.close();
            } catch ( IOException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
