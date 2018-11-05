package biz.turnonline.ecosystem.origin.frontend.content.subscription;

import biz.turnonline.ecosystem.origin.frontend.content.model.CommonContent;
import com.googlecode.objectify.annotation.Entity;

/**
 * The {@link CommonContent} content holder.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
@Entity( name = "FE_RawCommonContent" )
public class RawCommonContent
        extends BaseRawContent<CommonContent>
{
    private static final long serialVersionUID = -3004729486459772962L;

    @SuppressWarnings( "unused" )
    RawCommonContent()
    {
        super( CommonContent.class );
    }

    public RawCommonContent( String name )
    {
        super( name, CommonContent.class );
    }
}
