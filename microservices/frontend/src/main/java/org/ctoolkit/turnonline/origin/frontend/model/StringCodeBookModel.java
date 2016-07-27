package org.ctoolkit.turnonline.origin.frontend.model;

import org.apache.wicket.model.IModel;
import org.ctoolkit.turnonline.shared.resource.BaseCodeBook;
import org.ctoolkit.turnonline.wicket.model.DropDownListBridgeModel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * The wicket model to convert code books' code string value of the target model into {@link T}
 * instance and vise versa.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class StringCodeBookModel<T extends BaseCodeBook>
        extends DropDownListBridgeModel<T, String>
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     *
     * @param targetModel the model as a target to set/get code books' code
     * @param expression  the property expression as path to code books' code at target model
     * @param listModel   the model of the code book choices
     */
    public StringCodeBookModel( IModel<?> targetModel, String expression, IModel<List<T>> listModel )
    {
        super( targetModel, expression, listModel );
    }

    @Override
    protected boolean equals( @Nonnull T t, @Nullable String target )
    {
        return target != null && target.equals( t.getCode() );
    }

    @Override
    protected String getValue( @Nullable T codeBook )
    {
        return codeBook == null ? null : codeBook.getCode();
    }
}
