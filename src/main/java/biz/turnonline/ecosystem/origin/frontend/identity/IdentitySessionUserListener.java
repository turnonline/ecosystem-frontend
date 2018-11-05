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

import com.google.firebase.auth.FirebaseToken;
import org.ctoolkit.restapi.client.firebase.IdentityLoginListener;

import javax.annotation.Nonnull;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The listener implementation handling logged in identity user instance as
 * {@link AccountProfile} stored in the session.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
@Singleton
public class IdentitySessionUserListener
        implements IdentityLoginListener
{
    @Override
    public void processIdentity( @Nonnull HttpServletRequest request,
                                 @Nonnull HttpServletResponse response,
                                 @Nonnull FirebaseToken identity,
                                 @Nonnull String sessionAttribute )
    {
        AccountProfile profile = new AccountProfile( identity );
        request.getSession().setAttribute( sessionAttribute, profile );
    }
}
