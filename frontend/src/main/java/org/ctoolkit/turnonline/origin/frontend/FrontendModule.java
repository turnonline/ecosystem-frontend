package org.ctoolkit.turnonline.origin.frontend;

import com.google.appengine.api.utils.SystemProperty;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import org.ctoolkit.restapi.client.ApiCredential;
import org.ctoolkit.services.common.CtoolkitCommonServicesModule;
import org.ctoolkit.services.common.PropertyConfig;
import org.ctoolkit.services.common.PropertyService;
import org.ctoolkit.services.guice.CtoolkitServicesAppEngineModule;
import org.ctoolkit.services.identity.CtoolkitServicesIdentityModule;
import org.ctoolkit.services.identity.IdentityLoginListener;
import org.ctoolkit.turnonline.client.appengine.TurnOnlineRestApiClientModule;
import org.ctoolkit.turnonline.origin.frontend.identity.IdentitySessionUserListener;
import org.ctoolkit.turnonline.origin.frontend.model.NoContent;
import org.ctoolkit.turnonline.origin.frontend.server.ServerModule;
import org.ctoolkit.wicket.turnonline.identity.IdentityOptions;

import javax.inject.Named;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.InputStream;

/**
 * Frontend application high level guice module.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class FrontendModule
        extends AbstractModule
{
    public static final String JOKER_DOMAIN = "turnonline.biz";

    private static String TESTING_JOKER_DOMAIN = "ctoolkit.org";

    @Override
    protected void configure()
    {
        install( new ServerModule() );
        // ctoolkit services module
        install( new CtoolkitCommonServicesModule() );
        install( new CtoolkitServicesAppEngineModule() );
        install( new CtoolkitServicesIdentityModule() );

        // TurnOnline REST API modules
        install( new TurnOnlineRestApiClientModule() );

        PropertyConfig config = new PropertyConfig();
        config.setProductionAppI( "turn-online" );
        config.setTestAppI( "turn-online" );
        Names.bindProperties( binder(), config );

        Multibinder<IdentityLoginListener> identityListener;
        identityListener = Multibinder.newSetBinder( binder(), IdentityLoginListener.class );
        identityListener.addBinding().to( IdentitySessionUserListener.class );

        InputStream stream = FrontendModule.class.getResourceAsStream( "/identity.properties" );
        ApiCredential credential = new ApiCredential();
        try
        {
            credential.load( stream );
        }
        catch ( IOException e )
        {
            throw new IllegalArgumentException( "identity.properties not found.", e );
        }

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
    public IdentityOptions provideIdentityOptions( @Named( "credential.identity.apiKey" ) String apiKey,
                                                   @Named( "credential.identity.senderId" ) String senderId )
    {
        String appId = SystemProperty.applicationId.get();
        IdentityOptions options = new IdentityOptions();

        options.setSignInSuccessUrl( FrontendApplication.MY_ACCOUNT );
        options.setTermsUrl( "terms" );
        options.getSignInOptions().add( "firebase.auth.GoogleAuthProvider.PROVIDER_ID" );
        options.getSignInOptions().add( "firebase.auth.EmailAuthProvider.PROVIDER_ID" );
        options.getSignInOptions().add( "firebase.auth.FacebookAuthProvider.PROVIDER_ID" );
        options.setApiKey( apiKey );
        options.setProjectId( appId );
        options.setDatabaseName( appId );
        options.setBucketName( appId );
        options.setSenderId( senderId );

        return options;
    }
}
