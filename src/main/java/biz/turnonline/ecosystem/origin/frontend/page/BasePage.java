package biz.turnonline.ecosystem.origin.frontend.page;

import biz.turnonline.ecosystem.origin.frontend.FrontendApplication;
import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarExternalLink;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.ctoolkit.wicket.standard.model.I18NResourceModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Base page
 *
 * @author <a href="mailto:pohorelec@comvai.com">Jozef Pohorelec</a>
 */
public class BasePage
        extends WebPage
{
    public BasePage()
    {
        // initialize navbar
        add( newNavbar( "navbar" ) );

        // initialize header
        add( newHeader( "header" ) );

        // initialize notifications
        add( newNotifications( "notifications" ) );

        // initialize footer
        add( newFooter( "footer" ) );
    }

    // -- navbar

    protected Navbar newNavbar( String componentId )
    {
        Navbar navbar = new Navbar( componentId );

        navbar.addComponents( NavbarComponents.transform( Navbar.ComponentPosition.RIGHT, newNavbarComponents() ) );
        navbar.setBrandImage( new PackageResourceReference( FrontendApplication.class, "logo.png" ), Model.of( "" ) );
        navbar.get( "brandName" ).get( "brandImage" ).add( AttributeModifier.append( "style", "max-height:32px;" ) );
        navbar.setInverted( true );

        return navbar;
    }

    protected Component[] newNavbarComponents()
    {
        List<Component> navbarComponents = new ArrayList<>();

        if ( AuthenticatedWebSession.get().isSignedIn() )
        {
            // TODO: add my account and logout items
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
                .setIconType( GlyphIconType.user )
                .setLabel( new I18NResourceModel( "label.login" ) );
    }

    protected Component newSignupNavbarItem()
    {
        return new NavbarExternalLink( Model.of( urlFor( Signup.class, new PageParameters() ).toString() ) )
                .setIconType( GlyphIconType.pencil )
                .setLabel( new I18NResourceModel( "label.signup" ) );
    }

    // -- notifications

    protected Component newNotifications( String componentId )
    {
        return new NotificationPanel( componentId );
    }

    protected Component getNotifications()
    {
        return get( "notifications" );
    }

    // -- header

    protected Component newHeader( String componentId )
    {
        Label header = new Label( componentId, new I18NResourceModel( "header." + getClass().getSimpleName() ) );
        header.setEscapeModelStrings( false );
        return header;
    }

    protected Component newFooter( String componentId )
    {
        return new WebMarkupContainer( "footer" );
    }
}
