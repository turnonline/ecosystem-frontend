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

    private AccountBusiness business;

    private InvoicingConfig invoicing;

    public Long getId()
    {
        return id;
    }

    public AccountBusiness getBusiness()
    {
        return business;
    }

    public InvoicingConfig getInvoicing()
    {
        return invoicing;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public void setIdentityId( String identityId )
    {
        this.identityId = identityId;
    }

    public void setCompany( Boolean company )
    {
        this.company = company;
    }
}
