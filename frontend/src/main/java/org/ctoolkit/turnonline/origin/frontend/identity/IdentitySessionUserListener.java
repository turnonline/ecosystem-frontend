package org.ctoolkit.turnonline.origin.frontend.identity;

import biz.turnonline.api.turnonline.Turnonline;
import com.google.firebase.auth.FirebaseToken;
import org.ctoolkit.restapi.client.NotFoundException;
import org.ctoolkit.restapi.client.PayloadRequest;
import org.ctoolkit.restapi.client.RestFacade;
import org.ctoolkit.restapi.client.UnauthorizedException;
import org.ctoolkit.services.identity.IdentityLoginListener;
import org.ctoolkit.turnonline.origin.frontend.FrontendApplication;
import org.ctoolkit.turnonline.shared.resource.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The listener implementation handling logged in identity user instance as
 * {@link AuthenticatedUser} stored in the session.
 * In case that no user has found for authenticated email, user account will be created (sign up).
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
@Singleton
public class IdentitySessionUserListener
        implements IdentityLoginListener
{
    private static final Logger logger = LoggerFactory.getLogger( IdentitySessionUserListener.class );

    private final RestFacade resources;

    @Inject
    public IdentitySessionUserListener( RestFacade resources )
    {
        this.resources = resources;
    }

    @Override
    public void processIdentity( @Nonnull HttpServletRequest request,
                                 @Nonnull HttpServletResponse response,
                                 @Nonnull FirebaseToken identity,
                                 @Nonnull String sessionAttribute )
    {
        User authUser;
        String signedEmail = identity.getEmail();

        try
        {
            User user = resources.get( User.class ).identifiedBy( signedEmail ).finish();

            authUser = new AuthenticatedUser( user );
            request.getSession().setAttribute( sessionAttribute, authUser );
        }
        catch ( NotFoundException e )
        {
            // the user is verified against identity service but not created in backend yet
            User user = new User();
            user.setEmail( signedEmail );

            PayloadRequest<User> query = resources.insert( user );

            Turnonline.Account.Insert insert = query.underlying( Turnonline.Account.Insert.class );
            insert.setDisplayName( identity.getName() );
            insert.setProviderId( identity.getIssuer() );

            user = query.finish();
            user = new AuthenticatedUser( user );
            request.getSession().setAttribute( sessionAttribute, user );
            response.sendRedirect( FrontendApplication.MY_ACCOUNT );
        }
        catch ( UnauthorizedException e )
        {
            logger.error( "Call to REST API is unauthorized! Email " + signedEmail, e );
        }
        catch ( Exception e )
        {
            logger.error( "Unknown error has occurred during user login", e );
        }
    }
}
