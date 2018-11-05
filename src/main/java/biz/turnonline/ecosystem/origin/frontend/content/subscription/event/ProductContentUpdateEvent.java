package biz.turnonline.ecosystem.origin.frontend.content.subscription.event;

import biz.turnonline.ecosystem.origin.frontend.content.model.ProductContent;

/**
 * Dedicated {@link ProductContent} update event.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class ProductContentUpdateEvent
        extends BaseContentUpdateEvent
{
    public ProductContentUpdateEvent( String name )
    {
        super( name );
    }
}
