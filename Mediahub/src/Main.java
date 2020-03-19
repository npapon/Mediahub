import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main( String[] args ) {

        try {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {
            /* Gérer les éventuelles erreurs ici. */
            e.printStackTrace();
        }

        /* Connexion à la base de données */
        String url = "jdbc:mysql://localhost:3306/bdd_mediahub";
        String utilisateur = "npapon";
        String motDePasse = "Patapoun123!";
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        try {
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );

            statement = connexion.createStatement();
            resultat = statement.executeQuery( "select * from utilisateur" );
            while ( resultat.next() ) {
                int idUtilisateur = resultat.getInt( "id" );
                String loginUtilisateur = resultat.getString( "login" );
                String emailUtilisateur = resultat.getString( 3 );
                System.out.println( idUtilisateur + " " + loginUtilisateur + " " + emailUtilisateur );

            }

            int statut = statement.executeUpdate(
                    "update utilisateur set date_creation =now() ;" );

            System.out.println( statut );

            /* Ici, nous placerons nos requêtes vers la BDD */
            /* ... */

        } catch ( SQLException e ) {
            /* Gérer les éventuelles erreurs ici */
            e.printStackTrace();
        } finally {

            if ( resultat != null ) {
                try {
                    resultat.close();
                } catch ( SQLException e ) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if ( statement != null ) {
                    try {
                        statement.close();
                    } catch ( SQLException e ) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if ( connexion != null )
                        try {
                            /* Fermeture de la connexion */
                            connexion.close();
                        } catch ( SQLException e ) {
                            /*
                             * Si une erreur survient lors de la fermeture, il
                             * suffit de l'ignorer.
                             */
                        }

                }

            }
        }

    }

}
