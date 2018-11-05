package biz.turnonline.ecosystem.origin.frontend.content.subscription;

import biz.turnonline.ecosystem.origin.frontend.content.model.EventContent;
import com.googlecode.objectify.annotation.Entity;

/**
 * The {@link EventContent} content holder.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
@Entity( name = "FE_RawEventContent" )
public class RawEventContent
        extends BaseRawContent<EventContent>
{
    private static final long serialVersionUID = -6671018182238784584L;

    @SuppressWarnings( "unused" )
    RawEventContent()
    {
        super( EventContent.class );
    }

    public RawEventContent( String name )
    {
        super( name, EventContent.class );
    }
}
