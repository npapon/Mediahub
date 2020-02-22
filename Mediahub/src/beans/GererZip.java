package beans;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import constantes.Fichiers;
import constantes.Messages;
import constantes.MessagesErreur;

public class GererZip {

    List<String> erreurs = new ArrayList<String>();

    public void creerZip( HttpServletRequest request, String repertoire, String nomZip ) {

        FichierAvecExtension fichier = new FichierAvecExtension();
        fichier.setNomAvecExtension( request.getParameter( nomZip ), "zip" );
        nomZip = fichier.getNom();
        String cheminZip = repertoire + "\\" + nomZip;
        File file = new File( cheminZip );
        verifierExistanceZip( file );

        FileOutputStream fileOutputStream;
        DataOutputStream dataOutputStream = null;
        ZipOutputStream zipOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream( file );
            zipOutputStream = new ZipOutputStream( fileOutputStream );

            ZipEntry zipEntry = new ZipEntry( Fichiers.CONSTANTE_NOMFICHIERZIPCREEPARDEFAUT );
            zipOutputStream.putNextEntry( zipEntry );
            dataOutputStream = new DataOutputStream( zipOutputStream );
            Date date = new Date();

            dataOutputStream.writeBytes( Messages.CONSTANTE_MESSAGE_FICHIERCREEPARDEFAUTAVECLEZIP + date.dateNowFormatString() );
            zipOutputStream.close();
            dataOutputStream.close();
            zipOutputStream.close();
        } catch ( FileNotFoundException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                dataOutputStream.close();
                zipOutputStream.close();
            } catch ( IOException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    public void verifierExistanceZip( File file ) {
        if ( file.exists() ) {
            try {
                throw new Exception( MessagesErreur.CONSTANTE_ERREUR_NOMZIP );
            } catch ( Exception e ) {
                e.printStackTrace();
                erreurs.add( e.getMessage() );
            }
        }

    }

    public FileSystem definirFileSystemZip( HttpServletRequest request, String repertoire, String nomZip )

    {
        FichierAvecExtension fichier = new FichierAvecExtension();
        fichier.setNomAvecExtension( request.getParameter( nomZip ), "zip" );
        nomZip = fichier.getNom();

        String cheminZip = repertoire + "\\" + nomZip;

        try {
            FileSystem fileSystem = null;

            try {
                fileSystem = FileSystems.newFileSystem(
                        Paths.get( cheminZip ), null );

                fileSystem.close();
            } catch ( ZipException e ) {
                e.printStackTrace();
            } catch ( FileSystemNotFoundException e ) {
                e.printStackTrace();
                System.out.println( "Le fichier n'existe pas " + cheminZip );
            }

            return fileSystem;
        } catch ( IOException e ) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }

        return null;
    }

    public void supprimerUnFichierDuFileSystem( FileSystem fileSystemduzip, FichierAvecExtension fichier ) {

        Path pathDuFichierASupprimer = fileSystemduzip.getPath( fichier.getNom() );

        try {
            Files.deleteIfExists( pathDuFichierASupprimer );
            fileSystemduzip.close();
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void creerUnFichierDansLeFileSystem( FileSystem fileSystemduzip, FichierAvecExtension fichier,
            String contenuDuFichier ) {

        Path pathDuFichierACreer = fileSystemduzip.getPath( fichier.getNom() );
        try {
            Files.write( pathDuFichierACreer, contenuDuFichier.getBytes() );
            fileSystemduzip.close();
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
