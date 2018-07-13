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
