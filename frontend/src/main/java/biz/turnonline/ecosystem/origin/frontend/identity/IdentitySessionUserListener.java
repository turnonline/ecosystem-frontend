package biz.turnonline.ecosystem.origin.frontend.identity;

import com.google.firebase.auth.FirebaseToken;
import org.ctoolkit.restapi.client.firebase.IdentityLoginListener;

import javax.annotation.Nonnull;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The listener implementation handling logged in identity user instance as
 * {@link AccountProfile} stored in the session.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
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
        AccountProfile profile = new AccountProfile( identity );
        request.getSession().setAttribute( sessionAttribute, profile );
    }
}
