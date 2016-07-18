package org.ctoolkit.turnonline.frontend.myaccount.event;

import org.ctoolkit.turnonline.shared.resource.User;

/**
 * The account update event with {@link org.ctoolkit.turnonline.shared.resource.User} payload.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class AccountUpdateEvent
{
    private final User account;

    public AccountUpdateEvent( User account )
    {
        this.account = account;
    }

    public User getAccount()
    {
        return account;
    }


}
