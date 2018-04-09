package biz.turnonline.ecosystem.origin.frontend.myaccount.event;

import biz.turnonline.ecosystem.origin.frontend.identity.AccountProfile;

/**
 * The account update event with {@link AccountProfile} payload.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class AccountUpdateEvent
{
    private final AccountProfile account;

    public AccountUpdateEvent( AccountProfile account )
    {
        this.account = account;
    }

    public AccountProfile getAccount()
    {
        return account;
    }


}
