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
import java.nio.file.NoSuchFileException;
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
import constantes.MessagesSucces;

public class GererZip {

    List<String> erreurs = new ArrayList<String>();

    public FichierAvecExtension creerZip( HttpServletRequest request, String repertoire, String nomZip ) {

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
            System.out.println( MessagesSucces.CONSTANTE_SUCCES_CREATIONZIP + cheminZip );
            return fichier;
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
        return null;

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

                System.out.println( MessagesSucces.CONSTANTE_SUCCES_CREATIONFILESYSTEM + fileSystem );

            } catch ( ZipException e ) {
                e.printStackTrace();

            } catch ( FileSystemNotFoundException e ) {
                e.printStackTrace();

                System.out.println( MessagesErreur.CONSTANTE_ERREUR_FILESYSTEMINTROUVABLE + cheminZip );
                erreurs.add( MessagesErreur.CONSTANTE_ERREUR_FILESYSTEMINTROUVABLE + cheminZip );
            }

            return fileSystem;
        } catch ( IOException e ) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }

        return null;
    }

    public void deplacerLesFichiersDansLeZip( HttpServletRequest request, String parametreListantElements,
            String repertoireDesElementsADeplacer,
            FileSystem fileSystemDuZip ) {

        String elementsadeplacer = request.getParameter( parametreListantElements );
        String[] elementsadeplacerTableau = elementsadeplacer.split( "," );

        for ( String elementlibelle : elementsadeplacerTableau ) {

            Path path = Paths.get( repertoireDesElementsADeplacer + "/" + elementlibelle );

            try {
                Files.copy( path, fileSystemDuZip.getPath( elementlibelle ) );

            } catch ( NoSuchFileException e ) {
                e.printStackTrace();
                System.out.println( MessagesErreur.CONSTANTE_ERREUR_ELEMENT_INEXISTANT_TABLEAU + path.getFileName() );
                erreurs.add( MessagesErreur.CONSTANTE_ERREUR_ELEMENT_INEXISTANT_TABLEAU + path.getFileName() );
            } catch ( IOException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

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

    public List<String> getErreurs() {
        return this.erreurs;
    }

}
