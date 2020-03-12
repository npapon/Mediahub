public class Main {
    public static void main( String[] args ) {
        Main main = new Main();
        int i = 17;
        try {
            i = main.plusUn( i );
        } catch ( Exception e ) {
            System.out.println( e.getMessage() );
        }

        System.out.print( i );
        ;

    }

    public int plusUn( int i ) throws Exception {
        if ( i > 2 ) {

            throw new Exception( "merde" );
        }

        i = i + 1;

        return i;
    }

}
