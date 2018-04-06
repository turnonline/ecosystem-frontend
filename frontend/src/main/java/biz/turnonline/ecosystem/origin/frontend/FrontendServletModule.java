package biz.turnonline.ecosystem.origin.frontend;

import biz.turnonline.ecosystem.origin.frontend.identity.Logout;
import com.google.inject.servlet.ServletModule;
import org.ctoolkit.services.identity.IdentityCheckSessionFilter;

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
        params.put( IdentityCheckSessionFilter.LOGIN_PATH, FrontendApplication.LOGIN );
        params.put( IdentityCheckSessionFilter.SIGN_UP_PATH, FrontendApplication.SIGNUP );
        params.put( IdentityCheckSessionFilter.SESSION_AUTH_USER_ATTRIBUTE, FrontendSession.AUTH_USER_ATTRIBUTE );
        params.put( IdentityCheckSessionFilter.REDIRECT_PATH, FrontendApplication.MY_ACCOUNT );
        params.put( IdentityCheckSessionFilter.IGNORE_PATHS, ignorePaths );

        filter( "/*" ).through( IdentityCheckSessionFilter.class, params );

        // configure logout path to the servlet to invalidate active session
        serve( FrontendApplication.LOGOUT ).with( Logout.class );
    }
}
