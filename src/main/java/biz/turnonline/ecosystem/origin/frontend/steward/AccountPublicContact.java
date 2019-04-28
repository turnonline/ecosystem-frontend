package biz.turnonline.ecosystem.origin.frontend.steward;

import java.io.Serializable;

public class AccountPublicContact
        implements Serializable
{
    private static final long serialVersionUID = -5966456037925688475L;

    private String email;

    private String name;

    private String phone;

    private String website;

    /**
     * The public contact email address.
     **/
    public AccountPublicContact email( String email )
    {
        this.email = email;
        return this;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    /**
     * The public contact person name.
     **/
    public AccountPublicContact name( String name )
    {
        this.name = name;
        return this;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    /**
     * The public contact phone number.
     **/
    public AccountPublicContact phone( String phone )
    {
        this.phone = phone;
        return this;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone( String phone )
    {
        this.phone = phone;
    }

    /**
     * The account website.
     **/
    public AccountPublicContact website( String website )
    {
        this.website = website;
        return this;
    }

    public String getWebsite()
    {
        return website;
    }

    public void setWebsite( String website )
    {
        this.website = website;
    }
}

