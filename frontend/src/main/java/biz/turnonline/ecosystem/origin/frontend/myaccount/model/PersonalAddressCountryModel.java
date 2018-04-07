package biz.turnonline.ecosystem.origin.frontend.myaccount.model;

import biz.turnonline.ecosystem.account.client.model.Account;
import biz.turnonline.ecosystem.account.client.model.AccountPersonalAddress;
import biz.turnonline.ecosystem.account.client.model.Country;
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
