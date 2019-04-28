package biz.turnonline.ecosystem.origin.frontend.steward;

import java.io.Serializable;

public class InvoicingConfigBillingContact
        implements Serializable
{
    private static final long serialVersionUID = -1405711894419721839L;

    private String email;

    private String prefix;

    private String firstName;

    private String middleName;

    private String lastName;

    private String suffix;

    private String phone;

    /**
     * The billing contact email.
     **/
    public InvoicingConfigBillingContact email( String email )
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
     * The billing contact, name prefix.
     **/
    public InvoicingConfigBillingContact prefix( String prefix )
    {
        this.prefix = prefix;
        return this;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public void setPrefix( String prefix )
    {
        this.prefix = prefix;
    }

    /**
     * The billing contact, first name.
     **/
    public InvoicingConfigBillingContact firstName( String firstName )
    {
        this.firstName = firstName;
        return this;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    /**
     * The billing contact, middle name.
     **/
    public InvoicingConfigBillingContact middleName( String middleName )
    {
        this.middleName = middleName;
        return this;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public void setMiddleName( String middleName )
    {
        this.middleName = middleName;
    }

    /**
     * The billing contact, last name.
     **/
    public InvoicingConfigBillingContact lastName( String lastName )
    {
        this.lastName = lastName;
        return this;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    /**
     * The billing contact, name suffix.
     **/
    public InvoicingConfigBillingContact suffix( String suffix )
    {
        this.suffix = suffix;
        return this;
    }

    public String getSuffix()
    {
        return suffix;
    }

    public void setSuffix( String suffix )
    {
        this.suffix = suffix;
    }

    /**
     * The billing contact, phone number.
     **/
    public InvoicingConfigBillingContact phone( String phone )
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
}

