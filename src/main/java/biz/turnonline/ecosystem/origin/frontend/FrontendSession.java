package biz.turnonline.ecosystem.origin.frontend;

import biz.turnonline.ecosystem.origin.frontend.identity.Role;
import biz.turnonline.ecosystem.steward.model.Account;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.ctoolkit.wicket.standard.util.WicketUtil;

import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * TODO look at {@link AuthenticatedWebSession#isSignedIn()} and try to employ.
 *
 * @author <a href="mailto:jozef.pohorelec@ctoolkit.org">Jozef Pohorelec</a>
 */
public class FrontendSession
        extends AuthenticatedWebSession
{
    static final String AUTH_USER_ATTRIBUTE = "__session_auth_user_attribute";

    static final Locale DEFAULT_SESSION_LOCALE = Locale.ENGLISH;

    private static final long serialVersionUID = -5908999362458425557L;

    private String loggedInUserId;

    public FrontendSession( Request request )
    {
        super( request );

        setLocale( DEFAULT_SESSION_LOCALE );
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
            roles.add( Role.STANDARD );
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

    public String getSellerDomicile()
    {
        return ( String ) getAttribute( "sellerDomicile" );
    }

    public void setSellerDomicile( String sellerDomicile )
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

    public boolean isLoggedIn( Account account )
    {
        Account loggedInUser = getLoggedInUser();
        return loggedInUser != null && loggedInUser.equals( account );
    }

    public String getLoggedInUserId()
    {
        if ( loggedInUserId == null )
        {
            loggedInUserId = getLoggedInUser().getEmail();
        }
        return loggedInUserId;
    }

    public Account getLoggedInUser()
    {
        HttpSession session = WicketUtil.getHttpServletRequest().getSession();
        return ( Account ) session.getAttribute( AUTH_USER_ATTRIBUTE );
    }

    public void updateLoggedInUser( Account account )
    {
        HttpSession session = WicketUtil.getHttpServletRequest().getSession();
        session.setAttribute( AUTH_USER_ATTRIBUTE, account );
    }
}