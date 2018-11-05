package biz.turnonline.ecosystem.origin.frontend.content.subscription;

import biz.turnonline.ecosystem.origin.frontend.content.model.PayInvoiceContent;
import com.googlecode.objectify.annotation.Entity;

/**
 * The {@link PayInvoiceContent} content holder.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
@Entity( name = "FE_RawPayInvoiceContent" )
public class RawPayInvoiceContent
        extends BaseRawContent<PayInvoiceContent>
{
    private static final long serialVersionUID = 3072593841883949708L;

    @SuppressWarnings( "unused" )
    RawPayInvoiceContent()
    {
        super( PayInvoiceContent.class );
    }

    public RawPayInvoiceContent( String name )
    {
        super( name, PayInvoiceContent.class );
    }
}
