package beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class EcriteurFichier {

    public Fichier ecrireFichierParDuplication( String nomFichierLu, String nomFichierEcri,
            String repertoireAbsoluFichierLu, String repertoireAbsoluFichierEcri ) {

        Fichier fichierLu = new Fichier();
        fichierLu.setNom( nomFichierLu );
        String cheminFichierLu = repertoireAbsoluFichierLu + "/" + nomFichierLu;
        File fileFichierLu = new File( repertoireAbsoluFichierLu + "/" + nomFichierLu );
        fichierLu.setChemin( cheminFichierLu );

        Fichier fichierEcri = new Fichier();
        fichierEcri.setNom( nomFichierEcri );
        String cheminFichierEcri = repertoireAbsoluFichierEcri + "/" + nomFichierEcri;
        File fileFichierEcri = new File( repertoireAbsoluFichierEcri + "/" + nomFichierEcri );
        fichierEcri.setChemin( cheminFichierEcri );

        FileInputStream lecteurfichier = null;
        FileOutputStream ecriteurfichier = null;
        byte[] bytes = new byte[8];
        String contenuFichierLu = "";
        String contenuFichierEcri = "";

        try {
            lecteurfichier = new FileInputStream( fileFichierLu );
            ecriteurfichier = new FileOutputStream( fileFichierEcri );
            while ( lecteurfichier.read( bytes ) > 0 ) {
                ecriteurfichier.write( bytes );
                for ( byte bite : bytes ) {

                    contenuFichierLu = contenuFichierLu + (char) bite;
                    contenuFichierEcri = contenuFichierEcri + (char) bite;

                }

            }

        } catch ( FileNotFoundException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if ( lecteurfichier != null ) {
                try {
                    lecteurfichier.close();
                } catch ( IOException e ) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if ( ecriteurfichier != null ) {
                try {
                    ecriteurfichier.close();
                } catch ( IOException e ) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        fichierLu.setContenu( contenuFichierLu );
        fichierEcri.setContenu( contenuFichierEcri );
        return fichierEcri;

    }

}
