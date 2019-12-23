package beans;

import java.util.HashMap;
import java.util.Map;

import scripts.Script;

public class Utilisateur {

    private String              login;
    private String              motDePasse;
    private String              dateCreationCompte;
    private String              dateDeConnexion;
    private String              email;
    private Map<String, Script> listeScripts = new HashMap<String, Script>();

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

    public String getDateCreationCompte() {
        return dateCreationCompte;
    }

    public void setDateCreationCompte( String dateCreationCompte ) {
        this.dateCreationCompte = dateCreationCompte;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public Map<String, Script> getListeScripts() {
        return listeScripts;
    }

    public void setListeScripts( Map<String, Script> listeScripts ) {
        this.listeScripts = listeScripts;
    }

}
