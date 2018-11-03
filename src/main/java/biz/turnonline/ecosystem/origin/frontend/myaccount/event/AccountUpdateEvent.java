package biz.turnonline.ecosystem.origin.frontend.myaccount.event;

import biz.turnonline.ecosystem.steward.model.Account;

/**
 * The account update event with {@link Account} payload.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
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
