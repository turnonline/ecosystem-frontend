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
import net.sf.jsr107cache.Cache;
import org.ctoolkit.restapi.client.ApiCredential;
import org.ctoolkit.restapi.client.appengine.CtoolkitRestFacadeAppEngineModule;
import org.ctoolkit.restapi.client.appengine.JCacheProvider;
import org.ctoolkit.restapi.client.firebase.GoogleApiFirebaseModule;
import org.ctoolkit.restapi.client.firebase.IdentityLoginListener;
import org.ctoolkit.services.common.CtoolkitCommonServicesModule;
import org.ctoolkit.services.guice.CtoolkitServicesAppEngineModule;
import org.ctoolkit.wicket.standard.identity.FirebaseConfig;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Frontend application high level guice module.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
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
        install( new CtoolkitRestFacadeAppEngineModule() );
        install( new GoogleApiFirebaseModule() );

        // Account and Contact management client modules
        install( new AccountManagementApiModule() );
        install( new AccountManagementAdapterModule() );

        bind( Cache.class ).toProvider( JCacheProvider.class ).in( Singleton.class );

        Multibinder<IdentityLoginListener> identityListener;
        identityListener = Multibinder.newSetBinder( binder(), IdentityLoginListener.class );
        identityListener.addBinding().to( IdentitySessionUserListener.class );

        ApiCredential credential = new ApiCredential();
        credential.load( "/identity.properties" );

        Names.bindProperties( binder(), credential );
    }

    @Provides
    @Singleton
    public FirebaseConfig provideFirebaseConfig( @Named( "credential.identity.apiKey" ) String apiKey,
                                                 @Named( "credential.identity.senderId" ) String senderId,
                                                 @Named( "credential.identity.clientId" ) String clientId )
    {
        String appId = SystemProperty.applicationId.get();
        FirebaseConfig options = new FirebaseConfig();

        options.setSignInSuccessUrl( FrontendApplication.MY_ACCOUNT );
        options.setTermsUrl( "terms" );
        options.google().email().facebook().oneTapSignUp( clientId );
        options.setApiKey( apiKey );
        options.setProjectId( appId );
        options.setDatabaseName( appId );
        options.setBucketName( appId );
        options.setSenderId( senderId );

        return options;
    }
}
