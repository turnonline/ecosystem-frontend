package biz.turnonline.ecosystem.origin.frontend.content.model;

import java.io.Serializable;

/**
 * The event where address contract.
 *
 * @author <a href="mailto:medvegy@turnonline.biz">Aurel Medvegy</a>
 */
public class WhereAddress
        implements Serializable
{
    private static final long serialVersionUID = -7881692436622086069L;

    private String placeName;

    private String street;

    private String city;

    private String postcode;

    private String country;

    private String infoEmail;

    private String infoPhone;

    private Double latitude;

    private Double longitude;

    public WhereAddress()
    {
    }

    public boolean isAddressSame( AddressBuilder address )
    {
        AddressBuilder thisAddress = new AddressBuilder();

        thisAddress.setStreet( this.street );
        thisAddress.setCity( this.city );
        thisAddress.setCountry( this.country );

        return thisAddress.isSame( address );
    }

    public void setAddress( AddressBuilder builder )
    {
        if ( builder != null )
        {
            this.street = builder.getStreet();
            this.city = builder.getCity();
            this.country = builder.getCountry();
        }
    }

    /**
     * The event's where place name.
     *
     * @return the place name
     */
    public String getPlaceName()
    {
        return placeName;
    }

    /**
     * The event's where place name.
     *
     * @param placeName the event's where place name to be set
     */
    public void setPlaceName( String placeName )
    {
        this.placeName = placeName;
    }

    /**
     * The event's where street and street number.
     *
     * @return the street
     */
    public String getStreet()
    {
        return street;
    }

    /**
     * The event's where .
     *
     * @param street the event's where  to be set
     */
    public void setStreet( String street )
    {
        this.street = street;
    }

    /**
     * The event's where city.
     *
     * @return the city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * The event's where city.
     *
     * @param city the event's where city to be set
     */
    public void setCity( String city )
    {
        this.city = city;
    }

    /**
     * The event's where postal code.
     *
     * @return the postal address code
     */
    public String getPostcode()
    {
        return postcode;
    }

    /**
     * The event's where postal code.
     *
     * @param postcode the event's where postal code to be set
     */
    public void setPostcode( String postcode )
    {
        this.postcode = postcode;
    }

    /**
     * The event's where country (localized).
     *
     * @return the country
     */
    public String getCountry()
    {
        return country;
    }

    /**
     * The event's where country.
     *
     * @param country the event's where country to be set
     */
    public void setCountry( String country )
    {
        this.country = country;
    }

    /**
     * The event's where info email.
     *
     * @return the info email
     */
    public String getInfoEmail()
    {
        return infoEmail;
    }

    /**
     * The event's where info email.
     *
     * @param infoEmail the event's where info email to be set
     */
    public void setInfoEmail( String infoEmail )
    {
        this.infoEmail = infoEmail;
    }

    /**
     * The event's where info phone.
     *
     * @return the info phone
     */
    public String getInfoPhone()
    {
        return infoPhone;
    }

    /**
     * The event's where info phone.
     *
     * @param infoPhone the event's where info phone to be set
     */
    public void setInfoPhone( String infoPhone )
    {
        this.infoPhone = infoPhone;
    }

    /**
     * The event's where latitude geographic coordinate.
     *
     * @return the latitude
     */
    public Double getLatitude()
    {
        return latitude;
    }

    /**
     * The event's where latitude geographic coordinate.
     *
     * @param latitude the event's where latitude to be set
     */
    public void setLatitude( Double latitude )
    {
        this.latitude = latitude;
    }

    /**
     * The event's where longitude geographic coordinate.
     *
     * @return the longitude
     */
    public Double getLongitude()
    {
        return longitude;
    }

    /**
     * The event's where longitude geographic coordinate.
     *
     * @param longitude the event's where longitude to be set
     */
    public void setLongitude( Double longitude )
    {
        this.longitude = longitude;
    }

    @Override
    public String toString()
    {
        return "WhereAddress{" +
                "placeName='" + placeName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", postcode='" + postcode + '\'' +
                ", country='" + country + '\'' +
                ", infoEmail='" + infoEmail + '\'' +
                ", infoPhone='" + infoPhone + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
