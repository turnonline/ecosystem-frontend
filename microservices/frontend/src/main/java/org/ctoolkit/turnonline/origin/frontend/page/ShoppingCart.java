package org.ctoolkit.turnonline.origin.frontend.page;

import org.apache.wicket.model.IModel;
import org.ctoolkit.wicket.standard.model.I18NResourceModel;
import org.ctoolkit.wicket.turnonline.markup.html.page.DecoratedPage;

/**
 * The default shopping cart page.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class ShoppingCart<T>
        extends DecoratedPage<T>
{
    private static final long serialVersionUID = 3575980225582351217L;

    private I18NResourceModel titleModel = new I18NResourceModel( "title.shopping-cart" );

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
