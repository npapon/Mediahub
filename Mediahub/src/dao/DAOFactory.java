package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {

    // ------------------- constantes------------------------
    // on d�clare des constantes qui correspondent aux noms de variables du
    // fichier dao.properties

    private static final String FICHIER_PROPERTIES       = "dao/dao.properties";
    private static final String PROPERTY_URL             = "url";
    private static final String PROPERTY_DRIVER          = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
    private static final String PROPERTY_MOT_DE_PASSE    = "motdepasse";

    // ------------------- variables/attributs------------------------
    // voici les attributs de notre DaoFactory qu'on va setter avec le
    // constructeur

    private String              url;
    private String              nomutilisateur;
    private String              motdepasse;

    // -------------------le constructeur --------------------------
    // on cr�� le constructeur de notre classe DAOFactory avec les attributs de
    // la classes
    DAOFactory( String url, String nomutilisateur, String motdepasse ) {
        this.url = url;
        this.nomutilisateur = nomutilisateur;
        this.motdepasse = motdepasse;

    }

    // -------getInstance() m�thode qui va sett� le constructeur avec les
    // variables r�cup�r�s dans dao.properties � savoir les identifiants de
    // connexion et charge le driver avant----------
    // cette m�thode va :
    // 1. R�cup�rer les informations de connexion
    // 2. Charger le driver
    // 3. Retourner un objet DAOFactory (le contructeur)
    // L'objectif principal est d'instancier la classe DAOFactory
    // il s'agit l� d'une m�thode statique pour pouvoir l'appeler avant
    // d'instancier l'objet DAOFactory
    // Car c'est son r�le:)
    // pourquoi instancier avec une m�thode et pas juste un new ... cad un
    // constructeur classique
    // car nous voulons instancier notre DAOFactory uniquement sous certaines
    // conditions :
    // a) si le fichier dao.properties est accessible;
    // b) si les donn�es qu'il contient sont valides;
    // c) si le driver JDBC est bien pr�sent dans l'application.
    //

    public static DAOFactory getInstance() throws DAOConfigurationException {

        // nous initialisons un objet Properties qui, comme son nom l'indique,
        // va nous permettre de g�rer notre fichier de configuration
        Properties properties = new Properties();
        // on d�clare des variables
        String url;
        String nomutilisateur;
        String motdepasse;
        String driver;

        // nous proc�dons � l'ouverture du fichier dao.properties gr�ce � la
        // m�thode getResourceAsStream() de l'objet ClassLoader
        // Pourquoi ne pas avoir utilis� un traditionnel FileInputStream
        // pour ouvrir notre fichier properties ?
        // on utilise ici une m�thode plus sophistiqu�e � premi�re vue mais qui
        // en r�alit� est tr�s simple :
        // elle consiste � appeler la m�thode getResourceAsStream() de l'objet
        // ClassLoader,
        // qui se charge pour nous d'ouvrir le flux demand� et de retourner null
        // en cas d'erreur
        // NB : nous r�cup�rons le ClassLoader depuis le thread courant gr�ce �
        // la m�thode getContextClassLoader()
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );

        // du coup si fichierProperties est null, cela signifie que le flux n'a
        // pas �t� ouvert
        // et on envoie une erreur une exception personnalis�e
        // DAOConfigurationException
        // stipulant que le fichier a pas �t� trouv�
        if ( fichierProperties == null ) {
            throw new DAOConfigurationException( "Le fichier properties est introuvable " + FICHIER_PROPERTIES );
        }

        // On va charger les propri�t�s contenues dans le fichier
        try {
            properties.load( fichierProperties );
            url = properties.getProperty( PROPERTY_URL );
            nomutilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
            motdepasse = properties.getProperty( PROPERTY_MOT_DE_PASSE );
            driver = properties.getProperty( PROPERTY_DRIVER );
        } catch ( IOException e ) {
            /*
             * rappel du contructeur de DAOConfigurationException cause c'est
             * l'exception e qui a g�n�r� l'exception
             */
            // public DAOConfigurationException( String message, Throwable cause
            // ) { super( message, cause ); }

            throw new DAOConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
        }

        // on peut maintenant charger le driver JDBC dont le nom est pr�cis�
        // dans le fichier dao.properties
        /*
         * c'est comme dans le main sauf 'encapsulation de l'exception envoy�e
         * en cas d'erreur (driver introuvable) dans une exception personnalis�e
         * de type DAOConfigurationException.
         * 
         * try { Class.forName( "com.mysql.cj.jdbc.Driver" ); } catch (
         * ClassNotFoundException e ) {
         * 
         * e.printStackTrace(); }
         */

        try {
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            throw new DAOConfigurationException( "Driver introuvable dans le classath ", e );
        }

        // en cas de succ�s des �tapes pr�c�dente,on peut instancier le
        // DAOFactory
        System.out.println( "Info de connexion"
                + "url : " + url
                + "nomutilisateur :" + nomutilisateur
                + "motdepasse :" + motdepasse
                + "driver :" + driver );
        DAOFactory instance = new DAOFactory( url, nomutilisateur, motdepasse );
        return instance;
    }

    // ------------------getConnexion connecte � la BDD avec les variables sett�
    // par le constructeur de la m�thode plus
    // haut----------------------------------------------------------
    // Nous cr�ons ensuite la m�thode getConnection()
    // charg�e de fournir une connexion � la base de donn�es
    // comme dans le main
    /*
     * connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
     * }catch(SQLException e)
     */

    // les valeur sont sett�s grace au constructeur dans le main
    //
    public Connection getConnexion() throws SQLException {
        return DriverManager.getConnection( url, nomutilisateur, motdepasse );
    }

    // -------------------getter d�crit plus bas ----------
    // nous devons �crire les getters retournant les diff�rentes impl�mentations
    // de DAO
    // on va le faire apr�s

    // cette m�thode va instancier un UtilisateurDaoImpl (qui a le code des
    // m�thodes d'acc�s
    // � la bdd
    //
    public UtilisateurDao getUtilisateurDao() {
        return new UtilisateurDaoImpl( this );
    }

}
