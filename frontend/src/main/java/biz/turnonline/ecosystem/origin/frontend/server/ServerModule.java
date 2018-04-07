package biz.turnonline.ecosystem.origin.frontend.server;

import com.google.inject.AbstractModule;
import org.ctoolkit.wicket.turnonline.model.IModelFactory;

/**
 * The frontend server module to configure Guice.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class ServerModule
        extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind( IModelFactory.class ).to( DefaultModelFactory.class );
    }
}
