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

import biz.turnonline.ecosystem.steward.model.Account;
import biz.turnonline.ecosystem.steward.model.AccountPersonalAddress;
import biz.turnonline.ecosystem.steward.model.Country;
import org.apache.wicket.model.IModel;
import org.ctoolkit.wicket.standard.model.DropDownBridgeModel;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * The model to get {@link Country} and set related code to
 * {@link AccountPersonalAddress#setCountry(String)} taken from the countries map.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class PersonalAddressCountryModel
        extends DropDownBridgeModel<Country, Account>
{
    private static final long serialVersionUID = 938155036445630296L;

    public PersonalAddressCountryModel( IModel<Account> model, IModel<Map<String, Country>> countriesModel )
    {
        super( model, countriesModel );
    }

    @Override
    public String getCode( @Nonnull Account target )
    {
        AccountPersonalAddress address = target.getPersonalAddress();
        return address == null ? null : address.getCountry();
    }

    @Override
    public void updateCode( Country country, @Nonnull Account target )
    {
        AccountPersonalAddress address = target.getPersonalAddress();
        if ( address != null )
        {
            address.setCountry( country == null ? null : country.getCode() );
        }
    }
}
