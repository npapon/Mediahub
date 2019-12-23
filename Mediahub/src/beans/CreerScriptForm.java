package beans;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import constantes.MessagesErreur;
import constantes.Parametres;
import scripts.Script;

public class CreerScriptForm {

    List<String> erreurs = new ArrayList<String>();

    public Script creerScript( HttpServletRequest request ) {

        Script script = new Script();
        String codescript = request.getParameter( Parametres.CONSTANTE_PARAMETRE_CODE_SCRIPT ).trim();
        verifierCode( codescript );
        script.setCode( codescript );
        String libellescript = request.getParameter( Parametres.CONSTANTE_PARAMETRE_LIBELLE_SCRIPT );
        verifierLibelle( libellescript );
        script.setLibelle( libellescript );

        return script;
    }

    public void verifierCode( String codescript ) {
        if ( codescript.length() != 3 ) {
            try {
                throw new Exception( MessagesErreur.CONSTANTE_ERREUR_CODE_SCRIPT );
            } catch ( Exception e ) {
                System.out.println( e.getMessage() );

                erreurs.add(
                        e.getMessage() );
            }
        }

    }

    public void verifierLibelle( String libellescript ) {
        if ( libellescript.length() <= 3 ) {
            try {
                throw new Exception( MessagesErreur.CONSTANTE_ERREUR_LIBELLE_SCRIPT );
            } catch ( Exception e ) {
                System.out.println( e.getMessage() );

                erreurs.add(
                        e.getMessage() );
            }
        }

    }

}
