package biz.turnonline.ecosystem.origin.frontend;

import biz.turnonline.ecosystem.origin.frontend.identity.IdentitySessionUserListener;
import com.google.appengine.api.utils.SystemProperty;
import com.google.common.base.Strings;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import net.sf.jsr107cache.Cache;
import org.ctoolkit.restapi.client.ApiCredential;
import org.ctoolkit.restapi.client.appengine.CtoolkitRestFacadeAppEngineModule;
import org.ctoolkit.restapi.client.appengine.DefaultOrikaMapperFactoryModule;
import org.ctoolkit.restapi.client.appengine.JCacheProvider;
import org.ctoolkit.restapi.client.firebase.GoogleApiFirebaseModule;
import org.ctoolkit.restapi.client.firebase.IdentityLoginListener;
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
        // ctoolkit services module
        install( new CtoolkitServicesAppEngineModule() );
        install( new CtoolkitRestFacadeAppEngineModule() );
        install( new GoogleApiFirebaseModule() );
        install( new DefaultOrikaMapperFactoryModule() );

        bind( Cache.class ).toProvider( JCacheProvider.class ).in( Singleton.class );

        Multibinder<IdentityLoginListener> identityListener = Multibinder.newSetBinder( binder(), IdentityLoginListener.class );
        identityListener.addBinding().to( IdentitySessionUserListener.class );

        ApiCredential credential = new ApiCredential();
        credential.load( "/identity.properties" );

        Names.bindProperties( binder(), credential );
    }

    @Provides
    @Singleton
    public FirebaseConfig provideFirebaseConfig( @Named( "credential.firebase.apiKey" ) String apiKey,
                                                 @Named( "credential.firebase.senderId" ) String senderId,
                                                 @Named( "credential.firebase.projectId" ) String appId,
                                                 @Named( "credential.firebase.clientId" ) String clientId )
    {
        if ( Strings.isNullOrEmpty( appId ) )
        {
            appId = SystemProperty.applicationId.get();
        }
        FirebaseConfig config = new FirebaseConfig();

        config.setSignInSuccessUrl( FrontendApplication.MY_ACCOUNT );
        config.setTermsUrl( "terms" );
        config.google().email().facebook().oneTapSignUp( clientId );
        config.setApiKey( apiKey );
        config.setProjectId( appId );
        config.setDatabaseName( appId );
        config.setBucketName( appId );
        config.setSenderId( senderId );

        return config;
    }
}
