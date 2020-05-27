package biz.turnonline.ecosystem.origin.frontend.service;

import com.google.auth.oauth2.GoogleCredentials;
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
    public FirebaseAuth provideFirebaseAuth() throws IOException
    {
        // TODO: move to config
        String fileName = "identity-service-account.json";
        String databaseName = "turnon-t1";
        // TODO: move to config

        String databaseUrl = "https://" + databaseName + ".firebaseio.com";

        URL url = FirebaseFactory.class.getResource( fileName );
        FileInputStream serviceAccount = new FileInputStream( url.getPath() );

        FirebaseOptions.Builder builder = new FirebaseOptions.Builder()
                .setCredentials( GoogleCredentials.fromStream( serviceAccount ) )
                .setDatabaseUrl( databaseUrl );

        FirebaseOptions options = builder.build();
        FirebaseApp.initializeApp( options );

        return FirebaseAuth.getInstance();
    }
}
