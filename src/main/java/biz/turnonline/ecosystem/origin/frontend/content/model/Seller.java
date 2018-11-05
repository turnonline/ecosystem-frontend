package biz.turnonline.ecosystem.origin.frontend.content.model;


import biz.turnonline.ecosystem.steward.facade.Domicile;

/**
 * The seller content contract interface.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public interface Seller
{
    /**
     * Seller's default payment link associated with this user account configuration.
     *
     * @return the default payment link
     */
    String getPaymentLink();

    /**
     * Seller's logo URL aimed to be shown to the user.
     *
     * @return the logo URL
     */
    String getLogoUrl();

    /**
     * Seller's terms and conditions URL.
     *
     * @return the terms URL
     */
    String getTermsUrl();

    /**
     * Seller's company account ID as an unique identification at TurnOnline.biz.
     *
     * @return the account ID
     */
    Long getAccountId();

    /**
     * Seller's login email.
     *
     * @return the login email
     */
    String getEmail();

    /**
     * Seller's company business name.
     *
     * @return the business name
     */
    String getBusinessName();

    /**
     * Seller's company street and street number.
     *
     * @return the street
     */
    String getStreet();

    /**
     * Seller's company domicile city.
     *
     * @return the city
     */
    String getCity();

    /**
     * Seller's company business country in form ISO 3166 alpha-2 country code.
     * It’s case insensitive.
     *
     * @return the country code
     */
    String getCountryCode();

    /**
     * Seller's company business country (localized).
     *
     * @return the country
     */
    String getCountry();

    /**
     * Seller's company business postal code.
     *
     * @return the postal address code
     */
    String getPostcode();

    /**
     * Seller's company business contact email.
     *
     * @return the contact email
     */
    String getContactEmail();

    /**
     * Seller's company business contact web site.
     *
     * @return the contact web site
     */
    String getContactWebSite();

    /**
     * Seller's company business identification number.
     *
     * @return the company ID
     */
    String getCompanyId();

    /**
     * Seller's company tax payer identification number of the company.
     *
     * @return the TAX ID.
     */
    String getTaxId();

    /**
     * Seller's company value added tax identification number (VAT ID) of the company.
     *
     * @return the VAT ID.
     */
    String getVatId();

    /**
     * Seller's boolean indication whether company is registered as VAT payer.
     *
     * @return if VAT payer returns true.
     */
    Boolean isVatPayer();

    /**
     * Seller's company default locale of the content to be shown to the end user.
     *
     * @return the default locale of the content
     */
    String getLocale();

    /**
     * Seller's business domicile.
     *
     * @return the business domicile
     */
    Domicile getDomicile();

    /**
     * Seller's company address latitude.
     *
     * @return the company address latitude
     */
    Double getLatitude();

    /**
     * Seller's company address longitude.
     *
     * @return the company address longitude
     */
    Double getLongitude();

    /**
     * Seller's boolean indication whether company is content use customized styling
     *
     * @return true if custom styling
     */
    @Deprecated
    Boolean isCustomStyling();

    /**
     * Seller's whether to show price excluding VAT as default.
     *
     * @return true if to show
     */
    Boolean isShowPriceExclVatAsDefault();

    /**
     * Seller's whether to show price VAT switcher.
     *
     * @return true if to show
     */
    Boolean isShowPriceVatSwitcher();
}
