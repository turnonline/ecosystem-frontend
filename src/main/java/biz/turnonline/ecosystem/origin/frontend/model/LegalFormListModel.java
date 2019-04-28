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
import biz.turnonline.ecosystem.origin.frontend.steward.LegalForm;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.ctoolkit.restapi.client.RestFacade;

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
