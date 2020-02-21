/*
 * Copyright 2018 Comvai, s.r.o.
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
import com.google.appengine.api.utils.SystemProperty;
import com.google.cloud.secretmanager.v1beta1.AccessSecretVersionRequest;
import com.google.cloud.secretmanager.v1beta1.AccessSecretVersionResponse;
import com.google.cloud.secretmanager.v1beta1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1beta1.SecretVersionName;
import com.google.common.base.Stopwatch;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.InputStream;

import static biz.turnonline.ecosystem.origin.frontend.FrontendSession.DEFAULT_SESSION_LOCALE;

/**
 * Frontend application high level guice module.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class FrontendModule
        extends AbstractModule
{
    private static final Logger LOGGER = LoggerFactory.getLogger( FrontendModule.class );

    private static final String CREDENTIAL_NAME = "ecosystem-frontend-credential";

    /**
     * The GCP identification. It must be the project where the microservice is running
     * in order to connect to the Secret Manager within the same project.
     */
    private final String PROJECT_ID;

    public FrontendModule()
    {
        PROJECT_ID = SystemProperty.applicationId.get();
    }

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

        ApiCredential credential = new ApiCredential( "firebase" );
        credential.setProjectId( PROJECT_ID );
        credential.setProperty( "credential.search.endpointUrl", "https://search-engine-dot-turn-online.appspot.com/api/search/v1/global" );

        Stopwatch stopwatch = Stopwatch.createStarted();
        try
        {
            InputStream stream = readCredential();
            credential.load( stream );
        }
        catch ( IOException e )
        {
            LOGGER.error( "Reading credential has failed ", e );
        }
        finally
        {
            LOGGER.info( "Reading credential lasted " + stopwatch.stop() );
        }

        Names.bindProperties( binder(), credential );
    }

    private InputStream readCredential() throws IOException
    {
        SecretVersionName name = SecretVersionName.of( PROJECT_ID, CREDENTIAL_NAME, "latest" );

        try ( SecretManagerServiceClient client = SecretManagerServiceClient.create() )
        {
            AccessSecretVersionRequest request;
            request = AccessSecretVersionRequest.newBuilder()
                    .setName( name.toString() )
                    .build();

            // calling the remote secret manager service
            AccessSecretVersionResponse response = client.accessSecretVersion( request );
            return response.getPayload().getData().newInput();
        }
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
        config.setUiWidgetVersion( "4.4.0" );
        config.setFirebaseVersion( "7.9.0" );

        config.setSignInSuccessUrl( FrontendApplication.PRODUCT );
        config.setTermsUrl( "terms" );
        config.google().email().oneTapSignUp( clientId );
        config.setApiKey( apiKey );
        config.setProjectId( appId );
        config.setDatabaseName( appId );
        config.setBucketName( appId );
        config.setSenderId( senderId );

        return config;
    }
}
