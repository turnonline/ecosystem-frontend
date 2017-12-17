package org.ctoolkit.turnonline.origin.frontend.model;

import biz.turnonline.ecosystem.account.client.model.Account;
import org.apache.wicket.model.LoadableDetachableModel;
import org.ctoolkit.turnonline.origin.frontend.FrontendSession;

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
