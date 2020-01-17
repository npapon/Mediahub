import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(
            String[] args ) {

        // permet de lire les données écrites dans un fichier via un
        // DateOutputStream
        DataInputStream lecteurFichierGenereParUnDataInputStream = null;
        DataOutputStream ecriteurDonneesDansUnFichier = null;

        try {
            File fichiertextecri = new File( "testnew.txt" );
            ecriteurDonneesDansUnFichier = new DataOutputStream(
                    new BufferedOutputStream( new FileOutputStream( fichiertextecri ) ) );

            ecriteurDonneesDansUnFichier.writeBoolean( true );
            ecriteurDonneesDansUnFichier.writeByte( 100 );
            ecriteurDonneesDansUnFichier.writeChars( "C" );
            ecriteurDonneesDansUnFichier.writeDouble( 12.05 );
            ecriteurDonneesDansUnFichier.writeFloat( 100.52f );
            ecriteurDonneesDansUnFichier.writeInt( 1024 );
            ecriteurDonneesDansUnFichier.writeLong( 123456789654321L );
            ecriteurDonneesDansUnFichier.writeShort( 2 );
            // attention il faut fermer l'écriture avant de lire
            ecriteurDonneesDansUnFichier.close();

            lecteurFichierGenereParUnDataInputStream = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream(
                                    fichiertextecri ) ) );
            System.out.println( lecteurFichierGenereParUnDataInputStream.readBoolean() );
            System.out.println( lecteurFichierGenereParUnDataInputStream.readByte() );
            System.out.println( lecteurFichierGenereParUnDataInputStream.readChar() );
            System.out.println( lecteurFichierGenereParUnDataInputStream.readDouble() );
            System.out.println( lecteurFichierGenereParUnDataInputStream.readFloat() );
            System.out.println( lecteurFichierGenereParUnDataInputStream.readInt() );
            System.out.println( lecteurFichierGenereParUnDataInputStream.readLong() );
            System.out.println( lecteurFichierGenereParUnDataInputStream.readShort() );

        } catch ( FileNotFoundException e ) {

            e.printStackTrace();
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        finally {
            if ( ecriteurDonneesDansUnFichier != null ) {
                try {
                    ecriteurDonneesDansUnFichier.close();
                } catch ( IOException e ) {

                    e.printStackTrace();
                }
            }

        }

        if ( lecteurFichierGenereParUnDataInputStream != null ) {
            try {
                lecteurFichierGenereParUnDataInputStream.close();
            } catch ( IOException e ) {

                e.printStackTrace();
            }
        }

    }
}
