package biz.turnonline.ecosystem.origin.frontend.content.subscription.adaptee;

import biz.turnonline.ecosystem.origin.frontend.content.TurnOnlineClient;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.ContentNaming;
import com.google.appengine.api.modules.ModulesService;
import com.google.appengine.api.modules.ModulesServiceFactory;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import net.sf.jsr107cache.Cache;
import org.ctoolkit.restapi.client.Identifier;
import org.ctoolkit.restapi.client.adaptee.GetExecutorAdaptee;
import org.ctoolkit.restapi.client.provider.LocalResourceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The base cached content GET adaptee implementation.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
abstract class GetExecutorCachedContentAdaptee<T>
        implements GetExecutorAdaptee<T>, LocalResourceProvider<T>
{
    private static final Logger logger = LoggerFactory.getLogger( GetExecutorCachedContentAdaptee.class );

    private final Cache cache;

    private final ContentNaming naming;

    private ModulesService modulesService = ModulesServiceFactory.getModulesService();

    GetExecutorCachedContentAdaptee( @TurnOnlineClient Cache cache, ContentNaming naming )
    {
        this.cache = cache;
        this.naming = naming;
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public final T get( @Nonnull Identifier identifier,
                        @Nullable Map<String, Object> parameters,
                        @Nullable Locale locale )
    {
        checkNotNull( identifier );

        String key = composeKey( identifier, parameters, locale );

        try
        {
            if ( !cache.containsKey( key ) )
            {
                return null;
            }

            return ( T ) cache.get( key );
        }
        catch ( Exception e )
        {
            logger.warn( "Retrieval of the resource with key: " + key + " has failed.", e );
            return null;
        }
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public final void persist( @Nonnull T instance,
                               @Nonnull Identifier identifier,
                               @Nullable Map<String, Object> parameters,
                               @Nullable Locale locale,
                               @Nullable Long lastFor )
    {
        checkNotNull( instance );
        checkNotNull( identifier );

        Object key = null;
        try
        {
            key = composeKey( identifier, parameters, locale );
            cache.put( key, instance );
            logger.info( instance.getClass().getSimpleName() + " has been cached with key: " + key );
        }
        catch ( Exception e )
        {
            logger.warn( "Caching of the value with key: " + key + " has failed.", e );
        }
    }

    @Override
    public Identifier prepareGet( @Nonnull Identifier identifier ) throws IOException
    {
        return identifier;
    }

    protected abstract String composeKey( @Nonnull Identifier identifier,
                                          @Nullable Map<String, Object> parameters,
                                          @Nullable Locale locale );

    /**
     * Removes the content identified by the name from the cache.
     *
     * @param contentName the full content name with incl. locale prefix
     */
    protected void removeFromCache( @Nonnull String contentName )
    {
        try
        {
            if ( cache.containsKey( contentName ) )
            {
                cache.remove( contentName );
                addTask( new RemoveContentCacheTask( contentName ) );
                logger.info( "Content name: '" + contentName + "' has been removed from the cache." );
            }
            else
            {
                logger.info( "Content name: '" + contentName + "' to be removed from the cache has not been found." );
            }
        }
        catch ( Exception e )
        {
            logger.warn( "Removing of the resource from the cache with key: '" + contentName + "' has failed.", e );
        }
    }

    protected ContentNaming naming()
    {
        return naming;
    }

    private void addTask( RemoveContentCacheTask task )
    {
        Queue queue = QueueFactory.getDefaultQueue();
        TaskOptions options = TaskOptions.Builder.withDefaults();

        String module = modulesService.getCurrentModule();
        String version = modulesService.getCurrentVersion();
        String hostname = modulesService.getVersionHostname( module, version );

        // postpone task execution by 3 minutes
        options.etaMillis( System.currentTimeMillis() + 180000L );
        // header added to make sure run against current module (even non default module)
        // see https://code.google.com/p/googleappengine/issues/detail?id=10457
        options.header( "Host", hostname );

        logger.info( "Enqueued in queue: " + queue.getQueueName() + ",  module: " + module + ", version: " + version
                + ", Module hostname: " + hostname );

        queue.add( options.payload( task ) );
    }
}
