package biz.turnonline.ecosystem.origin.frontend.content.subscription;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.nio.charset.Charset;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Content subscription listener processing and persisting incoming content of known type.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
@Singleton
class ContentSubscription
        extends AbstractContentSubscription
{
    static final String DATA_TYPE = "DataType";

    static final String CONTENT_UNIQUE_NAME = "ContentUniqueName";

    static final String CONTENT_LOCALE = "ContentLocale";

    private static final long serialVersionUID = -7399244661971846809L;

    private static final Logger logger = LoggerFactory.getLogger( ContentSubscription.class );

    private final EventBus bus;

    @Inject
    ContentSubscription( @biz.turnonline.ecosystem.origin.frontend.content.ContentSubscription EventBus bus,
                         ContentNaming naming )
    {
        super( naming );
        this.bus = bus;
    }

    @Override
    public void onMessage( @Nonnull String dataType,
                           @Nonnull String contentName,
                           @Nonnull String data,
                           @Nonnull String subscription,
                           @Nonnull String receivedContentName,
                           @Nullable String receivedLocale )
            throws Exception
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

    void save( BaseRawContent content )
    {
        ofy().save().entity( content ).now();
    }

    <T> T load( Class<T> type, String contentName )
    {
        return ofy().load().type( type ).id( contentName ).now();
    }
}
