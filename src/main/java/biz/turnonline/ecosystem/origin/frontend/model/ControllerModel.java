package biz.turnonline.ecosystem.origin.frontend.model;

/**
 * @author <a href="mailto:pohorelec@turnonlie.biz">Jozef Pohorelec</a>
 */
public class ControllerModel
{
    private boolean loggedIn = false; // TODO: take from firebase

    private FirebaseConfig firebaseConfig;

    public boolean isLoggedIn()
    {
        return loggedIn;
    }

    public void setLoggedIn( boolean loggedIn )
    {
        this.loggedIn = loggedIn;
    }

    public FirebaseConfig getFirebaseConfig()
    {
        return firebaseConfig;
    }

    public void setFirebaseConfig( FirebaseConfig firebaseConfig )
    {
        this.firebaseConfig = firebaseConfig;
    }
}
