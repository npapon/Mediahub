package beans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LecteurFichierFileReaderBean {

    public Fichier lireFichierFileReaderBean( String nom, String repertoireAbsolu ) {

        Fichier fichier = new Fichier();
        fichier.setNom( nom );
        String chemin = repertoireAbsolu + "/" + nom;
        File file = new File( repertoireAbsolu + "/" + nom );
        fichier.setChemin( chemin );
        String fichierlu = "";
        FileReader fileReader = null;
        int i = 0;

        try {
            fileReader = new FileReader( file );
            while ( ( i = fileReader.read() ) != -1 ) {
                fichierlu += (char) i;
            }

            fileReader.close();
        } catch ( FileNotFoundException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        fichier.setContenu( fichierlu );

        return fichier;
    }

}
