package biz.turnonline.ecosystem.origin.frontend.steward;

import java.io.Serializable;

public class AccountPersonalAddress
        implements Serializable
{
    private static final long serialVersionUID = -4346428053148415418L;

    private String street;

    private String city;

    private String country;

    private String postcode;

    private Double latitude;

    private Double longitude;

    /**
     * The personal street and street number
     **/
    public AccountPersonalAddress street( String street )
    {
        this.street = street;
        return this;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet( String street )
    {
        this.street = street;
    }

    /**
     * The personal address city.
     **/
    public AccountPersonalAddress city( String city )
    {
        this.city = city;
        return this;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity( String city )
    {
        this.city = city;
    }

    /**
     * The personal address ISO 3166 alpha-2 country code. It’s case insensitive.
     **/
    public AccountPersonalAddress country( String country )
    {
        this.country = country;
        return this;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry( String country )
    {
        this.country = country;
    }

    /**
     * The personal address post code.
     **/
    public AccountPersonalAddress postcode( String postcode )
    {
        this.postcode = postcode;
        return this;
    }

    public String getPostcode()
    {
        return postcode;
    }

    public void setPostcode( String postcode )
    {
        this.postcode = postcode;
    }

    /**
     * The personal address latitude geographic coordinate, generated by the service.
     **/
    public AccountPersonalAddress latitude( Double latitude )
    {
        this.latitude = latitude;
        return this;
    }

    public Double getLatitude()
    {
        return latitude;
    }

    public void setLatitude( Double latitude )
    {
        this.latitude = latitude;
    }

    /**
     * The personal address longitude geographic coordinate, generated by the service.
     **/
    public AccountPersonalAddress longitude( Double longitude )
    {
        this.longitude = longitude;
        return this;
    }

    public Double getLongitude()
    {
        return longitude;
    }

    public void setLongitude( Double longitude )
    {
        this.longitude = longitude;
    }
}
