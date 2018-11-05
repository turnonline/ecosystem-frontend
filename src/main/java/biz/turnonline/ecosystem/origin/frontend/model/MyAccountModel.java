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

package biz.turnonline.ecosystem.origin.frontend.model;

import biz.turnonline.ecosystem.origin.frontend.FrontendSession;
import biz.turnonline.ecosystem.origin.frontend.identity.AccountProfile;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * Dedicated loadable model that retrieves logged in {@link AccountProfile} from the session.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class MyAccountModel
        extends LoadableDetachableModel<AccountProfile>
{
    private static final long serialVersionUID = 1L;

    @Override
    protected AccountProfile load()
    {
        return FrontendSession.get().getLoggedInUser();
    }

    @Override
    public void detach()
    {
        AccountProfile account = super.getObject();
        FrontendSession.get().updateLoggedInUser( account );
        super.detach();
    }
}
