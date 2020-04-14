
public class JeuxVideo implements Jeu {
    private String  name;
    private Integer prix;

    private Console console;

    public JeuxVideo( Console console ) {
        this.console = console;

    }

    public String lancer() {
        return "jeu est lancé par " +
                this.console.getName();

    }

    public void printPlayers( String[] players ) {
        for ( String player : players ) {
            System.out.println( player );
        }

        for ( int i = 0; i < players.length; i++ ) {
            System.out.println( players[i] );
        }
    }

    public void printPlayersVarargs( String... players ) {
        for ( String player : players ) {
            System.out.println( player );
        }

        for ( int i = 0; i < players.length; i++ ) {
            System.out.println( players[i] );
        }

    }

}
