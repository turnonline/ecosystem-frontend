package biz.turnonline.ecosystem.origin.frontend.service;

import biz.turnonline.ecosystem.origin.frontend.model.ControllerModel;
import biz.turnonline.ecosystem.origin.frontend.model.FirebaseConfig;
import biz.turnonline.ecosystem.origin.frontend.model.GwtConfig;
import biz.turnonline.ecosystem.origin.frontend.model.Messages;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Prototype;
import io.micronaut.http.context.ServerRequestContext;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

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
        Locale locale = locale();

        ControllerModel model = new ControllerModel();
        model.setLocale( locale.getLanguage() );
        model.setFirebaseConfig( firebaseConfig );
        model.setGwtConfig( gwtConfig );
        model.setMessages( Messages.getBundle( "messages", locale ) );

        return model;
    }

    private Locale locale()
    {
        AtomicReference<String> locale = new AtomicReference<>( "en" );

        ServerRequestContext.currentRequest()
                .flatMap( request -> request.getCookies().findCookie( "locale" ) )
                .ifPresent( cookie -> locale.set( cookie.getValue() ) );

        return new Locale( locale.get(), locale.get() );
    }
}
