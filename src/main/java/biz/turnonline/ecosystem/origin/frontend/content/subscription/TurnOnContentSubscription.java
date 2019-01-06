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

package biz.turnonline.ecosystem.origin.frontend.content.subscription;

import biz.turnonline.ecosystem.origin.frontend.content.ContentSubscription;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.event.CommonContentUpdateEvent;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.event.EventContentUpdateEvent;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.event.MallArticleUpdateEvent;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.event.PayInvoiceContentUpdateEvent;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.event.ProductContentUpdateEvent;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.event.TermsContentUpdateEvent;
import com.google.api.services.pubsub.model.PubsubMessage;
import com.google.common.base.Strings;
import com.google.common.eventbus.EventBus;
import org.apache.commons.codec.binary.Base64;
import org.ctoolkit.restapi.client.pubsub.PubsubCommand;
import org.ctoolkit.restapi.client.pubsub.PubsubMessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import java.nio.charset.Charset;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.googlecode.objectify.ObjectifyService.ofy;
import static org.ctoolkit.restapi.client.pubsub.PubsubCommand.DATA_TYPE;
import static org.ctoolkit.restapi.client.pubsub.PubsubCommand.ENTITY_ID;

/**
 * The subscription 'turnon.content' listener implementation
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
class TurnOnContentSubscription
        implements PubsubMessageListener
{
    private static final Logger logger = LoggerFactory.getLogger( TurnOnContentSubscription.class );

    private static final long serialVersionUID = -5738883108697749503L;

    @SuppressWarnings( "UnstableApiUsage" )
    private final EventBus bus;

    private final ContentNaming naming;

    /**
     * The constructor.
     *
     * @param naming the naming rules class instance
     */
    @SuppressWarnings( "UnstableApiUsage" )
    @Inject
    TurnOnContentSubscription( @Nonnull ContentNaming naming,
                               @ContentSubscription EventBus bus )
    {
        this.naming = checkNotNull( naming );
        this.bus = bus;
    }

    @Override
    public void onMessage( @Nonnull PubsubMessage message, @Nonnull String subscription )
    {
        PubsubCommand command = new PubsubCommand( message );
        command.validate( DATA_TYPE, ENTITY_ID );

        String dataType = command.getDataType();
        String receivedContentName = command.getEntityId();
        String receivedLocale = command.getAcceptLanguage();
        String data = message.getData();
        boolean deletion = command.isDelete();

        String name = naming.prefixName( receivedContentName, receivedLocale );
        if ( deletion )
        {
            onDeletion( dataType, name, data, subscription, receivedContentName, receivedLocale );
        }
        else
        {
            onMessage( dataType, name, data, subscription, receivedContentName, receivedLocale );
        }
    }

    private void onMessage( @Nonnull String dataType,
                            @Nonnull String contentName,
                            @Nonnull String data,
                            @Nonnull String subscription,
                            @Nonnull String receivedContentName,
                            @Nullable String receivedLocale )
    {
        String msgLocale = Strings.isNullOrEmpty( receivedLocale ) ? "" : " [" + receivedLocale + "]";
        logger.info( "[" + subscription + "] The message type '" + dataType + "' has been received with length: "
                + data.length() + " and name: '" + receivedContentName + "'" + msgLocale + "." );

        String decoded;
        if ( Base64.isBase64( data.getBytes() ) )
        {
            decoded = new String( new PubsubMessage().setData( data ).decodeData(), Charset.forName( "UTF-8" ) );
        }
        else
        {
            decoded = data;
        }
        logger.info( decoded );

        switch ( dataType )
        {
            case "CommonContent":
            {
                RawCommonContent raw = load( RawCommonContent.class, contentName );
                if ( raw == null )
                {
                    raw = new RawCommonContent( contentName );
                }
                raw.setContent( data );
                save( raw );
                bus.post( new CommonContentUpdateEvent( contentName ) );

                break;
            }
            case "MallArticle":
            {
                RawMallArticle raw = load( RawMallArticle.class, contentName );
                if ( raw == null )
                {
                    raw = new RawMallArticle( contentName );
                }
                raw.setContent( data );
                save( raw );
                bus.post( new MallArticleUpdateEvent( contentName ) );

                break;
            }
            case "ProductContent":
            {
                RawProductContent raw = load( RawProductContent.class, contentName );
                if ( raw == null )
                {
                    raw = new RawProductContent( contentName );
                }
                raw.setContent( data );
                save( raw );
                bus.post( new ProductContentUpdateEvent( contentName ) );

                break;
            }
            case "EventContent":
            {
                RawEventContent raw = load( RawEventContent.class, contentName );
                if ( raw == null )
                {
                    raw = new RawEventContent( contentName );
                }
                raw.setContent( data );
                save( raw );
                bus.post( new EventContentUpdateEvent( contentName ) );

                break;
            }
            case "PayInvoiceContent":
            {
                RawPayInvoiceContent raw = load( RawPayInvoiceContent.class, contentName );
                if ( raw == null )
                {
                    raw = new RawPayInvoiceContent( contentName );
                }
                raw.setContent( data );
                save( raw );
                bus.post( new PayInvoiceContentUpdateEvent( contentName ) );

                break;
            }
            case "TermsContent":
            {
                RawTermsContent raw = load( RawTermsContent.class, contentName );
                if ( raw == null )
                {
                    raw = new RawTermsContent( contentName );
                }
                raw.setContent( data );
                save( raw );
                bus.post( new TermsContentUpdateEvent( contentName ) );

                break;
            }
            default:
            {
                logger.error( "Unknown type '" + dataType + "' with name: '" + contentName + "' has been received." );
            }
        }
    }

    private void onDeletion( @Nonnull String dataType,
                             @Nonnull String contentName,
                             @Nonnull String data,
                             @Nonnull String subscription,
                             @Nonnull String receivedContentName,
                             @Nullable String receivedLocale )
    {
        String msgLocale = Strings.isNullOrEmpty( receivedLocale ) ? "" : " [" + receivedLocale + "]";
        logger.info( "[" + subscription + "] The request to delete content of type '" + dataType + "' with name: '"
                + receivedContentName + "'" + msgLocale + " has been received." );

        switch ( dataType )
        {
            case "CommonContent":
            {
                delete( RawCommonContent.class, contentName );
                bus.post( new CommonContentUpdateEvent( contentName ) );
                break;
            }
            case "MallArticle":
            {
                delete( RawMallArticle.class, contentName );
                bus.post( new MallArticleUpdateEvent( contentName ) );
                break;
            }
            case "ProductContent":
            {
                delete( RawProductContent.class, contentName );
                bus.post( new ProductContentUpdateEvent( contentName ) );
                break;
            }
            case "EventContent":
            {
                delete( RawEventContent.class, contentName );
                bus.post( new EventContentUpdateEvent( contentName ) );
                break;
            }
            case "PayInvoiceContent":
            {
                delete( RawPayInvoiceContent.class, contentName );
                bus.post( new PayInvoiceContentUpdateEvent( contentName ) );
                break;
            }
            case "TermsContent":
            {
                delete( RawTermsContent.class, contentName );
                bus.post( new TermsContentUpdateEvent( contentName ) );
                break;
            }
            default:
            {
                logger.error( "Unknown type '" + dataType + "' with name: '" + contentName + "' has been received." );
            }
        }
    }

    void save( BaseRawContent content )
    {
        ofy().save().entity( content ).now();
    }

    <T> T load( Class<T> type, String contentName )
    {
        return ofy().load().type( type ).id( contentName ).now();
    }

    void delete( Class type, String contentName )
    {
        ofy().delete().type( type ).id( contentName );
    }
}
