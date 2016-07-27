package org.ctoolkit.turnonline.origin.frontend.myaccount.model;

import org.apache.wicket.model.IModel;
import org.ctoolkit.turnonline.shared.resource.Country;
import org.ctoolkit.turnonline.shared.resource.User;
import org.ctoolkit.turnonline.wicket.model.DropDownBridgeModel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

/**
 * The model to get {@link Country} and set related code to
 * {@link User#setPostalAddressState(String)} taken from the countries map.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class PostalAddressCountryModel
        extends DropDownBridgeModel<Country, User>
{
    private static final long serialVersionUID = -2873755499659424219L;

    /**
     * Constructor.
     *
     * @param model        the target model as a source of default choice code
     *                     or to be updated by selected choice
     * @param choicesModel the map of choices as a source for drop down list
     */
    public PostalAddressCountryModel( IModel<User> model, IModel<Map<String, Country>> choicesModel )
    {
        super( model, choicesModel );
    }

    @Override
    protected String getCode( @Nonnull User target )
    {
        return target.getPostalAddressState();
    }

    @Override
    protected void updateCode( @Nullable Country country, @Nonnull User target )
    {
        target.setPostalAddressState( country == null ? null : country.getCode() );
    }
}
