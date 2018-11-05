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

import biz.turnonline.ecosystem.origin.frontend.content.TurnOnlineClient;
import com.google.appengine.api.taskqueue.DeferredTask;
import com.google.inject.Injector;
import net.sf.jsr107cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The asynchronous content removal from the memcache.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public final class RemoveContentCacheTask
        implements DeferredTask
{
    private static final long serialVersionUID = 4738735758950577315L;

    private static final Logger logger = LoggerFactory.getLogger( RemoveContentCacheTask.class );

    @Inject
    private static Injector injector;

    @Inject
    @TurnOnlineClient
    transient private Cache cache;

    private String contentName;

    RemoveContentCacheTask( String contentName )
    {
        this.contentName = checkNotNull( contentName );
    }

    @Override
    public void run()
    {
        injector.injectMembers( this );

        if ( cache.containsKey( contentName ) )
        {
            cache.remove( contentName );
            logger.info( "Postponed removal: content name: '" + contentName + "' has been removed from the cache." );
        }
        else
        {
            logger.info( "Postponed removal: Content name: '" + contentName + "' to be removed from the cache has not been found." );
        }
    }

    @Override
    public final boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        if ( !super.equals( o ) ) return false;
        RemoveContentCacheTask that = ( RemoveContentCacheTask ) o;
        return Objects.equals( contentName, that.contentName );
    }

    @Override
    public final int hashCode()
    {
        return Objects.hash( super.hashCode(), contentName );
    }
}
