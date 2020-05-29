package biz.turnonline.ecosystem.origin.frontend.model;

/**
 * Simplified Account DTO for account steward api
 *
 * @author <a href="mailto:pohorelec@turnonline.biz">Jozef Pohorelec</a>
 */
public class Account
{
    private Long id;

    private String email;

    private String firstName;

    private String identityId;

    private Boolean company;

    private String locale;

    private AccountBusiness business;

    private InvoicingConfig invoicing;

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public String getIdentityId()
    {
        return identityId;
    }

    public void setIdentityId( String identityId )
    {
        this.identityId = identityId;
    }

    public Boolean getCompany()
    {
        return company;
    }

    public void setCompany( Boolean company )
    {
        this.company = company;
    }

    public String getLocale()
    {
        return locale;
    }

    public void setLocale( String locale )
    {
        this.locale = locale;
    }

    public AccountBusiness getBusiness()
    {
        return business;
    }

    public void setBusiness( AccountBusiness business )
    {
        this.business = business;
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
