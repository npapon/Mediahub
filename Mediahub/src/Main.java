import java.util.List;

import beans.AdministrationImages;
import beans.Image;
import constantes.Images;
import constantes.Repertoires;

public class Main {

    public static void main(
            String[] args ) {

        AdministrationImages administrationimages = new AdministrationImages();
        List<Image> imagesProfil = administrationimages.retournerImages( Images.CONSTANTE_TYPE_PROFIL,
                Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_IMAGESPROFIL, Repertoires.CONSTANTE_REPERTOIRE_RELATIF_IMAGESPROFIL );
        System.out.println( imagesProfil.get( 1 ).getLibelle() );
        /*
         * imagesProfil.get( 0 ); File dossierImagesProfil = new File(
         * "WebContent/img/profil" );
         * 
         * C:\Users\npapo\git\Mediahub\Mediahub\WebContent\img\profil
         * 
         * for ( File file : dossierImagesProfil.listFiles() ) {
         * System.out.println( file.getName() ); } /* File fichier = new File(
         * "dossier" ); fichier.mkdir(); System.out.println(
         * fichier.getAbsolutePath() ); System.out.println( fichier.exists() );
         * // true
         * 
         * System.out.println( fichier.exists() ); // on va afficher les
         * fichiers et dossier à la racine des lecteurs // réseau (ici le C)
         * 
         * // retourne la liste des éléments racine du système de fichier sur //
         * lequel on se trouve [C:\, D:\, E:\, F:\, G:\] for ( File file :
         * fichier.listRoots() ) { System.out.println( file.getAbsolutePath() );
         * // sur mon PC que C:\ // listFiles retourne les fichier et les
         * répertoires // ici qu'un seul file : c'est C:\ int i = 1; try { for (
         * File nom : file.listFiles() ) { // on rajoute un / en plus du nom
         * quand c'est un répertoire // \ c'est une tabulaton System.out.print(
         * "\t\t" + ( nom.isDirectory() ? nom.getName() + "/" : nom.getName() )
         * );
         * 
         * if ( ( i % 4 ) == 0 ) { System.out.print( "\n" ); } i++;
         * 
         * } } catch ( NullPointerException e ) { }
         * 
         * }
         */

    }
}