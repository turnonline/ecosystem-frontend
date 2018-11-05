package biz.turnonline.ecosystem.origin.frontend.content.subscription.event;

import biz.turnonline.ecosystem.origin.frontend.content.model.MallArticle;

/**
 * Dedicated {@link MallArticle} update event.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class MallArticleUpdateEvent
        extends BaseContentUpdateEvent
{
    public MallArticleUpdateEvent( String name )
    {
        super( name );
    }
}
