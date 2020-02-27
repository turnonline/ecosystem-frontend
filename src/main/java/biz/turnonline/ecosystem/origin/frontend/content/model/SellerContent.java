/*
 * Copyright 2020 TurnOnline.biz s.r.o.
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

import java.io.Serializable;

/**
 * The seller content contract.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class SellerContent
        implements Serializable, Seller
{
    private static final long serialVersionUID = -6651082688804554073L;

    private String paymentLink;

    private String logoUrl;

    private String termsUrl;

    private Long accountId;

    private String email;

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

    @Override
    public String getPaymentLink()
    {
        return paymentLink;
    }

    public void setPaymentLink( String paymentLink )
    {
        this.paymentLink = paymentLink;
    }

    @Override
    public String getLogoUrl()
    {
        return logoUrl;
    }

    public void setLogoUrl( String logoUrl )
    {
        this.logoUrl = logoUrl;
    }

    @Override
    public String getTermsUrl()
    {
        return termsUrl;
    }

    public void setTermsUrl( String termsUrl )
    {
        this.termsUrl = termsUrl;
    }

    @Override
    public Long getAccountId()
    {
        return accountId;
    }

    public void setAccountId( Long accountId )
    {
        this.accountId = accountId;
    }

    @Override
    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    @Override
    public String getBusinessName()
    {
        return businessName;
    }

    public void setBusinessName( String businessName )
    {
        this.businessName = businessName;
    }

    @Override
    public String getStreet()
    {
        return street;
    }

    public void setStreet( String street )
    {
        this.street = street;
    }

    @Override
    public String getCity()
    {
        return city;
    }

    public void setCity( String city )
    {
        this.city = city;
    }

    @Override
    public String getCountryCode()
    {
        return countryCode;
    }

    public void setCountryCode( String countryCode )
    {
        this.countryCode = countryCode;
    }

    @Override
    public String getCountry()
    {
        return country;
    }

    public void setCountry( String country )
    {
        this.country = country;
    }

    @Override
    public String getPostcode()
    {
        return postcode;
    }

    public void setPostcode( String postcode )
    {
        this.postcode = postcode;
    }

    @Override
    public String getContactEmail()
    {
        return contactEmail;
    }

    public void setContactEmail( String contactEmail )
    {
        this.contactEmail = contactEmail;
    }

    @Override
    public String getContactWebSite()
    {
        return contactWebSite;
    }

    public void setContactWebSite( String contactWebSite )
    {
        this.contactWebSite = contactWebSite;
    }

    @Override
    public String getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId( String companyId )
    {
        this.companyId = companyId;
    }

    @Override
    public String getTaxId()
    {
        return taxId;
    }

    public void setTaxId( String taxId )
    {
        this.taxId = taxId;
    }

    @Override
    public String getVatId()
    {
        return vatId;
    }

    public void setVatId( String vatId )
    {
        this.vatId = vatId;
    }

    @Override
    public Boolean isVatPayer()
    {
        return vatPayer;
    }

    public void setVatPayer( Boolean vatPayer )
    {
        this.vatPayer = vatPayer;
    }

    @Override
    public String getLocale()
    {
        return locale;
    }

    public void setLocale( String locale )
    {
        this.locale = locale;
    }

    public String getGwtLocale()
    {
        return getLocale();
    }

    @Override
    public Domicile getDomicile()
    {
        return domicile;
    }

    public void setDomicile( Domicile domicile )
    {
        this.domicile = domicile;
    }

    @Override
    public Double getLatitude()
    {
        return latitude;
    }

    public void setLatitude( Double latitude )
    {
        this.latitude = latitude;
    }

    @Override
    public Double getLongitude()
    {
        return longitude;
    }

    public void setLongitude( Double longitude )
    {
        this.longitude = longitude;
    }

    @Override
    public Boolean isCustomStyling()
    {
        return customStyling;
    }

    public void setCustomStyling( Boolean customStyling )
    {
        this.customStyling = customStyling;
    }

    @Override
    public Boolean isShowPriceExclVatAsDefault()
    {
        return showPriceExclVatAsDefault;
    }

    public void setShowPriceExclVatAsDefault( Boolean showPriceExclVatAsDefault )
    {
        this.showPriceExclVatAsDefault = showPriceExclVatAsDefault;
    }

    @Override
    public Boolean isShowPriceVatSwitcher()
    {
        return showPriceVatSwitcher;
    }

    public void setShowPriceVatSwitcher( Boolean showPriceVatSwitcher )
    {
        this.showPriceVatSwitcher = showPriceVatSwitcher;
    }

    public boolean isContactAddressSame( AddressBuilder address )
    {
        AddressBuilder thisAddress = new AddressBuilder();

        thisAddress.setStreet( this.street );
        thisAddress.setCity( this.city );
        thisAddress.setCountry( this.country );

        return thisAddress.isSame( address );
    }

    public void setContactAddress( AddressBuilder builder )
    {
        if ( builder != null )
        {
            this.street = builder.getStreet();
            this.city = builder.getCity();
            this.country = builder.getCountry();
        }
    }

    @Override
    public String toString()
    {
        return "SellerContent{" +
                "paymentLink='" + paymentLink + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", termsUrl='" + termsUrl + '\'' +
                ", accountId=" + accountId +
                ", email='" + email + '\'' +
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
                '}';
    }
}
