package biz.turnonline.ecosystem.origin.frontend.content.subscription.event;

import biz.turnonline.ecosystem.origin.frontend.content.model.EventContent;

/**
 * Dedicated {@link EventContent} update event.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class EventContentUpdateEvent
        extends BaseContentUpdateEvent
{
    public EventContentUpdateEvent( String name )
    {
        super( name );
    }
}
