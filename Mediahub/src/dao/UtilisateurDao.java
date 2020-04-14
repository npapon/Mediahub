package dao;

import beans.UtilisateurV2;

public interface UtilisateurDao {

    void creer( UtilisateurV2 utilisateur ) throws DAOException;

    UtilisateurV2 rechercher( String email ) throws DAOException;

}
