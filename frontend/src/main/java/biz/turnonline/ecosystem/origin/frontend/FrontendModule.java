package biz.turnonline.ecosystem.origin.frontend;

import biz.turnonline.ecosystem.account.client.AccountManagementAdapterModule;
import biz.turnonline.ecosystem.account.client.AccountManagementApiModule;
import biz.turnonline.ecosystem.origin.frontend.identity.IdentitySessionUserListener;
import biz.turnonline.ecosystem.origin.frontend.server.ServerModule;
import com.google.appengine.api.utils.SystemProperty;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import org.ctoolkit.restapi.client.ApiCredential;
import org.ctoolkit.restapi.client.appengine.FacadeAppEngineModule;
import org.ctoolkit.services.common.CtoolkitCommonServicesModule;
import org.ctoolkit.services.guice.CtoolkitServicesAppEngineModule;
import org.ctoolkit.services.identity.CtoolkitServicesIdentityModule;
import org.ctoolkit.services.identity.IdentityLoginListener;
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
    @Override
    protected void configure()
    {
        install( new ServerModule() );
        // ctoolkit services module
        install( new CtoolkitCommonServicesModule() );
        install( new CtoolkitServicesAppEngineModule() );
        install( new CtoolkitServicesIdentityModule() );
        install( new FacadeAppEngineModule() );

        // Account and Contact management client modules
        install( new AccountManagementApiModule() );
        install( new AccountManagementAdapterModule() );

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
