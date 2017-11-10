package org.ctoolkit.turnonline.origin.frontend.identity;

import org.ctoolkit.services.identity.IdentityHandler;
import org.ctoolkit.turnonline.origin.frontend.FrontendApplication;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Single purpose servlet to invalidate session and redirect to the login page.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
@Singleton
public class Logout
        extends HttpServlet
{
    private static final long serialVersionUID = -957674638895395920L;

    private final IdentityHandler resolver;

    @Inject
    Logout( IdentityHandler resolver )
    {
        this.resolver = resolver;
    }

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        resolver.delete( request, response );
        request.getSession().invalidate();

        // mode select is being required by identity toolkit widget as configuration parameter
        response.sendRedirect( FrontendApplication.LOGIN );
    }
}
