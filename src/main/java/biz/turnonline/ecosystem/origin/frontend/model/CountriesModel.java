/*
 * Copyright 2018 Comvai, s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package biz.turnonline.ecosystem.origin.frontend.model;

import biz.turnonline.ecosystem.origin.frontend.FrontendSession;
import biz.turnonline.ecosystem.origin.frontend.steward.Country;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.ctoolkit.restapi.client.RestFacade;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Dedicated stateless model that retrieves map of countries as <code>Map<String, Country></code> code->country.
 * Locale based on the seller's locale.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class CountriesModel
        extends LoadableDetachableModel<Map<String, Country>>
{
    private static final long serialVersionUID = 1L;

    @Inject
    private RestFacade resources;

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
        Locale locale = FrontendSession.get().getLocale();

        for ( Country item : resources.list( Country.class ).finish( locale ) )
        {
            countries.put( item.getCode(), item );
        }

        return countries;
    }
}
