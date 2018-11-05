package biz.turnonline.ecosystem.origin.frontend.myaccount.model;

import biz.turnonline.ecosystem.steward.model.Account;
import biz.turnonline.ecosystem.steward.model.AccountBusiness;
import biz.turnonline.ecosystem.steward.model.Country;
import org.apache.wicket.model.IModel;
import org.ctoolkit.wicket.standard.model.DropDownBridgeModel;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * The model to get {@link Country} and set related code to
 * {@link AccountBusiness#setDomicile(String)} taken from the countries map.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class CompanyDomicileModel
        extends DropDownBridgeModel<Country, Account>
{
    private static final long serialVersionUID = 1216844044792601998L;

    public CompanyDomicileModel( IModel<Account> model, IModel<Map<String, Country>> countriesModel )
    {
        super( model, countriesModel );
    }

    @Override
    public String getCode( @Nonnull Account target )
    {
        AccountBusiness business = target.getBusiness();
        return business == null ? null : business.getDomicile();
    }

    @Override
    public void updateCode( Country choice, @Nonnull Account target )
    {
        AccountBusiness business = target.getBusiness();
        if ( business == null )
        {
            business = new AccountBusiness();
            target.setBusiness( business );
        }
        business.setDomicile( choice == null ? null : choice.getCode() );
    }
}

