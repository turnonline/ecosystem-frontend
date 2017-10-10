package org.ctoolkit.turnonline.origin.frontend.model;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.ctoolkit.turnonline.origin.frontend.FrontendSession;
import org.ctoolkit.turnonline.origin.frontend.identity.AuthenticatedUser;
import org.ctoolkit.wicket.standard.util.WicketUtil;
import org.ctoolkit.wicket.turnonline.model.IModelFactory;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Dedicated loadable model that retrieves {@link AuthenticatedUser} wrapped as {@link MyAccountContent}.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class AuthenticatedMyAccountContent
        extends LoadableDetachableModel<MyAccountContent>
{
    private static final long serialVersionUID = 1L;

    @Inject
    private IModelFactory factory;

    public AuthenticatedMyAccountContent()
    {
        Injector.get().inject( this );
    }

    @Override
    protected MyAccountContent load()
    {
        AuthenticatedUser account = FrontendSession.get().getLoggedInUser();

        checkNotNull( account );

        String originRequest = factory.getCountryOriginRequest( WicketUtil.getHttpServletRequest() );

        MyAccountContent.Builder builder = new MyAccountContent.Builder();

        builder.setAccount( account );
        builder.setGateways( account.getPaymentGateways() );
        builder.setCurrentCountry( originRequest );

        return new MyAccountContent( builder );
    }

    @Override
    public void detach()
    {
        MyAccountContent props = super.getObject();
        FrontendSession.get().updateLoggedInUser( props.getAccount() );
        super.detach();
    }
}
