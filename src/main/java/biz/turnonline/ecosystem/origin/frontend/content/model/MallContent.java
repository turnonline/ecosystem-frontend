package biz.turnonline.ecosystem.origin.frontend.content.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Mall model object wrapping {@link MallArticle} items.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class MallContent
        extends CommonContent
{
    private static final long serialVersionUID = 8757810421351178645L;

    private List<MallArticle> articles;

    public List<MallArticle> getArticles()
    {
        if ( articles == null )
        {
            articles = new ArrayList<>();
        }
        Collections.sort( articles );
        return articles;
    }

    public void add( MallArticle article )
    {
        if ( articles == null )
        {
            articles = new ArrayList<>();
        }
        articles.add( article );
    }
}
