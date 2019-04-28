package biz.turnonline.ecosystem.origin.frontend.steward;

import java.io.Serializable;

/**
 * The set of account default invoicing rules. These values might be overriden.
 **/
public class InvoicingConfig
        implements Serializable
{
    private static final long serialVersionUID = 8951306266688882807L;

    private String currency;

    private Integer numberOfDays;

    private Boolean hasBillingAddress;

    private InvoicingConfigBillingAddress billingAddress;

    private InvoicingConfigBillingContact billingContact;

    /**
     * The currency code (alphabetic code) based on the ISO 4217. If not set, the country default will be set.
     **/
    public InvoicingConfig currency( String currency )
    {
        this.currency = currency;
        return this;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency( String currency )
    {
        this.currency = currency;
    }

    /**
     * The default value of number of days for calculation of the invoice due date.
     **/
    public InvoicingConfig numberOfDays( Integer numberOfDays )
    {
        this.numberOfDays = numberOfDays;
        return this;
    }

    public Integer getNumberOfDays()
    {
        return numberOfDays;
    }

    public void setNumberOfDays( Integer numberOfDays )
    {
        this.numberOfDays = numberOfDays;
    }

    /**
     * If true, the billing address is not same as the company address and must be provided.
     **/
    public InvoicingConfig hasBillingAddress( Boolean hasBillingAddress )
    {
        this.hasBillingAddress = hasBillingAddress;
        return this;
    }

    public Boolean getHasBillingAddress()
    {
        return hasBillingAddress;
    }

    public void setHasBillingAddress( Boolean hasBillingAddress )
    {
        this.hasBillingAddress = hasBillingAddress;
    }

    /**
     * The billing address details. The address is being ignored until property “hasBillingAddress” is set to true.
     **/
    public InvoicingConfig billingAddress( InvoicingConfigBillingAddress billingAddress )
    {
        this.billingAddress = billingAddress;
        return this;
    }

    public InvoicingConfigBillingAddress getBillingAddress()
    {
        return billingAddress;
    }

    public void setBillingAddress( InvoicingConfigBillingAddress billingAddress )
    {
        this.billingAddress = billingAddress;
    }

    /**
     * The contact person related to billing. It might be presented at invoice or in email communication related to billing.
     **/
    public InvoicingConfig billingContact( InvoicingConfigBillingContact billingContact )
    {
        this.billingContact = billingContact;
        return this;
    }

    public InvoicingConfigBillingContact getBillingContact()
    {
        return billingContact;
    }

    public void setBillingContact( InvoicingConfigBillingContact billingContact )
    {
        this.billingContact = billingContact;
    }

}

