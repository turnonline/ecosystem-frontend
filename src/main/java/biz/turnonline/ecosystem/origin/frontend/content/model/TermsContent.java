package biz.turnonline.ecosystem.origin.frontend.content.model;

/**
 * The terms and conditions content contract.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class TermsContent
        extends PublicContent
{
    private static final long serialVersionUID = -2170922238755755399L;

    private String fullTerms;

    public TermsContent()
    {
    }

    public TermsContent( String name )
    {
        super( name );
    }

    public String getFullTerms()
    {
        return fullTerms;
    }

    public void setFullTerms( String fullTerms )
    {
        this.fullTerms = fullTerms;
    }
}
