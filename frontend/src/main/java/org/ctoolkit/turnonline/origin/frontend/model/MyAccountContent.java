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

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import org.ctoolkit.turnonline.shared.content.PublicContent;
import org.ctoolkit.turnonline.shared.content.SellerContent;
import org.ctoolkit.turnonline.shared.resource.IPayment;
import org.ctoolkit.turnonline.shared.resource.User;

import java.util.List;

/**
 * MyAccount page properties, in memory.
 * <p/>
 * The important use case is the use for administration purpose under administration subdomain.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class MyAccountContent
        extends PublicContent
{
    private static final long serialVersionUID = 9138516111634810465L;

    private User account;

    private String currentCountry;

    private List<IPayment.Gate> gateways;

    @SuppressWarnings( "UnusedDeclaration" )
    MyAccountContent()
    {
    }

    public MyAccountContent( Builder builder )
    {
        this.account = builder.account;
        this.gateways = builder.gateways;
        this.currentCountry = builder.currentCountry;

        SellerContent seller = new SellerContent();
        seller.setLogoUrl( account.getLogoServingUrl() );
        seller.setTermsUrl( account.getTermsUrl() );
        seller.setEmail( account.getEmail() );
        seller.setDomicile( account.getDomicile() );
        seller.setLocale( account.getLocale() );
        seller.setCustomStyling( true );

        this.setSeller( seller );
    }

    public User getAccount()
    {
        return account;
    }

    public String getCurrentCountry()
    {
        return currentCountry;
    }

    public List<IPayment.Gate> getGateways()
    {
        return MoreObjects.firstNonNull( gateways, ImmutableList.<IPayment.Gate>of() );
    }

    public static class Builder
    {
        private User account;

        private List<IPayment.Gate> gateways;

        private String currentCountry;

        public Builder setAccount( User account )
        {
            this.account = account;
            return this;
        }

        public Builder setGateways( List<IPayment.Gate> gateways )
        {
            this.gateways = gateways;
            return this;
        }

        public Builder setCurrentCountry( String currentCountry )
        {
            this.currentCountry = currentCountry;
            return this;
        }
    }
}
