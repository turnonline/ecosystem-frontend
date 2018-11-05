/*
 * Copyright (c) 2017 Comvai, s.r.o. All Rights Reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
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
