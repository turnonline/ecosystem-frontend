package biz.turnonline.ecosystem.origin.frontend;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import org.ctoolkit.services.guice.AppEngineEnvironmentContextListener;

/**
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class GuiceServletConfig
        extends AppEngineEnvironmentContextListener
{
    @Override
    protected Injector getDevelopmentInjector()
    {
        return Guice.createInjector( new FrontendModule(),
                new FrontendServletModule() );
    }

    @Override
    protected Injector getProductionInjector()
    {
        return Guice.createInjector( Stage.PRODUCTION,
                new FrontendModule(),
                new FrontendServletModule() );
    }
}
