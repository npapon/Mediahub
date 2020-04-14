package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.UtilisateurV2;

public class UtilisateurDaoImpl implements UtilisateurDao {

    private static final String SQL_SELECT_PAR_EMAIL = "SELECT * FROM Utilisateur WHERE email = ?";
    private static final String SQL_INSERT           = "insert into utilisateur (login, email, mot_de_passe, nom, date_creation) values (?,?,?,?,now())";

    private DAOFactory          daoFactory;
    // cet objet prend un DAOFactory pour se connecter dans le constructeur
    // mais son initialisation va se faire dans le FAOFactory gr�ce � la m�thode
    // ci dessous dans DAOFactory

    /*
     * public UtilisateurDao getUtilisateurDao() { return new
     * UtilisateurDaoImpl( this ); }
     */

    // on aurai pu mettre aussi
    /*
     * public UtilisateurDaoImpl getUtilisateurDao() { return new
     * UtilisateurDaoImpl( this ); }
     */

    // mais on veut que l'impl�mentation passe par l'interface et non par
    // UtilisateurDaoImpl

    UtilisateurDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void creer( UtilisateurV2 utilisateur ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* R�cup�ration d'une connexion depuis la Factory */
            connexion = daoFactory.getConnexion();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, utilisateur.getLogin(),
                    utilisateur.getEmail(), utilisateur.getMotDePasse(), utilisateur.getNom() );

            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourn� par la requ�te d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "�chec de la cr�ation de l'utilisateur, aucune ligne ajout�e dans la table." );
            }
            /* R�cup�ration de l'id auto-g�n�r� par la requ�te d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                /*
                 * Puis initialisation de la propri�t� id du bean Utilisateur
                 * avec sa valeur ca permet de bien cr�� un utilisateur complet
                 */
                utilisateur.setId( valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new DAOException( "�chec de la cr�ation de l'utilisateur en base, aucun ID auto-g�n�r� retourn�." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }

    }

    @Override
    public UtilisateurV2 rechercher( String email ) throws DAOException {

        /*
         * il faut penser que toutes les autres classes qu'on a cr�� sont des
         * classes standardis�es dont la finalit� sert juste � ce que les
         * classes de type nomtablebaseDaoImpl ait acc�s � toutes les infos de
         * connexion pour executer les m�thodes de cette classe qui sont celles
         * qui attaquent la base
         * 
         * si c'est une requete de type select on retoune dans la m�thode
         * l'objet qui nous interesse ici utilisateur
         * 
         * si c'est une requete de type modif c'est du void
         * 
         * ici c'est une select
         * 
         * de quoi a t'on besoin pour executer une requete
         * 
         * d'une connexion qu'on peut initier avec la m�thode
         * daoFactory.getConnection()
         * 
         * d'un statement qu'on peut initier avec la m�thode statique
         * DAOUtilitaire.preparedStatement (dans laquelle on passe en param�tre
         * la requete sql)
         * 
         * �ventuellement d'un ResultSet pour les requ�te de type select
         * 
         * et ne pas oublier d'executer la requ�te avec
         * preparedStatement.executeQuery();
         * 
         * pour les requete de type select, on peut cr�er map avec la m�thode
         * map de cette classe un objet utilisateur
         * 
         * si avec toutes ces op�rations on catch une SQLException, on throw une
         * new DAOException()
         * 
         * ne pas oublier de fermer les connexions avec la m�thode dans le
         * finally avec la m�thode fermeturesSilencieuse de la classe
         * fermeturesSilencieuses( resultSet, preparedStatement, connexion ); de
         * la classe DAOUtilitaire
         * 
         * et si comme rechercher est de type select on retourne un utilisateur
         * � la fin
         */

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UtilisateurV2 utilisateur = null;

        try {
            /* R�cup�ration d'une connexion depuis la Factory */
            connexion = daoFactory.getConnexion();
            // au lieu de DAOUtilitaire.initialisationRequetePreparee
            // car on a import� import static dao.DAOUtilitaire.*;
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_EMAIL, true, email );
            resultSet = preparedStatement.executeQuery();
            /*
             * Parcours de la ligne de donn�es de l'�ventuel ResulSet retourn�
             */

            if ( resultSet.next() ) {
                utilisateur = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            // au lieu de DAOUtilitaire.fermeturesSilencieuses
            // car on a import� import static dao.DAOUtilitaire.*;
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return utilisateur;
    }

    /*
     * Correspond � cela en plus g�n�rique (on verra apr�s comment est g�r� le
     * while(resultat.next())
     * 
     * preparedStatement = connexion.prepareStatement(
     * "select * from utilisateur" ); resultat =
     * preparedStatement.executeQuery();
     * 
     * while ( resultat.next() ) {
     * 
     * int idUtilisateur = resultat.getInt( "id" );
     * 
     * String loginUtilisateur = resultat.getString( "login" );
     * 
     * String emailUtilisateur = resultat.getString( 3 );
     * 
     * 
     * 
     * }
     */

    public static UtilisateurV2 map( ResultSet resultset ) throws SQLException {

        UtilisateurV2 utilisateur = new UtilisateurV2();

        utilisateur.setId( resultset.getInt( "id" ) );
        utilisateur.setLogin( resultset.getString( "login" ) );
        utilisateur.setEmail( resultset.getString( "email" ) );
        utilisateur.setMotDePasse( resultset.getString( "mot_de_passe" ) );
        utilisateur.setNom( resultset.getString( "nom" ) );
        utilisateur.setDateCreation( resultset.getTimestamp( "date_creation" ) );

        return utilisateur;
    }

}
