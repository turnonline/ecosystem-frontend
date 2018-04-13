package biz.turnonline.ecosystem.origin.frontend.page;

import biz.turnonline.ecosystem.origin.frontend.FrontendApplication;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.ctoolkit.wicket.turnonline.markup.html.page.BaseSiteMap;
import org.ctoolkit.wicket.turnonline.markup.html.page.SiteMapItem;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class SiteMap
        extends BaseSiteMap
{
    private static final long serialVersionUID = 1L;

    @Override
    protected List<SiteMapItem> getItems()
    {
        HttpServletRequest request = ( ( ServletWebRequest ) getRequest() ).getContainerRequest();
        String website = request.getRequestURL().toString().replaceAll( request.getRequestURI(), "" );

        List<SiteMapItem> items = new ArrayList<>();

        items.add( new SiteMapItem( website ) );
        items.add( new SiteMapItem( website + FrontendApplication.SIGNUP ) );
        items.add( new SiteMapItem( website + FrontendApplication.LOGIN ) );

        return items;
    }
}
