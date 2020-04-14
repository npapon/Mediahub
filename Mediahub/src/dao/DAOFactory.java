package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {

    // ------------------- constantes------------------------
    // on déclare des constantes qui correspondent aux noms de variables du
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
    // on créé le constructeur de notre classe DAOFactory avec les attributs de
    // la classes
    DAOFactory( String url, String nomutilisateur, String motdepasse ) {
        this.url = url;
        this.nomutilisateur = nomutilisateur;
        this.motdepasse = motdepasse;

    }

    // -------getInstance() méthode qui va setté le constructeur avec les
    // variables récupérés dans dao.properties à savoir les identifiants de
    // connexion et charge le driver avant----------
    // cette méthode va :
    // 1. Récupérer les informations de connexion
    // 2. Charger le driver
    // 3. Retourner un objet DAOFactory (le contructeur)
    // L'objectif principal est d'instancier la classe DAOFactory
    // il s'agit là d'une méthode statique pour pouvoir l'appeler avant
    // d'instancier l'objet DAOFactory
    // Car c'est son rôle:)
    // pourquoi instancier avec une méthode et pas juste un new ... cad un
    // constructeur classique
    // car nous voulons instancier notre DAOFactory uniquement sous certaines
    // conditions :
    // a) si le fichier dao.properties est accessible;
    // b) si les données qu'il contient sont valides;
    // c) si le driver JDBC est bien présent dans l'application.
    //

    public static DAOFactory getInstance() throws DAOConfigurationException {

        // nous initialisons un objet Properties qui, comme son nom l'indique,
        // va nous permettre de gérer notre fichier de configuration
        Properties properties = new Properties();
        // on déclare des variables
        String url;
        String nomutilisateur;
        String motdepasse;
        String driver;

        // nous procédons à l'ouverture du fichier dao.properties grâce à la
        // méthode getResourceAsStream() de l'objet ClassLoader
        // Pourquoi ne pas avoir utilisé un traditionnel FileInputStream
        // pour ouvrir notre fichier properties ?
        // on utilise ici une méthode plus sophistiquée à première vue mais qui
        // en réalité est très simple :
        // elle consiste à appeler la méthode getResourceAsStream() de l'objet
        // ClassLoader,
        // qui se charge pour nous d'ouvrir le flux demandé et de retourner null
        // en cas d'erreur
        // NB : nous récupérons le ClassLoader depuis le thread courant grâce à
        // la méthode getContextClassLoader()
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );

        // du coup si fichierProperties est null, cela signifie que le flux n'a
        // pas été ouvert
        // et on envoie une erreur une exception personnalisée
        // DAOConfigurationException
        // stipulant que le fichier a pas été trouvé
        if ( fichierProperties == null ) {
            throw new DAOConfigurationException( "Le fichier properties est introuvable " + FICHIER_PROPERTIES );
        }

        // On va charger les propriétés contenues dans le fichier
        try {
            properties.load( fichierProperties );
            url = properties.getProperty( PROPERTY_URL );
            nomutilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
            motdepasse = properties.getProperty( PROPERTY_MOT_DE_PASSE );
            driver = properties.getProperty( PROPERTY_DRIVER );
        } catch ( IOException e ) {
            /*
             * rappel du contructeur de DAOConfigurationException cause c'est
             * l'exception e qui a généré l'exception
             */
            // public DAOConfigurationException( String message, Throwable cause
            // ) { super( message, cause ); }

            throw new DAOConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
        }

        // on peut maintenant charger le driver JDBC dont le nom est précisé
        // dans le fichier dao.properties
        /*
         * c'est comme dans le main sauf 'encapsulation de l'exception envoyée
         * en cas d'erreur (driver introuvable) dans une exception personnalisée
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

        // en cas de succès des étapes précédente,on peut instancier le
        // DAOFactory
        System.out.println( "Info de connexion"
                + "url : " + url
                + "nomutilisateur :" + nomutilisateur
                + "motdepasse :" + motdepasse
                + "driver :" + driver );
        DAOFactory instance = new DAOFactory( url, nomutilisateur, motdepasse );
        return instance;
    }

    // ------------------getConnexion connecte à la BDD avec les variables setté
    // par le constructeur de la méthode plus
    // haut----------------------------------------------------------
    // Nous créons ensuite la méthode getConnection()
    // chargée de fournir une connexion à la base de données
    // comme dans le main
    /*
     * connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
     * }catch(SQLException e)
     */

    // les valeur sont settés grace au constructeur dans le main
    //
    public Connection getConnexion() throws SQLException {
        return DriverManager.getConnection( url, nomutilisateur, motdepasse );
    }

    // -------------------getter décrit plus bas ----------
    // nous devons écrire les getters retournant les différentes implémentations
    // de DAO
    // on va le faire après

    // cette méthode va instancier un UtilisateurDaoImpl (qui a le code des
    // méthodes d'accès
    // à la bdd
    //
    public UtilisateurDao getUtilisateurDao() {
        return new UtilisateurDaoImpl( this );
    }

}
