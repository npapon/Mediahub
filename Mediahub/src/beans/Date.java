package beans;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import constantes.FormatsDates;

public class Date {

    public String dateNowFormatString() {
        // calcul date du jour au format DateTime
        DateTime dateNow = new DateTime();
        // on créé un format de date
        DateTimeFormatter formatter = DateTimeFormat.forPattern( FormatsDates.CONSTANTE_FORMATDATE_DDMMYYYY_HHMMSS );
        // on convertit date du jour au format DateTime au format String
        String dateNowString = dateNow.toString( formatter );
        return dateNowString;
    }

    // méthode qui transforme une date au format string au format DateTime
    public DateTime DateStringFormatDateTime( String dateFormatString ) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern( FormatsDates.CONSTANTE_FORMATDATE_DDMMYYYY_HHMMSS );
        // au cas ou la date est pas renseignée
        if ( dateFormatString != null ) {
            return formatter.parseDateTime( dateFormatString );
        } else
            return null;
    }

    // calcule l'écart entre 2 dates et l'affiche dans un format verbeux
    public String ecartDateFormatVerbeux( DateTime dateTime1, DateTime dateTime2 ) {

        Period period = new Period( dateTime1, dateTime2 );
        PeriodFormatter periodFormatter = new PeriodFormatterBuilder()
                .appendYears().appendSuffix( " an ", " ans " )
                .appendMonths().appendSuffix( " mois " )
                .appendDays().appendSuffix( " jour ", " jours " )
                .appendHours().appendSuffix( " heure ", " heures " )
                .appendMinutes().appendSuffix( " minute ", " minutes " )
                .appendSeparator( "et " )
                .appendSeconds().appendSuffix( " seconde", " secondes" )
                .toFormatter();
        String ecartDateFormatVerbeux = periodFormatter.print( period );

        return ecartDateFormatVerbeux;

    }

}
