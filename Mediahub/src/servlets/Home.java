package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constantes.Vues;

@WebServlet( "/Home" )
public class Home extends HttpServlet {

    private String vue = Vues.CONSTANTE_VUE_HOME;

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( vue ).forward( request,
                response );
    }

}
