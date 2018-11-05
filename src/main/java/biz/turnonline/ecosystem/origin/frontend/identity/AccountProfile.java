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
    private static final long serialVersionUID = 32061868949999028L;

    private String email;

    private String name;

    private String picture;

    private String uid;

    private String role;

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

    // TODO AccountProfile.getFirstName() is temporal
    public String getFirstName()
    {
        return getName();
    }

    // TODO AccountProfile.getLastName() is temporal
    public String getLastName()
    {
        return getName();
    }

    public String getRole()
    {
        if ( role == null )
        {
            return Role.SELLER;
        }
        return role;
    }

    public void setRole( String role )
    {
        this.role = role;
    }
}
