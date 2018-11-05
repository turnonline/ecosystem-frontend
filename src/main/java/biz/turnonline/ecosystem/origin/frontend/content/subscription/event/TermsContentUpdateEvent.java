package biz.turnonline.ecosystem.origin.frontend.content.subscription.event;

import biz.turnonline.ecosystem.origin.frontend.content.model.TermsContent;

/**
 * Dedicated {@link TermsContent} update event.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class TermsContentUpdateEvent
        extends BaseContentUpdateEvent
{
    public TermsContentUpdateEvent( String name )
    {
        super( name );
    }
}
