package biz.turnonline.ecosystem.origin.frontend.identity;

import com.google.firebase.auth.FirebaseToken;

import java.io.Serializable;

/**
 * The authenticated user profile properties holder, the mirror of the {@link FirebaseToken}.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class AccountProfile
        implements Serializable
{
    private static final long serialVersionUID = 8965549603743224988L;

    private String email;

    private String name;

    private String picture;

    private String uid;

    AccountProfile( FirebaseToken token )
    {
        this.email = token.getEmail();
        this.name = token.getName();
        this.picture = token.getPicture();
        this.uid = token.getUid();
    }

    public String getEmail()
    {
        return email;
    }

    public String getName()
    {
        return name;
    }

    public String getPicture()
    {
        return picture;
    }

    public String getUid()
    {
        return uid;
    }
}
