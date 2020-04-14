package beans;

import java.sql.Timestamp;

public class UtilisateurV2 {

    // id int(11)

    /*
     * nous aurions pu a priori nous contenter d'un type primitif long.
     * Seulement dans une base de données les valeurs peuvent être initialisées
     * à NULL, alors qu'un type primitif en Java ne peut pas valoir null
     */
    private Long      id;
    // login varchar(32)
    private String    login;
    // email varchar(60)
    private String    email;
    // mot_de_passe char(56)
    private String    motDePasse;
    // nom varchar(20)
    private String    nom;
    // date_creation datetime
    private Timestamp dateCreation;

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin( String login ) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse( String motDePasse ) {
        this.motDePasse = motDePasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation( Timestamp dateCreation ) {
        this.dateCreation = dateCreation;
    }

    /*
     * Le timestamp (unix) désigne le nombre de secondes depuis le 1er janvier
     * 1970 à minuit UTC précise
     */

}
