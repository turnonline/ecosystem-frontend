package org.ctoolkit.turnonline.frontend.model;

import org.ctoolkit.turnonline.shared.resource.LegalForm;

/**
 * The {@link LegalForm} list model.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class LegalFormListModel
        extends CodeBookListModel<LegalForm>
{
    private static final long serialVersionUID = 1L;

    public LegalFormListModel()
    {
    }

    @Override
    protected Class<LegalForm> type()
    {
        return LegalForm.class;
    }
}
