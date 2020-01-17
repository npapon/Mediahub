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
        menuediteur.put( Vues.CONSTANTE_VUE_CREERSCRIPT_DESCRIPTION, Vues.CONSTANTE_VUE_CREERSCRIPT_NOMCOURT );
        menuediteur.put( Vues.CONSTANTE_VUE_LISTESCRIPTS_DESCRIPTION, Vues.CONSTANTE_VUE_LISTESCRIPTS_NOMCOURT );
        menuediteur.put( Vues.CONSTANTE_VUE_ENVOIFICHIERS_DESCRIPTION, Vues.CONSTANTE_VUE_ENVOIFICHIERS_NOMCOURT );
        menuediteur.put( Vues.CONSTANTE_VUE_LECTUREFICHIER_DESCRIPTION, Vues.CONSTANTE_VUE_LECTUREFICHIER_NOMCOURT );
        menuediteur.put( Vues.CONSTANTE_VUE_ECRITUREFICHIER_DESCRIPTION, Vues.CONSTANTE_VUE_ECRITUREFICHIER_NOMCOURT );
        menuediteur.put( Vues.CONSTANTE_VUE_ECRIREGENERERFICHIERTEXT_DESCRIPTION,
                Vues.CONSTANTE_VUE_ECRIREGENERERFICHIERTEXT_NOMCOURT );
        return menuediteur;

    }

}
