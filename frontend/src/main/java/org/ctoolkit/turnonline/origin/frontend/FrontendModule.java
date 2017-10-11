package org.ctoolkit.turnonline.origin.frontend;

import com.google.api.services.identitytoolkit.IdentityToolkit;
import com.google.api.services.identitytoolkit.model.IdentitytoolkitRelyingpartyGetProjectConfigResponse;
import com.google.api.services.identitytoolkit.model.IdpConfig;
import com.google.appengine.api.utils.SystemProperty;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import org.ctoolkit.restapi.client.ApiCredential;
import org.ctoolkit.restapi.client.identity.GoogleApiIdentityToolkitModule;
import org.ctoolkit.services.common.CtoolkitCommonServicesModule;
import org.ctoolkit.services.common.PropertyConfig;
import org.ctoolkit.services.common.PropertyService;
import org.ctoolkit.services.guice.CtoolkitServicesAppEngineModule;
import org.ctoolkit.services.identity.IdentityLoginListener;
import org.ctoolkit.services.identity.IdentityTroubleListener;
import org.ctoolkit.turnonline.client.appengine.TurnOnlineRestApiClientModule;
import org.ctoolkit.turnonline.origin.frontend.identity.IdentityChangesListener;
import org.ctoolkit.turnonline.origin.frontend.identity.IdentitySessionUserListener;
import org.ctoolkit.turnonline.origin.frontend.model.NoContent;
import org.ctoolkit.turnonline.origin.frontend.server.ServerModule;
import org.ctoolkit.wicket.turnonline.identity.IdentityOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import javax.inject.Singleton;
import java.io.IOException;

/**
 * Frontend application high level guice module.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class FrontendModule
        extends AbstractModule
{
    public static final String JOKER_DOMAIN = "turnonline.biz";

    private static final Logger log = LoggerFactory.getLogger( FrontendModule.class );

    private static String TESTING_JOKER_DOMAIN = "ctoolkit.org";

    @Override
    protected void configure()
    {
        install( new ServerModule() );
        // ctoolkit services module
        install( new CtoolkitCommonServicesModule() );
        install( new CtoolkitServicesAppEngineModule() );
        // REST API Facade modules
        install( new GoogleApiIdentityToolkitModule() );
        // TurnOnline REST API modules
        install( new TurnOnlineRestApiClientModule() );

        PropertyConfig config = new PropertyConfig();
        config.setProductionAppI( "turn-online" );
        config.setTestAppI( "turn-online" );
        Names.bindProperties( binder(), config );

        Multibinder<IdentityLoginListener> identityListener;
        identityListener = Multibinder.newSetBinder( binder(), IdentityLoginListener.class );
        identityListener.addBinding().to( IdentitySessionUserListener.class );

        Multibinder<IdentityTroubleListener> troubleListener;
        troubleListener = Multibinder.newSetBinder( binder(), IdentityTroubleListener.class );
        troubleListener.addBinding().to( IdentityChangesListener.class );


        //Google API initialization
        String appId = SystemProperty.applicationId.get();
        boolean isDevelopmentEnvironment = SystemProperty.environment.value()
                == SystemProperty.Environment.Value.Development;

        ApiCredential credential = new ApiCredential();
        // The OAuth 2.0 client ID (type - web application)
        String clientId = "";
        String serviceAccount = "";

        // needed for local development
        credential.setFileName( "/" );
        credential.setServiceAccountEmail( serviceAccount );
        credential.setApiKey( "" );
        credential.setCredentialOn( isDevelopmentEnvironment );

        // common config
        credential.setProjectId( appId );
        credential.setClientId( clientId );
        //FIXME EndpointUrl hardcoded
        credential.setEndpointUrl( "http://localhost:8990/_ah/api/" );

        Names.bindProperties( binder(), credential );
    }

    @Provides
    @Singleton
    @Named( "JokerDomain" )
    String provideJokerDomain( PropertyService propertyService )
    {
        return propertyService.isProductionEnvironment() ? JOKER_DOMAIN : TESTING_JOKER_DOMAIN;
    }

    @Provides
    @Singleton
    public NoContent provideNoContent()
    {
        // Note: this works for production only
        return new NoContent( "", FrontendModule.JOKER_DOMAIN );
    }

    @Provides
    @Singleton
    public IdentityOptions provideIdentityOptions( IdentityToolkit it )
    {
        IdentityOptions options = new IdentityOptions();

        options.setSignInSuccessUrl( FrontendApplication.MY_ACCOUNT );
        options.setSignOutUrl( FrontendApplication.LOGOUT );
        options.setOobActionUrl( FrontendApplication.LOGIN_TROUBLE_HANDLER );
        options.setSiteName( "ctoolkit.org" );
        options.setDisplayMode( IdentityOptions.DisplayMode.PROVIDER_FIRST );
        options.getSignInOptions().add( IdentityOptions.SignInOption.PASSWORD );

        long start = System.currentTimeMillis();
        try
        {
            IdentitytoolkitRelyingpartyGetProjectConfigResponse config = it.relyingparty().getProjectConfig().execute();
            for ( IdpConfig idpConfig : config.getIdpConfig() )
            {
                if ( idpConfig.getEnabled() )
                {
                    options.getSignInOptions().add( IdentityOptions.SignInOption.valueOf( idpConfig.getProvider() ) );
                }
            }

            options.setApiKey( config.getApiKey() );
        }
        catch ( IOException e )
        {
            log.error( "Error occur during loading identity config", e );
        }

        log.warn( "Duration: " + ( System.currentTimeMillis() - start ) );
        System.out.println( "Duration: " + ( System.currentTimeMillis() - start ) );
        return options;
    }
}
