package biz.turnonline.ecosystem.origin.frontend.page;

import org.apache.wicket.request.flow.RedirectToUrlException;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.ctoolkit.restapi.client.firebase.IdentityHandler;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Logout page
 *
 * @author <a href="mailto:pohorelec@comvai.com">Jozef Pohorelec</a>
 */
public class Logout
        extends BasePage
{
    @Inject
    private IdentityHandler resolver;

    public Logout()
    {
        HttpServletRequest request = getContainerRequest();
        HttpServletResponse response = getContainerResponse();

        resolver.delete( request, response );
        request.getSession().invalidate();

        // redirect to login
        throw new RedirectToUrlException( urlFor( Login.class, new PageParameters() ).toString() );
    }
}
