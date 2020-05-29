package biz.turnonline.ecosystem.origin.frontend.service;

import biz.turnonline.ecosystem.origin.frontend.model.FirebaseConfig;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.base.Strings;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

/**
 * @author <a href="mailto:pohorelec@turnonlie.biz">Jozef Pohorelec</a>
 */
@Factory
public class FirebaseFactory
{
    @Singleton
    public FirebaseAuth provideFirebaseAuth( FirebaseConfig config ) throws IOException
    {
        FirebaseOptions.Builder builder;

        if ( config.isCredentialOn() )
        {
            builder = createFromFile( config );
        }
        else
        {
            builder = createFromApplicationDefault( config );
        }

        String databaseName = databaseName( config );
        String databaseUrl = "https://" + databaseName + ".firebaseio.com";
        builder.setDatabaseUrl( databaseUrl );

        if ( !Strings.isNullOrEmpty( config.getServiceAccountEmail() ) )
        {
            builder.setServiceAccountId( config.getServiceAccountEmail() );
        }

        FirebaseOptions options = builder.build();
        FirebaseApp.initializeApp( options );

        return FirebaseAuth.getInstance();
    }

    private FirebaseOptions.Builder createFromFile( FirebaseConfig config ) throws IOException
    {
        URL url = FirebaseFactory.class.getResource( config.getFileName() );
        FileInputStream serviceAccount = new FileInputStream( url.getPath() );

        return new FirebaseOptions.Builder().setCredentials( GoogleCredentials.fromStream( serviceAccount ) );
    }

    private FirebaseOptions.Builder createFromApplicationDefault( FirebaseConfig config ) throws IOException
    {
        return new FirebaseOptions.Builder().setCredentials( GoogleCredentials.getApplicationDefault() );
    }

    private String databaseName( FirebaseConfig config )
    {
        if ( !Strings.isNullOrEmpty( config.getDatabaseName() ) )
        {
            return config.getDatabaseName();
        }

        return config.getProjectId();
    }
}
