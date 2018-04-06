package biz.turnonline.ecosystem.origin.frontend.page;

import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.ctoolkit.wicket.turnonline.markup.html.page.BaseRobots;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class Robots
        extends BaseRobots
{
    private static final long serialVersionUID = 1L;

    @Override
    protected String getUserAgent()
    {
        return "*";
    }

    @Override
    protected List<String> getDisallowUrls()
    {
        return new ArrayList<>();
    }

    @Override
    protected String getSiteMapUrl()
    {
        HttpServletRequest request = ( ( ServletWebRequest ) getRequest() ).getContainerRequest();
        return request.getRequestURL().toString().replaceAll( request.getRequestURI(), "" ) + "/sitemap.xml";
    }
}
