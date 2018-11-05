package biz.turnonline.ecosystem.origin.frontend.content.model;

/**
 * The pay invoice content contract.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class PayInvoiceContent
        extends CommonContent
{
    private static final long serialVersionUID = -3400065084124595345L;

    private String invoiceKey;

    /* the ID of the account that has been invoiced */
    private Long issuedForId;

    private String pin;

    private String invoiceImageUrl;

    private Double totalPrice;

    private Double totalPriceExclVat;

    private String currency;

    private String paymentKey;

    private String variableSymbol;

    private String applyReverseChargeText;

    public PayInvoiceContent()
    {
    }

    public PayInvoiceContent( String name )
    {
        super( name );
    }

    public String getInvoiceKey()
    {
        return invoiceKey;
    }

    public void setInvoiceKey( String invoiceKey )
    {
        this.invoiceKey = invoiceKey;
    }

    public Long getIssuedForId()
    {
        return issuedForId;
    }

    public void setIssuedForId( Long issuedForId )
    {
        this.issuedForId = issuedForId;
    }

    public String getPin()
    {
        return pin;
    }

    public void setPin( String pin )
    {
        this.pin = pin;
    }

    public String getInvoiceImageUrl()
    {
        return invoiceImageUrl;
    }

    public void setInvoiceImageUrl( String invoiceImageUrl )
    {
        this.invoiceImageUrl = invoiceImageUrl;
    }

    public Double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice( Double totalPrice )
    {
        this.totalPrice = totalPrice;
    }

    public Double getTotalPriceExclVat()
    {
        return totalPriceExclVat;
    }

    public void setTotalPriceExclVat( Double totalPriceExclVat )
    {
        this.totalPriceExclVat = totalPriceExclVat;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency( String currency )
    {
        this.currency = currency;
    }

    public String getPaymentKey()
    {
        return paymentKey;
    }

    public void setPaymentKey( String paymentKey )
    {
        this.paymentKey = paymentKey;
    }

    public String getVariableSymbol()
    {
        return variableSymbol;
    }

    public void setVariableSymbol( String variableSymbol )
    {
        this.variableSymbol = variableSymbol;
    }

    public String getApplyReverseChargeText()
    {
        return applyReverseChargeText;
    }

    public void setApplyReverseChargeText( String applyReverseChargeText )
    {
        this.applyReverseChargeText = applyReverseChargeText;
    }

    @Override
    public String toString()
    {
        return "PayInvoiceContent{" +
                "invoiceKey='" + invoiceKey + '\'' +
                ", issuedForId=" + issuedForId +
                ", pin='" + pin + '\'' +
                ", invoiceImageUrl='" + invoiceImageUrl + '\'' +
                ", totalPrice=" + totalPrice +
                ", totalPriceExclVat=" + totalPriceExclVat +
                ", currency='" + currency + '\'' +
                ", paymentKey='" + paymentKey + '\'' +
                ", variableSymbol='" + variableSymbol + '\'' +
                ", applyReverseChargeText='" + applyReverseChargeText + '\'' +
                "} " + super.toString();
    }
}
