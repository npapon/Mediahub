package beans;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ListFichiersRepertoire {

    public List<Fichier> retournerFichiersText( String type, String repertoireAbsolu, String extensionFichier ) {
        List<Fichier> fichiersText = new ArrayList<Fichier>();
        Path dossierFichiersText = Paths.get( repertoireAbsolu );

        try {
            DirectoryStream<Path> stream = Files.newDirectoryStream( dossierFichiersText );

            for ( Path path : stream ) {
                if ( path.getFileName().endsWith( ".txt" ) ) {
                    Fichier fichierText = new Fichier();
                    fichierText.setType( type );
                    fichierText.setPath( path );
                    fichierText.setPathNomFichier( path.getFileName() );
                    fichiersText.add( fichierText );
                }
            }

        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return fichiersText;

    }

}
