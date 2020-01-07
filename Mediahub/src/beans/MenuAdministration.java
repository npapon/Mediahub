package beans;

import java.util.HashMap;
import java.util.Map;

import constantes.Vues;

public class MenuAdministration {

    public Map<String, String> initierMenu() {
        Map<String, String> menuadministration = new HashMap<String, String>();
        menuadministration.put( Vues.CONSTANTE_VUE_GERERFICHIERS_DESCRIPTION, Vues.CONSTANTE_VUE_GERERFICHIERS_NOMCOURT );
        return menuadministration;

    }

}
