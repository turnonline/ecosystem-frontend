package org.ctoolkit.turnonline.origin.frontend.identity;

import org.ctoolkit.restapi.client.identity.Identity;
import org.ctoolkit.services.identity.IdentityLoginListener;
import org.ctoolkit.turnonline.shared.resource.Account;

import javax.annotation.Nonnull;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The listener implementation handling logged in identity user instance as
 * {@link AuthenticatedUser} stored in the session.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
@Singleton
public class IdentitySessionUserListener
        implements IdentityLoginListener
{
    @Override
    public void processIdentity( @Nonnull HttpServletRequest request,
                                 @Nonnull HttpServletResponse response,
                                 @Nonnull Identity identity,
                                 @Nonnull String sessionAttribute )
            throws IOException
    {
        String signedEmail = identity.getEmail();

        Account user = new Account();
        user.setEmail( signedEmail );
        user.setCompany( false );

        request.getSession().setAttribute( sessionAttribute, new AuthenticatedUser( user ) );
    }
}
