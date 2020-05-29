package biz.turnonline.ecosystem.origin.frontend.service;

import biz.turnonline.ecosystem.origin.frontend.model.Account;
import biz.turnonline.ecosystem.origin.frontend.model.AccountBusiness;
import biz.turnonline.ecosystem.origin.frontend.model.ControllerModel;
import biz.turnonline.ecosystem.origin.frontend.model.FirebaseConfig;
import biz.turnonline.ecosystem.origin.frontend.model.GwtConfig;
import biz.turnonline.ecosystem.origin.frontend.model.InvoicingConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Prototype;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.context.ServerRequestContext;
import io.micronaut.http.cookie.Cookie;

import static biz.turnonline.ecosystem.origin.frontend.service.IdentityCheckSessionFilter.COOKIE_ACCOUNT_ATTRIBUTE;

/**
 * @author <a href="mailto:pohorelec@turnonlie.biz">Jozef Pohorelec</a>
 */
@Factory
public class ModelFactory
{
    @Prototype
    public ControllerModel provideControllerModel( GwtConfig gwtConfig,
                                                   FirebaseConfig firebaseConfig,
                                                   ObjectMapper mapper )
    {
        Account account = getAccountFromCookie( mapper );

        ControllerModel model = new ControllerModel();
        model.setLoggedIn( account != null );
        model.setAccount( account );
        model.setFirebaseConfig( firebaseConfig );
        model.setGwtConfig( enrichGwtConfigWithAccountData( gwtConfig, account ) );

        return model;
    }

    private Account getAccountFromCookie( ObjectMapper mapper )
    {
        HttpRequest<Object> currentRequest = ServerRequestContext.currentRequest().orElse( null );
        if ( currentRequest != null )
        {
            Cookie accountCookie = currentRequest.getCookies().findCookie( COOKIE_ACCOUNT_ATTRIBUTE ).orElse( null );
            if ( accountCookie != null )
            {
                String accountJson = accountCookie.getValue();

                try
                {
                    return mapper.readValue( accountJson, Account.class );
                }
                catch ( JsonProcessingException e )
                {
                    return null;
                }
            }
        }

        return null;
    }

    private GwtConfig enrichGwtConfigWithAccountData( GwtConfig config, Account account )
    {
        if ( account == null )
        {
            return config;
        }

        String domicile = null;
        AccountBusiness business = account.getBusiness();
        if ( business != null )
        {
            domicile = business.getDomicile();
        }

        String currency = null;
        InvoicingConfig invoicing = account.getInvoicing();
        if ( invoicing != null )
        {
            currency = invoicing.getCurrency();
        }

        String logo = null;
        if ( account.getBusiness() != null &&
                account.getBusiness().getLogo() != null &&
                account.getBusiness().getLogo().getServingUrl() != null )
        {
            logo = account.getBusiness().getLogo().getServingUrl();
        }

        config.setLoginId( account.getId().toString() );
        config.setDomicile( domicile );
        config.setCurrency( currency );
        config.setLogo( logo );

        return config;
    }
}
