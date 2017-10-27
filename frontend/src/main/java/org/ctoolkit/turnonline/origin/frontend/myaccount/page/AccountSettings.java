package org.ctoolkit.turnonline.origin.frontend.myaccount.page;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.model.IModel;
import org.ctoolkit.turnonline.origin.frontend.identity.Role;
import org.ctoolkit.wicket.standard.identity.behavior.FirebaseAppInit;
import org.ctoolkit.wicket.standard.model.I18NResourceModel;
import org.ctoolkit.wicket.turnonline.identity.IdentityOptions;
import org.ctoolkit.wicket.turnonline.markup.html.page.DecoratedPage;

import javax.inject.Inject;

/**
 * The page dedicated for advanced account settings.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
@AuthorizeInstantiation( Role.ACCOUNT )
public class AccountSettings<T>
        extends DecoratedPage<T>
{
    private static final long serialVersionUID = 8259598575896609246L;

    private I18NResourceModel titleModel = new I18NResourceModel( "title.my-account" );

    @Inject
    private IdentityOptions identityOptions;

    public AccountSettings()
    {
        add( new FirebaseAppInit( identityOptions ) );
    }

    @Override
    protected IModel<?> getPageH1Header()
    {
        return getPageTitle();
    }

    @Override
    public IModel<String> getPageTitle()
    {
        return titleModel;
    }
}
