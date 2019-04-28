package biz.turnonline.ecosystem.origin.frontend.steward;

import java.io.Serializable;

/**
 * The country code-book definition. The list of ISO 3166 alpha-2 country codes with associated country names for each of the supported language. https://datahub.io/core/country-list
 **/
public class Country
        implements Serializable
{
    private static final long serialVersionUID = -4496674235013037247L;

    private String code;

    private String label;

    private String locale;

    private Integer version;

    /**
     * The ISO 3166 alpha-2 country code.
     **/
    public Country code( String code )
    {
        this.code = code;
        return this;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode( String code )
    {
        this.code = code;
    }

    /**
     * The codebook value, country name.
     **/
    public Country label( String label )
    {
        this.label = label;
        return this;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel( String label )
    {
        this.label = label;
    }

    /**
     * The label language. ISO 639 alpha-2 or alpha-3 language code.
     **/
    public Country locale( String locale )
    {
        this.locale = locale;
        return this;
    }

    public String getLocale()
    {
        return locale;
    }

    public void setLocale( String locale )
    {
        this.locale = locale;
    }

    /**
     * The codebook item version.
     **/
    public Country version( Integer version )
    {
        this.version = version;
        return this;
    }

    public Integer getVersion()
    {
        return version;
    }

    public void setVersion( Integer version )
    {
        this.version = version;
    }
}

