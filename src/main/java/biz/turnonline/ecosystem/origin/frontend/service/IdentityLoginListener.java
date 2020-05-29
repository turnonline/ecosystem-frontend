package biz.turnonline.ecosystem.origin.frontend.service;

import com.google.firebase.auth.FirebaseToken;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import org.reactivestreams.Publisher;

import javax.annotation.Nonnull;

/**
 * The listener to handle notification once an identity toolkit user has logged in.
 * <pre>
 *   &#64;Inject
 *   IdentityHandler handler;
 *
 *   public void processIdentity( HttpServletRequest request,
 *                                HttpServletResponse response,
 *                                FirebaseToken identity,
 *                                String sessionAttribute )
 *   {
 *        String signedEmail = identity.getEmail();
 *        // the verified Firebase ID token (available only for this request)
 *        String token = handler.getToken( request );
 *   }
 * </pre>FilterHttpFilter
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public interface IdentityLoginListener
{
    /**
     * Gets notification about right logged in authenticated user.
     * The implementation is responsible to put target authenticated user in to session at given session attribute.
     *
     * @param request   the current HTTP request
     * @param identity  the Firebase token of the logged in user
     * @param attribute the cookie attribute where parent filter will check authenticated user presence
     */
    Publisher<MutableHttpResponse<?>> processIdentity( @Nonnull HttpRequest<?> request,
                                                       @Nonnull Publisher<MutableHttpResponse<?>> publisher,
                                                       @Nonnull FirebaseToken identity,
                                                       @Nonnull String attribute );
}
