package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class TestJDBC {

    /* La liste qui contiendra tous les r�sultats de nos essais */
    private List<String> messages = new ArrayList<String>();

    public List<String> executerTests( HttpServletRequest request ) {
        /* Chargement Driver */

        try {
            messages.add( "chargement driver" );
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            messages.add( "driver charg�" );
        } catch ( ClassNotFoundException e ) {
            // TODO Auto-generated catch block
            messages.add( "le driver n'a pas �t� trouv� dans le classpath <br/>"

                    + e.getMessage() );
        }

        /* Connexion */

        String url = "jdbc:mysql://localhost:3306/bdd_mediahub";
        String utilisateur = "npapon";
        String motDePasse = "Patapoun123!";
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
            messages.add( "Connexion � la bdd" );
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
            messages.add( "Connexion r�ussie" );

            /* cr�ation objet permettant les requ�tes */
            preparedStatement = connexion.prepareStatement(
                    "insert into utilisateur (login, email, mot_de_passe, nom, date_creation) values (?,?,?,?,now());",
                    Statement.RETURN_GENERATED_KEYS );
            messages.add( "Objet cr�ateur de requ�te cr��e" );

            ;

            String paramLogin = request.getParameter( "login" );
            String paramNom = request.getParameter( "nom" );
            String paramEmail = request.getParameter( "email" );
            String paramMotDePasse = request.getParameter( "motdepasse" );

            preparedStatement.setString( 1, paramLogin );
            preparedStatement.setString( 2, paramNom );
            preparedStatement.setString( 3, paramEmail );
            preparedStatement.setString( 4, paramMotDePasse );

            int statut = preparedStatement.executeUpdate();

            messages.add( "succes requ�te insert, valeur variable statut " + statut );

            // on r�cup�rer les id g�n�r�s automatiquement
            resultat = preparedStatement.getGeneratedKeys();

            // on r�cup�re les ID g�n�r� (qui sont dans la colonne 1)
            // on a pas utiliser de select :)
            while ( resultat.next() )

            {
                messages.add( "id retourner pendant la requ�te d'insertion " + resultat.getInt( 1 ) );
            }

            resultat = preparedStatement.executeQuery( "select * from utilisateur;" );

            messages.add( "requ�te select * from utilisateur exectut�e" );

            /* r�cup�ration r�sultat requ�te */
            while ( resultat.next() ) {
                int id = resultat.getInt( "id" );
                int idUtilisateur = resultat.getInt( "id" );
                String loginUtilisateur = resultat.getString( "login" );
                String emailUtilisateur = resultat.getString( 3 );
                messages.add(
                        "resultat requete  " + "Id  : " + id + " login : " + loginUtilisateur + " email : " + emailUtilisateur );
            }

        } catch (

        SQLException e ) {
            messages.add( "erreur lors de la connexion � sql <br/> " + e.getMessage() );
        } finally {

            try {
                if ( resultat != null )
                    resultat.close();

            } catch ( SQLException e ) {
                // TODO Auto-generated catch block

            }
            messages.add( "fermeture resultat" );

            try {
                if ( preparedStatement != null )
                    preparedStatement.close();
            } catch ( SQLException e ) {
                // TODO Auto-generated catch block

            }
            messages.add( "fermeture statement" );

            try {
                if ( connexion != null )
                    connexion.close();
            } catch ( SQLException e ) {
                // TODO Auto-generated catch block

            }
            messages.add( "fermeture connexion" );

        }

        return messages;
    }

}
