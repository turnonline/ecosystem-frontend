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

import biz.turnonline.ecosystem.steward.model.Country;
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
