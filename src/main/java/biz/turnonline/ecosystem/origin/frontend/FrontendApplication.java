package biz.turnonline.ecosystem.origin.frontend;

import biz.turnonline.ecosystem.origin.frontend.myaccount.page.MyAccount;
import biz.turnonline.ecosystem.origin.frontend.page.Home;
import biz.turnonline.ecosystem.origin.frontend.page.Login;
import biz.turnonline.ecosystem.origin.frontend.page.Robots;
import biz.turnonline.ecosystem.origin.frontend.page.Signup;
import biz.turnonline.ecosystem.origin.frontend.page.SiteMap;
import com.google.inject.Injector;
import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.SingleThemeProvider;
import de.agilecoders.wicket.themes.markup.html.material_design.MaterialDesignTheme;
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
    public static final String SIGNUP = "/sign-up";

    public static final String LOGIN = "/login";

    public static final String LOGOUT = "/logout";

    public static final String MY_ACCOUNT = "/my-account";

    private Set<String> include = new HashSet<>();

    {
        include.add( "biz/turnonline/ecosystem/origin/frontend/FrontendApplication" );
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

        mountPage( LOGIN, Login.class );
        mountPage( SIGNUP, Signup.class );
        mountPage( MY_ACCOUNT, MyAccount.class );

        // init wicket bootstrap
        BootstrapSettings bootstrapSettings = new BootstrapSettings();
        bootstrapSettings.setThemeProvider( new SingleThemeProvider( new MaterialDesignTheme() ) );
        bootstrapSettings.useCdnResources( true );
        Bootstrap.install( this, bootstrapSettings );
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
        return Login.class;
    }

    @Override
    public Class<? extends Page> getHomePage()
    {
        return Home.class;
    }
}
