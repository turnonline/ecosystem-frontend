/*
 * Copyright 2020 Comvai, s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package biz.turnonline.ecosystem.origin.frontend.model;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Prototype;
import io.micronaut.http.context.ServerRequestContext;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

import static biz.turnonline.ecosystem.origin.frontend.controller.LanguageSelectorFilter.DEFAULT_LANGUAGE;
import static biz.turnonline.ecosystem.origin.frontend.controller.LanguageSelectorFilter.LANGUAGE_COOKIE;

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
        AtomicReference<String> locale = new AtomicReference<>( DEFAULT_LANGUAGE );

        ServerRequestContext.currentRequest()
                .flatMap( request -> request.getCookies().findCookie( LANGUAGE_COOKIE ) )
                .ifPresent( cookie -> locale.set( cookie.getValue() ) );

        return new Locale( locale.get() );
    }
}
