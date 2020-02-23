import java.util.List;

import beans.Fichier;
import beans.ListFichiersRepertoire;

public class Main {
    public static void main( String[] args ) {

        ListFichiersRepertoire listFichiersRepertoire = new ListFichiersRepertoire();
        List<Fichier> listFichiersTxt = listFichiersRepertoire
                .retournerFichiers( "C:\\Users\\npapo\\git\\Mediahub\\Mediahub\\WebContent\\fichiers", "txt" );
        System.out.println( listFichiersTxt );

        System.out.println( "helloworld.txt".endsWith( "txt" ) );

    }
}
