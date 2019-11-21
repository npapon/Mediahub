package beans;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import constantes.MessagesErreur;
import constantes.Parametres;

public final class ConnexionForm {

    List<String> erreurs = new ArrayList<String>();

    public Utilisateur inscrireUtilisateur( HttpServletRequest request, String login, String motDePasse ) {

        Utilisateur utilisateur = new Utilisateur();
        login = request.getParameter( Parametres.CONSTANTE_PARAMETRE_LOGIN ).trim();
        verifierLogin( login );
        utilisateur.setLogin( login );
        motDePasse = request.getParameter( Parametres.CONSTANTE_PARAMETRE_MOTDEPASSE ).trim();
        verifierMotdepasse( motDePasse );
        utilisateur.setMotDePasse( motDePasse );

        return utilisateur;
    }

    private void verifierLogin( String login ) {
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

    private void verifierMotdepasse( String motDePasse ) {
        if ( motDePasse.length() <= 3 ) {
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
