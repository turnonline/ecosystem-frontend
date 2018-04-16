package biz.turnonline.ecosystem.origin.frontend;

import biz.turnonline.ecosystem.origin.frontend.identity.IdentitySessionUserListener;
import com.google.appengine.api.utils.SystemProperty;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import net.sf.jsr107cache.Cache;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.model.IModel;
import org.ctoolkit.restapi.client.ApiCredential;
import org.ctoolkit.restapi.client.appengine.CtoolkitRestFacadeAppEngineModule;
import org.ctoolkit.restapi.client.appengine.DefaultOrikaMapperFactoryModule;
import org.ctoolkit.restapi.client.appengine.JCacheProvider;
import org.ctoolkit.restapi.client.firebase.GoogleApiFirebaseModule;
import org.ctoolkit.restapi.client.firebase.IdentityLoginListener;
import org.ctoolkit.services.guice.CtoolkitServicesAppEngineModule;
import org.ctoolkit.wicket.standard.identity.FirebaseConfig;
import org.ctoolkit.wicket.turnonline.menu.MenuSchema;
import org.ctoolkit.wicket.turnonline.menu.SearchResponse;
import org.ctoolkit.wicket.turnonline.model.IModelFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

        // TODO: remove
        bind( IModelFactory.class ).to(FakeModelFactory.class);
    }

    @Provides
    @Singleton
    public FirebaseConfig provideFirebaseConfig( @Named( "credential.firebase.apiKey" ) String apiKey,
                                                 @Named( "credential.firebase.senderId" ) String senderId,
                                                 @Named( "credential.firebase.clientId" ) String clientId )
    {
        String appId = SystemProperty.applicationId.get();
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

    // TODO: remove
    @Deprecated
    private static class FakeModelFactory
            implements IModelFactory
    {

        @Override
        public Class<? extends Page> getShoppingCartPage()
        {
            return null;
        }

        @Override
        public Class<? extends Page> getLoginPage()
        {
            return null;
        }

        @Override
        public Class<? extends Page> getSignUpPage()
        {
            return null;
        }

        @Override
        public Class<? extends Page> getMyAccountPage()
        {
            return null;
        }

        @Override
        public Class<? extends Page> getAccountSettingsPage()
        {
            return null;
        }

        @Override
        public IModel<String> getTermsUrlModel( @Nullable IModel<?> pageModel )
        {
            return null;
        }

        @Override
        public IModel<String> getLogoUrlModel( @Nullable IModel<?> pageModel )
        {
            return null;
        }

        @Override
        public IModel<Boolean> isLoggedInModel()
        {
            return null;
        }

        @Override
        public IModel<Long> getCartItemsCountModel()
        {
            return null;
        }

        @Override
        public Roles getRoles()
        {
            return null;
        }

        @Override
        public IModel getLoggedInAccountModel()
        {
            return null;
        }

        @Override
        public Behavior[] getBehaviors( @Nonnull RuntimeConfigurationType type, @Nullable IModel<?> pageModel )
        {
            return new Behavior[0];
        }

        @Override
        public String getGoogleAnalyticsTrackingId( @Nullable IModel<?> pageModel )
        {
            return null;
        }

        @Override
        public MenuSchema provideMenuSchema( @Nonnull Page context, @Nullable Roles roles )
        {
            return null;
        }

        @Override
        public IModel<?> getShoppingMallModel( @Nonnull HttpServletRequest request )
        {
            return null;
        }

        @Override
        public String getCountryOriginRequest( @Nonnull HttpServletRequest request )
        {
            return null;
        }

        @Override
        public String getAccountRole()
        {
            return null;
        }

        @Override
        public String getCityOriginRequest( @Nonnull HttpServletRequest request )
        {
            return null;
        }

        @Override
        public List<SearchResponse> getSearchResponseList( String input )
        {
            return null;
        }
    }
}
