package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DAOUtilitaire {

    public static PreparedStatement initialisationRequetePreparee( Connection connexion, String sql, boolean returnGeneratedKeys,
            Object... objects ) throws SQLException {

        /*
         * correspond à de manière générique
         * 
         * preparedStatement = connexion.prepareStatement( "insert into
         * utilisateur (login, email, mot_de_passe, nom, date_creation) values
         * (?,?,?,?,now());" , preparedStatement.RETURN_GENERATED_KEYS );
         */

        PreparedStatement preparedStatement = connexion.prepareStatement( sql,
                returnGeneratedKeys ? PreparedStatement.RETURN_GENERATED_KEYS : PreparedStatement.NO_GENERATED_KEYS );

        /*
         * correspond à de manière générique
         * 
         * preparedStatement.setString( 1, "jrouach" );
         * preparedStatement.setString( 2, "rouach" );
         * preparedStatement.setString( 3, "jeremie.rouach@canal-plus.com" );
         * 
         * on grace au var-args appelé la méthode avec autant de critères
         * souhaités pour les objets le compilateur regroupera lui-même tous les
         * arguments supplémentaires dans un simple tableau.
         * 
         * initialisationRequetePreparee( connexion, requeteSQL, true, email);
         * 
         * initialisationRequetePreparee( connexion, requeteSQL, true, email,
         * motDePasse );
         * 
         * initialisationRequetePreparee( connexion, requeteSQL, true, email,
         * motDePasse, nom );
         * 
         * initialisationRequetePreparee( connexion, requeteSQL, true, email,
         * motDePasse, nom, dateInscription );
         * 
         * On prend Objet en paramètre car on sait pas de quel type sera le
         * paramètre (String ...)
         */
        for ( int i = 0; i < objects.length; i++ )

        {
            preparedStatement.setObject( i + 1, objects[i] );
        }

        return preparedStatement;
    }

    /*
     * equivaut à ce passage finally {
     * 
     * if ( resultat != null ) { try { resultat.close(); } catch ( SQLException
     * e ) { e.printStackTrace(); } }
     * 
     * if ( preparedStatement != null ) { try { preparedStatement.close(); }
     * catch ( SQLException e ) { e.printStackTrace(); } }
     * 
     * if ( connexion != null ) try { connexion.close(); } catch ( SQLException
     * e ) { }
     * 
     * }
     */
    public static void fermetureSilencieuse( ResultSet resultSet ) {

        try {
            if ( resultSet != null )
                resultSet.close();
        } catch ( SQLException e ) {
            // TODO Auto-generated catch block
            System.out.println( "Echec de la fermeture du ResultSet " + e.getMessage() );
        }
    }

    public static void fermetureSilencieuse( Statement statement ) {

        try {
            if ( statement != null )
                statement.close();
        } catch ( SQLException e ) {
            // TODO Auto-generated catch block
            System.out.println( "Echec de la fermeture du Statement " + e.getMessage() );
        }
    }

    public static void fermetureSilencieuse( Connection connexion ) {

        try {
            if ( connexion != null )
                connexion.close();
        } catch ( SQLException e ) {
            // TODO Auto-generated catch block
            System.out.println( "Echec de la fermeture de la connexion " + e.getMessage() );
        }
    }

    public static void fermeturesSilencieuses( ResultSet resultSet, Statement statement, Connection connexion ) {
        fermetureSilencieuse( resultSet );
        fermetureSilencieuse( statement );
        fermetureSilencieuse( connexion );

    }

    public static void fermeturesSilencieuses( Statement statement, Connection connexion ) {

        fermetureSilencieuse( statement );
        fermetureSilencieuse( connexion );

    }

}
