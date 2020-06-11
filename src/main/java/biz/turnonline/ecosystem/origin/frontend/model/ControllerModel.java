package biz.turnonline.ecosystem.origin.frontend.model;

import io.micronaut.core.annotation.Introspected;

import java.util.ResourceBundle;

/**
 * @author <a href="mailto:pohorelec@turnonline.biz">Jozef Pohorelec</a>
 */
@Introspected
public class ControllerModel
{
    private String locale;

    private FirebaseConfig firebaseConfig;

    private GwtConfig gwtConfig;

    private ResourceBundle messages;

    public String getLocale()
    {
        return locale;
    }

    public void setLocale( String locale )
    {
        this.locale = locale;
    }

    public FirebaseConfig getFirebaseConfig()
    {
        return firebaseConfig;
    }

    public void setFirebaseConfig( FirebaseConfig firebaseConfig )
    {
        this.firebaseConfig = firebaseConfig;
    }

    public GwtConfig getGwtConfig()
    {
        return gwtConfig;
    }

    public void setGwtConfig( GwtConfig gwtConfig )
    {
        this.gwtConfig = gwtConfig;
    }

    public ResourceBundle getMessages()
    {
        return messages;
    }

    public void setMessages( ResourceBundle messages )
    {
        this.messages = messages;
    }
}
