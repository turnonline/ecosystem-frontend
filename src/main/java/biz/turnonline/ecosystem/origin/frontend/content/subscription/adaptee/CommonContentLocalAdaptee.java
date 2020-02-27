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

package biz.turnonline.ecosystem.origin.frontend.content.subscription.adaptee;

import biz.turnonline.ecosystem.origin.frontend.content.ContentSubscription;
import biz.turnonline.ecosystem.origin.frontend.content.TurnOnlineClient;
import biz.turnonline.ecosystem.origin.frontend.content.model.CommonContent;
import biz.turnonline.ecosystem.origin.frontend.content.model.MallContent;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.ContentNaming;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.RawCommonContent;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.event.CommonContentUpdateEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import net.sf.jsr107cache.Cache;
import org.ctoolkit.restapi.client.Identifier;
import org.ctoolkit.restapi.client.NotFoundException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * {@link CommonContent} local adaptee implementation.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
@Singleton
public class CommonContentLocalAdaptee
        extends GetExecutorCachedContentAdaptee<CommonContent>
{
    private final ObjectMapper mapper;

    @Inject
    public CommonContentLocalAdaptee( @TurnOnlineClient Cache cache,
                                      @ContentSubscription ObjectMapper mapper,
                                      @ContentSubscription EventBus bus,
                                      ContentNaming naming )
    {
        super( cache, naming );
        this.mapper = mapper;
        bus.register( this );
    }

    @Override
    public CommonContent executeGet( @Nonnull Object request,
                                     @Nullable Map<String, Object> parameters,
                                     @Nullable Locale locale )
            throws IOException
    {
        Identifier identifier = ( Identifier ) request;
        String name = composeKey( identifier, parameters, locale );
        RawCommonContent raw = ofy().load().type( RawCommonContent.class ).id( name ).now();
        if ( raw == null )
        {
            throw new NotFoundException( identifier.toString() + " Full Entity.Id " + name );
        }

        return raw.convert( mapper );
    }

    @Override
    protected String composeKey( @Nonnull Identifier identifier,
                                 @Nullable Map<String, Object> parameters,
                                 @Nullable Locale locale )
    {
        return naming().composeFullName( CommonContent.class, identifier, locale );
    }

    @Subscribe
    public void updateCache( CommonContentUpdateEvent event )
    {
        removeFromCache( event.getName() );

        // mall content is built from common content too thus needs to be removed from the cache.
        RawCommonContent temp = new RawCommonContent( event.getName() );
        String owner = temp.getOwnerFromName();
        Locale locale = temp.getLocaleFromName();

        // Compose mall content name related,
        String mallName = naming().composeFullName( MallContent.class, new Identifier( owner ), locale );
        removeFromCache( mallName );
    }
}
