public class Main {
    public static void main( String[] args ) {

        String enteteRequetePostContentDisposition = " Content-Disposition: form-data;name=\"fich;ier\"; filename=\"nom_du_fichier.ext\"";
        String nomFichierImage;
        for ( String coupleAttributValeur : enteteRequetePostContentDisposition.split( ";" ) ) {
            if ( coupleAttributValeur.trim().startsWith( "filename" ) ) {
                nomFichierImage = coupleAttributValeur.substring( coupleAttributValeur.indexOf( "\"" ),
                        coupleAttributValeur.lastIndexOf( "\"" ) );
                System.out.println( "Nom du fichier de l'image " + nomFichierImage );

            }
        }

    }
}
