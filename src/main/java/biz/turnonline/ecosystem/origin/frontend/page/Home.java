package biz.turnonline.ecosystem.origin.frontend.page;

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
        extends DecoratedPage
{
    @Inject
    @Named( "credential.firebase.projectId" )
    private String projectId;

    public Home()
    {
        add( new ExternalLink( "link-gcloud", "" )
        {
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
            @Override
            protected void onInitialize()
            {
                super.onInitialize();

                String gcloudUrl = "https://console.firebase.google.com/u/0/project/" + projectId + "/authentication/users";
                setDefaultModelObject( gcloudUrl );
                setBody( Model.of( gcloudUrl ) );
            }
        } );

        InputStream identityPropertiesContentStream = Home.class.getResourceAsStream( "/identity.properties" );
        String identityPropertiesContent = null;
        try
        {
            identityPropertiesContent = CharStreams.toString(new InputStreamReader(identityPropertiesContentStream, Charsets.UTF_8));
        }
        catch ( IOException e )
        {
            e.printStackTrace();
            identityPropertiesContent = "Error unable to load /identity.properties";
        }
        add( new Label( "identity-properties", identityPropertiesContent ) );
    }
}
