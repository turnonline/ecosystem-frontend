package biz.turnonline.ecosystem.origin.frontend.content.subscription.adaptee;

import biz.turnonline.ecosystem.origin.frontend.content.ContentSubscription;
import biz.turnonline.ecosystem.origin.frontend.content.TurnOnlineClient;
import biz.turnonline.ecosystem.origin.frontend.content.model.TermsContent;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.ContentNaming;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.RawTermsContent;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.event.TermsContentUpdateEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import net.sf.jsr107cache.Cache;
import org.ctoolkit.restapi.client.Identifier;
import org.ctoolkit.restapi.client.NotFoundException;
import org.ctoolkit.restapi.client.adaptee.GetExecutorAdaptee;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * {@link TermsContent} local adaptee implementation.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
@Singleton
public class TermsContentLocalAdaptee
        extends GetExecutorCachedContentAdaptee<TermsContent>
        implements GetExecutorAdaptee<TermsContent>
{
    private final ObjectMapper mapper;

    @Inject
    public TermsContentLocalAdaptee( @TurnOnlineClient Cache cache,
                                     @ContentSubscription ObjectMapper mapper,
                                     @ContentSubscription EventBus bus,
                                     ContentNaming naming )
    {
        super( cache, naming );
        this.mapper = mapper;
        bus.register( this );
    }

    @Override
    public TermsContent executeGet( @Nonnull Object request,
                                    @Nullable Map<String, Object> parameters,
                                    @Nullable Locale locale )
            throws IOException
    {
        Identifier identifier = ( Identifier ) request;
        String name = composeKey( identifier, parameters, locale );
        RawTermsContent raw = ofy().load().type( RawTermsContent.class ).id( name ).now();
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
        return naming().composeFullName( TermsContent.class, identifier, locale );
    }

    @Subscribe
    public void updateCache( TermsContentUpdateEvent event )
    {
        removeFromCache( event.getName() );
    }
}
