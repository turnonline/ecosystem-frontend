package biz.turnonline.ecosystem.origin.frontend.page;

import biz.turnonline.ecosystem.origin.frontend.FrontendApplication;
import biz.turnonline.ecosystem.origin.frontend.FrontendSession;
import biz.turnonline.ecosystem.origin.frontend.myaccount.page.MyAccount;
import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarExternalLink;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.ctoolkit.wicket.standard.model.I18NResourceModel;
import org.ctoolkit.wicket.turnonline.markup.html.page.Skeleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Decorated page used as a base page for other pages
 *
 * @author <a href="mailto:pohorelec@comvai.com">Jozef Pohorelec</a>
 */
public class DecoratedPage<T>
        extends Skeleton<T>
{
    public static final String HTML_BOTTOM_FILTER_NAME = "html-bottom-container";

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
        // initialize page title
        add( newPageTitle( "title" ) );

        // initialize navbar
        add( newNavbar( "navbar" ) );

        // initialize header
        add( newHeader( "header" ) );

        // initialize notifications
        add( newFeedbackPanel( ) );

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
                .setIconType( GlyphIconType.user )
                .setLabel( new I18NResourceModel( "label.login" ) );
    }

    protected Component newSignupNavbarItem()
    {
        return new NavbarExternalLink( Model.of( urlFor( Signup.class, new PageParameters() ).toString() ) )
                .setIconType( GlyphIconType.pencil )
                .setLabel( new I18NResourceModel( "label.signup" ) );
    }

    protected Component newMyAccountNavbarItem()
    {
        return new NavbarExternalLink( Model.of( urlFor( MyAccount.class, new PageParameters() ).toString() ) )
                .setIconType( GlyphIconType.th )
                .setLabel( new I18NResourceModel( "label.myAccount" ) );
    }

    protected Component newLogoutNavbarItem()
    {
        String script = "firebase.auth().signOut().then(function(){window.location.href='" + FrontendApplication.LOGOUT + "'});";

        return new NavbarExternalLink( Model.of( FrontendApplication.LOGOUT) )
                .setIconType( GlyphIconType.off )
                .setLabel( new I18NResourceModel( "label.logout" ) )
                .add( new AttributeAppender( "onclick", script, ";" ) );
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

    // -- footer

    protected Component newFooter( String componentId )
    {
        return new WebMarkupContainer( componentId );
    }

    // -- initialize

    @Override
    protected void onInitialize()
    {
        super.onInitialize();

        // container for firebase javascripts - must be located in html at the bottom
        add( new HeaderResponseContainer( "html-bottom-container", HTML_BOTTOM_FILTER_NAME ) );
    }
}
