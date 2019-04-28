package biz.turnonline.ecosystem.origin.frontend.steward;

import java.io.Serializable;

public class NewsletterResponse
        implements Serializable
{
    private static final long serialVersionUID = 2879042299266752091L;

    private Boolean successful = false;

    /**
     * True if requested contact has matched the security code and request has been processed (subscribed / unsubscribed to / from the newsletter). If false, contact with requested email has been found and verification email has been sent to that contact email to let user verify newsletter subscription.  False will be returned also in the case if processing was ignored because of old non null modification date.
     **/
    public NewsletterResponse successful( Boolean successful )
    {
        this.successful = successful;
        return this;
    }

    public Boolean getSuccessful()
    {
        return successful;
    }

    public void setSuccessful( Boolean successful )
    {
        this.successful = successful;
    }
}

