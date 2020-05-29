package biz.turnonline.ecosystem.origin.frontend.model;

/**
 * @author <a href="mailto:pohorelec@turnonline.biz">Jozef Pohorelec</a>
 */
public class ControllerModel
{
    private boolean loggedIn;

    private Account account;

    private FirebaseConfig firebaseConfig;

    private GwtConfig gwtConfig;

    public boolean isLoggedIn()
    {
        return loggedIn;
    }

    public void setLoggedIn( boolean loggedIn )
    {
        this.loggedIn = loggedIn;
    }

    public Account getAccount()
    {
        return account;
    }

    public void setAccount( Account account )
    {
        this.account = account;
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
}
