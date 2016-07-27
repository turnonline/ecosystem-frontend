package org.ctoolkit.turnonline.origin.frontend.page;

import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.ctoolkit.turnonline.wicket.markup.html.page.BaseSiteMap;
import org.ctoolkit.turnonline.wicket.markup.html.page.SiteMapItem;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class SiteMap
        extends BaseSiteMap
{
    private static final long serialVersionUID = 1L;

    @Override
    protected List<SiteMapItem> getItems()
    {
        HttpServletRequest request = ( ( ServletWebRequest ) getRequest() ).getContainerRequest();
        String website = request.getRequestURL().toString().replaceAll( request.getRequestURI(), "" ) + "/";

        List<SiteMapItem> items = new ArrayList<>();

        items.add( new SiteMapItem( website ) );
        items.add( new SiteMapItem( website + "sign-up" ) );
        items.add( new SiteMapItem( website + "login" ) );

        return items;
    }
}
