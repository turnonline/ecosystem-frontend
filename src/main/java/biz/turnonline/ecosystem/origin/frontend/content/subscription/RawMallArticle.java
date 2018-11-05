package biz.turnonline.ecosystem.origin.frontend.content.subscription;

import biz.turnonline.ecosystem.origin.frontend.content.model.MallArticle;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

/**
 * The {@link MallArticle} content holder.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
@Entity( name = "FE_RawMallArticle" )
public class RawMallArticle
        extends BaseRawContent<MallArticle>
{
    private static final long serialVersionUID = 1153390593465885162L;

    @Index
    private String owner;

    @SuppressWarnings( "unused" )
    RawMallArticle()
    {
        super( MallArticle.class );
    }

    public RawMallArticle( String name )
    {
        super( name, MallArticle.class );
        this.owner = getOwnerFromName();
    }

    /**
     * Returns the domain as an owner of the article.
     *
     * @return the domain as an owner
     */
    public String getOwner()
    {
        return owner;
    }
}
