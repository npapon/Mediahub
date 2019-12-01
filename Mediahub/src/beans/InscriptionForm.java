package beans;

import javax.servlet.http.HttpServletRequest;

import constantes.MessagesErreur;
import constantes.Parametres;
import constantes.Regex;

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

        Date date = new Date();
        utilisateur.setDateCreationCompte( date.dateNowFormatString() );

        return utilisateur;

    }

    public void verifierEmail( String email ) {
        if ( !email.matches( Regex.CONSTANTE_REGEX_MAIL ) ) {
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
