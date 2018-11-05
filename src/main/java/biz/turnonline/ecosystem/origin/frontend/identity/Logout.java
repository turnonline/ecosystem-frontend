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

package biz.turnonline.ecosystem.origin.frontend.identity;

import biz.turnonline.ecosystem.origin.frontend.FrontendApplication;
import org.ctoolkit.restapi.client.firebase.IdentityHandler;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Single purpose servlet to invalidate session and redirect to the login page.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
@Singleton
public class Logout
        extends HttpServlet
{
    private static final long serialVersionUID = -957674638895395920L;

    private final IdentityHandler resolver;

    @Inject
    Logout( IdentityHandler resolver )
    {
        this.resolver = resolver;
    }

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        resolver.delete( request, response );
        request.getSession().invalidate();

        // mode select is being required by identity toolkit widget as configuration parameter
        response.sendRedirect( FrontendApplication.LOGIN );
    }
}
