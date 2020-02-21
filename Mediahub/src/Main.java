import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import beans.Date;
import beans.GererZip;

public class Main {
    public static void main( String[] args ) throws IOException {

        GererZip gererZip = new GererZip();
        gererZip.creerZip( "C:\\Users\\npapo\\git\\Mediahub\\Mediahub\\WebContent\\fichiers\\newzip12.zip" );
        File fichier = new File( "C:\\Users\\npapo\\git\\Mediahub\\Mediahub\\WebContent\\fichiers\\newzip10.zip" );

        // ouverture d'un flux Zip sur ce fichier
        ZipOutputStream zipOutputStream = new ZipOutputStream( new FileOutputStream( fichier ) );

        // cr�ation d'un r�pertoire : il s'agit d'une entr�e dont le nom
        // se termine par un /
        ZipEntry entry = new ZipEntry( "vide/" );

        zipOutputStream.putNextEntry( entry );

        // cr�ation d'un autre r�pertoire
        entry = new ZipEntry( "test.txt" );
        zipOutputStream.putNextEntry( entry );
        entry = new ZipEntry( "vide/test.txt" );

        zipOutputStream.putNextEntry( entry );
        entry = new ZipEntry( "bonjour/Bonjour-2.txt" );
        zipOutputStream.putNextEntry( entry );
        // cr�ation d'un fichier Bonjour-1.txt dans ce r�pertoire
        entry = new ZipEntry( "bonjour/Bonjour-3.txt" );
        zipOutputStream.putNextEntry( entry );

        // ouverture d'un flus de date sur le zip pour �crire dedans
        // �crit dans le dernier entry
        DataOutputStream dataOutputStream = new DataOutputStream( zipOutputStream );
        Date date = new Date();

        dataOutputStream.writeBytes( "Fichier zip cr�� le " + date.dateNowFormatString() );
        zipOutputStream.close();
        dataOutputStream.close();
    }
}
