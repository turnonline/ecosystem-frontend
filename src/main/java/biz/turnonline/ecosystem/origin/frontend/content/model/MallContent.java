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

package biz.turnonline.ecosystem.origin.frontend.content.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Mall model object wrapping {@link MallArticle} items.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class MallContent
        extends CommonContent
{
    private static final long serialVersionUID = 8757810421351178645L;

    private List<MallArticle> articles;

    public List<MallArticle> getArticles()
    {
        if ( articles == null )
        {
            articles = new ArrayList<>();
        }
        Collections.sort( articles );
        return articles;
    }

    public void add( MallArticle article )
    {
        if ( articles == null )
        {
            articles = new ArrayList<>();
        }
        articles.add( article );
    }
}
