package biz.turnonline.ecosystem.origin.frontend.content.subscription;

import biz.turnonline.ecosystem.origin.frontend.content.ContentSubscription;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.event.CommonContentUpdateEvent;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.event.EventContentUpdateEvent;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.event.MallArticleUpdateEvent;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.event.PayInvoiceContentUpdateEvent;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.event.ProductContentUpdateEvent;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.event.TermsContentUpdateEvent;
import com.google.common.base.Strings;
import com.google.common.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
@Singleton
class ContentDeleteSubscription
        extends AbstractContentSubscription
{
    private static final long serialVersionUID = -4664140205702937397L;

    private static final Logger logger = LoggerFactory.getLogger( ContentDeleteSubscription.class );

    private final EventBus bus;

    @Inject
    ContentDeleteSubscription( @ContentSubscription EventBus bus, ContentNaming naming )
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

    void delete( Class type, String contentName )
    {
        ofy().delete().type( type ).id( contentName );
    }
}
