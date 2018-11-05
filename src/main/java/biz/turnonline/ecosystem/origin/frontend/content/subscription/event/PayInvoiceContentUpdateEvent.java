package biz.turnonline.ecosystem.origin.frontend.content.subscription.event;

import biz.turnonline.ecosystem.origin.frontend.content.model.PayInvoiceContent;

/**
 * Dedicated {@link PayInvoiceContent} update event.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class PayInvoiceContentUpdateEvent
        extends BaseContentUpdateEvent
{
    public PayInvoiceContentUpdateEvent( String name )
    {
        super( name );
    }
}
