package org.ctoolkit.turnonline.origin.frontend.server;

import com.google.inject.AbstractModule;
import org.ctoolkit.turnonline.wicket.model.IModelFactory;

/**
 * The frontend server module to configure Guice.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
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
