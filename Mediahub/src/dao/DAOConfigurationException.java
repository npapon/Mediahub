package dao;

public class DAOConfigurationException extends RuntimeException

{

    public DAOConfigurationException( String message ) {
        super( message );
    }

    // cause c'est l'exception e qui a généré l'exception
    public DAOConfigurationException( String message, Throwable cause ) {
        super( message, cause );
    }

    public DAOConfigurationException( Throwable cause ) {
        super( cause );
    }

}
