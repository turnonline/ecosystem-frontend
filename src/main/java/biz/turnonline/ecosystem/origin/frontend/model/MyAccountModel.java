package biz.turnonline.ecosystem.origin.frontend.model;

import biz.turnonline.ecosystem.origin.frontend.FrontendSession;
import biz.turnonline.ecosystem.origin.frontend.identity.AccountProfile;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * Dedicated loadable model that retrieves logged in {@link AccountProfile} from the session.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class MyAccountModel
        extends LoadableDetachableModel<AccountProfile>
{
    private static final long serialVersionUID = 1L;

    @Override
    protected AccountProfile load()
    {
        return FrontendSession.get().getLoggedInUser();
    }

    @Override
    public void detach()
    {
        AccountProfile account = super.getObject();
        FrontendSession.get().updateLoggedInUser( account );
        super.detach();
    }
}
