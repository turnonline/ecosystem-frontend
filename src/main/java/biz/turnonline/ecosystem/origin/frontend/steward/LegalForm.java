package biz.turnonline.ecosystem.origin.frontend.steward;

import java.io.Serializable;

/**
 * The company legal form code-book definition.
 **/
public class LegalForm
        implements Serializable
{
    private static final long serialVersionUID = -5146954122542925448L;

    private String code;

    private String label;

    private String locale;

    private Integer version;

    /**
     * The ISO 3166 alpha-2 country code.
     **/
    public LegalForm code( String code )
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
     * The codebook value, legal form short name.
     **/
    public LegalForm label( String label )
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
    public LegalForm locale( String locale )
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
    public LegalForm version( Integer version )
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

