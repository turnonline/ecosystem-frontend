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

package biz.turnonline.ecosystem.origin.frontend.page;

import org.apache.wicket.protocol.https.RequireHttps;
import org.ctoolkit.wicket.standard.gwt.GwtLocaleModel;
import org.ctoolkit.wicket.standard.gwt.GwtScriptAppender;

/**
 * The wicket single page GWT application (array of modules) HTML wrapper.
 * The HTML page expects the GWT app will be added to the div tag with id 'gwt-content', see:
 * <p>
 * {@code <div id="gwt-content">}
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
@RequireHttps
public class GwtWidget
        extends DecoratedPage
{
    private static final long serialVersionUID = 1L;

    public GwtWidget( Builder builder )
    {
        GwtScriptAppender appender = new GwtScriptAppender( new GwtLocaleModel( this ), builder.source );
        appender.setWebComponentsImportPrefix( builder.prefix );

        add( appender );
    }

    public static class Builder
    {
        private String prefix;

        private String[] source;

        private Builder()
        {
        }

        public static Builder builder()
        {
            return new Builder();
        }

        /**
         * Sets the javascript GWT app path prefix.
         * If set, the standard GWT generated path to the web components javascript
         * will be appended to be added as a head scrip tag.
         *
         * @param prefix the GWT app path prefix to be set
         * @return the builder to chain
         */
        public Builder setPrefix( String prefix )
        {
            this.prefix = prefix;
            return this;
        }

        /**
         * The array of the GWT widget source paths, relative to the webapp folder.
         *
         * @param source the array of GWT source paths
         * @return the builder to chain
         */
        public Builder setSource( String... source )
        {
            this.source = source;
            return this;
        }
    }
}
