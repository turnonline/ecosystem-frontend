package biz.turnonline.ecosystem.origin.frontend.model;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Prototype;

/**
 * @author <a href="mailto:pohorelec@turnonlie.biz">Jozef Pohorelec</a>
 */
@Factory
public class ModelFactory
{
    @Prototype
    public ControllerModel provideControllerModel( GwtConfig gwtConfig,
                                                   FirebaseConfig firebaseConfig )
    {
        ControllerModel model = new ControllerModel();
        model.setFirebaseConfig( firebaseConfig );
        model.setGwtConfig( gwtConfig );

        return model;
    }
}
