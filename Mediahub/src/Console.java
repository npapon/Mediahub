
public class Console {

    String name;

    public Console( String name ) {
        this.name = name;
    }

    // instancie un constructeur jeuxVideo avec la classe console ici en param
    public Jeu instancierJeuxVideo() {
        return new JeuxVideo( this );
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

}
