/*
 * Copyright 2018 Comvai, s.r.o.
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

package biz.turnonline.ecosystem.origin.frontend;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import org.ctoolkit.services.guice.AppEngineEnvironmentContextListener;

/**
 * The guice injector configuration.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class GuiceServletConfig
        extends AppEngineEnvironmentContextListener
{
    @Override
    protected Injector getDevelopmentInjector()
    {
        return Guice.createInjector( new StorageModule(),
                new FrontendModule(),
                new FrontendServletModule() );
    }

    @Override
    protected Injector getProductionInjector()
    {
        return Guice.createInjector( Stage.PRODUCTION,
                new StorageModule(),
                new FrontendModule(),
                new FrontendServletModule() );
    }
}
