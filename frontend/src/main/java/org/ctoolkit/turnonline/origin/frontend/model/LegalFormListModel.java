package org.ctoolkit.turnonline.origin.frontend.model;

import biz.turnonline.ecosystem.account.client.model.LegalForm;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.ctoolkit.restapi.client.RestFacade;
import org.ctoolkit.turnonline.origin.frontend.FrontendSession;

import javax.inject.Inject;
import java.util.List;
import java.util.Locale;

/**
 * Abstract list model dedicated for all {@link LegalForm}. It retrieves code book list via {@link RestFacade}.
 * The {@link Locale} and domicile is being taken from the session.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 * @see FrontendSession#get().getSellerLocale()
 * @see FrontendSession#get().getSellerDomicile()
 */
public class LegalFormListModel
        extends LoadableDetachableModel<List<LegalForm>>
{
    private static final long serialVersionUID = 1L;

    @Inject
    private RestFacade resources;

    public LegalFormListModel()
    {
        Injector.get().inject( this );
    }

    @Override
    protected List<LegalForm> load()
    {
        Locale locale = FrontendSession.get().getLocale();
        return resources.list( LegalForm.class ).finish( locale );
    }
}
