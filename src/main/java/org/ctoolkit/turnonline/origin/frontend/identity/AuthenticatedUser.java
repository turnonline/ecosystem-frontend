package org.ctoolkit.turnonline.origin.frontend.identity;

import org.ctoolkit.turnonline.shared.resource.User;

/**
 * Authenticated user instance.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class AuthenticatedUser
        extends User
{
    private static final long serialVersionUID = -2765232701720348301L;

    public AuthenticatedUser()
    {
    }

    public AuthenticatedUser( User user )
    {
        super( user );
    }
}
