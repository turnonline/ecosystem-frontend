package biz.turnonline.ecosystem.origin.frontend.model;

import biz.turnonline.ecosystem.account.client.model.Country;
import org.apache.wicket.markup.html.form.IChoiceRenderer;

/**
 * Code book type {@link Country} specific renderer.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class CountryRenderer
        implements IChoiceRenderer<Country>
{
    private static final long serialVersionUID = 2362737472204661897L;

    @Override
    public Object getDisplayValue( Country object )
    {
        return object.getLabel();
    }

    @Override
    public String getIdValue( Country object, int index )
    {
        return object.getCode();
    }
}
