package biz.turnonline.ecosystem.origin.frontend.service;

import biz.turnonline.ecosystem.origin.frontend.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseToken;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.netty.cookies.NettyCookie;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import static biz.turnonline.ecosystem.origin.frontend.service.IdentityCheckSessionFilter.COOKIE_ACCOUNT_ATTRIBUTE;

/**
 * The listener implementation handling logged in identity user instance as
 * {@link Account} stored in the 'account' cookie.
 * In case that no user has found for authenticated email, user account will be created (sign up).
 *
 * @author <a href="mailto:pohorelec@turnonline.biz">Jozef Pohorelec</a>
 */
@Singleton
public class IdentityCookieAccountListener
        implements IdentityLoginListener
{
    private static final Logger logger = LoggerFactory.getLogger( IdentityCookieAccountListener.class );

    private final IdentityHandler handler;

    private final RxHttpClient accountStewardClient;

    private final ObjectMapper mapper;

    @Inject
    public IdentityCookieAccountListener( IdentityHandler handler,
                                          @Client( "${gwt.account-steward-api-root}" ) RxHttpClient accountStewardClient,
                                          ObjectMapper mapper )
    {
        this.handler = handler;
        this.accountStewardClient = accountStewardClient;
        this.mapper = mapper;
    }

    @Override
    public Publisher<MutableHttpResponse<?>> processIdentity( @Nonnull HttpRequest<?> request,
                                                              @Nonnull Publisher<MutableHttpResponse<?>> responsePublisher,
                                                              @Nonnull FirebaseToken identity,
                                                              @Nonnull String attribute )
    {
        String signedEmail = identity.getEmail();

        // JWT token of logged in user to be forwarded
        String jwtToken = handler.getToken( request );
        if ( jwtToken == null )
        {
            throw new IllegalArgumentException( "Missing JWT token!" );
        }

        HttpResponse<Account> httpResponse = getAccount( signedEmail, jwtToken );
        if ( HttpStatus.OK == httpResponse.getStatus() )
        {
            return okResponse( httpResponse, responsePublisher );
        }
        else if ( HttpStatus.NOT_FOUND == httpResponse.getStatus() )
        {
            httpResponse = createAccount( signedEmail, identity, jwtToken );
            if ( HttpStatus.OK == httpResponse.getStatus() )
            {
                logger.info( "Account successfully created: " + signedEmail );
                return okResponse( httpResponse, responsePublisher );
            }
            else
            {
                logger.error( "Error has occurred during user creation: " + httpResponse.getStatus() + ", " + httpResponse.reason() + ", email: " + signedEmail );
            }
        }
        else
        {
            logger.error( "Error has occurred during user login: " + httpResponse.getStatus() + ", " + httpResponse.reason(), ", email: " + signedEmail );
        }

        return responsePublisher;
    }

    private Publisher<MutableHttpResponse<?>> okResponse(HttpResponse<Account> httpResponse, Publisher<MutableHttpResponse<?>> responsePublisher) {
        Account account = httpResponse.getBody( Account.class ).orElse( new Account() );

        return Flowable.fromPublisher( responsePublisher ).doOnNext( response -> {
            String jsonAccount = mapper.writeValueAsString( account );
            response.cookie( new NettyCookie( COOKIE_ACCOUNT_ATTRIBUTE, jsonAccount ) );
        } );
    }

    private HttpResponse<Account> getAccount( String signedEmail, String jwtToken )
    {
        Flowable<HttpResponse<Account>> call = accountStewardClient
                .exchange(
                        HttpRequest
                                .GET( "/accounts/" + signedEmail )
                                .header( "Authorization", "Bearer " + jwtToken ),
                        Account.class
                );

        return call.blockingFirst();
    }

    private HttpResponse<Account> createAccount( String signedEmail, FirebaseToken identity, String jwtToken )
    {
        Account account = new Account();
        account.setEmail( signedEmail );
        account.setCompany( false );
        account.setFirstName( identity.getName() );
        account.setIdentityId( identity.getIssuer() );

        return accountStewardClient.exchange(
                HttpRequest
                        .POST( "/accounts", account )
                        .header( "Authorization", "Bearer " + jwtToken ), Account.class )
                .blockingFirst();
    }
}
