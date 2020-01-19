import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(
            String[] args ) {

        File file = new File( "fichierecriavecfilewriter.txt" );
        FileWriter fileWriter = null;
        FileReader fileReader = null;

        String text = "nicolas et leslie";
        text += "\n Valentine";

        try {
            fileWriter = new FileWriter( file );
            fileWriter.write( text );
            fileWriter.close();
            fileReader = new FileReader( file );
            int i = 0;
            String str = "";
            while ( ( i = fileReader.read() ) != -1 ) {
                str += (char) i;
            }
            System.out.println( str );

        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        finally {
            try {
                fileWriter.close();
            } catch ( IOException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                fileReader.close();
            } catch ( IOException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }
}