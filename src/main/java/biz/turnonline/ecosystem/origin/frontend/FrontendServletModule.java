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

import biz.turnonline.ecosystem.origin.frontend.identity.Logout;
import com.google.inject.servlet.ServletModule;
import com.googlecode.objectify.ObjectifyFilter;
import org.ctoolkit.restapi.client.firebase.IdentityCheckSessionFilter;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

/**
 * The frontend servlet module to configure Guice to serve requests at custom defined servlet path.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class FrontendServletModule
        extends ServletModule
{
    @Override
    protected void configureServlets()
    {
        bind( ObjectifyFilter.class ).in( Singleton.class );
        filter( "/*" ).through( ObjectifyFilter.class );

        String ignorePaths = "/styles,/scripts,/images,/favicon.ico,/appstats,/wicket/resource";

        Map<String, String> params = new HashMap<>();
        params.put( IdentityCheckSessionFilter.LOGIN_PATH, FrontendApplication.LOGIN );
        params.put( IdentityCheckSessionFilter.SIGN_UP_PATH, FrontendApplication.SIGNUP );
        params.put( IdentityCheckSessionFilter.SESSION_AUTH_USER_ATTRIBUTE, FrontendSession.AUTH_USER_ATTRIBUTE );
        params.put( IdentityCheckSessionFilter.REDIRECT_PATH, FrontendApplication.MY_ACCOUNT );
        params.put( IdentityCheckSessionFilter.IGNORE_PATHS, ignorePaths );

        filter( "/*" ).through( IdentityCheckSessionFilter.class, params );

        // configure logout path to the servlet to invalidate active session
        serve( FrontendApplication.LOGOUT ).with( Logout.class );
    }
}
