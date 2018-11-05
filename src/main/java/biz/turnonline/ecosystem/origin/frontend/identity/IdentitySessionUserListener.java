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
import biz.turnonline.ecosystem.steward.model.Account;
import com.google.firebase.auth.FirebaseToken;
import org.ctoolkit.restapi.client.NotFoundException;
import org.ctoolkit.restapi.client.RestFacade;
import org.ctoolkit.restapi.client.UnauthorizedException;
import org.ctoolkit.restapi.client.firebase.IdentityHandler;
import org.ctoolkit.restapi.client.firebase.IdentityLoginListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The listener implementation handling logged in identity user instance as
 * {@link Account} stored in the session.
 * In case that no user has found for authenticated email, user account will be created (sign up).
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
@Singleton
public class IdentitySessionUserListener
        implements IdentityLoginListener
{
    private static final Logger logger = LoggerFactory.getLogger( IdentitySessionUserListener.class );

    private final RestFacade facade;

    private final IdentityHandler handler;

    @Inject
    public IdentitySessionUserListener( RestFacade facade,
                                        IdentityHandler handler )
    {
        this.facade = facade;
        this.handler = handler;
    }

    @Override
    public void processIdentity( @Nonnull HttpServletRequest request,
                                 @Nonnull HttpServletResponse response,
                                 @Nonnull FirebaseToken identity,
                                 @Nonnull String sessionAttribute )
    {
        String signedEmail = identity.getEmail();

        // JWT token of logged in user to be forwarded
        String jwtToken = handler.getToken( request );
        if ( jwtToken == null )
        {
            throw new IllegalArgumentException( "Missing JWT token!" );
        }

        try
        {
            Account account = facade.get( Account.class ).identifiedBy( signedEmail ).authBy( jwtToken ).bearer().finish();
            account.setCompany( false );
            request.getSession().setAttribute( sessionAttribute, account );
        }
        catch ( NotFoundException e )
        {
            // the user is verified against identity service but not created in backend yet
            Account account = new Account();
            account.setEmail( signedEmail );
            account.setCompany( false );
            account.setFirstName( identity.getName() );
            account.setIdentityId( identity.getIssuer() );

            account = facade.insert( account ).authBy( jwtToken ).bearer().finish();

            AccountProfile profile = new AccountProfile( identity );

            request.getSession().setAttribute( AccountProfile.class.getName(), profile );
            request.getSession().setAttribute( sessionAttribute, account );

            try
            {
                response.sendRedirect( FrontendApplication.MY_ACCOUNT );
            }
            catch ( IOException ioe )
            {
                logger.error( "An error has occurred while user redirect", e );
            }
        }
        catch ( UnauthorizedException e )
        {
            logger.error( "Call to REST API is unauthorized! Email " + signedEmail, e );
        }
        catch ( Exception e )
        {
            logger.error( "Unknown error has occurred during user login", e );
        }
    }
}
