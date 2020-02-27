/*
 * Copyright 2020 TurnOnline.biz s.r.o.
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

import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.ctoolkit.wicket.turnonline.markup.html.page.BaseRobots;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class Robots
        extends BaseRobots
{
    private static final long serialVersionUID = 1L;

    @Override
    protected String getUserAgent()
    {
        return "*";
    }

    @Override
    protected List<String> getDisallowUrls()
    {
        return new ArrayList<>();
    }

    @Override
    protected String getSiteMapUrl()
    {
        HttpServletRequest request = ( ( ServletWebRequest ) getRequest() ).getContainerRequest();
        return request.getRequestURL().toString().replaceAll( request.getRequestURI(), "" ) + "/sitemap.xml";
    }
}
