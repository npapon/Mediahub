import org.joda.time.DateTime;

import beans.Date;

public class Main {

    public static void main(
            String[] args ) {
        DateTime dateTime = new DateTime();

        DateTime dateTime2 = new DateTime();
        DateTime datefixedatetime = new DateTime( 2019, 01, 01, 1, 1, 10 );

        Date date = new Date();
        System.out.println( date.ecartDateFormatVerbeux( datefixedatetime, dateTime2 ) );

    }
}