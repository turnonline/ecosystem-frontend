package biz.turnonline.ecosystem.origin.frontend.service;

import biz.turnonline.ecosystem.origin.frontend.model.FirebaseConfig;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.base.Strings;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;
import java.io.IOException;
import java.io.InputStream;

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
        String fileName = config.getFileName();
        InputStream stream = FirebaseFactory.class.getResourceAsStream( fileName );
        if ( stream == null )
        {
            String msg = "The file '" + fileName + "' has not been found";
            throw new IllegalArgumentException( msg );
        }

        return new FirebaseOptions.Builder().setCredentials( GoogleCredentials.fromStream( stream ) );
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
