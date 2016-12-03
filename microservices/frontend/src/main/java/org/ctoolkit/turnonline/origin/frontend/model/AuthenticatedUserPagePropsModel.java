package org.ctoolkit.turnonline.origin.frontend.model;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.ctoolkit.turnonline.origin.frontend.FrontendSession;
import org.ctoolkit.turnonline.origin.frontend.identity.AuthenticatedUser;
import org.ctoolkit.turnonline.shared.feprops.MyAccountProps;
import org.ctoolkit.wicket.standard.util.WicketUtil;
import org.ctoolkit.wicket.turnonline.model.IModelFactory;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Dedicated loadable model that retrieves {@link AuthenticatedUser} wrapped as {@link MyAccountProps}.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class AuthenticatedUserPagePropsModel
        extends LoadableDetachableModel<MyAccountProps>
{
    private static final long serialVersionUID = 1L;

    @Inject
    private IModelFactory factory;

    public AuthenticatedUserPagePropsModel()
    {
        Injector.get().inject( this );
    }

    @Override
    protected MyAccountProps load()
    {
        AuthenticatedUser account = FrontendSession.get().getLoggedInUser();

        checkNotNull( account );

        String originRequest = factory.getCountryOriginRequest( WicketUtil.getHttpServletRequest() );

        MyAccountProps.Builder builder = new MyAccountProps.Builder();

        builder.setAccount( account );
        builder.setCurrentCountry( originRequest );

        return new MyAccountProps( builder );
    }

    @Override
    public void detach()
    {
        MyAccountProps props = super.getObject();
        FrontendSession.get().updateLoggedInUser( props.getAccount() );
        super.detach();
    }
}
