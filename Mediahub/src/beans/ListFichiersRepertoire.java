package beans;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ListFichiersRepertoire {

    public List<Fichier> retournerFichiers( String repertoireAbsolu, String extensionFichier ) {
        List<Fichier> fichiers = new ArrayList<Fichier>();
        Path dossierFichiers = Paths.get( repertoireAbsolu );

        try {
            DirectoryStream<Path> stream = Files.newDirectoryStream( dossierFichiers );

            for ( Path path : stream ) {
                if ( path.getFileName().toString().endsWith( extensionFichier ) ) {

                    Fichier fichier = new Fichier();

                    fichier.setPath( path );
                    fichier.setPathNomFichier( path.getFileName() );
                    fichier.setNom( path.getFileName().toString() );

                    fichiers.add( fichier );
                }
            }

        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return fichiers;

    }

}
