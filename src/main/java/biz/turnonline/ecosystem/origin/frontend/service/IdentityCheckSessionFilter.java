package biz.turnonline.ecosystem.origin.frontend.service;

import biz.turnonline.ecosystem.origin.frontend.model.FirebaseConfig;
import com.google.common.base.Strings;
import com.google.firebase.auth.FirebaseToken;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:pohorelec@turnonline.biz">Jozef Pohorelec</a>
 */
@Filter( "/**" )
public class IdentityCheckSessionFilter
        implements HttpServerFilter
{
    public static final String COOKIE_ACCOUNT_ATTRIBUTE = "account";

    private String signUpPath = "/sign-up";

    private Set<String> ignorePaths = new HashSet<>();

    private final IdentityHandler identityHandler;

    private final FirebaseConfig firebaseConfig;

    private final Set<IdentityLoginListener> listeners;

    public IdentityCheckSessionFilter( IdentityHandler identityHandler,
                                       FirebaseConfig firebaseConfig,
                                       Set<IdentityLoginListener> listeners )
    {
        ignorePaths.add( "/favicon.ico" );

        this.identityHandler = identityHandler;
        this.firebaseConfig = firebaseConfig;
        this.listeners = listeners;
    }

    @Override
    public Publisher<MutableHttpResponse<?>> doFilter( HttpRequest<?> request, ServerFilterChain chain )
    {
        if ( ignoreServletPath( request ) )
        {
            return chain.proceed( request );
        }

        if ( loggedIn( request ) )
        {
            if ( request.getPath().startsWith( signUpPath ) )
            {
                // if user is logged in redirect him for sign-in success url
                return Flowable.fromCallable( () -> HttpResponse.redirect( URI.create( firebaseConfig.getSignInSuccessUrl() ) ) );
            }
        }
        else
        {
            FirebaseToken identity = identityHandler.resolveVerifyToken( request );
            String signedEmail = identity != null ? identity.getEmail() : null;

            if ( signedEmail != null )
            {
                Publisher<MutableHttpResponse<?>> responsePublisher = chain.proceed( request );

                // the user is logged in but authenticated session has not been created yet
                for ( IdentityLoginListener listener : listeners )
                {
                    responsePublisher = listener.processIdentity( request, responsePublisher, identity, COOKIE_ACCOUNT_ATTRIBUTE );
                }

                // identity cookie is not needed now
                return identityHandler.delete( request, responsePublisher );
            }
        }

        return chain.proceed( request );
    }

    private boolean loggedIn( HttpRequest<?> request )
    {
        return request.getCookies().findCookie( COOKIE_ACCOUNT_ATTRIBUTE ).orElse( null ) != null;
    }

    /**
     * Checks whether given request has servlet path to be ignored.
     *
     * @param request the current http request
     * @return true if the request should be ignored, otherwise false
     */
    private boolean ignoreServletPath( final HttpRequest<?> request )
    {
        if ( !ignorePaths.isEmpty() )
        {
            String servletPath = request.getPath();
            if ( !Strings.isNullOrEmpty( servletPath ) )
            {
                for ( String toBeIgnored : ignorePaths )
                {
                    if ( servletPath.startsWith( toBeIgnored ) )
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
