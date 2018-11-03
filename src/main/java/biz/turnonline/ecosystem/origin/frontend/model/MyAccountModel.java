package biz.turnonline.ecosystem.origin.frontend.model;

import biz.turnonline.ecosystem.origin.frontend.FrontendSession;
import biz.turnonline.ecosystem.steward.model.Account;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * Dedicated loadable model that retrieves logged in {@link Account} from the session.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class MyAccountModel
        extends LoadableDetachableModel<Account>
{
    private static final long serialVersionUID = 1L;

    @Override
    protected Account load()
    {
        return FrontendSession.get().getLoggedInUser();
    }

    @Override
    public void detach()
    {
        Account account = super.getObject();
        FrontendSession.get().updateLoggedInUser( account );
        super.detach();
    }
}
