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

import biz.turnonline.ecosystem.origin.frontend.steward.Account;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.model.Model;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Home page
 *
 * @author <a href="mailto:pohorelec@comvai.com">Jozef Pohorelec</a>
 */
public class Home
        extends DecoratedPage<Account>
{
    private static final long serialVersionUID = 6273666842603504497L;

    @Inject
    @Named( "credential.default.projectId" )
    private String projectId;

    public Home()
    {
        add( new ExternalLink( "link-gcloud", "" )
        {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onInitialize()
            {
                super.onInitialize();

                String gcloudUrl = "https://console.cloud.google.com/home/dashboard?project=" + projectId;
                setDefaultModelObject( gcloudUrl );
                setBody( Model.of( gcloudUrl ) );
            }
        } );

        add( new ExternalLink( "link-firebase", "" )
        {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onInitialize()
            {
                super.onInitialize();

                String gcloudUrl = "https://console.firebase.google.com/u/0/project/" + projectId + "/authentication/users";
                setDefaultModelObject( gcloudUrl );
                setBody( Model.of( gcloudUrl ) );
            }
        } );

        InputStream identityPropertiesContentStream = Home.class.getResourceAsStream( "/credential.properties" );
        String identityPropertiesContent;
        try
        {
            identityPropertiesContent = CharStreams.toString( new InputStreamReader( identityPropertiesContentStream, Charsets.UTF_8 ) );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
            identityPropertiesContent = "Error unable to load /api.properties";
        }
        add( new Label( "credential-properties", identityPropertiesContent ) );
    }
}
