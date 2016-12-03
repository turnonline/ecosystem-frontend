package org.ctoolkit.turnonline.origin.frontend.myaccount.model;

import org.apache.wicket.model.IModel;
import org.ctoolkit.turnonline.shared.resource.Country;
import org.ctoolkit.turnonline.shared.resource.User;
import org.ctoolkit.wicket.standard.model.DropDownBridgeModel;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * The model to get {@link Country} and set related code to
 * {@link User#setPersonalAddressState(String)} taken from the countries map.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class PersonalAddressCountryModel
        extends DropDownBridgeModel<Country, User>
{
    private static final long serialVersionUID = 938155036445630296L;

    public PersonalAddressCountryModel( IModel<User> model, IModel<Map<String, Country>> countriesModel )
    {
        super( model, countriesModel );
    }

    @Override
    public String getCode( @Nonnull User target )
    {
        return target.getPersonalAddressState();
    }

    @Override
    public void updateCode( Country country, @Nonnull User target )
    {
        target.setPersonalAddressState( country == null ? null : country.getCode() );
    }
}
