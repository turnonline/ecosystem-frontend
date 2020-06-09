package biz.turnonline.ecosystem.origin.frontend.model;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.annotation.Introspected;

/**
 * GWT configuration from application.yml for 'gwt' prefix
 *
 * @author <a href="mailto:pohorelec@turnonline.biz">Jozef Pohorelec</a>
 */
@Introspected
@ConfigurationProperties("gwt")
public class GwtConfig
{
    private String accountStewardStorage;
    private String productBillingStorage;
    private String billingProcessorStorage;

    private String accountStewardApiRoot;
    private String productBillingApiRoot;
    private String billingProcessorApiRoot;
    private String paymentProcessorApiRoot;
    private String searchApiRoot;

    private String mapsApiKey;

    public String getAccountStewardStorage()
    {
        return accountStewardStorage;
    }

    public void setAccountStewardStorage( String accountStewardStorage )
    {
        this.accountStewardStorage = accountStewardStorage;
    }

    public String getProductBillingStorage()
    {
        return productBillingStorage;
    }

    public void setProductBillingStorage( String productBillingStorage )
    {
        this.productBillingStorage = productBillingStorage;
    }

    public String getBillingProcessorStorage()
    {
        return billingProcessorStorage;
    }

    public void setBillingProcessorStorage( String billingProcessorStorage )
    {
        this.billingProcessorStorage = billingProcessorStorage;
    }

    public String getAccountStewardApiRoot()
    {
        return accountStewardApiRoot;
    }

    public void setAccountStewardApiRoot( String accountStewardApiRoot )
    {
        this.accountStewardApiRoot = accountStewardApiRoot;
    }

    public String getProductBillingApiRoot()
    {
        return productBillingApiRoot;
    }

    public void setProductBillingApiRoot( String productBillingApiRoot )
    {
        this.productBillingApiRoot = productBillingApiRoot;
    }

    public String getBillingProcessorApiRoot()
    {
        return billingProcessorApiRoot;
    }

    public void setBillingProcessorApiRoot( String billingProcessorApiRoot )
    {
        this.billingProcessorApiRoot = billingProcessorApiRoot;
    }

    public String getPaymentProcessorApiRoot()
    {
        return paymentProcessorApiRoot;
    }

    public void setPaymentProcessorApiRoot( String paymentProcessorApiRoot )
    {
        this.paymentProcessorApiRoot = paymentProcessorApiRoot;
    }

    public String getSearchApiRoot()
    {
        return searchApiRoot;
    }

    public void setSearchApiRoot( String searchApiRoot )
    {
        this.searchApiRoot = searchApiRoot;
    }

    public String getMapsApiKey()
    {
        return mapsApiKey;
    }

    public void setMapsApiKey( String mapsApiKey )
    {
        this.mapsApiKey = mapsApiKey;
    }
}
