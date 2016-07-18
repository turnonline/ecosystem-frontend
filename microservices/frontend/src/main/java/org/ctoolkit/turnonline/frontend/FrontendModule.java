package org.ctoolkit.turnonline.frontend;

import com.google.api.services.identitytoolkit.IdentityToolkit;
import com.google.api.services.identitytoolkit.model.IdentitytoolkitRelyingpartyGetProjectConfigResponse;
import com.google.api.services.identitytoolkit.model.IdpConfig;
import com.google.appengine.api.utils.SystemProperty;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import org.ctoolkit.restapi.client.googleapis.ApiKey;
import org.ctoolkit.restapi.client.googleapis.ClientId;
import org.ctoolkit.restapi.client.googleapis.DevelopmentEnvironment;
import org.ctoolkit.restapi.client.googleapis.EndpointUrl;
import org.ctoolkit.restapi.client.googleapis.P12FileName;
import org.ctoolkit.restapi.client.googleapis.ProjectId;
import org.ctoolkit.restapi.client.googleapis.ServiceAccountEmail;
import org.ctoolkit.restapi.client.identity.GoogleApiIdentityToolkitModule;
import org.ctoolkit.services.common.CommonServicesModule;
import org.ctoolkit.services.common.PropertyService;
import org.ctoolkit.services.guice.appengine.CtoolkitServicesAppEngineModule;
import org.ctoolkit.services.identity.IdentityLoginListener;
import org.ctoolkit.services.identity.IdentityTroubleListener;
import org.ctoolkit.turnonline.client.appengine.TurnOnlineRestApiClientModule;
import org.ctoolkit.turnonline.frontend.identity.IdentityChangesListener;
import org.ctoolkit.turnonline.frontend.identity.IdentitySessionUserListener;
import org.ctoolkit.turnonline.frontend.server.ServerModule;
import org.ctoolkit.turnonline.shared.feprops.NoPageProps;
import org.ctoolkit.turnonline.wicket.identity.IdentityOptions;
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
        install( new CommonServicesModule() );
        install( new CtoolkitServicesAppEngineModule() );
        // REST API Facade modules
//        install( new FacadeAppEngineModule() );
//        install( new DefaultOrikaMapperFactoryModule() );
        install( new GoogleApiIdentityToolkitModule() );
        // TurnOnline REST API modules
        install( new TurnOnlineRestApiClientModule() );

        bind( String.class ).annotatedWith( Names.named( PropertyService.PRODUCTION_APP_ID ) ).toInstance( "c-shop" );
        bind( String.class ).annotatedWith( Names.named( PropertyService.TEST_APP_ID ) ).toInstance( "turn-online" );

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

        // The OAuth 2.0 client ID (type - web application)
        String clientId = "";
        String serviceAccount = "";

        // needed for local development
        bind( String.class ).annotatedWith( P12FileName.class ).toInstance( "/" );
        bind( String.class ).annotatedWith( ServiceAccountEmail.class ).toInstance( serviceAccount );
        bind( String.class ).annotatedWith( ApiKey.class ).toInstance( "" );
        bind( Boolean.class ).annotatedWith( DevelopmentEnvironment.class ).toInstance( isDevelopmentEnvironment );

        // common config
        bind( String.class ).annotatedWith( ProjectId.class ).toInstance( appId );
        bind( String.class ).annotatedWith( ClientId.class ).toInstance( clientId );
        //FIXME EndpointUrl hardcoded
        bind( String.class ).annotatedWith( EndpointUrl.class ).toInstance( "http://localhost:8990/_ah/api/" );

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
    public NoPageProps provideNoPageProps()
    {
        // Note: this works for production only
        return new NoPageProps( "", FrontendModule.JOKER_DOMAIN );
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
