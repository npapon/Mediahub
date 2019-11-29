package beans;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import constantes.MessagesErreur;
import constantes.Parametres;

public class ConnexionForm {

    List<String> erreurs = new ArrayList<String>();

    public Utilisateur connecterUtilisateur( HttpServletRequest request ) {

        Utilisateur utilisateur = new Utilisateur();
        String login = request.getParameter( Parametres.CONSTANTE_PARAMETRE_LOGIN ).trim();
        verifierLogin( login );
        utilisateur.setLogin( login );
        String motdepasse = request.getParameter( Parametres.CONSTANTE_PARAMETRE_MOTDEPASSE ).trim();
        verifierMotdepasse( motdepasse );
        utilisateur.setMotDePasse( motdepasse );
        Date date = new Date();
        utilisateur.setDateDeConnexion( date.dateNowFormatString() );

        return utilisateur;
    }

    public void verifierLogin( String login ) {
        if ( login.length() <= 3 ) {
            try {
                throw new Exception( MessagesErreur.CONSTANTE_ERREUR_LOGIN );
            } catch ( Exception e ) {
                System.out.println( e.getMessage() );

                erreurs.add(
                        e.getMessage() );
            }
        }

    }

    public void verifierMotdepasse( String motdepasse ) {
        if ( motdepasse.length() <= 3 ) {
            try {
                throw new Exception( MessagesErreur.CONSTANTE_ERREUR_MOTDEPASSE );
            } catch ( Exception e ) {
                System.out.println( e.getMessage() );

                erreurs.add(
                        e.getMessage() );
            }
        }

    }
}
