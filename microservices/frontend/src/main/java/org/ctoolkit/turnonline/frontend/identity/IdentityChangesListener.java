package org.ctoolkit.turnonline.frontend.identity;

import org.ctoolkit.restapi.client.ResourceFacade;
import org.ctoolkit.services.identity.IdentityTroubleListener;
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

    private final ResourceFacade resources;

    @Inject
    public IdentityChangesListener( ResourceFacade resources )
    {
        this.resources = resources;
    }

    @Override
    public void resetPassword( String email, String resetLink )
    {
        // not implemented yet
        logger.error( "resetPassword not implemented yet! Email: " + email + ", resetLink: " + resetLink );
    }

    @Override
    public void changeEmail( String localId, String email, String newEmail )
    {
        // not implemented yet
        logger.error( "changeEmail not implemented yet! Email request: " + email + ", new email: " + newEmail );
    }
}
