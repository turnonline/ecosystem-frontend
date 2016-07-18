package org.ctoolkit.turnonline.frontend;

import com.google.inject.servlet.ServletModule;
import org.ctoolkit.services.identity.IdentityToolkitCheckSessionFilter;
import org.ctoolkit.services.identity.IdentityToolkitLoginTroubleHandler;
import org.ctoolkit.turnonline.frontend.identity.Logout;

import java.util.HashMap;
import java.util.Map;

/**
 * The frontend servlet module to configure Guice to serve requests at custom defined servlet path.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class FrontendServletModule
        extends ServletModule
{
    @Override
    protected void configureServlets()
    {
        String ignorePaths = "/styles,/scripts,/images,/favicon.ico,/appstats,/wicket/resource";

        Map<String, String> params = new HashMap<>();
        params.put( IdentityToolkitCheckSessionFilter.LOGIN_PATH, FrontendApplication.LOGIN );
        params.put( IdentityToolkitCheckSessionFilter.SIGN_UP_PATH, FrontendApplication.SIGNUP );
        params.put( IdentityToolkitCheckSessionFilter.SESSION_AUTH_USER_ATTRIBUTE, FrontendSession.AUTH_USER_ATTRIBUTE );
        params.put( IdentityToolkitCheckSessionFilter.REDIRECT_PATH, FrontendApplication.MY_ACCOUNT );
        params.put( IdentityToolkitCheckSessionFilter.IGNORE_PATHS, ignorePaths );

        filter( "/*" ).through( IdentityToolkitCheckSessionFilter.class, params );

        // configure logout path to the servlet to invalidate active session
        serve( FrontendApplication.LOGOUT ).with( Logout.class );
        serve( FrontendApplication.LOGIN_TROUBLE_HANDLER ).with( IdentityToolkitLoginTroubleHandler.class, params );
    }
}
