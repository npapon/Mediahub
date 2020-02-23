package beans;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import constantes.MessagesErreur;

public class ElementsASupprimerDansUnTableau {

    public void supprimerLesElements( String parametre, String repertoireDesElementsASupprimer ) {
        HttpServletRequest request = null;
        String elementsasupprimer = request.getParameter( parametre );
        String[] elementsasupprimerTableau = elementsasupprimer.split( "," );

        for ( String elementlibelle : elementsasupprimerTableau ) {

            Path path = Paths.get( repertoireDesElementsASupprimer + "/" + elementlibelle );

            try {
                if ( Files.exists( path ) ) {
                    Files.delete( path );
                }
            } catch ( NoSuchFileException e ) {
                e.printStackTrace();
                System.out.println( MessagesErreur.CONSTANTE_ERREUR_ELEMENT_INEXISTANT_TABLEAU + path.getFileName() );
            } catch ( IOException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }
}
