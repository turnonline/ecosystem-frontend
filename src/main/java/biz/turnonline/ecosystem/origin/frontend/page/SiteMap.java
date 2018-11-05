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

package biz.turnonline.ecosystem.origin.frontend.page;

import biz.turnonline.ecosystem.origin.frontend.FrontendApplication;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.ctoolkit.wicket.turnonline.markup.html.page.BaseSiteMap;
import org.ctoolkit.wicket.turnonline.markup.html.page.SiteMapItem;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class SiteMap
        extends BaseSiteMap
{
    private static final long serialVersionUID = 1L;

    @Override
    protected List<SiteMapItem> getItems()
    {
        HttpServletRequest request = ( ( ServletWebRequest ) getRequest() ).getContainerRequest();
        String website = request.getRequestURL().toString().replaceAll( request.getRequestURI(), "" );

        List<SiteMapItem> items = new ArrayList<>();

        items.add( new SiteMapItem( website ) );
        items.add( new SiteMapItem( website + FrontendApplication.SIGNUP ) );
        items.add( new SiteMapItem( website + FrontendApplication.LOGIN ) );

        return items;
    }
}
