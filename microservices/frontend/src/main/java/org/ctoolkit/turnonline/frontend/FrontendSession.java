package org.ctoolkit.turnonline.frontend;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.ctoolkit.turnonline.frontend.identity.AuthenticatedUser;
import org.ctoolkit.turnonline.shared.resource.Domicile;
import org.ctoolkit.turnonline.shared.resource.Locale;
import org.ctoolkit.turnonline.shared.resource.User;
import org.ctoolkit.turnonline.wicket.util.WicketUtil;

import javax.servlet.http.HttpSession;

/**
 * TODO look at {@link AuthenticatedWebSession#isSignedIn()} and try to employ.
 *
 * @author <a href="mailto:jozef.pohorelec@ctoolkit.org>Jozef Pohorelec</a>"
 */
public class FrontendSession
        extends AuthenticatedWebSession
{
    static final String AUTH_USER_ATTRIBUTE = "__session_auth_user_attribute";

    private static final long serialVersionUID = -5908999362458425557L;

    private Long loggedInUserId;

    public FrontendSession( Request request )
    {
        super( request );

        //FIXME fix hardcoded values Session.sellerLocale and setSellerDomicile, initial values should come from IPageProperties
        setSellerLocale( Domicile.SK.getLocale() );
        setSellerDomicile( Domicile.SK );
    }

    public static FrontendSession get()
    {
        return ( FrontendSession ) Session.get();
    }

    @Override
    public boolean authenticate( String s, String s1 )
    {
        return false;
    }

    @Override
    public Roles getRoles()
    {
        Roles roles = new Roles();

        if ( isLoggedIn() )
        {
            roles.add( getLoggedInUser().getRole().name() );
        }

        return roles;
    }

    public Long getItemsCount()
    {
        Long itemsCount = Long.class.cast( getAttribute( "itemsCount" ) );

        if ( itemsCount == null )
        {
            return 0L;
        }

        return itemsCount;
    }

    public void setItemsCount( Long itemsCount )
    {
        setAttribute( "itemsCount", itemsCount );
    }

    public Locale getSellerLocale()
    {
        return ( Locale ) getAttribute( "sellerLocale" );
    }

    public void setSellerLocale( Locale sellerLocale )
    {
        setAttribute( "sellerLocale", sellerLocale );
    }

    public Domicile getSellerDomicile()
    {
        return ( Domicile ) getAttribute( "sellerDomicile" );
    }

    public void setSellerDomicile( Domicile sellerDomicile )
    {
        setAttribute( "sellerDomicile", sellerDomicile );
    }

    public void resetItemsCount()
    {
        setAttribute( "itemsCount", null );
    }

    public boolean isLoggedIn()
    {
        return getLoggedInUser() != null;
    }

    public boolean isLoggedIn( User account )
    {
        User loggedInUser = getLoggedInUser();
        return loggedInUser != null && loggedInUser.equals( account );
    }

    public Long getLoggedInUserId()
    {
        if ( loggedInUserId == null )
        {
            loggedInUserId = getLoggedInUser().getId();
        }
        return loggedInUserId;
    }

    public AuthenticatedUser getLoggedInUser()
    {
        HttpSession session = WicketUtil.getHttpServletRequest().getSession();
        return ( AuthenticatedUser ) session.getAttribute( AUTH_USER_ATTRIBUTE );
    }

    public void updateLoggedInUser( User user )
    {
        HttpSession session = WicketUtil.getHttpServletRequest().getSession();
        session.setAttribute( AUTH_USER_ATTRIBUTE, new AuthenticatedUser( user ) );
    }
}