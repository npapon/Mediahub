package beans;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import constantes.MessagesErreur;
import constantes.Parametres;

public class CreerFichierTextForm {

    List<String> erreurs = new ArrayList<String>();

    public Fichier CreerFichierText( HttpServletRequest request, String repertoire ) {

        String TextTape = request.getParameter( Parametres.CONSTANTE_PARAMETRE_TEXTTAPE );
        String nomFichier = request.getParameter( Parametres.CONSTANTE_PARAMETRE_NOMFICHIER ).trim();
        Fichier fichier = new Fichier();
        verifierNomFichier( nomFichier );
        fichier.setNom( nomFichier );
        fichier.setContenu( TextTape );
        String cheminFichier = repertoire + "/" + nomFichier + ".txt";
        fichier.setChemin( cheminFichier );
        DataOutputStream createurFichier = null;
        File file = new File( cheminFichier );

        try {
            createurFichier = new DataOutputStream( new BufferedOutputStream( new FileOutputStream( file ) ) );
            createurFichier.writeChars( TextTape );
            createurFichier.close();
        } catch ( FileNotFoundException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                createurFichier.close();
            } catch ( IOException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return fichier;
    }

    public void verifierNomFichier( String nom ) {
        if ( nom.length() < 3 ) {
            try {
                throw ( new Exception( MessagesErreur.CONSTANTE_ERREUR_LONGUEURNOMFICHIER ) );
            } catch ( Exception e ) {
                // TODO Auto-generated catch block
                System.out.println( e.getMessage() );
                erreurs.add( e.getMessage() );

            }
        }
    }

}
