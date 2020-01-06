package biz.turnonline.ecosystem.origin.frontend.steward;

import java.io.Serializable;
import java.util.Date;

/**
 * The set of account default invoicing rules. These values might be overridden.
 **/
public class InvoicingConfig
        implements Serializable
{
    private static final long serialVersionUID = -7674543367531601539L;

    private String currency;

    private String introductoryText;

    private String finalText;

    private Date modificationDate;

    private Integer numberOfDays;

    private Boolean hasBillingAddress;

    private Image stamp;

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
     * A default introductory text to be placed at invoice usually at top of the billing items. Use this to communicate a message to the invoice recipient.
     **/
    public InvoicingConfig introductoryText( String introductoryText )
    {
        this.introductoryText = introductoryText;
        return this;
    }

    public String getIntroductoryText()
    {
        return introductoryText;
    }

    public void setIntroductoryText( String introductoryText )
    {
        this.introductoryText = introductoryText;
    }

    /**
     * A default final text to be placed at invoice usually at the bottom. Use this to communicate a message to the invoice recipient.
     **/
    public InvoicingConfig finalText( String finalText )
    {
        this.finalText = finalText;
        return this;
    }

    public String getFinalText()
    {
        return finalText;
    }

    public void setFinalText( String finalText )
    {
        this.finalText = finalText;
    }

    /**
     * The date and time of the last modification of invoicing configuration values. Populated by the service.
     **/
    public InvoicingConfig modificationDate( Date modificationDate )
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
     * An optional image used to be placed at invoice as a stamp or sign.
     **/
    public InvoicingConfig stamp( Image stamp )
    {
        this.stamp = stamp;
        return this;
    }

    public Image getStamp()
    {
        return stamp;
    }

    public void setStamp( Image stamp )
    {
        this.stamp = stamp;
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

