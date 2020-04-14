import beans.UtilisateurV2;
import dao.DAOFactory;
import dao.UtilisateurDao;

public class Main {

    public static void main( String[] args ) {
        // on a un daoFactory qui a l'url,login mot de passe en param
        // il a chargé le driver
        DAOFactory daoFactory = DAOFactory.getInstance();
        UtilisateurV2 utilisateur = new UtilisateurV2();

        // on instancie utilisateurDaoImpl avec le daoFactory en paramètre qui a
        // les méthodes CRUD
        UtilisateurDao utilisateurDao = daoFactory.getUtilisateurDao();
        // cette méthode initie la connexion avec les paramètres du dao et
        // execute une requete type select et retourne un utilisateur
        utilisateur = utilisateurDao.rechercher( "npapon@live.fr" );
        System.out.println( "mail recherché  " + utilisateur.getEmail() );
        utilisateur = new UtilisateurV2();

        utilisateur.setEmail( "help14@live.fr" );
        utilisateur.setLogin( "help14" );
        utilisateur.setMotDePasse( "caca" );
        utilisateur.setNom( "help14" );

        utilisateurDao.creer( utilisateur );

        System.out.println( utilisateur.toString() );

    }
}
