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

package biz.turnonline.ecosystem.origin.frontend.myaccount.model;

import biz.turnonline.ecosystem.origin.frontend.steward.Account;
import biz.turnonline.ecosystem.origin.frontend.steward.AccountPostalAddress;
import biz.turnonline.ecosystem.origin.frontend.steward.Country;
import org.apache.wicket.model.IModel;
import org.ctoolkit.wicket.standard.model.DropDownBridgeModel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

/**
 * The model to get {@link Country} and set related code to
 * {@link AccountPostalAddress#setCountry(String)} (String)} taken from the countries map.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class PostalAddressCountryModel
        extends DropDownBridgeModel<Country, Account>
{
    private static final long serialVersionUID = -2873755499659424219L;

    /**
     * Constructor.
     *
     * @param model        the target model as a source of default choice code
     *                     or to be updated by selected choice
     * @param choicesModel the map of choices as a source for drop down list
     */
    public PostalAddressCountryModel( IModel<Account> model, IModel<Map<String, Country>> choicesModel )
    {
        super( model, choicesModel );
    }

    @Override
    protected String getCode( @Nonnull Account target )
    {
        AccountPostalAddress address = target.getPostalAddress();
        return address == null ? null : address.getCountry();
    }

    @Override
    protected void updateCode( @Nullable Country country, @Nonnull Account target )
    {
        AccountPostalAddress address = target.getPostalAddress();
        if ( address != null )
        {
            address.setCountry( country == null ? null : country.getCode() );
        }
    }
}
