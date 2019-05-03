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
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

import java.util.List;

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

    @Override
    public LegalForm getObject( String id, IModel<? extends List<? extends LegalForm>> choices )
    {
        return null;
    }
}
