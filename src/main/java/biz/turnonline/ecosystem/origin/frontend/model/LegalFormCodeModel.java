/*
 * Copyright 2018 Comvai, s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package biz.turnonline.ecosystem.origin.frontend.model;

import biz.turnonline.ecosystem.origin.frontend.steward.LegalForm;
import org.apache.wicket.model.IModel;
import org.ctoolkit.wicket.standard.model.DropDownListBridgeModel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * The wicket model to convert code books' code string value of the target model into {@link LegalForm}
 * instance and vise versa.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class LegalFormCodeModel
        extends DropDownListBridgeModel<LegalForm, String>
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     *
     * @param targetModel the model as a target to set/get code books' code
     * @param expression  the property expression as path to code books' code at target model
     * @param listModel   the model of the code book choices
     */
    public LegalFormCodeModel( IModel<?> targetModel, String expression, IModel<List<LegalForm>> listModel )
    {
        super( targetModel, expression, listModel );
    }

    @Override
    protected boolean equals( @Nonnull LegalForm t, @Nullable String target )
    {
        return target != null && target.equals( t.getCode() );
    }

    @Override
    protected String getValue( @Nullable LegalForm codeBook )
    {
        return codeBook == null ? null : codeBook.getCode();
    }
}
