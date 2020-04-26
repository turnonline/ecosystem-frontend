/*
 * Copyright 2020 TurnOnline.biz s.r.o.
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

import biz.turnonline.ecosystem.origin.frontend.component.SplashScreen;
import biz.turnonline.ecosystem.origin.frontend.identity.Role;
import biz.turnonline.ecosystem.origin.frontend.model.MyAccountModel;
import biz.turnonline.ecosystem.origin.frontend.steward.Account;
import biz.turnonline.ecosystem.origin.frontend.steward.AccountBusiness;
import biz.turnonline.ecosystem.origin.frontend.steward.InvoicingConfig;
import org.apache.wicket.Component;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.MetaDataHeaderItem;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.https.RequireHttps;
import org.ctoolkit.wicket.standard.gwt.BootConfiguration;
import org.ctoolkit.wicket.standard.gwt.GwtLocaleModel;
import org.ctoolkit.wicket.standard.identity.FirebaseConfig;
import org.ctoolkit.wicket.standard.identity.behavior.FirebaseAppInit;
import org.ctoolkit.wicket.turnonline.markup.html.page.Skeleton;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Named;

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
    private static final long serialVersionUID = 2774707310499563827L;

    @Inject
    private FirebaseConfig firebaseConfig;

    @Inject
    @Named( "account.steward.storage" )
    private String accountStewardStorage;

    @Inject
    @Named( "product.billing.storage" )
    private String productBillingStorage;

    @Inject
    @Named( "billing.processor.storage" )
    private String billingProcessorStorage;

    @Inject
    @Named( "account.steward.api.root" )
    private String accountStewardApi;

    @Inject
    @Named( "product.billing.api.root" )
    private String productBillingApi;

    @Inject
    @Named( "payment.processor.api.root" )
    private String paymentProcessorApi;

    @Inject
    @Named( "billing.processor.api.root" )
    private String billingProcessorApi;

    @Inject
    @Named( "search.api.root" )
    private String searchApi;

    @Inject
    @Named( "maps.api.key" )
    private String mapsApiKey;

    /**
     * The array of the GWT widget source paths, relative to the webapp folder.
     *
     * @param source the array of GWT source paths
     */
    GwtWidget( @Nonnull String source )
    {
        super( new MyAccountModel() );

        BootConfiguration configuration = new BootConfiguration( GwtWidget.class );
        configuration.arguments( ( BootConfiguration.Arguments ) arguments -> {
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

            arguments.put( "LOGIN_ID", notNull( account.getId(), "Signed up Account.ID" ) );
            arguments.put( "DOMICILE", isNullOrEmpty( domicile ) ? DEFAULT_SESSION_DOMICILE : domicile );
            arguments.put( "CURRENCY", isNullOrEmpty( currency ) ? DEFAULT_SESSION_CURRENCY : currency );
            arguments.put( "ACCOUNT_STEWARD_STORAGE", accountStewardStorage );
            arguments.put( "PRODUCT_BILLING_STORAGE", productBillingStorage );
            arguments.put( "BILLING_PROCESSOR_STORAGE", billingProcessorStorage );
            arguments.put( "ACCOUNT_STEWARD_API_ROOT", accountStewardApi );
            arguments.put( "PRODUCT_BILLING_API_ROOT", productBillingApi );
            arguments.put( "BILLING_PROCESSOR_API_ROOT", billingProcessorApi );
            arguments.put( "PAYMENT_PROCESSOR_API_ROOT", paymentProcessorApi );
            arguments.put( "SEARCH_API_ROOT", searchApi );
            arguments.put( "MAPS_API_KEY", mapsApiKey );

            if ( account.getBusiness() != null &&
                    account.getBusiness().getLogo() != null &&
                    account.getBusiness().getLogo().getServingUrl() != null )
            {
                arguments.put( "LOGO", account.getBusiness().getLogo().getServingUrl() );
            }
        } );

        add( configuration );

        FirebaseAppInit firebaseInit = new FirebaseAppInit( firebaseConfig )
                .fileName( GwtWidget.class, "GwtInit.js" )
                .fileNameArguments( arg -> arg.put( "gwt.src", source ) );

        add( firebaseInit );

        add( new SplashScreen( "splash-screen" ) );
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

        add( new GwtLocale() );
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

    private class GwtLocale
            extends Behavior
    {
        private static final long serialVersionUID = 1L;

        private IModel<String> localeModel = new GwtLocaleModel( GwtWidget.this );

        @Override
        public void renderHead( Component component, IHeaderResponse response )
        {
            final IModel<?> model = component.getDefaultModel();

            if ( model != null )
            {
                response.render( MetaDataHeaderItem.forMetaTag( new Model<>( "gwt:property" ), localeModel ) );
            }
        }

        @Override
        public boolean isTemporary( Component component )
        {
            return true;
        }
    }
}
