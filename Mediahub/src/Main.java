import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
        BufferedInputStream lecteurFichierBuffered = null;
        BufferedOutputStream ecritureFichierBuffered = null;

        File fichiertextlu = new File( "dictionnaire.txt" );
        File fichiertextecri = new File( "copielente.txt" );
        try {

            lectureFichier = new FileInputStream( fichiertextlu );
            ecritureFichier = new FileOutputStream( fichiertextecri );

            byte[] buf = new byte[8];

            int n = 0;
            long starttime = System.currentTimeMillis();

            while ( ( n = lectureFichier.read( buf ) ) >= 0 ) {

                ecritureFichier.write( buf );

            }

            long endtime = System.currentTimeMillis();
            System.out.println( "le script a pris avec FileInputStream/FileOutputStream " + ( endtime - starttime ) );

            lecteurFichierBuffered = new BufferedInputStream(
                    new FileInputStream( new File( "dictionnaire.txt" ) ) );
            ecritureFichierBuffered = new BufferedOutputStream(
                    new FileOutputStream( new File( "copierapide.txt" ) ) );

            starttime = System.currentTimeMillis();
            while ( lecteurFichierBuffered.read() != -1 ) {
                ecritureFichierBuffered.write( buf );

            }

            endtime = System.currentTimeMillis();
            System.out.println( "le script a pris avec BufferedInputStream/BufferedOutputStream " + ( endtime - starttime ) );

        } catch ( FileNotFoundException e ) {

            e.printStackTrace();

        } catch ( IOException e ) {

            e.printStackTrace();

        } finally {
            if ( lectureFichier != null ) {
                try {
                    lectureFichier.close();
                } catch ( IOException e ) {

                    e.printStackTrace();
                }
            }

            if ( lecteurFichierBuffered != null ) {
                try {
                    lectureFichier.close();
                } catch ( IOException e ) {
                }
            }

        }
        if ( ecritureFichier != null ) {
            try {
                ecritureFichier.close();
            } catch ( IOException e ) {

                e.printStackTrace();
            }
        }

        if ( ecritureFichierBuffered != null ) {
            try {
                ecritureFichierBuffered.close();
            } catch ( IOException e ) {

                e.printStackTrace();
            }
        }
    }
}