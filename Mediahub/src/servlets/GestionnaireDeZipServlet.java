package servlets;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Fichier;
import beans.FichierAvecExtension;
import beans.GererZip;
import beans.ListFichiersRepertoire;
import constantes.Attributs;
import constantes.Extensions;
import constantes.Parametres;
import constantes.Repertoires;
import constantes.Vues;

@WebServlet( "/GestionnaireDeZipServlet" )
public class GestionnaireDeZipServlet extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        ListFichiersRepertoire listFichiersRepertoire = new ListFichiersRepertoire();
        List<Fichier> listFichiersTxt = listFichiersRepertoire
                .retournerFichiers( Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_FICHIERSZIP,
                        Extensions.CONSTANTE_EXTENSION_FICHIERSTXT );

        String emplacementFichiersTxt = Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_FICHIERSTEXT;
        HttpSession session = request.getSession();

        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_FICHIERSTEXT, listFichiersTxt );
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_DOSSIERRANGEMENTFICHIERSTEXT, emplacementFichiersTxt );
        String vue = Vues.CONSTANTE_VUE_GESTIONNAIREDEZIP_NOMCOURT;

        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_GERERZIPSVUE, vue );
        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_GESTIONNAIREDEZIP ).forward( request,
                response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        GererZip gererZip = new GererZip();
        FichierAvecExtension fichierZip = gererZip.creerZip( request,
                Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_FICHIERSZIP,
                Parametres.CONSTANTE_PARAMETRE_CREERZIP );

        FileSystem fileSystemDuZip = gererZip.definirFileSystemZip( request,
                Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_FICHIERSZIP,
                Parametres.CONSTANTE_PARAMETRE_CREERZIP );

        gererZip.deplacerLesFichiersDansLeZip( request, Parametres.CONSTANTE_PARAMETRE_ELEMENTS_TABLEAU_COCHES,
                Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_FICHIERSZIP, fileSystemDuZip );

        fileSystemDuZip.close();

        List<String> erreurs = gererZip.getErreurs();

        HttpSession session = request.getSession();
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_FICHIERZIPCREE, fichierZip );
        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_LISTE_ERREURS_CREATION_ZIP, erreurs );
        response.sendRedirect( Vues.CONSTANTE_VUE_FICHIERZIPCREE_NOMCOURT );

    }

}
