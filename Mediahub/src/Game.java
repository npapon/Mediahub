import java.io.Serializable;

public class Game implements Serializable {

    private String           name;
    private Integer          prix;
    private transient Notice notice;

    private Console          console;

    public Game( Console console ) {
        this.console = console;

    }

    public String launchGame() {
        return "jeu est lancé par " +
                this.console.getName();

    }

    public Game( String name, int prix ) {
        this.name = name;
        this.prix = prix;
        this.notice = new Notice();
    }

    public Joueur getJoueur() {
        return new Joueur( this );
    }

    public void calculAgeAvecException( int age ) {
        if ( age < 30 ) {
            System.out.println( "t'es jeune" );
        } else {
            try {
                throw new Exception( "t'es vieux exception" );

            } catch ( Exception e ) {
                // TODO Auto-generated catch block
                System.out.println( e.getMessage() );
            }

        }

    }

    public void calculAgeAvecRuntimeException( int age ) {
        if ( age < 30 ) {
            System.out.println( "t'es jeune" );
        } else {
            throw new RuntimeException( "t'es vieux run time" );
        }

    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix( Integer prix ) {
        this.prix = prix;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice( Notice notice ) {
        this.notice = notice;
    }

}