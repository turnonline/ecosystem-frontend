package biz.turnonline.ecosystem.origin.frontend.content.subscription;

import biz.turnonline.ecosystem.origin.frontend.content.model.ProductContent;
import com.googlecode.objectify.annotation.Entity;

/**
 * The {@link ProductContent} content holder.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
@Entity( name = "FE_RawProductContent" )
public class RawProductContent
        extends BaseRawContent<ProductContent>
{
    private static final long serialVersionUID = 7391612813511944865L;

    @SuppressWarnings( "unused" )
    RawProductContent()
    {
        super( ProductContent.class );
    }

    public RawProductContent( String name )
    {
        super( name, ProductContent.class );
    }
}
