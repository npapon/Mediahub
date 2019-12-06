package beans;

import java.util.HashMap;
import java.util.Map;

import constantes.Vues;

public class MenuEditeur {

    public Map<String, String> initierMenu() {
        Map<String, String> menuediteur = new HashMap<String, String>();
        menuediteur.put( Vues.CONSTANTE_VUE_HOME_DESCRIPTION, Vues.CONSTANTE_VUE_HOME_NOMCOURT );
        menuediteur.put( Vues.CONSTANTE_VUE_CONNEXION_DESCRIPTION, Vues.CONSTANTE_VUE_CONNEXION_NOMCOURT );
        menuediteur.put( Vues.CONSTANTE_VUE_PROFIL_DESCRIPTION, Vues.CONSTANTE_VUE_PROFIL_NOMCOURT );
        menuediteur.put( Vues.CONSTANTE_VUE_INSCRIPTION_DESCRIPTION, Vues.CONSTANTE_VUE_INSCRIPTION_NOMCOURT );
        menuediteur.put( Vues.CONSTANTE_VUE_ADMINISTRATION_DESCRIPTION, Vues.CONSTANTE_VUE_ADMINISTRATION_NOMCOURT );
        return menuediteur;

    }

}
