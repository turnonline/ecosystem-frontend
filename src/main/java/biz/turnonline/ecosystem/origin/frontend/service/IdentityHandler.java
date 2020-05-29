package biz.turnonline.ecosystem.origin.frontend.service;

import com.google.common.base.Strings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.cookie.Cookie;
import io.micronaut.http.cookie.Cookies;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.ExecutionException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Google Firebase token verification wrapper with convenient methods.
 *
 * @author <a href="mailto:pohorelec@turnonline.biz">Jozef Pohorelec</a>
 */
@Singleton
public final class IdentityHandler
{
    /**
     * Default cookie name of the Firebase token.
     */
    public static final String FTOKEN = "ftoken";

    private static final Logger logger = LoggerFactory.getLogger( IdentityHandler.class );

    private final FirebaseAuth firebase;

    @Inject
    IdentityHandler( FirebaseAuth firebase )
    {
        this.firebase = firebase;
    }

    /**
     * Verifies firebase token taken from the request.
     * Once verification is successful returns populated identity instance.
     * If verification fails or token has expired returns <code>null</code>.
     *
     * @param request the HTTP request
     * @return the successfully verified firebase token instance or null
     */
    public FirebaseToken resolveVerifyToken( @Nonnull HttpRequest<?> request )
    {
        String token = getToken( checkNotNull( request ) );
        FirebaseToken decodedToken = null;

        if ( !Strings.isNullOrEmpty( token ) )
        {
            try
            {
                decodedToken = firebase.verifyIdTokenAsync( token ).get();
            }
            catch ( InterruptedException | ExecutionException e )
            {
                logger.error( "Token verification has failed.", e );
            }
        }

        return decodedToken;
    }

    /**
     * Returns the firebase token from the request. Searched either in headers (first) or cookies.
     * If not found returns <code>null</code>.
     *
     * @param request the HTTP request
     * @return the firebase token
     */
    public final String getToken( @Nonnull HttpRequest<?> request )
    {
        return request
                .getHeaders()
                .findFirst( FTOKEN )
                .orElseGet( () -> {
                    Cookie cookie = request.getCookies()
                            .findCookie( FTOKEN )
                            .orElse( null );

                    if ( cookie != null )
                    {
                        return cookie.getValue();
                    }

                    return null;
                } );
    }

    /**
     * Delete firebase token cookie.
     *
     * @param request   the HTTP servlet request
     * @param publisher the HTTP servlet response publisher
     */
    public Publisher<MutableHttpResponse<?>> delete( @Nonnull HttpRequest<?> request, @Nonnull Publisher<MutableHttpResponse<?>> publisher )
    {
        return Flowable.fromPublisher( publisher ).doOnNext( response -> {
            Cookies cookies = request.getCookies();
            Cookie cookie = cookies.findCookie( FTOKEN ).orElse( null );

            if ( cookie != null )
            {
                //the zero value causes the cookie to be deleted
                cookie.maxAge( 0 );
                cookie.value( "" );
                cookie.path( "/" );

                response.cookie( cookie );
            }
        } );
    }
}
