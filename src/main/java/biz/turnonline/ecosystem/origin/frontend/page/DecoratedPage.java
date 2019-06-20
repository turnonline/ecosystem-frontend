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

import biz.turnonline.ecosystem.origin.frontend.FrontendApplication;
import biz.turnonline.ecosystem.origin.frontend.FrontendSession;
import biz.turnonline.ecosystem.origin.frontend.component.MaterialIconType;
import biz.turnonline.ecosystem.origin.frontend.component.SearchPanel;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarExternalLink;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.https.RequireHttps;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResource;
import org.ctoolkit.wicket.standard.model.I18NResourceModel;
import org.ctoolkit.wicket.turnonline.markup.html.page.Skeleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Decorated page used as a base page for other pages
 *
 * @author <a href="mailto:pohorelec@comvai.com">Jozef Pohorelec</a>
 */
@RequireHttps
public class DecoratedPage<T>
        extends Skeleton<T>
{
    private static final long serialVersionUID = 5396376456675284004L;

    public DecoratedPage()
    {
        initialize();
    }

    public DecoratedPage( IModel<T> model )
    {
        super( model );
        initialize();
    }

    public DecoratedPage( PageParameters parameters )
    {
        super( parameters );
        initialize();
    }

    // initialize

    protected void initialize()
    {
        // Appends Bootstrap JavaScript and CSS
        add( new BootstrapResourcesBehavior() );

        // initialize page title
        add( newPageTitle( "title" ) );

        // initialize navbar
        add( newNavbar( "navbar" ) );

        // initialize header
        add( newHeader( "header" ) );

        // initialize notifications
        add( newFeedbackPanel() );
    }

    // -- navbar

    protected Navbar newNavbar( String componentId )
    {
        Navbar navbar = new Navbar( componentId )
        {
            @Override
            protected Image newBrandImage( String markupId )
            {
                Image image = super.newBrandImage( markupId );

                image.setImageResource( new ContextRelativeResource( "logo.png" ) );

                return image;
            }

            @Override
            protected Label newBrandLabel( String markupId )
            {
                return new Label( markupId, new I18NResourceModel( "brand" ) );
            }
        };

        navbar.fluid();
        navbar.addComponents( NavbarComponents.transform( Navbar.ComponentPosition.RIGHT, newNavbarComponents() ) );
        navbar.get( "container:brandName" ).get( "brandImage" ).add( AttributeModifier.append( "style", "max-height:32px;" ) );

        return navbar;
    }

    protected Component[] newNavbarComponents()
    {
        List<Component> navbarComponents = new ArrayList<>();

        navbarComponents.add( newSearchPanel() );
        if ( FrontendSession.get().isLoggedIn() )
        {
            navbarComponents.add( newMyAccountNavbarItem() );
            navbarComponents.add( newLogoutNavbarItem() );
        }
        else
        {
            navbarComponents.add( newLoginNavbarItem() );
            navbarComponents.add( newSignupNavbarItem() );
        }

        return navbarComponents.toArray( new Component[]{} );
    }

    protected Component newLoginNavbarItem()
    {
        return new NavbarExternalLink( Model.of( urlFor( Login.class, new PageParameters() ).toString() ) )
                .setIconType( MaterialIconType.lockOpen )
                .setLabel( new I18NResourceModel( "label.login" ) );
    }

    protected Component newSignupNavbarItem()
    {
        return new NavbarExternalLink( Model.of( urlFor( Signup.class, new PageParameters() ).toString() ) )
                .setIconType( MaterialIconType.edit )
                .setLabel( new I18NResourceModel( "label.signup" ) );
    }

    protected Component newMyAccountNavbarItem()
    {
        return new NavbarExternalLink( Model.of( urlFor( MyAccount.class, new PageParameters() ).toString() ) )
                .setIconType( MaterialIconType.person )
                .setLabel( new I18NResourceModel( "label.myAccount" ) );
    }

    protected Component newLogoutNavbarItem()
    {
        String script = "firebase.auth().signOut().then(function(){window.location.href='" + FrontendApplication.LOGOUT + "'});";

        return new NavbarExternalLink( Model.of( FrontendApplication.LOGOUT ) )
                .setIconType( MaterialIconType.powerSettingsNew )
                .setLabel( new I18NResourceModel( "label.logout" ) )
                .add( new AttributeAppender( "onclick", script, ";" ) );
    }

    protected Component newSearchPanel()
    {
        return new SearchPanel( Navbar.componentId() );
    }

    // -- notifications

    @Override
    protected FeedbackPanel newFeedbackPanel( String componentId )
    {
        return new NotificationPanel( componentId );
    }

    protected Component getFeedbackPanel()
    {
        return get( FEEDBACK_MARKUP_ID );
    }

    // -- page title

    protected Component newPageTitle( String componentId )
    {
        return new Label( componentId, new I18NResourceModel( "title" ) );
    }

    // -- header

    protected Component newHeader( String componentId )
    {
        Label header = new Label( componentId, new I18NResourceModel( "header." + getClass().getSimpleName() ) );
        header.setEscapeModelStrings( false );
        return header;
    }

    // -- initialize

    @Override
    protected void onInitialize()
    {
        super.onInitialize();

        // container for firebase javascripts - must be located in html at the bottom
        add( new HeaderResponseContainer( "html-bottom-container", HTML_BOTTOM_FILTER_NAME ) );

        add( new Behavior()
        {
            @Override
            public void renderHead( Component component, IHeaderResponse response )
            {
                response.render( CssHeaderItem.forUrl( "https://fonts.googleapis.com/icon?family=Material+Icons" ) );
                response.render( CssHeaderItem.forUrl( "/styles/turnonline.css" ) );
            }
        } );
    }
}
