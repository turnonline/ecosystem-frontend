/*
 * Copyright 2019 Comvai, s.r.o.
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

package biz.turnonline.ecosystem.origin.frontend;

import de.agilecoders.wicket.themes.markup.html.material_design.MaterialDesignTheme;
import org.apache.wicket.Application;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.resource.JQueryPluginResourceReference;
import org.apache.wicket.resource.bundles.ConcatResourceBundleReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Bootstrap material design related JavaScript resource reference bundle.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
class BootstrapScriptBundle
        extends ConcatResourceBundleReference<JavaScriptReferenceHeaderItem>
{
    static final String NAME = "bootstrap-material-bundle.js";

    private static final long serialVersionUID = 1L;

    BootstrapScriptBundle()
    {
        super( BootstrapScriptBundle.class, NAME, getResources() );

        if ( Application.exists() )
        {
            Application.get().getResourceReferenceRegistry().registerResourceReference( this );
        }
    }

    private static List<JavaScriptReferenceHeaderItem> getResources()
    {
        List<JavaScriptReferenceHeaderItem> references = new ArrayList<>();

        references.add( JavaScriptHeaderItem.forReference( new JQueryPluginResourceReference( MaterialDesignTheme.class, "js/ripples.js" ) ) );
        references.add( JavaScriptHeaderItem.forReference( new JQueryPluginResourceReference( MaterialDesignTheme.class, "js/material.js" ) ) );

        return references;
    }
}
