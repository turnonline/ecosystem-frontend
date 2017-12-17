package org.ctoolkit.turnonline.origin.frontend.myaccount.model;

import biz.turnonline.ecosystem.account.client.model.Account;
import biz.turnonline.ecosystem.account.client.model.AccountPostalAddress;
import biz.turnonline.ecosystem.account.client.model.Country;
import org.apache.wicket.model.IModel;
import org.ctoolkit.wicket.standard.model.DropDownBridgeModel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

/**
 * The model to get {@link Country} and set related code to
 * {@link AccountPostalAddress#setCountry(String)} (String)} taken from the countries map.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
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
