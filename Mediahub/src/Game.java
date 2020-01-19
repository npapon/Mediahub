import java.io.Serializable;

public class Game implements Serializable {

    private String           name;
    private Integer          prix;
    private transient Notice notice;

    public Game( String name, int prix ) {
        this.name = name;
        this.prix = prix;
        this.notice = new Notice();
    }

    public String toString() {
        return "Ce jeu s'appelle " + this.name + "et coûte " + this.prix;

    }
}