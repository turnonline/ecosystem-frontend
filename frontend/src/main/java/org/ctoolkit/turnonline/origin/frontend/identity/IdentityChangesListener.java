package org.ctoolkit.turnonline.origin.frontend.identity;

import org.ctoolkit.restapi.client.Identifier;
import org.ctoolkit.restapi.client.RestFacade;
import org.ctoolkit.services.identity.IdentityTroubleListener;
import org.ctoolkit.turnonline.shared.resource.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * The listener implementation handling password reset.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
@Singleton
public class IdentityChangesListener
        implements IdentityTroubleListener
{
    private static final Logger logger = LoggerFactory.getLogger( IdentityChangesListener.class );

    private final RestFacade resources;

    @Inject
    public IdentityChangesListener( RestFacade resources )
    {
        this.resources = resources;
    }

    @Override
    public void resetPassword( String email, String resetLink )
    {
        User.Password password = new User.Password( resetLink );
        resources.update( password ).identifiedBy( new Identifier( email ) ).finish();
    }

    @Override
    public void changeEmail( String localId, String email, String newEmail )
    {
        // not implemented yet
        logger.error( "changeEmail not implemented yet! Email request: " + email + ", new email: " + newEmail );
    }
}
