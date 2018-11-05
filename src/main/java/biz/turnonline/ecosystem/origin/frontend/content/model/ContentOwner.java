/*
 * Copyright 2018 Comvai, s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package biz.turnonline.ecosystem.origin.frontend.content.model;

import biz.turnonline.ecosystem.steward.facade.Domicile;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;

import java.io.Serializable;
import java.util.List;

/**
 * Content owner is a representation of the account at TurnOnline.biz.
 * Every content have an owner and it's represented with association to the this definition.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class ContentOwner
        implements Serializable, Seller
{
    private static final long serialVersionUID = -6644263010078648157L;

    private Long accountId;

    private String domain;

    private String subscriberAppId;

    private String analyticsAccount;

    private String headerDescription;

    private String headerKeywords;

    private String email;

    private String paymentLink;

    private String logoUrl;

    private String termsUrl;

    private String businessName;

    private String street;

    private String city;

    private String countryCode;

    private String country;

    private String postcode;

    private String contactEmail;

    private String contactWebSite;

    private String companyId;

    private String taxId;

    private String vatId;

    private Boolean vatPayer;

    private String locale;

    private Domicile domicile;

    private Double latitude;

    private Double longitude;

    private Boolean customStyling;

    private Boolean showPriceExclVatAsDefault;

    private Boolean showPriceVatSwitcher;

    private List<String> supportedLocales;

    private List<Gate> gateways;

    private List<String> loginProviders;

    /**
     * Returns the domain associated with the company account at TurnOnline.biz.
     *
     * @return the domain associated with the company
     */
    public String getDomain()
    {
        return domain;
    }

    /**
     * Sets a domain associated with the company account at TurnOnline.biz in form of subdomain, domain and TLD,
     * for example 'company.turnonline.biz' or even custom domain 'www.company.com'
     *
     * @param domain the domain associated with the company to be set
     */
    public void setDomain( String domain )
    {
        this.domain = domain;
    }

    /**
     * Returns the application ID used to compose final subscriber URL where to push final content.
     *
     * @return the subscriber application ID
     */
    public String getSubscriberAppId()
    {
        return subscriberAppId;
    }

    /**
     * Sets the application ID used to compose final subscriber URL where to push final content.
     *
     * @param subscriberAppId the subscriber application ID to be set
     */
    public void setSubscriberAppId( String subscriberAppId )
    {
        this.subscriberAppId = subscriberAppId;
    }

    /**
     * Returns the Google Analytics account identification.
     *
     * @return the Google Analytics account
     */
    public String getAnalyticsAccount()
    {
        return analyticsAccount;
    }

    /**
     * Sets the Google Analytics account identification.
     * If set, analytics account identification will be distributed to all public content.
     *
     * @param analyticsAccount the Google Analytics account identification to be set
     */
    public void setAnalyticsAccount( String analyticsAccount )
    {
        this.analyticsAccount = analyticsAccount;
    }

    /**
     * The HTML header description meta tag.
     *
     * @return the header description
     */
    public String getHeaderDescription()
    {
        return headerDescription;
    }

    /**
     * Property setter.
     *
     * @param headerDescription value to be set
     */
    public void setHeaderDescription( String headerDescription )
    {
        this.headerDescription = headerDescription;
    }

    /**
     * The HTML header keywords meta tag.
     *
     * @return the header keywords
     */
    public String getHeaderKeywords()
    {
        return headerKeywords;
    }

    /**
     * Property setter.
     *
     * @param headerKeywords value to be set
     */
    public void setHeaderKeywords( String headerKeywords )
    {
        this.headerKeywords = headerKeywords;
    }

    @Override
    public String getEmail()
    {
        return email;
    }

    /**
     * Property setter.
     *
     * @param email value to be set
     */
    public void setEmail( String email )
    {
        this.email = email;
    }

    @Override
    public Long getAccountId()
    {
        return accountId;
    }

    /**
     * Property setter.
     *
     * @param accountId value to be set
     */
    public void setAccountId( Long accountId )
    {
        this.accountId = accountId;
    }

    @Override
    public String getPaymentLink()
    {
        return paymentLink;
    }

    /**
     * Property setter.
     *
     * @param paymentLink value to be set
     */
    public void setPaymentLink( String paymentLink )
    {
        this.paymentLink = paymentLink;
    }

    @Override
    public String getLogoUrl()
    {
        return logoUrl;
    }

    /**
     * Property setter.
     *
     * @param logoUrl value to be set
     */
    public void setLogoUrl( String logoUrl )
    {
        this.logoUrl = logoUrl;
    }

    @Override
    public String getTermsUrl()
    {
        return termsUrl;
    }

    /**
     * Property setter.
     *
     * @param termsUrl value to be set
     */
    public void setTermsUrl( String termsUrl )
    {
        this.termsUrl = termsUrl;
    }

    @Override
    public String getBusinessName()
    {
        return businessName;
    }

    /**
     * Property setter.
     *
     * @param businessName value to be set
     */
    public void setBusinessName( String businessName )
    {
        this.businessName = businessName;
    }

    @Override
    public String getStreet()
    {
        return street;
    }

    /**
     * Property setter.
     *
     * @param street value to be set
     */
    public void setStreet( String street )
    {
        this.street = street;
    }

    @Override
    public String getCity()
    {
        return city;
    }

    /**
     * Property setter.
     *
     * @param city value to be set
     */
    public void setCity( String city )
    {
        this.city = city;
    }

    @Override
    public String getCountryCode()
    {
        return countryCode;
    }

    /**
     * Property setter.
     *
     * @param countryCode value to be set
     */
    public void setCountryCode( String countryCode )
    {
        this.countryCode = countryCode;
    }

    @Override
    public String getCountry()
    {
        return country;
    }

    /**
     * Property setter.
     *
     * @param country value to be set
     */
    public void setCountry( String country )
    {
        this.country = country;
    }

    @Override
    public String getPostcode()
    {
        return postcode;
    }

    /**
     * Property setter.
     *
     * @param postcode value to be set
     */
    public void setPostcode( String postcode )
    {
        this.postcode = postcode;
    }

    @Override
    public String getContactEmail()
    {
        return contactEmail;
    }

    /**
     * Property setter.
     *
     * @param contactEmail value to be set
     */
    public void setContactEmail( String contactEmail )
    {
        this.contactEmail = contactEmail;
    }

    @Override
    public String getContactWebSite()
    {
        return contactWebSite;
    }

    /**
     * Property setter.
     *
     * @param contactWebSite value to be set
     */
    public void setContactWebSite( String contactWebSite )
    {
        this.contactWebSite = contactWebSite;
    }

    @Override
    public String getCompanyId()
    {
        return companyId;
    }

    /**
     * Property setter.
     *
     * @param companyId value to be set
     */
    public void setCompanyId( String companyId )
    {
        this.companyId = companyId;
    }

    @Override
    public String getTaxId()
    {
        return taxId;
    }

    /**
     * Property setter.
     *
     * @param taxId value to be set
     */
    public void setTaxId( String taxId )
    {
        this.taxId = taxId;
    }

    @Override
    public String getVatId()
    {
        return vatId;
    }

    /**
     * Property setter.
     *
     * @param vatId value to be set
     */
    public void setVatId( String vatId )
    {
        this.vatId = vatId;
    }

    @Override
    public Boolean isVatPayer()
    {
        return vatPayer;
    }

    /**
     * Property setter.
     *
     * @param vatPayer value to be set
     */
    public void setVatPayer( Boolean vatPayer )
    {
        this.vatPayer = vatPayer;
    }

    @Override
    public String getLocale()
    {
        return locale;
    }

    /**
     * Property setter.
     *
     * @param locale value to be set
     */
    public void setLocale( String locale )
    {
        this.locale = locale;
    }

    @Override
    public Domicile getDomicile()
    {
        return domicile;
    }

    /**
     * Property setter.
     *
     * @param domicile value to be set
     */
    public void setDomicile( Domicile domicile )
    {
        this.domicile = domicile;
    }

    @Override
    public Double getLatitude()
    {
        return latitude;
    }

    /**
     * Property setter.
     *
     * @param latitude value to be set
     */
    public void setLatitude( Double latitude )
    {
        this.latitude = latitude;
    }

    @Override
    public Double getLongitude()
    {
        return longitude;
    }

    /**
     * Property setter.
     *
     * @param longitude value to be set
     */
    public void setLongitude( Double longitude )
    {
        this.longitude = longitude;
    }

    @Override
    public Boolean isCustomStyling()
    {
        return customStyling;
    }

    /**
     * Property setter.
     *
     * @param customStyling value to be set
     */
    public void setCustomStyling( Boolean customStyling )
    {
        this.customStyling = customStyling;
    }

    @Override
    public Boolean isShowPriceExclVatAsDefault()
    {
        return showPriceExclVatAsDefault;
    }

    /**
     * Property setter.
     *
     * @param showPriceExclVatAsDefault value to be set
     */
    public void setShowPriceExclVatAsDefault( Boolean showPriceExclVatAsDefault )
    {
        this.showPriceExclVatAsDefault = showPriceExclVatAsDefault;
    }

    @Override
    public Boolean isShowPriceVatSwitcher()
    {
        return showPriceVatSwitcher;
    }

    /**
     * Property setter.
     *
     * @param showPriceVatSwitcher value to be set
     */
    public void setShowPriceVatSwitcher( Boolean showPriceVatSwitcher )
    {
        this.showPriceVatSwitcher = showPriceVatSwitcher;
    }

    /**
     * Returns the list of all supported locales that might be used in the final content.
     *
     * @return the list of locales
     */
    public List<String> getSupportedLocales()
    {
        return supportedLocales;
    }

    /**
     * Sets the list of all supported locales that might be used in the final content.
     *
     * @param supportedLocales the list of locales to be set
     */
    public void setSupportedLocales( List<String> supportedLocales )
    {
        this.supportedLocales = supportedLocales;
    }

    /**
     * Returns the list of supported payment methods that customer can use to pay.
     *
     * @return the list of supported payment methods
     */
    public List<Gate> getGateways()
    {
        return MoreObjects.firstNonNull( gateways, ImmutableList.<Gate>of() );
    }

    /**
     * Property setter.
     *
     * @param gateways value to be set
     */
    public void setGateways( List<Gate> gateways )
    {
        this.gateways = gateways;
    }

    /**
     * Returns the list of supported third-party login providers that customer can use to log in.
     *
     * @return the list of supported login providers
     */
    public List<String> getLoginProviders()
    {
        return loginProviders;
    }

    /**
     * Property setter.
     *
     * @param loginProviders value to be set
     */
    public void setLoginProviders( List<String> loginProviders )
    {
        this.loginProviders = loginProviders;
    }

    @Override
    public String toString()
    {
        return "ContentOwner{" +
                "accountId=" + accountId +
                ", domain='" + domain + '\'' +
                ", subscriberAppId='" + subscriberAppId + '\'' +
                ", analyticsAccount='" + analyticsAccount + '\'' +
                ", headerDescription='" + headerDescription + '\'' +
                ", headerKeywords='" + headerKeywords + '\'' +
                ", email='" + email + '\'' +
                ", paymentLink='" + paymentLink + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", termsUrl='" + termsUrl + '\'' +
                ", businessName='" + businessName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", country='" + country + '\'' +
                ", postcode='" + postcode + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", contactWebSite='" + contactWebSite + '\'' +
                ", companyId='" + companyId + '\'' +
                ", taxId='" + taxId + '\'' +
                ", vatId='" + vatId + '\'' +
                ", vatPayer=" + vatPayer +
                ", locale='" + locale + '\'' +
                ", domicile=" + domicile +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", customStyling=" + customStyling +
                ", showPriceExclVatAsDefault=" + showPriceExclVatAsDefault +
                ", showPriceVatSwitcher=" + showPriceVatSwitcher +
                ", gateways=" + gateways +
                ", loginProviders=" + loginProviders +
                '}';
    }
}
