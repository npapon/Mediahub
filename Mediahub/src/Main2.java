import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main2 {
    public static void main( String[] args ) {

        // A. CHARGER DRIVER (ON CATCHE UNE CLASSNOTFOUNDEXCEPTION (SI DRIVER
        // ABSENT)
        try {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {

            e.printStackTrace();
        }
        // B. DEFINIR VARIABLES DE CONNEXION (url de la bdd, utilisateur, mot de
        // passe)
        String url = "jdbc:mysql://localhost:3306/bdd_mediahub";
        String utilisateur = "npapon";
        String motDePasse = "Patapoun123!";

        // C. DEFINIR OBJETS POUR SE CONNECTER A LA BASE :
        // UNE CONNECTION POUR SE CONNECTER
        // UNE PREPAREDSTATEMENT POUR FAIRE LES REQUETES
        // UN RESULSET FACULTATIF POUR LES REQUETES DE TYPE SELECT
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        // D. SE CONNECTER ET EXECUTER LES REQUETES (ON CATCH UNE SQLEXCEPTION)
        try {
            // D.1 SE CONNECTER
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
            // D.2. FAIRE UNE REQUETE DE TYPE SELECT
            preparedStatement = connexion.prepareStatement( "select * from utilisateur" );
            // ON RECUPERE LE RESULTAT DANS LE RESULTSET
            resultat = preparedStatement.executeQuery();
            // ON AFFICHE LIGNE A LIGNE LES DONNES DE COLONNES TANT QU ON A DES
            // RESULTATS
            while ( resultat.next() ) {
                int idUtilisateur = resultat.getInt( "id" );
                String loginUtilisateur = resultat.getString( "login" );
                String emailUtilisateur = resultat.getString( 3 );
                System.out.println( idUtilisateur + " " + loginUtilisateur + " " + emailUtilisateur );

            }
            // D.3. FAIRE UNE REQUETE DE TYPE UPDATE
            preparedStatement = connexion.prepareStatement( "update utilisateur set date_creation =now() ;" );
            // LE INTEGER STATUT PERMET DE RECUPERER LE NOMBRE DE LIGNES
            // IMPACTEES
            int statut = preparedStatement.executeUpdate();

            System.out.println( "nombre de ligne modifié " + statut );
            // D. 3 FAIRE UNE REQUETE DE TYPE INSERT AVEC RECUPERATION DE L ID
            // AUTO GENERE
            // ET DONNEES PARAMETRABLES

            preparedStatement = connexion.prepareStatement(
                    "insert into utilisateur (login, email, mot_de_passe, nom, date_creation) values (?,?,?,?,now());",
                    preparedStatement.RETURN_GENERATED_KEYS );

            preparedStatement.setString( 1, "jrouach2" );
            preparedStatement.setString( 2, "rouach2" );
            preparedStatement.setString( 3, "jeremie2.rouach@canal-plus.com" );
            preparedStatement.setString( 4, "avatar" );

            preparedStatement.executeUpdate();
            // ON RECUPERE DANS UN RESULTSET L ID AUTOGENERE QUI EST DANS LA
            // COLONNE 1 (D OU LE GETINT(1))
            resultat = preparedStatement.getGeneratedKeys();

            while ( resultat.next() )

            {
                System.out.println( resultat.getInt( 1 ) );

            }

        } catch ( SQLException e ) {

            e.printStackTrace();

            // E. ON FERME LES OBJETS DANS L ORDRE RESULSET PREPAREDSTAMTENT ET
            // CONNECTION
        } finally {

            if ( resultat != null ) {
                try {
                    resultat.close();
                } catch ( SQLException e ) {
                    e.printStackTrace();
                }
            }

            if ( preparedStatement != null ) {
                try {
                    preparedStatement.close();
                } catch ( SQLException e ) {
                    e.printStackTrace();
                }
            }

            if ( connexion != null )
                try {
                    connexion.close();
                } catch ( SQLException e ) {
                }

        }
    }

}
