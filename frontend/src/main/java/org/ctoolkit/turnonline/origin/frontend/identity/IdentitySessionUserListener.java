package org.ctoolkit.turnonline.origin.frontend.identity;

import biz.turnonline.ecosystem.account.client.model.Account;
import com.google.firebase.auth.FirebaseToken;
import org.ctoolkit.services.identity.IdentityLoginListener;

import javax.annotation.Nonnull;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The listener implementation handling logged in identity user instance as
 * {@link Account} stored in the session.
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
                                 @Nonnull FirebaseToken identity,
                                 @Nonnull String sessionAttribute )
    {
        String signedEmail = identity.getEmail();

        Account account = new Account();
        account.setEmail( signedEmail );
        account.setCompany( false );

        request.getSession().setAttribute( sessionAttribute, account );
    }
}
