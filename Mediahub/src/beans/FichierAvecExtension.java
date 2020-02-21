package beans;

public class FichierAvecExtension extends Fichier {

    public void setNomAvecExtension( String nom, String extension ) {
        super.setNom( nom + "." + extension );

    }

}