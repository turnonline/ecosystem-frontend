package org.ctoolkit.turnonline.origin.frontend.model;

import biz.turnonline.ecosystem.account.client.model.LegalForm;
import org.apache.wicket.markup.html.form.IChoiceRenderer;

/**
 * Code book type {@link LegalForm} specific renderer.
 *
 * @author <a href="mailto:jozef.pohorelec@ctoolkit.org">Jozef Pohorelec</a>
 */
public class LegalFormRenderer
        implements IChoiceRenderer<LegalForm>
{
    private static final long serialVersionUID = 8639690863495455104L;

    @Override
    public Object getDisplayValue( LegalForm object )
    {
        return object.getLabel();
    }

    @Override
    public String getIdValue( LegalForm object, int index )
    {
        return object.getCode();
    }
}
