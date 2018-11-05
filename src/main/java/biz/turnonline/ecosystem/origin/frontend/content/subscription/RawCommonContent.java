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

package biz.turnonline.ecosystem.origin.frontend.content.subscription;

import biz.turnonline.ecosystem.origin.frontend.content.model.CommonContent;
import com.googlecode.objectify.annotation.Entity;

/**
 * The {@link CommonContent} content holder.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
@Entity( name = "FE_RawCommonContent" )
public class RawCommonContent
        extends BaseRawContent<CommonContent>
{
    private static final long serialVersionUID = -3004729486459772962L;

    @SuppressWarnings( "unused" )
    RawCommonContent()
    {
        super( CommonContent.class );
    }

    public RawCommonContent( String name )
    {
        super( name, CommonContent.class );
    }
}
