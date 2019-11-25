package beans;

import javax.servlet.http.HttpServletRequest;

import constantes.MessagesErreur;
import constantes.Parametres;

public class InscriptionForm extends ConnexionForm {

    public Utilisateur inscrireUtilisateur( HttpServletRequest request ) {

        Utilisateur utilisateur = new Utilisateur();
        String login = request.getParameter( Parametres.CONSTANTE_PARAMETRE_LOGIN ).trim();
        verifierLogin( login );
        utilisateur.setLogin( login );

        String motdepasse = request.getParameter( Parametres.CONSTANTE_PARAMETRE_MOTDEPASSE ).trim();
        verifierMotdepasse( motdepasse );
        utilisateur.setMotDePasse( motdepasse );

        String confirmationMotdepasse = request.getParameter( Parametres.CONSTANTE_PARAMETRE_CONFIRMATIONMOTDEPASSE.trim() );
        verifierConfirmationMotdepasse( motdepasse, confirmationMotdepasse );

        String email = request.getParameter( Parametres.CONSTANTE_PARAMETRE_EMAIL ).trim();
        verifierEmail( email );
        utilisateur.setEmail( email );

        return utilisateur;

    }

    public void verifierEmail( String email ) {
        if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            try {
                throw ( new Exception( MessagesErreur.CONSTANTE_ERREUR_EMAIL ) );
            } catch ( Exception e ) {
                {
                    System.out.println( e.getMessage() );
                    erreurs.add(
                            e.getMessage() );
                }
            }
        }
    }

    public void verifierConfirmationMotdepasse( String motdepasse, String confirmationMotdepasse ) {
        verifierMotdepasse( motdepasse );
        verifierMotdepasse( confirmationMotdepasse );
        if ( !confirmationMotdepasse.equals( motdepasse ) ) {
            try {
                throw ( new Exception( MessagesErreur.CONSTANTE_ERREUR_CONFIRMATIONMOTDEPASSE ) );
            } catch ( Exception e ) {
                System.out.println( e.getMessage() );
                erreurs.add( e.getMessage() );
            }
        }

    }
}
