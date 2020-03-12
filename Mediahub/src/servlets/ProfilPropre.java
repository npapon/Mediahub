package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import beans.ChargerImageProfil;
import beans.Fichier;
import constantes.Attributs;
import constantes.Repertoires;
import constantes.Vues;

@WebServlet( "/ProfilPropre" )
public class ProfilPropre extends HttpServlet {

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( Vues.CONSTANTE_VUE_PROFILPROPRE ).forward( request,
                response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        ChargerImageProfil chargerImageProfil = new ChargerImageProfil();
        Fichier imageProfil = chargerImageProfil.chargerImageProfil( request );
        Part fichierPhysiqueImageProfil = imageProfil.getFichierPhysique();
        try {
            chargerImageProfil.enregistrerImageProfil( fichierPhysiqueImageProfil,
                    Repertoires.CONSTANTE_REPERTOIRE_ABSOLU_IMAGESPROFIL );
        } catch ( Exception e ) {
            System.out.println( e.getMessage() );

        }

        String nomFichierImageProfil = chargerImageProfil.recupererNomFichierImage( fichierPhysiqueImageProfil );
        imageProfil.setNom( nomFichierImageProfil );
        imageProfil.setChemin( Repertoires.CONSTANTE_REPERTOIRE_RELATIF_IMAGESPROFIL + "/" + nomFichierImageProfil );

        HttpSession session = request.getSession();

        session.setAttribute( Attributs.CONSTANTE_ATTRIBUT_IMAGEPROFIL, imageProfil );
        response.sendRedirect( Vues.CONSTANTE_VUE_PROFILPROPRE_NOMCOURT );

    }

}
