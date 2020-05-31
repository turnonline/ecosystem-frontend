/*
 * Copyright 2020 TurnOnline.biz s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package biz.turnonline.ecosystem.origin.frontend;

import biz.turnonline.ecosystem.origin.frontend.content.subscription.SubscriptionModule;
import biz.turnonline.ecosystem.origin.frontend.identity.IdentitySessionUserListener;
import biz.turnonline.ecosystem.origin.frontend.steward.AccountStewardBeanMapperConfig;
import biz.turnonline.ecosystem.steward.facade.AccountStewardAdapterModule;
import biz.turnonline.ecosystem.steward.facade.AccountStewardClientModule;
import com.google.cloud.ServiceOptions;
import com.google.common.base.Strings;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import net.sf.jsr107cache.Cache;
import org.ctoolkit.restapi.client.ApiCredential;
import org.ctoolkit.restapi.client.adapter.BeanMapperConfig;
import org.ctoolkit.restapi.client.adapter.ClientApi;
import org.ctoolkit.restapi.client.appengine.CtoolkitRestFacadeAppEngineModule;
import org.ctoolkit.restapi.client.appengine.CtoolkitRestFacadeDefaultOrikaModule;
import org.ctoolkit.restapi.client.appengine.JCacheProvider;
import org.ctoolkit.restapi.client.firebase.GoogleApiFirebaseModule;
import org.ctoolkit.restapi.client.firebase.IdentityLoginListener;
import org.ctoolkit.services.guice.CtoolkitServicesAppEngineModule;
import org.ctoolkit.wicket.standard.identity.FirebaseConfig;

import javax.inject.Named;
import javax.inject.Singleton;

import static biz.turnonline.ecosystem.origin.frontend.FrontendSession.DEFAULT_SESSION_LOCALE;

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
        install( new CtoolkitRestFacadeDefaultOrikaModule() );
        install( new GoogleApiFirebaseModule() );
        install( new EntityRegistrarModule() );
        install( new SubscriptionModule( DEFAULT_SESSION_LOCALE ) );

        // Account and Contact management client modules
        install( new AccountStewardClientModule() );
        install( new AccountStewardAdapterModule() );

        bind( Cache.class ).toProvider( JCacheProvider.class ).in( Singleton.class );

        Multibinder<IdentityLoginListener> identityListener = Multibinder.newSetBinder( binder(), IdentityLoginListener.class );
        identityListener.addBinding().to( IdentitySessionUserListener.class );

        MapBinder.newMapBinder( binder(), String.class, ClientApi.class );

        Multibinder<BeanMapperConfig> multi = Multibinder.newSetBinder( binder(), BeanMapperConfig.class );
        multi.addBinding().to( AccountStewardBeanMapperConfig.class );

        ApiCredential credential = new ApiCredential();
        // The Firebase used from the same project where the service is running
        credential.setProjectId( ServiceOptions.getDefaultProjectId() );
        credential.load( "/config.properties" );
        Names.bindProperties( binder(), credential );
    }

    @Provides
    @Singleton
    public FirebaseConfig provideFirebaseConfig( @Named( "credential.default.projectId" ) String appId,
                                                 @Named( "credential.firebase.apiKey" ) String apiKey,
                                                 @Named( "credential.firebase.senderId" ) String senderId,
                                                 @Named( "credential.firebase.authDomain" ) String authDomain,
                                                 @Named( "credential.firebase.clientId" ) String clientId )
    {
        FirebaseConfig config = new FirebaseConfig();
        config.setUiWidgetVersion( "4.5.0" );
        config.setFirebaseVersion( "7.14.6" );

        config.setSignInSuccessUrl( FrontendApplication.PRODUCT );
        config.setTermsUrl( "terms" );
        config.google().email().oneTapSignUp( clientId );
        config.setApiKey( apiKey );
        config.setProjectId( appId );
        config.setDatabaseName( appId );
        config.setBucketName( appId );
        config.setSenderId( senderId );

        if ( !Strings.isNullOrEmpty( authDomain ) )
        {
            config.setAuthDomain( authDomain );
        }

        return config;
    }
}
