package biz.turnonline.ecosystem.origin.frontend.content.subscription.event;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The base content update event.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
class BaseContentUpdateEvent
{
    private final String name;

    BaseContentUpdateEvent( @Nonnull String name )
    {
        this.name = checkNotNull( name );
    }

    /**
     * Returns the content identification.
     *
     * @return the content name
     */
    public String getName()
    {
        return name;
    }
}
