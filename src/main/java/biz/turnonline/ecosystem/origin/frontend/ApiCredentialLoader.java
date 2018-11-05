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

package biz.turnonline.ecosystem.origin.frontend;

import org.ctoolkit.restapi.client.ApiCredential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Api credential loader loads configuration for {@link ApiCredential}. It loads
 * default configuration file which is located in /api.properties. If system environment 'profile' is set
 * for instance to 'test' it will load property file /api-test.properties
 *
 * @author <a href="mailto:pohorelec@turnonline.biz">Jozef Pohorelec</a>
 */
public class ApiCredentialLoader
{
    private static Logger log = LoggerFactory.getLogger( ApiCredentialLoader.class );

    private String configurationFilePath = "/api.properties";

    private ApiCredential apiCredential;

    public void configure()
    {
        ApiCredential configuration = new ApiCredential();
        configuration.load( configurationFilePath );

        String profile = System.getProperty( "profile" );
        if ( profile != null && !profile.isEmpty() )
        {
            configurationFilePath = "/api-" + profile + ".properties";
            configuration = new ApiCredential();
            configuration.load( configurationFilePath );
        }

        log.info( "Using api properties file on path: {}", configurationFilePath );

        this.apiCredential = configuration;
    }

    public String getConfigurationFilePath()
    {
        return configurationFilePath;
    }

    public ApiCredential getApiCredential()
    {
        return apiCredential;
    }
}
