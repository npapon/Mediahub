package beans;

import java.nio.file.Path;

import javax.servlet.http.Part;

public class Fichier {

    private String nom;
    private String description;
    private String chemin;
    private Path   path;
    private String type;
    private String contenu;
    private Part   parametresFichier;
    private String dossierRangement;

    private Path   pathNomFichier;

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

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu( String contenu ) {
        this.contenu = contenu;
    }

    public Path getPath() {
        return path;
    }

    public void setPath( Path path ) {
        this.path = path;
    }

    public Path getPathNomFichier() {
        return pathNomFichier;
    }

    public void setPathNomFichier( Path pathNomFichier ) {
        this.pathNomFichier = pathNomFichier;
    }

    public String getDossierRangement() {
        return dossierRangement;
    }

    public void setDossierRangement( String dossierRangement ) {
        this.dossierRangement = dossierRangement;
    }

}
