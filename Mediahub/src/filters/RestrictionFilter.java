package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constantes.Attributs;
import constantes.Vues;

public class RestrictionFilter implements Filter {

    public void init( FilterConfig config ) throws ServletException {

    }

    public void doFilter( ServletRequest req, ServletResponse resp, FilterChain chain ) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();

        if ( session.getAttribute( Attributs.CONSTANTE_ATTRIBUT_SESSION ) == null ) {
            response.sendRedirect( Vues.CONSTANTE_VUE_HOME_NOMCOURT );
        }

        else {
            chain.doFilter( req, resp );
        }
    }

    public void destroy() {

    }

}
