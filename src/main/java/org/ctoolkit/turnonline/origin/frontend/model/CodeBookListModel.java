package org.ctoolkit.turnonline.origin.frontend.model;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.ctoolkit.restapi.client.RestFacade;
import org.ctoolkit.turnonline.origin.frontend.FrontendSession;
import org.ctoolkit.turnonline.shared.resource.BaseCodeBook;
import org.ctoolkit.turnonline.shared.resource.Domicile;
import org.ctoolkit.turnonline.shared.resource.Locale;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;

/**
 * Abstract list model dedicated for all {@link BaseCodeBook}. It retrieves code book list via {@link RestFacade}.
 * The {@link Locale} and {@link Domicile} is being taken from the session.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 * @see FrontendSession#get().getSellerLocale()
 * @see FrontendSession#get().getSellerDomicile()
 */
public abstract class CodeBookListModel<T extends BaseCodeBook>
        extends LoadableDetachableModel<List<T>>
{
    private static final long serialVersionUID = 1L;

    @Inject
    private RestFacade resources;

    public CodeBookListModel()
    {
        Injector.get().inject( this );
    }

    protected abstract Class<T> type();

    @Override
    protected List<T> load()
    {
        Locale l = FrontendSession.get().getSellerLocale();
        Domicile domicile = FrontendSession.get().getSellerDomicile();

        HashMap<String, Object> criteria = new HashMap<>();
        criteria.put( "domicile", domicile );

        java.util.Locale locale = new java.util.Locale( l.getLanguage(), l.getCountry() );
        return resources.list( type() ).finish( criteria, locale );
    }
}
