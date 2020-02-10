import java.nio.ByteBuffer;

public class Main2 {

    public static void main( String[] args ) {
        // TODO Auto-generated method stub

        // buffer à 6
        ByteBuffer buffermanuel = ByteBuffer.allocate( 6 );

        System.out
                .println( "1. Limite du buffer : " + buffermanuel.limit() + "\nPosition du buffer : " + buffermanuel.position() );

        // 6 et 0
        buffermanuel.putChar( 'A' );

        readBuffer( buffermanuel );

        System.out
                .println( "2. Limite du buffer : " + buffermanuel.limit() + "\nPosition du buffer : " + buffermanuel.position() );

        // 6 et 2

        buffermanuel.putChar( 'B' );

        readBuffer( buffermanuel );

        System.out
                .println( "3. Limite du buffer : " + buffermanuel.limit() + "\nPosition du buffer : " + buffermanuel.position() );

        // 6 et 4
        buffermanuel.flip();

        System.out
                .println( "4. Limite du buffer : " + buffermanuel.limit() + "\nPosition du buffer : " + buffermanuel.position() );

        // 4 et 0

        while ( buffermanuel.hasRemaining() ) {
            System.out.print( (char) buffermanuel.get() );
        }

        buffermanuel.clear();

        System.out
                .println( "5. Limite du buffer : " + buffermanuel.limit() + "\nPosition du buffer : " + buffermanuel.position() );
    }

    public static void readBuffer( ByteBuffer bytebuffer ) {

        byte[] tableauByteDuBuffeur = bytebuffer.array();
        System.out.print( "Contenu du buffeur : " );
        for ( byte bite : tableauByteDuBuffeur ) {

            System.out.print( (char) bite );
        }
        System.out.print( "\n" );
    }

}
