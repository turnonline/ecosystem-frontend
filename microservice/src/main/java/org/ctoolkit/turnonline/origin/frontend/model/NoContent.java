/*
 * Copyright 2017 Comvai, s.r.o.
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
package org.ctoolkit.turnonline.origin.frontend.model;

import org.ctoolkit.turnonline.shared.content.PublicContent;
import org.ctoolkit.turnonline.shared.content.SellerContent;

/**
 * Private or non public pages:
 * PageNotFound, InternalError, MyAccount, PayInvoice, Logout, OrderConfirmation, OrderCancellation
 * <p/>
 * In memory only.
 * <p/>
 * This should have a configuration do not let search engine to crawl in case of the public but non visible page.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class NoContent
        extends PublicContent
{
    private static final long serialVersionUID = -3610695685500214730L;

    public NoContent( String name, String jokerDomain )
    {
        super( name );
        String fullJokerDomain = "http://www." + jokerDomain;
        SellerContent seller = new SellerContent();
        seller.setPaymentLink( fullJokerDomain );
        setSeller( seller );
    }

    public String getContentTitle()
    {
        return "";
    }

    public String getPageHeader()
    {
        return "";
    }

    public String getAnalyticsAccount()
    {
        return "";
    }

    public String getHeaderDescription()
    {
        return "";
    }

    public String getHeaderKeywords()
    {
        return "";
    }
}
