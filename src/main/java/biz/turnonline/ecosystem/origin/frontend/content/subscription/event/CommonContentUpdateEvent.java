package biz.turnonline.ecosystem.origin.frontend.content.subscription.event;

import biz.turnonline.ecosystem.origin.frontend.content.model.CommonContent;

/**
 * Dedicated {@link CommonContent} update event.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class CommonContentUpdateEvent
        extends BaseContentUpdateEvent
{
    public CommonContentUpdateEvent( String name )
    {
        super( name );
    }
}
