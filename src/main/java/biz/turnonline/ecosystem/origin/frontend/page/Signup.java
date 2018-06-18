package biz.turnonline.ecosystem.origin.frontend.page;

import org.ctoolkit.wicket.standard.identity.FirebaseConfig;
import org.ctoolkit.wicket.standard.identity.behavior.FirebaseAppInit;

import javax.inject.Inject;

/**
 * Signup
 *
 * @author <a href="mailto:pohorelec@comvai.com">Jozef Pohorelec</a>
 */
public class Signup
        extends DecoratedPage
{
    @Inject
    private FirebaseConfig firebaseConfig;

    public Signup()
    {
        add( new FirebaseAppInit( firebaseConfig, true ) );
    }
}
