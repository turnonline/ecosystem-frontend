package org.ctoolkit.turnonline.origin.frontend.model;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.ctoolkit.restapi.client.ResourceFacade;
import org.ctoolkit.turnonline.origin.frontend.FrontendSession;
import org.ctoolkit.turnonline.shared.resource.Country;
import org.ctoolkit.turnonline.shared.resource.Locale;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Dedicated stateless model that retrieves map of countries as <code>Map<String, Country></code> code->country.
 * Locale based on the seller's locale.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class CountriesModel
        extends LoadableDetachableModel<Map<String, Country>>
{
    private static final long serialVersionUID = 1L;

    @Inject
    private ResourceFacade resources;

    /**
     * Constructor.
     */
    public CountriesModel()
    {
        Injector.get().inject( this );
    }

    @Override
    protected Map<String, Country> load()
    {
        Map<String, Country> countries = new HashMap<>();

        Locale locale = FrontendSession.get().getSellerLocale();

        java.util.Locale utilLocale = new java.util.Locale( locale.getLanguage(), locale.getCountry() );
        for ( Country item : resources.list( Country.class ).execute( utilLocale ) )
        {
            countries.put( item.getCode(), item );
        }

        return countries;
    }
}
