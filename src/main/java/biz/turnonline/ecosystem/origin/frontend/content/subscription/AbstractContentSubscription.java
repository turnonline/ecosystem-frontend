package biz.turnonline.ecosystem.origin.frontend.content.subscription;

import com.google.api.services.pubsub.model.PubsubMessage;
import org.ctoolkit.restapi.client.pubsub.PubsubMessageListener;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static biz.turnonline.ecosystem.origin.frontend.content.subscription.ContentSubscription.CONTENT_LOCALE;
import static biz.turnonline.ecosystem.origin.frontend.content.subscription.ContentSubscription.CONTENT_UNIQUE_NAME;
import static biz.turnonline.ecosystem.origin.frontend.content.subscription.ContentSubscription.DATA_TYPE;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The base abstract subscription listener implementation
 * extracting attributes from the received message.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
abstract class AbstractContentSubscription
        implements PubsubMessageListener
{
    private static final long serialVersionUID = -5738883108697749503L;

    private final ContentNaming naming;

    /**
     * The constructor.
     *
     * @param naming the naming rules class instance
     */
    AbstractContentSubscription( @Nonnull ContentNaming naming )
    {
        this.naming = checkNotNull( naming );
    }

    @Override
    public void onMessage( @Nonnull PubsubMessage message, @Nonnull String subscription )
            throws Exception
    {
        String dataType = message.getAttributes().get( DATA_TYPE );
        String receivedContentName = message.getAttributes().get( CONTENT_UNIQUE_NAME );
        String receivedLocale = message.getAttributes().get( CONTENT_LOCALE );
        String data = message.getData();

        checkNotNull( dataType, DATA_TYPE + " attribute is mandatory and cannot be null." );
        checkNotNull( receivedContentName, CONTENT_UNIQUE_NAME + " attribute is mandatory and cannot be null." );

        String name = naming.prefixName( receivedContentName, receivedLocale );
        onMessage( dataType, name, data, subscription, receivedContentName, receivedLocale );
    }

    /**
     * The method called right after {@link #onMessage(PubsubMessage, String)}
     * mandatory and optional attributes has been extracted.
     *
     * @param dataType            the published content data type
     * @param contentName         the published content name incl locale (either received or default one)
     * @param data                the published Base64 encoded content data
     * @param subscription        the name of the subscription
     * @param receivedContentName the published optional locale of the content
     * @param receivedLocale      the published optional locale of the content
     * @throws Exception the exception thrown in case of failure
     */
    protected abstract void onMessage( @Nonnull String dataType,
                                       @Nonnull String contentName,
                                       @Nonnull String data,
                                       @Nonnull String subscription,
                                       @Nonnull String receivedContentName,
                                       @Nullable String receivedLocale )
            throws Exception;
}
