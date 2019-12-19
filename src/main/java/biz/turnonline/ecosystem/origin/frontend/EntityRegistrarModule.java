package biz.turnonline.ecosystem.origin.frontend;

import biz.turnonline.ecosystem.origin.frontend.content.subscription.RawCommonContent;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.RawEventContent;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.RawMallArticle;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.RawPayInvoiceContent;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.RawProductContent;
import biz.turnonline.ecosystem.origin.frontend.content.subscription.RawTermsContent;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import org.ctoolkit.services.storage.guice.EntityRegistrar;
import org.ctoolkit.services.storage.guice.GuicefiedOfyFactory;

/**
 * Before using Objectify to load or save data, you must register all entity classes used in application.
 * In order to make sure all registration occurs in right time (either for production use or in tests) use this
 * entity registrar to register all Product Billing service entities.
 * <p>
 * To turn it on, somewhere in module use: {@code bind( GuicefiedOfyFactory.class ).asEagerSingleton();}
 * and leverage full Guice DI in entities too.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class EntityRegistrarModule
        extends AbstractModule
{
    @Override
    protected void configure()
    {
        Multibinder<EntityRegistrar> registrar = Multibinder.newSetBinder( binder(), EntityRegistrar.class );
        registrar.addBinding().to( Entities.class );
    }

    private static class Entities
            implements EntityRegistrar
    {
        @Override
        public void register( GuicefiedOfyFactory factory )
        {
            factory.register( RawCommonContent.class );
            factory.register( RawMallArticle.class );
            factory.register( RawPayInvoiceContent.class );
            factory.register( RawProductContent.class );
            factory.register( RawEventContent.class );
            factory.register( RawTermsContent.class );
        }
    }
}
