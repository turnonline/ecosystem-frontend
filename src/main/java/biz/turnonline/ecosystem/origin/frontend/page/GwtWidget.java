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

import biz.turnonline.ecosystem.origin.frontend.identity.Role;
import biz.turnonline.ecosystem.origin.frontend.model.MyAccountModel;
import biz.turnonline.ecosystem.origin.frontend.steward.Account;
import biz.turnonline.ecosystem.origin.frontend.steward.AccountBusiness;
import biz.turnonline.ecosystem.origin.frontend.steward.InvoicingConfig;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.protocol.https.RequireHttps;
import org.ctoolkit.wicket.standard.gwt.BootConfiguration;
import org.ctoolkit.wicket.standard.gwt.GwtLocaleModel;
import org.ctoolkit.wicket.standard.gwt.GwtScriptAppender;
import org.ctoolkit.wicket.standard.identity.FirebaseConfig;
import org.ctoolkit.wicket.standard.identity.behavior.FirebaseAppInit;
import org.ctoolkit.wicket.turnonline.markup.html.page.Skeleton;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static biz.turnonline.ecosystem.origin.frontend.FrontendSession.DEFAULT_SESSION_CURRENCY;
import static biz.turnonline.ecosystem.origin.frontend.FrontendSession.DEFAULT_SESSION_DOMICILE;
import static com.google.common.base.Strings.isNullOrEmpty;
import static org.apache.http.util.Args.notNull;

/**
 * The wicket single page GWT application (array of modules) HTML wrapper.
 * The HTML page expects the GWT app will be added to dedicated DIV tag:
 * <p>
 * {@code <div id="widget-content">}
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
@RequireHttps
@AuthorizeInstantiation( Role.STANDARD )
class GwtWidget
        extends Skeleton<Account>
{
    private static final long serialVersionUID = 1L;

    private final Builder builder;

    @Inject
    private FirebaseConfig firebaseConfig;

    GwtWidget( @Nonnull Builder builder )
    {
        super( new MyAccountModel() );
        this.builder = notNull( builder, "builder" );
        add( new FirebaseAppInit( firebaseConfig, false ) );

        BootConfiguration configuration = new BootConfiguration( GwtWidget.class )
        {
            private static final long serialVersionUID = -1438242135395325801L;

            @Override
            protected Map<String, Object> getArgs()
            {
                Account account = getModelObject();

                String domicile = null;
                AccountBusiness business = account.getBusiness();
                if ( business != null )
                {
                    domicile = business.getDomicile();
                }

                String currency = null;
                InvoicingConfig invoicing = account.getInvoicing();
                if ( invoicing != null )
                {
                    currency = invoicing.getCurrency();
                }

                Map<String, Object> args = new HashMap<>();
                args.put( "LOGIN_ID", notNull( account.getId(), "Signed up Account.ID" ) );
                args.put( "DOMICILE", isNullOrEmpty( domicile ) ? DEFAULT_SESSION_DOMICILE : domicile );
                args.put( "CURRENCY", isNullOrEmpty( currency ) ? DEFAULT_SESSION_CURRENCY : currency );

                return args;
            }
        };

        add( configuration );
    }

    @Override
    protected void onInitialize()
    {
        super.onInitialize();

        // container for firebase JavaScripts - must be located in html at body bottom
        add( new HeaderResponseContainer( "html-bottom-container", HTML_BOTTOM_FILTER_NAME ) );

        WebMarkupContainer mainContent = new WebMarkupContainer( "widget-content" );
        mainContent.setMarkupId( sectionId() );
        add( mainContent );

        RepeatingView modules = new RepeatingView( "modules" );
        for ( String name : additionalModuleNames() )
        {
            WebMarkupContainer div = new WebMarkupContainer( modules.newChildId() );
            div.setMarkupId( name );
            modules.add( div );
        }
        add( modules );
        modules.setRenderBodyOnly( true );

        // GWT source attribute rendering
        GwtScriptAppender appender = new GwtScriptAppender( new GwtLocaleModel( this ), builder.source );
        appender.setWebComponentsImportPrefix( builder.prefix );

        add( appender );
    }

    /**
     * Returns the DIV tag id as a widget content name, hardcoded root element expected by widget implementation.
     *
     * @return the widget content DIV tag id
     */
    protected String sectionId()
    {
        return "widget-content";
    }

    /**
     * Returns the arrays of tag div id's to be rendered as addition to the widget module {@link #sectionId()}.
     * The array size (including {@link #sectionId()}) must be in sync with constructor source array size.
     *
     * @return the array of tag div id's
     */
    protected String[] additionalModuleNames()
    {
        return new String[0];
    }

    public static class Builder
            implements Serializable
    {
        private static final long serialVersionUID = 8038119840215782939L;

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
        public Builder prefix( String prefix )
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
        public Builder source( String... source )
        {
            this.source = source;
            return this;
        }
    }
}
