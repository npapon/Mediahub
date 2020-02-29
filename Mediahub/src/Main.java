import servlets.ProfilPropre;

public class Main {
    public static void main( String[] args ) {

        ProfilPropre profilpropre = new ProfilPropre();

        String test = "filename=\"C:\\Users\\npapo\\git\\Mediahub\\Mediahub\\WebContent\\img\\profil\\bulma.jpg\"";
        System.out.println( test.indexOf( "C" ) );
        System.out.println( test.substring( test.lastIndexOf( "\\" + 1 ) ) );

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
