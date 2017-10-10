package org.ctoolkit.turnonline.origin.frontend.identity;

import org.ctoolkit.turnonline.shared.resource.AccountConfig;
import org.ctoolkit.turnonline.shared.resource.IPayment.Gate;
import org.ctoolkit.turnonline.shared.resource.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public List<Gate> getPaymentGateways()
    {
        AccountConfig config = this.getConfig();

        if ( config == null )
        {
            return Collections.emptyList();
        }

        String gateways = config.getPaymentGateways();

        if ( gateways == null || "".equals( gateways ) )
        {
            return Collections.emptyList();
        }

        String[] split = gateways.split( ";" );
        List<Gate> list = new ArrayList<>();

        for ( String s : split )
        {
            list.add( Gate.valueOf( s ) );
        }

        return list;
    }
}
