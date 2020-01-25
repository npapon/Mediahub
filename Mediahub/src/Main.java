import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Main {

    public static void main(
            String[] args ) {

        String text = "gay et toi";

        FileOutputStream fileOutPutStream;
        FileChannel fileChannel;
        ByteBuffer byteBuffer;
        byte[] bites = text.getBytes();

        try {
            fileOutPutStream = new FileOutputStream( "vege.txt" );
            fileChannel = fileOutPutStream.getChannel();

            byteBuffer = ByteBuffer.allocateDirect( bites.length );

            byteBuffer = ByteBuffer.wrap( bites );
            fileChannel.write( byteBuffer );

            fileChannel.close();
            fileOutPutStream.close();

        } catch ( IOException e ) {
            e.printStackTrace();
        }

    }

}
