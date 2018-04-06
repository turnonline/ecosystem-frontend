package biz.turnonline.ecosystem.origin.frontend.myaccount.event;

import biz.turnonline.ecosystem.account.client.model.Account;

/**
 * The account update event with {@link Account} payload.
 *
 * @author <a href="mailto:aurel.medvegy@ctoolkit.org">Aurel Medvegy</a>
 */
public class AccountUpdateEvent
{
    private final Account account;

    public AccountUpdateEvent( Account account )
    {
        this.account = account;
    }

    public Account getAccount()
    {
        return account;
    }


}
