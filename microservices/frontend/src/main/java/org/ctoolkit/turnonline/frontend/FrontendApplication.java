package org.ctoolkit.turnonline.frontend;

import com.google.inject.Injector;
import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.pageStore.memory.IDataStoreEvictionStrategy;
import org.apache.wicket.pageStore.memory.MemorySizeEvictionStrategy;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.lang.Bytes;
import org.ctoolkit.turnonline.frontend.myaccount.page.AccountSettings;
import org.ctoolkit.turnonline.frontend.myaccount.page.MyAccountBasics;
import org.ctoolkit.turnonline.frontend.page.Robots;
import org.ctoolkit.turnonline.frontend.page.ShoppingCart;
import org.ctoolkit.turnonline.frontend.page.SiteMap;
import org.ctoolkit.turnonline.wicket.AppEngineApplication;
import org.ctoolkit.turnonline.wicket.identity.page.IdentityLogin;
import org.ctoolkit.turnonline.wicket.identity.page.SignUp;

import java.util.HashSet;
import java.util.Set;

/**
 * The application entry point and its configuration.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class FrontendApplication
        extends AppEngineApplication
{
    public static final String SETTINGS = MY_ACCOUNT + "/settings";

    public FrontendApplication()
    {
    }

    public static FrontendApplication get()
    {
        return ( FrontendApplication ) WebApplication.get();
    }

    @Override
    protected void init()
    {
        super.init();

        // add guice component injector
        Injector injector = getInjector();
        getComponentInstantiationListeners().add( new GuiceComponentInjector( this, injector ) );

        mountPage( LOGIN, IdentityLogin.class );
        mountPage( SIGNUP, SignUp.class );
        mountPage( MY_ACCOUNT, MyAccountBasics.class );
        mountPage( SETTINGS, AccountSettings.class );
        mountPage( SHOPPING_CART, ShoppingCart.class );
    }

    @Override
    public Set<String> getThemeNames()
    {
        return new HashSet<>();
    }

    private Injector getInjector()
    {
        return ( Injector ) getServletContext().getAttribute( Injector.class.getName() );
    }

    @Override
    public IDataStoreEvictionStrategy getEvictionStrategy()
    {
        return new MemorySizeEvictionStrategy( Bytes.kilobytes( 200L ) );
    }

    @Override
    protected Class<? extends WebPage> getSiteMapPage()
    {
        return SiteMap.class;
    }

    @Override
    protected Class<? extends WebPage> getRobotsPage()
    {
        return Robots.class;
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass()
    {
        return FrontendSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass()
    {
        return IdentityLogin.class;
    }

    @Override
    public Class<? extends Page> getHomePage()
    {
        return ShoppingCart.class;
    }
}
