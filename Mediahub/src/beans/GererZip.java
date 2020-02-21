package beans;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import constantes.MessagesErreur;

public class GererZip {

    List<String> erreurs = new ArrayList<String>();

    public void creerZip( String emplacement ) {

        File file = new File( emplacement );
        verifierExistanceZip( file );

        FileOutputStream fileOutputStream;
        DataOutputStream dataOutputStream = null;
        ZipOutputStream zipOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream( file );
            zipOutputStream = new ZipOutputStream( fileOutputStream );

            ZipEntry zipEntry = new ZipEntry( "info.txt" );
            zipOutputStream.putNextEntry( zipEntry );
            dataOutputStream = new DataOutputStream( zipOutputStream );
            Date date = new Date();

            dataOutputStream.writeBytes( "Fichier zip créé le " + date.dateNowFormatString() );
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

    public Map definirFileSystemZip( HttpServletRequest request, String repertoire, String nomZip )

    {
        Boolean fichierDejaExistant;
        Map<FileSystem, Boolean> map = new HashMap<FileSystem, Boolean>();
        FichierAvecExtension fichier = new FichierAvecExtension();
        fichier.setNomAvecExtension( request.getParameter( nomZip ), "zip" );
        nomZip = fichier.getNom();
        String cheminZip = repertoire + "\\" + nomZip;
        Path path = Paths.get( cheminZip );
        if ( !Files.exists( Paths.get( cheminZip ) ) ) {
            try {
                FileSystem fileSystem = null;
                Files.createFile( Paths.get( cheminZip ) );

                try {
                    fileSystem = FileSystems.newFileSystem(
                            Paths.get( cheminZip ), null );

                    map.put( fileSystem, true );
                    fileSystem.close();
                } catch ( ZipException e ) {
                    e.printStackTrace();
                } finally {
                    fichierDejaExistant = true;
                    map.put( fileSystem, true );
                    System.out.println( "ouch" + map );

                }

                return map;
            } catch ( IOException e ) {
                // TODO Auto-generated catch block

                e.printStackTrace();
            }
        } else {
            try {
                FileSystem fileSystem = null;

                try {
                    fileSystem = FileSystems.newFileSystem(
                            Paths.get( cheminZip ), null );
                    System.out.println( "filesystem 2" + fileSystem );
                    map.put( fileSystem, false );
                    fileSystem.close();
                } catch ( ZipException e ) {
                    e.printStackTrace();
                } finally {
                    fichierDejaExistant = false;
                    map.put( fileSystem, false );
                    System.out.println( "pasouch" + map );

                }

                return map;
            } catch ( IOException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return null;
    }

    public FichierAvecExtension creerUnFichierZip( HttpServletRequest request, String champFormulaireSaisieNom ) {

        FichierAvecExtension fichierZip = new FichierAvecExtension();

        // Parametres.CONSTANTE_PARAMETRE_NOMFICHIER
        String nom = request.getParameter( champFormulaireSaisieNom );

        fichierZip.setNomAvecExtension( nom, "zip" );

        return fichierZip;
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
