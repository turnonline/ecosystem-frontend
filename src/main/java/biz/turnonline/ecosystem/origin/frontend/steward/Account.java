package biz.turnonline.ecosystem.origin.frontend.steward;

import java.io.Serializable;
import java.util.Date;

public class Account
        implements Serializable
{
    private static final long serialVersionUID = -8689839666628956879L;

    private Long id;

    private String email;

    private String identityId;

    private String contactEmail;

    private String role;

    private String locale;

    private String prefix;

    private String firstName;

    private String middleName;

    private String lastName;

    private String suffix;

    private Date modificationDate;

    private String zoneId;

    private AccountPersonalAddress personalAddress;

    private AccountPublicContact publicContact;

    private Boolean hasPostalAddress;

    private AccountPostalAddress postalAddress;

    private Boolean company;

    private AccountBusiness business;

    private InvoicingConfig invoicing;

    /**
     * The account unique identification
     **/
    public Account id( Long id )
    {
        this.id = id;
        return this;
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    /**
     * The login email address as the account unique identification, taken from the login provider as an authenticated email.
     **/
    public Account email( String email )
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
     * The email account unique identification within third-party provider system. Taken from the login provider.
     **/
    public Account identityId( String identityId )
    {
        this.identityId = identityId;
        return this;
    }

    public String getIdentityId()
    {
        return identityId;
    }

    public void setIdentityId( String identityId )
    {
        this.identityId = identityId;
    }

    /**
     * The email address considered as a contact email used for notification purposes related to this account (supports comma separated list). If it's not provided the account's email will be used by default.
     **/
    public Account contactEmail( String contactEmail )
    {
        this.contactEmail = contactEmail;
        return this;
    }

    public String getContactEmail()
    {
        return contactEmail;
    }

    public void setContactEmail( String contactEmail )
    {
        this.contactEmail = contactEmail;
    }

    /**
     * The account role
     **/
    public Account role( String role )
    {
        this.role = role;
        return this;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole( String role )
    {
        this.role = role;
    }

    /**
     * The preferred account language. ISO 639 alpha-2 or alpha-3 language code.
     **/
    public Account locale( String locale )
    {
        this.locale = locale;
        return this;
    }

    public String getLocale()
    {
        return locale;
    }

    public void setLocale( String locale )
    {
        this.locale = locale;
    }

    /**
     * The personal / sub account, person name prefix.
     **/
    public Account prefix( String prefix )
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
     * The personal / sub account, person first name.
     **/
    public Account firstName( String firstName )
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
     * The personal / sub account, person middle name.
     **/
    public Account middleName( String middleName )
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
     * The personal / sub account, person last name.
     **/
    public Account lastName( String lastName )
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
     * The personal / sub account, person name suffix.
     **/
    public Account suffix( String suffix )
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
     * The date and time of the last modification of entity values. Populated by the service.
     **/
    public Account modificationDate( Date modificationDate )
    {
        this.modificationDate = modificationDate;
        return this;
    }

    public Date getModificationDate()
    {
        return modificationDate;
    }

    public void setModificationDate( Date modificationDate )
    {
        this.modificationDate = modificationDate;
    }

    /**
     * A time-zone ID, such as Europe/Paris. Used to identify the rules how to render date time properties of the resources associated with this account.   It's a case sensitive. The default set of data is supplied by the IANA Time Zone Database (TZDB). This has region IDs of the form '{area}/{city}', such as 'Europe/Paris' or 'America/New_York'. If zone ID has an invalid format or cannot be found a bad request will be thrown.
     **/
    public Account zoneId( String zoneId )
    {
        this.zoneId = zoneId;
        return this;
    }

    public String getZoneId()
    {
        return zoneId;
    }

    public void setZoneId( String zoneId )
    {
        this.zoneId = zoneId;
    }

    /**
     * The personal account address details.
     **/
    public Account personalAddress( AccountPersonalAddress personalAddress )
    {
        this.personalAddress = personalAddress;
        return this;
    }

    public AccountPersonalAddress getPersonalAddress()
    {
        return personalAddress;
    }

    public void setPersonalAddress( AccountPersonalAddress personalAddress )
    {
        this.personalAddress = personalAddress;
    }

    /**
     * The public contact of the account, might be publicly available.
     **/
    public Account publicContact( AccountPublicContact publicContact )
    {
        this.publicContact = publicContact;
        return this;
    }

    public AccountPublicContact getPublicContact()
    {
        return publicContact;
    }

    public void setPublicContact( AccountPublicContact publicContact )
    {
        this.publicContact = publicContact;
    }

    /**
     * If true, the postal address is not same as the company address and must be provided.
     **/
    public Account hasPostalAddress( Boolean hasPostalAddress )
    {
        this.hasPostalAddress = hasPostalAddress;
        return this;
    }

    public Boolean getHasPostalAddress()
    {
        return hasPostalAddress;
    }

    public void setHasPostalAddress( Boolean hasPostalAddress )
    {
        this.hasPostalAddress = hasPostalAddress;
    }

    /**
     * The postal address details. The address is being ignored until property \"hasPostalAddress\" is set to true.
     **/
    public Account postalAddress( AccountPostalAddress postalAddress )
    {
        this.postalAddress = postalAddress;
        return this;
    }

    public AccountPostalAddress getPostalAddress()
    {
        return postalAddress;
    }

    public void setPostalAddress( AccountPostalAddress postalAddress )
    {
        this.postalAddress = postalAddress;
    }

    /**
     * The boolean indicating whether this account represents a business account. The missing value or false means it's a personal account.
     **/
    public Account company( Boolean company )
    {
        this.company = company;
        return this;
    }

    public Boolean getCompany()
    {
        return company;
    }

    public void setCompany( Boolean company )
    {
        this.company = company;
    }

    /**
     * The business account details. If the property \"company\" is false, the posted values will be stored anyway for further use, however account is still being considered as a personal account.
     **/
    public Account business( AccountBusiness business )
    {
        this.business = business;
        return this;
    }

    public AccountBusiness getBusiness()
    {
        return business;
    }

    public void setBusiness( AccountBusiness business )
    {
        this.business = business;
    }

    /**
     * The set of default invoicing rules. Valid only for an account representing a business account (configured by \"company\" property).
     **/
    public Account invoicing( InvoicingConfig invoicing )
    {
        this.invoicing = invoicing;
        return this;
    }

    public InvoicingConfig getInvoicing()
    {
        return invoicing;
    }

    public void setInvoicing( InvoicingConfig invoicing )
    {
        this.invoicing = invoicing;
    }
}

