package beans;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import constantes.MessagesErreur;

public class AdministrationImages {

    public List<Image> retournerImages( String type, String repertoireAbsolu, String repertoireRelatif ) {
        File dossierImages = new File( repertoireAbsolu );
        List<Image> images = new ArrayList<Image>();
        try {
            for ( File file : dossierImages.listFiles() ) {
                Image image = new Image();
                image.setType( type );
                image.setLibelle( file.getName() );
                image.setEmplacement( repertoireRelatif + file.getName() );

                images.add( image );
            }

            return images;

        } catch ( NullPointerException e ) {
            System.out.println( MessagesErreur.CONSTANTE_ERREUR_DOSSIERIMAGESPROFIL );
            return null;
        }

    }

}
