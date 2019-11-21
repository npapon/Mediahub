package beans;

public class Utilisateur {

    private String login;
    private String motDePasse;
    private String dateDeConnexion;

    public String getLogin() {
        return login;
    }

    public void setLogin( String login ) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse( String motDePasse ) {
        this.motDePasse = motDePasse;
    }

    public String getDateDeConnexion() {
        return dateDeConnexion;
    }

    public void setDateDeConnexion( String dateDeConnexion ) {
        this.dateDeConnexion = dateDeConnexion;
    }

}
