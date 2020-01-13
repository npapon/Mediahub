import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(
            String[] args ) {

        FileInputStream lectureFichier = null;
        FileOutputStream ecritureFichier = null;

        File fichiertextlu = new File( "nico.txt" );
        File fichiertextecri = new File( "nico3.txt" );
        try {

            lectureFichier = new FileInputStream( fichiertextlu );
            ecritureFichier = new FileOutputStream( fichiertextecri );

            byte[] buf = new byte[8];

            int n = 0;
            int tour = 0;

            while ( ( n = lectureFichier.read( buf ) ) >= 0 ) {
                tour = tour + 1;
                System.out.println( "tour numero " + tour );
                ecritureFichier.write( buf );
                for ( byte bit : buf ) {
                    System.out.println( bit + "(" + (char) bit + ")" );
                }

            }
            // au cas ou il n'y ait pas de fichier dans la lecture du fichier
            // (l'écriture il le créé)
        } catch ( FileNotFoundException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            // cela arrive quand il y a une erreur d'écriture ou lecture
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            // On ferme nos flux de données dans un bloc finally pour s'assurer
            // que ca soit fait même en cas d'exception

        } finally {
            if ( lectureFichier != null ) {
                try {
                    lectureFichier.close();
                } catch ( IOException e ) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        if ( ecritureFichier != null ) {
            try {
                ecritureFichier.close();
            } catch ( IOException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}