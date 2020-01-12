package beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LecteurFichier {

    public Fichier lireFichier( String nom, String repertoireAbsolu ) {

        Fichier fichier = new Fichier();
        fichier.setNom( nom );
        String chemin = repertoireAbsolu + "/" + nom;
        File file = new File( repertoireAbsolu + "/" + nom );
        fichier.setChemin( chemin );

        FileInputStream lecteurfichier = null;
        byte[] bytes = new byte[8];
        String fichierlu = "";

        try {
            lecteurfichier = new FileInputStream( file );
            while ( lecteurfichier.read( bytes ) > 0 ) {
                for ( byte bite : bytes ) {
                    fichierlu = fichierlu + (char) bite;
                }

            }

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
