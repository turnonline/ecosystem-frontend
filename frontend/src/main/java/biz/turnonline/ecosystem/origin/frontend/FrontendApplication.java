package biz.turnonline.ecosystem.origin.frontend;

import biz.turnonline.ecosystem.origin.frontend.myaccount.page.AccountSettings;
import biz.turnonline.ecosystem.origin.frontend.myaccount.page.MyAccountBasics;
import biz.turnonline.ecosystem.origin.frontend.page.Robots;
import biz.turnonline.ecosystem.origin.frontend.page.ShoppingCart;
import biz.turnonline.ecosystem.origin.frontend.page.SiteMap;
import com.google.inject.Injector;
import net.sf.jsr107cache.Cache;
import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.pageStore.memory.IDataStoreEvictionStrategy;
import org.apache.wicket.pageStore.memory.MemorySizeEvictionStrategy;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.IResourceSettings;
import org.apache.wicket.util.file.IResourceFinder;
import org.apache.wicket.util.lang.Bytes;
import org.ctoolkit.wicket.standard.cache.MemcachePropertiesFactory;
import org.ctoolkit.wicket.standard.cache.MemcacheResourceLocator;
import org.ctoolkit.wicket.turnonline.AppEngineApplication;
import org.ctoolkit.wicket.turnonline.identity.page.IdentityLogin;
import org.ctoolkit.wicket.turnonline.identity.page.SignUp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The application entry point and its configuration.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class FrontendApplication
        extends AppEngineApplication
{
    public static final String SETTINGS = MY_ACCOUNT + "/settings";

    private Set<String> include = new HashSet<>();

    {
        include.add( "biz/turnonline/ecosystem/origin/frontend/FrontendApplication" );
        include.add( "biz/turnonline/ecosystem/origin/frontend/FrontendApplication_sk" );
        include.add( "org/apache/wicket/Application" );
        include.add( "com/googlecode/wicket/jquery/ui/Initializer" );
        include.add( "org/apache/wicket/extensions/Initializer" );
    }

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

        // set custom resource locator
        IResourceSettings resourceSettings = getResourceSettings();
        List<IResourceFinder> finders = resourceSettings.getResourceFinders();
        Cache memcache = injector.getInstance( Cache.class );
        resourceSettings.setResourceStreamLocator( new MemcacheResourceLocator( finders, memcache ) );

        // set custom properties factory
        resourceSettings.setPropertiesFactory( new MemcachePropertiesFactory( resourceSettings, memcache, include ) );

        mountPage( LOGIN, IdentityLogin.class );
        mountPage( SIGNUP, SignUp.class );
        mountPage( MY_ACCOUNT, MyAccountBasics.class );
        mountPage( SETTINGS, AccountSettings.class );
        mountPage( SHOPPING_CART, ShoppingCart.class );
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
