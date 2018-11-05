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

package biz.turnonline.ecosystem.origin.frontend.component;

import biz.turnonline.ecosystem.origin.frontend.FrontendApplication;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptContentHeaderItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.ctoolkit.wicket.standard.gwt.GwtScriptAppender;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * The GWT application HTML wrapper for search.widget
 *
 * @author <a href="mailto:pohorelec@turnonline.biz">Jozef Pohorelec</a>
 */
public class SearchPanel
        extends Panel
{
    private static final long serialVersionUID = 1L;

    private static final String GWT_SOURCE = "/search.widget/search.widget.nocache.js";

    @Inject
    @Named("credential.search.endpointUrl")
    private String searchApiEndpointUrl;

    public SearchPanel( String id )
    {
        super( id );
        FrontendApplication.get().getInjector().injectMembers( this );

        setRenderBodyOnly( true );
    }

    @Override
    protected void onInitialize()
    {
        super.onInitialize();

        // GWT source attribute and locale meta tag rendering
        getPage().add( new GwtScriptAppender( GWT_SOURCE ) );

        // add configuration for rest service profile
        add( new Behavior()
        {
            @Override
            public void renderHead( Component component, IHeaderResponse response )
            {
                String script = " var RestServiceProfile = {SEARCH_API_SERVICE_ROOT: '" + searchApiEndpointUrl + "'}";
                response.render( JavaScriptContentHeaderItem.forScript( script, "SearchWidgetRestServiceProfile" ) );
            }
        } );
    }
}
