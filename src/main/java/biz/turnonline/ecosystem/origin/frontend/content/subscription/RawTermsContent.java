package biz.turnonline.ecosystem.origin.frontend.content.subscription;

import biz.turnonline.ecosystem.origin.frontend.content.model.TermsContent;
import com.googlecode.objectify.annotation.Entity;

/**
 * The {@link TermsContent} content holder.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
@Entity( name = "FE_RawTermsContent" )
public class RawTermsContent
        extends BaseRawContent<TermsContent>
{
    private static final long serialVersionUID = -578872369737132699L;

    @SuppressWarnings( "unused" )
    RawTermsContent()
    {
        super( TermsContent.class );
    }

    public RawTermsContent( String name )
    {
        super( name, TermsContent.class );
    }
}
