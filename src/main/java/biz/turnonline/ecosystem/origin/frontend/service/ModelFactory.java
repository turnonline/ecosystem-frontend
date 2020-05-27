package biz.turnonline.ecosystem.origin.frontend.service;

import biz.turnonline.ecosystem.origin.frontend.model.ControllerModel;
import biz.turnonline.ecosystem.origin.frontend.model.FirebaseConfig;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Prototype;

/**
 * @author <a href="mailto:pohorelec@turnonlie.biz">Jozef Pohorelec</a>
 */
@Factory
public class ModelFactory
{
    @Prototype
    public ControllerModel provideControllerModel()
    {
        ControllerModel model = new ControllerModel();
        model.setLoggedIn( false );
        model.setFirebaseConfig( new FirebaseConfig() );

        return model;
    }
}
