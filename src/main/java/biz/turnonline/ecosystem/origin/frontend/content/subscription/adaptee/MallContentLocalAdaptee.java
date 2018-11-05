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

package biz.turnonline.ecosystem.origin.frontend.content.subscription.adaptee;

import biz.turnonline.ecosystem.origin.frontend.content.ContentSubscription;
import biz.turnonline.ecosystem.origin.frontend.content.TurnOnlineClient;
import biz.turnonline.ecosystem.origin.frontend.content.model.CommonContent;
import biz.turnonline.ecosystem.origin.frontend.content.model.MallArticle;
import biz.turnonline.ecosystem.origin.frontend.content.model.MallContent;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.ContentNaming;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.RawCommonContent;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.RawMallArticle;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.event.MallArticleUpdateEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.LoadType;
import com.googlecode.objectify.cmd.QueryKeys;
import net.sf.jsr107cache.Cache;
import org.ctoolkit.restapi.client.Identifier;
import org.ctoolkit.restapi.client.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * {@link MallArticle} local adaptee implementation.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
@Singleton
public class MallContentLocalAdaptee
        extends GetExecutorCachedContentAdaptee<MallContent>
{
    private static final Logger logger = LoggerFactory.getLogger( MallContentLocalAdaptee.class );

    private final ObjectMapper json;

    @Inject
    public MallContentLocalAdaptee( @TurnOnlineClient Cache cache,
                                    @ContentSubscription ObjectMapper json,
                                    @ContentSubscription EventBus bus,
                                    ContentNaming naming )
    {
        super( cache, naming );
        this.json = json;
        bus.register( this );
    }

    @Override
    public MallContent executeGet( @Nonnull Object request,
                                   @Nullable Map<String, Object> parameters,
                                   @Nullable Locale locale )
            throws IOException
    {
        Identifier identifier = ( Identifier ) request;

        String name = naming().composeFullName( CommonContent.class, identifier, locale );
        RawCommonContent raw = ofy().load().type( RawCommonContent.class ).id( name ).now();
        if ( raw == null )
        {
            throw new NotFoundException( identifier.toString() + " Full Entity.Id " + name );
        }

        CommonContent common = raw.convert( json );

        String ownerName = raw.getOwnerFromName();
        String mallName = naming().idName( MallContent.class, ownerName );

        MallContent content = new MallContent();
        content.setName( mallName );
        content.setContentTitle( common.getContentTitle() );
        content.setAnalyticsAccount( common.getAnalyticsAccount() );
        content.setHeaderDescription( common.getHeaderDescription() );
        content.setHeaderKeywords( common.getHeaderKeywords() );
        content.setContentLocale( common.getContentLocale() );
        content.setCreatedDate( common.getCreatedDate() );
        content.setModificationDate( common.getModificationDate() );
        content.setGateways( common.getGateways() );
        content.setShowGmailLogin( common.isShowGmailLogin() );
        content.setShowFacebookLogin( common.isShowFacebookLogin() );
        content.setLoginProviders( common.getLoginProviders() );
        content.setSeller( common.getSeller() );

        // retrieve keys first, as a result is always consistent (the most actual record)
        LoadType<RawMallArticle> query = ofy().load().type( RawMallArticle.class );
        QueryKeys<RawMallArticle> ids = query.filter( "owner", ownerName ).keys();
        RawMallArticle mallArticle;

        for ( Key<RawMallArticle> key : ids.list() )
        {
            mallArticle = ofy().load().key( key ).now();
            content.add( mallArticle.convert( json ) );
        }

        logger.info( "Mall's articles list size = " + content.getArticles().size() + " for owner: " + ownerName );
        return content;
    }

    @Override
    protected String composeKey( @Nonnull Identifier identifier,
                                 @Nullable Map<String, Object> parameters,
                                 @Nullable Locale locale )
    {
        return naming().composeFullName( MallContent.class, identifier, locale );
    }

    @Subscribe
    public void updateCache( MallArticleUpdateEvent event )
    {
        RawMallArticle temp = new RawMallArticle( event.getName() );
        String owner = temp.getOwner();
        Locale locale = temp.getLocaleFromName();

        // compose mall content name related to given domain/owner
        String mallName = composeKey( new Identifier( owner ), null, locale );
        removeFromCache( mallName );
    }
}
