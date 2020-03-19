package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class TestJDBC {

    /* La liste qui contiendra tous les résultats de nos essais */
    private List<String> messages = new ArrayList<String>();

    public List<String> executerTests( HttpServletRequest request ) {
        /* Chargement Driver */

        try {
            messages.add( "chargement driver" );
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            messages.add( "driver chargé" );
        } catch ( ClassNotFoundException e ) {
            // TODO Auto-generated catch block
            messages.add( "le driver n'a pas été truvé dans le classpath <br/>"

                    + e.getMessage() );
        }

        /* Connexion */

        String url = "jdbc:mysql://localhost:3306/bdd_mediahub";
        String utilisateur = "npapon";
        String motDePasse = "Patapoun123!";
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            messages.add( "Connexion à la bdd" );
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
            messages.add( "Connexion réussie" );

            /* création objet permettant les requêtes */
            statement = connexion.createStatement();
            messages.add( "Objet créateur de requête créée" );

            resultat = statement.executeQuery( "select * from utilisateur;" );

            messages.add( "requête select * from utilisateur exectutée" );

            /* récupération résultat requête */
            while ( resultat.next() ) {
                int id = resultat.getInt( "id" );
                int idUtilisateur = resultat.getInt( "id" );
                String loginUtilisateur = resultat.getString( "login" );
                String emailUtilisateur = resultat.getString( 3 );
                messages.add(
                        "resultat requete  " + "Id  : " + id + " login : " + loginUtilisateur + " email : " + emailUtilisateur );
            }

        } catch ( SQLException e ) {
            messages.add( "erreur lors de la connexion à sql <br/> " + e.getMessage() );
        } finally {

            try {
                if ( resultat != null )
                    resultat.close();

            } catch ( SQLException e ) {
                // TODO Auto-generated catch block

            }
            messages.add( "fermeture resultat" );

            try {
                if ( statement != null )
                    statement.close();
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
