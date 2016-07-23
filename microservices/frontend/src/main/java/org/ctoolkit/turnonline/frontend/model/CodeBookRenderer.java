package org.ctoolkit.turnonline.frontend.model;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.ctoolkit.turnonline.shared.resource.BaseCodeBook;

/**
 * Code book type {@link BaseCodeBook} specific renderer.
 *
 * @author <a href="mailto:jozef.pohorelec@ctoolkit.org">Jozef Pohorelec</a>
 */
public class CodeBookRenderer<T extends BaseCodeBook>
        implements IChoiceRenderer<T>
{
    private static final long serialVersionUID = 2362737472204661897L;

    @Override
    public Object getDisplayValue( BaseCodeBook object )
    {
        return object.getLabel();
    }

    @Override
    public String getIdValue( BaseCodeBook object, int index )
    {
        return object.getCode();
    }
}
