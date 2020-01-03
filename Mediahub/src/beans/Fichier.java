package beans;

import javax.servlet.http.Part;

public class Fichier {

    private String nom;
    private String description;
    private String chemin;
    private Part   parametresFichier;

    public String getNom() {
        return nom;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin( String chemin ) {
        this.chemin = chemin;
    }

    public Part getParametresFichier() {
        return parametresFichier;
    }

    public void setParametresFichier( Part parametresFichier ) {
        this.parametresFichier = parametresFichier;
    }

}
