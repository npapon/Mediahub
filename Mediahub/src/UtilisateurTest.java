import beans.UtilisateurV2;
import dao.DAOException;
import dao.UtilisateurDao;

public class UtilisateurTest implements UtilisateurDao {

    @Override
    public void creer( UtilisateurV2 utilisateur ) throws DAOException {
        System.out.println( "gay" );

    }

    @Override
    public UtilisateurV2 rechercher( String email ) throws DAOException {
        // TODO Auto-generated method stub
        UtilisateurV2 utilisateur = new UtilisateurV2();
        return utilisateur;
    }

}
