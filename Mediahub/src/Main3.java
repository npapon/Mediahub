public class Main3 {

    public static void main( String[] args ) {
        // TODO Auto-generated method stub

        Console PS4 = new Console( "PS4" );

        Jeu ffVIIREMAKE = PS4.instancierJeuxVideo();

        String[] players = { "nico", "leslie" };

        ffVIIREMAKE.printPlayers( players );

        ffVIIREMAKE.printPlayersVarargs( "nico", "leslie", "chenu" );

    }

}
